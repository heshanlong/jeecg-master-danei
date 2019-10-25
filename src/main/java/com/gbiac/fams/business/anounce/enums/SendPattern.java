package com.gbiac.fams.business.anounce.enums;

import com.gbiac.fams.business.anounce.entity.NotifyContant;

public enum SendPattern {
	All(NotifyContant.SEND_PATTERN_ALL,"全员发送"),Internal(NotifyContant.SEND_PATTERN_INTERNAL,"飞管内部"),
	Custom(NotifyContant.SEND_PATTERN_CUSTOM,"选择分组"),Current(NotifyContant.SEND_PATTERN_CURRENT,"选择人员")
	;
	private String pattern;
	private String name;
	private SendPattern(String pattern,String name) {

		this.pattern = pattern;
		this.name = name;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
