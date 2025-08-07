package com.yzkj.framework.handler;

import com.yzkj.framework.exception.YzkjException;
import com.yzkj.framework.mvc.result.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler({YzkjException.class})
    public ApiResult<Void> yzkjExceptionHandler(HttpServletRequest request, Exception e) {
        log.info(String.format("请求的地址为：%s", request.getRequestURI()));
        log.error(e.getMessage(), e);
        return ApiResult.error(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ApiResult<Void> otherException(HttpServletRequest request,Exception e) {
        log.error("请求的地址为：{},异常:", request.getRequestURI(), e);
        return ApiResult.error("处理失败");
    }

    /**
     * 参数校验失败异常
     * @param request http请求
     * @param e 参数校验异常
     * @return 返回错误信息
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResult<Void> handleValidationException(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.info("请求参数校验不通过,请求的地址为：{}", request.getRequestURI());
        String errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField()+":"+fieldError.getDefaultMessage())
                .collect(Collectors.joining(","));
        return ApiResult.error("参数校验不通过:" + errors);
    }
}
