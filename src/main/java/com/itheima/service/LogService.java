package com.itheima.service;

import com.itheima.pojo.PageResult;

/**
 * 日志服务接口
 */
public interface LogService {
    
    /**
     * 分页查询日志信息
     * @param page 页码
     * @param pageSize 每页记录数
     * @return 分页日志数据
     */
    PageResult page(Integer page, Integer pageSize);
} 