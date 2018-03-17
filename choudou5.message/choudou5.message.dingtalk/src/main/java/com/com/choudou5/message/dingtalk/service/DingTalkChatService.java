package com.com.choudou5.message.dingtalk.service;

import com.alibaba.dingtalk.openapi.message.LinkMessage;

import java.util.List;

/**
 * @Name：钉钉群组 接口
 * @Author：xuhaowen
 * @Date：2018-03-17
 */
public interface DingTalkChatService {

    /**
     * 创建 群组
     * @param chatName
     * @param owner
     * @param userIdlist
     * @return 群组ID
     */
    String create(String chatName, String owner, List<String> userIdlist);

    /**
     * 发送 群组 文本消息
     * @param chatId
     * @param text
     */
    void sendTextMsg(String chatId, String text);


    /**
     * 发送 群组 链接消息
     * @param chatId
     * @param message
     */
    void sendLinkMsg(String chatId, LinkMessage message);

}
