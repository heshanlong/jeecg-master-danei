package com.gbiac.fams.common.flow.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 通用流程主表
 * @author onlineGenerator
 * @date 2019-01-15 11:14:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_common_flow_detail", schema = "")
@SuppressWarnings("serial")
public class FamsCommonFlowDetailEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**流程主表id*/
	private java.lang.String flowId;
	/**下个节点*/
	@Excel(name="下个节点",width=15,dictTable ="fams_common_flow_detail",dicCode ="id",dicText ="node_name")
	private java.lang.String nextNodeId;
	/**是否已到最后节点*/
	@Excel(name="是否已到最后节点",width=15,dicCode="sf_yn")
	private java.lang.String isEnd;
	/**节点名称*/
	@Excel(name="节点名称",width=15)
	private java.lang.String nodeName;
	/**分支名称*/
	@Excel(name="分支名称",width=15)
	private java.lang.String branchName;
	/**分支标识*/
	@Excel(name="分支标识",width=15)
	private java.lang.String branchFlag;
	/**创建用户*/
	private java.lang.String createName;
	/**创建用户*/
	private java.lang.String createUserId;
	/**创建时间*/
	private java.util.Date createTime;
	
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
	 *@return: java.lang.String  流程主表id
	 */
	
	@Column(name ="FLOW_ID",nullable=true,length=36)
	public java.lang.String getFlowId(){
		return this.flowId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程主表id
	 */
	public void setFlowId(java.lang.String flowId){
		this.flowId = flowId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下个节点
	 */
	
	@Column(name ="NEXT_NODE_ID",nullable=true,length=36)
	public java.lang.String getNextNodeId(){
		return this.nextNodeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下个节点
	 */
	public void setNextNodeId(java.lang.String nextNodeId){
		this.nextNodeId = nextNodeId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否已到最后节点
	 */
	
	@Column(name ="IS_END",nullable=true,length=10)
	public java.lang.String getIsEnd(){
		return this.isEnd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否已到最后节点
	 */
	public void setIsEnd(java.lang.String isEnd){
		this.isEnd = isEnd;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  节点名称
	 */
	
	@Column(name ="NODE_NAME",nullable=true,length=50)
	public java.lang.String getNodeName(){
		return this.nodeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  节点名称
	 */
	public void setNodeName(java.lang.String nodeName){
		this.nodeName = nodeName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分支名称
	 */
	
	@Column(name ="BRANCH_NAME",nullable=true,length=50)
	public java.lang.String getBranchName(){
		return this.branchName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分支名称
	 */
	public void setBranchName(java.lang.String branchName){
		this.branchName = branchName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分支标识
	 */
	
	@Column(name ="BRANCH_FLAG",nullable=true,length=50)
	public java.lang.String getBranchFlag(){
		return this.branchFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分支标识
	 */
	public void setBranchFlag(java.lang.String branchFlag){
		this.branchFlag = branchFlag;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建用户
	 */
	
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建用户
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
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
	
	@Column(name ="CREATE_TIME",nullable=true,length=32)
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
	
}
