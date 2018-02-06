package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpSmartdeviceHasfaceResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.smartdevice.hasface request
 * 
 * @author top auto create
 * @since 1.0, 2018.01.12
 */
public class CorpSmartdeviceHasfaceRequest extends BaseDingTalkRequest<CorpSmartdeviceHasfaceResponse> {
	
	

	/** 
	* 查询用userid列表
	 */
	private String useridList;

	public void setUseridList(String useridList) {
		this.useridList = useridList;
	}

	public String getUseridList() {
		return this.useridList;
	}

	public String getApiMethodName() {
		return "dingtalk.corp.smartdevice.hasface";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("userid_list", this.useridList);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpSmartdeviceHasfaceResponse> getResponseClass() {
		return CorpSmartdeviceHasfaceResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(useridList, "useridList");
		RequestCheckUtils.checkMaxListSize(useridList, 20, "useridList");
	}
	

}