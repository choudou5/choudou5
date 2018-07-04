package com.choudou5.rpc.dubbo.util;

/**
 * Project: dubbo.governance-2.2.0-SNAPSHOT
 *
 * File Created at Mar 31, 2012
 * $Id: SyncUtils.java 184666 2012-07-05 11:13:17Z tony.chenl $
 *
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.choudou5.rpc.dubbo.domain.ConsumerEntity;
import com.choudou5.rpc.dubbo.domain.OverrideEntity;
import com.choudou5.rpc.dubbo.domain.ProviderEntity;
import com.choudou5.rpc.dubbo.domain.RouteEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ding.lid
 */
public class SyncUtils {

    public static final String SERVICE_FILTER_KEY = ".service";

    public static final String ADDRESS_FILTER_KEY = ".address";

    public static final String ID_FILTER_KEY = ".id";

    public static ProviderEntity url2Provider(Pair<Long, URL> pair) {
        if (pair == null) {
            return null;
        }

        Long id = pair.getKey();
        URL url = pair.getValue();

        if (url == null)
            return null;

        ProviderEntity p = new ProviderEntity();
        p.setId(id);
        p.setService(url.getServiceKey());
        p.setAddress(url.getAddress());
        p.setApplication(url.getParameter(Constants.APPLICATION_KEY));
        p.setUrl(url.toIdentityString());
        p.setParameters(url.toParameterString());

        p.setDynamic(url.getParameter("dynamic", true));
        p.setEnabled(url.getParameter(Constants.ENABLED_KEY, true));
        p.setWeight(url.getParameter(Constants.WEIGHT_KEY, Constants.DEFAULT_WEIGHT));
        p.setUsername(url.getParameter("owner"));

        return p;
    }

    public static List<ProviderEntity> url2ProviderList(Map<Long, URL> ps) {
        List<ProviderEntity> ret = new ArrayList<ProviderEntity>();
        for(Map.Entry<Long, URL> entry : ps.entrySet()) {
            ret.add(url2Provider(new Pair<Long, URL>(entry.getKey(), entry.getValue())));
        }
        return ret;
    }

    public static ConsumerEntity url2Consumer(Pair<Long, URL> pair) {
        if (pair == null) {
            return null;
        }

        Long id = pair.getKey();
        URL url = pair.getValue();

        if (null == url)
            return null;

        ConsumerEntity c = new ConsumerEntity();
        c.setId(id);
        c.setService(url.getServiceKey());
        c.setAddress(url.getHost());
        c.setApplication(url.getParameter(Constants.APPLICATION_KEY));
        c.setParameters(url.toParameterString());

        return c;
    }

    public static List<ConsumerEntity> url2ConsumerList(Map<Long, URL> cs) {
        List<ConsumerEntity> list = new ArrayList<ConsumerEntity>();
        if(cs == null) return list;
        for(Map.Entry<Long, URL> entry : cs.entrySet()) {
            list.add(url2Consumer(new Pair<Long, URL>(entry.getKey(), entry.getValue())));
        }
        return list;
    }

    public static RouteEntity url2Route(Pair<Long, URL> pair) {
        if (pair == null) {
            return null;
        }

        Long id = pair.getKey();
        URL url = pair.getValue();

        if (null == url)
            return null;

        RouteEntity r = new RouteEntity();
        r.setId(id);
        r.setName(url.getParameter("name"));
        r.setService(url.getServiceKey());
        r.setPriority(url.getParameter(Constants.PRIORITY_KEY, 0));
        r.setEnabled(url.getParameter(Constants.ENABLED_KEY, true));
        r.setForce(url.getParameter(Constants.FORCE_KEY, false));
        r.setRule(url.getParameterAndDecoded(Constants.RULE_KEY));
        return r;
    }

    public static List<RouteEntity> url2RouteList(Map<Long, URL> cs) {
        List<RouteEntity> list = new ArrayList<RouteEntity>();
        if(cs == null) return list;
        for(Map.Entry<Long, URL> entry : cs.entrySet()) {
            list.add(url2Route(new Pair<Long, URL>(entry.getKey(), entry.getValue())));
        }
        return list;
    }

