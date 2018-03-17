package com.choudou5.base.bean;

import java.io.Serializable;

/**
 * @Name：响应数据
 * @Author：xuhaowen
 * @Date：2018-03-17
 */
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 1421937580611606987L;

    private Integer statusCode;

    private String message = "";

    private T data;

    public ResponseData() {
        super();
    }

    public ResponseData(T data) {
        super();
        this.data = data;
        this.statusCode = 200;
    }

    public ResponseData(Integer errorCode, String message) {
        super();
        this.statusCode = errorCode;
        this.message = message;
    }

    public ResponseData(Integer errorCode, String message, T data) {
        super();
        this.statusCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}