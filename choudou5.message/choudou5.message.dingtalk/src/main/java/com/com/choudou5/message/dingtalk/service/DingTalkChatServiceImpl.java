package com.com.choudou5.message.dingtalk.service;

import com.alibaba.dingtalk.openapi.message.LinkMessage;
import com.alibaba.dingtalk.openapi.message.TextMessage;
import com.alibaba.dingtalk.openapi.message.chat.ChatGroupHelper;
import com.choudou5.base.exception.SysException;
import com.dingtalk.open.client.api.model.corp.ChatInfo;

import java.util.List;

/**
 * @Name：钉钉群组 service
 * @Author：xuhaowen
 * @Date：2018-03-17
 */
public class DingTalkChatServiceImpl implements DingTalkChatService {

    private DingTalkAuthService authService;
    public DingTalkAuthService getAuthService() {
        return authService;
    }
    public void setAuthService(DingTalkAuthService authService) {
        this.authService = authService;
    }


    @Override
    public String create(String chatName, String owner, List<String> userIdlist) {
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setName(chatName);
        chatInfo.setOwner(owner);
        chatInfo.setUseridlist(userIdlist);
        String chatId = null;
        try {
            chatId = ChatGroupHelper.createChat(authService.getAccessToken(), chatInfo);
        } catch (Exception e) {
            throw new SysException("创建钉钉群组失败", e);
        }
        return chatId;
    }

    @Override
    public void sendTextMsg(String chatId, String text) {
        TextMessage message = new TextMessage(text);
        try {
            ChatGroupHelper.sendMsg(authService.getAccessToken(), chatId, message);
        } catch (Exception e) {
            throw new SysException("发送钉钉群组消息失败", e);
        }
    }

    @Override
    public void sendLinkMsg(String chatId, LinkMessage message) {
        try {
            ChatGroupHelper.sendMsg(authService.getAccessToken(), chatId, message);
        } catch (Exception e) {
            throw new SysException("发送钉钉群组消息失败", e);
        }
    }


}
