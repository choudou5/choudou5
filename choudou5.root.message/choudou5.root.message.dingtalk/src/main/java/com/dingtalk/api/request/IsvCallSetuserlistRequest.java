package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.IsvCallSetuserlistResponse;

/**
 * TOP DingTalk-API: dingtalk.isv.call.setuserlist request
 * 
 * @author top auto create
 * @since 1.0, 2017.04.06
 */
public class IsvCallSetuserlistRequest extends BaseDingTalkRequest<IsvCallSetuserlistResponse> {
	
	

	/** 
	* 套件所所属企业免费电话主叫人员工号列表
	 */
	private String staffIdList;

	public void setStaffIdList(String staffIdList) {
		this.staffIdList = staffIdList;
	}

	public String getStaffIdList() {
		return this.staffIdList;
	}

	public String getApiMethodName() {
		return "dingtalk.isv.call.setuserlist";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("staff_id_list", this.staffIdList);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<IsvCallSetuserlistResponse> getResponseClass() {
		return IsvCallSetuserlistResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(staffIdList, "staffIdList");
		RequestCheckUtils.checkMaxListSize(staffIdList, 20, "staffIdList");
	}
	

}