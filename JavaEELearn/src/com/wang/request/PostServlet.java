package com.wang.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "PostServlet",urlPatterns = {"/postServlet"})
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //中文参数乱码
        request.setCharacterEncoding("UTF-8");
        //get请求中文乱码
   /*   String parameter = "";
        parameter = new String(parameter.getBytes("iso8859-1"),"utf-8");*/
        String username = request.getParameter("username");
        String code = request.getParameter("code");
        //获得多个值
        String[] hobbys = request.getParameterValues("hobby");
        for (String hobby:hobbys){
            System.out.println(hobby);
        }

        //获得所有的参数,这些参数封装到Map中
        Map<String,String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> set = map.entrySet();
        Iterator<Map.Entry<String, String[]>> iter = set.iterator();
        while (iter.hasNext()){
            Map.Entry<String,String[]> entry = iter.next();
            System.out.print(entry.getKey()+":");
            for (int i=0;i<entry.getValue().length;i++){
                System.out.print(entry.getValue()[i]+",");
            }
            System.out.println();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
