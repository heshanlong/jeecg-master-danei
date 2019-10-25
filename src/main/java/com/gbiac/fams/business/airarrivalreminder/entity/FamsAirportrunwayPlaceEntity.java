package com.gbiac.fams.business.airarrivalreminder.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 航班入位提醒
 * @author onlineGenerator
 * @date 2019-08-06 18:00:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_airportrunway_place", schema = "")
@SuppressWarnings("serial")
public class FamsAirportrunwayPlaceEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.Integer numbering;
	/**区域*/
	@Excel(name="区域",width=15)
	private java.lang.String area;
	/**机位*/
	@Excel(name="机位",width=15)
	private java.lang.String position;
	/**航班号*/
	@Excel(name="航班号",width=15)
	private java.lang.String flightNumber;
	/**预计落地时间*/
	@Excel(name="预计落地时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date landingTime;
	/**接收人*/
	@Excel(name="接收人",width=15)
	private java.lang.String receiver;
	/**角色*/
	@Excel(name="角色",width=15)
	private java.lang.String role;
	/**发送时间*/
	@Excel(name="发送时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date sendingTime;
	/**提醒时间设置*/
	private java.lang.String reminderTime;
	
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
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
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
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
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
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
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
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */

	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  编号
	 */

	@Column(name ="NUMBERING",nullable=true,length=32)
	public java.lang.Integer getNumbering(){
		return this.numbering;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  编号
	 */
	public void setNumbering(java.lang.Integer numbering){
		this.numbering = numbering;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  区域
	 */

	@Column(name ="AREA",nullable=true,length=32)
	public java.lang.String getArea(){
		return this.area;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  区域
	 */
	public void setArea(java.lang.String area){
		this.area = area;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机位
	 */

	@Column(name ="POSITION",nullable=true,length=32)
	public java.lang.String getPosition(){
		return this.position;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机位
	 */
	public void setPosition(java.lang.String position){
		this.position = position;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班号
	 */

	@Column(name ="FLIGHT_NUMBER",nullable=true,length=32)
	public java.lang.String getFlightNumber(){
		return this.flightNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班号
	 */
	public void setFlightNumber(java.lang.String flightNumber){
		this.flightNumber = flightNumber;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预计落地时间
	 */

	@Column(name ="LANDING_TIME",nullable=true,length=32)
	public java.util.Date getLandingTime(){
		return this.landingTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预计落地时间
	 */
	public void setLandingTime(java.util.Date landingTime){
		this.landingTime = landingTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  接收人
	 */

	@Column(name ="RECEIVER",nullable=true,length=32)
	public java.lang.String getReceiver(){
		return this.receiver;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  接收人
	 */
	public void setReceiver(java.lang.String receiver){
		this.receiver = receiver;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  角色
	 */

	@Column(name ="ROLE",nullable=true,length=32)
	public java.lang.String getRole(){
		return this.role;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  角色
	 */
	public void setRole(java.lang.String role){
		this.role = role;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发送时间
	 */

	@Column(name ="SENDING_TIME",nullable=true,length=32)
	public java.util.Date getSendingTime(){
		return this.sendingTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发送时间
	 */
	public void setSendingTime(java.util.Date sendingTime){
		this.sendingTime = sendingTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提醒时间设置
	 */

	@Column(name ="REMINDER_TIME",nullable=true,length=32)
	public java.lang.String getReminderTime(){
		return this.reminderTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提醒时间设置
	 */
	public void setReminderTime(java.lang.String reminderTime){
		this.reminderTime = reminderTime;
	}
}
