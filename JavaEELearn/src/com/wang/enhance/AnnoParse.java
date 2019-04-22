package com.wang.enhance;


import java.lang.reflect.Method;

public class AnnoParse {
    public static void main(String[] args) throws NoSuchMethodException {
        //解析show方法上面的@Anno
        //解析的目的是获得@Anno的参数
        Class clazz = AnnoTest.class;
        Method method = clazz.getMethod("show",String.class);
        //获得show方法上的Anno
        Anno anno = method.getAnnotation(Anno.class);
        System.out.println(anno.name()); //获得注解的属性

    }
}
