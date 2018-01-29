package com.alibaba.dingtalk.openapi.department;

import com.alibaba.dingtalk.openapi.utils.HttpHelper;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.client.api.model.corp.Department;
import com.dingtalk.open.client.api.model.corp.DepartmentDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门相关API
 * link: https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.blDShl&treeId=385&articleId=106817&docType=1#s2
 */
public class DepartmentHelper {

    /**
     *  创建部门
     */
    public static String createDepartment(String accessToken, String name,
                                          String parentId, String order, boolean createDeptGroup) throws Exception {
        String url = "/department/create?access_token=" + accessToken;
        Map param = new HashMap<>();
        param.put("name", name);
        param.put("parentId", parentId);
        param.put("order", order);
        param.put("createDeptGroup", createDeptGroup);
        JSONObject response = HttpHelper.httpPost(url, param);
        return response.getString("id");
    }


    /**
     *  部门详情
     */
    public static DepartmentDetail getDeptDetail(String accessToken, String id) throws Exception {
        String url = "/department/get?access_token=" + accessToken + "&id=" + id;
        JSONObject response = HttpHelper.httpGet(url);
        return response.toJavaObject(DepartmentDetail.class);
    }


    /**
     * 获取部门列表
     */
    public static List<DepartmentExt> listDepartments(String accessToken, String parentDeptId)
            throws Exception {
        String url = "/department/list?access_token=" + accessToken+"&id="+parentDeptId;
        JSONObject response = HttpHelper.httpGet(url);
        return response.getJSONArray("department").toJavaList(DepartmentExt.class);
    }

    /**
     * 获取部门父节点数组
     */
    public static String[] getParentIds(String accessToken, String id)
            throws Exception {
        String url = "/department/list_parent_depts_by_dept?access_token=" + accessToken+"&id="+id;
        JSONObject response = HttpHelper.httpGet(url);
        return response.getJSONArray("parentIds").toArray(new String[]{});
    }

    /**
     * 删除部门
     */
    public static void deleteDepartment(String accessToken, Long id) throws Exception {
        String url = "/department/delete?access_token=" + accessToken+"&id="+id;
        JSONObject response = HttpHelper.httpGet(url);
        System.out.println(response.toJSONString());
    }

    /**
     * 更新部门
     */
    public static void updateDepartment(String accessToken, long id, String name,
                                        String parentId, String order, Boolean createDeptGroup,
                                        boolean autoAddUser, String deptManagerUseridList, boolean deptHiding, String deptPerimits,
                                        String userPerimits, Boolean outerDept, String outerPermitDepts,
                                        String outerPermitUsers, String orgDeptOwner) throws Exception {
        String url = "/department/update?access_token=" + accessToken;
        Map param = new HashMap<>();
        param.put("name", name);
        param.put("parentId", parentId);
        param.put("order", order);
        param.put("createDeptGroup", createDeptGroup);
        param.put("autoAddUser", autoAddUser);
        param.put("deptManagerUseridList", deptManagerUseridList);
        param.put("deptHiding", deptHiding);
        param.put("deptPerimits", deptPerimits);
        param.put("userPerimits", userPerimits);
        param.put("outerDept", outerDept);
        param.put("outerPermitDepts", outerPermitDepts);
        param.put("outerPermitUsers", outerPermitUsers);
        param.put("orgDeptOwner", orgDeptOwner);
        JSONObject response = HttpHelper.httpPost(url, param);
        System.out.println(response.toJSONString());
    }

}
