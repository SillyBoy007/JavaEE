package com.wang.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContextServlet",urlPatterns = {"/contextServlet"})
public class ContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
      //获得web.xml的初始化参数
        String initParameter = servletContext.getInitParameter("driver");
        System.out.println(initParameter);
        //获得web应用资源的绝对路径
        //1.获得a.txt
        String realPathA = servletContext.getRealPath("a.txt");
        System.out.println(realPathA);
        //2.获得b.txt
        String realPathB = servletContext.getRealPath("WEB-INF/b.txt");
        System.out.println(realPathB);
        //3.获得c.txt
        String realPathC = servletContext.getRealPath("WEB-INF/classes/c.txt");
        System.out.println(realPathC);
        //通过类加载器获得绝对路径
        String path = ContextServlet.class.getClassLoader().getResource("c.txt").getPath();
        System.out.println(path);
        String path2 = ContextServlet.class.getClassLoader().getResource("../b.txt").getPath();
        System.out.println(path2);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
