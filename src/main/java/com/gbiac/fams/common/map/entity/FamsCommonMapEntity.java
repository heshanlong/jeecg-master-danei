package com.gbiac.fams.common.map.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 通用地图标注
 * @author 龚道海
 * @date 2019-01-09 11:34:18
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_common_map", schema = "")
@SuppressWarnings("serial")
public class FamsCommonMapEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**模块名称*/
	private java.lang.String module;
	/**业务ID*/
	private java.lang.String businessId;
	/**状态*/
	private java.lang.String state;
	/**地图点位信息*/
	private java.lang.String mapPoints;
	/**地图位置*/
	private java.lang.String mapPositions;
	/**创建用户部门*/
	@Excel(name="创建用户部门",width=15)
	private java.lang.String createDepartId;
	/**创建用户*/
	private java.lang.String createUserId;
	/**创建时间*/
	private java.util.Date createTime;
	/**冗余字段1*/
	private java.lang.String filed1;
	/**冗余字段2*/
	private java.lang.String filed2;

	public FamsCommonMapEntity(){}
	public FamsCommonMapEntity(String userId, String userDepId, String module, String businessId, String mapPoints, String mapPositions) {
		this.module=module;
		this.businessId=businessId;
		this.mapPoints=mapPoints;
		this.mapPositions=mapPositions;
		this.createUserId=userId;
		this.createDepartId=userDepId;
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
	 *@return: java.lang.String  地图点位信息
	 */

	@Column(name ="MAP_POINTS",nullable=true,length=200)
	public java.lang.String getMapPoints(){
		return this.mapPoints;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地图点位信息
	 */
	public void setMapPoints(java.lang.String mapPoints){
		this.mapPoints = mapPoints;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地图位置
	 */

	@Column(name ="MAP_POSITIONS",nullable=true,length=200)
	public java.lang.String getMapPositions(){
		return this.mapPositions;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地图位置
	 */
	public void setMapPositions(java.lang.String mapPositions){
		this.mapPositions = mapPositions;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建用户部门
	 */

	@Column(name ="CREATE_DEPART_ID",nullable=true,length=36)
	public java.lang.String getCreateDepartId(){
		return this.createDepartId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建用户部门
	 */
	public void setCreateDepartId(java.lang.String createDepartId){
		this.createDepartId = createDepartId;
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