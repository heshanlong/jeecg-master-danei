package com.gbiac.fams.business.anounce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 通知通告接收表
 * @author onlineGenerator
 * @date 2019-01-23 14:07:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_anounce_receive", schema = "")
@SuppressWarnings("serial")
public class FamsAnounceReceive implements Serializable{

	/**主键*/
	private java.lang.String id;
	/**主表ID*/
	@Excel(name="通知id",width=15)
	private java.lang.String notifyId;
	/**用户ID*/
	@Excel(name="用户id",width=15)
	private java.lang.String userId;
	/**是否阅读*/
	private java.lang.String state;
	/**阅读日期*/
	private java.util.Date readTime;
	/**创建日期*/
	private java.util.Date createDate;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主表ID
	 */

	@Column(name ="notify_id",nullable=false,length=36)
	public java.lang.String getNotifyId(){
		return this.notifyId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主表ID
	 */
	public void setNotifyId(java.lang.String notifyId){
		this.notifyId = notifyId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户id
	 */

	@Column(name ="user_id",nullable=false,length=36)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户id
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  是否阅读
	 */

	@Column(name ="state",nullable=false,length=11)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  是否阅读
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  阅读日期
	 */

	@Column(name ="read_time",nullable=false,length=20)
	public java.util.Date getReadTime(){
		return this.readTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setReadTime(java.util.Date readTime){
		this.readTime = readTime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=false,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
}
