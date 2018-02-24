package com.alibaba.dingtalk.openapi.auth;

/**
 * @Name
 * @Author xuhaowen
 * @Date 2018-01-27
 */
public interface AuthService {

    String getAccessToken();

    String getNoCacheAccessToken();

    String getSsoToken();

}
