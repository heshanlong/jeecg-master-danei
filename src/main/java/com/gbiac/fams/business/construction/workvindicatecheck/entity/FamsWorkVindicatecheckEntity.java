package com.gbiac.fams.business.construction.workvindicatecheck.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * @Title: Entity
 * @Description: 维护施工检查表
 * @author onlineGenerator
 * @date 2019-02-22 16:11:22
 * @version V1.0
 *
 */
@Entity
@Table(name = "fams_work_vindicatecheck", schema = "")
@SuppressWarnings("serial")
public class FamsWorkVindicatecheckEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建人名称*/
	@Excel(name="创建人名称",width=15)
	private java.lang.String createName;
	/**创建人登录名称*/
	@Excel(name="创建人登录名称",width=15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人名称*/
	@Excel(name="更新人名称",width=15)
	private java.lang.String updateName;
	/**更新人登录名称*/
	@Excel(name="更新人登录名称",width=15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name="更新日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**所属部门*/
	@Excel(name="所属部门",width=15)
	private java.lang.String sysOrgCode;
	/**所属公司*/
	@Excel(name="所属公司",width=15)
	private java.lang.String sysCompanyCode;
	/**关联的业务主表id*/
	@Excel(name="关联的业务主表id",width=15)
	private java.lang.String bid;
	/**左上编号*/
	@Excel(name="左上编号",width=15)
	private java.lang.String number;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String numberContent;
	/**检查单位*/
	@Excel(name="检查单位",width=15)
	private java.lang.String checkDepart;
	/**检查时间*/
	@Excel(name="检查时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date checkTime;
	/**建设单位*/
	@Excel(name="建设单位",width=15)
	private java.lang.String buildDepart;
	/**施工项目*/
	@Excel(name="施工项目",width=15)
	private java.lang.String workName;
	/**施工地点*/
	@Excel(name="施工地点",width=15)
	private java.lang.String workPlace;
	/**施工单位*/
	@Excel(name="施工单位",width=15)
	private java.lang.String workDepart;
	/**施工队进场时间*/
	@Excel(name="施工队进场时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date workStartTime;
	/**施工预计结束时间*/
	@Excel(name="施工预计结束时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date workEndTime;
	/**检查结果*/
	@Excel(name="检查结果",width=15)
	private java.lang.String checkResult;
	/**整改结果*/
	@Excel(name="整改结果",width=15)
	private java.lang.String repairResult;
	/**检查小结*/
	@Excel(name="检查小结",width=15)
	private java.lang.String checkResultNote;
	/**整改意见*/
	@Excel(name="整改意见",width=15)
	private java.lang.String checkRepairNote;
	/**建设单位签字*/
	@Excel(name="建设单位签字",width=15)
	private java.lang.String jsSignature;
	/**飞行区管理部签字*/
	@Excel(name="飞行区管理部签字",width=15)
	private java.lang.String fxSignature;
	/**流程实例id*/
	@Excel(name="流程实例id",width=15)
	private java.lang.String procinstId;
	public FamsWorkVindicatecheckEntity() {
	}
	public FamsWorkVindicatecheckEntity(Map<String,Object> params) {
		Long checkTime=params.get("checkTime")==null||params.get("checkTime").equals("")?null:Long.valueOf(params.get("checkTime").toString());
		this.setCheckTime(checkTime==null?null:new Date(checkTime));
		this.setBid(params.get("bid").toString());
		this.setWorkName(params.get("workName").toString());
		this.setWorkDepart(params.get("workDepart").toString());
		this.setWorkPlace(params.get("workPlace").toString());
		Long workStartTime=params.get("workStartTime")==null||params.get("workStartTime").equals("")?null:Long.valueOf(params.get("workStartTime").toString());
		this.setWorkStartTime(workStartTime==null?null:new Date(workStartTime));
		Long workEndTime=params.get("workEndTime")==null||params.get("workEndTime").equals("")?null:Long.valueOf(params.get("workEndTime").toString());
		this.setWorkEndTime(workEndTime==null?null:new Date(workEndTime));
		this.setCheckResultNote(params.get("checkResultNote").toString());
		this.setCheckRepairNote(params.get("checkRepairNote").toString());
	}

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
	 *@return: java.lang.String  关联的业务主表id
	 */

	@Column(name ="BID",nullable=true,length=36)
	public java.lang.String getBid(){
		return this.bid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关联的业务主表id
	 */
	public void setBid(java.lang.String bid){
		this.bid = bid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  左上编号
	 */

	@Column(name ="NUMBER",nullable=true,length=50)
	public java.lang.String getNumber(){
		return this.number;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  左上编号
	 */
	public void setNumber(java.lang.String number){
		this.number = number;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编号
	 */

	@Column(name ="NUMBER_CONTENT",nullable=true)
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
	 *@return: java.lang.String  检查单位
	 */

	@Column(name ="CHECK_DEPART",nullable=true,length=36)
	public java.lang.String getCheckDepart(){
		return this.checkDepart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查单位
	 */
	public void setCheckDepart(java.lang.String checkDepart){
		this.checkDepart = checkDepart;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  检查时间
	 */

	@Column(name ="CHECK_TIME",nullable=true)
	public java.util.Date getCheckTime(){
		return this.checkTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  检查时间
	 */
	public void setCheckTime(java.util.Date checkTime){
		this.checkTime = checkTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  建设单位
	 */

	@Column(name ="BUILD_DEPART",nullable=true,length=36)
	public java.lang.String getBuildDepart(){
		return this.buildDepart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  建设单位
	 */
	public void setBuildDepart(java.lang.String buildDepart){
		this.buildDepart = buildDepart;
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
	 *@return: java.lang.String  施工地点
	 */

	@Column(name ="WORK_PLACE",nullable=true,length=100)
	public java.lang.String getWorkPlace(){
		return this.workPlace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工地点
	 */
	public void setWorkPlace(java.lang.String workPlace){
		this.workPlace = workPlace;
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
	 *@return: java.util.Date  施工队进场时间
	 */

	@Column(name ="WORK_START_TIME",nullable=true)
	public java.util.Date getWorkStartTime(){
		return this.workStartTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  施工队进场时间
	 */
	public void setWorkStartTime(java.util.Date workStartTime){
		this.workStartTime = workStartTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  施工预计结束时间
	 */

	@Column(name ="WORK_END_TIME",nullable=true)
	public java.util.Date getWorkEndTime(){
		return this.workEndTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  施工预计结束时间
	 */
	public void setWorkEndTime(java.util.Date workEndTime){
		this.workEndTime = workEndTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查结果
	 */

	@Column(name ="CHECK_RESULT",nullable=true,length=32)
	public java.lang.String getCheckResult(){
		return this.checkResult;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查结果
	 */
	public void setCheckResult(java.lang.String checkResult){
		this.checkResult = checkResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整改结果
	 */

	@Column(name ="REPAIR_RESULT",nullable=true,length=32)
	public java.lang.String getRepairResult(){
		return this.repairResult;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整改结果
	 */
	public void setRepairResult(java.lang.String repairResult){
		this.repairResult = repairResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查小结
	 */

	@Column(name ="CHECK_RESULT_NOTE",nullable=true,length=100)
	public java.lang.String getCheckResultNote(){
		return this.checkResultNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查小结
	 */
	public void setCheckResultNote(java.lang.String checkResultNote){
		this.checkResultNote = checkResultNote;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整改意见
	 */

	@Column(name ="CHECK_REPAIR_NOTE",nullable=true,length=100)
	public java.lang.String getCheckRepairNote(){
		return this.checkRepairNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整改意见
	 */
	public void setCheckRepairNote(java.lang.String checkRepairNote){
		this.checkRepairNote = checkRepairNote;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  建设单位签字
	 */

	@Column(name ="JS_SIGNATURE",nullable=true,length=50)
	public java.lang.String getJsSignature(){
		return this.jsSignature;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  建设单位签字
	 */
	public void setJsSignature(java.lang.String jsSignature){
		this.jsSignature = jsSignature;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  飞行区管理部签字
	 */

	@Column(name ="FX_SIGNATURE",nullable=true,length=50)
	public java.lang.String getFxSignature(){
		return this.fxSignature;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  飞行区管理部签字
	 */
	public void setFxSignature(java.lang.String fxSignature){
		this.fxSignature = fxSignature;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程实例id
	 */

	@Column(name ="PROCINST_ID",nullable=true,length=36)
	public java.lang.String getProcinstId(){
		return this.procinstId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程实例id
	 */
	public void setProcinstId(java.lang.String procinstId){
		this.procinstId = procinstId;
	}
}