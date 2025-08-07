package com.yzkj.framework.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 现成本地变量操作工具类
 */
@SuppressWarnings("unused")
public class ThreadLocalUtil {
    /**
     * 线程数据缓存
     */
    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    /**
     * 移除线程缓存数据,在线程处理结束后需要调用此方法,以免造成内存泄漏
     */
    public static void removeThreadData() {
        threadLocal.remove();
    }

    /**
     * 根据key移除缓存数据
     *
     * @param dataKey 线程缓存key
     */
    public static void removeThreadDataByKey(String dataKey) {
        Map<String, Object> dataMap = threadLocal.get();
        if (dataMap != null) {
            dataMap.remove(dataKey);
        }
    }

    /**
     * 从线程变量中获取私有缓存数据
     *
     * @param dataKey 数据key
     * @return 数据对象
     */
    public static Object getThreadDataByKey(String dataKey) {
        Map<String, Object> dataMap = threadLocal.get();
        if (dataMap != null) {
            return dataMap.get(dataKey);
        }
        return null;
    }

    /**
     * 根据key从线程变量中获取指定类型的数据
     * @param dataKey 数据key
     * @param clazz 数据类型
     * @return 数据对象
     */
    public static <T> T getThreadDataByKey(String dataKey, Class<T> clazz) {
        Object data = getThreadDataByKey(dataKey);
        if (clazz.isInstance(data)) {
            return clazz.cast(data);
        }
        return null;
    }

    /**
     * 根据Key把数据保存到数据缓存中
     * @param dataKey 数据key
     * @param data 数据值
     */
    public static void setThreadData(String dataKey, Object data) {
        Map<String, Object> dataMap = threadLocal.get();
        if (dataMap == null) {
            dataMap = new HashMap<>();
            threadLocal.set(dataMap);
        }
        dataMap.put(dataKey, data);
    }


}
