package com.zfl19.satokendemospringboot.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin())).addPathPatterns("/**")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/welcome/doLogin")
                .excludePathPatterns("/welcome/hello");
    }
}

/**
 * 以上代码，我们注册了一个基于 StpUtil.checkLogin() 的登录校验拦截器，
 * 并且排除了/welcome/doLogin等接口用来开放登录（除了/user/doLogin以外的所有接口都需要登录才能访问）。
 */