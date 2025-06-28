package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 数据统计相关接口
 */
@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工性别数据
     * @return 员工性别统计数据
     */
    @GetMapping("/empGenderData")
    public Result empGenderData() {
        log.info("统计员工性别数据");
        List<Map<String, Object>> data = reportService.getEmpGenderData();
        return Result.success(data);
    }

    /**
     * 统计员工职位人数
     * @return 员工职位人数统计数据
     */
    @GetMapping("/empJobData")
    public Result empJobData() {
        log.info("统计员工职位人数");
        Map<String, Object> data = reportService.getEmpJobData();
        return Result.success(data);
    }

    /**
     * 统计学员学历信息
     * @return 学员学历统计数据
     */
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        log.info("统计学员学历信息");
        List<Map<String, Object>> data = reportService.getStudentDegreeData();
        return Result.success(data);
    }

    /**
     * 统计班级人数
     * @return 班级人数统计数据
     */
    @GetMapping("/studentCountData")
    public Result studentCountData() {
        log.info("统计班级人数");
        Map<String, Object> data = reportService.getStudentCountData();
        return Result.success(data);
    }
} 