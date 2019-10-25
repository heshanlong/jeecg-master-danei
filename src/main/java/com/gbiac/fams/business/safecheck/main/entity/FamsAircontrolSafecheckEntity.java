package com.gbiac.fams.business.safecheck.main.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gbiac.fams.common.Util.DateJacksonConverter;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**   
 * @Title: Entity
 * @Description: 航班保障作业检查单主记录
 * @author 邓正辉
 * @date 2019-01-21 13:50:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_aircontrol_safecheck", schema = "")
@SuppressWarnings("serial")
public class FamsAircontrolSafecheckEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String number;
	/**航班计划id*/
	@Excel(name="航班计划id",width=15)
	private java.lang.String airPlanId;
	/**航班号*/
	@Excel(name="航班号",width=15)
	private java.lang.String airNumber;
	/**航线*/
	@Excel(name="航线",width=15)
	private java.lang.String airLine;
	/**机尾号*/
	@Excel(name="机尾号",width=15)
	private java.lang.String airTail;
	/**机型*/
	@Excel(name="机型",width=15)
	private java.lang.String airModel;
	/**停机位*/
	@Excel(name="停机位",width=15)
	private java.lang.String airSlots;
	/**检查人*/
	private java.lang.String checkerName;
	/**检查日期*/
	@Excel(name="检查日期",width=15,format = "yyyy-MM-dd")
//	@JsonDeserialize(using = DateJacksonConverter.class)
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date checkDate;
	/**实际时间*/
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Excel(name="实际时间",width=15,format = "yyyy-MM-dd HH:mm")
//	@JsonDeserialize(using = DateJacksonConverter.class)
	private java.util.Date actualDate;
	/**预计时间*/
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonDeserialize(using = DateJacksonConverter.class)
	@Excel(name="预计时间",width=15,format = "yyyy-MM-dd HH:mm")
	private java.util.Date preDate;
	
	
	/**检查开始时间*/
	@Excel(name="开始时间",width=15)
	private java.lang.String checkTimeStart;
	/**检查结束时间*/
	@Excel(name="结束时间",width=15)
	private java.lang.String checkTimeEnd;
	
	/**检查总结果*/
	private java.lang.String result;
	/**检查结果描述*/
	private java.lang.String resultDes;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark;
	/**状态1未删除2删除*/
	@Excel(name="状态",width=15)
	private java.lang.String status;
	//以下与数据库无关
	private java.lang.Integer pageNoApp;//第几页
	private java.lang.Integer pageSizeApp;//每页多少条
	private  String pctureApp;//图片ids，形如id；id;id
	private String radioResult;//id:1;id:2
	private String dateStart;
	private  String dateEnd;
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

	@Column(name ="NUMBER",nullable=true,length=32)
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
	 *@return: java.lang.String  航班计划id
	 */

	@Column(name ="AIR_PLAN_ID",nullable=true,length=32)
	public java.lang.String getAirPlanId(){
		return this.airPlanId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班计划id
	 */
	public void setAirPlanId(java.lang.String airPlanId){
		this.airPlanId = airPlanId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班号
	 */

	@Column(name ="AIR_NUMBER",nullable=true,length=32)
	public java.lang.String getAirNumber(){
		return this.airNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班号
	 */
	public void setAirNumber(java.lang.String airNumber){
		this.airNumber = airNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航线
	 */

	@Column(name ="AIR_LINE",nullable=true,length=32)
	public java.lang.String getAirLine(){
		return this.airLine;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航线
	 */
	public void setAirLine(java.lang.String airLine){
		this.airLine = airLine;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机尾号
	 */

	@Column(name ="AIR_TAIL",nullable=true,length=32)
	public java.lang.String getAirTail(){
		return this.airTail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机尾号
	 */
	public void setAirTail(java.lang.String airTail){
		this.airTail = airTail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机型
	 */

	@Column(name ="AIR_MODEL",nullable=true,length=32)
	public java.lang.String getAirModel(){
		return this.airModel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机型
	 */
	public void setAirModel(java.lang.String airModel){
		this.airModel = airModel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  停机位
	 */

	@Column(name ="AIR_SLOTS",nullable=true,length=32)
	public java.lang.String getAirSlots(){
		return this.airSlots;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  停机位
	 */
	public void setAirSlots(java.lang.String airSlots){
		this.airSlots = airSlots;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查人
	 */

	@Column(name ="CHECKER_NAME",nullable=true,length=32)
	public java.lang.String getCheckerName(){
		return this.checkerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查人
	 */
	public void setCheckerName(java.lang.String checkerName){
		this.checkerName = checkerName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  检查日期
	 */

	@Column(name ="CHECK_DATE",nullable=true,length=32)
	public java.util.Date getCheckDate(){
		return this.checkDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  检查日期
	 */
	public void setCheckDate(java.util.Date checkDate){
		this.checkDate = checkDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  实际时间
	 */

	@Column(name ="ACTUAL_DATE",nullable=true,length=32)
	public java.util.Date getActualDate(){
		return this.actualDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  实际时间
	 */
	public void setActualDate(java.util.Date actualDate){
		this.actualDate = actualDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预计时间
	 */

	@Column(name ="PRE_DATE",nullable=true,length=32)
	public java.util.Date getPreDate(){
		return this.preDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预计时间
	 */
	public void setPreDate(java.util.Date preDate){
		this.preDate = preDate;
	}
	
	@Column(name="CHECK_TIME_START",nullable=true,length=32)
	public java.lang.String getCheckTimeStart(){
		return this.checkTimeStart;
	}
	
	public void setCheckTimeStart(java.lang.String checkTimeStart) {
		this.checkTimeStart = checkTimeStart;
	}
	
	@Column(name="CHECK_TIME_END",nullable=true,length=32)
	public java.lang.String getCheckTimeEnd(){
		return this.checkTimeEnd;
	}
	
	public void setCheckTimeEnd(java.lang.String checkTimeEnd) {
		this.checkTimeEnd = checkTimeEnd;
	}
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查总结果
	 */

	@Column(name ="RESULT",nullable=true,length=32)
	public java.lang.String getResult(){
		return this.result;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查总结果
	 */
	public void setResult(java.lang.String result){
		this.result = result;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查结果描述
	 */

	@Column(name ="RESULT_DES",nullable=true,length=32)
	public java.lang.String getResultDes(){
		return this.resultDes;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARK",nullable=true,length=32)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查结果描述
	 */
	public void setResultDes(java.lang.String resultDes){
		this.resultDes = resultDes;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */

	@Column(name ="STATUS",nullable=true,length=32)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}

	@Transient
	public Integer getPageNoApp() {
		return pageNoApp;
	}

	public void setPageNoApp(Integer pageNoApp) {
		this.pageNoApp = pageNoApp;
	}
	@Transient
	public Integer getPageSizeApp() {
		return pageSizeApp;
	}

	public void setPageSizeApp(Integer pageSizeApp) {
		this.pageSizeApp = pageSizeApp;
	}
	@Transient
	public String getPctureApp() {
		return pctureApp;
	}

	public void setPctureApp(String pctureApp) {
		this.pctureApp = pctureApp;
	}
	@Transient
	public String getRadioResult() {
		return radioResult;
	}

	public void setRadioResult(String radioResult) {
		this.radioResult = radioResult;
	}
	@Transient
	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	@Transient
	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	//以下为jeecg平台字段补充
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
}