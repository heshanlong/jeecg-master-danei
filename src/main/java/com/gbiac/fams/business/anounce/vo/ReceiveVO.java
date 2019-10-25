package com.gbiac.fams.business.anounce.vo;

import java.util.Date;

/**
 * 通知接收状态的VO
 * @author mchen
 *
 */
public class ReceiveVO {

	//接收单的Id
	private String id;
	private String userId;
	private String realName;
	private String userName;
	//头像
	private String headUrl;
	
	private String state;
	//阅读时间
	private String viewTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getViewTime() {
		return viewTime;
	}
	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}
	
	
}
