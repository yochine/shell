package me.zrxjava.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * 切面api
 * @author void
 * @create 2020-10-23
 */
public interface AspectApi {

    /**
     * 定义切面拦截基类
     * @param pjp
     * @param method
     * @return
     * @throws Throwable
     */
    Object doHandlerAspect(ProceedingJoinPoint pjp, Method method)throws Throwable;
}
