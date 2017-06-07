package com.king.annotation;

/**
 * Created by king on 2017/6/7.
 *
 * 用于标明该属性是主键属性。
 */
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Key {
    String name() default "key";
}

