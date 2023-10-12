package com.mun.user.domain.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResp {
    private String userName;
    private String token;
    private String iconUrl;
}
