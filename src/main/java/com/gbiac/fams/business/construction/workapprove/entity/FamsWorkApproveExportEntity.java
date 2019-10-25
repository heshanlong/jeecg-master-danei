package com.gbiac.fams.business.construction.workapprove.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @Title: Entity
 * @Description: 施工申请
 * @author onlineGenerator
 * @date 2019-02-26 15:16:26
 * @version V1.0
 *
 */
@SuppressWarnings("serial")
public class FamsWorkApproveExportEntity implements java.io.Serializable {
	@Excel(name="所属辖区",width=15,mergeVertical = true)
	private String area;//所属区域
	/**主键*/
	private String id;
	/**创建用户*/
	private String createUserId;
	/**创建人名称*/
	private String createName;
	/**创建人登录名称*/
	private String createBy;
	/**更新人名称*/
	private String updateName;
	/**更新人登录名称*/
	private String updateBy;
	/**所属部门*/
	private String sysOrgCode;
	/**所属公司*/
	private String sysCompanyCode;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**编号*/
	//@Excel(name="编号",width=15)
	private String number;
	/**编号*/
	@Excel(name="序号",width=10)
	private String numberContent;
	/**施工项目*/
	@Excel(name="施工项目",width=30)
	private String workName;
	/**施工单位*/
	//@Excel(name="施工单位",width=15)
	private String workDepart;
	/**施工申报单位*/
	//@Excel(name="施工申报单位",width=15)
	private String workApproveDepart;
	//中文名
	private String workApproveDepartStr;
	/**施工区域*/
	//@Excel(name="施工区域",width=15)
	private String workArea;
	/**施工内容*/
	//@Excel(name="施工内容",width=15)
	private String workContent;
	/**施工类型*/	
	//@Excel(name="施工类型",width=15,dictTable ="fams_work_type",dicCode ="id",dicText ="type_name")
	private String workTypeId;
	private String workTypeStr;
	/**施工开始时间*/
	//@Excel(name="施工开始时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date workStartTime;
	/**施工结束时间*/
	//@Excel(name="施工结束时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date workEndTime;
	/**施工影响区域*/
	@Excel(name="影响范围",width=30)
	private String workAffectArea;
	/**是否动火*/
	//@Excel(name="是否动火",width=15,dicCode="sf_yn")
	private String isUseFire;
	/**动火开始时间*/
	//@Excel(name="动火开始时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date useFireStartTime;
	/**动火结束时间*/
	//@Excel(name="动火结束时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date useFireEndTime;
	/**是否高空*/
	//@Excel(name="是否高空",width=15)
	private String isHigh;
	@Excel(name="影响时段(施工时段)",width=20)
	private String work_time;//影响时段(施工时段)
	/**施工备注*/
	@Excel(name="施工备注",width=30)
	private String workNote;
	/**流程实例Id*/
	//@Excel(name="流程实例Id",width=15)
	private String procinstId;
	/**项目唯一性标识*/
	private String workNameOnly;
	/**是否影响机场运行*/
	//@Excel(name="是否影响机场运行",width=15,dicCode="sf_yn")
	private String isAffectFlight;
	/**是否连续施工*/
	//@Excel(name="是否连续施工",width=15,dicCode="sf_yn")
	private String isContinueWork;
	/**是否第一次*/
	private String isFirst;
	/**流程节点*/
	//@Excel(name="流程节点",width=15)
	private String taskKey;
	/**报备类型*/
	//@Excel(name="报备类型",width=15)
	private String applyType;

	public FamsWorkApproveExportEntity() {}

    /**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建用户
	 */

