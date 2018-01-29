package com.alibaba.dingtalk.openapi.role;

import com.alibaba.dingtalk.openapi.Env;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.CorpRoleGetrolegroupRequest;
import com.dingtalk.api.request.CorpRoleListRequest;
import com.dingtalk.api.request.CorpRoleSimplelistRequest;
import com.dingtalk.api.response.CorpRoleGetrolegroupResponse;
import com.dingtalk.api.response.CorpRoleListResponse;
import com.dingtalk.api.response.CorpRoleSimplelistResponse;

import java.util.List;

/**
 * 通讯录-角色相关的接口调用
 */
public class RoleHelper {

    /**
     * 获取角色列表
     * @param accessToken
     * @return
     * @throws Exception
     */
    public static List<CorpRoleListResponse.RoleGroups> getRoleList(String accessToken, long row, long start) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(Env.TOP_HOST);
        CorpRoleListRequest req = new CorpRoleListRequest();
        req.setSize(row);
        req.setOffset(start);
        CorpRoleListResponse rsp = client.execute(req, accessToken);
        return rsp.getResult().getList();
    }

    /**
     * 获取 角色的用户列表
     * @param accessToken
     * @param roleId
     * @param row
     * @param start
     * @return
     * @throws Exception
     */
    public static List<CorpRoleSimplelistResponse.EmpSimpleList> getUserIdListByRoleId(String accessToken, long roleId, long row, long start) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(Env.TOP_HOST);
        CorpRoleSimplelistRequest req = new CorpRoleSimplelistRequest();
        req.setRoleId(roleId);
        req.setSize(row);
        req.setOffset(start);
        CorpRoleSimplelistResponse rsp = client.execute(req, accessToken);
        return rsp.getResult().getList();
    }

    /**
     * 获取角色组信息
     * @param accessToken
     * @param groupId
     * @return
     * @throws Exception
     */
    public static CorpRoleGetrolegroupResponse.OpenRoleGroup getRoleGroup (String accessToken, long groupId) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(Env.TOP_HOST);
        CorpRoleGetrolegroupRequest req = new CorpRoleGetrolegroupRequest();
        req.setGroupId(groupId);
        CorpRoleGetrolegroupResponse rsp = client.execute(req, accessToken);
        return rsp.getResult().getRoleGroup();
    }


}
