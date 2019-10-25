package com.gbiac.fams.business.diaomipstndinfo.entity;

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
 * @Description: 机位管理test
 * @author onlineGenerator
 * @date 2019-08-20 16:57:46
 * @version V1.0   
 *
 */
@Entity
@Table(name = "di_aomip_stnd_info", schema = "")
@SuppressWarnings("serial")
public class DiAomipStndInfoEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String stndCode;
	/**机位*/
	@Excel(name="机位",width=15)
	private java.lang.String stndCnnm;
	/**stndEnnm*/
	private java.lang.String stndEnnm;
	/**Y远机位N近机位*/
	private java.lang.String stndRemt;
	/**stndTmlc*/
	private java.lang.String stndTmlc;
	/**stus*/
	private java.lang.String stndStus;
	/**stndStat*/
	private java.math.BigDecimal stndStat;
	/**createTime*/
	private java.util.Date createTime;
	/**updateTime*/
	private java.util.Date updateTime;
	/**stat*/
	private java.math.BigDecimal stat;
	
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
	 *@return: java.lang.String  编号
	 */

	@Column(name ="STND_CODE",nullable=false,length=5)
	public java.lang.String getStndCode(){
		return this.stndCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setStndCode(java.lang.String stndCode){
		this.stndCode = stndCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机位
	 */

	@Column(name ="STND_CNNM",nullable=true,length=32)
	public java.lang.String getStndCnnm(){
		return this.stndCnnm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机位
	 */
	public void setStndCnnm(java.lang.String stndCnnm){
		this.stndCnnm = stndCnnm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  stndEnnm
	 */

	@Column(name ="STND_ENNM",nullable=true,length=32)
	public java.lang.String getStndEnnm(){
		return this.stndEnnm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  stndEnnm
	 */
	public void setStndEnnm(java.lang.String stndEnnm){
		this.stndEnnm = stndEnnm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Y远机位N近机位
	 */

	@Column(name ="STND_REMT",nullable=true,length=1)
	public java.lang.String getStndRemt(){
		return this.stndRemt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Y远机位N近机位
	 */
	public void setStndRemt(java.lang.String stndRemt){
		this.stndRemt = stndRemt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  stndTmlc
	 */

	@Column(name ="STND_TMLC",nullable=true,length=4)
	public java.lang.String getStndTmlc(){
		return this.stndTmlc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  stndTmlc
	 */
	public void setStndTmlc(java.lang.String stndTmlc){
		this.stndTmlc = stndTmlc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  stus
	 */

	@Column(name ="STND_STUS",nullable=true,length=1)
	public java.lang.String getStndStus(){
		return this.stndStus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  stus
	 */
	public void setStndStus(java.lang.String stndStus){
		this.stndStus = stndStus;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  stndStat
	 */

	@Column(name ="STND_STAT",nullable=false,length=1)
	public java.math.BigDecimal getStndStat(){
		return this.stndStat;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  stndStat
	 */
	public void setStndStat(java.math.BigDecimal stndStat){
		this.stndStat = stndStat;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createTime
	 */

	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createTime
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  updateTime
	 */

	@Column(name ="UPDATE_TIME",nullable=true)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  updateTime
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  stat
	 */

	@Column(name ="STAT",nullable=true,length=1)
	public java.math.BigDecimal getStat(){
		return this.stat;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  stat
	 */
	public void setStat(java.math.BigDecimal stat){
		this.stat = stat;
	}
}
