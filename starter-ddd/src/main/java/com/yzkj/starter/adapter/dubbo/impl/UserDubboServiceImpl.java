package com.yzkj.starter.adapter.dubbo.impl;

import com.yzkj.dubbo.user.UserDubboService;
import com.yzkj.dubbo.user.entity.UserDubboEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * userdubboservice调用方
 */
@DubboService
@Component
public class UserDubboServiceImpl implements UserDubboService {
    @Override
    public List<UserDubboEntity> queryUserList() {
        return List.of();
    }

    @Override
    public String queryUserName(String userId) {
        return String.format("Yzkj User %s", userId);
    }
}
