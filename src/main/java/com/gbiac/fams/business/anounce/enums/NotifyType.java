package com.gbiac.fams.business.anounce.enums;

public enum NotifyType {
	GENERAL("general","普通通告")//普通通告
	,RUN("run","运行通告")//运行通告
	,VOYAGE("voyage","航行通告");//航行通告
	private NotifyType(String name,String value) {
		this.name = name;
		this.value = value;
	}
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
