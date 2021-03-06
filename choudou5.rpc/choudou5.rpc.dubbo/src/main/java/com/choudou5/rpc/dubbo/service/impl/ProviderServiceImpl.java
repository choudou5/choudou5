/**
 * Project: dubbo.registry-1.1.0-SNAPSHOT
 * 
 * File Created at 2010-4-15
 * $Id: ProviderServiceImpl.java 185206 2012-07-09 03:06:37Z tony.chenl $
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
package com.choudou5.rpc.dubbo.service.impl;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.choudou5.base.util.MapUtil;
import com.choudou5.rpc.dubbo.domain.ProviderEntity;
import com.choudou5.rpc.dubbo.service.ProviderService;
import com.choudou5.rpc.dubbo.util.Pair;
import com.choudou5.rpc.dubbo.util.ParseUtils;
import com.choudou5.rpc.dubbo.util.SyncUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

/**
 * IbatisProviderService
 * 
 * @author tony.chenl
 */
public class ProviderServiceImpl extends AbstractService implements ProviderService {
	
    public ProviderEntity findProvider(Long id) {
        return SyncUtils.url2Provider(findProviderUrlPair(id));
    }
    
    public Pair<Long, URL> findProviderUrlPair(Long id) {
        return SyncUtils.filterFromCategory(getRegistryCache(), Constants.PROVIDERS_CATEGORY, id);
    }

    public List<String> findServices() {
        List<String> ret = new ArrayList<String>();
        ConcurrentMap<String, Map<Long, URL>> providerUrls = getRegistryCache().get(Constants.PROVIDERS_CATEGORY);
        if(MapUtil.isEmpty(providerUrls))
            return ret;
        ret.addAll(providerUrls.keySet());
        return ret;
    }

    public List<String> findAddresses() {
        List<String> ret = new ArrayList<String>();
        ConcurrentMap<String, Map<Long, URL>> providerUrls = getRegistryCache().get(Constants.PROVIDERS_CATEGORY);
        if(MapUtil.isEmpty(providerUrls)) return ret;
        for(Entry<String, Map<Long, URL>> e1 : providerUrls.entrySet()) {
            Map<Long, URL> value = e1.getValue();
            for(Entry<Long, URL> e2 : value.entrySet()) {
                URL u = e2.getValue();
                String app = u.getAddress();
                if(app != null) ret.add(app);
            }
        }
        return ret;
    }

    public List<String> findAddressesByApplication(String application) {
        List<String> ret = new ArrayList<String>();
        ConcurrentMap<String, Map<Long, URL>> providerUrls = getRegistryCache().get(Constants.PROVIDERS_CATEGORY);
        if(MapUtil.isEmpty(providerUrls)) return ret;
        for(Entry<String, Map<Long, URL>> e1 : providerUrls.entrySet()) {
            Map<Long, URL> value = e1.getValue();
            if(MapUtil.isEmpty(value))
                continue;
            for(Entry<Long, URL> e2 : value.entrySet()) {
                URL u = e2.getValue();
                if(application.equals(u.getParameter(Constants.APPLICATION_KEY))) {
                    String addr = u.getAddress();
                    if(addr != null) ret.add(addr);
                }
            }
        }
        
        return ret;
    }

    public List<String> findAddressesByService(String service) {
        List<String> ret = new ArrayList<String>();
        ConcurrentMap<String, Map<Long, URL>> providerUrls = getRegistryCache().get(Constants.PROVIDERS_CATEGORY);
        if(MapUtil.isEmpty(providerUrls))
            return ret;
        Map<Long, URL> value = providerUrls.get(service);
        if(MapUtil.isEmpty(value))
            return ret;
        for(Entry<Long, URL> e2 : value.entrySet()) {
            URL u = e2.getValue();
            String app = u.getAddress();
            if(app != null) ret.add(app);
        }
        return ret;
    }

    public List<URL> findURLByService(String service) {
        List<URL> ret = new ArrayList<URL>();
        ConcurrentMap<String, Map<Long, URL>> providerUrls = getRegistryCache().get(Constants.PROVIDERS_CATEGORY);
        if(MapUtil.isEmpty(providerUrls))
            return ret;
        Map<Long, URL> value = providerUrls.get(service);
        if(MapUtil.isEmpty(value))
            return ret;
        for(Entry<Long, URL> e2 : value.entrySet()) {
            ret.add(e2.getValue());
        }
        return ret;
    }


    public List<String> findApplicationsByServiceName(String service) {
        List<String> ret = new ArrayList<String>();
        ConcurrentMap<String, Map<Long, URL>> providerUrls = getRegistryCache().get(Constants.PROVIDERS_CATEGORY);
        if(MapUtil.isEmpty(providerUrls)) return ret;
        Map<Long, URL> value = providerUrls.get(service);
        if(MapUtil.isEmpty(value))
            return ret;
        for(Entry<Long, URL> e2 : value.entrySet()) {
            URL u = e2.getValue();
            String app = u.getParameter(Constants.APPLICATION_KEY);
            if(app != null) ret.add(app);
        }
        return ret;
    }

    public List<ProviderEntity> findByService(String serviceName) {
        return SyncUtils.url2ProviderList(findProviderUrlByService(serviceName));
    }
    
    private Map<Long, URL> findProviderUrlByService(String service) {
        Map<String, String> filter = new HashMap<String, String>();
        filter.put(Constants.CATEGORY_KEY, Constants.PROVIDERS_CATEGORY);
        filter.put(SyncUtils.SERVICE_FILTER_KEY, service);
        return SyncUtils.filterFromCategory(getRegistryCache(), filter);
    }

