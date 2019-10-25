package com.gbiac.fams.business.airportrunway.repair.entity;

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
 * @Description: fams_airportrunway_repair
 * @author 江家滨
 * @date 2019-01-21 10:39:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_airportrunway_repair", schema = "")
@SuppressWarnings("serial")
public class FamsAirportrunwayRepairEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**编号*/
	@Excel(name="编号",width=25)
	private java.lang.String no;
	/**作业人*/
	@Excel(name="作业人",width=25)
	private java.lang.String people;
	/**作业开始时间*/
	@Excel(name="作业开始时间",width=15)
	private java.lang.String startTime;
	/**作业结束时间*/
	@Excel(name="作业结束时间")
	private java.lang.String endTime;
	/**破损位置*/
	@Excel(name="破损位置",width=10)
	private java.lang.String damageLocation;
	/**破损类型*/
	@Excel(name="破损类型",width=10)
	private java.lang.String damageType;
	/**维修方案*/
	@Excel(name="维修方案",width=10)
	private java.lang.String maintenancePlan;
	
	/**破损类型模型*/
	@Excel(name="破损类型模型",width=10)
	private java.lang.String damageTypeModel;
	/**维修方案模型*/
	@Excel(name="维修方案模型",width=10)
	private java.lang.String maintenancePlanModel;
	
	/**修补面积*/
	@Excel(name="修补面积",width=10)
	private java.lang.String repairArea;
	/**灌缝长度*/
	@Excel(name="灌缝长度",width=10)
	private java.lang.String irrigationSeamLength;
	/**图片路径*/
	@Excel(name="图片路径",width=15)
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
	/**破损类型其他*/
	@Excel(name="破损类型其他",width=50)
	private java.lang.String damageTypeOther;
	/**维修方案其他*/
	@Excel(name="维修方案其他",width=50)
	private java.lang.String maintenancePlanOther;
	/**工作量*/
	@Excel(name="工作量",width=15)
	private java.lang.String workload;
	/**创建人登录中文名*/
	@Excel(name="创建人登录中文名",width=50)
	private java.lang.String createByCn;
	private List<FamsCommonFileEntity> file;
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
	 *@return: java.util.Date  作业开始时间
	 */

	@Column(name ="START_TIME",nullable=true)
	public java.lang.String getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  作业开始时间
	 */
	public void setStartTime(java.lang.String startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  作业结束时间
	 */

	@Column(name ="END_TIME",nullable=true)
	public java.lang.String getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  作业结束时间
	 */
	public void setEndTime(java.lang.String endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  破损位置
	 */

	@Column(name ="DAMAGE_LOCATION",nullable=true,length=100)
	public java.lang.String getDamageLocation(){
		return this.damageLocation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  破损位置
	 */
	public void setDamageLocation(java.lang.String damageLocation){
		this.damageLocation = damageLocation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  破损类型
	 */

	@Column(name ="DAMAGE_TYPE",nullable=true,length=100)
	public java.lang.String getDamageType(){
		return this.damageType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  破损类型
	 */
	public void setDamageType(java.lang.String damageType){
		this.damageType = damageType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维修方案
	 */

	@Column(name ="MAINTENANCE_PLAN",nullable=true,length=100)
	public java.lang.String getMaintenancePlan(){
		return this.maintenancePlan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维修方案
	 */
	public void setMaintenancePlan(java.lang.String maintenancePlan){
		this.maintenancePlan = maintenancePlan;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  修补面积
	 */

	@Column(name ="REPAIR_AREA",nullable=true,length=100)
	public java.lang.String getRepairArea(){
		return this.repairArea;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  修补面积
	 */
	public void setRepairArea(java.lang.String repairArea){
		this.repairArea = repairArea;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  灌缝长度
	 */

	@Column(name ="IRRIGATION_SEAM_LENGTH",nullable=true,length=100)
	public java.lang.String getIrrigationSeamLength(){
		return this.irrigationSeamLength;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  灌缝长度
	 */
	public void setIrrigationSeamLength(java.lang.String irrigationSeamLength){
		this.irrigationSeamLength = irrigationSeamLength;
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
	 *@return: java.lang.String  破损类型其他
	 */

	@Column(name ="DAMAGE_TYPE_OTHER",nullable=true,length=2000)
	public java.lang.String getDamageTypeOther(){
		return this.damageTypeOther;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  破损类型其他
	 */
	public void setDamageTypeOther(java.lang.String damageTypeOther){
		this.damageTypeOther = damageTypeOther;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维修方案其他
	 */

	@Column(name ="MAINTENANCE_PLAN_OTHER",nullable=true,length=2000)
	public java.lang.String getMaintenancePlanOther(){
		return this.maintenancePlanOther;
	}
	@Column(name ="create_by_cn",nullable=true)
	public java.lang.String getCreateByCn() {
		return createByCn;
	}

	public void setCreateByCn(java.lang.String createByCn) {
		this.createByCn = createByCn;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维修方案其他
	 */
	public void setMaintenancePlanOther(java.lang.String maintenancePlanOther){
		this.maintenancePlanOther = maintenancePlanOther;
	}
	@Transient
	public java.lang.String getWorkload() {
		return workload;
	}

	public void setWorkload(java.lang.String workload) {
		this.workload = workload;
	}
	@Transient
	public java.lang.String getDamageTypeModel() {
		return damageTypeModel;
	}

	public void setDamageTypeModel(java.lang.String damageTypeModel) {
		this.damageTypeModel = damageTypeModel;
	}
	@Transient
	public java.lang.String getMaintenancePlanModel() {
		return maintenancePlanModel;
	}

	public void setMaintenancePlanModel(java.lang.String maintenancePlanModel) {
		this.maintenancePlanModel = maintenancePlanModel;
	}
	@Transient
	public List<FamsCommonFileEntity> getFile() {
		return file;
	}

	public void setFile(List<FamsCommonFileEntity> file) {
		this.file = file;
	}
	
}