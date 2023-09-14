//package com.yjkj.filter;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterConfig;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//
//@WebFilter(filterName="ManagerFilter",servletNames={"game"})
//public class ManagerFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        HttpSession session = request.getSession();
//        String currPath = request.getRequestURI();  //当前请求的URL
//
//        String method=request.getParameter("method");
//
//        if (session.getAttribute("loginAdmin") != null||method.equals("loginAdmin")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            response.sendRedirect("http://localhost:8080/jsp/loginAdmin.jsp");
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}