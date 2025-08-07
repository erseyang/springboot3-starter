package com.yzkj.framework.dto;

import com.yzkj.framework.entity.BaseEntity;

import java.io.Serializable;

/**
 * 所有请求的参数基类，统一继承自basedto
 */
public abstract class BaseDTO implements Serializable {

    private String id;
}
