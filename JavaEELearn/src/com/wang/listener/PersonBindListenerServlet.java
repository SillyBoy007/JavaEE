package com.wang.listener;

import com.wang.listener.domain.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PersonBindListenerServlet",urlPatterns = {"/personBindListener"})
public class PersonBindListenerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将persion对象绑定到session域中
        HttpSession session = request.getSession();
        Person p =new Person();
        p.setId("1");
        p.setName("wang");
        session.setAttribute("person",p);

        //将person对象从session解绑
        session.removeAttribute("person");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
