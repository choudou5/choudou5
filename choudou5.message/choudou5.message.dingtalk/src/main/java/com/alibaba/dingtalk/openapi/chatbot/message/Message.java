package com.alibaba.dingtalk.openapi.chatbot.message;

import java.io.Serializable;

/**
 * Created by dustin on 2017/3/17.
 */
public interface Message extends Serializable{

    /**
     * 返回消息的Json格式字符串
     *
     * @return 消息的Json格式字符串
     */
    String toJsonString();
}
