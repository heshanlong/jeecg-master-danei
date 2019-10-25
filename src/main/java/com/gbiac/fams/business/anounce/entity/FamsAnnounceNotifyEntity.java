package com.gbiac.fams.business.anounce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title: Entity
 * @Description: 通知通告表
 * @author onlineGenerator
 * @date 2019-01-23 13:47:56
 * @version V1.0
 *
 */
@Entity
@Table(name = "fams_announce_notify", schema = "")
@SuppressWarnings("serial")
public class FamsAnnounceNotifyEntity implements java.io.Serializable {
	/** 主键 */
	private java.lang.String id;
	/** 编号 */
	@Excel(name = "编号", width = 15)
//	@NotEmpty(message="编号不能为空")
//	@Size(min=10,max=50,message="编号长度应该{min}-{max}之间")
//	@Pattern(regexp="(\\w){6,50}",message="格式不对")
	private java.lang.String number;
	/** 标题 */
	@Excel(name = "标题", width = 15)
	@NotEmpty(message = "标题不能为空")
	@Size(min = 1, max = 100, message = "标题长度应该{min}-{max}之间")
	private java.lang.String title;
	/** 内容 */
	@Excel(name = "内容", width = 15)
	@NotEmpty(message = "内容不能为空")
	@Size(min = 1, max = 2000, message = "内容长度应该{min}-{max}之间")
	private java.lang.String content;
	/** 类型 */
	@Excel(name = "类型", width = 15)
	@NotEmpty(message = "类型不能为空")
	@Size(min = 2, max = 32, message = "类型长度应该{min}-{max}之间")
	private java.lang.String type;
	/** 发送模式 */
	@Excel(name = "发送模式", width = 15)
	@NotEmpty(message = "发送类型不能为空")
	@Size(min = 2, max = 32, message = "发送类型长度应该{min}-{max}之间")
	private java.lang.String sendPattern;
	/** 签发人 */
	@Excel(name = "签发人", width = 15)
	@NotEmpty(message = "签发人不能为空")
	@Size(min = 1, max = 100, message = "签发人长度应该{min}-{max}之间")
	private java.lang.String sender;
	/** 签发时间 */
	@Excel(name = "签发时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "签发时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") 
	private java.util.Date sendTime;
	/** 分组维护主表id */
	@Excel(name = "分组维护主表id", width = 15)
	@NotNull(message = "接收人不能为空")
	@Size(max = 36, message = "数据长度过长")
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

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name = "ID", nullable = false, length = 36)
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

	@Column(name = "NUMBER", nullable = false, length = 50)
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

	@Column(name = "TITLE", nullable = false, length = 100)
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

	@Column(name = "CONTENT", nullable = false, length = 2000)
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

	@Column(name = "TYPE", nullable = false, length = 32)
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

	@Column(name = "SEND_PATTERN", nullable = false, length = 32)
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

	@Column(name = "SENDER", nullable = false, length = 100)
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
	@Column(name = "SEND_TIME", nullable = false)
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

	@Column(name = "GROUP_ID", nullable = false, length = 5000)
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

	@Column(name = "CREATE_BY", nullable = true, length = 50)
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

	@Column(name = "CREATE_DATE", nullable = true, length = 20)
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

	@Column(name = "UPDATE_BY", nullable = true, length = 50)
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

	@Column(name = "UPDATE_DATE", nullable = true, length = 20)
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

	@Column(name = "STATE", nullable = true, length = 10)
	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

}