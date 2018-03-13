/**
 * Project: dubbo.registry.server-1.1.0-SNAPSHOT
 * 
 * File Created at 2010-6-28
 * 
 * Copyright 1999-2010 Alibaba.com Croporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.choudou5.rpc.dubbo.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.choudou5.rpc.dubbo.service.RemoteLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.RegistryService;

/**
 * IbatisDAO
 * 
 * @author william.liangf
 */
public class AbstractService {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractService.class);

    private RegistryServerSync sync;

    public RegistryServerSync getSync() {
        return sync;
    }
    public void setSync(RegistryServerSync sync) {
        this.sync = sync;
    }

    public ConcurrentMap<String, ConcurrentMap<String, Map<Long, URL>>> getRegistryCache(){
        return sync.getRegistryCache();
    }



    // 注册中心信息缓存
    private static Map<String, RegistryConfig> registryConfigCache = new ConcurrentHashMap<>();
    private static Map<String, ReferenceConfig<RemoteLogService>> logCache = new ConcurrentHashMap<>();
    private static ApplicationConfig application = new ApplicationConfig();
    static {
        application.setName("test");
    }


    /**
     * 获取注册中心信息
     *
     * @param address
     *            zk注册地址
     * @param group
     *            dubbo服务所在的组
     * @return
     */
    private static RegistryConfig getRegistryConfig(String address, String group, String version) {
        String key = address + "-" + group + "-" + version;
        RegistryConfig registryConfig = registryConfigCache.get(key);
        if (null == registryConfig) {
            registryConfig = new RegistryConfig();
            registryConfig.setAddress(address);
            registryConfig.setGroup(group);

            registryConfigCache.put(key, registryConfig);
        }
        return registryConfig;
    }


    /**
     * 获取服务的代理对象
     * @param business
     * @param address
     * @param group
     * @return
     */
    private static ReferenceConfig<RemoteLogService> getReferenceConfig(String business, String address, String group, String version) {
        String referenceKey = business+address;
        ReferenceConfig<RemoteLogService> referenceConfig = logCache.get(referenceKey);
        if (null == referenceConfig) {
            referenceConfig = new ReferenceConfig<>();
            referenceConfig.setApplication(application);
            referenceConfig.setRegistry(getRegistryConfig(address, group, version));
            referenceConfig.setInterface(RemoteLogService.class);
            referenceConfig.setVersion(version);
            logCache.put(referenceKey, referenceConfig);
        }
        return referenceConfig;
    }


    /**
     * 调用远程服务
     * @param business
     * @return
     */
    public static boolean invoke(String business, String address, String group, String version) {
        ReferenceConfig<RemoteLogService> reference = getReferenceConfig(business, address, group, version);
        if (null != reference) {
            RemoteLogService callbackService = reference.get();
            if (null != callbackService) {
                callbackService.findList(1);
            }
        }
        return false;
    }

}
