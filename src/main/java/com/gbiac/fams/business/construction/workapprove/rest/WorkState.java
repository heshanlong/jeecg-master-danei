package com.gbiac.fams.business.construction.workapprove.rest;

/**
 * 作业状态的枚举类
 * @author sir
 *
 */
public enum WorkState {

	sOut(0,"进行中"),
	sIn(4,"待进场"),
	
	sApply(3,"待提交"),
	sDepartmentApply(6,"待部门审核"),
	yApproveApply(1,"待审批"),
	yApproveIn(5,"进场待确认"),
	yApproveOut(7,"离场待确认"),
	
	leave(2,"已离场"),
	end(8,"已结束"),

	sUndo(9,"已撤回");
	
	
	private int code;
	private String name;
	private WorkState(int code,String name) {
		this.code = code;
		this.name = name;
	}
	
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.getName() +"+"+this.getCode();
	}
	
	public static WorkState getWorkStateByCode(Integer code) {
		for (WorkState workState : WorkState.values()) {
			if (code == workState.getCode()) {
				return workState;
			}
		}
		return null;
	  }
}
