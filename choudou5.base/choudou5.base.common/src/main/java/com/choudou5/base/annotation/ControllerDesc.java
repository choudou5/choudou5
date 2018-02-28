package com.choudou5.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Name：Controller 说明注解
 * @Author：xuhaowen
 * @Date：2018-02-28
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerDesc {

    /**
     * 说明
     * @return
     */
    String desc();

    /**
     * 操作类型
     * @return
     */
    String optType() default "view";

}
