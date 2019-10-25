package com.gbiac.fams.common.push.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 通用推送日志
 * @author 龚道海
 * @date 2019-01-09 09:52:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_common_push", schema = "")
@SuppressWarnings("serial")
public class FamsCommonPushEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**模块名称*/
	private java.lang.String module;
	/**业务ID*/
	private java.lang.String businessId;
	/**状态*/
	private java.lang.String state;
	/**推送目标人物id*/
	private java.lang.String pushToUserId;
	/**手机imei号*/
	@Excel(name="手机imei号",width=15)
	private java.lang.String imei;
	/**推送内容*/
	@Excel(name="推送内容",width=15)
	private java.lang.String pushContent;
	/**创建用户*/
	private java.lang.String createUserId;
	/**创建时间*/
	private java.util.Date createTime;
	/**冗余字段1*/
	private java.lang.String filed1;
	/**冗余字段2*/
	private java.lang.String filed2;

	public FamsCommonPushEntity(){}

	public FamsCommonPushEntity(String userId, String module, String businessId, String pushToUserId, String imei,String pushContent) {
		this.createUserId=userId;
		this.module=module;
		this.businessId=businessId;
		this.pushToUserId=pushToUserId;
		this.imei=imei;
		this.pushContent=pushContent;
		this.createTime=new Date();
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
	 *@return: java.lang.String  推送目标人物id
	 */

	@Column(name ="PUSH_TO_USER_ID",nullable=true,length=36)
	public java.lang.String getPushToUserId(){
		return this.pushToUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  推送目标人物id
	 */
	public void setPushToUserId(java.lang.String pushToUserId){
		this.pushToUserId = pushToUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机imei号
	 */

	@Column(name ="IMEI",nullable=true,length=50)
	public java.lang.String getImei(){
		return this.imei;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机imei号
	 */
	public void setImei(java.lang.String imei){
		this.imei = imei;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  推送内容
	 */

	@Column(name ="PUSH_CONTENT",nullable=true,length=500)
	public java.lang.String getPushContent(){
		return this.pushContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  推送内容
	 */
	public void setPushContent(java.lang.String pushContent){
		this.pushContent = pushContent;
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

	@Column(name ="CREATE_TIME",nullable=true,length=20)
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