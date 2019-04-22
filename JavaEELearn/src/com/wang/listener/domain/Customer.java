package com.wang.listener.domain;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

public class Customer implements HttpSessionActivationListener,Serializable {
    @Override
    /**
     * 钝化
     */
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("Customer被钝化触发"); //服务器关闭时钝化
    }

    @Override
    /**
     * 活化
     */
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("Customer被活化触发");//
    }
    private String id;
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
