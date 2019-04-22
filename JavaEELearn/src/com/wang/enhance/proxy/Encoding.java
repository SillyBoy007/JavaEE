package com.wang.enhance.proxy;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Encoding implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //使用动态代理完成全局编码
        HttpServletRequest enhanceRequest = (HttpServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //对getParameter方法进行增强
                String methodName =  method.getName();//获得目标对象的方法名称

                if ("getParameter".equals(methodName)){
                    String invoke = (String) method.invoke(request,args);   //乱码
                    
                    //进行转码
                    invoke = new String(invoke.getBytes("iso8859-1"),"UTF-8");
                    return invoke;
                }
                return method.invoke(request,args);
            }
        });
        filterChain.doFilter(enhanceRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
