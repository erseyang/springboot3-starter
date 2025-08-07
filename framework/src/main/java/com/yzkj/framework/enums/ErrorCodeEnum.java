package com.yzkj.framework.enums;

import com.yzkj.framework.exception.YzkjException;
import lombok.Getter;

/**
 * 错误码枚举
 */
@Getter
public enum ErrorCodeEnum {
    ENCRYPT_ERROR(1001,"数据加密失败"),
    DECODE_ERROR(1002,"数据解密失败"),;
    private final Integer code;
    private final String message;

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取异常对象
     * @param errorCode 错误码枚举
     * @return 业务异常对象
     */
    public static YzkjException getException(ErrorCodeEnum errorCode) {
        return new YzkjException(errorCode.getMessage(),errorCode.getCode());
    }
}
