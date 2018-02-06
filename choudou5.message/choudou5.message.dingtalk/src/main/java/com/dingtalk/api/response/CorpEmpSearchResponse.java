package com.dingtalk.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.dingtalk.api.DingTalkResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.emp.search response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
public class CorpEmpSearchResponse extends DingTalkResponse {

	private static final long serialVersionUID = 7184888743425648436L;

	/** 
	 * 返回结果
	 */
	@ApiField("result")
	private String result;


	public void setResult(String result) {
		this.result = result;
	}
	public String getResult( ) {
		return this.result;
	}
	


}
