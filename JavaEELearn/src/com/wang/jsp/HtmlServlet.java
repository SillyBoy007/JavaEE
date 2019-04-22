package com.wang.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HtmlServlet",urlPatterns = {"/htmlServlet"})
public class HtmlServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //动态的响应html页面
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE html>");
        writer.write("<html lang=\"en\">");
        writer.write("<head>");
        writer.write("<body>");
        writer.write("<h1>麻烦的htmlservlet</h1>");
        writer.write("</body>");
        writer.write("</head>");
        writer.write("</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
