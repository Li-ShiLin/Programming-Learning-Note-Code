package com.atguigu.gulimall.product.exception;


import com.atguigu.common.exception.BizCodeEnum;
import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 22418
 * @version 1.0
 * @description: 集中处理异常
 * @date 2023/4/29 23:16
 */
@Slf4j
//@ResponseBody  // 所有异常信息都要json格式信息响应出去
//@ControllerAdvice(basePackages = "com.atguigu.gulimall.product.controller")

// @RestControllerAdvice等效于 @ResponseBody + @ControllerAdvice
@RestControllerAdvice(basePackages = "com.atguigu.gulimall.product.controller")
public class ExceptionControllerAdvice {

    /**
     * 处理数据校验异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException ex){
        log.error("数据校验出现问题：{},异常类型：{}",ex.getMessage(),ex.getClass());
        BindingResult bindingResult = ex.getBindingResult();
        Map<String,String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError)->{
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        });

        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(),BizCodeEnum.VAILD_EXCEPTION.getMsg()).put("data",map);
    }

    /**
     * 处理任意类型的异常
     * @param throwable
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){

        log.error("错误：",throwable);
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }

}
