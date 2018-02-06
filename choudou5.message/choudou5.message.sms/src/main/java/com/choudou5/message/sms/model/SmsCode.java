package com.choudou5.message.sms.model;

import java.io.Serializable;

/**
 * @Name：短信 code
 * @Author：xuhaowen
 * @Date：2018-01-31
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class SmsCode implements Serializable {

    /**
     * 短信code  6位数字或字母
     */
    private String code;

    /**
     * @param code  6位数字或字母
     */
    public SmsCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
