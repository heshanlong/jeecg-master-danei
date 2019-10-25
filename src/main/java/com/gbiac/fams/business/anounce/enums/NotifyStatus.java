package com.gbiac.fams.business.anounce.enums;

public enum NotifyStatus {
	//一般状态
	NORMAL("1");
	
	private String state;
	private NotifyStatus(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
