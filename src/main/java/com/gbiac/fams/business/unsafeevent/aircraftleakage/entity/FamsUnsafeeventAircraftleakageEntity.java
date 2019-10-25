package com.gbiac.fams.business.unsafeevent.aircraftleakage.entity;


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
 * @Description: fams_unsafeevent_aircraftleakage
 * @author onlineGenerator
 * @date 2019-02-14 15:27:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_unsafeevent_aircraftleakage", schema = "")
@SuppressWarnings("serial")
public class FamsUnsafeeventAircraftleakageEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String no;
	/**作业人*/
	@Excel(name="作业人",width=15)
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
	/**备注*/
	@Excel(name="备注",width=15)
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
	/**航班号*/
	@Excel(name="航班号",width=10)
	private java.lang.String flightno;
	/**航班注册号*/
	@Excel(name="航班注册号",width=10)
	private java.lang.String flightregistrationnumber;
	/**航空公司*/
	@Excel(name="航空公司",width=10)
	private java.lang.String airlines;
	/**航线*/
	@Excel(name="航线",width=10)
	private java.lang.String routes;
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
	/**通报场道时间*/
	@Excel(name="通报场道时间",width=10)
	private java.lang.String announcetracktime;
	/**通报值班领导时间*/
	@Excel(name="通报值班领导时间",width=10)
	private java.lang.String announcedutytime;
	/**通报AOC时间*/
	@Excel(name="通报AOC时间",width=10)
	private java.lang.String notificationaoctime;
	/**通报机坪时间*/
	@Excel(name="通报机坪时间",width=10)
	private java.lang.String transactiontime;
	/**漏油种类*/
	@Excel(name="漏油种类",width=10)
	private java.lang.String oiltypes;
	/**漏油种类*/
	@Excel(name="漏油种类其他",width=10)
	private java.lang.String oiltypesOther;
	/**漏油面积*/
	@Excel(name="漏油面积",width=10)
	private java.lang.String spillarea;
	/**漏油原因*/
	@Excel(name="漏油原因",width=10)
	private java.lang.String oilleakreason;
	/**漏油原因*/
	@Excel(name="漏油原因其他",width=10)
	private java.lang.String oilleakreasonOther;
	/**影响运行区域*/
	@Excel(name="影响运行区域",width=10)
	private java.lang.String affectedoperationarea;
	/**启动燃油溢漏预案*/
	@Excel(name="启动燃油溢漏预案",width=10)
	private java.lang.String initiatefuelspillplan;
	/**启动时间*/
	@Excel(name="启动时间",width=10)
	private java.lang.String startuptime;
	/**解除时间*/
	@Excel(name="解除时间",width=10)
	private java.lang.String lifttime;
	/**现场油迹清理完毕时间*/
	@Excel(name="现场油迹清理完毕时间",width=10)
	private java.lang.String oilcleaningsite;
	/**飞机推出时间*/
	@Excel(name="飞机推出时间",width=10)
	private java.lang.String aircraftlaunchtime;
	/**机位可用时间*/
	@Excel(name="机位可用时间",width=10)
	private java.lang.String availabletime;
	/**相关滑行道可用时间*/
	@Excel(name="相关滑行道可用时间",width=10)
	private java.lang.String relevanttaxiwayavailabilitytime;
	/**通报AOC时间*/
	@Excel(name="通报AOC时间",width=10)
	private java.lang.String notificationofaoctime;
	/**通报值班领导时间*/
	@Excel(name="通报值班领导时间",width=10)
	private java.lang.String announcetheleaderonduty;
	/**补充说明*/
	@Excel(name="补充说明",width=200)
	private java.lang.String added;
	/**图片路径*/
	@Excel(name="图片路径",width=25)
	private java.lang.String files;
	/**飞机自主转弯*/
	@Excel(name="飞机自主转弯",width=10)
	private java.lang.String autonomousturning;
	/**引领车辆等待位置*/
	@Excel(name="引领车辆等待位置",width=10)
	private java.lang.String guidevehicleswaitingposition;
	/**引领路线*/
	@Excel(name="引领路线",width=10)
	private java.lang.String leadline;
	/**拖车到达等待点时间*/
	@Excel(name="拖车到达等待点时间",width=10)
	private java.lang.String trailerarrivalwaitingtime;
	/**引领车辆到达等待点时间*/
	@Excel(name="引领车辆到达等待点时间",width=10)
	private java.lang.String leadvehiclethewaitingpointtime;
	/**开始引领时间*/
	@Excel(name="开始引领时间",width=10)
	private java.lang.String leadtime;
	/**飞机进位时间*/
	@Excel(name="飞机进位时间",width=10)
	private java.lang.String aircraftcarrytime;
	/**飞机拖动路线*/
	@Excel(name="飞机拖动路线",width=10)
	private java.lang.String aircraftdragroute;
	/**拖车拖动补充说明*/
	@Excel(name="拖车拖动补充说明",width=200)
	private java.lang.String trailerdragadded;
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
	
	/**检查方向*/
	@Excel(name="检查方向",width=10)
	private java.lang.String checdirection;
	
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
	 *@return: java.lang.String  备注
	 */

	@Column(name ="NOTE",nullable=true,length=255)
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

	@Column(name ="PLANNEDTIME",nullable=true,length=100)
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

	@Column(name ="PLANNEDDEPARTURE",nullable=true,length=100)
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

	@Column(name ="ACTUALTIMEARRIVAL",nullable=true,length=100)
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

	@Column(name ="ACTUALDEPARTURE",nullable=true,length=100)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  通报场道时间
	 */

	@Column(name ="ANNOUNCETRACKTIME",nullable=true,length=100)
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
	 *@return: java.util.Date  通报值班领导时间
	 */

	@Column(name ="ANNOUNCEDUTYTIME",nullable=true,length=100)
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

	@Column(name ="NOTIFICATIONAOCTIME",nullable=true,length=100)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  通报机坪时间
	 */

	@Column(name ="TRANSACTIONTIME",nullable=true,length=100)
	public java.lang.String getTransactiontime(){
		return this.transactiontime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  通报机坪时间
	 */
	public void setTransactiontime(java.lang.String transactiontime){
		this.transactiontime = transactiontime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  漏油种类
	 */

	@Column(name ="OILTYPES",nullable=true,length=100)
	public java.lang.String getOiltypes(){
		return this.oiltypes;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  漏油种类
	 */
	public void setOiltypes(java.lang.String oiltypes){
		this.oiltypes = oiltypes;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  漏油面积
	 */

	@Column(name ="SPILLAREA",nullable=true,length=100)
	public java.lang.String getSpillarea(){
		return this.spillarea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  漏油面积
	 */
	public void setSpillarea(java.lang.String spillarea){
		this.spillarea = spillarea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  漏油原因
	 */

	@Column(name ="OILLEAKREASON",nullable=true,length=100)
	public java.lang.String getOilleakreason(){
		return this.oilleakreason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  漏油原因
	 */
	public void setOilleakreason(java.lang.String oilleakreason){
		this.oilleakreason = oilleakreason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  影响运行区域
	 */

	@Column(name ="AFFECTEDOPERATIONAREA",nullable=true,length=255)
	public java.lang.String getAffectedoperationarea(){
		return this.affectedoperationarea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  影响运行区域
	 */
	public void setAffectedoperationarea(java.lang.String affectedoperationarea){
		this.affectedoperationarea = affectedoperationarea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  启动燃油溢漏预案
	 */

	@Column(name ="INITIATEFUELSPILLPLAN",nullable=true,length=255)
	public java.lang.String getInitiatefuelspillplan(){
		return this.initiatefuelspillplan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  启动燃油溢漏预案
	 */
	public void setInitiatefuelspillplan(java.lang.String initiatefuelspillplan){
		this.initiatefuelspillplan = initiatefuelspillplan;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  启动时间
	 */

	@Column(name ="STARTUPTIME",nullable=true,length=100)
	public java.lang.String getStartuptime(){
		return this.startuptime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  启动时间
	 */
	public void setStartuptime(java.lang.String startuptime){
		this.startuptime = startuptime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  解除时间
	 */

	@Column(name ="LIFTTIME",nullable=true,length=100)
	public java.lang.String getLifttime(){
		return this.lifttime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  解除时间
	 */
	public void setLifttime(java.lang.String lifttime){
		this.lifttime = lifttime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  现场油迹清理完毕时间
	 */

	@Column(name ="OILCLEANINGSITE",nullable=true,length=100)
	public java.lang.String getOilcleaningsite(){
		return this.oilcleaningsite;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  现场油迹清理完毕时间
	 */
	public void setOilcleaningsite(java.lang.String oilcleaningsite){
		this.oilcleaningsite = oilcleaningsite;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  飞机推出时间
	 */

	@Column(name ="AIRCRAFTLAUNCHTIME",nullable=true,length=100)
	public java.lang.String getAircraftlaunchtime(){
		return this.aircraftlaunchtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  飞机推出时间
	 */
	public void setAircraftlaunchtime(java.lang.String aircraftlaunchtime){
		this.aircraftlaunchtime = aircraftlaunchtime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  机位可用时间
	 */

	@Column(name ="AVAILABLETIME",nullable=true,length=100)
	public java.lang.String getAvailabletime(){
		return this.availabletime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  机位可用时间
	 */
	public void setAvailabletime(java.lang.String availabletime){
		this.availabletime = availabletime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  相关滑行道可用时间
	 */

	@Column(name ="RELEVANTTAXIWAYAVAILABILITYTIME",nullable=true,length=100)
	public java.lang.String getRelevanttaxiwayavailabilitytime(){
		return this.relevanttaxiwayavailabilitytime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  相关滑行道可用时间
	 */
	public void setRelevanttaxiwayavailabilitytime(java.lang.String relevanttaxiwayavailabilitytime){
		this.relevanttaxiwayavailabilitytime = relevanttaxiwayavailabilitytime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  通报AOC时间
	 */

	@Column(name ="NOTIFICATIONOFAOCTIME",nullable=true,length=100)
	public java.lang.String getNotificationofaoctime(){
		return this.notificationofaoctime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  通报AOC时间
	 */
	public void setNotificationofaoctime(java.lang.String notificationofaoctime){
		this.notificationofaoctime = notificationofaoctime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  通报值班领导时间
	 */

	@Column(name ="ANNOUNCETHELEADERONDUTY",nullable=true,length=100)
	public java.lang.String getAnnouncetheleaderonduty(){
		return this.announcetheleaderonduty;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  通报值班领导时间
	 */
	public void setAnnouncetheleaderonduty(java.lang.String announcetheleaderonduty){
		this.announcetheleaderonduty = announcetheleaderonduty;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  补充说明
	 */

	@Column(name ="ADDED",nullable=true,length=2000)
	public java.lang.String getAdded(){
		return this.added;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  补充说明
	 */
	public void setAdded(java.lang.String added){
		this.added = added;
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
	 *@return: java.lang.String  飞机自主转弯
	 */

	@Column(name ="AUTONOMOUSTURNING",nullable=true,length=100)
	public java.lang.String getAutonomousturning(){
		return this.autonomousturning;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  飞机自主转弯
	 */
	public void setAutonomousturning(java.lang.String autonomousturning){
		this.autonomousturning = autonomousturning;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  引领车辆等待位置
	 */

	@Column(name ="GUIDEVEHICLESWAITINGPOSITION",nullable=true,length=100)
	public java.lang.String getGuidevehicleswaitingposition(){
		return this.guidevehicleswaitingposition;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  引领车辆等待位置
	 */
	public void setGuidevehicleswaitingposition(java.lang.String guidevehicleswaitingposition){
		this.guidevehicleswaitingposition = guidevehicleswaitingposition;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  引领路线
	 */

	@Column(name ="LEADLINE",nullable=true,length=100)
	public java.lang.String getLeadline(){
		return this.leadline;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  引领路线
	 */
	public void setLeadline(java.lang.String leadline){
		this.leadline = leadline;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  拖车到达等待点时间
	 */

	@Column(name ="TRAILERARRIVALWAITINGTIME",nullable=true,length=100)
	public java.lang.String getTrailerarrivalwaitingtime(){
		return this.trailerarrivalwaitingtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  拖车到达等待点时间
	 */
	public void setTrailerarrivalwaitingtime(java.lang.String trailerarrivalwaitingtime){
		this.trailerarrivalwaitingtime = trailerarrivalwaitingtime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  引领车辆到达等待点时间
	 */

	@Column(name ="LEADVEHICLETHEWAITINGPOINTTIME",nullable=true,length=100)
	public java.lang.String getLeadvehiclethewaitingpointtime(){
		return this.leadvehiclethewaitingpointtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  引领车辆到达等待点时间
	 */
	public void setLeadvehiclethewaitingpointtime(java.lang.String leadvehiclethewaitingpointtime){
		this.leadvehiclethewaitingpointtime = leadvehiclethewaitingpointtime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始引领时间
	 */

	@Column(name ="LEADTIME",nullable=true,length=100)
	public java.lang.String getLeadtime(){
		return this.leadtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始引领时间
	 */
	public void setLeadtime(java.lang.String leadtime){
		this.leadtime = leadtime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  飞机进位时间
	 */

	@Column(name ="AIRCRAFTCARRYTIME",nullable=true,length=100)
	public java.lang.String getAircraftcarrytime(){
		return this.aircraftcarrytime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  飞机进位时间
	 */
	public void setAircraftcarrytime(java.lang.String aircraftcarrytime){
		this.aircraftcarrytime = aircraftcarrytime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  飞机拖动路线
	 */

	@Column(name ="AIRCRAFTDRAGROUTE",nullable=true,length=255)
	public java.lang.String getAircraftdragroute(){
		return this.aircraftdragroute;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  飞机拖动路线
	 */
	public void setAircraftdragroute(java.lang.String aircraftdragroute){
		this.aircraftdragroute = aircraftdragroute;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拖车拖动补充说明
	 */

	@Column(name ="TRAILERDRAGADDED",nullable=true,length=2000)
	public java.lang.String getTrailerdragadded(){
		return this.trailerdragadded;
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
	
	@Column(name ="oiltypesother",nullable=true,length=100)
	public java.lang.String getOiltypesOther() {
		return oiltypesOther;
	}

	public void setOiltypesOther(java.lang.String oiltypesOther) {
		this.oiltypesOther = oiltypesOther;
	}
	@Column(name ="oilleakreasonother",nullable=true,length=100)
	public java.lang.String getOilleakreasonOther() {
		return oilleakreasonOther;
	}

	public void setOilleakreasonOther(java.lang.String oilleakreasonOther) {
		this.oilleakreasonOther = oilleakreasonOther;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拖车拖动补充说明
	 */
	public void setTrailerdragadded(java.lang.String trailerdragadded){
		this.trailerdragadded = trailerdragadded;
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
}