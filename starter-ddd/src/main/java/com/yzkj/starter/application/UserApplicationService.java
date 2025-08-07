package com.yzkj.starter.application;

import com.yzkj.starter.application.model.UserItemDto;

/**
 * 应用服务
 */
public interface UserApplicationService {

    /**
     * 查询用户名称
     * @param userId ''
     * @return ''
     */
    public String queryUserNameById(String userId);

    public UserItemDto queryUserItemById(String userId);
}
