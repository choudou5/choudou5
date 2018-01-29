package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpRoleRemoverolesforempsResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.role.removerolesforemps request
 * 
 * @author top auto create
 * @since 1.0, 2017.11.16
 */
public class CorpRoleRemoverolesforempsRequest extends BaseDingTalkRequest<CorpRoleRemoverolesforempsResponse> {
	
	

	/** 
	* 角色标签id
	 */
	private String roleidList;

	/** 
	* 用户userId
	 */
	private String useridList;

	public void setRoleidList(String roleidList) {
		this.roleidList = roleidList;
	}

	public String getRoleidList() {
		return this.roleidList;
	}

	public void setUseridList(String useridList) {
		this.useridList = useridList;
	}

	public String getUseridList() {
		return this.useridList;
	}

	public String getApiMethodName() {
		return "dingtalk.corp.role.removerolesforemps";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("roleid_list", this.roleidList);
		txtParams.put("userid_list", this.useridList);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpRoleRemoverolesforempsResponse> getResponseClass() {
		return CorpRoleRemoverolesforempsResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(roleidList, "roleidList");
		RequestCheckUtils.checkMaxListSize(roleidList, 20, "roleidList");
		RequestCheckUtils.checkNotEmpty(useridList, "useridList");
		RequestCheckUtils.checkMaxListSize(useridList, 100, "useridList");
	}
	

}