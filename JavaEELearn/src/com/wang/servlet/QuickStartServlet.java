package com.wang.servlet;

import javax.servlet.*;
import java.io.IOException;

public class QuickStartServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init is running...");
        //1.获得servlet的name
        String servletname = servletConfig.getServletName();
        System.out.println(servletname);
        //2.获得初始化参数
        String url = servletConfig.getInitParameter("url");
        //3.获得Servletcontext对象
        ServletContext servletContext = servletConfig.getServletContext();

        
    }

    @Override
    public ServletConfig getServletConfig() {

        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service is running...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy is running...");
    }
}
