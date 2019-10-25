package com.gbiac.fams.business.construction.workapprove.entity;

public class ApproveDto {
    //申请id
    private String workingItemID;
    //获取审批状态（通过 不通过）
    private String approveState;
    //获取审批意见
    private String approveStateNote;
    //报备类型
    private String applyType;

    public String getWorkingItemID() {
        return workingItemID;
    }

    public void setWorkingItemID(String workingItemID) {
        this.workingItemID = workingItemID;
    }

    public String getApproveState() {
        return approveState;
    }

    public void setApproveState(String approveState) {
        this.approveState = approveState;
    }

    public String getApproveStateNote() {
        return approveStateNote;
    }

    public void setApproveStateNote(String approveStateNote) {
        this.approveStateNote = approveStateNote;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
}
