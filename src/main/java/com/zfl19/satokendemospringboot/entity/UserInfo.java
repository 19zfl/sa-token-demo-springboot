package com.zfl19.satokendemospringboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zfl19.satokendemospringboot.json.IDCardJsonSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sa_user_info")
public class UserInfo {
    @TableId
    private Long id;
    @TableField("user_id")
    private Long userId;
    private String phone;
    @TableField("id_card")
    @JsonSerialize(using = IDCardJsonSerializer.class)
    private String idCard;
    private Integer age;
    private Integer sex;
    private Date birthday;
}
