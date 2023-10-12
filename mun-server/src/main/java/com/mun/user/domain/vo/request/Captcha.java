package com.mun.user.domain.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/10/9
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Captcha {
    private String code;
    private long expirationTime; // 过期时间戳
    /**
     * auth_registry: 账号注册场景
     */
    private String scene; // 场景信息
}
