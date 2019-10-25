package com.gbiac.fams.common.flowown.page;

import com.gbiac.fams.common.flowown.entity.FamsCommonFlowOwnDetailEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Title: Entity
 * @Description: 通用流程权限拥有表
 * @author onlineGenerator
 * @date 2019-01-15 15:28:59
 * @version V1.0   
 *
 */
public class FamsCommonFlowOwnPage implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**部门*/
    @Excel(name="部门")
	private java.lang.String departId;
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
	 *@return: java.lang.String  部门
	 */
	public java.lang.String getDepartId(){
		return this.departId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setDepartId(java.lang.String departId){
		this.departId = departId;
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
	
	/**保存-通用流程权限拥有附表*/
    @ExcelCollection(name="通用流程权限拥有附表")
	private List<FamsCommonFlowOwnDetailEntity> famsCommonFlowOwnDetailList = new ArrayList<FamsCommonFlowOwnDetailEntity>();
	public List<FamsCommonFlowOwnDetailEntity> getFamsCommonFlowOwnDetailList() {
		return famsCommonFlowOwnDetailList;
	}
	public void setFamsCommonFlowOwnDetailList(List<FamsCommonFlowOwnDetailEntity> famsCommonFlowOwnDetailList) {
		this.famsCommonFlowOwnDetailList = famsCommonFlowOwnDetailList;
	}
	
}
