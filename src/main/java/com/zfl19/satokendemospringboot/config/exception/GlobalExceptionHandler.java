package com.zfl19.satokendemospringboot.config.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }

    @ExceptionHandler
    public SaResult handlerException(NotRoleException e) {
        e.printStackTrace();
        return SaResult.error("没有访问权限！");
    }

    @ExceptionHandler
    public SaResult handlerException(NotPermissionException e) {
        e.printStackTrace();
        return SaResult.error("没有访问许可！");
    }

    @ExceptionHandler
    public SaResult handlerException(NotLoginException e) {
        e.printStackTrace();
        return SaResult.error("请先登录！");
    }
}