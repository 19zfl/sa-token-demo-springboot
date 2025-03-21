package com.zfl19.satokendemospringboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("sa_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId
    private Long id;
    @TableField("user_name")
    private String userName;
    @TableField("pass_word")
    private String passWord;
    private String salt;
    private Integer state;
    @TableField("register_time")
    private Date registerTime;
    @TableField("last_login_time")
    private Date lastLoginTime;
    private String permission;
    @TableField("origin_pass")
    private String originPass;
}
