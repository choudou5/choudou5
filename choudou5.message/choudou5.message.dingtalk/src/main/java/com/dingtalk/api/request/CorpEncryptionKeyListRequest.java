package com.dingtalk.api.request;

import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpEncryptionKeyListResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.encryption.key.list request
 * 
 * @author top auto create
 * @since 1.0, 2017.02.17
 */
public class CorpEncryptionKeyListRequest extends BaseDingTalkRequest<CorpEncryptionKeyListResponse> {
	
	

	public String getApiMethodName() {
		return "dingtalk.corp.encryption.key.list";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpEncryptionKeyListResponse> getResponseClass() {
		return CorpEncryptionKeyListResponse.class;
	}

	public void check() throws ApiRuleException {
	}
	

}