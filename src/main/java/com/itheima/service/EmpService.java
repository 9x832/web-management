package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 按条件对员工分页查询
     */
    PageResult page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */
    void save(Emp emp);

    /**
     * 批量删除员工
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 查询回显
     * @param id 要查询的员工id
     * @return 员工类
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);
    
    /**
     * 查询全部员工
     * @return 员工列表
     */
    List<Emp> list();
}
