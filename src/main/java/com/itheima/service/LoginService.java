package com.itheima.service;

import com.itheima.pojo.Emp;

import java.util.Map;

/**
 * 登录服务接口
 */
public interface LoginService {
    
    /**
     * 员工登录
     * @param emp 登录员工信息（用户名、密码）
     * @return 登录成功返回包含JWT令牌的Map，登录失败返回null
     */
    Map<String, Object> login(Emp emp);
} 