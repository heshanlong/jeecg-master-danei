package com.gbiac.fams.common.flowown.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 通用流程权限拥有表
 * @author onlineGenerator
 * @date 2019-01-15 15:28:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_common_flow_own_detail", schema = "")
@SuppressWarnings("serial")
public class FamsCommonFlowOwnDetailEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**流程权限主表*/
	private java.lang.String flowOwnId;
	/**流程附表*/
	@Excel(name="流程附表",width=15,dictTable ="fams_common_flow_detail",dicCode ="id",dicText ="branch_flag")
	private java.lang.String flowDetailId;
	
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
	 *@return: java.lang.String  流程权限主表
	 */
	
	@Column(name ="FLOW_OWN_ID",nullable=true,length=36)
	public java.lang.String getFlowOwnId(){
		return this.flowOwnId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程权限主表
	 */
	public void setFlowOwnId(java.lang.String flowOwnId){
		this.flowOwnId = flowOwnId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程附表
	 */
	
	@Column(name ="FLOW_DETAIL_ID",nullable=true,length=36)
	public java.lang.String getFlowDetailId(){
		return this.flowDetailId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程附表
	 */
	public void setFlowDetailId(java.lang.String flowDetailId){
		this.flowDetailId = flowDetailId;
	}
	
}
