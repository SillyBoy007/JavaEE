package com.wang.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DispatcherServlet1",urlPatterns = {"/dispatcherServlet1"})
public class DispatcherServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //向request中存数据
        request.setAttribute("name","wang");
        //请求转发器,将servlet1的请求转发给servlet2
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dispatcherServlet2");
        //指向转发方法
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
