package com.yzkj.framework.constant;

import java.util.concurrent.TimeUnit;

public class UserCacheConstant {
    public static final String USER_TOKEN_KEY = "yzkj:user:token:";
    /**
     * 用户信息缓存key，可以确定用户是否在线有效期与token的有效期相同
     */
    public static final String USER_INFO_KEY = "yzkj:user:info:";
    // 默认过期时间，30分钟
    public static final long EXPIRE_TIME = 60 * 30L;
    public static final TimeUnit EXPIRE_TIME_UNIT = TimeUnit.SECONDS;

}
