package com.choudou5.file.store;

import cn.hutool.json.JSONUtil;

public class BaseApiTest {

    protected void log(Object... msgs) {
        StringBuilder sb = new StringBuilder();
        for (Object o : msgs) {
            if (o != null) {
                sb.append(JSONUtil.toJsonStr(o));
            }
        }
        System.out.println(sb.toString());
    }
}
