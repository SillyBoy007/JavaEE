package com.wang.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContextAreaServlet",urlPatterns = {"/contextAreaServlet"})
public class ContextAreaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //域对象---向servletContext中存取数据
        ServletContext servletContext =  this.getServletContext();
        servletContext.setAttribute("name","xiaowang");
        String str = (String) this.getServletContext().getAttribute("name");
        System.out.println(str);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
