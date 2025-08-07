package com.yzkj.starter.domain.user.model;

import com.yzkj.starter.application.model.UserItemDto;

public interface UserRepository {

    public UserItemDto queryUserByUserId(String userId);
}
