package com.itheima.service.impl;

import com.itheima.mapper.LogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志服务实现类
 */
@Slf4j
@Service
public class LogServiceImpl implements LogService {
    
    @Autowired
    private LogMapper logMapper;
    
    /**
     * 分页查询日志信息
     * @param page 页码
     * @param pageSize 每页记录数
     * @return 分页日志数据
     */
    @Override
    public PageResult page(Integer page, Integer pageSize) {
        // 计算分页起始索引
        Integer start = (page - 1) * pageSize;
        
        // 查询总记录数
        Long total = logMapper.count();
        
        // 查询当前页数据
        List<OperateLog> logs = logMapper.page(start, pageSize);
        
        // 封装PageResult并返回
        return new PageResult(total, logs);
    }
} 