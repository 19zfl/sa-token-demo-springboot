package com.zfl19.satokendemospringboot.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.zfl19.satokendemospringboot.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录测试
 */
@RestController
@RequestMapping("/welcome/")
public class LoginController {
    @Resource
    private IUserService userService;

    @GetMapping("hello")
    public SaResult hello() {
        System.out.println(StpUtil.hasPermission("hello"));
        return SaResult.ok("hello");
    }

    // 登录  ---- http://localhost:8081/welcome/doLogin?name=jack&pwd=123
    @GetMapping("doLogin")
    public SaResult doLogin(String name, String pwd) {
        return userService.doLogin(name, pwd);
    }

    // 注销  ---- http://localhost:8081/welcome/logout
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    // 查询登录状态  ---- http://localhost:8081/welcome/isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/welcome/tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

}
