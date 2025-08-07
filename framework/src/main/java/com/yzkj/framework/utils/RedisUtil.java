package com.yzkj.framework.utils;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final Long RELEASE_SUCCESS = 1L;

    private static final String RELEASE_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
            " return redis.call('del', KEYS[1])  " +
            " ELSE " +
            "return 0 " +
            "end";

    @PostConstruct
    public void init() {
        log.info("RedisUtil init");
    }

    public boolean expire(String key, int seconds) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        return Boolean.TRUE.equals(redisTemplate.expire(key, seconds, TimeUnit.SECONDS));
    }

    public boolean expire(String key, long expire, TimeUnit unit) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        unit = unit == null ? TimeUnit.SECONDS : unit;
        return Boolean.TRUE.equals(redisTemplate.expire(key, expire, unit));
    }

    // 设置键值对
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 设置键值对并指定过期时间
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // 获取值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 获取值
    public String getString(String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        return obj == null ? null : obj.toString();
    }

    // 删除键
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    // 判断键是否存在
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    // 如果不存在，则设置
    public Boolean sexNx(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    // 如果不存在，则设置，附带过期时间
    public Boolean tryLock(String lockKey, String requestoId, long seconds) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey, requestoId, seconds, TimeUnit.SECONDS);
    }

    // 如果不存在，则设置，附带过期时间，并带上时间单位
    public Boolean tryLock(String lockKey, String requestoId, long timeout, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey, requestoId, timeout, unit);
    }

    // 不存在返回true, 存在则删除
    public Boolean releaseLock(String lockKey, String requestoId) {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText(RELEASE_SCRIPT);
        script.setResultType(Long.class);
        Long result = redisTemplate.execute(script, Collections.singletonList(lockKey), Collections.singletonList(requestoId));
        return RELEASE_SUCCESS.equals(result);
    }


}
