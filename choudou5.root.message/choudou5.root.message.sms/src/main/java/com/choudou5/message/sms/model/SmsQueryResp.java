package com.choudou5.message.sms.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Name：短信查询响应
 * @Author：xuhaowen
 * @Date：2018-01-31
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class SmsQueryResp extends SmsResp implements Serializable{

    private Long totalCount;
    private Long totalPage;
    private List<SmsDetailDto> smsSendDetailDTOs;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List<SmsDetailDto> getSmsSendDetailDTOs() {
        return smsSendDetailDTOs;
    }

    public void setSmsSendDetailDTOs(List<SmsDetailDto> smsSendDetailDTOs) {
        this.smsSendDetailDTOs = smsSendDetailDTOs;
    }
}
