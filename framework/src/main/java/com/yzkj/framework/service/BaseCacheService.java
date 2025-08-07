package com.yzkj.framework.service;

import java.util.concurrent.TimeUnit;

public interface BaseCacheService {
    Object get(String key);
    //设置缓存失效时间
    void expire(String key, long expire, TimeUnit timeUnit);

    //获取string缓存
    String getString(String key);
}
