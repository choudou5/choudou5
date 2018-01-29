package com.dingtalk.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.dingtalk.api.DingTalkResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.ext.listlabelgroups response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
public class CorpExtListlabelgroupsResponse extends DingTalkResponse {

	private static final long serialVersionUID = 5791295563497388513L;

	/** 
	 * result
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
