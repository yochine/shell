package me.zrxjava.system.support.aspect;

import lombok.SneakyThrows;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.enums.ResultCode;
import me.zrxjava.common.utils.ServletUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 演示模式切面
 * @author void
 * @create 2021-04-27
 */
@Order(-1)
@Aspect
@Component
public class DemoAspect {


    @Value("${login.mode}")
    private String mode;

    @Pointcut("execution(* me.zrxjava..*.controller..*(..)) && !@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void requestServer() {
    }


    @SneakyThrows
    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint pjp) {
        if ("demo".equals(mode)){
            ServletUtils.renderString(ResponseResult.failed(ResultCode.DEMO));
            return null;
        }
        return pjp.proceed();
    }
}
