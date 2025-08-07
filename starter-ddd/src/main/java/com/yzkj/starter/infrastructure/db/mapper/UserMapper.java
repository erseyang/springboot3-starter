package com.yzkj.starter.infrastructure.db.mapper;

import com.yzkj.starter.infrastructure.db.model.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.yzkj.framework.mapper.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

    UserPO queryUserByUserId(@Param("userId") String userId);

//    Integer addUser(UserPO userPO);
}
