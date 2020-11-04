package me.zrxjava.system.support.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


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
       return pjp.proceed();
    }

    @AfterReturning(pointcut = "aspect()", returning = "ret")
    public void afterReturning(Object ret){

    }

    @AfterThrowing(pointcut = "aspect()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {

    }





}
