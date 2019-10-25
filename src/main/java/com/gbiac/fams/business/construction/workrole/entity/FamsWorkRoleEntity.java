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
@Table(name = "fams_work_role", schema = "")
@SuppressWarnings("serial")
public class FamsWorkRoleEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**角色名称*/
	@Excel(name="角色名称",width=15)
	private java.lang.String roleName;
	/**角色code*/
	@Excel(name="角色code",width=15)
	private java.lang.String roleCode;
	
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
	 *@return: java.lang.String  角色名称
	 */
	
	@Column(name ="ROLE_NAME",nullable=true,length=50)
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
	
	@Column(name ="ROLE_CODE",nullable=true,length=50)
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
	
}
