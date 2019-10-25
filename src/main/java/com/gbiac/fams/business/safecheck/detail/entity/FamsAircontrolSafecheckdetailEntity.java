package com.gbiac.fams.business.safecheck.detail.entity;

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
 * @Description: 保障作业检查单记录附表
 * @author 邓正辉
 * @date 2019-01-21 13:49:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_aircontrol_safecheckdetail", schema = "")
@SuppressWarnings("serial")
public class FamsAircontrolSafecheckdetailEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**检查单主表id*/
	@Excel(name="检查单主表id",width=15)
	private java.lang.String checkId;
	/**表单信息主表id*/
	@Excel(name="表单信息主表id",width=15)
	private java.lang.String checkConfigiId;
	/**单项结果*/
	@Excel(name="单项结果",width=15)
	private java.lang.String result;
	/**到达时间*/
	@Excel(name="到达时间",width=30)
	private java.lang.String arrayTime;
	/**完成时间*/
	@Excel(name="完成时间",width=30)
	private java.lang.String completeTime;
	/**处理结果*/
	@Excel(name="处理结果",width=15)
	private java.lang.String dealResult;
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
	 *@return: java.lang.String  检查单主表id
	 */

	@Column(name ="CHECK_ID",nullable=true,length=32)
	public java.lang.String getCheckId(){
		return this.checkId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查单主表id
	 */
	public void setCheckId(java.lang.String checkId){
		this.checkId = checkId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  表单信息主表id
	 */

	@Column(name ="CHECK_CONFIGI_ID",nullable=true,length=32)
	public java.lang.String getCheckConfigiId(){
		return this.checkConfigiId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  表单信息主表id
	 */
	public void setCheckConfigiId(java.lang.String checkConfigiId){
		this.checkConfigiId = checkConfigiId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单项结果
	 */

	@Column(name ="RESULT",nullable=true,length=32)
	public java.lang.String getResult(){
		return this.result;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单项结果
	 */
	public void setResult(java.lang.String result){
		this.result = result;
	}
	
	@Column(name = "ARRAYTIME",nullable=true, length=32)
	public java.lang.String getArrayTime(){
		return this.arrayTime;
	}
	
	public void setArrayTime(java.lang.String arrayTime) {
		this.arrayTime = arrayTime;
	}
	
	@Column(name = "COMPLETETIME",nullable=true, length=32)
	public java.lang.String getCompleteTime(){
		return this.completeTime;
	}
	
	public void setCompleteTime(java.lang.String completeTime) {
		this.completeTime = completeTime;
	}
	
	@Column(name = "DEALRESULT",nullable=true, length=32)
	public java.lang.String getDealResult(){
		return this.dealResult;
	}
	
	public void setDealResult(java.lang.String dealResult) {
		this.dealResult = dealResult;
	}
	
}