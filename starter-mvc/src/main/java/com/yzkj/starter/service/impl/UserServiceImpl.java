package com.yzkj.starter.service.impl;

import com.yzkj.dubbo.user.UserDubboService;
import com.yzkj.starter.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @DubboReference
    private UserDubboService userDubboService;


    @Override
    public String queryName(String userId) {
        return userDubboService.queryUserName(userId);
    }
}
