package com.alibaba.dingtalk.openapi.user;

import com.alibaba.dingtalk.openapi.OApiException;
import com.alibaba.dingtalk.openapi.utils.HttpHelper;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.CorpUserBaseInfo;
import com.dingtalk.open.client.api.model.corp.CorpUserDetail;
import com.dingtalk.open.client.api.model.corp.CorpUserDetailList;
import com.dingtalk.open.client.api.model.corp.CorpUserList;
import com.dingtalk.open.client.api.service.corp.CorpUserService;

import java.util.Arrays;
import java.util.List;

/**
 * 通讯录成员相关的接口调用
 */
public class UserHelper {


    /**
     * 根据免登授权码查询免登用户
     * @param accessToken
     * @param userId
     * @return
     * @throws Exception
     */
    public static CorpUserDetail getUserInfo(String accessToken, String userId) throws Exception {
        String url = "/user/get?access_token=" + accessToken+"&userid="+userId;
        JSONObject response = HttpHelper.httpGet(url);
        return response.toJavaObject(CorpUserDetail.class);

    }

    /**
     * 创建企业成员
     * <p>
     * https://open-doc.dingtalk.com/docs/doc.htm?treeId=385&articleId=106816&docType=1#s1
     */
    public static String createUser(String accessToken, CorpUserDetail userDetail) throws Exception {
//        JSONObject js = (JSONObject) JSONObject.parse(userDetail.getOrderInDepts());
//        Map<Long, Long> orderInDepts = toHashMap(js);
        String url = "/user/create?access_token=" + accessToken;
        JSONObject response = HttpHelper.httpPost(url, userDetail);
        // 员工唯一标识ID
        return response.getString("userid");
    }


    /**
     * 更新成员
     * <p>
     * https://open-doc.dingtalk.com/docs/doc.htm?treeId=385&articleId=106816&docType=1#s2
     */
    public static boolean updateUser(String accessToken, CorpUserDetail userDetail) throws Exception {
        String url = "/user/update?access_token=" + accessToken;
        JSONObject response = HttpHelper.httpPost(url, userDetail);
        return response.getInteger("errcode").equals(0);
    }


    /**
     * 删除成员
     */
    public static boolean deleteUser(String accessToken, String userId) throws Exception {
        String url = "/user/delete?access_token=" + accessToken+"&userid="+userId;
        JSONObject response = HttpHelper.httpGet(url);
        return response.getInteger("errcode").equals(0);
    }

    //批量删除成员
    public static boolean batchDeleteUser(String accessToken, List<String> userIdlist)
            throws Exception {
        String url = "/user/batchdelete?access_token=" + accessToken;
        JSONObject response = HttpHelper.httpPost(url, userIdlist);
        return response.getInteger("errcode").equals(0);
    }

    //批量删除成员
    public static boolean batchDeleteUser(String accessToken, String ... userIds)
            throws Exception {
        String url = "/user/batchdelete?access_token=" + accessToken;
        JSONObject response = HttpHelper.httpPost(url, Arrays.asList(userIds));
        return response.getInteger("errcode").equals(0);

    }

    //获取部门成员
    public static CorpUserList getDepartmentUser(String accessToken, long departmentId, Long offset, Integer size)
            throws Exception {
        String url = "/user/simplelist?access_token=" + accessToken+"&department_id="+departmentId+"&offset="+offset+"&size="+size;
        JSONObject response = HttpHelper.httpGet(url);
        return response.toJavaObject(CorpUserList.class);
    }


    //获取部门成员（详情）
    public static CorpUserDetailList getUserDetails(String accessToken, long departmentId, Long offset, Integer size)
            throws Exception {
        String url = "/user/list?access_token=" + accessToken+"&department_id="+departmentId+"&offset="+offset+"&size="+size;
        JSONObject response = HttpHelper.httpGet(url);
        return response.toJavaObject(CorpUserDetailList.class);
    }

    public static List<CorpUserBaseInfo> getAdminList(String accessToken)
            throws Exception {
        String url = "/user/get_admin?access_token=" + accessToken;
        JSONObject response = HttpHelper.httpGet(url);
        return response.getJSONArray("adminList").toJavaList(CorpUserBaseInfo.class);
    }

    /**
     * 根据unionid获取成员的userid
     * @param accessToken
     * @param unionId 钉钉开放平台账号范围内的唯一标识
     * @return
     * @throws Exception
     */
    public static String getUserIdByUnionid(String accessToken, String unionId)
            throws Exception {
        String url = "/user/getUseridByUnionid?access_token=" + accessToken+"&unionid=";
        JSONObject response = HttpHelper.httpGet(url);
        return response.getString("userid");
    }

    /**
     * 管理后台免登时通过CODE换取微应用管理员的身份信息
     * @param ssoToken
     * @param code
     * @return
     * @throws OApiException
     */
    public static JSONObject getAgentUserInfo(String ssoToken, String code) throws OApiException {
        String url = "/sso/getuserinfo?" + "access_token=" + ssoToken + "&code=" + code;
        JSONObject response = HttpHelper.httpGet(url);
        return response;
    }
}
