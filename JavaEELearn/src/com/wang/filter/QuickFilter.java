package com.wang.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器
 */
public class QuickFilter implements Filter {
    @Override
    /**
     * filter创建时执行:服务器启动
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        //获得web.xml中filter的名字
        filterConfig.getFilterName();
        //获得当前filter的初始化参数
        filterConfig.getInitParameter("aaa");
        //获得servletContext
        filterConfig.getServletContext();
        System.out.println("quickfilter创建了...");
    }

    @Override
    /**
     * 调用时执行
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("quickfilter running ...");

        //将请求放行,否则后面的资源无法访问到
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    /**
     * filter销毁时执行:服务器关闭
     */
    public void destroy() {
        System.out.println("quickfilter销毁了...");
    }
}
