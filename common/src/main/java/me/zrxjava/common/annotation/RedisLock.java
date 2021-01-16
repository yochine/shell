package me.zrxjava.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * redis分布式锁注解
 * @author void
 * @create 2021-01-15
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {

    /**
     * 特定参数名 支持SPEL表达式
     */
    String key() default "business";
    /**
     * 失败重试次数
     */
    int tryCount() default 3;
    /**
     * 占用锁时长，单位ms
     */
    long lockTime() default 1000;

    /**
     * 重试等待时间 单位ms
     */
    int retryWait() default 200;
}
