package com.gbiac.fams.business.construction.workcheckitem.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 施工管理检查项目表
 * @author onlineGenerator
 * @date 2019-02-14 15:36:05
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_work_check_item", schema = "")
@SuppressWarnings("serial")
public class FamsWorkCheckItemEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**检查记录表id*/
	@Excel(name="检查记录表id",width=15)
	private java.lang.String checkId;
	/**检查条目id*/
	@Excel(name="检查条目id",width=15)
	private java.lang.String categoryId;
	/**检查结果*/
	@Excel(name="检查结果",width=15)
	private java.lang.String checkResult;
	/**检查备注*/
	@Excel(name="检查备注",width=15)
	private java.lang.String checkNote;

    public FamsWorkCheckItemEntity() {}
    public FamsWorkCheckItemEntity(String checkId, String categoryId, String checkResult) {
    	this.checkId=checkId;
    	this.categoryId=categoryId;
    	this.checkResult=checkResult;
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
	 *@return: java.lang.String  检查记录表id
	 */

	@Column(name ="CHECK_ID",nullable=true,length=36)
	public java.lang.String getCheckId(){
		return this.checkId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查记录表id
	 */
	public void setCheckId(java.lang.String checkId){
		this.checkId = checkId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查条目id
	 */

	@Column(name ="CATEGORY_ID",nullable=true,length=36)
	public java.lang.String getCategoryId(){
		return this.categoryId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查条目id
	 */
	public void setCategoryId(java.lang.String categoryId){
		this.categoryId = categoryId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查结果
	 */

	@Column(name ="CHECK_RESULT",nullable=true,length=32)
	public java.lang.String getCheckResult(){
		return this.checkResult;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查结果
	 */
	public void setCheckResult(java.lang.String checkResult){
		this.checkResult = checkResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查备注
	 */

	@Column(name ="CHECK_NOTE",nullable=true,length=100)
	public java.lang.String getCheckNote(){
		return this.checkNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查备注
	 */
	public void setCheckNote(java.lang.String checkNote){
		this.checkNote = checkNote;
	}
}