package com.mun.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */
@AllArgsConstructor
@Getter
public enum BusinessErrorEnum implements ErrorEnum{

    BUSINESS_ERROR(1001, "{0}"),
    SYSTEM_ERROR(9999, "不好意思,系统被玩坏了!");

    private Integer code;
    private String msg;
    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }
}
