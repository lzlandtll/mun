package com.mun.common.exception;

import com.mun.common.domain.vo.response.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    public ApiResult businessExceptionHandler(BusinessException e) {
        return ApiResult.fail(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public ApiResult systemExceptionHandler(Exception e) {
        return ApiResult.fail(BusinessErrorEnum.SYSTEM_ERROR);
    }
}
