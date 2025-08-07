package com.yzkj.starter.interceptor;

import com.yzkj.framework.annotation.Security;
import com.yzkj.framework.constant.FrameworkMessageConstant;
import com.yzkj.framework.constant.HttpParamConstants;
import com.yzkj.framework.mvc.result.ApiResult;
import com.yzkj.framework.utils.JsonUtils;
import com.yzkj.framework.utils.MessageUtils;
import com.yzkj.framework.utils.RedisUtil;
import com.yzkj.framework.utils.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${app.user.prefix:sso|}")
    private String redisPrefix;

    // 配置用户的redis过期时间
    @Value("${app.user.token.expire:7200}")
    private Integer tokenExpire;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, Object handler) throws Exception {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            log.debug("this request not support");
            response.setContentType(HttpParamConstants.HEADER_CONTENT_TYPE_JSON_UTF_8);
            PrintWriter out = response.getWriter();
            String message = MessageUtils.getMessages(FrameworkMessageConstant.FRAMEWORK_HTTP_REQUEST_NOT_SUPPORTED);
            String resultJson = ApiResult.result2String(11, message, null);
            out.append(resultJson);
            return false;
        }
        Security security = ((HandlerMethod) handler).getMethodAnnotation(Security.class);
        if (security == null) {
            log.debug("security is enabled");
            String message = MessageUtils.getMessages(FrameworkMessageConstant.FRAMEWORK_REQUEST_PERMISSION_UNDEFINED);
            String resultJson  = ApiResult.result2String(11, message, null);
            response.setContentType(HttpParamConstants.HEADER_CONTENT_TYPE_JSON_UTF_8);
            PrintWriter out = response.getWriter();
            out.append(resultJson);
            return false;
        }
        if (security.checkLogon()) {
            log.debug("security is enabled");
            String token = request.getParameter(HttpParamConstants.HEADER_PARAM_KEY_TOKEN);
            String key = redisPrefix + token;
            String userJson = redisUtil.getString(key);
//            Object object = redisUtil.get(key);
            if (StringUtils.isBlank(userJson)) {
                log.debug("user is null");
                response.setContentType(HttpParamConstants.HEADER_CONTENT_TYPE_JSON_UTF_8);
                PrintWriter out = response.getWriter();
                String message = MessageUtils.getMessages("need login");
                String resultJson = ApiResult.needLogin(message);
                out.append(resultJson);
                return false;
            }

            log.debug("用户信息:" + userJson);
//            @SuppressWarnings("unchecked")
            Map<String, Object> map = (HashMap<String, Object>) JsonUtils.json2Map(userJson, String.class, Object.class);
            updateUserRedis(key);
            //开始鉴权
            String resource = request.getRequestURI();
            //@todo 添加权限接口验证
//            if (!checkPermission(resource, (String) map.get("userId"))) {
//                response.setContentType(HttpParamConstants.HEADER_CONTENT_TYPE_JSON_UTF_8);
//                PrintWriter out = response.getWriter();
//                String resultJson = ApiResult.needPermission(message);
//                out.append(resultJson);
//
//                return false;
//            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 更新用户在redis中的时间
     * @param token
     */
    private void updateUserRedis(String token) {
        String redisToken = redisPrefix + token;
        redisUtil.expire(redisToken, tokenExpire);
    }

    /**
     * 权限检查
     * @param path
     * @param userId
     * @return
     */
    private boolean checkPermission(String path, String userId) {
        //@todo 后续再完善
        return true;
    }
}
