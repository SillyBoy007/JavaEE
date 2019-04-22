package com.wang.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class ServletContextAttributeImpl implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println(servletContextAttributeEvent.getName());//添加的name
        System.out.println(servletContextAttributeEvent.getValue());//添加的的value
        System.out.println("属性添加时的被调用");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("属性移除时的被调用");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println(servletContextAttributeEvent.getName());//获得修改前的name'
        System.out.println(servletContextAttributeEvent.getValue());//修改前的value
        System.out.println("属性更改时的被调用");
    }
}
