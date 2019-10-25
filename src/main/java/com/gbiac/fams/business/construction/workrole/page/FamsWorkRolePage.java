package com.gbiac.fams.business.construction.workrole.page;

import com.gbiac.fams.business.construction.workrole.entity.FamsWorkRoleDetailEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Title: Entity
 * @Description: 施工角色表
 * @author onlineGenerator
 * @date 2019-03-25 11:35:02
 * @version V1.0   
 *
 */
public class FamsWorkRolePage implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**角色名称*/
    @Excel(name="角色名称")
	private java.lang.String roleName;
	/**角色code*/
    @Excel(name="角色code")
	private java.lang.String roleCode;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
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
	
	/**保存-施工角色附表*/
    @ExcelCollection(name="施工角色附表")
	private List<FamsWorkRoleDetailEntity> famsWorkRoleDetailList = new ArrayList<FamsWorkRoleDetailEntity>();
	public List<FamsWorkRoleDetailEntity> getFamsWorkRoleDetailList() {
		return famsWorkRoleDetailList;
	}
	public void setFamsWorkRoleDetailList(List<FamsWorkRoleDetailEntity> famsWorkRoleDetailList) {
		this.famsWorkRoleDetailList = famsWorkRoleDetailList;
	}
	
}
