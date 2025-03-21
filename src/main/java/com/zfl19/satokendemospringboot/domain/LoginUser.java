package com.zfl19.satokendemospringboot.domain;

import com.zfl19.satokendemospringboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private User user;
    private String token;
}
