package com.wang.enhance.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class ProxyTest {
    public static void main(String[] args) {
        TargetInterface objproxy = (TargetInterface) Proxy.newProxyInstance(Target.class.getClassLoader(),
                new Class[]{TargetInterface.class}, new InvocationHandler() {
                      //表示执行代理对象的方法
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //执行目标方法前
                        Object invoke = method.invoke(new Target(),args);
                        //执行目标方法后
                        return invoke;
                    }
                });
        objproxy.method();
        objproxy.method2();
        System.out.println(objproxy.method3(3));
    }
}
