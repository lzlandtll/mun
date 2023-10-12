package com.mun.user.service;

import com.mun.user.domain.entity.User;
import com.mun.user.domain.vo.request.UserReq;
import com.mun.user.domain.vo.response.UserResp;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */
public interface UserService {
    UserResp accountLogin(UserReq user);
}
