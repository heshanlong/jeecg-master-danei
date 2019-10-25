package com.gbiac.fams.business.construction.workappstate.page;

import com.gbiac.fams.business.construction.workappstate.entity.FamsWorkAppStateDetailEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: Entity
 * @Description: 施工管理APP端状态配置表
 * @author onlineGenerator
 * @date 2019-03-29 18:12:11
 * @version V1.0
 *
 */
public class FamsWorkAppStatePage implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**角色名称*/
	@Excel(name="角色名称")
	private java.lang.String roleName;
	/**角色code*/
	@Excel(name="角色code")
	private java.lang.String roleCode;
	/**页面标识*/
	@Excel(name="页面标识")
	private java.lang.String flag;
	/**状态0待审批1进行中*/
	@Excel(name="状态0待审批1进行中")
	private java.lang.String state;

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
	 *@return: java.lang.String  角色名称
	 */
	public java.lang.String getRoleName(){
		return this.roleName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  角色名称
	 */
	public void setRoleName(java.lang.String roleName){
		this.roleName = roleName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  角色code
	 */
	public java.lang.String getRoleCode(){
		return this.roleCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  角色code
	 */
	public void setRoleCode(java.lang.String roleCode){
		this.roleCode = roleCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  页面标识
	 */
	public java.lang.String getFlag(){
		return this.flag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  页面标识
	 */
	public void setFlag(java.lang.String flag){
		this.flag = flag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态0待审批1进行中
	 */
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态0待审批1进行中
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}

	/**保存-施工管理APP端状态配置附表*/
	@ExcelCollection(name="施工管理APP端状态配置附表")
	private List<FamsWorkAppStateDetailEntity> famsWorkAppStateDetailList = new ArrayList<FamsWorkAppStateDetailEntity>();
	public List<FamsWorkAppStateDetailEntity> getFamsWorkAppStateDetailList() {
		return famsWorkAppStateDetailList;
	}
	public void setFamsWorkAppStateDetailList(List<FamsWorkAppStateDetailEntity> famsWorkAppStateDetailList) {
		this.famsWorkAppStateDetailList = famsWorkAppStateDetailList;
	}

}
