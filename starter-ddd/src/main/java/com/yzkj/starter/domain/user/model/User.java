package com.yzkj.starter.domain.user.model;

import com.yzkj.framework.domain.DomainEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class User extends DomainEntity {

    private String userId;

    private Email email;

    public String generateUserId() {
        return "";
    }


    @Override
    public String generateId() {
        return "";
    }

    @Override
    public int hashCode(Object object) {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }
}
