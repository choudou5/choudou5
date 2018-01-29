package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpConversationCorpconversionGetconversationResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.conversation.corpconversion.getconversation request
 * 
 * @author top auto create
 * @since 1.0, 2017.03.06
 */
public class CorpConversationCorpconversionGetconversationRequest extends BaseDingTalkRequest<CorpConversationCorpconversionGetconversationResponse> {
	
	

	/** 
	* 群组ID
	 */
	private String openConversationId;

	public void setOpenConversationId(String openConversationId) {
		this.openConversationId = openConversationId;
	}

	public String getOpenConversationId() {
		return this.openConversationId;
	}

	public String getApiMethodName() {
		return "dingtalk.corp.conversation.corpconversion.getconversation";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("open_conversation_id", this.openConversationId);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpConversationCorpconversionGetconversationResponse> getResponseClass() {
		return CorpConversationCorpconversionGetconversationResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(openConversationId, "openConversationId");
	}
	

}