package com.zfl19.satokendemospringboot.domain;

import com.zfl19.satokendemospringboot.entity.User;
import com.zfl19.satokendemospringboot.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
    private User user;
    private UserInfo userInfo;
}
