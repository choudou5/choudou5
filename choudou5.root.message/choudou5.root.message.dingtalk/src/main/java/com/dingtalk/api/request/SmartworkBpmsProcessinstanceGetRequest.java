package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.SmartworkBpmsProcessinstanceGetResponse;

/**
 * TOP DingTalk-API: dingtalk.smartwork.bpms.processinstance.get request
 * 
 * @author top auto create
 * @since 1.0, 2017.12.20
 */
public class SmartworkBpmsProcessinstanceGetRequest extends BaseDingTalkRequest<SmartworkBpmsProcessinstanceGetResponse> {
	
	

	/** 
	* 审批实例id
	 */
	private String processInstanceId;

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public String getApiMethodName() {
		return "dingtalk.smartwork.bpms.processinstance.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("process_instance_id", this.processInstanceId);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<SmartworkBpmsProcessinstanceGetResponse> getResponseClass() {
		return SmartworkBpmsProcessinstanceGetResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(processInstanceId, "processInstanceId");
	}
	

}