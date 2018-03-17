package com.choudou5.base.helper;


import com.choudou5.base.exception.SysException;

import java.io.Serializable;
import java.util.List;

/**
 * @Name：缓存 助手
 * @Author：xuhaowende@sina.cn
 * @Date：2018-01-13 17:35
 * @Site：http://solrhome.com
 * @License：MIT
 */
public interface CacheHelper {

    void put(String cacheKey, Object object) throws SysException;

    void put(String cacheKey, Object object, int secondTimeout) throws SysException;

    String get(String cacheKey) throws SysException;

    Object getObj(String cacheKey) throws SysException;

    Integer getInt(String cacheKey) throws SysException;

    Long getLong(String cacheKey) throws SysException;

    <T extends Serializable> T get(String cacheKey, Class<T> classz) throws SysException;

    <T extends Serializable> List<T> getList(String cacheKey, Class<T> classz) throws SysException;

    void remove(String cacheKey) throws SysException;

    void removes(String... cacheKeys) throws SysException;
}
