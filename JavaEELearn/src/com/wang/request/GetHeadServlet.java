package com.wang.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * 获得请求头
 */
@WebServlet(name = "GetHeadServlet",urlPatterns = {"/getHeadServlet"})
public class GetHeadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获得指定头
        String header = request.getHeader("User-Agent");
        System.out.println(header);
        //获得所有请求头
        Enumeration enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String headername = (String) enumeration.nextElement();
            String headervalue = request.getHeader(headername);
            System.out.println(headername+":"+headervalue);
        }
        //获得访问来源
        String reffer = request.getHeader("reffer");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        if (header!=null&&header.startsWith("http://localhost")){
            response.getWriter().write("访问成功");
        }else{
            response.getWriter().write("访问失败");
           // response.sendRedirect("/getHeadServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
