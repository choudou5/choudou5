package com.alibaba.dingtalk.openapi;

import com.alibaba.dingtalk.openapi.media.MediaHelper;
import com.alibaba.dingtalk.openapi.message.LightAppMessageDelivery;
import com.alibaba.dingtalk.openapi.message.MessageHelper;
import com.dingtalk.open.client.api.model.corp.MessageBody;
import com.dingtalk.open.client.api.model.corp.MessageType;
import com.dingtalk.open.client.api.model.corp.UploadResult;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Name：说明
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
        String toUsers = "";
        String toParties = ""; //chatid 会话ID
        String agentId = ""; //应用ID
        LightAppMessageDelivery lightAppMessageDelivery = new LightAppMessageDelivery(toUsers, toParties, agentId);
        sendTextMessage(lightAppMessageDelivery);
        sendImageMessage(lightAppMessageDelivery);
        sendLinkMessage(lightAppMessageDelivery);
        sendOAMessage(lightAppMessageDelivery);

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
    private void sendTextMessage(LightAppMessageDelivery lightAppMessageDelivery) throws Exception {
        MessageBody.TextBody textBody = new MessageBody.TextBody();
        textBody.setContent("TextMessage");
        lightAppMessageDelivery.withMessage(MessageType.TEXT, textBody);
        MessageHelper.send(accessToken, lightAppMessageDelivery);
        log("成功发送 微应用文本消息");
    }

    //发送 图片消息
    private void sendImageMessage(LightAppMessageDelivery lightAppMessageDelivery) throws Exception {
        // 上传图片
        File file = new File("/Users/ian/Downloads/lALOAVYgbc0DIM0Bwg_450_800.png");
        UploadResult uploadResult = MediaHelper.upload(accessToken, MediaHelper.TYPE_IMAGE, file);
        log("成功上传图片", uploadResult);
        // 下载图片
        String fileDir = "/Users/ian/Desktop/";
        MediaHelper.download(accessToken, uploadResult.getMedia_id(), fileDir);
        log("成功下载图片");

        MessageBody.ImageBody imageBody = new MessageBody.ImageBody();
        imageBody.setMedia_id(uploadResult.getMedia_id());
        lightAppMessageDelivery.withMessage(MessageType.IMAGE, imageBody);
        MessageHelper.send(accessToken, lightAppMessageDelivery);
        log("成功发送 微应用图片消息");
    }

    //发送 link消息
    private void sendLinkMessage(LightAppMessageDelivery lightAppMessageDelivery) throws Exception {
        MessageBody.LinkBody linkBody = new MessageBody.LinkBody();
        linkBody.setMessageUrl("http://www.baidu.com");
        linkBody.setPicUrl("@lALOACZwe2Rk");
        linkBody.setTitle("Link Message");
        linkBody.setText("This is a link message");
        lightAppMessageDelivery.withMessage(MessageType.LINK, linkBody);
        MessageHelper.send(accessToken, lightAppMessageDelivery);
        log("成功发送 微应用link消息");
    }

    //发送 oa消息
    private void sendOAMessage(LightAppMessageDelivery lightAppMessageDelivery) throws Exception {
        // 创建oa消息
        MessageBody.OABody oaBody = new MessageBody.OABody();
        oaBody.setMessage_url("message_url");
        MessageBody.OABody.Head head = new MessageBody.OABody.Head();
        head.setText("head.text");
        head.setBgcolor("FFBBBBBB");
        oaBody.setHead(head);

        MessageBody.OABody.Body body = new MessageBody.OABody.Body();
        body.setAuthor("author");
        body.setContent("content");
        body.setFile_count("file_count");
        body.setImage("@image");
        body.setTitle("body.title");
        MessageBody.OABody.Body.Rich rich = new MessageBody.OABody.Body.Rich();
        rich.setNum("num");
        rich.setUnit("unit");
        body.setRich(rich);
        List<MessageBody.OABody.Body.Form> formList = new ArrayList<MessageBody.OABody.Body.Form>();
        MessageBody.OABody.Body.Form form = new MessageBody.OABody.Body.Form();
        form.setKey("key");
        form.setValue("value");
        formList.add(form);
        body.setForm(formList);
        oaBody.setBody(body);
        lightAppMessageDelivery.withMessage(MessageType.OA, oaBody);
        MessageHelper.send(accessToken, lightAppMessageDelivery);
        log("成功发送 微应用oa消息");
    }
}
