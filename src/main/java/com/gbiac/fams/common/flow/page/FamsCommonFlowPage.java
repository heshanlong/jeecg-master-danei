package com.gbiac.fams.common.flow.page;

import com.gbiac.fams.common.flow.entity.FamsCommonFlowDetailEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Title: Entity
 * @Description: 通用流程主表
 * @author onlineGenerator
 * @date 2019-01-15 11:14:22
 * @version V1.0   
 *
 */
public class FamsCommonFlowPage implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**模块名称*/
    @Excel(name="模块名称")
	private java.lang.String module;
	/**业务名称*/
    @Excel(name="业务名称")
	private java.lang.String business;
	/**流程名称*/
    @Excel(name="流程名称")
	private java.lang.String flowName;
	/**流程备注*/
    @Excel(name="流程备注")
	private java.lang.String flowNote;
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
	 *@return: java.lang.String  业务名称
	 */
	public java.lang.String getBusiness(){
		return this.business;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务名称
	 */
	public void setBusiness(java.lang.String business){
		this.business = business;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程名称
	 */
	public java.lang.String getFlowName(){
		return this.flowName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程名称
	 */
	public void setFlowName(java.lang.String flowName){
		this.flowName = flowName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程备注
	 */
	public java.lang.String getFlowNote(){
		return this.flowNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程备注
	 */
	public void setFlowNote(java.lang.String flowNote){
		this.flowNote = flowNote;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建用户
	 */
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
	
	/**保存-通用流程附表*/
    @ExcelCollection(name="通用流程附表")
	private List<FamsCommonFlowDetailEntity> famsCommonFlowDetailList = new ArrayList<FamsCommonFlowDetailEntity>();
	public List<FamsCommonFlowDetailEntity> getFamsCommonFlowDetailList() {
		return famsCommonFlowDetailList;
	}
	public void setFamsCommonFlowDetailList(List<FamsCommonFlowDetailEntity> famsCommonFlowDetailList) {
		this.famsCommonFlowDetailList = famsCommonFlowDetailList;
	}
	
}
