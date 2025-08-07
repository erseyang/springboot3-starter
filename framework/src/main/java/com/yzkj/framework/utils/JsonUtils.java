package com.yzkj.framework.utils;

import com.yzkj.framework.constant.FrameworkMessageConstant;
import com.yzkj.framework.constant.ResultConstant;
import com.yzkj.framework.exception.YzkjException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 把json 转为map对象
     * @param json
     * @param keyType
     * @param valueType
     * @return map
     * @param <K>
     * @param <V>
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> json2Map(String json, Class<K> keyType, Class<V> valueType) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructMapType(HashMap.class, keyType, valueType);
            return (Map<K, V>) objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            Integer code = ResultConstant.MSG_YZKJ_FRAMEWORK_JSON_ERROR;
            String message = MessageUtils.getMessages(FrameworkMessageConstant.YZKJ_JSON_ERROR);
            throw new YzkjException(code, message, e);
        }
    }

    public static Object json2Bean(String json, Class<?> cls) {
        try {
            return objectMapper.readValue(json, cls);
        } catch (Exception e) {
            Integer code = ResultConstant.MSG_YZKJ_FRAMEWORK_JSON_ERROR;
            String message = MessageUtils.getMessages(FrameworkMessageConstant.YZKJ_JSON_ERROR);
            throw new YzkjException(code, message, e);
        }
    }

    /**
     * 把json node转为对象
     * @param jsonNode ‘’
     * @param cls ‘’
     * @return ''
     * @param <T>
     */
    public static <T> T jsonNode2Bean(JsonNode jsonNode, Class<T> cls) {
        try {
            return objectMapper.treeToValue(jsonNode, cls);
        } catch (Exception e) {
            Integer code = ResultConstant.MSG_YZKJ_FRAMEWORK_JSON_ERROR;
            String message = MessageUtils.getMessages(FrameworkMessageConstant.YZKJ_JSON_ERROR);
            throw new YzkjException(code, message, e);
        }
    }

    /**
     * 对象转为字符串
     * @param bean object
     * @return string
     */
    public static String bean2String(Object bean) {
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS); // 确保 null 字段被序列化
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            return objectMapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            Integer code = ResultConstant.MSG_YZKJ_FRAMEWORK_JSON_ERROR;
            String message = MessageUtils.getMessages(FrameworkMessageConstant.YZKJ_JSON_ERROR);
            throw new YzkjException(code, message, e);
        }
    }

    /**
     * map 转为 string
     * @param map .
     * @return str
     */
    public static String map2String(Map<?, ?> map) {
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            Integer code = ResultConstant.MSG_YZKJ_FRAMEWORK_JSON_ERROR;
            String message = MessageUtils.getMessages(FrameworkMessageConstant.YZKJ_JSON_ERROR);
            throw new YzkjException(code, message, e);
        }
    }

    public static <T> List<T> jsonToList(String json, Class<T> elementClasses) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, elementClasses);
            return (List<T>) objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            Integer errorCode = ResultConstant.MSG_YZKJ_FRAMEWORK_JSON_ERROR;
            String errorMessage = MessageUtils.getMessages(FrameworkMessageConstant.YZKJ_JSON_ERROR);
            throw new YzkjException(errorCode, errorMessage, e);
        }
    }

    public static <T> T json2RealBean(String json, Class<T> cls) {
        try {
            return objectMapper.readValue(json, cls);
        } catch (Exception e) {
            Integer code = ResultConstant.MSG_YZKJ_FRAMEWORK_JSON_ERROR;
            String message = MessageUtils.getMessages(FrameworkMessageConstant.YZKJ_JSON_ERROR);
            throw new YzkjException(code, message, e);
        }
    }

    /**
     * 将java对象转换成json字符串
     * @param object 准备转换的对象
     * @return e
     */
    public static String bean2Json(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            Integer code = ResultConstant.MSG_YZKJ_FRAMEWORK_JSON_ERROR;
            String message = MessageUtils.getMessages(FrameworkMessageConstant.YZKJ_JSON_ERROR);
            throw new YzkjException(code, message, e);
        }
    }

}
