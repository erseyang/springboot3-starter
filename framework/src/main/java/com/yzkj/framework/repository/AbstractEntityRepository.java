package com.yzkj.framework.repository;

import com.yzkj.framework.mapper.EntityMapper;
import com.yzkj.framework.po.BasePO;

public abstract class AbstractEntityRepository<T extends BasePO> implements EntityRepository<T> {

    public abstract EntityMapper<T> getMapper();

}
