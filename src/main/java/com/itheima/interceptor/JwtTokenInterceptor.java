package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT令牌校验拦截器
 */
@Slf4j
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求URL: {}", url);
        
        // 2. 判断是否为登录请求，如果是则放行
        if (url.contains("/login")) {
            return true;
        }
        
        // 3. 从请求头中获取token
        String token = request.getHeader("token");
        
        // 4. 判断token是否存在
        if (!StringUtils.hasLength(token)) {
            log.error("Token不存在，返回未授权错误");
            response.setStatus(401); // 未授权状态码
            return false;
        }
        
        // 5. 解析token
        try {
            Claims claims = JwtUtils.parseJwt(token);
            if (claims == null) {
                log.error("Token无效，返回未授权错误");
                response.setStatus(401); // 未授权状态码
                return false;
            }
            
            // 获取用户信息
            Integer userId = (Integer) claims.get("id");
            String username = (String) claims.get("username");
            String name = (String) claims.get("name");
            
            log.info("通过令牌获取的用户信息: id:{}, username:{}, name:{}", userId, username, name);
            
            // 放行
            return true;
        } catch (Exception e) {
            log.error("JWT令牌解析失败", e);
            response.setStatus(401); // 未授权状态码
            return false;
        }
    }
} 