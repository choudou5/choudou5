package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpRoleSimplelistResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.role.simplelist request
 * 
 * @author top auto create
 * @since 1.0, 2017.04.27
 */
public class CorpRoleSimplelistRequest extends BaseDingTalkRequest<CorpRoleSimplelistResponse> {
	
	

	/** 
	* 分页偏移
	 */
	private Long offset;

	/** 
	* 角色ID
	 */
	private Long roleId;

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

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getSize() {
		return this.size;
	}

	public String getApiMethodName() {
		return "dingtalk.corp.role.simplelist";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("offset", this.offset);
		txtParams.put("role_id", this.roleId);
		txtParams.put("size", this.size);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpRoleSimplelistResponse> getResponseClass() {
		return CorpRoleSimplelistResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(roleId, "roleId");
	}
	

}