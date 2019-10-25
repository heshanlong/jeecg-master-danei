package com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity;

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
 * @Description: fams_airportrunway_surfaceclean
 * @author 江家滨
 * @date 2019-01-17 17:07:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_airportrunway_surfaceclean", schema = "")
@SuppressWarnings("serial")
public class FamsAirportrunwaySurfacecleanEntity implements java.io.Serializable {
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
	@Excel(name="作业结束时间",width=15)
	private java.lang.String endTime;
	/**清扫原因*/
	@Excel(name="清扫原因详情",width=50)
	private java.lang.String cleanWhyOther;
	/**清扫位置*/
	@Excel(name="清扫位置详情",width=50)
	private java.lang.String cleanLocationOther;
	/**清扫设备*/
	@Excel(name="清扫设备详情",width=50)
	private java.lang.String cleanEquipmentOther;
	
	/**清扫原因*/
	@Excel(name="清扫原因",width=10)
	private java.lang.String cleanWhy;
	/**清扫位置*/
	@Excel(name="清扫位置",width=10)
	private java.lang.String cleanLocation;
	/**清扫设备*/
	@Excel(name="清扫设备",width=10)
	private java.lang.String cleanEquipment;
	
	
	/**除胶面积*/
	@Excel(name="除胶面积",width=10)
	private java.lang.String cleanArea;
	/**图片路径*/
	@Excel(name="图片路径",width=15)
	private java.lang.String files;
	/**备注*/
	@Excel(name="备注",width=50)
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
	 *@return: java.lang.String  清扫原因
	 */

	@Column(name ="CLEAN_WHY",nullable=true,length=100)
	public java.lang.String getCleanWhy(){
		return this.cleanWhy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  清扫原因
	 */
	public void setCleanWhy(java.lang.String cleanWhy){
		this.cleanWhy = cleanWhy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  清扫位置
	 */

	@Column(name ="CLEAN_LOCATION",nullable=true,length=100)
	public java.lang.String getCleanLocation(){
		return this.cleanLocation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  清扫位置
	 */
	public void setCleanLocation(java.lang.String cleanLocation){
		this.cleanLocation = cleanLocation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  清扫设备
	 */

	@Column(name ="CLEAN_EQUIPMENT",nullable=true,length=100)
	public java.lang.String getCleanEquipment(){
		return this.cleanEquipment;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  清扫设备
	 */
	public void setCleanEquipment(java.lang.String cleanEquipment){
		this.cleanEquipment = cleanEquipment;
	}
	@Column(name ="CLEAN_WHY_OTHER",nullable=true,length=2000)
	public java.lang.String getCleanWhyOther() {
		return cleanWhyOther;
	}

	public void setCleanWhyOther(java.lang.String cleanWhyOther) {
		this.cleanWhyOther = cleanWhyOther;
	}
	@Column(name ="CLEAN_LOCATION_OTHER",nullable=true,length=2000)
	public java.lang.String getCleanLocationOther() {
		return cleanLocationOther;
	}

	public void setCleanLocationOther(java.lang.String cleanLocationOther) {
		this.cleanLocationOther = cleanLocationOther;
	}
	@Column(name ="CLEAN_EQUIPMENT_OTHER",nullable=true,length=2000)
	public java.lang.String getCleanEquipmentOther() {
		return cleanEquipmentOther;
	}

	public void setCleanEquipmentOther(java.lang.String cleanEquipmentOther) {
		this.cleanEquipmentOther = cleanEquipmentOther;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  除胶面积
	 */

	@Column(name ="CLEAN_AREA",nullable=true,length=100)
	public java.lang.String getCleanArea(){
		return this.cleanArea;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  除胶面积
	 */
	public void setCleanArea(java.lang.String cleanArea){
		this.cleanArea = cleanArea;
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
	
	@Column(name ="create_by_cn",nullable=true)
	public java.lang.String getCreateByCn() {
		return createByCn;
	}

	public void setCreateByCn(java.lang.String createByCn) {
		this.createByCn = createByCn;
	}

	@Transient
	public List<FamsCommonFileEntity> getFile() {
		return file;
	}

	public void setFile(List<FamsCommonFileEntity> file) {
		this.file = file;
	}
}