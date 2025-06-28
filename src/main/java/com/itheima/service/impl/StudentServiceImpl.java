package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页条件查询学员信息
     */
    @Override
    public PageResult page(StudentQueryParam studentQueryParam) {
        // 1.设置PageHelper分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        // 2.执行查询
        List<Student> studentList = studentMapper.list(studentQueryParam);
        // 3.封装分页结果
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult(p.getTotal(), p.getResult());
    }

    /**
     * 根据ID查询学员
     */
    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    /**
     * 新增学员
     */
    @Transactional
    @Override
    public void save(Student student) {
        // 设置违纪初始值
        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        studentMapper.save(student);
    }

    /**
     * 更新学员信息
     */
    @Transactional
    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    /**
     * 批量删除学员
     */
    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    /**
     * 违纪处理
     */
    @Transactional
    @Override
    public void addViolation(Integer id, Short score) {
        studentMapper.addViolation(id, score);
    }
} 