/*
 * Copyright 1999-2101 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.choudou5.rpc.dubbo.service.impl;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.choudou5.rpc.dubbo.service.OverrideService;
import com.choudou5.rpc.dubbo.domain.Override;
import com.choudou5.rpc.dubbo.util.Pair;
import com.choudou5.rpc.dubbo.util.SyncUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IbatisOverrideDAO.java
 * @author tony.chenl
 */
public class OverrideServiceImpl extends AbstractService implements OverrideService {

    private Map<Long, URL> findOverrideUrl(String service, String address, String application) {
        Map<String, String> filter = new HashMap<String, String>();
        filter.put(Constants.CATEGORY_KEY, Constants.CONFIGURATORS_CATEGORY);
        if (service != null && service.length() > 0) {
        	filter.put(SyncUtils.SERVICE_FILTER_KEY, service);
        }
        if (address != null && address.length() > 0) {
        	filter.put(SyncUtils.ADDRESS_FILTER_KEY, address);
        }
        if (application != null && application.length() > 0) {
        	filter.put(Constants.APPLICATION_KEY, application);
        }
        return SyncUtils.filterFromCategory(getRegistryCache(), filter);
    }

    public List<Override> findByAddress(String address) {
        return  SyncUtils.url2OverrideList(findOverrideUrl(null, address, null));
    }

    public List<Override> findByServiceAndAddress(String service, String address) {
        return  SyncUtils.url2OverrideList(findOverrideUrl(service, address, null));
    }
    
    public List<Override> findByApplication(String application) {
        return SyncUtils.url2OverrideList(findOverrideUrl(null, null, application));
    }

    public List<Override> findByService(String service) {
        return  SyncUtils.url2OverrideList(findOverrideUrl(service, null, null));
    }

    public List<Override> findByServiceAndApplication(String service, String application) {
        return  SyncUtils.url2OverrideList(findOverrideUrl(service, null, application));
    }
    
    public List<Override> findAll() {
        return SyncUtils.url2OverrideList(findOverrideUrl(null, null, null));
    }
    
    private Pair<Long, URL> findOverrideUrlPair(Long id) {
        return SyncUtils.filterFromCategory(getRegistryCache(), Constants.CONFIGURATORS_CATEGORY, id);
    }

    public Override findById(Long id) {
        return SyncUtils.url2Override(findOverrideUrlPair(id));
    }
    
    private URL getUrlFromOverride(Override override) {
    	return override.toUrl();
        /*Map<String, String> params = ConvertUtil.serviceName2Map(override.getService());
        if(!params.containsKey(Constants.INTERFACE_KEY)) {
            throw new IllegalArgumentException("No interface info");
        }
        if(!params.containsKey(Constants.VERSION_KEY)) {
            throw new IllegalArgumentException("No version info");
        }
        
        boolean enabled = override.isEnabled();
        if(!enabled) {
            params.put("enabled", "false");
        }
        String application = override.getApplication();
        if(!StringUtils.isEmpty(application)) {
            params.put("application", application);
        }
        String address = override.getAddress();
        if(!StringUtils.isEmpty(address)) {
            params.put("address", address);
        }
        
        String overrideAddress = override.getOverrideAddress();
        if(StringUtils.isEmpty(overrideAddress)) {
            overrideAddress = "0.0.0.0";
        }
        params.put(Constants.CATEGORY_KEY, Constants.CONFIGURATORS_CATEGORY);
        
        URL url = new URL("override", overrideAddress, -1, params);
        url = url.addParameterString(override.getParams());
        return url;*/
    }
    
    URL findOverrideUrl(Long id){
        return getUrlFromOverride(findById(id));
    }

}
