package com.wang.affair.transfer.web;

import com.wang.affair.transfer.service.TransferService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TransferServlet",urlPatterns = {"/transferServlet"})
public class TransferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html;charset=utf-8");
        //接收转账参数
        String out = request.getParameter("out");
        String in = request.getParameter("in");
        String moneyStr = request.getParameter("money");
        double money = Double.parseDouble(moneyStr);
        //掉用业务层转账方法
        TransferService transferService = new TransferService();
        boolean isTransfer = transferService.isTransfer(out,in,money);
       if (isTransfer){
           response.getWriter().write("转账成功");
       }else {
           response.getWriter().write("转账失败");
       }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
