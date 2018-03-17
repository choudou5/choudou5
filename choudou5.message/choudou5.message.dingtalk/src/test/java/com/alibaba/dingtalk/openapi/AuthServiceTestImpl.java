package com.alibaba.dingtalk.openapi;

import com.com.choudou5.message.dingtalk.service.DingTalkAuthService;
import com.alibaba.dingtalk.openapi.utils.HttpHelper;
import com.alibaba.fastjson.JSONObject;
import com.choudou5.base.util.FileUtil;
import com.choudou5.base.util.StrUtil;

/**
 * @Name：认证服务实现
 * @Author：xuhaowen
 * @Date：2018-01-27
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class AuthServiceTestImpl implements DingTalkAuthService {

    //企业应用接入秘钥相关
    private String corpId;
    private String corpSecret;
    private String ssoSecret;

    public AuthServiceTestImpl() {
    }

    public AuthServiceTestImpl(String corpId, String corpSecret) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
    }

    public AuthServiceTestImpl(String corpId, String corpSecret, String ssoSecret) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
        this.ssoSecret = ssoSecret;
    }

    @Override
    public String getAccessToken() {
        return getAccessToken(true);
    }

    @Override
    public String getNoCacheAccessToken() {
        return getAccessToken(false);
    }

    /**
     * 获取 访问令牌
     * @param beforeGetCache 是否先获取缓存的
     * @return
     */
    private String getAccessToken(boolean beforeGetCache) {
        String cachePath = "/data/cache/dingtalk/accessToken.txt";
        String accessToken = null;
        if(beforeGetCache){
            try {
                accessToken = FileUtil.readString(cachePath, "utf8"); //redisCacheService.get(cacheKey);
            } catch (Exception e) {
            }
        }
        if (StrUtil.isBlank(accessToken)) {
            String url = "/gettoken?corpid="+corpId+"&corpsecret="+corpSecret;
            try {
                JSONObject accessTokenJson = HttpHelper.httpGet(url);
                accessToken = accessTokenJson.getString("access_token");
                // cache accessToken
                FileUtil.writeString(accessToken, cachePath, "utf8"); //redisCacheService.put(cacheKey, accessToken, 100, TimeUnit.MINUTES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accessToken;
    }



    @Override
    public String getSsoToken() {
        String ssoToken = null;
        try {
            String url = "/sso/gettoken?corpid=" + corpId + "&corpsecret=" + ssoSecret;
            JSONObject response = HttpHelper.httpGet(url);
            if (response.containsKey("access_token")) {
                ssoToken = response.getString("access_token");
            } else {
                throw new OApiException("Sso_token");
            }
        } catch (OApiException e) {
            e.printStackTrace();
        }
        return ssoToken;
    }
}
