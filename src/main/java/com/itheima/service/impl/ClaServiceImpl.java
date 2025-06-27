package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.ClaQueryParam;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClaServiceImpl implements ClaService {
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 分页查询班级
     * @param claQueryParam
     * @return
     */
    @Override
    public PageResult page(ClaQueryParam claQueryParam) {
        // 1.设置PageHelper分页参数
        PageHelper.startPage(claQueryParam.getPage(),claQueryParam.getPageSize());
        // 2.执行查询
        List<Clazz> clalist = clazzMapper.list(claQueryParam);
        // 3.封装分页结果
        Page<Clazz> page = (Page<Clazz>) clalist;

        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 删除班级
     */
    @Override
    public void deleteById(Integer id){
        clazzMapper.deleteById(id);
    }

    /**
     * 添加班级
     */
    @Override
    public void save(Clazz clazz) {
        // 1.补充基本属性
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        // 2.保存班级信息
        clazzMapper.save(clazz);
    }

    /**
     * 根据ID查找班级
     */
    @Override
    public Clazz findById(Integer id) {
        return clazzMapper.findById(id);
    }

    /**
     * 修改班级
     */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }
}
