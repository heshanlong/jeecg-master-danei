package com.gbiac.fams.business.construction.workapprove.entity;

public class ApplyDto {
    //申请id
    private String workingItemID;
    //施工角色
    private String roleCode;
    //
    private Integer workingState;
    //
    private Integer workFlag;

    public String getWorkingItemID() {
        return workingItemID;
    }

    public void setWorkingItemID(String workingItemID) {
        this.workingItemID = workingItemID;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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
}
