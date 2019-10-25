package com.gbiac.fams.business.anounce.vo;
/**
 * 回复信息的VO
 * @author mchen
 *
 */

import java.util.Date;

public class ReplyInfoVO {

	private String name;
	private String department;
	private String replyName;
	private String replyDepartment;
	private String content;
	private Date date;
	private String id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getReplyName() {
		return replyName;
	}
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	public String getReplyDepartment() {
		return replyDepartment;
	}
	public void setReplyDepartment(String replyDepartment) {
		this.replyDepartment = replyDepartment;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
