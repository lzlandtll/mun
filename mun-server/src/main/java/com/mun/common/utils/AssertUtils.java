package com.mun.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.mun.common.exception.BusinessErrorEnum;
import com.mun.common.exception.BusinessException;
import com.mun.common.exception.ErrorEnum;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */

public class AssertUtils {

    public static void isNotEmpty(Object obj, String msg){
        if(isEmpty(obj)){
            throwException(msg);
        }
    }

    public static void equal(Object o1, Object o2, String msg) {
        if (!ObjectUtil.equal(o1, o2)) {
            throwException(msg);
        }
    }
    private static boolean isEmpty(Object obj) {
        return ObjectUtil.isEmpty(obj);
    }
    private static void throwException(String msg) {
        throwException(null, msg);
    }
    private static void throwException(ErrorEnum errorEnum, Object... arg) {
        if (Objects.isNull(errorEnum)) {
            errorEnum = BusinessErrorEnum.BUSINESS_ERROR;
        }
        throw new BusinessException(errorEnum.getErrorCode(), MessageFormat.format(errorEnum.getErrorMsg(), arg));
    }
}
