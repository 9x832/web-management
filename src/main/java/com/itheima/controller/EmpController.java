package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 按条件对员工进行分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询请求参数：{}",empQueryParam);
        PageResult pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增员工
     */
    @PostMapping
    public Result save(@Validated @RequestBody Emp emp) {
        log.info("请求参数：{}",emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 批量删除员工
     */
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Integer> ids) {
        log.info("批量删除部门：ids={} ", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 查询回显
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer id) {
        log.info("根据id查询员工的详细信息：{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @PutMapping
    public Result update(@Validated @RequestBody Emp emp) {
        log.info("更新员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }
    
    /**
     * 查询全部员工
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询全部员工");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }
}
