package com.damon.media.starter.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@WebFilter
public class UrlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,2,1L,TimeUnit.DAYS, null);
        executor.shutdownNow();
    }
}
