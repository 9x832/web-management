package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import com.itheima.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * 员工管理
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PageResult page(EmpQueryParam empQueryParam) {
        // 1.设置PageHelper分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        // 2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3.封装分页结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        // 1.补全基本属性
        emp.setCreateTime(LocalDate.now());
        emp.setUpdateTime(LocalDate.now());
        
        // 2.加密密码
        if (StringUtils.hasText(emp.getPassword())) {
            emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        }

        // 3.保存员工基本信息
        empMapper.save(emp);

        // 4.保存员工的工作经历信息
        Integer empId = emp.getId();
        List<EmpExpr> exprsList = emp.getExprsList();
        if (!CollectionUtils.isEmpty(exprsList)) {
            exprsList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.saveBatch(exprsList);
        }
    }

    /**
     * 批量删除员工
     */
    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        // 1.根据员工id批量删除员工基本信息
        empMapper.deleteByIds(ids);
        // 2.根据员工id批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    /**
     * 查询回显
     */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 更新员工信息
     */
    @Transactional
    @Override
    public void update(Emp emp) {
        // 1.如果更新了密码，需要重新加密
        if (StringUtils.hasText(emp.getPassword())) {
            emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        }
        
        // 2.根据ID更新员工基本信息
        emp.setUpdateTime(LocalDate.now());
        empMapper.updateById(emp);

        // 3.根据员工ID删除原本的工作经历信息
        empExprMapper.deleteByEmpIds(Collections.singletonList(emp.getId()));

        // 4.新增员工的工作经历数据
        Integer empId = emp.getId();
        List<EmpExpr> exprsList = emp.getExprsList();
        if (!CollectionUtils.isEmpty(exprsList)) {
            exprsList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.saveBatch(exprsList);
        }
    }
}
