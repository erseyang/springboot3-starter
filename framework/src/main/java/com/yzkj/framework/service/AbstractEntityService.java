package com.yzkj.framework.service;

import com.yzkj.framework.utils.JsonUtils;
import com.yzkj.framework.utils.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class AbstractEntityService implements BaseService {

    @Value("${user.prefix:}")
    private String redisPrefix;

    @Resource
    private RedisUtil redisUtil;


    /**
     * 获取登录用户信息
     *
     * @param token ''
     * @return ''
     */
    public Map<String, Object> getUser(String token) {
        String key = redisPrefix + token;
        String userJson = redisUtil.getString(key);
        Object object = JsonUtils.json2Bean(userJson, HashMap.class);
        if (object instanceof HashMap) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            return map;
        }
        return null;
    }
}
