package com.choudou5.cache.ehcache;

import com.choudou5.base.exception.SysException;
import com.choudou5.base.helper.CacheHelper;
import com.choudou5.base.util.JsonUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 * @Name：EhCache 工具类
 * @Author：xuhaowende
 * @Date：2018-02-22
 */
public class EhCacheUtil implements CacheHelper {

    protected static final String CONF_PATH = "/cache/ehcache.xml";

    public static final String CACHE_SYS = "sysCache";

    private CacheManager manager;

    private static EhCacheUtil ehCache;

    protected EhCacheUtil() {
        this(CONF_PATH);
    }

    public EhCacheUtil(String path) {
        if(manager == null){
            URL url = getClass().getResource(path);
            manager = CacheManager.create(url);
        }
    }

    public static EhCacheUtil getInstance() {
        if (ehCache == null)
            ehCache = new EhCacheUtil();
        return ehCache;
    }

    @Override
    public void put(String key, Object value) {
        Cache cache = manager.getCache(CACHE_SYS);
        Element element = new Element(key, JsonUtil.toStr(value));
        cache.put(element);
    }

    @Override
    public void putObj(String key, Object value) throws SysException {
        Cache cache = manager.getCache(CACHE_SYS);
        Element element = new Element(key, value);
        cache.put(element);
    }

    @Override
    public void put(String cacheKey, Object value, int secondTimeout) throws SysException {
        Cache cache = manager.getCache(CACHE_SYS);
        Element element = new Element(cacheKey, JsonUtil.toStr(value), false, secondTimeout, secondTimeout);
        cache.put(element);
    }

    public void put(String cacheName, String key, Object value) {
        Cache cache = manager.getCache(cacheName);
        Element element = new Element(key, JsonUtil.toStr(value));
        cache.put(element);
    }

    public void putDisk(String cacheName, String key, Object value) {
        Cache cache = manager.getCache(cacheName);
        Element element = new Element(key, JsonUtil.toStr(value));
        cache.put(element);
        cache.flush();
    }

    @Override
    public String get(String key) {
        Cache cache = manager.getCache(CACHE_SYS);
        Element element = cache.get(key);
        if(element==null)
            return null;
        Object obj = element.getObjectValue();
        return obj == null ? null : obj.toString();
    }

    @Override
    public Object getObj(String key) {
        Cache cache = manager.getCache(CACHE_SYS);
        Element element = cache.get(key);
        if(element==null)
            return null;
        return element.getObjectValue();
    }

    public Object get(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    @Override
    public Integer getInt(String cacheKey) throws SysException {
        Cache cache = manager.getCache(CACHE_SYS);
        Element element = cache.get(cacheKey);
        if(element==null)
            return null;
        Object obj = element.getObjectValue();
        return obj == null ? null : Integer.parseInt(obj.toString());
    }

    @Override
    public Long getLong(String cacheKey) throws SysException {
        Cache cache = manager.getCache(CACHE_SYS);
        Element element = cache.get(cacheKey);
        if(element==null)
            return null;
        Object obj = element.getObjectValue();
        return obj == null ? null : Long.parseLong(obj.toString());
    }

    @Override
    public <T extends Serializable> T get(String cacheKey, Class<T> classz) throws SysException {
        Cache cache = manager.getCache(CACHE_SYS);
        Element element = cache.get(cacheKey);
        if(element==null)
            return null;
        Object obj = element.getObjectValue();
        if(obj==null)
            return null;
        return JsonUtil.toJavaObj(obj.toString(), classz);
    }

    @Override
    public <T extends Serializable> List<T> getList(String cacheKey, Class<T> classz) throws SysException {
        Cache cache = manager.getCache(CACHE_SYS);
        Element element = cache.get(cacheKey);
        if(element==null)
            return null;
        Object obj = element.getObjectValue();
        if(obj==null)
            return null;
        return JsonUtil.toJavaObjList(obj.toString(), classz);
    }

    public int getCacheSize(String cacheName) {
        return manager.getCache(cacheName).getSize();
    }

    public void remove(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        cache.remove(key);
    }

    @Override
    public void remove(String key) {
        Cache cache = manager.getCache(CACHE_SYS);
        cache.remove(key);
    }

    @Override
    public void removes(String... cacheKeys) throws SysException {
        Cache cache = manager.getCache(CACHE_SYS);
        for (String cacheKey : cacheKeys) {
            cache.remove(cacheKey);
        }
    }

    public void shutdown(){
        manager.shutdown();
    }
}
