package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 */
@Slf4j
public class JwtUtils {

    /**
     * 签名密钥
     */
    private static final String SECRET_KEY = "itheima_tlias_jwt_secret";

    /**
     * 有效期，默认为1小时
     */
    private static final long EXPIRE_TIME = 60 * 60 * 1000L;

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return 生成的JWT令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)  // 设置自定义claims
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))  // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // 设置签名算法和密钥
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return 解析成功返回Claims对象，解析失败返回null
     */
    public static Claims parseJwt(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)  // 设置签名密钥
                    .parseClaimsJws(jwt)  // 解析JWT
                    .getBody();  // 获取负载中的claims
        } catch (Exception e) {
            log.error("JWT解析失败", e);
            return null;
        }
    }
} 