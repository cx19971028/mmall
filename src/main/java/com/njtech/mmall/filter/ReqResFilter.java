package com.njtech.mmall.filter;

import com.njtech.mmall.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = {"/cart/*","/order/*","/user/userInfo","/userAddress/","/userAddress/*"}, filterName = "reqResFilter")
public class ReqResFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User u = (User)request.getSession().getAttribute("user");
        if(u == null){
            response.sendRedirect("/login.html");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
