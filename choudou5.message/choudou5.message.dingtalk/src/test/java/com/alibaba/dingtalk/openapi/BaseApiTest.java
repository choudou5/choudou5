package com.alibaba.dingtalk.openapi;

import com.alibaba.dingtalk.openapi.auth.AuthHelper;
import com.alibaba.dingtalk.openapi.department.DepartmentHelper;
import com.alibaba.dingtalk.openapi.media.MediaHelper;
import com.alibaba.dingtalk.openapi.message.LightAppMessageDelivery;
import com.alibaba.dingtalk.openapi.message.MessageHelper;
import com.alibaba.dingtalk.openapi.user.UserHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.client.api.model.corp.*;
import com.dingtalk.open.client.api.model.corp.MessageBody.OABody.Body;
import com.dingtalk.open.client.api.model.corp.MessageBody.OABody.Body.Form;
import com.dingtalk.open.client.api.model.corp.MessageBody.OABody.Body.Rich;
import com.dingtalk.open.client.api.model.corp.MessageBody.OABody.Head;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地测试方法钉钉API
 */
public class BaseApiTest {

    protected static String accessToken;
    protected static String parentDeptId = "1";

    @Before
    public void init() throws OApiException {
        System.out.println("init");
        String corpId = "dinga8f74fdcdbd1342835c2f4657eb6378f";
        String corpSecret = "UFG5lZq_L2VvD2NRUOpNkB5S0ichwCmNLF5cxmD_9gzR4zhO24dlkJ5IgT7xFoub";
        AuthHelper.setAuthService(new AuthServiceImpl(corpId, corpSecret));

        // 获取access token
        accessToken = AuthHelper.getAccessToken();
        log("成功获取access token: ", accessToken);
    }

    protected void log(Object... msgs) {
        StringBuilder sb = new StringBuilder();
        for (Object o : msgs) {
            if (o != null) {
                sb.append(JSON.toJSONString(o));
            }
        }
        System.out.println(sb.toString());
    }
}
