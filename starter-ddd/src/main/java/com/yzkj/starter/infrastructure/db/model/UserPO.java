package com.yzkj.starter.infrastructure.db.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yzkj.framework.po.BasePO;
import com.yzkj.starter.application.model.UserItemDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("el_user")
public class UserPO extends BasePO {

    private String userId;

    private String name;

    private String email;

    public static UserItemDto convertPO2Dto(UserPO userPO) {
        UserItemDto userItemDto = new UserItemDto();
        userItemDto.setUserName(userPO.getName());
        userItemDto.setUserId(userPO.getUserId());
        return userItemDto;
    }
}
