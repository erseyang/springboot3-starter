package com.yzkj.framework.utils;

import com.yzkj.framework.constant.ResultConstant;
import com.yzkj.framework.exception.YzkjException;
import com.yzkj.framework.mvc.result.ApiResult;

import java.util.Objects;

@SuppressWarnings("unused")
public class ApiResultUtil {
    /**
     * 获取远程调用结果,如果不成功，则直接抛出异常,成功则返回Data数据
     * @param result 调用结果
     * @return 返回结果
     * @param <T> 结果参数
     */
    public static <T> T getResultData(ApiResult<T> result){
        if (!Objects.equals(result.getCode(), ResultConstant.RESULT_SUCCESS_CODE)) {
            throw new YzkjException(result.getMessage(), result.getCode());
        }
        return result.getData();
    }
}
