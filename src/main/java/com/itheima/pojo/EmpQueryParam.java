package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 封装前端请求参数
 */
@Data
public class EmpQueryParam {

    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}
