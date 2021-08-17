
package me.zrxjava.common.annotation;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author void
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    //  基本对象的属性名
    String propName() default "";
    //  查询方式
    SqlKeyword type() default SqlKeyword.EQ;

    /**
     * 多字段模糊搜索，仅支持String类型字段，多个用逗号隔开, 如@Query(blurry = "email,username")
     */
    String blurry() default "";


}

