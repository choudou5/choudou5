package com.dingtalk.api.request;

import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpExtListResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.ext.list request
 * 
 * @author top auto create
 * @since 1.0, 2017.08.17
 */
public class CorpExtListRequest extends BaseDingTalkRequest<CorpExtListResponse> {
	
	

	/** 
	* 偏移位置
	 */
	private Long offset;

	/** 
	* 分页大小, 最大100
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
		return "dingtalk.corp.ext.list";
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

	public Class<CorpExtListResponse> getResponseClass() {
		return CorpExtListResponse.class;
	}

	public void check() throws ApiRuleException {
	}
	

}