package com.alibaba.dingtalk.openapi.role;

import com.alibaba.dingtalk.openapi.Env;
import com.choudou5.base.util.StrUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;

import java.util.Arrays;
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


    /**
     * 为员工增加角色信息
     * @param accessToken
     * @param roleId
     * @param userIdList
     * @return
     * @throws Exception
     */
    public static boolean addRoleForEmployee(String accessToken, String roleId, List<String> userIdList) throws Exception {
        return addRoleForEmployee(accessToken, Arrays.asList(roleId), userIdList);
    }

    /**
     * 批量为员工增加角色信息
     * @param accessToken
     * @param roleIdList
     * @param userIdList
     * @return
     * @throws Exception
     */
    public static boolean addRoleForEmployee(String accessToken, List<String> roleIdList, List<String> userIdList) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(Env.TOP_HOST);
        CorpRoleAddrolesforempsRequest req = new CorpRoleAddrolesforempsRequest();
        req.setRolelidList(StrUtil.join(",", roleIdList));
        req.setUseridList(StrUtil.join(",", userIdList));
        CorpRoleAddrolesforempsResponse rsp = client.execute(req, accessToken);
        return rsp.getResult().getSuccess();
    }

    /**
     * 批量删除员工的 角色信息
     * @param accessToken
     * @param roleId
     * @param userIdList
     * @return
     * @throws Exception
     */
    public static boolean removeRoleForEmployee(String accessToken, String roleId, List<String> userIdList) throws Exception {
        return removeRoleForEmployee(accessToken, Arrays.asList(roleId), userIdList);
    }

    /**
     * 批量删除员工的 角色信息
     * @param accessToken
     * @param roleIdList
     * @param userIdList
     * @return
     * @throws Exception
     */
    public static boolean removeRoleForEmployee(String accessToken, List<String> roleIdList, List<String> userIdList) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(Env.TOP_HOST);
        CorpRoleRemoverolesforempsRequest req = new CorpRoleRemoverolesforempsRequest();
        req.setRoleidList(StrUtil.join(",", roleIdList));
        req.setUseridList(StrUtil.join(",", userIdList));
        CorpRoleRemoverolesforempsResponse rsp = client.execute(req, accessToken);
        return rsp.getResult().getSuccess();
    }
}
