package com.alibaba.dingtalk.openapi.message;

public class LinkMessage extends Message {
	
	private String messageUrl;
	private String picUrl;
	private String title;
	private String text;

	public LinkMessage() {
	}

	public LinkMessage(String messageUrl, String picUrl, String title, String text) {
		super();
		this.messageUrl = messageUrl;
		this.picUrl = picUrl;
		this.title = title;
		this.text = text;
	}

	public String getMessageUrl() {
		return messageUrl;
	}

	public void setMessageUrl(String messageUrl) {
		this.messageUrl = messageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String type() {
		return "link";
	}
}
