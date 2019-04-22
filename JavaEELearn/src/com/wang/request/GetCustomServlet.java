package com.wang.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * request获得客户机的信息
 */
@WebServlet(name = "GetCustomServlet" , urlPatterns = {"/getCustomServlet"})
public class GetCustomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得客户机的IP
        String addr = request.getRemoteAddr();
        System.out.println(addr);
        //获得
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
