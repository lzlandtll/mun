package com.mun.user.service.impl;

import com.mun.common.utils.AssertUtils;
import com.mun.common.utils.JwtUtils;
import com.mun.user.dao.UserDao;
import com.mun.user.domain.entity.User;
import com.mun.user.domain.vo.request.UserReq;
import com.mun.user.domain.vo.response.UserResp;
import com.mun.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDao userDao;

    @Override
    public UserResp accountLogin(UserReq user) {
        User userResult = userDao.accountLogin(user);
        AssertUtils.isNotEmpty(userResult, "账号或则密码错误");
        UserResp userResp = new UserResp();
        BeanUtils.copyProperties(userResult, userResp);
        String token = jwtUtils.createToken(userResult.getUserId());
        userResp.setToken(token);
        return userResp;
    }
}
