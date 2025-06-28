package com.itheima.service.impl;

import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据统计服务实现类
 */
@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 获取员工性别统计数据
     * @return 员工性别统计数据
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        // 调用Mapper查询性别统计数据
        return empMapper.countByGender();
    }

    /**
     * 获取员工职位人数统计数据
     * @return 员工职位人数统计数据
     */
    @Override
    public Map<String, Object> getEmpJobData() {
        // 调用Mapper查询职位人数统计数据
        List<Map<String, Object>> jobData = empMapper.countByJob();

        // 处理数据，构造前端需要的格式
        List<String> jobList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();

        for (Map<String, Object> map : jobData) {
            String job = (String) map.get("name");
            Integer count = ((Number) map.get("value")).intValue();

            jobList.add(job);
            dataList.add(count);
        }

        // 组装结果
        Map<String, Object> result = new HashMap<>();
        result.put("jobList", jobList);
        result.put("dataList", dataList);

        return result;
    }

    /**
     * 获取学员学历统计数据
     * @return 学员学历统计数据
     */
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        // 调用Mapper查询学历统计数据
        return studentMapper.countByDegree();
    }

    /**
     * 获取班级人数统计数据
     * @return 班级人数统计数据
     */
    @Override
    public Map<String, Object> getStudentCountData() {
        // 调用Mapper查询班级人数统计数据
        List<Map<String, Object>> clazzData = studentMapper.countByClazz();

        // 处理数据，构造前端需要的格式
        List<String> clazzList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();

        for (Map<String, Object> map : clazzData) {
            String clazzName = (String) map.get("name");
            Integer count = ((Number) map.get("value")).intValue();

            clazzList.add(clazzName);
            dataList.add(count);
        }

        // 组装结果
        Map<String, Object> result = new HashMap<>();
        result.put("clazzList", clazzList);
        result.put("dataList", dataList);

        return result;
    }
} 