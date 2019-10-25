package com.gbiac.fams.common.file.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 通用文件表
 * @author 龚道海
 * @date 2019-01-08 16:21:48
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_common_file", schema = "")
@SuppressWarnings("serial")
public class FamsCommonFileEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**模块名称*/
	private java.lang.String module;
	/**业务ID*/
	@Excel(name="业务ID",width=15)
	private java.lang.String businessId;
	/**状态*/
	private java.lang.String state;
	/**文件名称*/
	private java.lang.String fileName;
	/**文件类型*/
	private java.lang.String fileType;
	/**文件大小*/
	private java.lang.Double fileSize;
	/**文件路径*/
	private java.lang.String filePath;
	/**创建用户*/
	private java.lang.String createUserId;
	/**创建时间*/
	private java.util.Date createTime;
	/**是否启用*/
	@Excel(name="是否启用",width=15)
	private java.lang.String isEnable;
	/**冗余字段1*/
	@Excel(name="冗余字段1",width=15)
	private java.lang.String filed1;
	/**冗余字段2*/
	@Excel(name="冗余字段2",width=15)
	private java.lang.String filed2;

	public FamsCommonFileEntity(){}
	public FamsCommonFileEntity(String createUserId,String module, String businessId,String state,String fileName,String fileType,Double fileSize,String filePath){
		this.createUserId=createUserId;
		this.module=module;
		this.businessId=businessId;
		this.state=state;
		this.fileName=fileName;
		this.fileType=fileType;
		this.fileSize=fileSize;
		this.filePath=filePath;
		this.createTime=new Date();
		this.isEnable="Y";
	}
	public FamsCommonFileEntity(FamsCommonFileEntity f) {
		this.module=f.getModule();
		this.businessId=f.getBusinessId();
		this.state=f.getState();
		this.fileName=f.getFileName();
		this.fileType=f.getFileType();
		this.fileSize=f.getFileSize();
		this.filePath=f.getFilePath();
		this.createUserId=f.getCreateUserId();
		this.createTime=f.getCreateTime();
		this.isEnable=f.getIsEnable();
		this.filed1=f.getFiled1();
		this.filed2=f.getFiled2();
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
	 *@return: java.lang.String  模块名称
	 */

	@Column(name ="MODULE",nullable=true,length=50)
	public java.lang.String getModule(){
		return this.module;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模块名称
	 */
	public void setModule(java.lang.String module){
		this.module = module;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务ID
	 */

	@Column(name ="BUSINESS_ID",nullable=true,length=36)
	public java.lang.String getBusinessId(){
		return this.businessId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务ID
	 */
	public void setBusinessId(java.lang.String businessId){
		this.businessId = businessId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */

	@Column(name ="STATE",nullable=true,length=10)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件名称
	 */

	@Column(name ="FILE_NAME",nullable=true,length=100)
	public java.lang.String getFileName(){
		return this.fileName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件名称
	 */
	public void setFileName(java.lang.String fileName){
		this.fileName = fileName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件类型
	 */

	@Column(name ="FILE_TYPE",nullable=true,length=50)
	public java.lang.String getFileType(){
		return this.fileType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件类型
	 */
	public void setFileType(java.lang.String fileType){
		this.fileType = fileType;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  文件大小
	 */

	@Column(name ="FILE_SIZE",nullable=true,length=50)
	public java.lang.Double getFileSize(){
		return this.fileSize;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  文件大小
	 */
	public void setFileSize(java.lang.Double fileSize){
		this.fileSize = fileSize;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件路径
	 */

	@Column(name ="FILE_PATH",nullable=true,length=200)
	public java.lang.String getFilePath(){
		return this.filePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件路径
	 */
	public void setFilePath(java.lang.String filePath){
		this.filePath = filePath;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */

	@Column(name ="CREATE_TIME",nullable=true,length=50)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否启用
	 */

	@Column(name ="IS_ENABLE",nullable=true,length=1)
	public java.lang.String getIsEnable(){
		return this.isEnable;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否启用
	 */
	public void setIsEnable(java.lang.String isEnable){
		this.isEnable = isEnable;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  冗余字段1
	 */

	@Column(name ="FILED1",nullable=true,length=50)
	public java.lang.String getFiled1(){
		return this.filed1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  冗余字段1
	 */
	public void setFiled1(java.lang.String filed1){
		this.filed1 = filed1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  冗余字段2
	 */

	@Column(name ="FILED2",nullable=true,length=50)
	public java.lang.String getFiled2(){
		return this.filed2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  冗余字段2
	 */
	public void setFiled2(java.lang.String filed2){
		this.filed2 = filed2;
	}
}