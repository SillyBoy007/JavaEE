package com.wang.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RedirectServlet",urlPatterns = {"/redirectServlet"})
public class RedirectServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //重定向:访问服务器俩次，地址发生变化
        //状态码302,location:地址

      /*  //设置状态码
        response.setStatus(302);
        //设置响应头
        response.setHeader("location","/responseServlet");*/


        //重定向方法
        //response.sendRedirect("/responseServlet");

        //定时刷新,5秒之后跳转百度
        response.setHeader("refresh","5;url-https://www.baidu.com/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
