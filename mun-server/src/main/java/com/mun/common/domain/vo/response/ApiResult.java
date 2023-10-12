package com.mun.common.domain.vo.response;

import com.mun.common.exception.ErrorEnum;
import lombok.Data;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */
@Data
public class ApiResult<T> {
    private Boolean success;
    private Integer errorCode;
    private String errorMsg;
    private T data;
    public static <T> ApiResult<T> success(){
        ApiResult<T> result = new ApiResult<>();
        result.setData(null);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> ApiResult<T> success(T data){
        ApiResult<T> result = new ApiResult<>();
        result.setData(data);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> ApiResult<T> fail(Integer code, String msg) {
        ApiResult<T> result = new ApiResult<T>();
        result.setSuccess(Boolean.FALSE);
        result.setErrorCode(code);
        System.out.println("msg: " + msg);
        result.setErrorMsg(msg);
        System.out.println("getErrorMsg: " + result.getErrorMsg());
        return result;
    }
    public static <T> ApiResult<T> fail(ErrorEnum errorEnum) {
        ApiResult<T> result = new ApiResult<T>();
        result.setSuccess(Boolean.FALSE);
        result.setErrorCode(errorEnum.getErrorCode());
        result.setErrorMsg(errorEnum.getErrorMsg());
        return result;
    }


    public boolean isSuccess() {
        return this.success;
    }
}
