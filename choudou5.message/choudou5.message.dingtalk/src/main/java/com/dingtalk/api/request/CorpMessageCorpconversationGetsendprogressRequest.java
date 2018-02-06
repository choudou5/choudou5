package com.dingtalk.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.dingtalk.api.BaseDingTalkRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.dingtalk.api.response.CorpMessageCorpconversationGetsendprogressResponse;

/**
 * TOP DingTalk-API: dingtalk.corp.message.corpconversation.getsendprogress request
 * 
 * @author top auto create
 * @since 1.0, 2018.01.10
 */
public class CorpMessageCorpconversationGetsendprogressRequest extends BaseDingTalkRequest<CorpMessageCorpconversationGetsendprogressResponse> {
	
	

	/** 
	* 发送消息时使用的微应用的id
	 */
	private Long agentId;

	/** 
	* 发送消息时钉钉返回的任务id
	 */
	private Long taskId;

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getAgentId() {
		return this.agentId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getTaskId() {
		return this.taskId;
	}

	public String getApiMethodName() {
		return "dingtalk.corp.message.corpconversation.getsendprogress";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("agent_id", this.agentId);
		txtParams.put("task_id", this.taskId);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<CorpMessageCorpconversationGetsendprogressResponse> getResponseClass() {
		return CorpMessageCorpconversationGetsendprogressResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(agentId, "agentId");
		RequestCheckUtils.checkNotEmpty(taskId, "taskId");
	}
	

}