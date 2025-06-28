package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 分页条件查询学员信息
     */
    PageResult page(StudentQueryParam studentQueryParam);
    
    /**
     * 根据ID查询学员
     */
    Student getById(Integer id);
    
    /**
     * 新增学员
     */
    void save(Student student);
    
    /**
     * 更新学员信息
     */
    void update(Student student);
    
    /**
     * 批量删除学员
     */
    void deleteByIds(List<Integer> ids);
    
    /**
     * 违纪处理
     */
    void addViolation(Integer id, Short score);
} 