package com.dingtalk.api.model.corp;

import com.dingtalk.open.client.api.model.corp.CorpUserDetail;

import java.util.List;

/**
 * @Name：用户详情
 * @Author：xuhaowende
 * @Date：2018-02-19
 */
public class CorpUserDetailExt extends CorpUserDetail {

    private String unionid;

    private List<CorpRole> roles;

    public String getUnionid() {
        return unionid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<CorpRole> getRoles() {
        return roles;
    }
    public void setRoles(List<CorpRole> roles) {
        this.roles = roles;
    }
}
