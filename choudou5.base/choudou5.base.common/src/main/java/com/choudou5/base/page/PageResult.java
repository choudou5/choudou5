package com.choudou5.base.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Name：分页实体
 * @Author：xuhaowen
 * @Date：2018-01-13
 */
public class PageResult<T> implements Serializable{

    protected int pageNo = 1;
    protected int pageSize = 10;
    protected List<T> result = null;
    protected long totalCount = 0;
    protected int length = 5;// 显示页面长度
    protected int rangeEnd = 0;// 范围页尾

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

    public PageResult(int pageSize, int pageNo, int totalCount, List<T> docs) {
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

    public void setPage(final int pageNo, final int pageSize) {
       setPageNo(pageNo);
        setPageSize(pageSize);
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

    public int getRangeEnd() {
        return rangeEnd;
    }

    /**
     * 获得 分页范围数
     */
    public List<Integer> getPageRange() {
        List<Integer> ranges = new ArrayList<Integer>(length);
        int firstPage = 1;
        int totalPage = getIntTotalPages();
        int begin = pageNo - (length / 2);
        if (begin < firstPage)
            begin = firstPage;

        int end = begin + length - 1;
        if (end >= totalPage) {
            end = totalPage;
            begin = end - length + 1;
            if (begin < firstPage) {
                begin = firstPage;
            }
        }
        if (begin > firstPage){
            int i = 0;
            for (i = firstPage; i < firstPage + 1 && i < begin; i++) {
                ranges.add(i);
            }
        }
        for (int i = begin; i <= end; i++) {
            ranges.add(i);
        }
        rangeEnd = end;
        return ranges;
    }
}
