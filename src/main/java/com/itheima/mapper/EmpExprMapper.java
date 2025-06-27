package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /**
     * 批量插入员工工作经历信息
     */
    void saveBatch(@Param("exprsList") List<EmpExpr> exprsList);

    /**
     * 批量删除员工工作经历信息
     */
    void deleteByEmpIds(@Param("empIds") List<Integer> empIds);
}
