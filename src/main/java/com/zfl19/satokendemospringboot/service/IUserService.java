package com.zfl19.satokendemospringboot.service;

import cn.dev33.satoken.util.SaResult;
import com.zfl19.satokendemospringboot.entity.User;

public interface IUserService {

    /** 用户注册 **/
    Integer register(User user);

    /** 用户登录 **/
    SaResult doLogin(String name, String pwd);

    /** 用户封禁 **/
    SaResult userBlocked(Long id);

}
