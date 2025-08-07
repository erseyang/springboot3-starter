package com.yzkj.dubbo.user;

import com.yzkj.dubbo.BaseDubboService;
import com.yzkj.dubbo.user.entity.UserDubboEntity;

import java.util.List;

public interface UserDubboService extends BaseDubboService {

    List<UserDubboEntity> queryUserList();

    String queryUserName(String userId);
}
