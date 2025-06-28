package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 学员管理
 */
@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    /**
     * 学员列表查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("学员列表条件查询：{}", studentQueryParam);
        PageResult pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }
    
    /**
     * 删除学员
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("批量删除学员：ids={}", ids);
        // 将ids字符串转换为List<Integer>
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        studentService.deleteByIds(idList);
        return Result.success();
    }
    
    /**
     * 添加学员
     */
    @PostMapping
    public Result save(@Validated @RequestBody Student student) {
        log.info("添加学员：{}", student);
        studentService.save(student);
        return Result.success();
    }
    
    /**
     * 根据ID查询学员
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询学员：{}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }
    
    /**
     * 修改学员
     */
    @PutMapping
    public Result update(@Validated @RequestBody Student student) {
        log.info("修改学员：{}", student);
        studentService.update(student);
        return Result.success();
    }
    
    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Short score) {
        log.info("学员违纪处理：id={}，扣分={}", id, score);
        studentService.addViolation(id, score);
        return Result.success();
    }
} 