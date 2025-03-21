package com.zfl19.satokendemospringboot.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zfl19.satokendemospringboot.domain.LoginUser;
import com.zfl19.satokendemospringboot.domain.UserDetail;
import com.zfl19.satokendemospringboot.entity.User;
import com.zfl19.satokendemospringboot.mapper.UserMapper;
import com.zfl19.satokendemospringboot.service.IUserService;
import com.zfl19.satokendemospringboot.util.SaltUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param user 注册信息
     * @return
     */
    @Override
    public Integer register(User user) {
        // 创建盐值
        user.setSalt(SaltUtil.generateSalt());
        // 备份未加密密码
        user.setOriginPass(user.getPassWord());
        // 密码加密
        String encryptPassword = SaltUtil.encryptPassword(user.getOriginPass(), user.getSalt());
        // 设置加密密码
        user.setPassWord(encryptPassword);
        // 设置初次注册时间
        user.setRegisterTime(new Date());
        // 设置状态
        user.setState(1);
        int row = userMapper.insert(user);
        return row;
    }

    /**
     * 用户登录
     * @param name 账号
     * @param pwd 密码
     * @return
     */
    @Override
    public SaResult doLogin(String name, String pwd) {
        // 创建查询条件Wrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 添加查询条件参数
        queryWrapper.eq("user_name", name).eq("origin_pass", pwd);
        // 查询满足条件的一个用户信息
        User user = userMapper.selectOne(queryWrapper);
        // 对查询结果判空
        if (ObjectUtil.isNull(user)) {
            return SaResult.error("用户名或密码错误！");
        }
        // 如果输入密码和查询出来的
        if (SaltUtil.encryptPassword(pwd, user.getSalt()).equals(user.getPassWord())) {
            user.setLastLoginTime(new Date());
            userMapper.insertOrUpdate(user);
            StpUtil.login(user.getId());
            StpUtil.getTokenSession().set("loginUser", new LoginUser(user, StpUtil.getTokenValue()));
            return SaResult.get(200, "登录成功！", StpUtil.getTokenInfo());
        }
        return SaResult.error("用户名或密码错误！");
    }

    /**
     * 用户封禁
     * @param id
     * @return
     */
    @Override
    public SaResult userBlocked(Long id) {
        try {
            User user = userMapper.selectById(id);
            user.setState(2);
            userMapper.insertOrUpdate(user);
            return SaResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return SaResult.error("封禁失败！");
        }
    }

    /**
     * 查询用户详细/信息
     * @param id
     * @return
     */
    @Override
    public SaResult selectUserDetail(Long id) {
        UserDetail selectedUserDetail = userMapper.selectUserDetail(id);
        if (Objects.nonNull(selectedUserDetail)) {
            return SaResult.get(200, "查询成功！", selectedUserDetail);
        }
        return SaResult.error("服务器连接异常！");
    }
}
