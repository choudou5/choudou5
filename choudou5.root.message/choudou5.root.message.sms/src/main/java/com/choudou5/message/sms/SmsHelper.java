package com.choudou5.message.sms;

import com.choudou5.message.sms.model.SmsReq;
import com.choudou5.message.sms.model.SmsQueryReq;
import com.choudou5.message.sms.model.SmsQueryResp;
import com.choudou5.message.sms.model.SmsSendResp;

import java.util.List;

/**
 * @Name：短信 助手
 * @Author：xuhaowen
 * @Date：2018-01-31
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public interface SmsHelper {

    SmsSendResp send(SmsReq message, List<String> phoneList);

    SmsSendResp send(SmsReq message, String ... phones);

    SmsQueryResp query(SmsQueryReq query, String phone);
}
