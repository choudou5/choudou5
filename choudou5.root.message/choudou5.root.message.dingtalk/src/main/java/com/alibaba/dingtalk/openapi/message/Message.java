package com.alibaba.dingtalk.openapi.message;


import java.io.Serializable;

public abstract class Message implements Serializable{
	public abstract String type();
}
