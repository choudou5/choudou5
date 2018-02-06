package com.alibaba.dingtalk.openapi.utils;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.dingtalk.openapi.Env;
import com.alibaba.dingtalk.openapi.OApiException;
import com.alibaba.dingtalk.openapi.auth.AuthHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * HTTP请求封装，建议直接使用sdk的API
 */
public class HttpHelper {

	public static JSONObject httpGet(String url) throws OApiException{
        String response = null;
        try {
            response = HttpUtil.get(Env.OAPI_HOST+url, 2000);
        } catch (Exception e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
            throw new OApiException(500, e.getMessage());
        }
        if(StrUtil.isBlank(response))
            return null;
        JSONObject result = JSON.parseObject(response);
        if (result.getInteger("errcode") == 0) {
            result.remove("errcode");
            result.remove("errmsg");
            return result;
        }else{
            int errCode = result.getInteger("errcode");
            //不合法的access_token
            if(errCode == 40014){
                String accessToken = AuthHelper.getNoCacheAccessToken();
                url = replaceUrlParamValue(Env.OAPI_HOST+url, "access_token", accessToken);
                return httpGet(url);
            }
            String errMsg = result.getString("errmsg");
            throw new OApiException(errCode, errMsg);
        }
    }

	public static JSONObject httpPost(String url, Object data) throws OApiException {
        HttpRequest request = HttpUtil.createPost(Env.OAPI_HOST+url);
        request.header("Content-Type", "application/json; charset=utf-8");
        request.timeout(2000);
        try {
            request.body(JSON.toJSONString(data));
            request.charset("UTF-8");
            HttpResponse response = request.execute();
            if (response.getStatus() == 200) {
                String body = response.body();
                JSONObject result = JSONObject.parseObject(body);
                if (result.getInteger("errcode") == 0) {
                    result.remove("errcode");
                    result.remove("errmsg");
                    return result;
                }else{
                    int errCode = result.getInteger("errcode");
                    //不合法的access_token
                    if(errCode == 40014){
                        String accessToken = AuthHelper.getNoCacheAccessToken();
                        url = replaceUrlParamValue(url, "access_token", accessToken);
                        return httpPost(Env.OAPI_HOST+url, data);
                    }
                    String errMsg = result.getString("errmsg");
                    throw new OApiException(errCode, errMsg);
                }
            }else{
                throw new OApiException(500, response.body());
            }
        } catch (Exception e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
            throw new OApiException(500, e.getMessage());
        }
    }
	

	public static JSONObject downloadMedia(String url, String fileDir) throws OApiException {
        File downloadFile = new File(fileDir);
        try {
            HttpUtil.downloadFile(Env.OAPI_HOST + url, downloadFile);
            JSONObject result = new JSONObject();
            result.put("downloadFilePath", downloadFile.getAbsolutePath());
            result.put("httpcode", "200");
            return result;
        } catch (Exception e) {
            System.out.println("downloadMedia url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
            throw new OApiException(500, e.getMessage());
        }
    }

    /**
     * 替换 url参数值
     * @param url
     * @param paramName
     * @param newParamValue
     * @return
     */
    public static String replaceUrlParamValue(String url, String paramName, String newParamValue){
        if(StrUtil.isBlank(url))
            throw new NullPointerException("param url not null.");
        if(StrUtil.isBlank(paramName))
            throw new NullPointerException("param paramName not null.");

        String host = getHost(url);
        Map<String, String> params = getUrlParams(url);
        params.put(paramName, newParamValue);
        return getGetUrl(host, params);
    }


    /**
     * 根据url提取url的参数
     *
     * @param url
     * @return
     */
    public static Map<String, String> getUrlParams(String url) {
        Map<String, String> result = new LinkedHashMap<>();
        if (url.contains("?")) {
            String paramsStr = url.substring(url.indexOf("?") + 1);
            String[] paramArr = paramsStr.split("&");
            for (String nameValPairStr : paramArr) {
                if (nameValPairStr.contains("=")) {
                    String[] nameValPair = nameValPairStr.split("=");
                    if (nameValPair.length > 1) {
                        result.put(nameValPair[0], nameValPair[1]);
                    } else if (nameValPair.length > 0) {
                        result.put(nameValPair[0], "");
                    }
                }
            }
        }
        return result;
    }

    /**
     * 根据url提取url的参数
     * @param url
     * @return
     */
    public static String getUrlParamValue(String url, String paramName) {
        Map<String, String> result = getUrlParams(url);
        return result.get(paramName);
    }

    /**
     * 根据url获取 基础url地址
     * @param url
     * @return
     */
    public static String getHost(String url) {
        try {
            URL urlObj = new URL(url);
            int port = urlObj.getPort();
            if(url.startsWith("https"))
                return "https://"+urlObj.getHost() + autoAppendPort(port);
            else
                return "http://"+urlObj.getHost() + autoAppendPort(port);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String autoAppendPort(int port){
        if(port != -1){
            return port!=80 ? ":"+port : "";
        }
        return "";
    }

    /**
     * 获取 GET url
     * @param baseUrl
     * @param params
     * @return
     */
    public static String getGetUrl(String baseUrl, Map<String, String> params) {
        if(MapUtil.isNotEmpty(params)){
            StringBuilder sb = new StringBuilder(params.size()*20);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            baseUrl = baseUrl+"?"+sb.toString();
            if (baseUrl.endsWith("&")) {
                baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
            }
            return baseUrl;
        }else{
            return baseUrl;
        }
    }

    public static boolean isHttps(String url) {
        return StrUtil.startWith(url, "https");
    }

}
