package com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsite.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 机位
 * @author onlineGenerator
 * @date 2019-02-22 10:48:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_attention_craftsite", schema = "")
@SuppressWarnings("serial")
public class FamsAttentionCraftsiteEntity implements java.io.Serializable {

	/**主键*/
	private java.lang.String id;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String serialNumber;
	/**机位*/
	@Excel(name="机位",width=15)
	private java.lang.String craftsite;
	/**审查机位*/
	@Excel(name="审查机位",width=15)
	private java.lang.String checkCraftsite;

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

	@Column(name ="SERIAL_NUMBER",nullable=true,length=50)
	public java.lang.String getSerialNumber(){
		return this.serialNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setSerialNumber(java.lang.String serialNumber){
		this.serialNumber = serialNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机位
	 */

	@Column(name ="CRAFTSITE",nullable=true,length=50)
	public java.lang.String getCraftsite(){
		return this.craftsite;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机位
	 */
	public void setCraftsite(java.lang.String craftsite){
		this.craftsite = craftsite;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审查机位
	 */

	@Column(name ="CHECK_CRAFTSITE",nullable=true,length=50)
	public java.lang.String getCheckCraftsite(){
		return this.checkCraftsite;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审查机位
	 */
	public void setCheckCraftsite(java.lang.String checkCraftsite){
		this.checkCraftsite = checkCraftsite;
	}
}