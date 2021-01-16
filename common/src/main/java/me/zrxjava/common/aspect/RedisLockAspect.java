package me.zrxjava.common.aspect;

import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.annotation.RedisLock;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.component.redis.RedisLockHolder;
import me.zrxjava.common.component.redis.RedisLockUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁切面
 * @author void
 * @create 2021-01-15
 */
@Slf4j
@Aspect
@Component
public class RedisLockAspect {

    private final ExpressionParser parser = new SpelExpressionParser();

    private final LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    /**
     * 线程池，维护keyAliveTime
     */
    private static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("redisLock-schedule-pool").daemon(true).build());
    /**
     * 存储目前有效的key定义
     */
    private static final ConcurrentLinkedQueue<RedisLockHolder> HOLDER_LIST = new ConcurrentLinkedQueue();

    @Resource
    private RedisLockUtil redisLockUtil;

    {
        // 两秒执行一次「续时」操作
        SCHEDULER.scheduleAtFixedRate(() -> {
            // 这里记得加 try-catch，否者报错后定时任务将不会再执行=-=
            Iterator<RedisLockHolder> iterator = HOLDER_LIST.iterator();
            while (iterator.hasNext()) {
                RedisLockHolder holder = iterator.next();
                log.debug("执行定时任务，持锁对象为:{}",holder);
                // 判空
                if (holder == null) {
                    iterator.remove();
                    continue;
                }
                // 判断 key 是否还有效，无效的话进行移除
                if (redisLockUtil.tryAcquire(holder.getBusinessKey()) == null) {
                    iterator.remove();
                    continue;
                }
                // 判断是否进入最后三分之一时间
                long curTime = System.currentTimeMillis();
                boolean shouldExtend = (holder.getLastModifyTime() + holder.getModifyPeriod()) <= curTime;
                if (shouldExtend) {
                    log.debug("开始为:{}锁续期",holder.getBusinessKey());
                    holder.setLastModifyTime(curTime);
                    redisLockUtil.tryRenew(holder.getBusinessKey(), holder.getLockTime(), TimeUnit.MILLISECONDS);
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    @Pointcut("@annotation(redisLock)")
    public void aspect(RedisLock redisLock){}

    @Around(value = "aspect(redisLock)", argNames = "pjp,redisLock")
    public Object execute(ProceedingJoinPoint pjp, RedisLock redisLock) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        //获取方法的参数值
        String className = methodSignature.getDeclaringType().getName();
        String keyName = redisLock.key();
        String businessKey;
        if (keyName.startsWith("#")){
            //根据spel表达式获取值
            EvaluationContext context = this.bindParam(targetMethod, pjp.getArgs());
            Expression expression = parser.parseExpression(keyName);
            Object value = expression.getValue(context);
            // key = 类名::参数名::参数值 value= 到期时间
            businessKey = className + "::" + keyName + "::" + value;
        }else {
            // 非spel表达式 key = 类名::key
            businessKey = className + "::" + keyName;
        }
        String uniqueValue = UUID.randomUUID().toString();
        // 加锁
        Object result;
        try {
            boolean isSuccess;
            for (int i = 0; i < redisLock.tryCount(); i++) {
                isSuccess = redisLockUtil.tryLock(businessKey,uniqueValue,redisLock.lockTime());
                if (isSuccess){
                    break;
                }
                Thread.sleep(redisLock.retryWait());
                log.info("获取分布式锁:{}失败,开始重试，当前重试次数为:{},最大重试次数为:{}",businessKey,i+1,redisLock.tryCount());
                if (i == redisLock.tryCount()-1){
                    return ResponseResult.failed("当前系统繁忙，请稍后再试");
                }
            }
            HOLDER_LIST.add(new RedisLockHolder(businessKey, redisLock.lockTime(), System.currentTimeMillis(), Thread.currentThread()));
            result = pjp.proceed();
            // 线程被中断，抛出异常，中断此次请求
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException("You had been interrupted =-=");
            }
        } catch (Exception e) {
            log.error("has some error, please check again", e);
            return ResponseResult.failed("系统内部错误，请稍后再试");
        } finally {
            // 请求结束后，释放锁
            redisLockUtil.unLock(businessKey,uniqueValue);
        }
        return result;
    }

    /**
     * 将方法的参数名和参数值绑定
     *
     * @param method 方法，根据方法获取参数名
     * @param args   方法的参数值
     * @return
     */
    private EvaluationContext bindParam(Method method, Object[] args) {
        //获取方法的参数名
        String[] params = discoverer.getParameterNames(method);
        //将参数名与参数值对应起来
        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < (params != null ? params.length : 0); len++) {
            context.setVariable(params[len], args[len]);
        }
        return context;
    }

}
