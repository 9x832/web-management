package com.itheima.mapper;

import com.itheima.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 日志Mapper接口
 */
@Mapper
public interface LogMapper {
    
    /**
     * 查询总记录数
     * @return 总记录数
     */
    @Select("select count(*) from operate_log")
    Long count();
    
    /**
     * 分页查询日志
     * @param start 起始索引
     * @param pageSize 每页记录数
     * @return 日志列表
     */
    List<OperateLog> page(Integer start, Integer pageSize);
} 