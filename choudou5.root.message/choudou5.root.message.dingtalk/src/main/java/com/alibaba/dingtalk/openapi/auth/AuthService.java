package com.alibaba.dingtalk.openapi.auth;

/**
 * @Name：认证服务
 * @Author：xuhaowen
 * @Date：2018-01-27
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public interface AuthService {

    String getAccessToken();

    String getNoCacheAccessToken();

    String getSsoToken();

}
