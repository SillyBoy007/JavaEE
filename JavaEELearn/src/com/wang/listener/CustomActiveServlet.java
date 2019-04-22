package com.wang.listener;

import com.wang.listener.domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 钝化:将session内存中的对象持久化（序列化）到磁盘
 */
@WebServlet(name = "CustomActiveServlet",urlPatterns = {"/customActive"})
public class CustomActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将customer放入session中
        HttpSession session = request.getSession();
        Customer customer = new Customer();
        customer.setId("12");
        customer.setName("wang");
        session.setAttribute("customer",customer);
        System.out.println("customer被放到session域中");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
