package com.choudou5.message.sms.model;

import cn.hutool.json.JSONUtil;

import java.io.Serializable;

/**
 * @Name：说明
 * @Author：xuhaowen
 * @Date：2018-01-31
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class SmsReq implements Serializable {

    /**
     * 短信模板ID
     */
    private String tplCode;
    /**
     * 短信模板变量
     */
    private String tplJsonParam;
    /**
     * 业务ID  最终在短信回执消息中将此值带回给调用者
     */
    private String bizId;

    public SmsReq(String tplCode, SmsCode smsCode, String bizId) {
        this.tplCode = tplCode;
        this.tplJsonParam = JSONUtil.toJsonStr(smsCode);
        this.bizId = bizId;
    }


    public SmsReq(String tplCode, String tplJsonParam, String bizId) {
        this.tplCode = tplCode;
        this.tplJsonParam = tplJsonParam;
        this.bizId = bizId;
    }

    public String getTplCode() {
        return tplCode;
    }

    public void setTplCode(String tplCode) {
        this.tplCode = tplCode;
    }

    public String getTplJsonParam() {
        return tplJsonParam;
    }

    public void setTplJsonParam(String tplJsonParam) {
        this.tplJsonParam = tplJsonParam;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
}