    public static OverrideEntity url2Override(Pair<Long, URL> pair) {
        if (pair == null) {
            return null;
        }

        Long id = pair.getKey();
        URL url = pair.getValue();

        if (null == url)
            return null;

        OverrideEntity o = new OverrideEntity();
        o.setId(id);

        Map<String, String> parameters = new HashMap<String, String>(url.getParameters());

        o.setService(url.getServiceKey());
        parameters.remove(Constants.INTERFACE_KEY);
        parameters.remove(Constants.GROUP_KEY);
        parameters.remove(Constants.VERSION_KEY);
        parameters.remove(Constants.APPLICATION_KEY);
        parameters.remove(Constants.CATEGORY_KEY);
        parameters.remove(Constants.DYNAMIC_KEY);
        parameters.remove(Constants.ENABLED_KEY);

        o.setEnabled(url.getParameter(Constants.ENABLED_KEY, true));

        String host = url.getHost();
        boolean anyhost = url.getParameter(Constants.ANYHOST_VALUE, false);
        if(!anyhost || !"0.0.0.0".equals(host)) {
            o.setAddress(url.getAddress());
        }

        o.setApplication(url.getParameter(Constants.APPLICATION_KEY, url.getUsername()));
        parameters.remove(Constants.VERSION_KEY);

        o.setParams(StringUtils.toQueryString(parameters));

        return o;
    }

    // Map<category, Map<servicename, Map<Long, URL>>>
    public static <SM extends Map<String, Map<Long, URL>>> Map<Long, URL> filterFromCategory(Map<String, SM> urls, Map<String, String> filter) {
        String c = (String) filter.get(Constants.CATEGORY_KEY);
        if(c==null) throw new IllegalArgumentException("no category");

        filter.remove(Constants.CATEGORY_KEY);
        return filterFromService(urls.get(c), filter);
    }

    public static List<OverrideEntity> url2OverrideList(Map<Long, URL> cs) {
        List<OverrideEntity> list = new ArrayList<OverrideEntity>();
        if(cs == null) return list;
        for(Map.Entry<Long, URL> entry : cs.entrySet()) {
            list.add(url2Override(new Pair<Long, URL>(entry.getKey(), entry.getValue())));
        }
        return list;
    }


    // Map<servicename, Map<Long, URL>>
    public static Map<Long, URL> filterFromService(Map<String, Map<Long, URL>> urls, Map<String, String> filter) {
        Map<Long, URL> ret = new HashMap<Long, URL>();
        if(urls == null) return ret;

        String s = (String) filter.remove(SERVICE_FILTER_KEY);
        if(s == null) {
            for(Map.Entry<String, Map<Long, URL>> entry : urls.entrySet()) {
                filterFromUrls(entry.getValue(), ret, filter);
            }
        }
        else {
            Map<Long, URL> map = urls.get(s);
            filterFromUrls(map, ret, filter);
        }

        return ret;
    }

    // Map<Long, URL>
    static void filterFromUrls(Map<Long, URL> from, Map<Long, URL> to, Map<String, String> filter) {
        if(from == null || from.isEmpty()) return;

        for(Map.Entry<Long, URL> entry : from.entrySet()) {
            URL url = entry.getValue();

            boolean match = true;
            for(Map.Entry<String, String> e : filter.entrySet()) {
                String key = e.getKey();
                String value = e.getValue();

                if(ADDRESS_FILTER_KEY.equals(key)) {
                    if(!value.equals(url.getAddress())) {
                        match = false;
                        break;
                    }
                }
                else {
                    if(!value.equals(url.getParameter(key))) {
                        match = false;
                        break;
                    }
                }
            }

            if(match) {
                to.put(entry.getKey(), url);
            }
        }
    }

    public static <SM extends Map<String, Map<Long, URL>>> Pair<Long, URL> filterFromCategory(Map<String, SM> urls, String category, Long id) {
        SM services = urls.get(category);
        if(services == null) return null;

        for(Map.Entry<String, Map<Long, URL>> e1 : services.entrySet()) {
            Map<Long, URL> u = e1.getValue();
            if(u.containsKey(id)) return new Pair<Long, URL>(id, u.get(id));
        }
        return null;
    }
}
