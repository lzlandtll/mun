package com.mun.common.exception;

import lombok.Data;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */
@Data
public class BusinessException extends RuntimeException{
    private Integer code;
    private String message;

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
