package com.alibaba.dingtalk.openapi.department;

import com.dingtalk.open.client.api.model.corp.CorpUserDetail;
import com.dingtalk.open.client.api.model.corp.Department;

import java.util.List;

/**
 * @Name：部门扩展类
 * @Author：xuhaowen
 * @Date：2018-01-28
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class DepartmentExt extends Department {

    private List<CorpUserDetail> userList;

    public List<CorpUserDetail> getUserList() {
        return userList;
    }
    public void addUserList(List<CorpUserDetail> userList) {
        if(this.userList == null){
            this.userList = userList;
        }else{
            this.userList.addAll(userList);
        }
    }


}
