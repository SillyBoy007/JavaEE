package com.wang.response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "OutPutStreamServlet",urlPatterns = {"/outPutStreamServlet"})
public class OutPutStreamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        //获得服务器上的图片
        String path = this.getServletContext().getRealPath("/download/a.jpg");
        InputStream inputStream = new FileInputStream(path);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer))>0){
            outputStream.write(buffer,0,len);
        }
        inputStream.close();
        outputStream.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
