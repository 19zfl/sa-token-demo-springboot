package com.zfl19.satokendemospringboot.service;

import cn.dev33.satoken.util.SaResult;
import com.zfl19.satokendemospringboot.entity.User;

public interface IUserService {
    Integer register(User user);

    SaResult doLogin(String name, String pwd);
}
