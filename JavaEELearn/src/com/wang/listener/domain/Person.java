package com.wang.listener.domain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Session感知监听器不需要在web.xml配置
 */
public class Person implements HttpSessionBindingListener {
    private String id;
    private String name;
    @Override
    //绑定方法
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        //
        System.out.println("将Person对象放入Session时触发");
    }

    @Override
    //解绑方法
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("将Person对象移除Session时触发");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
