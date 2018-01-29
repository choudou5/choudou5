package com.dingtalk.api.request;

import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpInvoiceGettitleResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.invoice.gettitle request
 * 
 * @author top auto create
 * @since 1.0, 2017.07.13
 */
public class CorpInvoiceGettitleRequest extends BaseDingTalkRequest<CorpInvoiceGettitleResponse> {
	
	

	public String getApiMethodName() {
		return "dingtalk.corp.invoice.gettitle";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpInvoiceGettitleResponse> getResponseClass() {
		return CorpInvoiceGettitleResponse.class;
	}

	public void check() throws ApiRuleException {
	}
	

}