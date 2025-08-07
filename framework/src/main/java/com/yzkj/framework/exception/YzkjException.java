package com.yzkj.framework.exception;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 亿量统一的异常接口
 */
@Getter
public class YzkjException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -3088400522970364299L;

    private Integer code;

    public YzkjException() {
    }

    public YzkjException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public YzkjException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public YzkjException(Throwable cause) {
        super(cause);
    }

    public YzkjException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
