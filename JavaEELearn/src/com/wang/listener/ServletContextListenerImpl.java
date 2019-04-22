package com.wang.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  ServletContext监听器
 */
public class ServletContextListenerImpl implements ServletContextListener{

    @Override
    /**
     * 监听content域对象的创建
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        /*//获得被监听的对象
        ServletContext servletContext = servletContextEvent.getServletContext();
        Object source = servletContextEvent.getSource(); //这也是获得被监听的对象,是通用方法
        System.out.println(servletContext);
        System.out.println("context创建..."); //服务器开启时调用*/

        Timer timer = new Timer();
        //参数(任务,第一次执行时间,间隔时间)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("银行计息");//每隔5秒调用一次
            }
        },new Date(),5000);

    }

    @Override
    /**
     * 监听content域对象的销毁
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("context销毁..."); //服务器销毁关闭调用
    }
}
