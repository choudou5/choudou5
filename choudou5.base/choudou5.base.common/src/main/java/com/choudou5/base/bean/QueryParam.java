package com.choudou5.base.bean;


import com.choudou5.base.page.PageBean;
import com.choudou5.base.util.StrUtil;

import java.beans.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Name：查询Bean
 * @Author：xuhaowende@sina.cn
 * @Date：2018-01-13 16:39
 * @Site：http://solrhome.com
 * @License：MIT
 */
public class QueryParam implements Serializable {

    private Object paramBean;
    private PageBean pageBean;
    private OrderBean orderBean;
    private Map<String, Object> extendParams = new HashMap();


    public Object getParamBean() {
        return paramBean;
    }

    public void setParamBean(Object paramBean) {
        this.paramBean = paramBean;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public Map<String, Object> getExtendParams() {
        return this.extendParams;
    }

    public void addExtendParam(String extendParamKey, Object extendParamValue) {
        this.extendParams.put(extendParamKey, extendParamValue);
    }

    public void removeExtendParam(String extendParamKey) {
        this.extendParams.remove(extendParamKey);
    }


    public void setDefParam(int pageNo, int pageSize, String orderBy, String order) {
        setPageDefParam(pageNo, pageSize);
        setOrderDefParam(orderBy, order);
    }

    public void setOrderDefParam(String orderBy, String order) {
        setOrderParam(orderBy, order, true);
    }
    public void setOrderParam(String orderBy, String order) {
        setOrderParam(orderBy, order, false);
    }

    private void setOrderParam(String orderBy, String order, boolean isSetDef) {
        OrderBean orderBean = this.getOrderBean();
        if(orderBean == null) {
            setOrderBean(new OrderBean(orderBy, order));
        } else {
            if(!isSetDef){
                orderBean.setOrderBy(orderBy);
                orderBean.setOrder(StrUtil.isBlank(order) ? "DESC" : order);
            }
        }
    }

    public void setPageDefParam(int pageNo, int pageSize) {
        setPageParam(pageNo, pageSize, true);
    }
    public void setPageParam(int pageNo, int pageSize) {
        setPageParam(pageNo, pageSize, false);
    }

    private void setPageParam(int pageNo, int pageSize, boolean isSetDef) {
        PageBean pageBean = this.getPageBean();
        if(pageBean == null) {
            this.setPageBean(new PageBean(pageNo, pageSize));
        }else{
            if(!isSetDef){
                pageBean.setPageNo(pageNo);
                pageBean.setPageSize(pageSize);
            }
        }
    }


    public void setDefPage() {
        PageBean pageBean = this.getPageBean();
        if(pageBean == null) {
            this.setPageBean(new PageBean(1, PageBean.DEF_PAGE_SIZE));
        }
    }

}
