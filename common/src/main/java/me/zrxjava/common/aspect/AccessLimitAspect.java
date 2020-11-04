package me.zrxjava.common.aspect;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.annotation.AccessLimit;
import me.zrxjava.common.exception.BusinessException;
import me.zrxjava.common.utils.ServletUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author void
 * @create 2020-10-23
 */
@Slf4j
@Aspect
@Order(-1)
@Component
public class AccessLimitAspect {


    @Pointcut("@annotation(accessLimit)")
    public void aspect(AccessLimit accessLimit) {
    }

    /**
     * 添加速率.保证是单例的
     */
    private static RateLimiter rateLimiter = RateLimiter.create(1000);

    private static Map<String, RateLimiter> limitMap = Maps.newConcurrentMap();


    @Around(value = "aspect(accessLimit)")
    public Object execute(ProceedingJoinPoint pjp,AccessLimit accessLimit) throws Throwable {
        HttpServletRequest request = ServletUtils.getRequest();
        // 或者url(存在map集合的key)
        String url = request.getRequestURI();
        if (!limitMap.containsKey(url)) {
            // 创建令牌桶
            rateLimiter = RateLimiter.create(accessLimit.perSecond());
            limitMap.put(url, rateLimiter);
            log.info("<<=================>>  请求{},创建令牌桶,容量{} 成功!!!",url,accessLimit.perSecond());
        }
        rateLimiter = limitMap.get(url);
        if (!rateLimiter.tryAcquire(accessLimit.timeOut(), accessLimit.timeOutUnit())) {
            log.error("Error--url:{}, ip:{},time:{},获取令牌失败.",url,request.getRemoteUser(), LocalDateTime.now());
            throw new BusinessException("当前访问人数过多，请稍后再试!");
        }
        return pjp.proceed();
    }
}
