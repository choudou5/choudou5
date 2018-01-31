package com.choudou5.message.sms.model;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @Name：短信查询
 * @Author：xuhaowen
 * @Date：2018-01-31
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class SmsQueryReq implements Serializable {

    /**
     * 发送流水号,从调用发送接口返回值中获取
     */
    private String bizId;
    /**
     * 	短信发送日期（往前偏移时间）   格式yyyyMMdd,支持最近30天记录查询
     */
    private String sendDate;
    /**
     * 页大小Max=50
     */
    private Long pageSize;
    /**
     * 当前页码
     */
    private Long currentPage;

    public SmsQueryReq(String sendDate, Long pageSize, Long currentPage) {
        this.sendDate = sendDate;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public SmsQueryReq(Long pageSize, Long currentPage) {
        this.sendDate = DateUtil.format(DateUtil.yesterday(), DatePattern.PURE_DATE_FORMAT);
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public String getBizId() {
        return bizId;
    }

    public String getSendDate() {
        return sendDate;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getCurrentPage() {
        return currentPage;
    }
}
