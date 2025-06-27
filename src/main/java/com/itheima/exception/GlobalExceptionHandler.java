package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 处理数据校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e) {
        log.error("数据校验错误", e);
        
        // 获取所有字段验证错误
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        
        // 组装错误信息
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        return Result.error("数据验证失败").setData(errorMap);
    }
    
    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常", e);
        return Result.error("对不起，操作失败，请联系管理员");
    }
}
