package com.zfl19.satokendemospringboot.util;

import cn.hutool.Hutool;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;

public class SaltUtil {
    /**
     * 对密码进行加盐加密
     * @param password 原始密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    public static String encryptPassword(String password, String salt) {
        // 使用 Hutool 的 HMAC-MD5 算法对密码加盐加密
        return SecureUtil.hmacMd5(password + salt).digestHex(salt);
    }

    /**
     * 验证密码是否正确
     * @param rawPassword 用户输入的原始密码
     * @param salt 盐值
     * @param encryptedPassword 数据库中存储的加密密码
     * @return 是否匹配
     */
    public static boolean verifyPassword(String rawPassword, String salt, String encryptedPassword) {
        // 对用户输入的密码进行同样的加盐加密处理，然后与数据库中的加密密码比对
        return encryptPassword(rawPassword, salt).equals(encryptedPassword);
    }

    /**
     * 生成随机盐值
     * @return 随机盐值
     */
    public static String generateSalt() {
        // 使用 Hutool 的随机工具生成随机盐值
        return RandomUtil.randomBytes(16).toString(); // 生成长度为 16 的随机字符串作为盐值
    }
}
