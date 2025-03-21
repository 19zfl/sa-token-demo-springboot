package com.zfl19.satokendemospringboot.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.zfl19.satokendemospringboot.domain.LoginUser;
import com.zfl19.satokendemospringboot.entity.User;
import com.zfl19.satokendemospringboot.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    private User user;

    @Resource
    private UserMapper userMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        user = getUserById();
        return parsePermissions(user.getPermission());
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        user = getUserById();
        return parsePermissions(user.getPermission());
    }

    /**
     * 根据登录ID获取用户信息
     * @return 用户信息
     */
    private User getUserById() {
        LoginUser loginUser = (LoginUser) StpUtil.getTokenSession().get("loginUser");
        return userMapper.selectById(loginUser.getUser().getId());
    }

    /**
     * 解析权限字符串为权限列表
     * @param permission 权限字符串
     * @return 权限列表
     */
    private List<String> parsePermissions(String permission) {
        // 去掉首尾的括号
        String trimmedStr = permission.substring(1, permission.length() - 1);
        // 按逗号分隔，并去掉多余的空格和引号
        return Arrays.stream(trimmedStr.split(","))
                .map(s -> s.trim().replace("\"", ""))
                .collect(Collectors.toList());
    }

}

