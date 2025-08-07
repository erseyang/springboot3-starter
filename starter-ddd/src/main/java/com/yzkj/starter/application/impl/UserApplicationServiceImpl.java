package com.yzkj.starter.application.impl;

import com.yzkj.starter.application.UserApplicationService;
import com.yzkj.starter.application.model.UserItemDto;
import com.yzkj.starter.domain.user.model.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    @Resource
    private UserRepository userRepository;

    @Override
    public String queryUserNameById(String userId) {
        return "";
    }

    @Override
    public UserItemDto queryUserItemById(String userId) {
        return userRepository.queryUserByUserId(userId);
    }
}
