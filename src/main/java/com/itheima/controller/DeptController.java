package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     */
    @GetMapping
    public Result list() {
        log.info("查询部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门
     */
    @DeleteMapping
    public Result delete(Integer id) {
        System.out.println("根据id删除部门：" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping
    public Result save(@RequestBody Dept dept) {
        System.out.println("新增部门：" + dept);
        deptService.save(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门 -GET http://localhost:8080/depts/1
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        System.out.println("根据id查询：" + id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        System.out.println("修改部门" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
