package com.wang.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 被转发的servlet
 */
@WebServlet(name = "DispatcherServlet2",urlPatterns = {"/dispatcherServlet2"})
public class DispatcherServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从request域中取出数据
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String name = (String) request.getAttribute("name");
        response.getWriter().write("request域中的数据是:"+name);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
