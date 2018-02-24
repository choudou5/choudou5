package com.dingtalk.api.model.corp;

import com.taobao.api.internal.mapping.ApiField;

import java.io.Serializable;

/**
 * @Name：角色
 * @Author：xuhaowende
 * @Date：2018-02-22
 */
public class CorpRole implements Serializable {

    private Long id;
    private String name;
    private String groupName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
