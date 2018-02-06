package com.alibaba.dingtalk.openapi.auth;

import cn.hutool.core.io.FileUtil;
import com.alibaba.dingtalk.openapi.Env;
import com.alibaba.dingtalk.openapi.OApiException;
import com.alibaba.dingtalk.openapi.utils.HttpHelper;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.service.corp.CorpConnectionService;

/**
 * AccessToken和jsticket的获取封装
 */
public class AuthHelper {

    /** 请采用Spring注入 **/
    private static AuthService authService;
    public static AuthService getAuthService() {
        return authService;
    }
    public static void setAuthService(AuthService authService) {
        AuthHelper.authService = authService;
    }

    /*
     * 在此方法中，为了避免频繁获取access_token，
     * 在距离上一次获取access_token时间在两个小时之内的情况，
     * 将直接从持久化存储中读取access_token
     *
     * 因为access_token和jsapi_ticket的过期时间都是7200秒
     * 所以在获取access_token的同时也去获取了jsapi_ticket
     * 注：jsapi_ticket是在前端页面JSAPI做权限验证配置的时候需要使用的
     * 具体信息请查看开发者文档--权限验证配置
     */
    public static String getAccessToken() throws OApiException {
        return authService.getAccessToken();
    }

    public static String getNoCacheAccessToken() {
        return authService.getNoCacheAccessToken();
    }

    public static String getSsoToken() throws OApiException {
        return authService.getSsoToken();
    }

}
