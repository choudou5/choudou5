package com.alibaba.dingtalk.openapi;

import com.alibaba.dingtalk.openapi.user.UserHelper;
import com.dingtalk.open.client.api.model.corp.CorpUserDetail;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Name：用户api
 * @Author：xuhaowen
 * @Date：2018-01-27
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class UserApiTest extends BaseApiTest {

    @Test
    public void mainTest() throws Exception {
        long departmentId = 1L;
        //创建成员
        CorpUserDetail userDetail = createUser(departmentId);

        // 更新成员
        userDetail.setMobile("11177776666");
        UserHelper.updateUser(accessToken, userDetail);
        log("成功更新成员", "成员信息=", userDetail);

        // 获取成员
        CorpUserDetail userDetail11 = UserHelper.getUserInfo(accessToken, userDetail.getUserid());
        log("成功获取成员", "成员userid=", userDetail11.getUserid());

        //删除成员
        deleteUser("1001");

        //批量删除成员
        batchDeleteUser("1001", "1002");

    }

    //创建成员
    private CorpUserDetail createUser(long departmentId) throws Exception {
        CorpUserDetail userDetail = new CorpUserDetail();
        userDetail.setUserid("id_yuhuan");
        userDetail.setName("name_yuhuan");
        userDetail.setEmail("yuhuan@abc.com");
        userDetail.setMobile("18612124567");
        userDetail.setDepartment(new ArrayList());
        userDetail.getDepartment().add(departmentId);

        UserHelper.createUser(accessToken, userDetail);
        log("成功创建成员", "成员信息=", userDetail);
        return userDetail;
    }

    //删除成员
    private void deleteUser(String userId) throws Exception {
        UserHelper.deleteUser(accessToken, userId);
    }

    //批量删除成员
    private void batchDeleteUser(String ... userId) throws Exception {
        UserHelper.batchDeleteUser(accessToken, userId);
        log("成功批量删除成员", "成员列表useridlist=", userId);
    }

}
