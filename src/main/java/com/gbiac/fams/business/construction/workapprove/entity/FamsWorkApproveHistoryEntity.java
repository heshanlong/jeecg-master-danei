package com.gbiac.fams.business.construction.workapprove.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @Title: Entity
 * @Description: 施工申请备份表
 * @author onlineGenerator
 * @date 2019-02-26 15:17:16
 * @version V1.0
 *
 */
@Entity
@Table(name = "fams_work_approve_history", schema = "")
@SuppressWarnings("serial")
public class FamsWorkApproveHistoryEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**修改类型*/
	@Excel(name="修改类型",width=15)
	private java.lang.String editType;
	/**业务主键*/
	@Excel(name="业务主键",width=15)
	private java.lang.String bid;
	/**创建人名称*/
	@Excel(name="创建人名称",width=15)
	private java.lang.String createName;
	/**创建人登录名称*/
	@Excel(name="创建人登录名称",width=15)
	private java.lang.String createBy;
	/**更新人名称*/
	@Excel(name="更新人名称",width=15)
	private java.lang.String updateName;
	/**更新人登录名称*/
	@Excel(name="更新人登录名称",width=15)
	private java.lang.String updateBy;
	/**所属部门*/
	@Excel(name="所属部门",width=15)
	private java.lang.String sysOrgCode;
	/**所属公司*/
	@Excel(name="所属公司",width=15)
	private java.lang.String sysCompanyCode;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新日期*/
	@Excel(name="更新日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**创建用户*/
	@Excel(name="创建用户",width=15)
	private java.lang.String createUserId;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String number;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String numberContent;
	/**施工项目*/
	@Excel(name="施工项目",width=15)
	private java.lang.String workName;
	/**施工申报单位*/
	@Excel(name="施工申报单位",width=15)
	private java.lang.String workApproveDepart;
	/**施工单位*/
	@Excel(name="施工单位",width=15)
	private java.lang.String workDepart;
	/**施工开始时间*/
	@Excel(name="施工开始时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date workStartTime;
	/**施工结束时间*/
	@Excel(name="施工结束时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date workEndTime;
	/**施工区域*/
	@Excel(name="施工区域",width=15)
	private java.lang.String workArea;
	/**施工影响区域*/
	@Excel(name="施工影响区域",width=15)
	private java.lang.String workAffectArea;
	/**施工类型*/
	@Excel(name="施工类型",width=15)
	private java.lang.String workTypeId;
	/**施工内容*/
	@Excel(name="施工内容",width=15)
	private java.lang.String workContent;
	/**施工备注*/
	@Excel(name="施工备注",width=15)
	private java.lang.String workNote;
	/**是否动火*/
	@Excel(name="是否动火",width=15)
	private java.lang.String isUseFire;
	/**动火开始时间*/
	@Excel(name="动火开始时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date useFireStartTime;
	/**动火结束时间*/
	@Excel(name="动火结束时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date useFireEndTime;
	/**是否高空*/
	@Excel(name="是否高空",width=15)
	private java.lang.String isHigh;
	/**流程实例Id*/
	@Excel(name="流程实例Id",width=15)
	private java.lang.String procinstId;
	public FamsWorkApproveHistoryEntity(){}
	public FamsWorkApproveHistoryEntity(String editType,FamsWorkApproveEntity entity){
		this.editType=editType;
		this.bid=entity.getId();
		this.createName=entity.getCreateName();
		this.createBy=entity.getCreateBy();
		this.updateName=entity.getUpdateName();
		this.updateBy=entity.getUpdateBy();
		this.sysOrgCode=entity.getSysOrgCode();
		this.sysCompanyCode=entity.getSysCompanyCode();
		this.createDate=entity.getCreateDate();
		this.updateDate=entity.getUpdateDate();
		this.createUserId=entity.getCreateUserId();
		this.number=entity.getNumber();
		this.numberContent=entity.getNumberContent();
		this.workName=entity.getWorkName();
		this.workApproveDepart=entity.getWorkApproveDepart();
		this.workDepart=entity.getWorkDepart();
		this.workStartTime=entity.getWorkStartTime();
		this.workEndTime=entity.getWorkEndTime();
		this.workArea=entity.getWorkArea();
		this.workAffectArea=entity.getWorkAffectArea();
		this.workTypeId=entity.getWorkTypeId();
		this.workContent=entity.getWorkContent();
		this.workNote=entity.getWorkNote();
		this.isUseFire=entity.getWorkNote();
		this.useFireStartTime=entity.getUseFireStartTime();
		this.useFireEndTime=entity.getUseFireEndTime();
		this.isHigh=entity.getIsHigh();
		this.procinstId=entity.getProcinstId();
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
	 *@return: java.lang.String  修改类型
	 */

	@Column(name ="EDIT_TYPE",nullable=true,length=50)
	public java.lang.String getEditType(){
		return this.editType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改类型
	 */
	public void setEditType(java.lang.String editType){
		this.editType = editType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务主键
	 */

	@Column(name ="BID",nullable=true,length=36)
	public java.lang.String getBid(){
		return this.bid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务主键
	 */
	public void setBid(java.lang.String bid){
		this.bid = bid;
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
	 *@return: java.lang.String  施工项目
	 */

	@Column(name ="WORK_NAME",nullable=true,length=50)
	public java.lang.String getWorkName(){
		return this.workName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工项目
	 */
	public void setWorkName(java.lang.String workName){
		this.workName = workName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工申报单位
	 */

	@Column(name ="WORK_APPROVE_DEPART",nullable=true,length=36)
	public java.lang.String getWorkApproveDepart(){
		return this.workApproveDepart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工申报单位
	 */
	public void setWorkApproveDepart(java.lang.String workApproveDepart){
		this.workApproveDepart = workApproveDepart;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工单位
	 */

	@Column(name ="WORK_DEPART",nullable=true,length=36)
	public java.lang.String getWorkDepart(){
		return this.workDepart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工单位
	 */
	public void setWorkDepart(java.lang.String workDepart){
		this.workDepart = workDepart;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  施工开始时间
	 */

	@Column(name ="WORK_START_TIME",nullable=true)
	public java.util.Date getWorkStartTime(){
		return this.workStartTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  施工开始时间
	 */
	public void setWorkStartTime(java.util.Date workStartTime){
		this.workStartTime = workStartTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  施工结束时间
	 */

	@Column(name ="WORK_END_TIME",nullable=true)
	public java.util.Date getWorkEndTime(){
		return this.workEndTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  施工结束时间
	 */
	public void setWorkEndTime(java.util.Date workEndTime){
		this.workEndTime = workEndTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工区域
	 */

	@Column(name ="WORK_AREA",nullable=true,length=200)
	public java.lang.String getWorkArea(){
		return this.workArea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工区域
	 */
	public void setWorkArea(java.lang.String workArea){
		this.workArea = workArea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工影响区域
	 */

	@Column(name ="WORK_AFFECT_AREA",nullable=true,length=200)
	public java.lang.String getWorkAffectArea(){
		return this.workAffectArea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工影响区域
	 */
	public void setWorkAffectArea(java.lang.String workAffectArea){
		this.workAffectArea = workAffectArea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工类型
	 */

	@Column(name ="WORK_TYPE_ID",nullable=true,length=36)
	public java.lang.String getWorkTypeId(){
		return this.workTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工类型
	 */
	public void setWorkTypeId(java.lang.String workTypeId){
		this.workTypeId = workTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工内容
	 */

	@Column(name ="WORK_CONTENT",nullable=true,length=1000)
	public java.lang.String getWorkContent(){
		return this.workContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工内容
	 */
	public void setWorkContent(java.lang.String workContent){
		this.workContent = workContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工备注
	 */

	@Column(name ="WORK_NOTE",nullable=true,length=200)
	public java.lang.String getWorkNote(){
		return this.workNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工备注
	 */
	public void setWorkNote(java.lang.String workNote){
		this.workNote = workNote;
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

	@Column(name ="USE_FIRE_START_TIME",nullable=true)
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

	@Column(name ="USE_FIRE_END_TIME",nullable=true)
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
}