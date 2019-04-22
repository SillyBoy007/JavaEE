package com.wang.cookiesession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "GetSessionServlet",urlPatterns = {"/getSessionServlet"})
public class GetSessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //创建属于该客户端私有的session区域
        /**
         * request.getSession()内部会判断是否存在session,若存在,则会获取该session
         * 若不存在,则会创建该session(实质上是根据jsessionID判断是否存在)
         *
         * jsession存储在客户端的cookie中
         */
        HttpSession httpSession = request.getSession();
        //该session区域的id
        String id = httpSession.getId();
        //由于默认的cookie是会话级别的,关闭会消失,所以导致是session消失手动创建存储SESSTIONID的cookie,为cookie设置持久化时间
        Cookie cookie = new Cookie("JSESSIONID",id);  //JSESSIONID持久化
        cookie.setMaxAge(60*10);

        response.addCookie(cookie);

        httpSession.setAttribute("username","xiaowang"); //向session域对象中存储数据
        System.out.println(httpSession.getAttribute("username")); //读取session中的数据
        response.getWriter().write(id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
