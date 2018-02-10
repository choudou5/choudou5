package com.alibaba.dingtalk.openapi;

import com.alibaba.dingtalk.openapi.department.DepartmentExt;
import com.alibaba.dingtalk.openapi.department.DepartmentHelper;
import com.alibaba.dingtalk.openapi.user.UserHelper;
import com.alibaba.fastjson.JSON;
import com.dingtalk.open.client.api.model.corp.CorpUserDetailList;
import com.dingtalk.open.client.api.model.corp.CorpUserList;
import com.dingtalk.open.client.api.model.corp.DepartmentDetail;
import org.junit.Test;

import java.util.List;

/**
 * @Name：部门Api
 * @Author：xuhaowen
 * @Date：2018-01-27
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class DepartmentApiTest extends BaseApiTest {

    @Test
    public void mainTest() throws Exception {
        //创建部门
        long departmentId = 111;

        //部门详情
        getDeptDetail(departmentId+"");

        // 获取部门列表
        List<DepartmentExt> list = DepartmentHelper.listDepartments(accessToken, parentDeptId);
        log("成功获取部门列表", list);
        //更新部门
        updateDepartment(departmentId);

        // 获取部门成员
        CorpUserList userList = UserHelper.getDepartmentUser(accessToken, departmentId, null, null);
        log("成功获取部门成员", "部门成员user=", userList.getUserlist());

        // 获取部门成员（详情）
        CorpUserDetailList userList2 = UserHelper.getUserDetails(accessToken, departmentId, null, null);
        log("成功获取部门成员详情", "部门成员详情user=", userList2.getUserlist());

        //删除部门
//        createDepartment(departmentId);
    }

    //创建部门
    private long createDepartment() throws Exception {
        String name = "TestDept.34";
        String order = "1";
        boolean createDeptGroup = true;
        long departmentId = Long.parseLong(DepartmentHelper.createDepartment(accessToken, name, parentDeptId, order, createDeptGroup));
        log("成功创建部门", name, " 部门id=", departmentId);
        return departmentId;
    }

    //更新部门
    private void updateDepartment(long departmentId) throws Exception {
        // 更新部门
        String name = "hahahaha";
        boolean autoAddUser = true;
        String deptManagerUseridList = null;
        boolean deptHiding = false;
        String deptPerimits = "aa|qq";
        String order = "1";
        boolean createDeptGroup = true;
        DepartmentHelper.updateDepartment(accessToken, departmentId, name, parentDeptId, order, createDeptGroup,
                autoAddUser, deptManagerUseridList, deptHiding, deptPerimits, null,
                null, null, null, null);
        log("成功更新部门", " 部门id=", departmentId);
    }

    //删除部门
    private void deleteDepartment(long departmentId) throws Exception {
        DepartmentHelper.deleteDepartment(accessToken, departmentId);
        log("成功删除部门", " 部门id=", departmentId);
    }

    //部门详情
    private void getDeptDetail(String departmentId) throws Exception {
        DepartmentDetail detail = DepartmentHelper.getDeptDetail(accessToken, departmentId);
        log("部门详情:", JSON.toJSONString(detail));
    }

    //部门列表
    @Test
    public void getDepartmentList() throws Exception {
        List<DepartmentExt> departments = DepartmentHelper.listDepartments(accessToken, parentDeptId);
        log("部门总数:", departments.size());
        for (DepartmentExt department : departments) {
            log("部门信息:" + JSON.toJSONString(department));
            long offset = 0;
            int size = 50;
            CorpUserDetailList corpUserList = new CorpUserDetailList();
            while (true) {
                corpUserList = UserHelper.getUserDetails(accessToken, department.getId(), offset, size);
                department.addUserList(corpUserList.getUserlist());
                if (Boolean.TRUE.equals(corpUserList.isHasMore())) {
                    offset += size;
                } else {
                    break;
                }
            }
        }
        System.out.println(JSON.toJSONString(departments));
    }

}
