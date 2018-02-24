package com.choudou5.base.helper;


import com.choudou5.base.exception.BizException;

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

    void put(String cacheKey, Object object) throws BizException;

    void put(String cacheKey, Object object, int secondTimeout) throws BizException;

    String get(String cacheKey) throws BizException;

    Object getObj(String cacheKey) throws BizException;

    Integer getInt(String cacheKey) throws BizException;

    Long getLong(String cacheKey) throws BizException;

    <T extends Serializable> T get(String cacheKey, Class<T> classz) throws BizException;

    <T extends Serializable> List<T> getList(String cacheKey, Class<T> classz) throws BizException;

    void remove(String cacheKey) throws BizException;

    void removes(String... cacheKeys) throws BizException;
}
