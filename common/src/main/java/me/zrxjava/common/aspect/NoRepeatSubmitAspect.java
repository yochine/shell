package me.zrxjava.common.aspect;

import me.zrxjava.common.annotation.NoRepeatSubmit;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.component.redis.RedisUtil;
import me.zrxjava.common.utils.ServletUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 防止重复提交切面
 * @author void
 * @create 2020-11-04
 */
@Aspect
@Component
public class NoRepeatSubmitAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Value("${jwt.tokenHeader}")
    private String authorization;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void aspect(NoRepeatSubmit noRepeatSubmit) {
    }

    @Around(value = "aspect(noRepeatSubmit)", argNames = "pjp,noRepeatSubmit")
    public Object execute(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {

        HttpServletRequest request = ServletUtils.getRequest();
        String token = request.getHeader(authorization);
        String key = token + ":" + request.getServletPath() + ":" + (StringUtils.isEmpty(request.getQueryString()) ? "" : request.getQueryString());
        // 如果缓存中有这个url视为重复提交
        if (Objects.isNull(redisUtil.getCacheObject(key))) {
            Object o = pjp.proceed();
            redisUtil.setCacheObject(key, key, noRepeatSubmit.time(),noRepeatSubmit.timeUnit());
            return o;
        }
        return ResponseResult.failed("请勿重复提交");
    }

}
