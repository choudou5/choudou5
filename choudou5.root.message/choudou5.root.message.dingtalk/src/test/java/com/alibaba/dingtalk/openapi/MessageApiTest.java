package com.alibaba.dingtalk.openapi;

import com.alibaba.dingtalk.openapi.media.MediaHelper;
import com.alibaba.dingtalk.openapi.message.*;
import com.dingtalk.open.client.api.model.corp.MessageBody;
import com.dingtalk.open.client.api.model.corp.MessageType;
import com.dingtalk.open.client.api.model.corp.UploadResult;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Name：消息 API
 * @Author：xuhaowen
 * @Date：2018-01-27
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class MessageApiTest extends BaseApiTest {

    @Test
    public void mainTest() throws Exception {
        // 发送微应用消息
        List<String> toUserIdList = Arrays.asList("11");
        List<String> toDeptIdList = Arrays.asList("11"); //部门ID
        String appId = ""; //应用ID
        sendTextMessage(appId, toUserIdList, "8888888888");
        sendImageMessage(appId, toUserIdList, new ImageMessage("mediaId"));
        sendLinkMessage(appId, toUserIdList, new LinkMessage());
        sendOAMessage(appId, toUserIdList, new OAMessage());

        // 发送会话消息
//			String sender = Vars.SENDER;
//			String cid = Vars.CID;//cid需要通过jsapi获取，具体详情请查看开放平台文档--->客户端文档--->会话

//			ConversationMessageDelivery conversationMessageDelivery = new ConversationMessageDelivery(sender, cid,
//					agentId);
//
//			conversationMessageDelivery.withMessage(MessageType.TEXT, textBody);
//			MessageHelper.send(accessToken, conversationMessageDelivery);
//			log("成功发送 会话文本消息");
//			conversationMessageDelivery.withMessage(MessageType.IMAGE, imageBody);
//			MessageHelper.send(accessToken, conversationMessageDelivery);
//			log("成功发送 会话图片消息");
//			conversationMessageDelivery.withMessage(MessageType.LINK, linkBody);
//			MessageHelper.send(accessToken, conversationMessageDelivery);
//			log("成功发送 会话link消息");

    }

    //发送 文本消息
    private void sendTextMessage(String appId, List<String> toUserIdList, String content) throws Exception {
        MessageHelper.sendToUserList(accessToken, appId, toUserIdList, new TextMessage(content));
        log("成功发送 微应用文本消息");
    }

    //发送 图片消息
    private void sendImageMessage(String appId, List<String> toUserIdList, ImageMessage message) throws Exception {
        MessageHelper.sendToUserList(accessToken, appId, toUserIdList, message);
        log("成功发送 微应用图片消息");
    }

    //发送 link消息
    private void sendLinkMessage(String appId, List<String> toUserIdList, LinkMessage message) throws Exception {
        MessageHelper.sendToUserList(accessToken, appId, toUserIdList, message);
        log("成功发送 微应用link消息");
    }

    //发送 oa消息
    private void sendOAMessage(String appId, List<String> toUserIdList, OAMessage message) throws Exception {
        // 创建oa消息
        MessageBody.OABody oaBody = new MessageBody.OABody();
        oaBody.setMessage_url("message_url");
        OAMessage.Head head = new OAMessage.Head("标题", "FFBBBBBB");

        OAMessage.Body body = new OAMessage.Body();
        body.setAuthor("author");
        body.setContent("content");
        body.setImage("@image");
        body.setTitle("body.title");
        OAMessage.Body.Rich rich = new OAMessage.Body.Rich();
        rich.setNum("num");
        rich.setUnit("unit");
        body.setRich(rich);
        List<OAMessage.Body.Form> formList = new ArrayList<OAMessage.Body.Form>();
        OAMessage.Body.Form form = new OAMessage.Body.Form();
        form.setKey("key");
        form.setValue("value");
        formList.add(form);
        body.setForm(formList);

        message = new OAMessage("message_url", head, body);

        MessageHelper.sendToUserList(accessToken, appId, toUserIdList, message);
        log("成功发送 微应用oa消息");
    }
}
