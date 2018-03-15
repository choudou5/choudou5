package com.choudou5.base.helper;

import java.util.Map;

/**
 * @Name：Http助手 说明
 * @Author：xuhaowen
 * @Date：2018-03-15
 */
public interface HttpHelper {

    String get(String url);

    String post(String url, Map params);

}
