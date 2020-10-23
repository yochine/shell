package me.zrxjava.common.aspect;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.annotation.AccessLimit;
import me.zrxjava.common.exception.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * @author void
 * @create 2020-10-23
 */
@Slf4j
public class AccessLimitAspect extends AbstractAspectManager{


    public AccessLimitAspect(AspectApi aspectApi) {
        super(aspectApi);
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        super.doHandlerAspect(pjp,method);
        execute(pjp,method);
        return null;
    }

    /**
     * 添加速率.保证是单例的
     */
    private static RateLimiter rateLimiter = RateLimiter.create(1000);

    private static Map<String, RateLimiter> limitMap = Maps.newConcurrentMap();

    @Override
    public Object execute(ProceedingJoinPoint pjp,Method method) {
        AccessLimit lxRateLimit = method.getAnnotation(AccessLimit.class);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 或者url(存在map集合的key)
        String url = request.getRequestURI();
        if (!limitMap.containsKey(url)) {
            // 创建令牌桶
            rateLimiter = RateLimiter.create(lxRateLimit.perSecond());
            limitMap.put(url, rateLimiter);
            log.info("<<=================  请求{},创建令牌桶,容量{} 成功!!!",url,lxRateLimit.perSecond());
        }
        rateLimiter = limitMap.get(url);
        if (!rateLimiter.tryAcquire(lxRateLimit.timeOut(), lxRateLimit.timeOutUnit())) {
            log.error("Error ---时间:{},获取令牌失败.", LocalDateTime.now());
            throw new BusinessException("当前登录人数过多，请稍后再试!");
        }
        return null;
    }
}
