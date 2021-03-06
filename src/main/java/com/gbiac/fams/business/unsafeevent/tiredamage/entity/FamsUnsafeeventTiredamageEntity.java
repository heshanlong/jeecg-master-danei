package com.gbiac.fams.business.unsafeevent.tiredamage.entity;

//package com.gbiac.fams.business.unsafeevent.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;

/**   
 * @Title: Entity
 * @Description: fams_unsafeevent_tiredamage
 * @author onlineGenerator
 * @date 2019-02-01 10:26:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_unsafeevent_tiredamage", schema = "")
@SuppressWarnings("serial")
public class FamsUnsafeeventTiredamageEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String no;
	/**作业人*/
	@Excel(name="作业人",width=25)
	private java.lang.String people;
	/**日期*/
	@Excel(name="日期",width=10)
	private java.lang.String thedate;
	/**接报时间*/
	@Excel(name="接报时间",width=10)
	private java.lang.String settime;
	/**信息来源*/
	@Excel(name="信息来源",width=10)
	private java.lang.String sourceformation;
	/**航空公司*/
	@Excel(name="航空公司",width=10)
	private java.lang.String airlines;
	/**图片路径*/
	@Excel(name="图片路径",width=10)
	private java.lang.String files;
	/**备注*/
	@Excel(name="备注",width=20)
	private java.lang.String note;
	/**创建人登录名称*/
	@Excel(name="创建人登录名称",width=15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人登录名称*/
	@Excel(name="更新人登录名称",width=15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name="更新日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**航线*/
	@Excel(name="航线",width=10)
	private java.lang.String routes;
	/**航班号*/
	@Excel(name="航班号",width=10)
	private java.lang.String flightno;
	/**航班注册号*/
	@Excel(name="航班注册号",width=10)
	private java.lang.String flightregistrationnumber;
	/**机型*/
	@Excel(name="机型",width=10)
	private java.lang.String models;
	/**机号*/
	@Excel(name="机号",width=10)
	private java.lang.String immediately;
	/**停机位*/
	@Excel(name="停机位",width=10)
	private java.lang.String slots;
	/**计划进港时间*/
	@Excel(name="计划进港时间",width=10)
	private java.lang.String plannedtime;
	/**计划出港时间*/
	@Excel(name="计划出港时间",width=10)
	private java.lang.String planneddeparture;
	/**实际进港时间*/
	@Excel(name="实际进港时间",width=10)
	private java.lang.String actualtimearrival;
	/**实际出港时间*/
	@Excel(name="实际出港时间",width=10)
	private java.lang.String actualdeparture;
	/**受损位置*/
	@Excel(name="受损位置",width=100)
	private java.lang.String damagedlocation;
	
	/**伤口尺寸长度*/
	@Excel(name="伤口尺寸长度",width=10)
	private java.lang.String woundsizelong;
	/**伤口尺寸宽度*/
	@Excel(name="伤口尺寸宽度",width=10)
	private java.lang.String woundsizewide;
	/**伤口尺寸深度*/
	@Excel(name="伤口尺寸深度",width=10)
	private java.lang.String woundsizedeep;
	
	/**伤口受损尺寸（格式：长度，宽度，深度）*/
	@Excel(name="受损尺寸", width=20)
	private java.lang.String woundsize;
	/**受损尺寸的数量*/
	private int nums;
	/**用来存储单独的受损尺寸*/
	private java.lang.String[] damagedlocations;
//	/**受损尺寸位置编号*/
//	private java.lang.String damagedlocationid;
//	/**受损尺寸子位置*/
//	private java.lang.String damagedlocationchild;
//	/**受损尺寸子位置长*/
//	private java.lang.String woundsizelongchild;
//	/**受损尺寸子位置宽*/
//	private java.lang.String woundsizewidechild;
//	/**受损尺寸子位置深*/
//	private java.lang.String woundsizedeepchild;
	
	/**有无携带外来物*/
	@Excel(name="有无携带外来物",width=10)
	private java.lang.String foreignmatter;
	/**翻新胎*/
	@Excel(name="翻新胎",width=10)
	private java.lang.String renovationtire;
	/**通报场道时间*/
	@Excel(name="通报场道时间",width=10)
	private java.lang.String announcetracktime;
	/**场道反馈时间*/
	@Excel(name="场道反馈时间",width=10)
	private java.lang.String trackfeedbacktime;
	/**通报值班领导时间*/
	@Excel(name="通报值班领导时间",width=10)
	private java.lang.String announcedutytime;
	/**通报AOC时间*/
	@Excel(name="通报AOC时间",width=10)
	private java.lang.String notificationaoctime;
	/**影响后续航班*/
	@Excel(name="影响后续航班",width=10)
	private java.lang.String impactsubsequentflights;
	/**判定结果*/
	@Excel(name="判定结果",width=10)
	private java.lang.String determineresults;
	/**本场运行路线*/
	@Excel(name="本场运行路线",width=10)
	private java.lang.String localroute;
	/**检查跑道*/
	@Excel(name="检查跑道",width=10)
	private java.lang.String checkrunway;
	/**跑道*/
	@Excel(name="跑道",width=10)
	private java.lang.String runway;
	/**检查方向*/
	@Excel(name="检查方向",width=10)
	private java.lang.String checdirection;

	/**场道查道起时间*/
	@Excel(name="场道查道起时间",width=10)
	private java.lang.String trackstarttime;
	/**场道查道止时间*/
	@Excel(name="场道查道止时间",width=10)
	private java.lang.String trackcheckstoptime;
	/**查道结果*/
	@Excel(name="查道结果",width=25)
	private java.lang.String checkresults;
	/**后续航班计划*/
	@Excel(name="后续航班计划",width=10)
	private java.lang.String followupflightschedule;
	/**后续航线*/
	@Excel(name="后续航线",width=10)
	private java.lang.String subsequentcourse;
	/**后续计划出港时间*/
	@Excel(name="后续计划出港时间",width=10)
	private java.lang.String subsequentplanneddeparture;
	/**后续实际出港时间*/
	@Excel(name="后续实际出港时间",width=10)
	private java.lang.String subsequentactualdeparture;
	/**延误时间*/
	@Excel(name="延误时间",width=10)
	private java.lang.String delaytime;
	/**补充说明*/
	@Excel(name="补充说明",width=200)
	private java.lang.String added;
	/**出发城市编码*/
	@Excel(name="出发城市编码",width=10)
	private java.lang.String starstation;
	/**出发城市中文名*/
	@Excel(name="出发城市中文名",width=10)
	private java.lang.String starstationcn;
	/**到达城市编码*/
	@Excel(name="到达城市编码",width=10)
	private java.lang.String terminalstation;
	/**到达城市中文名*/
	@Excel(name="到达城市中文名",width=10)
	private java.lang.String terminalstationcn;
	/**航空公司中文名*/
	@Excel(name="航空公司中文名",width=10)
	private java.lang.String airlinescn;
	/**是否关闭*/
	@Excel(name="是否关闭",width=10)
	private java.lang.String cloes;

	/**是否删除*/
	@Excel(name="是否删除",width=10)
	private java.lang.String del;
	private List<FamsCommonFileEntity> file;
	
	private long settimelong;
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
	 *@return: java.lang.String  编号
	 */

	@Column(name ="NO",nullable=true,length=255)
	public java.lang.String getNo(){
		return this.no;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setNo(java.lang.String no){
		this.no = no;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业人
	 */

	@Column(name ="PEOPLE",nullable=true,length=255)
	public java.lang.String getPeople(){
		return this.people;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业人
	 */
	public void setPeople(java.lang.String people){
		this.people = people;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  日期
	 */
	@Column(name ="THEDATE",nullable=true)
	public java.lang.String getThedate(){
		return this.thedate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  日期
	 */
	public void setThedate(java.lang.String thedate){
		this.thedate = thedate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  接报时间
	 */

	@Column(name ="SETTIME",nullable=true)
	public java.lang.String getSettime(){
		return this.settime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  接报时间
	 */
	public void setSettime(java.lang.String settime){
		this.settime = settime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信息来源
	 */

	@Column(name ="SOURCEFORMATION",nullable=true,length=1000)
	public java.lang.String getSourceformation(){
		return this.sourceformation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信息来源
	 */
	public void setSourceformation(java.lang.String sourceformation){
		this.sourceformation = sourceformation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航空公司
	 */

	@Column(name ="AIRLINES",nullable=true,length=100)
	public java.lang.String getAirlines(){
		return this.airlines;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航空公司
	 */
	public void setAirlines(java.lang.String airlines){
		this.airlines = airlines;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片路径
	 */

	@Column(name ="FILES",nullable=true,length=255)
	public java.lang.String getFiles(){
		return this.files;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片路径
	 */
	public void setFiles(java.lang.String files){
		this.files = files;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="NOTE",nullable=true,length=2000)
	public java.lang.String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setNote(java.lang.String note){
		this.note = note;
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

	@Column(name ="CREATE_DATE",nullable=true)
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

	@Column(name ="UPDATE_DATE",nullable=true)
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
	 *@return: java.lang.String  航线
	 */

	@Column(name ="ROUTES",nullable=true,length=100)
	public java.lang.String getRoutes(){
		return this.routes;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航线
	 */
	public void setRoutes(java.lang.String routes){
		this.routes = routes;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班号
	 */

	@Column(name ="FLIGHTNO",nullable=true,length=100)
	public java.lang.String getFlightno(){
		return this.flightno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班号
	 */
	public void setFlightno(java.lang.String flightno){
		this.flightno = flightno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班注册号
	 */

	@Column(name ="FLIGHTREGISTRATIONNUMBER",nullable=true,length=100)
	public java.lang.String getFlightregistrationnumber(){
		return this.flightregistrationnumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班注册号
	 */
	public void setFlightregistrationnumber(java.lang.String flightregistrationnumber){
		this.flightregistrationnumber = flightregistrationnumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机型
	 */

	@Column(name ="MODELS",nullable=true,length=100)
	public java.lang.String getModels(){
		return this.models;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机型
	 */
	public void setModels(java.lang.String models){
		this.models = models;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机号
	 */

	@Column(name ="IMMEDIATELY",nullable=true,length=100)
	public java.lang.String getImmediately(){
		return this.immediately;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机号
	 */
	public void setImmediately(java.lang.String immediately){
		this.immediately = immediately;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  停机位
	 */

	@Column(name ="SLOTS",nullable=true,length=100)
	public java.lang.String getSlots(){
		return this.slots;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  停机位
	 */
	public void setSlots(java.lang.String slots){
		this.slots = slots;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计划进港时间
	 */

	@Column(name ="PLANNEDTIME",nullable=true)
	public java.lang.String getPlannedtime(){
		return this.plannedtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划进港时间
	 */
	public void setPlannedtime(java.lang.String plannedtime){
		this.plannedtime = plannedtime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计划出港时间
	 */

	@Column(name ="PLANNEDDEPARTURE",nullable=true)
	public java.lang.String getPlanneddeparture(){
		return this.planneddeparture;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划出港时间
	 */
	public void setPlanneddeparture(java.lang.String planneddeparture){
		this.planneddeparture = planneddeparture;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  实际进港时间
	 */

	@Column(name ="ACTUALTIMEARRIVAL",nullable=true)
	public java.lang.String getActualtimearrival(){
		return this.actualtimearrival;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  实际进港时间
	 */
	public void setActualtimearrival(java.lang.String actualtimearrival){
		this.actualtimearrival = actualtimearrival;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  实际出港时间
	 */

	@Column(name ="ACTUALDEPARTURE",nullable=true)
	public java.lang.String getActualdeparture(){
		return this.actualdeparture;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  实际出港时间
	 */
	public void setActualdeparture(java.lang.String actualdeparture){
		this.actualdeparture = actualdeparture;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  受损位置
	 */

	@Column(name ="DAMAGEDLOCATION",nullable=true,length=1000)
	public java.lang.String getDamagedlocation(){
		//受损位置格式化
		return this.damagedlocation.replaceAll(",", "/");
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  受损位置
	 */
	public void setDamagedlocation(java.lang.String damagedlocation){
		this.damagedlocation = damagedlocation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  伤口尺寸长度
	 */

	@Column(name ="WOUNDSIZELONG",nullable=true,length=100)
	public java.lang.String getWoundsizelong(){
		return this.woundsizelong;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  伤口尺寸长度
	 */
	public void setWoundsizelong(java.lang.String woundsizelong){
		this.woundsizelong = woundsizelong;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  伤口尺寸宽度
	 */

	@Column(name ="WOUNDSIZEWIDE",nullable=true,length=100)
	public java.lang.String getWoundsizewide(){
		return this.woundsizewide;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  伤口尺寸宽度
	 */
	public void setWoundsizewide(java.lang.String woundsizewide){
		this.woundsizewide = woundsizewide;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  伤口尺寸深度
	 */

	@Column(name ="WOUNDSIZEDEEP",nullable=true,length=100)
	public java.lang.String getWoundsizedeep(){
		return this.woundsizedeep;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  伤口尺寸深度
	 */
	public void setWoundsizedeep(java.lang.String woundsizedeep){
		this.woundsizedeep = woundsizedeep;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  有无携带外来物
	 */

	@Column(name ="FOREIGNMATTER",nullable=true,length=100)
	public java.lang.String getForeignmatter(){
		return this.foreignmatter;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  有无携带外来物
	 */
	public void setForeignmatter(java.lang.String foreignmatter){
		this.foreignmatter = foreignmatter;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  翻新胎
	 */

	@Column(name ="RENOVATIONTIRE",nullable=true,length=100)
	public java.lang.String getRenovationtire(){
		return this.renovationtire;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  翻新胎
	 */
	public void setRenovationtire(java.lang.String renovationtire){
		this.renovationtire = renovationtire;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  通报场道时间
	 */

	@Column(name ="ANNOUNCETRACKTIME",nullable=true)
	public java.lang.String getAnnouncetracktime(){
		return this.announcetracktime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  通报场道时间
	 */
	public void setAnnouncetracktime(java.lang.String announcetracktime){
		this.announcetracktime = announcetracktime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  场道反馈时间
	 */

	@Column(name ="TRACKFEEDBACKTIME",nullable=true)
	public java.lang.String getTrackfeedbacktime(){
		return this.trackfeedbacktime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  场道反馈时间
	 */
	public void setTrackfeedbacktime(java.lang.String trackfeedbacktime){
		this.trackfeedbacktime = trackfeedbacktime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  通报值班领导时间
	 */

	@Column(name ="ANNOUNCEDUTYTIME",nullable=true)
	public java.lang.String getAnnouncedutytime(){
		return this.announcedutytime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  通报值班领导时间
	 */
	public void setAnnouncedutytime(java.lang.String announcedutytime){
		this.announcedutytime = announcedutytime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  通报AOC时间
	 */

	@Column(name ="NOTIFICATIONAOCTIME",nullable=true)
	public java.lang.String getNotificationaoctime(){
		return this.notificationaoctime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  通报AOC时间
	 */
	public void setNotificationaoctime(java.lang.String notificationaoctime){
		this.notificationaoctime = notificationaoctime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  影响后续航班
	 */

	@Column(name ="IMPACTSUBSEQUENTFLIGHTS",nullable=true,length=100)
	public java.lang.String getImpactsubsequentflights(){
		return this.impactsubsequentflights;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  影响后续航班
	 */
	public void setImpactsubsequentflights(java.lang.String impactsubsequentflights){
		this.impactsubsequentflights = impactsubsequentflights;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  判定结果
	 */

	@Column(name ="DETERMINERESULTS",nullable=true,length=255)
	public java.lang.String getDetermineresults(){
		return this.determineresults;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  判定结果
	 */
	public void setDetermineresults(java.lang.String determineresults){
		this.determineresults = determineresults;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  本场运行路线
	 */

	@Column(name ="LOCALROUTE",nullable=true,length=255)
	public java.lang.String getLocalroute(){
		return this.localroute;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  本场运行路线
	 */
	public void setLocalroute(java.lang.String localroute){
		this.localroute = localroute;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查跑道
	 */

	@Column(name ="CHECKRUNWAY",nullable=true,length=100)
	public java.lang.String getCheckrunway(){
		return this.checkrunway;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查跑道
	 */
	public void setCheckrunway(java.lang.String checkrunway){
		this.checkrunway = checkrunway;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跑道
	 */

	@Column(name ="RUNWAY",nullable=true,length=100)
	public java.lang.String getRunway(){
		return this.runway;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  跑道
	 */
	public void setRunway(java.lang.String runway){
		this.runway = runway;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  场道查道起时间
	 */

	@Column(name ="TRACKSTARTTIME",nullable=true)
	public java.lang.String getTrackstarttime(){
		return this.trackstarttime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  场道查道起时间
	 */
	public void setTrackstarttime(java.lang.String trackstarttime){
		this.trackstarttime = trackstarttime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  场道查道止时间
	 */

	@Column(name ="TRACKCHECKSTOPTIME",nullable=true)
	public java.lang.String getTrackcheckstoptime(){
		return this.trackcheckstoptime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  场道查道止时间
	 */
	public void setTrackcheckstoptime(java.lang.String trackcheckstoptime){
		this.trackcheckstoptime = trackcheckstoptime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  查道结果
	 */

	@Column(name ="CHECKRESULTS",nullable=true,length=100)
	public java.lang.String getCheckresults(){
		return this.checkresults;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  查道结果
	 */
	public void setCheckresults(java.lang.String checkresults){
		this.checkresults = checkresults;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  后续航班计划
	 */

	@Column(name ="FOLLOWUPFLIGHTSCHEDULE",nullable=true,length=255)
	public java.lang.String getFollowupflightschedule(){
		return this.followupflightschedule;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  后续航班计划
	 */
	public void setFollowupflightschedule(java.lang.String followupflightschedule){
		this.followupflightschedule = followupflightschedule;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  后续航线
	 */

	@Column(name ="SUBSEQUENTCOURSE",nullable=true,length=100)
	public java.lang.String getSubsequentcourse(){
		return this.subsequentcourse;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  后续航线
	 */
	public void setSubsequentcourse(java.lang.String subsequentcourse){
		this.subsequentcourse = subsequentcourse;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  后续计划出港时间
	 */

	@Column(name ="SUBSEQUENTPLANNEDDEPARTURE",nullable=true)
	public java.lang.String getSubsequentplanneddeparture(){
		return this.subsequentplanneddeparture;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  后续计划出港时间
	 */
	public void setSubsequentplanneddeparture(java.lang.String subsequentplanneddeparture){
		this.subsequentplanneddeparture = subsequentplanneddeparture;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  后续实际出港时间
	 */

	@Column(name ="SUBSEQUENTACTUALDEPARTURE",nullable=true)
	public java.lang.String getSubsequentactualdeparture(){
		return this.subsequentactualdeparture;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  后续实际出港时间
	 */
	public void setSubsequentactualdeparture(java.lang.String subsequentactualdeparture){
		this.subsequentactualdeparture = subsequentactualdeparture;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  延误时间
	 */

	@Column(name ="DELAYTIME",nullable=true,length=100)
	public java.lang.String getDelaytime(){
		return this.delaytime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  延误时间
	 */
	public void setDelaytime(java.lang.String delaytime){
		this.delaytime = delaytime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  补充说明
	 */

	@Column(name ="ADDED",nullable=true,length=2000)
	public java.lang.String getAdded(){
		return this.added;
	}

	@Column(name ="starstation",nullable=true,length=100)
	public java.lang.String getStarstation() {
		return starstation;
	}

	public void setStarstation(java.lang.String starstation) {
		this.starstation = starstation;
	}
	@Column(name ="starstationcn",nullable=true,length=100)
	public java.lang.String getStarstationcn() {
		return starstationcn;
	}

	public void setStarstationcn(java.lang.String starstationcn) {
		this.starstationcn = starstationcn;
	}
	@Column(name ="terminalstation",nullable=true,length=100)
	public java.lang.String getTerminalstation() {
		return terminalstation;
	}

	public void setTerminalstation(java.lang.String terminalstation) {
		this.terminalstation = terminalstation;
	}
	@Column(name ="terminalstationcn",nullable=true,length=100)
	public java.lang.String getTerminalstationcn() {
		return terminalstationcn;
	}

	public void setTerminalstationcn(java.lang.String terminalstationcn) {
		this.terminalstationcn = terminalstationcn;
	}
	
	@Column(name ="airlinescn",nullable=true,length=100)
	public java.lang.String getAirlinescn() {
		return airlinescn;
	}

	public void setAirlinescn(java.lang.String airlinescn) {
		this.airlinescn = airlinescn;
	}
	
	@Column(name ="cloes",nullable=true,length=100)
	public java.lang.String getCloes() {
		return cloes;
	}

	public void setCloes(java.lang.String cloes) {
		this.cloes = cloes;
	}
	@Column(name ="checdirection",nullable=true,length=100)
	public java.lang.String getChecdirection() {
		return checdirection;
	}

	public void setChecdirection(java.lang.String checdirection) {
		this.checdirection = checdirection;
	}
	@Column(name ="del",nullable=true,length=100)
	public java.lang.String getDel() {
		return del;
	}

	public void setDel(java.lang.String del) {
		this.del = del;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  补充说明
	 */
	public void setAdded(java.lang.String added){
		this.added = added;
	}
	@Transient
	public List<FamsCommonFileEntity> getFile() {
		return file;
	}

	public void setFile(List<FamsCommonFileEntity> file) {
		this.file = file;
	}
	@Transient
	public long getSettimelong() {
		return settimelong;
	}

	public void setSettimelong(long settimelong) {
		this.settimelong = settimelong;
	}
	//获取受损位置尺寸，格式化
	@Transient
	public String getWoundsize(){
			String longs[] = woundsizelong.split(",");
			String wides[] = woundsizewide.split(",");
			String deeps[] = woundsizedeep.split(",");
			StringBuilder str = new StringBuilder();
			StringBuffer str1 = null;
			for(int i=0;i<longs.length;i++){
				str1 = new StringBuffer();
				//可能存在的问题，有空项
				str1.append(longs[i]+","+wides[i]+","+deeps[i]);
				str.append(str1);
				if(i<longs.length-1){
					str.append("/");
				}
			}
			return str.toString();	
	}
	@Transient
	public int getNums() {
		damagedlocations = damagedlocation.split("[,\\/]");
		return damagedlocations.length;
	}	
	@Transient
	public java.lang.String[] getDamagedlocations() {
		return damagedlocation.split("[,\\/]");
	}	
}