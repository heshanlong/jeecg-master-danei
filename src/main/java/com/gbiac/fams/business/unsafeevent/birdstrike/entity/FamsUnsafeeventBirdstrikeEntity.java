package com.gbiac.fams.business.unsafeevent.birdstrike.entity;

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
 * @Description: fams_unsafeevent_birdstrike
 * @author onlineGenerator
 * @date 2019-02-12 16:54:50
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_unsafeevent_birdstrike", schema = "")
@SuppressWarnings("serial")
public class FamsUnsafeeventBirdstrikeEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**编号*/
	@Excel(name="编号",width=25)
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
	/**痕迹部位*/
	@Excel(name="痕迹部位",width=10)
	private java.lang.String damagedlocation;
	/**鸟尸*/
	@Excel(name="鸟尸",width=10)
	private java.lang.String abody;
	/**鸟毛*/
	@Excel(name="鸟毛",width=10)
	private java.lang.String birdfeathers;
	/**血迹*/
	@Excel(name="血迹",width=10)
	private java.lang.String blood;
	/**航空器损伤*/
	@Excel(name="航空器损伤",width=10)
	private java.lang.String aircraftdamage;
	/**损伤部位*/
	@Excel(name="损伤部位",width=10)
	private java.lang.String injury;
	/**损伤尺寸长度*/
	@Excel(name="损伤尺寸长度",width=10)
	private java.lang.String woundsizelong;
	/**损伤尺寸宽度*/
	@Excel(name="损伤尺寸宽度",width=10)
	private java.lang.String woundsizewide;
	/**损伤尺寸深度*/
	@Excel(name="损伤尺寸深度",width=10)
	private java.lang.String woundsizedeep;
	/**鸟击高度*/
	@Excel(name="鸟击高度",width=10)
	private java.lang.String birdhighly;
	@Excel(name="鸟击高度详细",width=10)
	private java.lang.String birdhighlyDetail;
	/**通报生态时间*/
	@Excel(name="通报生态时间",width=10)
	private java.lang.String notificationecologicaltime;
	/**通报值班领导时间*/
	@Excel(name="通报值班领导时间",width=10)
	private java.lang.String announcedutytime;
	/**通报AOC时间*/
	@Excel(name="通报AOC时间",width=10)
	private java.lang.String notificationaoctime;
	/**通报场道时间*/
	@Excel(name="通报场道时间",width=10)
	private java.lang.String announcetracktime;
	/**跑道检查*/
	@Excel(name="跑道检查",width=10)
	private java.lang.String checkrunway;
	/**跑道*/
	@Excel(name="跑道",width=10)
	private java.lang.String runway;
	/**检查方向*/
	@Excel(name="检查方向",width=10)
	private java.lang.String checkdirection;
	/**查道起时间*/
	@Excel(name="查道起时间",width=10)
	private java.lang.String trackstarttime;
	/**查道止时间*/
	@Excel(name="查道止时间",width=10)
	private java.lang.String trackcheckstoptime;
	/**跑道异物*/
	@Excel(name="跑道异物",width=10)
	private java.lang.String runwayforeign;
	/**事件类型*/
	@Excel(name="事件类型",width=10)
	private java.lang.String eventtype;
	/**补充说明*/
	@Excel(name="补充说明",width=10)
	private java.lang.String added;
	/**图片路径*/
	@Excel(name="图片路径",width=10)
	private java.lang.String files;
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
	 *@return: java.lang.String  痕迹部位
	 */

	@Column(name ="DAMAGEDLOCATION",nullable=true,length=100)
	public java.lang.String getDamagedlocation(){
		return this.damagedlocation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  痕迹部位
	 */
	public void setDamagedlocation(java.lang.String damagedlocation){
		this.damagedlocation = damagedlocation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  鸟尸
	 */

	@Column(name ="ABODY",nullable=true,length=100)
	public java.lang.String getAbody(){
		return this.abody;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  鸟尸
	 */
	public void setAbody(java.lang.String abody){
		this.abody = abody;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  鸟毛
	 */

	@Column(name ="BIRDFEATHERS",nullable=true,length=100)
	public java.lang.String getBirdfeathers(){
		return this.birdfeathers;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  鸟毛
	 */
	public void setBirdfeathers(java.lang.String birdfeathers){
		this.birdfeathers = birdfeathers;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  血迹
	 */

	@Column(name ="BLOOD",nullable=true,length=100)
	public java.lang.String getBlood(){
		return this.blood;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  血迹
	 */
	public void setBlood(java.lang.String blood){
		this.blood = blood;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航空器损伤
	 */

	@Column(name ="AIRCRAFTDAMAGE",nullable=true,length=100)
	public java.lang.String getAircraftdamage(){
		return this.aircraftdamage;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航空器损伤
	 */
	public void setAircraftdamage(java.lang.String aircraftdamage){
		this.aircraftdamage = aircraftdamage;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  损伤部位
	 */

	@Column(name ="INJURY",nullable=true,length=255)
	public java.lang.String getInjury(){
		return this.injury;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  损伤部位
	 */
	public void setInjury(java.lang.String injury){
		this.injury = injury;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  损伤尺寸长度
	 */

	@Column(name ="WOUNDSIZELONG",nullable=true,length=100)
	public java.lang.String getWoundsizelong(){
		return this.woundsizelong;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  损伤尺寸长度
	 */
	public void setWoundsizelong(java.lang.String woundsizelong){
		this.woundsizelong = woundsizelong;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  损伤尺寸宽度
	 */

	@Column(name ="WOUNDSIZEWIDE",nullable=true,length=100)
	public java.lang.String getWoundsizewide(){
		return this.woundsizewide;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  损伤尺寸宽度
	 */
	public void setWoundsizewide(java.lang.String woundsizewide){
		this.woundsizewide = woundsizewide;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  损伤尺寸深度
	 */

	@Column(name ="WOUNDSIZEDEEP",nullable=true,length=100)
	public java.lang.String getWoundsizedeep(){
		return this.woundsizedeep;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  损伤尺寸深度
	 */
	public void setWoundsizedeep(java.lang.String woundsizedeep){
		this.woundsizedeep = woundsizedeep;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  鸟击高度
	 */

	@Column(name ="BIRDHIGHLY",nullable=true,length=100)
	public java.lang.String getBirdhighly(){
		return this.birdhighly;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  鸟击高度详细
	 */
	public void setBirdhighlyDetail(java.lang.String birdhighlyDetail){
		this.birdhighlyDetail = birdhighlyDetail;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  鸟击高度详细
	 */

	@Column(name ="birdhighlyDetail",nullable=true,length=100)
	public java.lang.String getBirdhighlyDetail(){
		return this.birdhighlyDetail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  鸟击高度
	 */
	public void setBirdhighly(java.lang.String birdhighly){
		this.birdhighly = birdhighly;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  通报生态时间
	 */

	@Column(name ="NOTIFICATIONECOLOGICALTIME",nullable=true)
	public java.lang.String getNotificationecologicaltime(){
		return this.notificationecologicaltime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  通报生态时间
	 */
	public void setNotificationecologicaltime(java.lang.String notificationecologicaltime){
		this.notificationecologicaltime = notificationecologicaltime;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跑道检查
	 */

	@Column(name ="CHECKRUNWAY",nullable=true,length=100)
	public java.lang.String getCheckrunway(){
		return this.checkrunway;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  跑道检查
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查方向
	 */

	@Column(name ="CHECKDIRECTION",nullable=true,length=100)
	public java.lang.String getCheckdirection(){
		return this.checkdirection;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查方向
	 */
	public void setCheckdirection(java.lang.String checkdirection){
		this.checkdirection = checkdirection;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  查道起时间
	 */

	@Column(name ="TRACKSTARTTIME",nullable=true)
	public java.lang.String getTrackstarttime(){
		return this.trackstarttime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  查道起时间
	 */
	public void setTrackstarttime(java.lang.String trackstarttime){
		this.trackstarttime = trackstarttime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  查道止时间
	 */

	@Column(name ="TRACKCHECKSTOPTIME",nullable=true)
	public java.lang.String getTrackcheckstoptime(){
		return this.trackcheckstoptime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  查道止时间
	 */
	public void setTrackcheckstoptime(java.lang.String trackcheckstoptime){
		this.trackcheckstoptime = trackcheckstoptime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跑道异物
	 */

	@Column(name ="RUNWAYFOREIGN",nullable=true,length=100)
	public java.lang.String getRunwayforeign(){
		return this.runwayforeign;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  跑道异物
	 */
	public void setRunwayforeign(java.lang.String runwayforeign){
		this.runwayforeign = runwayforeign;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  事件类型
	 */

	@Column(name ="EVENTTYPE",nullable=true,length=255)
	public java.lang.String getEventtype(){
		return this.eventtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  事件类型
	 */
	public void setEventtype(java.lang.String eventtype){
		this.eventtype = eventtype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  补充说明
	 */

	@Column(name ="ADDED",nullable=true,length=1000)
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