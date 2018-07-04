/**
 * Project: dubbo.registry-1.1.0-SNAPSHOT
 * 
 * File Created at 2010-4-15
 * $Id: ProviderService.java 182143 2012-06-27 03:25:50Z tony.chenl $
 * 
 * Copyright 2008 Alibaba.com Croporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.choudou5.rpc.dubbo.service;

import com.alibaba.dubbo.common.URL;
import com.choudou5.rpc.dubbo.domain.ProviderEntity;

import java.util.List;


/**
 * ProviderService
 * 
 * @author william.liangf
 */
public interface ProviderService {

    ProviderEntity findProvider(Long id);
    
    List<String> findServices();
    
    List<String> findAddresses();
    
    List<String> findAddressesByApplication(String application);
    
    List<String> findAddressesByService(String serviceName);

    List<URL> findURLByService(String serviceName);

    List<String> findApplicationsByServiceName(String serviceName);

    List<ProviderEntity> findByService(String serviceName);

    List<ProviderEntity> findAll();

    List<ProviderEntity> findByAddress(String providerAddress);

    List<String> findServicesByAddress(String providerAddress);

    List<String> findApplications();
    
    List<ProviderEntity> findByApplication(String application);
    
    List<String> findServicesByApplication(String application);
    
    List<String> findMethodsByService(String serviceName);

    ProviderEntity findByServiceAndAddress(String service, String address);
	
}