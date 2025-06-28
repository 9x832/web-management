package com.itheima.pojo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id; //ID
    
    @NotBlank(message = "姓名不能为空")
    private String name; //姓名
    
    @NotBlank(message = "学号不能为空")
    private String no; //序号
    
    @NotNull(message = "性别不能为空")
    @Min(value = 1, message = "性别值不合法")
    @Max(value = 2, message = "性别值不合法")
    private Integer gender; //性别 , 1: 男 , 2 : 女
    
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone; //手机号
    
    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "^\\d{17}[0-9Xx]$", message = "身份证号格式不正确")
    private String idCard; //身份证号
    
    @NotNull(message = "请选择是否来自于院校")
    @Min(value = 0, message = "是否来自于院校值不合法")
    @Max(value = 1, message = "是否来自于院校值不合法")
    private Integer isCollege; //是否来自于院校, 1: 是, 0: 否
    
    private String address; //联系地址
    
    @NotNull(message = "最高学历不能为空")
    @Min(value = 1, message = "学历值不合法")
    @Max(value = 6, message = "学历值不合法")
    private Integer degree; //最高学历, 1: 初中, 2: 高中 , 3: 大专 , 4: 本科 , 5: 硕士 , 6: 博士
    
    @NotNull(message = "毕业时间不能为空")
    @Past(message = "毕业时间不能是将来时间")
    private LocalDate graduationDate; //毕业时间
    
    @NotNull(message = "班级不能为空")
    private Integer clazzId; //班级ID
    
    private Short violationCount; //违纪次数
    private Short violationScore; //违纪扣分
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

    private String clazzName;//班级名称
}
