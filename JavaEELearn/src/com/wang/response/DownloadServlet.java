package com.wang.response;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(name = "DownloadServlet",urlPatterns = {"/downloadServlet"})
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得下载文件名称
        String filename = request.getParameter("filename");
        //解决获得中文参数的乱码
        filename = new String(filename.getBytes("ISO8859-1"),"utf-8");//编码

        //获得请求头中的User-Agent
        String agent = request.getHeader("User-Agent");
        //不同客户端用不同的字符编码
        String filenameEncoder = ""; //编过码的文件名，传给客户端
        if (agent.contains("MSIE")) {
            // IE浏览器
            filenameEncoder = URLEncoder.encode(filename, "utf-8");
            filenameEncoder = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            filenameEncoder = URLEncoder.encode(filename, "utf-8");
        }
        //设置文件要下载的类型---客户端通过文件的MIME类型区分
        response.setContentType(this.getServletContext().getMimeType(filename));
        //告诉客户端不解析文件，以附件形式打开
        response.setHeader("Content-Disposition","attachment;filename="+filenameEncoder);
         //获取文件的绝对路径
         String path = this.getServletContext().getRealPath("download/"+filename);
        //获得该文件的输入流
         InputStream is = new FileInputStream(path);
        //获得该文件的输出流
         ServletOutputStream os = response.getOutputStream();
         //文件拷贝
        int len = 0;
        byte [] buffer = new byte[1024];
        while ((len = is.read(buffer)) > 0 ){
            os.write(buffer,0,len);
        }
        is.close();
        os.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
