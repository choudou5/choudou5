package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpExtcontactGetResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.extcontact.get request
 * 
 * @author top auto create
 * @since 1.0, 2018.01.12
 */
public class CorpExtcontactGetRequest extends BaseDingTalkRequest<CorpExtcontactGetResponse> {
	
	

	/** 
	* userId
	 */
	private String userId;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public String getApiMethodName() {
		return "dingtalk.corp.extcontact.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("user_id", this.userId);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpExtcontactGetResponse> getResponseClass() {
		return CorpExtcontactGetResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(userId, "userId");
	}
	

}