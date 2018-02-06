package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpExtcontactListResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.extcontact.list request
 * 
 * @author top auto create
 * @since 1.0, 2018.01.12
 */
public class CorpExtcontactListRequest extends BaseDingTalkRequest<CorpExtcontactListResponse> {
	
	

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
		return "dingtalk.corp.extcontact.list";
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

	public Class<CorpExtcontactListResponse> getResponseClass() {
		return CorpExtcontactListResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkMaxValue(size, 100L, "size");
	}
	

}