package com.itheima.config;

import com.itheima.interceptor.JwtTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web相关配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    /**
     * 注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册JWT拦截器，拦截除登录以外的所有请求
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/login"); // 排除登录请求
    }
} 