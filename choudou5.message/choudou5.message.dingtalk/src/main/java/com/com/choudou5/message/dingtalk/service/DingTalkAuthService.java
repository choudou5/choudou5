package com.com.choudou5.message.dingtalk.service;

/**
 * @Name 钉钉认证 接口
 * @Author xuhaowen
 * @Date 2018-01-27
 */
public interface DingTalkAuthService {

    String getAccessToken();

    String getNoCacheAccessToken();

    String getSsoToken();

}
