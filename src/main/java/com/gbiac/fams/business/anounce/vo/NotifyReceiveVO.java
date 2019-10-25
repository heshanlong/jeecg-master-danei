package com.gbiac.fams.business.anounce.vo;



/**
 * 返回接收的通知通告
 * @author Mchen
 *
 */
public class NotifyReceiveVO {
	/** 主键 */
	private java.lang.String id;
	/** 编号 */

	private java.lang.String number;
	/** 标题 */
	
	private java.lang.String title;
	/** 内容 */

	private java.lang.String content;
	/** 类型 */
	
	private java.lang.String type;
	/** 发送模式 */
	
	private java.lang.String sendPattern;
	/** 签发人 */
	
	private java.lang.String sender;
	/** 签发时间 */
	
	private java.util.Date sendTime;
	/** 分组维护主表id */
	
	private java.lang.String groupId;
	/** 创建人登录名称 */
	private java.lang.String createBy;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 更新人登录名称 */
	private java.lang.String updateBy;
	/** 更新日期 */
	private java.util.Date updateDate;
	/** 通知通告的状态 */
	private java.lang.String state;
	/**通知通告的阅读状态**/
	private java.lang.String readState;

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 主键
	 */

	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 主键
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 编号
	 */

	public java.lang.String getNumber() {
		return this.number;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 编号
	 */
	public void setNumber(java.lang.String number) {
		this.number = number;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 标题
	 */

	public java.lang.String getTitle() {
		return this.title;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 标题
	 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 内容
	 */

	public java.lang.String getContent() {
		return this.content;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 内容
	 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 类型
	 */

	
	public java.lang.String getType() {
		return this.type;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 类型
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 发送模式
	 */


	public java.lang.String getSendPattern() {
		return this.sendPattern;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 发送模式
	 */
	public void setSendPattern(java.lang.String sendPattern) {
		this.sendPattern = sendPattern;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 签发人
	 */


	public java.lang.String getSender() {
		return this.sender;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 签发人
	 */
	public void setSender(java.lang.String sender) {
		this.sender = sender;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 签发时间
	 */

	public java.util.Date getSendTime() {
		return this.sendTime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 签发时间
	 */
	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 最多设置130人 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 分组维护主表id
	 */

	public java.lang.String getGroupId() {
		return this.groupId;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 分组维护主表id
	 */
	public void setGroupId(java.lang.String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 创建人登录名称
	 */

	public java.lang.String getCreateBy() {
		return this.createBy;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建日期
	 */

	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 创建日期
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 更新人登录名称
	 */

	public java.lang.String getUpdateBy() {
		return this.updateBy;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 更新日期
	 */

	
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

	public java.lang.String getReadState() {
		return readState;
	}

	public void setReadState(java.lang.String readState) {
		this.readState = readState;
	}
	

	
}
