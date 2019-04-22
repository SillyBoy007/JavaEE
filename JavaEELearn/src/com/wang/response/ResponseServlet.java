package com.wang.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ResponseServlet",urlPatterns = {"/responseServlet"})
public class ResponseServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //手动设置状态码
        // response.setStatus(123);
        //添加响应头
        response.addHeader("username","wewe");
        response.addIntHeader("age",23);
        response.addDateHeader("323",232333333);
        //设置响应头
        response.setHeader("username","wang");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
