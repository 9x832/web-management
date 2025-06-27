package com.itheima.controller;

import com.itheima.pojo.ClaQueryParam;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 班级管理
 */
@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClaService claService;

    /**
     * 按条件对班级进行分页查询
     */
    @GetMapping
    public Result page(ClaQueryParam claQueryParam) {
        log.info("查询请求参数：{}",claQueryParam);
        PageResult pageResult = claService.page(claQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 删除班级
     */
    @DeleteMapping
    public Result delete(Integer id) {
        log.info("根据id删除班级：{}",id);
        claService.deleteById(id);
        return Result.success();
    }

    /**
     * 添加班级
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("添加班级:{}",clazz);
        claService.save(clazz);
        return Result.success();
    }

    /**
     * 根据ID查询班级
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("根据id查找班级：{}",id);
        Clazz clazz = claService.findById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级：{}",clazz);
        claService.update(clazz);
        return Result.success();
    }
}
