package com.wang.enhance.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class ProxyTest2 {
    public static void main(String[] args) {
        Target target = new Target();
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            //被执行几次 ---  看代理对象调用方法几次
            //代理对象调用接口相应的方法 都是调用invoke
            /**
             * proxy是代理对象
             * method代表目标方法的字节码对象
             * args 代表调用目标方法时的参数
             */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //反射
                Object invoke = method.invoke(target,args);
                //return 返回的值给代理对象
                return invoke;
            }
        });

        proxy.method(); //调用invoke args:null 返回:null
        System.out.println(proxy.method3(111)); //args:Object[]{100} 返回:int
    }
}
