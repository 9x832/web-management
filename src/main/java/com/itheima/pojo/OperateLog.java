package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 操作日志实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    /**
     * ID
     */
    private Integer id;
    
    /**
     * 操作人ID
     */
    private Integer operateEmpId;
    
    /**
     * 操作人姓名
     */
    private String operateEmpName;
    
    /**
     * 操作时间
     */
    private LocalDateTime operateTime;
    
    /**
     * 类名
     */
    private String className;
    
    /**
     * 方法名
     */
    private String methodName;
    
    /**
     * 方法参数
     */
    private String methodParams;
    
    /**
     * 返回值
     */
    private String returnValue;
    
    /**
     * 耗时(单位:毫秒)
     */
    private Long costTime;
} 