    public List<ProviderEntity> findAll() {
        return SyncUtils.url2ProviderList(findAllProviderUrl());
    }
    
    private Map<Long, URL> findAllProviderUrl() {
        Map<String, String> filter = new HashMap<String, String>();
        filter.put(Constants.CATEGORY_KEY, Constants.PROVIDERS_CATEGORY);
        return SyncUtils.filterFromCategory(getRegistryCache(), filter);
    }
    
    public List<ProviderEntity> findByAddress(String providerAddress) {
        return SyncUtils.url2ProviderList(findProviderUrlByAddress(providerAddress));
    }
    
    public Map<Long, URL> findProviderUrlByAddress(String address) {
        Map<String, String> filter = new HashMap<String, String>();
        filter.put(Constants.CATEGORY_KEY, Constants.PROVIDERS_CATEGORY);
        filter.put(SyncUtils.ADDRESS_FILTER_KEY, address);
        return SyncUtils.filterFromCategory(getRegistryCache(), filter);
    }

    public List<String> findServicesByAddress(String address) {
        List<String> ret = new ArrayList<String>();
        
        ConcurrentMap<String, Map<Long, URL>> providerUrls = getRegistryCache().get(Constants.PROVIDERS_CATEGORY);
        if(providerUrls == null || address == null || address.length() == 0) return ret;
        
        for(Entry<String, Map<Long, URL>> e1 : providerUrls.entrySet()) {
            Map<Long, URL> value = e1.getValue();
            for(Entry<Long, URL> e2 : value.entrySet()) {
                URL u = e2.getValue();
                if(address.equals(u.getAddress())) {
                    ret.add(e1.getKey());
                    break;
                }
            }
        }
        
        return ret;
    }

    public List<String> findApplications() {
        List<String> ret = new ArrayList<String>();
        ConcurrentMap<String, Map<Long, URL>> providerUrls = getRegistryCache().get(Constants.PROVIDERS_CATEGORY);
        if(providerUrls == null) return ret;
        
        for(Entry<String, Map<Long, URL>> e1 : providerUrls.entrySet()) {
            Map<Long, URL> value = e1.getValue();
            for(Entry<Long, URL> e2 : value.entrySet()) {
                URL u = e2.getValue();
                String app = u.getParameter(Constants.APPLICATION_KEY);
                if(app != null) ret.add(app);
            }
        }
        
        return ret;
    }

    public List<ProviderEntity> findByApplication(String application) {
        return SyncUtils.url2ProviderList(findProviderUrlByApplication(application));
    }
    
    private Map<Long, URL> findProviderUrlByApplication(String application) {
        Map<String, String> filter = new HashMap<String, String>();
        filter.put(Constants.CATEGORY_KEY, Constants.PROVIDERS_CATEGORY);
        filter.put(Constants.APPLICATION_KEY, application);
        return SyncUtils.filterFromCategory(getRegistryCache(), filter);
    }

    public List<String> findServicesByApplication(String application) {
        List<String> ret = new ArrayList<String>();
        
        ConcurrentMap<String, Map<Long, URL>> providerUrls = getRegistryCache().get(Constants.PROVIDERS_CATEGORY);
        if(providerUrls == null || application == null || application.length() == 0) return ret;
        
        for(Entry<String, Map<Long, URL>> e1 : providerUrls.entrySet()) {
            Map<Long, URL> value = e1.getValue();
            for(Entry<Long, URL> e2 : value.entrySet()) {
                URL u = e2.getValue();
                if(application.equals(u.getParameter(Constants.APPLICATION_KEY))) {
                    ret.add(e1.getKey());
                    break;
                }
            }
        }
        
        return ret;
    }

    public List<String> findMethodsByService(String service) {
        List<String> ret = new ArrayList<String>();

        ConcurrentMap<String, Map<Long, URL>> providerUrls = getRegistryCache().get(Constants.PROVIDERS_CATEGORY);
        if(providerUrls == null || service == null || service.length() == 0) return ret;
        
        Map<Long, URL> providers = providerUrls.get(service);
        if(null == providers || providers.isEmpty()) return ret;
        
        Entry<Long, URL> p = providers.entrySet().iterator().next();
        String value = p.getValue().getParameter("methods");
        if (value == null || value.length() == 0) {
            return ret;
        }
        String[] methods = value.split(ParseUtils.METHOD_SPLIT);
        if (methods == null || methods.length == 0) {
            return ret;
        }
        
        for(String m : methods) {
            ret.add(m);
        }
        return ret;
    }

    private URL findProviderUrl(Long id) {
        return findProvider(id).toUrl();
    }

    public ProviderEntity findByServiceAndAddress(String service, String address) {
        return SyncUtils.url2Provider(findProviderUrl(service, address));
    }
    
	private Pair<Long, URL> findProviderUrl(String service, String address) {
        Map<String, String> filter = new HashMap<String, String>();
        filter.put(Constants.CATEGORY_KEY, Constants.PROVIDERS_CATEGORY);
        filter.put(SyncUtils.ADDRESS_FILTER_KEY, address);
        
        Map<Long, URL> ret = SyncUtils.filterFromCategory(getRegistryCache(), filter);
        if(ret.isEmpty()) {
            return null;
        }
        else { 
            Long key = ret.entrySet().iterator().next().getKey();
            return new Pair<Long, URL>(key, ret.get(key));
        }
    }


    
}
