package com.gbiac.fams.business.unsafeevent.updatetime.entity;
import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: fams_updatetime
 * @author onlineGenerator
 * @date 2019-03-11 16:07:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_updatetime", schema = "")
@SuppressWarnings("serial")
public class FamsUpdatetimeEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String no;
	/**作业人*/
	@Excel(name="作业人",width=15)
	private java.lang.String people;
	/**所属模块*/
	@Excel(name="所属模块",width=15)
	private java.lang.String module;
	/**插入时间*/
	@Excel(name="插入时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date intime;
	/**补充说明*/
	@Excel(name="补充说明",width=15)
	private java.lang.String added;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String note;
	/**创建人登录名称*/
	@Excel(name="创建人登录名称",width=15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人登录名称*/
	@Excel(name="更新人登录名称",width=15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name="更新日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	
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
	 *@return: java.lang.String  编号
	 */

	@Column(name ="NO",nullable=true,length=50)
	public java.lang.String getNo(){
		return this.no;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setNo(java.lang.String no){
		this.no = no;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业人
	 */

	@Column(name ="PEOPLE",nullable=true,length=50)
	public java.lang.String getPeople(){
		return this.people;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业人
	 */
	public void setPeople(java.lang.String people){
		this.people = people;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属模块
	 */

	@Column(name ="MODULE",nullable=true,length=100)
	public java.lang.String getModule(){
		return this.module;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属模块
	 */
	public void setModule(java.lang.String module){
		this.module = module;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  插入时间
	 */

	@Column(name ="INTIME",nullable=true)
	public java.util.Date getIntime(){
		return this.intime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  插入时间
	 */
	public void setIntime(java.util.Date intime){
		this.intime = intime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  补充说明
	 */

	@Column(name ="ADDED",nullable=true)
	public java.lang.String getAdded(){
		return this.added;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  补充说明
	 */
	public void setAdded(java.lang.String added){
		this.added = added;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="NOTE",nullable=true,length=255)
	public java.lang.String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setNote(java.lang.String note){
		this.note = note;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
}