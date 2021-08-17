package me.zrxjava.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 自定义注解防止表单重复提交
 *
 * @author void
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatSubmit
{

    /**
     * 间隔多长时间内算重复提交
     * @return
     */
    int time() default 1;

    /**
     * 时间单位 默认秒
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;


}
