package com.alibaba.dingtalk.openapi.message.chat;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dingtalk.openapi.message.LightAppMessageDelivery;
import com.alibaba.dingtalk.openapi.message.Message;
import com.alibaba.dingtalk.openapi.utils.HttpHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.ChatInfo;
import com.dingtalk.open.client.api.model.corp.MessageBody;
import com.dingtalk.open.client.api.model.corp.MessageSendResult;
import com.dingtalk.open.client.api.model.corp.MessageType;
import com.dingtalk.open.client.api.service.corp.ChatService;
import com.dingtalk.open.client.api.service.corp.MessageService;
import com.dingtalk.open.client.common.ParamAttr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name：聊天组 助手
 * @Author：xuhaowen
 * @Date：2018-01-29
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class ChatGroupHelper {

    //doc: https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.bpMc6I&treeId=385&articleId=104977&docType=1#s1

    /**
     * 创建群组
     * @param accessToken
     * @param chatInfo
     * @return
     * @throws Exception
     */
    public static String createChat(String accessToken, ChatInfo chatInfo)
            throws Exception {
        String url = "/chat/create?access_token="+accessToken;
        JSONObject jsonObject = HttpHelper.httpPost(url, chatInfo);
        System.out.println(JSON.toJSONString(jsonObject));
        return jsonObject.getString("chatid");
    }

    /**
     * 获得 群组信息
     * @param accessToken
     * @param chatid 群组ID
     * @return
     * @throws Exception
     */
    public static ChatInfo getChatInfo(String accessToken, String chatid)
            throws Exception {
        String url = "/chat/get?access_token="+accessToken+"&chatid="+chatid;
        JSONObject jsonObject = HttpHelper.httpGet(url);
        return jsonObject.toJavaObject(ChatInfo.class);
    }

    /**
     *  发送 群组信息
     * @param accessToken
     * @param chatid
     * @param message
     * @throws Exception
     */
    public static void sendMsg(String accessToken, String chatid, Message message)
            throws Exception {
        String url = "/chat/send?access_token="+accessToken;
        Map params = new HashMap<>();
        params.put("chatid", chatid);
        params.put("msgtype", message.type());
        params.put(message.type(), JSON.toJSONString(message));
        HttpHelper.httpPost(url, params);
    }

}
