package com.choudou5.message.sms.dayu;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.choudou5.message.sms.SmsHelper;
import com.choudou5.message.sms.model.SmsReq;
import com.choudou5.message.sms.model.SmsQueryReq;
import com.choudou5.message.sms.model.SmsQueryResp;
import com.choudou5.message.sms.model.SmsSendResp;

import java.util.Arrays;
import java.util.List;

/**
 * @Name：阿里大鱼 短信助手
 * @Author：xuhaowen
 * @Date：2018-01-31
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class DaYuSmsHelper implements SmsHelper {

    static final String PRODUCT = "Dysmsapi"; //短信API产品名称（短信产品名固定，无需修改）
    static final String API_DOMAIN = "dysmsapi.aliyuncs.com";

    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
    private IAcsClient acsClient;

    static{
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
    }

    private DaYuSmsHelper(){}

    public DaYuSmsHelper(String accessKeyId, String accessKeySecret, String signName) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.signName = signName;
        try {
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, API_DOMAIN);
            acsClient = new DefaultAcsClient(profile);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SmsSendResp send(SmsReq message, List<String> phoneList) {
        if(CollectionUtil.isEmpty(phoneList))
            Assert.notEmpty(phoneList, "发送号码不能为空");
        if(phoneList.size() > 1000)
            Assert.notEmpty(phoneList, "批量发送号码个数不能超过1000");
        try {
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(StrUtil.join(",", phoneList));
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(message.getTplCode());
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam(message.getTplJsonParam());
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId(message.getBizId());
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            System.out.println(JSONUtil.toJsonStr(sendSmsResponse));
            SmsSendResp resp = JSONUtil.toBean(JSONUtil.parseObj(sendSmsResponse), SmsSendResp.class);
            //请求成功
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                resp.setSuccess(true);
            }
            return resp;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SmsSendResp send(SmsReq message, String... phones) {
        return send(message, Arrays.asList(phones));
    }

    @Override
    public SmsQueryResp query(SmsQueryReq query, String phone) {
        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phone);
        //可选-调用发送短信接口时返回的BizId
        request.setBizId(query.getBizId());
        //必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
        request.setSendDate(query.getSendDate());
        //必填-页大小
        request.setPageSize(query.getPageSize());
        //必填-当前页码从1开始计数
        request.setCurrentPage(query.getCurrentPage());
        try {
            //hint 此处可能会抛出异常，注意catch
            QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
            //获取返回结果
            SmsQueryResp resp = JSONUtil.toBean(JSONUtil.parseObj(querySendDetailsResponse), SmsQueryResp.class);
            if(querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")){
                resp.setSuccess(true);
            }
            return resp;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
