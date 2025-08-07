package com.yzkj.framework.constant;

import java.io.Serializable;

public class ResultConstant implements Serializable {

    /**
     * 数据值返回成功
     */
    public static final Integer RESULT_SUCCESS_CODE = 200;

    /**
     * 统一返回失败的状态码
     */
    public static final Integer RESULT_ERROR_CODE = 500;

    /**
     * 未登录的状态码
     */
    public static final Integer RESULT_NO_LOGIN_CODE = 1441;

    public static final Integer MSG_YZKJ_FRAMEWORK_HTTP_RESOURCE_NOT_ALLOW = 1442;

    public static final Integer MSG_YZKJ_FRAMEWORK_HTTP_REQUEST_SOURCE_UNDEFINED =  1443;

    /**
     * 没有权限
     */
    public static final Integer MSG_YZKJ_FRAMEWORK_HTTP_RESPONSE_NO_PERMISSION = 1444;

    /**
     * json转化出错
     */
    public static final Integer MSG_YZKJ_FRAMEWORK_JSON_ERROR = 2443;

}
