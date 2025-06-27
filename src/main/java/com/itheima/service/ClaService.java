package com.itheima.service;

import com.itheima.pojo.ClaQueryParam;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import org.springframework.stereotype.Service;

public interface ClaService {

    /**
     * 按条件对班级进行分页查询
     */
    PageResult page(ClaQueryParam claQueryParam);

    /**
     * 删除班级
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 添加班级
     * @param clazz
     */
    void save(Clazz clazz);

    /**
     * 根据ID查找班级
     * @param id
     * @return
     */
    Clazz findById(Integer id);

    /**
     * 修改班级
     * @param clazz
     */
    void update(Clazz clazz);
}
