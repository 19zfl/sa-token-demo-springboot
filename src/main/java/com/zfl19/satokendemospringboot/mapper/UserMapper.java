package com.zfl19.satokendemospringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfl19.satokendemospringboot.domain.UserDetail;
import com.zfl19.satokendemospringboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /** 用户登录 **/
    User selectUserToLogin(String name, String pwd);

    /** 查询个人详细信息 **/
    UserDetail selectUserDetail(Long id);

}
