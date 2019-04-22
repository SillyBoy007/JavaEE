package com.wang.cookiesession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 显示上此登录的时间
 */
@WebServlet(name = "LastLoginServlet",urlPatterns = {"/lastLoginServlet"})
public class LastLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currenttime = format.format(date);
        Cookie cookie = new Cookie("lastAccessTime",currenttime);
        cookie.setMaxAge(60*10*500);
        response.addCookie(cookie);
        //获得客户端cookie
        String lastAccesssTime = null;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie coo:cookies){
                if ("lastAccessTime".equals(coo.getName())){
                     lastAccesssTime = coo.getValue();
                }
            }
        }
        response.setContentType("text/html;charset=utf-8");
        if (lastAccesssTime == null){
            response.getWriter().write("第一次访问");
        }else {
            response.getWriter().write("您上次访问的时间是:"+lastAccesssTime);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
