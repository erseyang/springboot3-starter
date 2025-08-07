package com.yzkj.framework.entity;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    /**
     * 把对象转为字符串，用于打印
     * @return string
     */
    public abstract String toString();
}
