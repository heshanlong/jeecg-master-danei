package com.gbiac.fams.common.progress.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 通用进度表
 * @author 龚道海
 * @date 2019-01-09 10:44:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_common_progress", schema = "")
@SuppressWarnings("serial")
public class FamsCommonProgressEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**模块名称*/
	private java.lang.String module;
	/**业务ID*/
	private java.lang.String businessId;
	/**状态*/
	private java.lang.String state;
	/**操作部门id*/
	private java.lang.String optionDepartId;
	/**操作用户id*/
	private java.lang.String optionUserId;
	/**操作时间*/
	private java.util.Date optionTime;
	/**操作标志*/
	private java.lang.String optionFlag;
	/**操作备注*/
	private java.lang.String optionNote;
	/**操作节点id*/
	private java.lang.String optionNodeId;
	/**操作内容*/
	@Excel(name="操作内容",width=15)
	private java.lang.String optionContent;
	/**冗余字段1*/
	@Excel(name="冗余字段1",width=15)
	private java.lang.String filed1;
	/**冗余字段2*/
	@Excel(name="冗余字段2",width=15)
	private java.lang.String filed2;

	public FamsCommonProgressEntity(){}
	public FamsCommonProgressEntity(String userId, String userDepId, String module, String businessId, String flag, String note, String content, String nodeId,String state) {
		this.module=module;
		this.businessId=businessId;
		this.optionFlag=flag;
		this.optionNote=note;
		this.optionContent=content;
		this.optionNodeId=nodeId;
		this.state=state;
		this.optionDepartId=userDepId;
		this.optionUserId=userId;
		this.optionTime=new Date();
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
	 *@return: java.lang.String  操作部门id
	 */

	@Column(name ="OPTION_DEPART_ID",nullable=true,length=36)
	public java.lang.String getOptionDepartId(){
		return this.optionDepartId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作部门id
	 */
	public void setOptionDepartId(java.lang.String optionDepartId){
		this.optionDepartId = optionDepartId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作用户id
	 */

	@Column(name ="OPTION_USER_ID",nullable=true,length=36)
	public java.lang.String getOptionUserId(){
		return this.optionUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作用户id
	 */
	public void setOptionUserId(java.lang.String optionUserId){
		this.optionUserId = optionUserId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  操作时间
	 */

	@Column(name ="OPTION_TIME",nullable=true,length=20)
	public java.util.Date getOptionTime(){
		return this.optionTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  操作时间
	 */
	public void setOptionTime(java.util.Date optionTime){
		this.optionTime = optionTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作标志
	 */

	@Column(name ="OPTION_FLAG",nullable=true,length=20)
	public java.lang.String getOptionFlag(){
		return this.optionFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作标志
	 */
	public void setOptionFlag(java.lang.String optionFlag){
		this.optionFlag = optionFlag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作备注
	 */

	@Column(name ="OPTION_NOTE",nullable=true,length=50)
	public java.lang.String getOptionNote(){
		return this.optionNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作备注
	 */
	public void setOptionNote(java.lang.String optionNote){
		this.optionNote = optionNote;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作节点id
	 */

	@Column(name ="OPTION_NODE_ID",nullable=true,length=36)
	public java.lang.String getOptionNodeId(){
		return this.optionNodeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作节点id
	 */
	public void setOptionNodeId(java.lang.String optionNodeId){
		this.optionNodeId = optionNodeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作内容
	 */

	@Column(name ="OPTION_CONTENT",nullable=true,length=100)
	public java.lang.String getOptionContent(){
		return this.optionContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作内容
	 */
	public void setOptionContent(java.lang.String optionContent){
		this.optionContent = optionContent;
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