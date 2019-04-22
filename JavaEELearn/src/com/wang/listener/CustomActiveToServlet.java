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
 * 活化:将磁盘上的对象再次恢复到session内存中
 * 注意:该对象需要实现Serializable接口才能被活化
 */
@WebServlet(name = "CustomActiveToServlet",urlPatterns = {"/customActiveTo"})
public class CustomActiveToServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Customer customer = (Customer) httpSession.getAttribute("customer");
        System.out.println(customer.getName());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
