package com.yzkj.starter.mapper.user;


import com.yzkj.framework.mapper.BaseMapper;
import com.yzkj.starter.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    public int addUser(User user);
}
