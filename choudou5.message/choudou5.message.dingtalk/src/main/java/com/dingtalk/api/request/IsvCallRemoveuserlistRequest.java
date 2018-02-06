package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.IsvCallRemoveuserlistResponse;

/**
 * TOP DingTalk-API: dingtalk.isv.call.removeuserlist request
 * 
 * @author top auto create
 * @since 1.0, 2017.04.06
 */
public class IsvCallRemoveuserlistRequest extends BaseDingTalkRequest<IsvCallRemoveuserlistResponse> {
	
	

	/** 
	* 要删除的员工userid列表
	 */
	private String staffIdList;

	public void setStaffIdList(String staffIdList) {
		this.staffIdList = staffIdList;
	}

	public String getStaffIdList() {
		return this.staffIdList;
	}

	public String getApiMethodName() {
		return "dingtalk.isv.call.removeuserlist";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("staff_id_list", this.staffIdList);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<IsvCallRemoveuserlistResponse> getResponseClass() {
		return IsvCallRemoveuserlistResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(staffIdList, "staffIdList");
		RequestCheckUtils.checkMaxListSize(staffIdList, 20, "staffIdList");
	}
	

}