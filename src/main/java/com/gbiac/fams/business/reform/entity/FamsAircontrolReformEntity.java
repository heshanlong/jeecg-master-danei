package com.gbiac.fams.business.reform.entity;

import java.math.BigDecimal;
import java.util.Date;
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

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import javax.persistence.Transient;

/**   
 * @Title: Entity
 * @Description: 整改单
 * @author 邓正辉
 * @date 2019-01-09 14:49:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_aircontrol_reform", schema = "")
@SuppressWarnings("serial")
public class FamsAircontrolReformEntity  implements java.io.Serializable  {
	/**主键*/
	private java.lang.String id;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String number;
	/**单位id*/
//	@Excel(name="单位id",width=15)
	private java.lang.String dutyId;
	/**责任单位*/
	@Excel(name="责任单位",width=15,dicCode="reform_d_c")
	private java.lang.String dutyCompany;
	/**责任单位组织机构编码*/
//	@Excel(name="责任单位组织机构编码",width=30)
	private java.lang.String dutyOrgCode;
	/**当事人姓名*/
	@Excel(name="当事人姓名",width=15)
	private java.lang.String dutyPersonName;
	/**当事人联系方式*/
	@Excel(name="当事人联系方式",width=15)
	private java.lang.String dutyPersonMobile;
	/**违章行为*/
	@Excel(name="违章行为",width=15)
	private java.lang.String violationName;
	/**处理结果*/
	@Excel(name="处理结果",width=15,dicCode="reform_p_r")
	private java.lang.String panishResult;
	/**派发人姓名*/
	@Excel(name="派发人姓名",width=15)
	private java.lang.String decideName;
	/**派发日期*/
	@Excel(name="派发日期",width=15,format = "yyyy-MM-dd HH:mm")
	private java.util.Date decideDate;
	/**整改措施描述*/
	private java.lang.String reformWay;
	/**整改完成时间*/
	private java.util.Date reformDate;
	/**验收时间*/
	private java.util.Date checkDate;
	/**验收不通过理由*/
	@Excel(name="验收不通过理由",width=15)
	private java.lang.String checkfailDec;
	/**备注*/
	private java.lang.String remark;
	/**状态*/
	@Excel(name="状态",width=15,dicCode="reform_sta")
//	1派发（未接收）
//	2接收 （整改中）
//  3整改完成（待验收）
//	4验收通过 （已关闭）
//	5验收不通过
	//6已撤销
	private java.lang.String status;
	//以下与数据库无关
	private String rebackDes;
	private java.lang.Integer pageNoApp;//第几页
	private java.lang.Integer pageSizeApp;//每页多少条
	private  String pctureApp;//图片ids，形如id；id;id
	private  String pctureAfApp;//图片第二张ids，形如id；id;id
	private  String keyWord;//关键字
	private String orderType;//排序 1先按状态排列，“待接收”>“整改中”>“验收不通过”；再按派发时间顺序排列显示
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
	 *@return: java.lang.String  单位id
	 */

	@Column(name ="DUTY_ID",nullable=true,length=32)
	public java.lang.String getDutyId(){
		return this.dutyId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位id
	 */
	public void setDutyId(java.lang.String dutyId){
		this.dutyId = dutyId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  责任单位
	 */

	@Column(name ="DUTY_COMPANY",nullable=true,length=32)
	public java.lang.String getDutyCompany(){
		return this.dutyCompany;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  责任单位
	 */
	public void setDutyCompany(java.lang.String dutyCompany){
		this.dutyCompany = dutyCompany;
	}

	@Column(name ="DUTY_ORG_CODE",nullable=true,length=32)
	public String getDutyOrgCode() {
		return dutyOrgCode;
	}

	public void setDutyOrgCode(String dutyOrgCode) {
		this.dutyOrgCode = dutyOrgCode;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当事人姓名
	 */

	@Column(name ="DUTY_PERSON_NAME",nullable=true,length=32)
	public java.lang.String getDutyPersonName(){
		return this.dutyPersonName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当事人姓名
	 */
	public void setDutyPersonName(java.lang.String dutyPersonName){
		this.dutyPersonName = dutyPersonName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当事人联系方式
	 */

	@Column(name ="DUTY_PERSON_MOBILE",nullable=true,length=32)
	public java.lang.String getDutyPersonMobile(){
		return this.dutyPersonMobile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当事人联系方式
	 */
	public void setDutyPersonMobile(java.lang.String dutyPersonMobile){
		this.dutyPersonMobile = dutyPersonMobile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  违章行为
	 */

	@Column(name ="VIOLATION_NAME",nullable=true,length=32)
	public java.lang.String getViolationName(){
		return this.violationName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  违章行为
	 */
	public void setViolationName(java.lang.String violationName){
		this.violationName = violationName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  处理结果
	 */

	@Column(name ="PANISH_RESULT",nullable=true,length=100)
	public java.lang.String getPanishResult(){
		return this.panishResult;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  处理结果
	 */
	public void setPanishResult(java.lang.String panishResult){
		this.panishResult = panishResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  派发人姓名
	 */

	@Column(name ="DECIDE_NAME",nullable=true,length=32)
	public java.lang.String getDecideName(){
		return this.decideName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  派发人姓名
	 */
	public void setDecideName(java.lang.String decideName){
		this.decideName = decideName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  派发日期
	 */

	@Column(name ="DECIDE_DATE",nullable=true,length=32)
	public java.util.Date getDecideDate(){
		return this.decideDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  派发日期
	 */
	public void setDecideDate(java.util.Date decideDate){
		this.decideDate = decideDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  整改措施描述
	 */

	@Column(name ="REFORM_WAY",nullable=true,length=32)
	public java.lang.String getReformWay(){
		return this.reformWay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  整改措施描述
	 */
	public void setReformWay(java.lang.String reformWay){
		this.reformWay = reformWay;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  整改完成时间
	 */

	@Column(name ="REFORM_DATE",nullable=true,length=32)
	public java.util.Date getReformDate(){
		return this.reformDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  整改完成时间
	 */
	public void setReformDate(java.util.Date reformDate){
		this.reformDate = reformDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  验收时间
	 */

	@Column(name ="CHECK_DATE",nullable=true,length=32)
	public java.util.Date getCheckDate(){
		return this.checkDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  验收时间
	 */
	public void setCheckDate(java.util.Date checkDate){
		this.checkDate = checkDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  验收不通过理由
	 */

	@Column(name ="CHECKFAIL_DEC",nullable=true,length=32)
	public java.lang.String getCheckfailDec(){
		return this.checkfailDec;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  验收不通过理由
	 */
	public void setCheckfailDec(java.lang.String checkfailDec){
		this.checkfailDec = checkfailDec;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */

	@Column(name ="STATUS",nullable=true,length=10)
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
	public String getPctureAfApp() {
		return pctureAfApp;
	}

	public void setPctureAfApp(String pctureAfApp) {
		this.pctureAfApp = pctureAfApp;
	}
	@Transient
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	@Transient
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
	@Transient
	public String getRebackDes() {
		return rebackDes;
	}

	public void setRebackDes(String rebackDes) {
		this.rebackDes = rebackDes;
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