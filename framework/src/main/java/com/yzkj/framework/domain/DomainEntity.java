package com.yzkj.framework.domain;

import lombok.Data;

/**
 * 领域模型所有实体的基类
 */
@Data
public abstract class DomainEntity {

    private String id;

    public String getId() {
        return id;
    }

    /**
     * 生成ID
     *
     * @return
     */
    public abstract String generateId();

    public abstract int hashCode(Object object);

    public abstract String toString();
}
