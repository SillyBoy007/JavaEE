package com.wang.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获得request请求行
 */
@WebServlet(name = "RequestServlet",urlPatterns = {"/requestServlet"})
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得请求方式
        String method = request.getMethod();
        System.out.println(method);
        //2.获得请求资源
        String uri = request.getRequestURI();
        System.out.println(uri);
        StringBuffer url = request.getRequestURL();
        System.out.println(url);
        //3.获得web应用名称
        String contextPath = request.getContextPath();
        System.out.println("web应用名称:"+contextPath);
        //4.获得get请求参数
        String querystr = request.getQueryString();
        System.out.println(querystr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
