package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.SmartworkBlackboardListtoptenResponse;

/**
 * TOP DingTalk-API: dingtalk.smartwork.blackboard.listtopten request
 * 
 * @author top auto create
 * @since 1.0, 2018.01.15
 */
public class SmartworkBlackboardListtoptenRequest extends BaseDingTalkRequest<SmartworkBlackboardListtoptenResponse> {
	
	

	/** 
	* 用户id
	 */
	private String userid;

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return this.userid;
	}

	public String getApiMethodName() {
		return "dingtalk.smartwork.blackboard.listtopten";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("userid", this.userid);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<SmartworkBlackboardListtoptenResponse> getResponseClass() {
		return SmartworkBlackboardListtoptenResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(userid, "userid");
	}
	

}