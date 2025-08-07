package com.yzkj.framework.domain;

public abstract class DomainEvent<T> {

    public abstract T publish();
}
