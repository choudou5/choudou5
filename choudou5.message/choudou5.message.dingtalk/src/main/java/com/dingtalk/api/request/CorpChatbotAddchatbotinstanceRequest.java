package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpChatbotAddchatbotinstanceResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.chatbot.addchatbotinstance request
 * 
 * @author top auto create
 * @since 1.0, 2017.10.26
 */
public class CorpChatbotAddchatbotinstanceRequest extends BaseDingTalkRequest<CorpChatbotAddchatbotinstanceResponse> {
	
	

	/** 
	* 机器人id，由钉钉事先分配
	 */
	private String chatbotId;

	/** 
	* 机器人头像(如果为空，默认是机器人安装时的头像)
	 */
	private String iconMediaId;

	/** 
	* 机器人名字(如果为空，默认是机器人安装时的名字)
	 */
	private String name;

	/** 
	* 创建群时返回的openConvsationId
	 */
	private String openConversationId;

	public void setChatbotId(String chatbotId) {
		this.chatbotId = chatbotId;
	}

	public String getChatbotId() {
		return this.chatbotId;
	}

	public void setIconMediaId(String iconMediaId) {
		this.iconMediaId = iconMediaId;
	}

	public String getIconMediaId() {
		return this.iconMediaId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setOpenConversationId(String openConversationId) {
		this.openConversationId = openConversationId;
	}

	public String getOpenConversationId() {
		return this.openConversationId;
	}

	public String getApiMethodName() {
		return "dingtalk.corp.chatbot.addchatbotinstance";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("chatbot_id", this.chatbotId);
		txtParams.put("icon_media_id", this.iconMediaId);
		txtParams.put("name", this.name);
		txtParams.put("open_conversation_id", this.openConversationId);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpChatbotAddchatbotinstanceResponse> getResponseClass() {
		return CorpChatbotAddchatbotinstanceResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(chatbotId, "chatbotId");
		RequestCheckUtils.checkNotEmpty(openConversationId, "openConversationId");
	}
	

}