package com.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author linxiaobai
 * @Date 2020/9/17 9:21
 * @Description TODO
 * @Version 1.0
 **/
@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(MyException.class)
    public String exception(MyException exception, HttpServletRequest request) {
        request.setAttribute("msg", "系统错误");
        return "/index/error.jsp";
    }

    public static class MyException extends Exception {
        public MyException(String msg) {
            super(msg);
        }
    }
}

