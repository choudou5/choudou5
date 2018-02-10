package com.alibaba.dingtalk.openapi;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.dingtalk.openapi.role.RoleHelper;
import com.choudou5.base.util.CollUtil;
import com.dingtalk.api.response.CorpRoleListResponse;
import com.dingtalk.api.response.CorpRoleSimplelistResponse;
import org.junit.Test;

import java.util.List;

/**
 * @Name：角色 api
 * @Author：xuhaowen
 * @Date：2018-01-27
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class RoleApiTest extends BaseApiTest {

    @Test
    public void mainTest() throws Exception {

        List<CorpRoleListResponse.RoleGroups> list = RoleHelper.getRoleList(accessToken, 100, 0);
        if(CollUtil.isNotEmpty(list)){
            for (CorpRoleListResponse.RoleGroups roleGroups : list) {
                log("角色组："+roleGroups.getGroupName());
                List<CorpRoleListResponse.Roles> roles = roleGroups.getRoles();
                if(CollUtil.isNotEmpty(roles)){
                    for (CorpRoleListResponse.Roles role : roles) {
                        log(role.getId()+" , "+role.getRoleName(), " - 用户列表：");
                        List<CorpRoleSimplelistResponse.EmpSimpleList> userList = RoleHelper.getUserIdListByRoleId(accessToken, role.getId(), 100, 0);
                        if(CollectionUtil.isNotEmpty(userList)){
                            for (CorpRoleSimplelistResponse.EmpSimpleList empSimpleList : userList) {
                                log(empSimpleList.getUserid());
                            }
                        }
                    }
                }
            }
        }

//        long groupId = 1L;
//        CorpRoleGetrolegroupResponse.OpenRoleGroup group = RoleHelper.getRoleGroup(accessToken, groupId);
//        log("角色组信息：", group);
    }

}
