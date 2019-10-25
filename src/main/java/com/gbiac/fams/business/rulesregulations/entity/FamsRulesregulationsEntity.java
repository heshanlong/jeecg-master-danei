package com.gbiac.fams.business.rulesregulations.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;

/**   
 * @Title: Entity
 * @Description: 规章制度
 * @author onlineGenerator
 * @date 2019-01-09 15:45:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_rulesregulations", schema = "")
@SuppressWarnings("serial")
public class FamsRulesregulationsEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**pid*/
	@Excel(name="pid",width=15)
	private java.lang.String pid;
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
	/**名称*/
	@Excel(name="名称",width=15)
	private java.lang.String rulesName;
	/**格式  0文件夹，1文件*/
	@Excel(name="格式",width=15)
	private java.lang.Integer rulesType;
	/**后缀*/
	@Excel(name="后缀",width=15)
	private java.lang.String rulesSuffix;
	/**文件路径*/
	@Excel(name="文件路径",width=15)
	private java.lang.String rulesPathUrl;
	@Excel(name="删除状态0未删除，1已删除",width=15)
	private Integer delType;
	@Excel(name="关键字",width=15)
	private String keyword;
	@Excel(name="备注",width=15)
	private String remark;
	@Excel(name="0所有人 1飞管",width=15)
	private Integer visible;
	@Excel(name="办文单位",width=15)
	private  String articleUnit;
	@Excel(name="体裁分类",width=15)
	private  String genreClassification;
	@Excel(name="发文日期",width=15)
	private Date dispatchDate;
	@Excel(name="代替规章",width=15)
	private  String replaceRegulations;
	
	/*======================= 普助字段======================= */
	//通用文件表
	private FamsCommonFileEntity famsCommonFileEntity;
	//图标
	private String rulesFileIcon;
	private String iconCls;	
	//图片路径
	private String rulesPathUrlName;
	//目录里文件总数
	private Integer fileCount;
	//父名称
	private String pName;
	
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
	 *@return: java.lang.String  pid
	 */

	@Column(name ="PID",nullable=true,length=32)
	public java.lang.String getPid(){
		return this.pid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  pid
	 */
	public void setPid(java.lang.String pid){
		this.pid = pid;
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
	 *@return: java.lang.String  名称
	 */

	@Column(name ="RULES_NAME",nullable=true,length=200)
	public java.lang.String getRulesName(){
		return this.rulesName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setRulesName(java.lang.String rulesName){
		this.rulesName = rulesName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  格式
	 */

	@Column(name ="RULES_TYPE",nullable=true,length=32)
	public java.lang.Integer getRulesType(){
		return this.rulesType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  格式
	 */
	public void setRulesType(java.lang.Integer rulesType){
		this.rulesType = rulesType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  后缀
	 */

	@Column(name ="RULES_SUFFIX",nullable=true,length=32)
	public java.lang.String getRulesSuffix(){
		return this.rulesSuffix;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  后缀
	 */
	public void setRulesSuffix(java.lang.String rulesSuffix){
		this.rulesSuffix = rulesSuffix;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件路径
	 */

	@Column(name ="RULES_PATH_URL",nullable=true,length=200)
	public java.lang.String getRulesPathUrl(){
		return this.rulesPathUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件路径
	 */
	public void setRulesPathUrl(java.lang.String rulesPathUrl){
		this.rulesPathUrl = rulesPathUrl;
	}
	
	
	@Column(name ="DEL_TYPE",nullable=true,length=2)
	public Integer getDelType() {
		return delType;
	}
	public void setDelType(Integer delType) {
		this.delType = delType;
	}

	@Column(name ="KEYWORD",nullable=true,length=255)
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name ="REMARK",nullable=true,length=2000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name ="VISIBLE",nullable=true,length=2000)
	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}
	
	@Column(name ="ARTICLE_UNIT",nullable=true,length=255)
	public String getArticleUnit() {
		return articleUnit;
	}

	public void setArticleUnit(String articleUnit) {
		this.articleUnit = articleUnit;
	}

	@Column(name ="GENRE_CLASSIFICATION",nullable=true,length=255)
	public String getGenreClassification() {
		return genreClassification;
	}

	public void setGenreClassification(String genreClassification) {
		this.genreClassification = genreClassification;
	}

	@Column(name ="DISPATCH_DATE",nullable=true,length=255)
	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	@Column(name ="REPLACE_REGULATIONS",nullable=true,length=255)
	public String getReplaceRegulations() {
		return replaceRegulations;
	}

	public void setReplaceRegulations(String replaceRegulations) {
		this.replaceRegulations = replaceRegulations;
	}

	@Transient
	public FamsCommonFileEntity getFamsCommonFileEntity() {
		return famsCommonFileEntity;
	}
	public void setFamsCommonFileEntity(FamsCommonFileEntity famsCommonFileEntity) {
		this.famsCommonFileEntity = famsCommonFileEntity;
	}



	@Transient
	public String getRulesFileIcon() {
		return rulesFileIcon;
	}

	public void setRulesFileIcon(String rulesFileIcon) {
		this.rulesFileIcon = rulesFileIcon;
	}

	@Transient
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Transient
	public String getRulesPathUrlName() {
		return rulesPathUrlName;
	}

	public void setRulesPathUrlName(String rulesPathUrlName) {
		this.rulesPathUrlName = rulesPathUrlName;
	}

	@Transient
	public Integer getFileCount() {
		return fileCount;
	}

	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}

	@Transient
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}	
	
	
}
