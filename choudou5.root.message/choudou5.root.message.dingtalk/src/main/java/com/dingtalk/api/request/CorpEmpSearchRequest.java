package com.dingtalk.api.request;

import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpEmpSearchResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.emp.search request
 * 
 * @author top auto create
 * @since 1.0, 2016.11.17
 */
public class CorpEmpSearchRequest extends BaseDingTalkRequest<CorpEmpSearchResponse> {
	
	

	/** 
	* 搜索关键字
	 */
	private String keyword;

	/** 
	* 偏移量
	 */
	private Long offset;

	/** 
	* 请求数量
	 */
	private Long size;

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return this.keyword;
	}

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
		return "dingtalk.corp.emp.search";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("keyword", this.keyword);
		txtParams.put("offset", this.offset);
		txtParams.put("size", this.size);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpEmpSearchResponse> getResponseClass() {
		return CorpEmpSearchResponse.class;
	}

	public void check() throws ApiRuleException {
	}
	

}