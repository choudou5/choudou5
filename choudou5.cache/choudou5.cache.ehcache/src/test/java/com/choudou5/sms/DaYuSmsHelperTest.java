package com.choudou5.sms;

import com.choudou5.message.sms.dayu.DaYuSmsHelper;
import com.choudou5.message.sms.model.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @Name：说明
 * @Author：xuhaowen
 * @Date：2018-01-31
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class DaYuSmsHelperTest extends BaseApiTest{

    private DaYuSmsHelper smsHelper;

    @Before
    public void init() {
        String accessKeyId = "LTAIoqD61zmseh3x";
        String accessKeySecret = "5sdDxb5gXHTQiYIkoe0iB0SH5GXYHz";
        String signName = "臭豆5";
        smsHelper = new DaYuSmsHelper(accessKeyId, accessKeySecret, signName);
    }


    @Test
    public void send() {
        SmsReq message = new SmsReq("SMS_123668492", new SmsCode("555423"), "1001");
        SmsSendResp resp = smsHelper.send(message, "17089497511");
        log(resp);
    }

    @Test
    public void query() {
        SmsQueryReq query = new SmsQueryReq(10L, 1L);
        SmsQueryResp resp = smsHelper.query(query, "17089497511");
        log(resp);
    }


}
