package com.gbiac.fams.business.construction.worksafe.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 施工安全员信息表
 * @author 龚道海
 * @date 2019-01-14 14:24:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_work_safe_person", schema = "")
@SuppressWarnings("serial")
public class FamsWorkSafePersonEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**施工申请*/
	@Excel(name="施工申请",width=15)
	private java.lang.String approveId;
	/**人员角色*/
	@Excel(name="人员角色",width=15)
	private java.lang.String personRole;
	/**人员姓名*/
	@Excel(name="人员姓名",width=15)
	private java.lang.String personName;
	/**人员电话*/
	@Excel(name="人员电话",width=15)
	private java.lang.String personPhone;
	/**对讲机呼号*/
	@Excel(name="对讲机呼号",width=15)
	private java.lang.String personIntercom;
	public FamsWorkSafePersonEntity(){}
	public FamsWorkSafePersonEntity(String approveId,String personRole,String personName,String personPhone,String personIntercom){
		this.approveId=approveId;
		this.personRole=personRole;
		this.personName=personName;
		this.personPhone=personPhone;
		this.personIntercom=personIntercom;
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
	 *@return: java.lang.String  施工申请
	 */

	@Column(name ="APPROVE_ID",nullable=true,length=36)
	public java.lang.String getApproveId(){
		return this.approveId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  施工申请
	 */
	public void setApproveId(java.lang.String approveId){
		this.approveId = approveId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人员角色
	 */

	@Column(name ="PERSON_ROLE",nullable=true,length=50)
	public java.lang.String getPersonRole(){
		return this.personRole;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员角色
	 */
	public void setPersonRole(java.lang.String personRole){
		this.personRole = personRole;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人员姓名
	 */

	@Column(name ="PERSON_NAME",nullable=true,length=50)
	public java.lang.String getPersonName(){
		return this.personName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员姓名
	 */
	public void setPersonName(java.lang.String personName){
		this.personName = personName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人员电话
	 */

	@Column(name ="PERSON_PHONE",nullable=true,length=50)
	public java.lang.String getPersonPhone(){
		return this.personPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员电话
	 */
	public void setPersonPhone(java.lang.String personPhone){
		this.personPhone = personPhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对讲机呼号
	 */

	@Column(name ="PERSON_INTERCOM",nullable=true,length=50)
	public java.lang.String getPersonIntercom(){
		return this.personIntercom;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对讲机呼号
	 */
	public void setPersonIntercom(java.lang.String personIntercom){
		this.personIntercom = personIntercom;
	}
}