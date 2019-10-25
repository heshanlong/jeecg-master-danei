package com.gbiac.fams.business.construction.worknodeinfo.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 施工管理流程节点信息表
 * @author onlineGenerator
 * @date 2019-03-07 09:48:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_work_node_info", schema = "")
@SuppressWarnings("serial")
public class FamsWorkNodeInfoEntity implements java.io.Serializable {
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
	/**任务key*/
	@Excel(name="任务key",width=15)
	private java.lang.String taskKey;
	/**任务状态*/
	@Excel(name="任务状态",width=15)
	private java.lang.String taskState;
	/**任务名称*/
	@Excel(name="任务名称",width=15)
	private java.lang.String taskName;
	/**执行的方法名称*/
	@Excel(name="执行的方法名称",width=15)
	private java.lang.String funname;
	/**方法标识*/
	@Excel(name="方法标识",width=15)
	private java.lang.String flag;
	/**跳转路径*/
	@Excel(name="跳转路径",width=15)
	private java.lang.String jumpurl;
	
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
	 *@return: java.lang.String  任务key
	 */

	@Column(name ="TASK_KEY",nullable=true,length=50)
	public java.lang.String getTaskKey(){
		return this.taskKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务key
	 */
	public void setTaskKey(java.lang.String taskKey){
		this.taskKey = taskKey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务状态
	 */

	@Column(name ="TASK_STATE",nullable=true,length=50)
	public java.lang.String getTaskState(){
		return this.taskState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务状态
	 */
	public void setTaskState(java.lang.String taskState){
		this.taskState = taskState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务名称
	 */

	@Column(name ="TASK_NAME",nullable=true,length=50)
	public java.lang.String getTaskName(){
		return this.taskName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务名称
	 */
	public void setTaskName(java.lang.String taskName){
		this.taskName = taskName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  执行的方法名称
	 */

	@Column(name ="FUNNAME",nullable=true,length=50)
	public java.lang.String getFunname(){
		return this.funname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  执行的方法名称
	 */
	public void setFunname(java.lang.String funname){
		this.funname = funname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  方法标识
	 */

	@Column(name ="FLAG",nullable=true,length=50)
	public java.lang.String getFlag(){
		return this.flag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  方法标识
	 */
	public void setFlag(java.lang.String flag){
		this.flag = flag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跳转路径
	 */

	@Column(name ="JUMPURL",nullable=true,length=200)
	public java.lang.String getJumpurl(){
		return this.jumpurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  跳转路径
	 */
	public void setJumpurl(java.lang.String jumpurl){
		this.jumpurl = jumpurl;
	}
}