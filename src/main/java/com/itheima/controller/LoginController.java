package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 员工登录
     * @param emp 登录员工信息（用户名、密码）
     * @return 登录结果，包含JWT令牌
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录: {}", emp.getUsername());
        
        // 调用业务层完成登录功能
        Map<String, Object> loginResult = loginService.login(emp);
        
        // 判断登录是否成功
        if (loginResult != null) {
            return Result.success(loginResult);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
} 