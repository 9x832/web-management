package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.service.LoginService;
import com.itheima.utils.JwtUtils;
import com.itheima.utils.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录服务实现类
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmpMapper empMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 员工登录
     * @param emp 登录员工信息（用户名、密码）
     * @return 登录成功返回包含JWT令牌的Map，登录失败返回null
     */
    @Override
    public Map<String, Object> login(Emp emp) {
        // 1. 根据用户名查询数据库中的员工
        Emp loginEmp = empMapper.getByUsername(emp.getUsername());
        
        // 2. 判断员工是否存在，以及密码是否正确
        if (loginEmp != null && passwordEncoder.matches(emp.getPassword(), loginEmp.getPassword())) {
            // 3. 登录成功，生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginEmp.getId());
            claims.put("username", loginEmp.getUsername());
            claims.put("name", loginEmp.getName());
            
            String jwt = JwtUtils.generateJwt(claims);
            
            // 4. 构造返回结果
            Map<String, Object> result = new HashMap<>();
            result.put("id", loginEmp.getId());
            result.put("username", loginEmp.getUsername());
            result.put("name", loginEmp.getName());
            result.put("token", jwt);
            
            return result;
        }
        
        // 5. 登录失败
        return null;
    }
} 