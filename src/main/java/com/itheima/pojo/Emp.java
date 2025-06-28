package com.itheima.pojo;

import jakarta.validation.constraints.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
public class Emp {
    private Integer id;
    
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^\\w{4,16}$", message = "用户名必须是4-16位的字母、数字或下划线")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20位之间")
    private String password;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "性别不能为空")
    @Min(value = 1, message = "性别值不合法")
    @Max(value = 2, message = "性别值不合法")
    private Integer gender;
    
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;
//
    @NotNull(message = "职位不能为空")
    @Min(value = 1, message = "职位不合法")
    private Integer job;
//
    @NotNull(message = "薪资不能为空")
    @Min(value = 0, message = "薪资不能为负数")
    private Integer salary;
//
    private String image;
//
    @NotNull(message = "入职日期不能为空")
    @Past(message = "入职日期不能是将来时间")
    private LocalDate entryDate;
//
    @NotNull(message = "部门不能为空")
    private Integer deptId;
    
    private LocalDate createTime;
    private LocalDate updateTime;

    // 封装部门名称
    private String deptName;

    // 封装员工工作经历信息
    private List<EmpExpr> exprsList;
}
