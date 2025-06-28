package com.itheima.service;

import java.util.List;
import java.util.Map;

/**
 * 数据统计服务
 */
public interface ReportService {

    /**
     * 获取员工性别统计数据
     * @return 员工性别统计数据
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 获取员工职位人数统计数据
     * @return 员工职位人数统计数据
     */
    Map<String, Object> getEmpJobData();

    /**
     * 获取学员学历统计数据
     * @return 学员学历统计数据
     */
    List<Map<String, Object>> getStudentDegreeData();

    /**
     * 获取班级人数统计数据
     * @return 班级人数统计数据
     */
    Map<String, Object> getStudentCountData();
} 