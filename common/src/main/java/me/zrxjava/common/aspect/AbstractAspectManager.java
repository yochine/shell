package me.zrxjava.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * 抽象切面装饰器
 * @author void
 * @create 2020-10-23
 */

public abstract class AbstractAspectManager implements AspectApi{

    private final AspectApi aspectApi;

    public AbstractAspectManager(AspectApi aspectApi){
        this.aspectApi = aspectApi;
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        return aspectApi.doHandlerAspect(pjp,method);
    }

    /**
     * 装饰方法
     * @param pjp
     * @param method
     * @return
     * @throws Throwable
     */
    protected abstract Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable;
}
