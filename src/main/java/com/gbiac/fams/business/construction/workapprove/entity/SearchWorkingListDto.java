package com.gbiac.fams.business.construction.workapprove.entity;

public class SearchWorkingListDto {
    private String searchInput;
    private Integer workingState;
    private Integer workFlag;
    private Integer pageNum;
    private Integer pageSize;

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public Integer getWorkingState() {
        return workingState;
    }

    public void setWorkingState(Integer workingState) {
        this.workingState = workingState;
    }

    public Integer getWorkFlag() {
        return workFlag;
    }

    public void setWorkFlag(Integer workFlag) {
        this.workFlag = workFlag;
    }

    public Integer getPageNum() {
        return pageNum;
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
