package com.gbiac.fams.business.construction.workappstate.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @Title: Entity
 * @Description: 施工管理APP端状态配置表
 * @author onlineGenerator
 * @date 2019-03-29 18:12:11
 * @version V1.0
 *
 */
@Entity
@Table(name = "fams_work_app_state_detail", schema = "")
@SuppressWarnings("serial")
public class FamsWorkAppStateDetailEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**外键*/
	private java.lang.String stateId;
	/**节点id*/
	@Excel(name="节点id",width=15,dictTable ="fams_work_node_info",dicCode ="id",dicText ="task_key")
	private java.lang.String nodeId;
	/**能否操作*/
	@Excel(name="能否操作",width=15,dicCode="sf_yn")
	private java.lang.String onlySee;

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
	 *@return: java.lang.String  外键
	 */

	@Column(name ="STATE_ID",nullable=true,length=36)
	public java.lang.String getStateId(){
		return this.stateId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外键
	 */
	public void setStateId(java.lang.String stateId){
		this.stateId = stateId;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  节点id
	 */

	@Column(name ="NODE_ID",nullable=true,length=36)
	public java.lang.String getNodeId(){
		return this.nodeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  节点id
	 */
	public void setNodeId(java.lang.String nodeId){
		this.nodeId = nodeId;
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
