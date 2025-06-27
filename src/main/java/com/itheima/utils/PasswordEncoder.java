package com.itheima.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * 密码加密工具类
 */
@Component
public class PasswordEncoder {
    
    /**
     * 加密密码
     * @param rawPassword 原始密码
     * @return 加盐加密后的密码
     */
    public String encode(String rawPassword) {
        // 生成随机盐值
        String salt = UUID.randomUUID().toString().replace("-", "");
        // 加盐加密
        String encodedPassword = encodeWithSalt(rawPassword, salt);
        // 将盐值和加密后的密码拼接存储（格式: salt$encodedPassword）
        return salt + "$" + encodedPassword;
    }
    
    /**
     * 验证密码
     * @param rawPassword 原始密码
     * @param encodedPassword 数据库中存储的已加密密码
     * @return 是否匹配
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.isEmpty()) {
            return false;
        }
        
        // 分离盐值和加密后的密码
        String[] parts = encodedPassword.split("\\$");
        if (parts.length != 2) {
            return false;
        }
        
        // 使用相同的盐值对输入密码加密
        String salt = parts[0];
        String storedHash = parts[1];
        String inputHash = encodeWithSalt(rawPassword, salt);
        
        // 比较加密结果
        return storedHash.equals(inputHash);
    }
    
    /**
     * 使用MD5算法加盐加密
     */
    private String encodeWithSalt(String rawPassword, String salt) {
        return DigestUtils.md5DigestAsHex((rawPassword + salt).getBytes());
    }
} 