package com.choudou5.base.page;

import java.io.Serializable;

/**
 * @Name：分页Bean
 * @Author：xuhaowen
 * @Date：2018-01-13
 */
public class PageBean implements Serializable {

    public static final Integer MAX_PAGE_SIZE = 100;	// 每页最大分页数大小限制
    public static final Integer DEF_PAGE_SIZE = 10;
    public static final Integer DEF_PAGE_NO = 1;
    private Integer pageNo = DEF_PAGE_NO;
    private Integer pageSize = DEF_PAGE_SIZE;

    public PageBean() {
    }

    public PageBean(int pageNo) {
        this.pageNo = pageNo;
    }

    public PageBean(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo == null)
            pageNo = DEF_PAGE_NO;
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize == null)
            pageSize = DEF_PAGE_SIZE;
        if (pageSize < 1) {
            pageSize = DEF_PAGE_SIZE;
        }else if(pageSize > MAX_PAGE_SIZE){
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (pageNo - 1) * pageSize;
    }
}
