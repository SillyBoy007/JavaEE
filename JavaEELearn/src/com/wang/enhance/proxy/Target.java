package com.wang.enhance.proxy;

public class Target implements TargetInterface {
    @Override
    public void method() {
        System.out.println("method is running...");
    }

    @Override
    public String method2() {
        System.out.println("method2 is running...");
        return null;
    }

    @Override
    public int method3(int x) {
        return x;
    }
}
