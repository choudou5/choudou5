package com.dingtalk.api.request;

import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpRoleListResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.role.list request
 * 
 * @author top auto create
 * @since 1.0, 2017.04.27
 */
public class CorpRoleListRequest extends BaseDingTalkRequest<CorpRoleListResponse> {
	
	

	/** 
	* 分页偏移
	 */
	private Long offset;

	/** 
	* 分页大小
	 */
	private Long size;

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Long getOffset() {
		return this.offset;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getSize() {
		return this.size;
	}

	public String getApiMethodName() {
		return "dingtalk.corp.role.list";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("offset", this.offset);
		txtParams.put("size", this.size);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpRoleListResponse> getResponseClass() {
		return CorpRoleListResponse.class;
	}

	public void check() throws ApiRuleException {
	}
	

}