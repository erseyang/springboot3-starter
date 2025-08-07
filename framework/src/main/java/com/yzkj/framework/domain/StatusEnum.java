package com.yzkj.framework.domain;

import lombok.Getter;

@Getter
public enum StatusEnum {

    /**有效*/
    STATUS_VALID(1),
    /**无效*/
    STATUS_INVALID(0);

    private final int status;

     StatusEnum(int status) {
        this.status = status;
    }
}
