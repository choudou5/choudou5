package com.choudou5.base.mapper;

import com.choudou5.base.page.PageResult;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 功能：
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 * 1. 持有Mapper的单例.
 * 2. 返回值类型转换.
 * 3. 批量转换Collection中的所有对象.
 * 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 */
public class BeanMapper {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if(source == null || destinationClass == null) return null;
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        if(source == null || destinationObject == null) return;
        dozer.map(source, destinationObject);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     * @param sourceList
     * @param destinationClass
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<>();
        if(sourceList == null) return destinationList;
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer转换PageResult中对象的类型.
     * @param sourceResult
     * @param destinationClass
     */
    public static <T> PageResult<T> mapPage(PageResult sourceResult, Class<T> destinationClass) {
        PageResult<T> result = new PageResult<>();
        if(sourceResult == null) return result;
        List<T> sourceList = sourceResult.getResult();
        if(sourceList == null) return result;
        result.setResult(mapList(sourceList, destinationClass));
        result.setPage(sourceResult.getPageNo(), sourceResult.getPageSize());
        result.setTotalCount(sourceResult.getTotalCount());
        return result;
    }



}
