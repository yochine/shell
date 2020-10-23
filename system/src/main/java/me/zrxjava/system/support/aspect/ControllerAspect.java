package me.zrxjava.system.support.aspect;


import me.zrxjava.common.annotation.AccessLimit;
import me.zrxjava.common.aspect.AccessLimitAspect;
import me.zrxjava.common.aspect.AspectApiImpl;
import me.zrxjava.common.aspect.RequestLogAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 控制器切面
 * @author void
 */
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(* me.zrxjava..*.controller..*(..))  ")
    public void aspect() {
    }

    @Before("aspect()")
    public void before(JoinPoint joinPoint){
    }

    @Around(value = "aspect()")
    public Object validationPoint(ProceedingJoinPoint pjp)throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
         // 获取切入点所在的方法
        Method method = signature.getMethod();
        //创建被装饰者
        AspectApiImpl aspectApi = new AspectApiImpl();

        //是否需要限流
        if (method.isAnnotationPresent(AccessLimit.class)) {
            new AccessLimitAspect(aspectApi).doHandlerAspect(pjp,method);
        }
        return new RequestLogAspect().successLog(pjp);
    }

    @AfterReturning(pointcut = "aspect()", returning = "ret")
    public void afterReturning(Object ret){

    }

    @AfterThrowing(pointcut = "aspect()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {
        new RequestLogAspect().errorLog(joinPoint,e);
    }





}
