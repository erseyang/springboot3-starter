package com.yzkj.framework.mvc.interceptor;

import com.yzkj.framework.constant.ThreadLocalKeyConstant;
import com.yzkj.framework.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 框架拦截器，需要处于拦截器链的最前面
 * 用户统计用户请求接口的耗时等其他自定义的操作，
 * 最后完成之后会清空threadLocal中的数据
 */
@Slf4j
@SuppressWarnings("unused")
public class FrameworkInterceptor implements HandlerInterceptor {
    public boolean preHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, Object handler) throws Exception {
        // 请求处理前,清空Thread中缓存数据,防止线程复用,带来的数据混乱
        ThreadLocalUtil.removeThreadData();
        Long startTime = System.currentTimeMillis();
        ThreadLocalUtil.setThreadData(ThreadLocalKeyConstant.REQUEST_START_TIME, startTime);
        return true;
    }

    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Long endTime = System.currentTimeMillis();
        Long startTime = ThreadLocalUtil.getThreadDataByKey(ThreadLocalKeyConstant.REQUEST_START_TIME, Long.class);
        if(startTime != null){
            log.info("接口:[{}],耗时:{}ms",request.getRequestURI(),(endTime-startTime));
        }
        ThreadLocalUtil.removeThreadData();
    }
}
