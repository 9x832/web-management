package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 分页查询日志信息
     * @param page 页码
     * @param pageSize 每页记录数
     * @return 分页日志数据
     */
    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询日志信息: page={}, pageSize={}", page, pageSize);
        PageResult pageResult = logService.page(page, pageSize);
        return Result.success(pageResult);
    }
} 