package com.choudou5.log.admin.service;

import java.util.Map;

/**
 * Desc：日志接口
 * Author：xuhaowen
 */
public interface ILogAdminService {

    void setLogLevel(String name, String level);

    Map getLogHi(long time);

    Map getLogListInfo();

}
