package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 封装学员查询参数
 */
@Data
public class StudentQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name; // 学员姓名
    private Integer degree; // 学历
    private Integer clazzId; // 班级ID
} 