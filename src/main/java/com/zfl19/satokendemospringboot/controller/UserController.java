package com.zfl19.satokendemospringboot.controller;

import cn.dev33.satoken.util.SaResult;
import com.zfl19.satokendemospringboot.entity.User;
import com.zfl19.satokendemospringboot.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Resource
    private IUserService userService;

    // http://localhost:8081/user/register?userName=sam&passWord=123456
    @PostMapping("register")
    public SaResult register(@RequestBody User user) {
        Integer register = userService.register(user);
        if (register > 0) {
            return SaResult.ok();
        }
        return SaResult.error();
    }

}
