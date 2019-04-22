package com.wang.cookiesession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetCookieServlet",urlPatterns = {"/getCookieServlet"})
public class GetCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过request对象获得cookie
        Cookie[] cookies = request.getCookies();
        //通过名称获得cookie
        if (cookies!=null){
            for (Cookie cookie: cookies){
                if ("name".equals(cookie.getName())){
                    System.out.println(cookie.getValue()); //拿到cookie的值
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
