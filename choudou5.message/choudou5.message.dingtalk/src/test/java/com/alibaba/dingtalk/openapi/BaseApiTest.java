package com.alibaba.dingtalk.openapi;

import com.alibaba.fastjson.JSON;
import com.com.choudou5.message.dingtalk.service.DingTalkAuthService;
import org.junit.Before;

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
        DingTalkAuthService authService = new AuthServiceTestImpl(corpId, corpSecret);

        // 获取access token
        accessToken = authService.getNoCacheAccessToken();
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
