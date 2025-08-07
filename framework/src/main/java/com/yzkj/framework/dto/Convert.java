package com.yzkj.framework.dto;

import com.yzkj.framework.entity.BaseEntity;

public interface Convert<E extends BaseEntity> {
    public E convert();
}
