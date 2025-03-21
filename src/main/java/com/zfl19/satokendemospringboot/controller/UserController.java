package com.zfl19.satokendemospringboot.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
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

    /**
     * 用户注册
     * @param user
     * @return
     */
    // http://localhost:8081/user/register?userName=sam&passWord=123456
    @PostMapping("register")
    public SaResult register(@RequestBody User user) {
        Integer register = userService.register(user);
        if (register > 0) {
            return SaResult.ok();
        }
        return SaResult.error();
    }

    /**
     * 用户封禁
     * @param id
     * @return
     */
    @GetMapping("block/{id}")
    @SaCheckPermission("super-admin")
    public SaResult userBlocked(@PathVariable Long id) {
        return userService.userBlocked(id);
    }

    @GetMapping("list/{id}")
    @SaCheckPermission({"super-admin", "user.*"})
    // TODO 404
    public SaResult selectUserDetail(@PathVariable Long id) {
        return userService.selectUserDetail(id);
    }

}
