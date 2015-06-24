package com.yxkong.common.web.vo;

import java.util.ArrayList;
import java.util.List;



public class PageBean {

    /**每页大小*/
    private int pageSize =10;// Records per page;
    /**总记录数*/
    private long total = 0;// Total record;
    /**当前页*/
    private int index = 1;// The page num which is loaded from,one based;
    /**总页数*/
    private long pageCount = 0;
    private List<String> pageList;
    /**上一页*/
    private int upNo;
    /**下一页*/
    private int nextNo;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getStart() {
        int startNum = Long.valueOf(this.getPageCount()).intValue();
        if (index > startNum) {
            index = startNum;
        }
        return (index - 1) * pageSize;
    }

    public int getIndex() {
        if (index <= 0) {
            index = 1;
        }
        return index;
    }

    public long getPageCount() {
        if (total % pageSize == 0) {
            pageCount = total / pageSize;
        } else {
            pageCount = total / pageSize + 1;
        }

        return pageCount;
    }

    public int getNextNo() {
        if (this.pageCount == this.index) {
            nextNo = this.index;
        } else {
            nextNo = this.index + 1;
        }
        return nextNo;
    }

    public int getUpNo() {
        if (this.index == 1) {
            upNo = 1;
        } else {
            upNo = this.index - 1;
        }
        return upNo;
    }

    public List<String> getPageList() {
        pageList = new ArrayList<String>();
        for (int i = 1; i <= this.pageCount; i++) {
            pageList.add(String.valueOf(i));
        }
        return pageList;
    }

}
