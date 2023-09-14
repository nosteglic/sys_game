package com.yjkj.filter;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.yjkj.beans.User;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName="UserFilter",servletNames={"user"},urlPatterns={"*.jsp"})
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String currPath = request.getRequestURI();  //当前请求的URL
        String method=request.getParameter("method");

        if(method==null){
            method=" ";
        }
        //管理员可访问所有页面
        if (session.getAttribute("loginAdmin") != null) {
                filterChain.doFilter(servletRequest, servletResponse);
        }else if(session.getAttribute("loginUser") != null&&(currPath.equals("/jsp/menuAdmin.jsp")||currPath.equals("/jsp/showModify.jsp")
                ||currPath.equals("/jsp/showOffline.jsp")||currPath.equals("/jsp/showOnline.jsp")||currPath.equals("/jsp/showUsers.jsp"))){
            //用户登录后访问管理员jsp
            response.sendRedirect("http://localhost:8080/user?method=showGameList");
        }else if(session.getAttribute("loginUser") != null){
            User user= (User) session.getAttribute("loginUser");
            if(user.getType()==1){
                //非开发者访问开发者页面，跳转到 开发者注册 页面
                if(currPath.equals("/jsp/gameOffline.jsp")||currPath.equals("/jsp/gameOnline.jsp")
                        ||currPath.equals("/jsp/gameModify.jsp")||currPath.equals("/jsp/showMyGame.jsp")
                        ||method.equals("selectOnlineGame") || method.equals("toPutGame")
                        || method.equals("toTakeGame")|| method.equals("toChangeGame")|| method.equals("chsOneMyGame")){
                    response.sendRedirect("http://localhost:8080/jsp/registerProducer.jsp");
                }else{
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }else{
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }else if (method.equals("loginUser") || method.equals("registerUser") || method.equals("showGameList")
                    || method.equals("searchLikeName") || method.equals("searchByType")
                    || currPath.equals("/jsp/menuUser.jsp") || currPath.equals("/jsp/loginUser.jsp") || currPath.equals("/jsp/loginAdmin.jsp")
                || currPath.equals("/jsp/registerUser.jsp") || currPath.equals("/jsp/registerProducer.jsp")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if(currPath.equals("/")){
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                response.sendRedirect("http://localhost:8080/user?method=showGameList");
            }
        }

    }

    @Override
    public void destroy() {

    }
}