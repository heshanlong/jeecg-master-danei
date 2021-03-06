package com.gbiac.fams.business.construction.workapprove.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import com.gbiac.fams.business.construction.worksafe.entity.FamsWorkSafePersonEntity;
import com.gbiac.fams.common.Util.Util;

/**
 * @Title: Entity
 * @Description: 作业申请
 * @author onlineGenerator
 * @date 2019-02-26 15:16:26
 * @version V1.0
 *
 */
@Entity
@Table(name = "fams_work_approve", schema = "")
@SuppressWarnings("serial")
public class FamsWorkApproveEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建用户*/
	private java.lang.String createUserId;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**提交人名称*/
	private java.lang.String pushName;
	/**提交人登录名称*/
	private java.lang.String pushBy;
	/**提交用户id*/
	private java.lang.String pushUserId;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**更新日期*/
	private java.util.Date updateDate;
	/**编号*/
	@Excel(name="编号",width=30)
	private java.lang.String number;
	/**编号*/
	//@Excel(name="编号",width=15)
	private java.lang.String numberContent;
	/**作业项目*/
	@Excel(name="作业项目",width=30)
	private java.lang.String workName;
	/**作业单位*/
	//@Excel(name="作业单位",width=15)
	private java.lang.String workDepart;
	/**作业申报单位*/
	@Excel(name="作业申报单位",width=30)
	private java.lang.String workApproveDepart;
	//中文名
	private java.lang.String workApproveDepartStr;
	/**作业区域*/
	@Excel(name="作业区域",width=80)
	private java.lang.String workArea;
	/**作业内容*/
	//@Excel(name="作业内容",width=15)
	private java.lang.String workContent;
	/**作业类型*/
	@Excel(name="作业类型",width=30,dictTable ="fams_work_type",dicCode ="id",dicText ="type_name")
	private java.lang.String workTypeId;
	private java.lang.String workTypeStr;
	/**报备类型*/
	@Excel(name="审批类型",width=15)
	private java.lang.String applyType;
	/**作业开始日期*/
	@Excel(name="作业开始日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date dateStart;
	/**作业结束日期*/
	@Excel(name="作业结束日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date dateEnd;
	/**作业开始时间*/
	@Excel(name="作业开始时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date workStartTime;
	/**作业结束时间*/
	@Excel(name="作业结束时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date workEndTime;
	/**创建日期*/
	@Excel(name="提交申请时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;
	/** 进场时间 */
	@Excel(name="进场时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date dateIn;
	/**作业影响区域*/
	//@Excel(name="作业影响区域",width=15)
	private java.lang.String workAffectArea;
	/**是否动火*/
	//@Excel(name="是否动火",width=15,dicCode="sf_yn")
	private java.lang.String isUseFire;
	/**动火开始时间*/
	//@Excel(name="动火开始时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date useFireStartTime;
	/**动火结束时间*/
	//@Excel(name="动火结束时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date useFireEndTime;
	/**是否高空*/
	//@Excel(name="是否高空",width=15)
	private java.lang.String isHigh;
	/**作业备注*/
	//@Excel(name="作业备注",width=15)
	private java.lang.String workNote;
	/**流程实例Id*/
	//@Excel(name="流程实例Id",width=15)
	private java.lang.String procinstId;
	/**项目唯一性标识*/
	private java.lang.String workNameOnly;
	/**是否影响机场运行*/
	//@Excel(name="是否影响机场运行",width=15,dicCode="sf_yn")
	private java.lang.String isAffectFlight;
	/**是否需要车辆避让*/
	//@Excel(name="是否需要车辆避让",width=15,dicCode="sf_yn")
	private java.lang.String isAffectCar;
	/**是否连续作业*/
	//@Excel(name="是否连续作业",width=15,dicCode="sf_yn")
	private java.lang.String isContinueWork;
	/**是否第一次*/
	private java.lang.String isFirst;
	/**流程节点*/
	@Excel(name="状态",width=15)
	private java.lang.String taskKey;


	/****************额外信息********************/
	private java.lang.String buttonStr;

	private String taskDefKey;

	private String taskstate;

	private String taskname;

	private String funname;

	private String flag;

	private String jumpurl;

	private String onlysee;
	
	private List<FamsWorkSafePersonEntity> safePersons;
	
	private String workAffectAreaMap;
	
	public FamsWorkApproveEntity() {}
    public FamsWorkApproveEntity(String id) {
		this.id=id;
    }


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
	 *@return: java.lang.String  创建用户
	 */

	@Column(name ="CREATE_USER_ID",nullable=true,length=36)
	public java.lang.String getCreateUserId(){
		return this.createUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建用户
	 */
	public void setCreateUserId(java.lang.String createUserId){
		this.createUserId = createUserId;
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
	
	public void setDateIn(java.util.Date dateIn){
		this.dateIn = dateIn;
	}
	
	@Column(name = "DATE_IN", nullable=true, length=30)
	public java.util.Date getDateIn(){
		return this.dateIn;
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
	 *@return: java.lang.String  编号
	 */

	@Column(name ="NUMBER",nullable=true,length=50)
	public java.lang.String getNumber(){
		return this.number;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setNumber(java.lang.String number){
		this.number = number;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编号
	 */

	@Column(name ="NUMBER_CONTENT",nullable=true,length=50)
	public java.lang.String getNumberContent(){
		return this.numberContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setNumberContent(java.lang.String numberContent){
		this.numberContent = numberContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业项目
	 */

	@Column(name ="WORK_NAME",nullable=true,length=50)
	public java.lang.String getWorkName(){
		return this.workName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业项目
	 */
	public void setWorkName(java.lang.String workName){
		this.workName = workName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业单位
	 */

	@Column(name ="WORK_DEPART",nullable=true,length=36)
	public java.lang.String getWorkDepart(){
		return this.workDepart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业单位
	 */
	public void setWorkDepart(java.lang.String workDepart){
		this.workDepart = workDepart;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业申报单位
	 */

	@Column(name ="WORK_APPROVE_DEPART",nullable=true,length=36)
	public java.lang.String getWorkApproveDepart(){
		return this.workApproveDepart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业申报单位
	 */
	public void setWorkApproveDepart(java.lang.String workApproveDepart){
		this.workApproveDepart = workApproveDepart;
	}

	@Transient
	public String getWorkApproveDepartStr() {
		return workApproveDepartStr;
	}

	public void setWorkApproveDepartStr(String workApproveDepartStr) {
		this.workApproveDepartStr = workApproveDepartStr;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业区域
	 */

	@Column(name ="WORK_AREA",nullable=true,length=250)
	public java.lang.String getWorkArea(){
		return this.workArea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业区域
	 */
	public void setWorkArea(java.lang.String workArea){
		this.workArea = workArea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业内容
	 */

	@Column(name ="WORK_CONTENT",nullable=true,length=1000)
	public java.lang.String getWorkContent(){
		return this.workContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业内容
	 */
	public void setWorkContent(java.lang.String workContent){
		this.workContent = workContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业类型
	 */

	@Column(name ="WORK_TYPE_ID",nullable=true,length=36)
	public java.lang.String getWorkTypeId(){
		return this.workTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业类型
	 */
	public void setWorkTypeId(java.lang.String workTypeId){
		this.workTypeId = workTypeId;
	}

	@Transient
	public String getWorkTypeStr() {
		return workTypeStr;
	}

	public void setWorkTypeStr(String workTypeStr) {
		this.workTypeStr = workTypeStr;
	}

	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  作业开始日期
	 */

	@Column(name ="DATE_START",nullable=true,length=32)
	public java.util.Date getDateStart(){
		return this.dateStart;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  作业开始日期
	 */
	public void setDateStart(Object dateStart){
		this.dateStart = Util.ifObjStr2Date(dateStart);
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  作业结束日期
	 */

	@Column(name ="DATE_END",nullable=true,length=32)
	public java.util.Date getDateEnd(){
		return this.dateEnd;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  作业结束日期
	 */
	public void setDateEnd(Object dateEnd){
		this.dateEnd = Util.ifObjStr2Date(dateEnd);
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  作业开始时间
	 */

	@Column(name ="WORK_START_TIME",nullable=true,length=32)
	public java.util.Date getWorkStartTime(){
		return this.workStartTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  作业开始时间
	 */
	public void setWorkStartTime(Object workStartTime){
		this.workStartTime = Util.ifObjStr2DateTime(workStartTime);
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  作业结束时间
	 */

	@Column(name ="WORK_END_TIME",nullable=true,length=32)
	public java.util.Date getWorkEndTime(){
		return this.workEndTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  作业结束时间
	 */
	public void setWorkEndTime(Object workEndTime){
		this.workEndTime = Util.ifObjStr2DateTime(workEndTime);
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业影响区域
	 */

	@Column(name ="WORK_AFFECT_AREA",nullable=true,length=200)
	public java.lang.String getWorkAffectArea(){
		return this.workAffectArea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业影响区域
	 */
	public void setWorkAffectArea(java.lang.String workAffectArea){
		this.workAffectArea = workAffectArea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否动火
	 */

	@Column(name ="IS_USE_FIRE",nullable=true,length=10)
	public java.lang.String getIsUseFire(){
		return this.isUseFire;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否动火
	 */
	public void setIsUseFire(java.lang.String isUseFire){
		this.isUseFire = isUseFire;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  动火开始时间
	 */

	@Column(name ="USE_FIRE_START_TIME",nullable=true,length=32)
	public java.util.Date getUseFireStartTime(){
		return this.useFireStartTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  动火开始时间
	 */
	/*
	 * public void setUseFireStartTime(java.util.Date useFireStartTime){
	 * this.useFireStartTime = useFireStartTime; }
	 */
	
	public void setUseFireStartTime(Object useFireStartTime) {
		this.useFireStartTime = Util.ifObjStr2DateTime(useFireStartTime);
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  动火结束时间
	 */

	@Column(name ="USE_FIRE_END_TIME",nullable=true,length=32)
	public java.util.Date getUseFireEndTime(){
		return this.useFireEndTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  动火结束时间
	 */
	public void setUseFireEndTime(Object useFireEndTime){
		this.useFireEndTime = Util.ifObjStr2DateTime(useFireEndTime);
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否高空
	 */

	@Column(name ="IS_HIGH",nullable=true,length=10)
	public java.lang.String getIsHigh(){
		return this.isHigh;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否高空
	 */
	public void setIsHigh(java.lang.String isHigh){
		this.isHigh = isHigh;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业备注
	 */

	@Column(name ="WORK_NOTE",nullable=true,length=200)
	public java.lang.String getWorkNote(){
		return this.workNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业备注
	 */
	public void setWorkNote(java.lang.String workNote){
		this.workNote = workNote;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程实例Id
	 */

	@Column(name ="PROCINST_ID",nullable=true,length=36)
	public java.lang.String getProcinstId(){
		return this.procinstId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程实例Id
	 */
	public void setProcinstId(java.lang.String procinstId){
		this.procinstId = procinstId;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目唯一性标识
	 */

	@Column(name ="WORK_NAME_ONLY",nullable=true,length=50)
	public java.lang.String getWorkNameOnly(){
		return this.workNameOnly;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目唯一性标识
	 */
	public void setWorkNameOnly(java.lang.String workNameOnly){
		this.workNameOnly = workNameOnly;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否影响机场运行
	 */

	@Column(name ="IS_AFFECT_FLIGHT",nullable=true,length=10)
	public java.lang.String getIsAffectFlight(){
		return this.isAffectFlight;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否影响机场运行
	 */
	public void setIsAffectFlight(java.lang.String isAffectFlight){
		this.isAffectFlight = isAffectFlight;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否需要车辆避让
	 */

	@Column(name ="IS_AFFECT_CAR",nullable=true,length=10)
	public java.lang.String getIsAffectCar(){
		return this.isAffectCar;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否需要车辆避让
	 */
	public void setIsAffectCar(java.lang.String isAffectCar){
		this.isAffectCar = isAffectCar;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否连续作业
	 */

	@Column(name ="IS_CONTINUE_WORK",nullable=true,length=10)
	public java.lang.String getIsContinueWork(){
		return this.isContinueWork;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否连续作业
	 */
	public void setIsContinueWork(java.lang.String isContinueWork){
		this.isContinueWork = isContinueWork;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否第一次
	 */

	@Column(name ="IS_FIRST",nullable=true,length=10)
	public java.lang.String getIsFirst(){
		return this.isFirst;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否第一次
	 */
	public void setIsFirst(java.lang.String isFirst){
		this.isFirst = isFirst;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程节点
	 */

	@Column(name ="TASK_KEY",nullable=true,length=50)
	public java.lang.String getTaskKey(){
		return this.taskKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程节点
	 */
	public void setTaskKey(java.lang.String taskKey){
		this.taskKey = taskKey;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报备类型
	 */
	@Column(name ="APPLY_TYPE",nullable=true,length=50)
	public String getApplyType() {
		return applyType;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报备类型
	 */
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	

	public void setPushUserId(java.lang.String pushUserId){
		this.pushUserId = pushUserId;
	}
	
	@Column(name="PUSH_USER_ID", nullable=true,length=36)
	public java.lang.String getPushUserId(){
		return this.pushUserId;
	}
	
	public void setPushName(java.lang.String pushName){
		this.pushName = pushName;
	}
	@Column(name = "PUSH_NAME", nullable=true, length=50)
	public java.lang.String getPushName(){
		return this.pushName;
	}
	
	public void setPushBy(java.lang.String pushBy){
		this.pushBy = pushBy;
	}
	@Column(name = "PUSH_BY", nullable=true, length=50)
	public java.lang.String getPushBy(){
		return this.pushBy;
	}
	
	
	
	
	@Transient
	public String getButtonStr() {
		if(this.getId()==null||this.getTaskname()==null){
			return buttonStr;
		}
		if (this.getOnlysee() != null) {
			// 其他部门正在审批的时候显示撤回,首先满足任务定义节点不为空
			if (this.getTaskDefKey() != null) {
				
				// 判断是否可见
				if ("N".equals(this.getOnlysee())) {
					// 如果可见的话,执行原先的逻辑,可见情况下只有:s-apply, s-in需要撤回
					if ("s-apply".equals(this.getTaskDefKey())) {
						return "<button style=\"margin-left: 5px;background-color: #31b0d5\" class=\"ace_button\" onclick=\"myUpdate('"
								+ this.getId() + "')\">编辑</button>"
								+ "<button style=\"margin-left: 5px;background-color: #31b0d5\" class=\"ace_button\" onclick=\"jumpBMPPage('"
								+ this.getId() + "','" + this.getFlag() + "','" + this.getFunname() + "','"
								+ this.getTaskDefKey() + "','" + this.getJumpurl() + "','" + this.getTaskname()
								+ "','"+this.getCreateName()+"','"+Util.getPcOrAppUserName()+"','"+this.getWorkStartTime()+"','"+this.getWorkEndTime()+"')\">" + this.getTaskname() + "</button>";
					}
					if ("s-in".equals(this.getTaskDefKey())) {
						if(this.pushUserId.equals(Util.getPcOrAppUserId())) {
							return "<button style=\"margin-left: 5px;background-color: #31b0d5\" class=\"ace_button\" onclick=\"jumpBMPPage('"
									+ this.getId() + "','" + this.getFlag() + "','" + this.getFunname() + "','"
									+ this.getTaskDefKey() + "','" + this.getJumpurl() + "','" + this.getTaskname()
									+ "','"+this.getCreateName()+"','"+Util.getPcOrAppUserName()+"','"+this.getWorkStartTime()+"','"+this.getWorkEndTime()+"')\">" + this.getTaskname() + "</button>";
						}
						return null;
					}
					
					if (this.getTaskname() != null) {
						if(!"s-out".equals(this.getTaskDefKey())) {
							return "<button style=\"margin-left: 5px;background-color: #31b0d5\" class=\"ace_button\" onclick=\"jumpBMPPage('"
									+ this.getId() + "','" + this.getFlag() + "','" + this.getFunname() + "','"
									+ this.getTaskDefKey() + "','" + this.getJumpurl() + "','" + this.getTaskname()
									+ "','"+this.getCreateName()+"','"+Util.getPcOrAppUserName()+"','"+this.getWorkStartTime()+"','"+this.getWorkEndTime()+"')\">" + this.getTaskname() + "</button>";
						}else {
							if(this.pushUserId.equals(Util.getPcOrAppUserId())) {
								return "<button style=\"margin-left: 5px;background-color: #31b0d5\" class=\"ace_button\" onclick=\"jumpBMPPage('"
										+ this.getId() + "','" + this.getFlag() + "','" + this.getFunname() + "','"
										+ this.getTaskDefKey() + "','" + this.getJumpurl() + "','" + this.getTaskname()
										+ "','"+this.getCreateName()+"','"+Util.getPcOrAppUserName()+"','"+this.getWorkStartTime()+"','"+this.getWorkEndTime()+"')\">" + this.getTaskname() + "</button>";
							}
							else {
								return null;
							}
						}
					
					}
				}
			}
			return null;
		}
		return null;
	}
	

	public void setButtonStr(String buttonStr) {
		this.buttonStr = buttonStr;
	}

	@Transient
	public String getTaskDefKey() {
		return taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	@Transient
	public String getTaskstate() {
		switch(this.taskKey) {
			case "s-apply":
				this.taskstate =  "待提交";
				break;
			case "s-department-apply":
				this.taskstate =  "待部门审批";
				break;
			case "y-approve-apply":
				this.taskstate =  "待审批";
				break;
			case "s-in":
				this.taskstate =  "待进场";
				break;
			case "y-approve-in":
				this.taskstate =  "进场待确认";
				break;
			case "s-out":
				this.taskstate =  "进行中";
				break;
			case "y-approve-out":
				this.taskstate =  "离场待确认";
				break;
			case "end":
				this.taskstate =  "已结束";
				break;
			case "s-undo":
				this.taskstate =  "已撤回";
				break;
			case "leave":
				this.taskstate =  "已离场";
				break;
			case "s-nopass":
				this.taskstate =  "不通过";
				break;
		}
		return taskstate;
	}

	public void setTaskstate(String taskstate) {
		this.taskstate = taskstate;
	}

	@Transient
	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	@Transient
	public String getFunname() {
		return funname;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}
	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Transient
	public String getJumpurl() {
		return jumpurl;
	}

	public void setJumpurl(String jumpurl) {
		this.jumpurl = jumpurl;
	}

	@Transient
	public String getOnlysee() {
		return onlysee;
	}

	public void setOnlysee(String onlysee) {
		this.onlysee = onlysee;
	}

	public void setNodeInfo(Map m) {
		if(m!=null){
			this.setFlag((String) m.get("flag"));
			this.setFunname((String) m.get("funname"));
			this.setJumpurl((String) m.get("jumpurl"));
			this.setTaskDefKey((String) m.get("taskkey"));
			this.setTaskstate((String) m.get("taskstate"));
			this.setTaskname((String) m.get("taskname"));
			this.setOnlysee((String) m.get("onlysee"));
		}
	}
	@Transient
	public List<FamsWorkSafePersonEntity> getSafePersons(){
		return this.safePersons;
	}
	public void setSafePersons(List<FamsWorkSafePersonEntity> safePersons) {
		this.safePersons = safePersons;
	}
	
	@Transient
	public java.lang.String getWorkAffectAreaMap(){
		return this.workAffectAreaMap;
	}
	
	public void setWorkAffectAreaMap(java.lang.String workAffectAreaMap) {
		this.workAffectAreaMap = workAffectAreaMap;
	}
	
	
}