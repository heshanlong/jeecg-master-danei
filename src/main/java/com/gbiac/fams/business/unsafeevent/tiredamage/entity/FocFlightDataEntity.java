package com.gbiac.fams.business.unsafeevent.tiredamage.entity;

//package com.gbiac.fams.business.unsafeevent.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: foc_flight_data
 * @author onlineGenerator
 * @date 2019-02-01 15:59:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_flight_data", schema = "")
@SuppressWarnings("serial")
public class FocFlightDataEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**类型数据*/
	@Excel(name="类型数据",width=15)
	private java.lang.String dataId;
	/**航班id*/
	@Excel(name="航班id",width=15)
	private java.lang.Integer flightid;
	/**航班ffid*/
	@Excel(name="航班ffid",width=15)
	private java.lang.String ffid;
	/**关联航班id*/
	@Excel(name="关联航班id",width=15)
	private java.lang.Integer afid;
	/**航空公司二字码*/
	@Excel(name="航空公司二字码",width=15)
	private java.lang.String twocharcode;
	/**航空公司简称*/
	@Excel(name="航空公司简称",width=15)
	private java.lang.String airlinescn;
	/**航班号*/
	@Excel(name="航班号",width=15)
	private java.lang.String flightno;
	/**进出 A,D*/
	@Excel(name="进出 A,D",width=15)
	private java.lang.String isoffin;
	/**航班任务*/
	@Excel(name="航班任务",width=15)
	private java.lang.String task;
	/**航班中文*/
	@Excel(name="航班中文",width=15)
	private java.lang.String taskcn;
	/**属性*/
	@Excel(name="属性",width=15)
	private java.lang.String attribute;
	/**航线*/
	@Excel(name="航线",width=15)
	private java.lang.String airlinefullcn;
	/**起始站三字码*/
	@Excel(name="起始站三字码",width=15)
	private java.lang.String starstation;
	/**起始站中文简称*/
	@Excel(name="起始站中文简称",width=15)
	private java.lang.String starstationcn;
	/**目的站三字码*/
	@Excel(name="目的站三字码",width=15)
	private java.lang.String terminalstation;
	/**目的站中文简称*/
	@Excel(name="目的站中文简称",width=15)
	private java.lang.String terminalstationcn;
	/**经停站三字码*/
	@Excel(name="经停站三字码",width=15)
	private java.lang.String viastation;
	/**经停站中文简称*/
	@Excel(name="经停站中文简称",width=15)
	private java.lang.String viastationcn;
	/**主航班号*/
	@Excel(name="主航班号",width=15)
	private java.lang.String mflightno;
	/**机型*/
	@Excel(name="机型",width=15)
	private java.lang.String crafttype;
	/**机号*/
	@Excel(name="机号",width=15)
	private java.lang.String craftno;
	/**航班状态*/
	@Excel(name="航班状态",width=15)
	private java.lang.String flightstate;
	/**航班异常状态*/
	@Excel(name="航班异常状态",width=15)
	private java.lang.String abnormalstate;
	/**航班异常原因*/
	@Excel(name="航班异常原因",width=15)
	private java.lang.String abnormalreason;
	/**登机时刻*/
	@Excel(name="登机时刻",width=15)
	private java.lang.String boardingtime;
	/**登机结束时刻*/
	@Excel(name="登机结束时刻",width=15)
	private java.lang.String endboardingtime;
	/**备降站三字码*/
	@Excel(name="备降站三字码",width=15)
	private java.lang.String divertstation;
	/**备降站中文简称*/
	@Excel(name="备降站中文简称",width=15)
	private java.lang.String divertstationcn;
	/**VIP：Y，N*/
	@Excel(name="VIP：Y，N",width=15)
	private java.lang.String vip;
	/**共享航班号*/
	@Excel(name="共享航班号",width=15)
	private java.lang.String shareflightno;
	/**前站计划起飞时间*/
	@Excel(name="前站计划起飞时间",width=15)
	private java.lang.String onrst;
	/**前站实际起飞时间*/
	@Excel(name="前站实际起飞时间",width=15)
	private java.lang.String onrat;
	/**航站楼*/
	@Excel(name="航站楼",width=15)
	private java.lang.String terminal;
	/**计划起飞时间*/
	@Excel(name="计划起飞时间",width=15)
	private java.lang.String sst;
	/**预计起飞时间*/
	@Excel(name="预计起飞时间",width=15)
	private java.lang.String set2;
	/**实际起飞时间*/
	@Excel(name="实际起飞时间",width=15)
	private java.lang.String sat;
	/**计划落地时间*/
	@Excel(name="计划落地时间",width=15)
	private java.lang.String est;
	/**预计落地时间*/
	@Excel(name="预计落地时间",width=15)
	private java.lang.String eet;
	/**实际落地时间*/
	@Excel(name="实际落地时间",width=15)
	private java.lang.String eat;
	/**登机口*/
	@Excel(name="登机口",width=15)
	private java.lang.String gate;
	/**行李提取转盘*/
	@Excel(name="行李提取转盘",width=15)
	private java.lang.String belt;
	/**到达出口*/
	@Excel(name="到达出口",width=15)
	private java.lang.String exit2;
	/**机位*/
	@Excel(name="机位",width=15)
	private java.lang.String craftsite;
	/**保障节点*/
	@Excel(name="保障节点",width=15)
	private java.lang.String dyndispatch;
	/**创建时间*/
	@Excel(name="创建时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date creationtime;
	/**实体仓库代码*/
	@Excel(name="实体仓库代码",width=15)
	private java.lang.String depnormal;
	/**Y正在保障，N未保障*/
	@Excel(name="Y正在保障，N未保障",width=15)
	private java.lang.String service;
	/**欲达时间*/
	@Excel(name="欲达时间",width=15)
	private java.lang.String amaneta;
	/**cobt*/
	@Excel(name="cobt",width=15)
	private java.lang.String cobt;
	/**ctot*/
	@Excel(name="ctot",width=15)
	private java.lang.String ctot;
	/**跑道*/
	@Excel(name="跑道",width=15)
	private java.lang.String runway;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型数据
	 */

	@Column(name ="DATA_ID",nullable=true,length=50)
	public java.lang.String getDataId(){
		return this.dataId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型数据
	 */
	public void setDataId(java.lang.String dataId){
		this.dataId = dataId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  航班id
	 */

	@Column(name ="FLIGHTID",nullable=true,length=10)
	public java.lang.Integer getFlightid(){
		return this.flightid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  航班id
	 */
	public void setFlightid(java.lang.Integer flightid){
		this.flightid = flightid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班ffid
	 */

	@Column(name ="FFID",nullable=true,length=64)
	public java.lang.String getFfid(){
		return this.ffid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班ffid
	 */
	public void setFfid(java.lang.String ffid){
		this.ffid = ffid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  关联航班id
	 */

	@Column(name ="AFID",nullable=true,length=10)
	public java.lang.Integer getAfid(){
		return this.afid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  关联航班id
	 */
	public void setAfid(java.lang.Integer afid){
		this.afid = afid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航空公司二字码
	 */

	@Column(name ="TWOCHARCODE",nullable=true,length=50)
	public java.lang.String getTwocharcode(){
		return this.twocharcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航空公司二字码
	 */
	public void setTwocharcode(java.lang.String twocharcode){
		this.twocharcode = twocharcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航空公司简称
	 */

	@Column(name ="AIRLINESCN",nullable=true,length=50)
	public java.lang.String getAirlinescn(){
		return this.airlinescn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航空公司简称
	 */
	public void setAirlinescn(java.lang.String airlinescn){
		this.airlinescn = airlinescn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班号
	 */

	@Column(name ="FLIGHTNO",nullable=true,length=50)
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
	 *@return: java.lang.String  进出 A,D
	 */

	@Column(name ="ISOFFIN",nullable=true,length=50)
	public java.lang.String getIsoffin(){
		return this.isoffin;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  进出 A,D
	 */
	public void setIsoffin(java.lang.String isoffin){
		this.isoffin = isoffin;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班任务
	 */

	@Column(name ="TASK",nullable=true,length=50)
	public java.lang.String getTask(){
		return this.task;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班任务
	 */
	public void setTask(java.lang.String task){
		this.task = task;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班中文
	 */

	@Column(name ="TASKCN",nullable=true,length=50)
	public java.lang.String getTaskcn(){
		return this.taskcn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班中文
	 */
	public void setTaskcn(java.lang.String taskcn){
		this.taskcn = taskcn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  属性
	 */

	@Column(name ="ATTRIBUTE",nullable=true,length=50)
	public java.lang.String getAttribute(){
		return this.attribute;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  属性
	 */
	public void setAttribute(java.lang.String attribute){
		this.attribute = attribute;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航线
	 */

	@Column(name ="AIRLINEFULLCN",nullable=true,length=6450)
	public java.lang.String getAirlinefullcn(){
		return this.airlinefullcn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航线
	 */
	public void setAirlinefullcn(java.lang.String airlinefullcn){
		this.airlinefullcn = airlinefullcn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  起始站三字码
	 */

	@Column(name ="STARSTATION",nullable=true,length=350)
	public java.lang.String getStarstation(){
		return this.starstation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  起始站三字码
	 */
	public void setStarstation(java.lang.String starstation){
		this.starstation = starstation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  起始站中文简称
	 */

	@Column(name ="STARSTATIONCN",nullable=true,length=64)
	public java.lang.String getStarstationcn(){
		return this.starstationcn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  起始站中文简称
	 */
	public void setStarstationcn(java.lang.String starstationcn){
		this.starstationcn = starstationcn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  目的站三字码
	 */

	@Column(name ="TERMINALSTATION",nullable=true,length=50)
	public java.lang.String getTerminalstation(){
		return this.terminalstation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  目的站三字码
	 */
	public void setTerminalstation(java.lang.String terminalstation){
		this.terminalstation = terminalstation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  目的站中文简称
	 */

	@Column(name ="TERMINALSTATIONCN",nullable=true,length=64)
	public java.lang.String getTerminalstationcn(){
		return this.terminalstationcn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  目的站中文简称
	 */
	public void setTerminalstationcn(java.lang.String terminalstationcn){
		this.terminalstationcn = terminalstationcn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经停站三字码
	 */

	@Column(name ="VIASTATION",nullable=true,length=50)
	public java.lang.String getViastation(){
		return this.viastation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经停站三字码
	 */
	public void setViastation(java.lang.String viastation){
		this.viastation = viastation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经停站中文简称
	 */

	@Column(name ="VIASTATIONCN",nullable=true,length=64)
	public java.lang.String getViastationcn(){
		return this.viastationcn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经停站中文简称
	 */
	public void setViastationcn(java.lang.String viastationcn){
		this.viastationcn = viastationcn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主航班号
	 */

	@Column(name ="MFLIGHTNO",nullable=true,length=50)
	public java.lang.String getMflightno(){
		return this.mflightno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主航班号
	 */
	public void setMflightno(java.lang.String mflightno){
		this.mflightno = mflightno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机型
	 */

	@Column(name ="CRAFTTYPE",nullable=true,length=50)
	public java.lang.String getCrafttype(){
		return this.crafttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机型
	 */
	public void setCrafttype(java.lang.String crafttype){
		this.crafttype = crafttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机号
	 */

	@Column(name ="CRAFTNO",nullable=true,length=50)
	public java.lang.String getCraftno(){
		return this.craftno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机号
	 */
	public void setCraftno(java.lang.String craftno){
		this.craftno = craftno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班状态
	 */

	@Column(name ="FLIGHTSTATE",nullable=true,length=50)
	public java.lang.String getFlightstate(){
		return this.flightstate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班状态
	 */
	public void setFlightstate(java.lang.String flightstate){
		this.flightstate = flightstate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班异常状态
	 */

	@Column(name ="ABNORMALSTATE",nullable=true,length=50)
	public java.lang.String getAbnormalstate(){
		return this.abnormalstate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班异常状态
	 */
	public void setAbnormalstate(java.lang.String abnormalstate){
		this.abnormalstate = abnormalstate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班异常原因
	 */

	@Column(name ="ABNORMALREASON",nullable=true,length=64)
	public java.lang.String getAbnormalreason(){
		return this.abnormalreason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班异常原因
	 */
	public void setAbnormalreason(java.lang.String abnormalreason){
		this.abnormalreason = abnormalreason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登机时刻
	 */

	@Column(name ="BOARDINGTIME",nullable=true,length=50)
	public java.lang.String getBoardingtime(){
		return this.boardingtime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登机时刻
	 */
	public void setBoardingtime(java.lang.String boardingtime){
		this.boardingtime = boardingtime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登机结束时刻
	 */

	@Column(name ="ENDBOARDINGTIME",nullable=true,length=50)
	public java.lang.String getEndboardingtime(){
		return this.endboardingtime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登机结束时刻
	 */
	public void setEndboardingtime(java.lang.String endboardingtime){
		this.endboardingtime = endboardingtime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备降站三字码
	 */

	@Column(name ="DIVERTSTATION",nullable=true,length=50)
	public java.lang.String getDivertstation(){
		return this.divertstation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备降站三字码
	 */
	public void setDivertstation(java.lang.String divertstation){
		this.divertstation = divertstation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备降站中文简称
	 */

	@Column(name ="DIVERTSTATIONCN",nullable=true,length=64)
	public java.lang.String getDivertstationcn(){
		return this.divertstationcn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备降站中文简称
	 */
	public void setDivertstationcn(java.lang.String divertstationcn){
		this.divertstationcn = divertstationcn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  VIP：Y，N
	 */

	@Column(name ="VIP",nullable=true,length=50)
	public java.lang.String getVip(){
		return this.vip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  VIP：Y，N
	 */
	public void setVip(java.lang.String vip){
		this.vip = vip;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  共享航班号
	 */

	@Column(name ="SHAREFLIGHTNO",nullable=true,length=256)
	public java.lang.String getShareflightno(){
		return this.shareflightno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  共享航班号
	 */
	public void setShareflightno(java.lang.String shareflightno){
		this.shareflightno = shareflightno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  前站计划起飞时间
	 */

	@Column(name ="ONRST",nullable=true,length=50)
	public java.lang.String getOnrst(){
		return this.onrst;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  前站计划起飞时间
	 */
	public void setOnrst(java.lang.String onrst){
		this.onrst = onrst;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  前站实际起飞时间
	 */

	@Column(name ="ONRAT",nullable=true,length=50)
	public java.lang.String getOnrat(){
		return this.onrat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  前站实际起飞时间
	 */
	public void setOnrat(java.lang.String onrat){
		this.onrat = onrat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航站楼
	 */

	@Column(name ="TERMINAL",nullable=true,length=50)
	public java.lang.String getTerminal(){
		return this.terminal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航站楼
	 */
	public void setTerminal(java.lang.String terminal){
		this.terminal = terminal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划起飞时间
	 */

	@Column(name ="SST",nullable=true,length=50)
	public java.lang.String getSst(){
		return this.sst;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划起飞时间
	 */
	public void setSst(java.lang.String sst){
		this.sst = sst;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预计起飞时间
	 */

	@Column(name ="SET2",nullable=true,length=50)
	public java.lang.String getSet2(){
		return this.set2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预计起飞时间
	 */
	public void setSet2(java.lang.String set2){
		this.set2 = set2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际起飞时间
	 */

	@Column(name ="SAT",nullable=true,length=50)
	public java.lang.String getSat(){
		return this.sat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际起飞时间
	 */
	public void setSat(java.lang.String sat){
		this.sat = sat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划落地时间
	 */

	@Column(name ="EST",nullable=true,length=50)
	public java.lang.String getEst(){
		return this.est;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划落地时间
	 */
	public void setEst(java.lang.String est){
		this.est = est;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预计落地时间
	 */

	@Column(name ="EET",nullable=true,length=50)
	public java.lang.String getEet(){
		return this.eet;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预计落地时间
	 */
	public void setEet(java.lang.String eet){
		this.eet = eet;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际落地时间
	 */

	@Column(name ="EAT",nullable=true,length=50)
	public java.lang.String getEat(){
		return this.eat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际落地时间
	 */
	public void setEat(java.lang.String eat){
		this.eat = eat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登机口
	 */

	@Column(name ="GATE",nullable=true,length=50)
	public java.lang.String getGate(){
		return this.gate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登机口
	 */
	public void setGate(java.lang.String gate){
		this.gate = gate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行李提取转盘
	 */

	@Column(name ="BELT",nullable=true,length=50)
	public java.lang.String getBelt(){
		return this.belt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行李提取转盘
	 */
	public void setBelt(java.lang.String belt){
		this.belt = belt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  到达出口
	 */

	@Column(name ="EXIT2",nullable=true,length=50)
	public java.lang.String getExit2(){
		return this.exit2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  到达出口
	 */
	public void setExit2(java.lang.String exit2){
		this.exit2 = exit2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机位
	 */

	@Column(name ="CRAFTSITE",nullable=true,length=50)
	public java.lang.String getCraftsite(){
		return this.craftsite;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机位
	 */
	public void setCraftsite(java.lang.String craftsite){
		this.craftsite = craftsite;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保障节点
	 */

	@Column(name ="DYNDISPATCH",nullable=true,length=5000)
	public java.lang.String getDyndispatch(){
		return this.dyndispatch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保障节点
	 */
	public void setDyndispatch(java.lang.String dyndispatch){
		this.dyndispatch = dyndispatch;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */

	@Column(name ="CREATIONTIME",nullable=true)
	public java.util.Date getCreationtime(){
		return this.creationtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreationtime(java.util.Date creationtime){
		this.creationtime = creationtime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实体仓库代码
	 */

	@Column(name ="DEPNORMAL",nullable=true,length=8)
	public java.lang.String getDepnormal(){
		return this.depnormal;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实体仓库代码
	 */
	public void setDepnormal(java.lang.String depnormal){
		this.depnormal = depnormal;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Y正在保障，N未保障
	 */

	@Column(name ="SERVICE",nullable=true,length=2)
	public java.lang.String getService(){
		return this.service;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Y正在保障，N未保障
	 */
	public void setService(java.lang.String service){
		this.service = service;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  欲达时间
	 */

	@Column(name ="AMANETA",nullable=true,length=30)
	public java.lang.String getAmaneta(){
		return this.amaneta;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  欲达时间
	 */
	public void setAmaneta(java.lang.String amaneta){
		this.amaneta = amaneta;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  cobt
	 */

	@Column(name ="COBT",nullable=true,length=30)
	public java.lang.String getCobt(){
		return this.cobt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  cobt
	 */
	public void setCobt(java.lang.String cobt){
		this.cobt = cobt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ctot
	 */

	@Column(name ="CTOT",nullable=true,length=30)
	public java.lang.String getCtot(){
		return this.ctot;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ctot
	 */
	public void setCtot(java.lang.String ctot){
		this.ctot = ctot;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跑道
	 */

	@Column(name ="RUNWAY",nullable=true,length=8)
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
}