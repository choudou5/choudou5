package com.choudou5.rpc.dubbo.service;

import java.util.List;

/**
 * @Name：RemoteLogService 说明
 * @Author：xuhaowen
 * @Date：2018-03-14
 */
public interface RemoteLogService {

    List<String> findList(int top);
}
