package com.gbiac.fams.common.reply.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**
 * @Title: Entity
 * @Description: 通用回复表
 * @author 龚道海
 * @date 2019-01-09 10:48:45
 * @version V1.0
 *
 */
@Entity
@Table(name = "fams_common_reply", schema = "")
@SuppressWarnings("serial")
public class FamsCommonReplyEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**模块名称*/
	private java.lang.String module;
	/**业务ID*/
	private java.lang.String businessId;
	/**状态*/
	private java.lang.String state;
	/**回复部门id*/
	@Excel(name="回复部门id",width=15)
	private java.lang.String replyDepartId;
	/**回复人id*/
	private java.lang.String replyUserId;
	/**回复目标任务id*/
	private java.lang.String replyToUserId;
	/**回复内容*/
	private java.lang.String replyContent;
	/**创建时间*/
	private java.util.Date createTime;
	/**冗余字段1*/
	private java.lang.String filed1;
	/**冗余字段2*/
	private java.lang.String filed2;
	//以下与数据库无关
	private java.lang.Integer pageNo;//第几页

	private java.lang.Integer pageSize;//每页多少条

	private String dutyId;//责任单位



	public FamsCommonReplyEntity(){}

	public FamsCommonReplyEntity(String userId, String departId,String module, String businessId, String replyToUserId, String replyContent) {
		this.module=module;
		this.businessId=businessId;
		this.replyToUserId=replyToUserId;
		this.replyUserId=userId;
		this.replyDepartId=departId;
		this.replyContent=replyContent;
		this.createTime=new Date();
	}

	public FamsCommonReplyEntity(String userId, String departId,String module, String businessId,String state,String replyToUserId, String replyContent) {
		this.module=module;
		this.businessId=businessId;
		this.replyToUserId=replyToUserId;
		this.replyUserId=userId;
		this.replyDepartId=departId;
		this.replyContent=replyContent;
		this.createTime=new Date();
		this.state=state;
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

	@Column(name ="STATE",nullable=true,length=20)
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
	 *@return: java.lang.String  回复部门id
	 */

	@Column(name ="REPLY_DEPART_ID",nullable=true,length=36)
	public java.lang.String getReplyDepartId(){
		return this.replyDepartId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  回复部门id
	 */
	public void setReplyDepartId(java.lang.String replyDepartId){
		this.replyDepartId = replyDepartId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  回复人id
	 */

	@Column(name ="REPLY_USER_ID",nullable=true,length=36)
	public java.lang.String getReplyUserId(){
		return this.replyUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  回复人id
	 */
	public void setReplyUserId(java.lang.String replyUserId){
		this.replyUserId = replyUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  回复目标任务id
	 */

	@Column(name ="REPLY_TO_USER_ID",nullable=true,length=36)
	public java.lang.String getReplyToUserId(){
		return this.replyToUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  回复目标任务id
	 */
	public void setReplyToUserId(java.lang.String replyToUserId){
		this.replyToUserId = replyToUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  回复内容
	 */

	@Column(name ="REPLY_CONTENT",nullable=true,length=200)
	public java.lang.String getReplyContent(){
		return this.replyContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  回复内容
	 */
	public void setReplyContent(java.lang.String replyContent){
		this.replyContent = replyContent;
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

	@Transient
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@Transient
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Transient
	public String getDutyId() {
		return dutyId;
	}

	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
}