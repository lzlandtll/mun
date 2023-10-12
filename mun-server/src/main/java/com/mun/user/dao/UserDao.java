package com.mun.user.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mun.user.domain.entity.User;
import com.mun.user.domain.vo.request.UserReq;
import com.mun.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */
@Service
public class UserDao extends ServiceImpl<UserMapper, User> {
    public User accountLogin(UserReq userReq){
        return lambdaQuery()
                .eq(User::getUserId, userReq.getUserId())
                .eq(User::getPassword, userReq.getPassword())
                .select(User::getUserId, User::getPassword, User::getUserName, User::getIconUrl)
                .one();
    }
}
