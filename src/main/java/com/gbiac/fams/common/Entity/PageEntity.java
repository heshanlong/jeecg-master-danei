package com.gbiac.fams.common.Entity;

public class PageEntity {
    //页数
    private Integer pageNum;
    //每页条数
    private Integer pageSize=10;
    public PageEntity(){}
    public PageEntity(Integer pageNum, Integer pageSize) {
        this.pageNum=pageNum;
        this.pageSize=pageSize;
    }

    public Integer getPageNum() {
        return (pageNum-1)*pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