	@Column(name ="CREATE_USER_ID",nullable=true,length=36)
	public String getCreateUserId(){
		return this.createUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建用户
	 */
	public void setCreateUserId(String createUserId){
		this.createUserId = createUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(String sysCompanyCode){
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
	public String getNumber(){
		return this.number;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setNumber(String number){
		this.number = number;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编号
	 */

	@Column(name ="NUMBER_CONTENT",nullable=true,length=50)
	public String getNumberContent(){
		return this.numberContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setNumberContent(String numberContent){
		this.numberContent = numberContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工项目
	 */

	@Column(name ="WORK_NAME",nullable=true,length=50)
	public String getWorkName(){
		return this.workName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工项目
	 */
	public void setWorkName(String workName){
		this.workName = workName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工单位
	 */

	@Column(name ="WORK_DEPART",nullable=true,length=36)
	public String getWorkDepart(){
		return this.workDepart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工单位
	 */
	public void setWorkDepart(String workDepart){
		this.workDepart = workDepart;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工申报单位
	 */

	@Column(name ="WORK_APPROVE_DEPART",nullable=true,length=36)
	public String getWorkApproveDepart(){
		return this.workApproveDepart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工申报单位
	 */
	public void setWorkApproveDepart(String workApproveDepart){
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
	 *@return: java.lang.String  施工区域
	 */

	@Column(name ="WORK_AREA",nullable=true,length=200)
	public String getWorkArea(){
		return this.workArea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工区域
	 */
	public void setWorkArea(String workArea){
		this.workArea = workArea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工内容
	 */

	@Column(name ="WORK_CONTENT",nullable=true,length=1000)
	public String getWorkContent(){
		return this.workContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工内容
	 */
	public void setWorkContent(String workContent){
		this.workContent = workContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工类型
	 */

	@Column(name ="WORK_TYPE_ID",nullable=true,length=36)
	public String getWorkTypeId(){
		return this.workTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工类型
	 */
	public void setWorkTypeId(String workTypeId){
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
	 *@return: java.util.Date  施工开始时间
	 */

	@Column(name ="WORK_START_TIME",nullable=true,length=32)
	public java.util.Date getWorkStartTime(){
		return this.workStartTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  施工开始时间
	 */
	public void setWorkStartTime(java.util.Date workStartTime){
		if(workStartTime!=null){
			workStartTime.setSeconds(0);
			this.workStartTime = workStartTime;
		}else{
			this.workStartTime = workStartTime;
		}
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  施工结束时间
	 */

	@Column(name ="WORK_END_TIME",nullable=true,length=32)
	public java.util.Date getWorkEndTime(){
		return this.workEndTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  施工结束时间
	 */
	public void setWorkEndTime(java.util.Date workEndTime){
		if(workEndTime!=null){
			workEndTime.setSeconds(0);
			this.workEndTime = workEndTime;
		}else{
			this.workEndTime = workEndTime;
		}
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工影响区域
	 */

	@Column(name ="WORK_AFFECT_AREA",nullable=true,length=200)
	public String getWorkAffectArea(){
		return this.workAffectArea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工影响区域
	 */
	public void setWorkAffectArea(String workAffectArea){
		this.workAffectArea = workAffectArea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否动火
	 */

	@Column(name ="IS_USE_FIRE",nullable=true,length=10)
	public String getIsUseFire(){
		return this.isUseFire;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否动火
	 */
	public void setIsUseFire(String isUseFire){
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
	public void setUseFireStartTime(java.util.Date useFireStartTime){
		this.useFireStartTime = useFireStartTime;
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
	public void setUseFireEndTime(java.util.Date useFireEndTime){
		this.useFireEndTime = useFireEndTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否高空
	 */

	@Column(name ="IS_HIGH",nullable=true,length=10)
	public String getIsHigh(){
		return this.isHigh;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否高空
	 */
	public void setIsHigh(String isHigh){
		this.isHigh = isHigh;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工备注
	 */

	@Column(name ="WORK_NOTE",nullable=true,length=200)
	public String getWorkNote(){
		return this.workNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工备注
	 */
	public void setWorkNote(String workNote){
		this.workNote = workNote;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程实例Id
	 */

	@Column(name ="PROCINST_ID",nullable=true,length=36)
	public String getProcinstId(){
		return this.procinstId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程实例Id
	 */
	public void setProcinstId(String procinstId){
		this.procinstId = procinstId;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目唯一性标识
	 */

	@Column(name ="WORK_NAME_ONLY",nullable=true,length=50)
	public String getWorkNameOnly(){
		return this.workNameOnly;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目唯一性标识
	 */
	public void setWorkNameOnly(String workNameOnly){
		this.workNameOnly = workNameOnly;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否影响机场运行
	 */

	@Column(name ="IS_AFFECT_FLIGHT",nullable=true,length=10)
	public String getIsAffectFlight(){
		return this.isAffectFlight;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否影响机场运行
	 */
	public void setIsAffectFlight(String isAffectFlight){
		this.isAffectFlight = isAffectFlight;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否连续施工
	 */

	@Column(name ="IS_CONTINUE_WORK",nullable=true,length=10)
	public String getIsContinueWork(){
		return this.isContinueWork;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否连续施工
	 */
	public void setIsContinueWork(String isContinueWork){
		this.isContinueWork = isContinueWork;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否第一次
	 */

	@Column(name ="IS_FIRST",nullable=true,length=10)
	public String getIsFirst(){
		return this.isFirst;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否第一次
	 */
	public void setIsFirst(String isFirst){
		this.isFirst = isFirst;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程节点
	 */

	@Column(name ="TASK_KEY",nullable=true,length=50)
	public String getTaskKey(){
		return this.taskKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程节点
	 */
	public void setTaskKey(String taskKey){
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


	@Transient
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Transient
	public String getWork_time() {
		return work_time;
	}

	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}
}