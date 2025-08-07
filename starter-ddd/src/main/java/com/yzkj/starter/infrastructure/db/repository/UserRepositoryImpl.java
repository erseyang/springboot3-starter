package com.yzkj.starter.infrastructure.db.repository;

import com.yzkj.starter.application.model.UserItemDto;
import com.yzkj.starter.domain.user.model.UserRepository;
import com.yzkj.starter.infrastructure.db.mapper.UserMapper;
import com.yzkj.starter.infrastructure.db.model.UserPO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserItemDto queryUserByUserId(String userId) {
        UserPO po = userMapper.queryUserByUserId(userId);
        return UserPO.convertPO2Dto(po);
    }
}
