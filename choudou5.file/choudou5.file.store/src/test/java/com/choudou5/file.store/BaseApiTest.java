package com.choudou5.file.store;


import com.choudou5.base.util.JsonUtil;

public class BaseApiTest {

    protected void log(Object... msgs) {
        StringBuilder sb = new StringBuilder();
        for (Object o : msgs) {
            if (o != null) {
                sb.append(JsonUtil.toStr(o));
            }
        }
        System.out.println(sb.toString());
    }
}
