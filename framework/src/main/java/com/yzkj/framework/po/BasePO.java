package com.yzkj.framework.po;

import java.io.Serializable;
import java.util.Date;

public class BasePO implements Serializable {

    /**
     * 创建时间 COLUMN:CREATE_TIME.
     */
    private Date createTime;

    /**
     * 更新时间 COLUMN:UPDATE_TIME.
     */
    private Date updateTime;

    /**
     * 创建人ID COLUMN:CREATE_USER.
     */
    private String createUser;

    /**
     * 更新人ID COLUMN:UPDATE_USER.
     */
    private String updateUser;

    private Integer version;

    /**
     * 删除标记 COLUMN:IS_DELETE.
     */
    private Integer isDelete = 0;
}
