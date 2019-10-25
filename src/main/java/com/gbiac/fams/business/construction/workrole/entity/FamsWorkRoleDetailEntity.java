package com.gbiac.fams.business.construction.workrole.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 施工角色表
 * @author onlineGenerator
 * @date 2019-03-25 11:35:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_work_role_detail", schema = "")
@SuppressWarnings("serial")
public class FamsWorkRoleDetailEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**施工管理角色id*/
	private java.lang.String workRoleId;
	/**流程节点*/
	@Excel(name="流程节点",width=15,dictTable ="fams_work_node_info",dicCode ="id",dicText ="task_key")
	private java.lang.String infoId;
	/**能否操作*/
	@Excel(name="能否操作",width=15,dicCode="sf_yn")
	private java.lang.String onlySee;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  施工管理角色id
	 */
	
	@Column(name ="WORK_ROLE_ID",nullable=true,length=36)
	public java.lang.String getWorkRoleId(){
		return this.workRoleId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工管理角色id
	 */
	public void setWorkRoleId(java.lang.String workRoleId){
		this.workRoleId = workRoleId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程节点
	 */
	
	@Column(name ="INFO_ID",nullable=true,length=36)
	public java.lang.String getInfoId(){
		return this.infoId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程节点
	 */
	public void setInfoId(java.lang.String infoId){
		this.infoId = infoId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  能否操作
	 */
	
	@Column(name ="ONLY_SEE",nullable=true,length=10)
	public java.lang.String getOnlySee(){
		return this.onlySee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  能否操作
	 */
	public void setOnlySee(java.lang.String onlySee){
		this.onlySee = onlySee;
	}
	
}
