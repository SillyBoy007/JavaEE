package com.wang.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //在传递request之前对request的getParameter的方法进行增强
        //装饰者模式,包装模式
        /**
         * 1.增强类与被增强类要实现同一父类
         *2.在增强类传入被增强的类
         *3.需要增强的方法重写不需要增强的方法调用被增强对象的
         */
        //被增强对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        EnhanceRequest enhanceRequest = new EnhanceRequest(request);

        filterChain.doFilter(enhanceRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}

/**
 * 实现装饰者模式的类
 */
class EnhanceRequest extends HttpServletRequestWrapper{
    private HttpServletRequest request;
    public EnhanceRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    /**
     * 对getParamer增强
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String parameter = request.getParameter(name);
        try {
            parameter = new String(parameter.getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return parameter;
    }
}