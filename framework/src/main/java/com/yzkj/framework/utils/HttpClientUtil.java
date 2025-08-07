package com.yzkj.framework.utils;

import com.yzkj.framework.constant.ResultConstant;
import com.yzkj.framework.exception.YzkjException;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

/**
 * 使用jdk自带的httpclient实现
 */
@SuppressWarnings("unused")
public class HttpClientUtil {
    private  static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(30))
            .version(HttpClient.Version.HTTP_2).build();

    private HttpClientUtil(){}
    /**
     * GET请求
     * @param url 请求地址
     * @param params 查询参数（可为null）
     * @param headers 请求头（可为null）
     * @return 响应内容
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> headers) {
        try {
            String fullUrl = buildUrlWithParams(url, params);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(fullUrl))
                    .GET();

            addHeaders(requestBuilder, headers);

            return sendRequest(requestBuilder.build());
        } catch (Exception e) {
            throw new RuntimeException("HTTP GET请求失败", e);
        }
    }
    /**
     * POST表单请求
     * @param url 请求地址
     * @param formData 表单参数（可为null）
     * @param headers 请求头（可为null）
     * @return 响应内容
     */
    public static String doPostForm(String url, Map<String, String> formData, Map<String, String> headers) {
        return doPost(url, buildFormData(formData), "application/x-www-form-urlencoded", headers);
    }

    /**
     * POST JSON请求
     * @param url 请求地址
     * @param jsonBody JSON字符串
     * @param headers 请求头（可为null）
     * @return 响应内容
     */
    public static String doPostJson(String url, String jsonBody, Map<String, String> headers) {
        return doPost(url, jsonBody, "application/json", headers);
    }
    public static String doPostJson(String url, String jsonBody) {
        return doPost(url, jsonBody, "application/json", null);
    }


    // 核心POST方法
    private static String doPost(String url, String body, String contentType, Map<String, String> headers) {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(body));

            requestBuilder.header("Content-Type", contentType);
            addHeaders(requestBuilder, headers);

            return sendRequest(requestBuilder.build());
        } catch (Exception e) {
            throw new RuntimeException("HTTP POST请求失败", e);
        }
    }

    // 工具方法
    private static String buildUrlWithParams(String url, Map<String, String> params) {
        if (params == null || params.isEmpty()) return url;

        StringBuilder query = new StringBuilder();
        params.forEach((k, v) -> {
            query.append(URLEncoder.encode(k, StandardCharsets.UTF_8))
                    .append("=")
                    .append(URLEncoder.encode(v, StandardCharsets.UTF_8))
                    .append("&");
        });
        return url + (url.contains("?") ? "&" : "?") + query.substring(0, query.length()-1);
    }
    private static String buildFormData(Map<String, String> formData) {
        if (formData == null) return "";
        return buildUrlWithParams("", formData).substring(1); // 移除开头的?
    }

    private static void addHeaders(HttpRequest.Builder builder, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(builder::header);
        }
    }
    private static String sendRequest(HttpRequest request) throws Exception {
        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new YzkjException("HTTP异常状态码: " + response.statusCode(), ResultConstant.RESULT_ERROR_CODE);
        }
        return response.body();
    }
}
