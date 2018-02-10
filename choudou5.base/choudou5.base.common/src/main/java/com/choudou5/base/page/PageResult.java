package com.choudou5.base.page;

import java.io.Serializable;
import java.util.List;

/**
 * @Name：分页实体
 * @Author：xuhaowende@sina.cn
 * @Date：2018-01-13 15:58
 * @Site：http://www.javasaas.top
 * @License：MIT
 */
public class PageResult implements Serializable{

    protected int pageNo = 1;
    protected int pageSize = 10;
    protected List result = null;
    protected long totalCount = 0;

    public PageResult() {
    }

    public PageResult(int pageSize) {
        setPageSize(pageSize);
    }

    public PageResult(int pageSize, int pageNo) {
        setPageSize(pageSize);
        setPageNo(pageNo);
    }

    public PageResult(int pageSize, int pageNo, int totalCount) {
        setPageSize(pageSize);
        setPageNo(pageNo);
        setTotalCount(totalCount);
    }

    public PageResult(int pageSize, int pageNo, int totalCount, List docs) {
        setPageSize(pageSize);
        setPageNo(pageNo);
        setTotalCount(totalCount);
        setResult(docs);
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;
        if (pageNo < 1) {
            this.pageNo = 1;
        }
    }

    public PageResult pageNo(final int thePageNo) {
        setPageNo(thePageNo);
        return this;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
        if (pageSize < 1) {
            this.pageSize = 1;
        }else if(pageSize > PageBean.MAX_PAGE_SIZE){
            this.pageSize = PageBean.MAX_PAGE_SIZE;
        }
    }

    public PageResult pageSize(final int thePageSize) {
        setPageSize(thePageSize);
        return this;
    }

    public int getStart() {
        return (pageNo - 1) * pageSize;
    }

    public List getResult() {
        return result;
    }


    public void setResult(final List result) {
        this.result = result;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(final long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPages() {
        if (totalCount <= 0)
            return 0;

        long count = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            count++;
        }
        return count;
    }

    public int getIntTotalPages() {
        if (totalCount <= 0)
            return 0;

        int count = Long.valueOf(totalCount / pageSize).intValue();
        if (totalCount % pageSize > 0) {
            count++;
        }
        return count;
    }

    public boolean isHasNext() {
        return (pageNo + 1 <= getTotalPages());
    }

    public int getNextPage() {
        if (isHasNext())
            return pageNo + 1;
        else
            return pageNo;
    }

    public boolean isHasPre() {
        return (pageNo - 1 >= 1);
    }

    public int getPrePage() {
        if (isHasPre())
            return pageNo - 1;
        else
            return pageNo;
    }

}
