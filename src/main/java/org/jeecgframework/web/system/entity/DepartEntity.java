package org.jeecgframework.web.system.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 部门信息实体
 * @author sir
 *
 */
@Entity
@Table(name = "t_s_depart", schema = "")
public class DepartEntity {

	private java.lang.String id;
	private java.lang.String departname;
	private List<DepartEntity> list;
	public DepartEntity(){}
	
	
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
	 *@return: java.lang.String  模块名称
	 */

	@Column(name ="DEOARTNAME",nullable=true,length=100)
	public java.lang.String getdepartname(){
		return this.departname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模块名称
	 */
	public void setdepartname(java.lang.String departname){
		this.departname = departname;
	}
	
	@Transient
	public List<DepartEntity> getList(){
		return this.list;
	}
	
	public void setList(List<DepartEntity> list) {
		this.list = list;
	}
}
