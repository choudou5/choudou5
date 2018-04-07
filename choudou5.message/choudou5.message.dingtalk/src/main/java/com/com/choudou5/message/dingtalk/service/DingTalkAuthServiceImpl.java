package com.com.choudou5.message.dingtalk.service;

import com.alibaba.dingtalk.openapi.utils.HttpHelper;
import com.alibaba.fastjson.JSONObject;
import com.choudou5.base.helper.CacheHelper;
import com.choudou5.base.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Name：DingTalkAuthServiceImpl 说明
 * @Author：xuhaowen
 * @Date：2018-03-18
 */
public class DingTalkAuthServiceImpl implements DingTalkAuthService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String CACHE_KEY = "dingtalk_auth_accesstoken";

    private String cachePrefx = "";
    private CacheHelper cache;
    //企业应用接入秘钥相关
    private String corpId;
    private String corpSecret;

    @Override
    public String getAccessToken() {
        String accessToken = cache.get(cachePrefx+CACHE_KEY);
        if(StrUtil.isBlank(accessToken))
            accessToken = httpGetAccessToken();
        else
            accessToken = accessToken.replace("\"", "");
        return accessToken;
    }

    private String httpGetAccessToken(){
        String accessToken = null;
        String url = "/gettoken?corpid="+corpId+"&corpsecret="+corpSecret;
        try {
            JSONObject accessTokenJson = HttpHelper.httpGet(url);
            accessToken = accessTokenJson.getString("access_token");
            if(StrUtil.isNotBlank(accessToken))
                cache.put(cachePrefx+CACHE_KEY, accessToken, 3600);
        } catch (Exception e) {
            logger.error("获取钉钉accessToken失败", e);
        }
        return accessToken;
    }


    @Override
    public String getNoCacheAccessToken() {
        return httpGetAccessToken();
    }

    @Override
    public String getSsoToken() {
        String ssoToken = null;
//        try {
//            String url = "/sso/gettoken?corpid=" + corpId + "&corpsecret=" + ssoSecret;
//            JSONObject response = HttpHelper.httpGet(url);
//            if (response.containsKey("access_token")) {
//                ssoToken = response.getString("access_token");
//            } else {
//                throw new OApiException("Sso_token");
//            }
//        } catch (OApiException e) {
//            e.printStackTrace();
//        }
        return ssoToken;
    }

    public String getCachePrefx() {
        return cachePrefx;
    }
    public void setCachePrefx(String cachePrefx) {
        this.cachePrefx = cachePrefx;
    }
    public CacheHelper getCache() {
        return cache;
    }
    public void setCache(CacheHelper cache) {
        this.cache = cache;
    }
    public String getCorpId() {
        return corpId;
    }
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }
    public String getCorpSecret() {
        return corpSecret;
    }
    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

}
