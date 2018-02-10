package com.choudou5.root.security.certification.juhe;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.choudou5.base.util.AssertUtil;
import com.choudou5.base.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 聚合数据 API
 * @author xuhaowen
 */
public class JuHeUtil {

    public static final String IDENTITY_API = "http://op.juhe.cn/idcard/query";

    /**
     * 验证 实名身份证 (验证过的身份请保存)
     * @param idCard
     * @param realName
     * @param appKey
     * @api https://www.juhe.cn/docs/api/id/103
     * @return
     * @throws Exception
     */
    public boolean checkRealIdentity(String idCard, String realName, String appKey) throws Exception{
        AssertUtil.notNull(idCard, "身份证不能为空!");
        AssertUtil.notNull(realName, "实名姓名不能为空!");
        AssertUtil.isTrue(IdcardUtil.isValidCard(idCard), "非有效身份证号码!");
        AssertUtil.notNull(appKey, "appKey参数不能为空!");
        Map<String, Object> params = new HashMap<>();
        params.put("key", appKey);
        params.put("idcard", idCard);
        params.put("realname", realName);
        String response =null;
        try {
            response= HttpUtil.get(IDENTITY_API, params, 1000*5);
            AssertUtil.notNull(response, "实名认证接口 返回空!");
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtil.notNull(null, "实名认证接口 请求出错:" + e.getMessage());
        }

        JSONObject json = JsonUtil.toJsonObj(response);
        if(json.getIntValue("error_code") == 0){ //查询成功
            //1：匹配 2：不匹配
            int status = json.getJSONObject("result").getIntValue("res");
            return status == 1;/*1：匹配 2：不匹配*/
        }else {
            AssertUtil.isTrue(false, "实名接口返回错误:" + json.getString("reason"));
            return false;
        }
    }

}
