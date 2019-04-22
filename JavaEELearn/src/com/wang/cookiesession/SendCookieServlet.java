package com.wang.cookiesession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SendCookieServlet",urlPatterns = {"/sendCookieServlet"})
public class SendCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建一个Cookie
        Cookie cookie = new Cookie("name","xiaowang");
        //3.设置Cookie在客户端的持久化时间
        // cookie.setMaxAge(60);//单位秒,删除Cookie设置时间为0
         //4.设置Cookie的携带路径,只有访问sendCookieServlet才会有cookie
      //  cookie.setPath("/sendCookieServlet");
        //2.发送Cookie到客户端
        response.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
