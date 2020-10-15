package com.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author linxiaobai
 * @Date 2020/9/7 16:08
 * @Description TODO
 * @Version 1.0
 **/


public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri=request.getRequestURI();
        if (uri.contains("img") || uri.contains("css")||uri.contains("js")||uri.contains("login")||uri.contains("logout")){
            return true;//不拦截路径
        }
        Object admin=request.getSession().getAttribute("admin");
        if (Objects.nonNull(admin) && !admin.toString().trim().isEmpty()){
            return true;//登入验证通过
        }
        response.sendRedirect("login.jsp");
        return false;//其他情况一律拦截
    }

}
