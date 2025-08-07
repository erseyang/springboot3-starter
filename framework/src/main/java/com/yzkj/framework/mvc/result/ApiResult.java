package com.yzkj.framework.mvc.result;

import com.yzkj.framework.exception.YzkjException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.yzkj.framework.constant.ResultConstant.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Tag(name="返回结果", description = "")
public class ApiResult<T> implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 4834226967478449715L;

    @Schema(description = "状态码")
    private Integer code;

    @Schema(description = "返回的消息")
    private String message;

    @Schema(description = "返回的数据")
    private T data;

    @Schema(description = "返回的时间")
    private String datetime;

    public ApiResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ApiResult<T> result(Integer code, String message, T data) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 定义格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化当前时间
        String formattedDate = now.format(formatter);
        return ApiResult.<T>builder().code(code).data(data).message(message).datetime(formattedDate).build();
    }

    public static <T> ApiResult<T> ok(T data) {
        return result(RESULT_SUCCESS_CODE, "数据获取成功", data);
    }

    public static <T> ApiResult<T> ok() {
        return result(RESULT_SUCCESS_CODE, "处理成功", null);
    }

    public static <T> ApiResult<T> needLogin() {
        return result(RESULT_NO_LOGIN_CODE, "需要重新登录", null);
    }

    public static <T> ApiResult<T> error(String message) {
        return result(RESULT_ERROR_CODE, message, null);
    }

    public static <T> ApiResult<T> error(Integer code, String message) {
        return result(code, message, null);
    }

    public static <T> String result2String(Integer code, String message, T data) {
        ApiResult<T> apiResult = result(code, message, data);
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(apiResult);
        } catch (JsonProcessingException e) {
            throw new YzkjException(e);
        }
    }

    public static <T> String needLogin(String message) {
        ApiResult<T> apiResult = result(RESULT_NO_LOGIN_CODE, message, null);
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(apiResult);
        } catch (JsonProcessingException e) {
            throw new YzkjException(e);
        }
    }

    public static <T> String needPermission(String message) {
        ApiResult<T> apiResult = result(MSG_YZKJ_FRAMEWORK_HTTP_RESPONSE_NO_PERMISSION, message, null);
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(apiResult);
        } catch (JsonProcessingException e) {
            throw new YzkjException(e);
        }
    }
}
