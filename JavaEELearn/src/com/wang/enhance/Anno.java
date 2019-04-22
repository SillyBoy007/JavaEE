package com.wang.enhance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Target({ElementType.METHOD,ElementType.TYPE}) //表示作用范围
@Retention(RetentionPolicy.RUNTIME) //表示可见范围
public @interface Anno {
    String name(); //定义注解的属性
    //int age() default 23; //定义属性和其默认值
  //  String [] value();  //定义的是value且只有一个可以省略key值

}
