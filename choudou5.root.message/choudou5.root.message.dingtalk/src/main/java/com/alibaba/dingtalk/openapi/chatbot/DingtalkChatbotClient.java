package com.alibaba.dingtalk.openapi.chatbot;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.dingtalk.openapi.chatbot.message.Message;

/**
 * @Createdby dustin
 * @date 2017/3/17.
 * @link https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.karFPe&treeId=257&articleId=105735&docType=1
 */
public class DingtalkChatbotClient {

    /**
     * 发送信息 (每个机器人每分钟最多发送20条)
     * @param url
     * @param message 消息对象
     * @return
     */
    public static SendResult send(String url, Message message) {
        HttpRequest request = HttpUtil.createPost(url);
        request.header("Content-Type", "application/json; charset=utf-8");
        request.body(message.toJsonString());
        SendResult result = null;
        try {
            HttpResponse response = request.execute();
            result = new SendResult();
            if (response.getStatus() == 200) {
                String body = response.body();
                JSONObject obj = JSONObject.parseObject(body);
                Integer errcode = obj.getInteger("errcode");
                result.setErrorCode(errcode);
                result.setErrorMsg(obj.getString("errmsg"));
                result.setIsSuccess(errcode.equals(0));
            }else{
                result.setErrorMsg(response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

}


