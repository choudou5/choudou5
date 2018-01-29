package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpSmartdeviceReceptionistPushinfoResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.smartdevice.receptionist.pushinfo request
 * 
 * @author top auto create
 * @since 1.0, 2018.01.12
 */
public class CorpSmartdeviceReceptionistPushinfoRequest extends BaseDingTalkRequest<CorpSmartdeviceReceptionistPushinfoResponse> {
	
	

	/** 
	* 展示模板需要的变量数据
	 */
	private String descContent;

	/** 
	* 智能前台信息展示模板ID，需要向智能硬件团队申请
	 */
	private String descTemplate;

	/** 
	* 微应用agentID
	 */
	private Long microappAgentId;

	public void setDescContent(String descContent) {
		this.descContent = descContent;
	}

	public String getDescContent() {
		return this.descContent;
	}

	public void setDescTemplate(String descTemplate) {
		this.descTemplate = descTemplate;
	}

	public String getDescTemplate() {
		return this.descTemplate;
	}

	public void setMicroappAgentId(Long microappAgentId) {
		this.microappAgentId = microappAgentId;
	}

	public Long getMicroappAgentId() {
		return this.microappAgentId;
	}

	public String getApiMethodName() {
		return "dingtalk.corp.smartdevice.receptionist.pushinfo";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("desc_content", this.descContent);
		txtParams.put("desc_template", this.descTemplate);
		txtParams.put("microapp_agent_id", this.microappAgentId);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpSmartdeviceReceptionistPushinfoResponse> getResponseClass() {
		return CorpSmartdeviceReceptionistPushinfoResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(descContent, "descContent");
		RequestCheckUtils.checkNotEmpty(descTemplate, "descTemplate");
		RequestCheckUtils.checkNotEmpty(microappAgentId, "microappAgentId");
	}
	

}