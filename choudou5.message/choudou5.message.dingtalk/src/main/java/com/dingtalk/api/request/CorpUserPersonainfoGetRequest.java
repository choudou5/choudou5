package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpUserPersonainfoGetResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.user.personainfo.get request
 * 
 * @author top auto create
 * @since 1.0, 2017.09.21
 */
public class CorpUserPersonainfoGetRequest extends BaseDingTalkRequest<CorpUserPersonainfoGetResponse> {
	
	

	/** 
	* 员工id
	 */
	private String userid;

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return this.userid;
	}

	public String getApiMethodName() {
		return "dingtalk.corp.user.personainfo.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("userid", this.userid);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpUserPersonainfoGetResponse> getResponseClass() {
		return CorpUserPersonainfoGetResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(userid, "userid");
	}
	

}