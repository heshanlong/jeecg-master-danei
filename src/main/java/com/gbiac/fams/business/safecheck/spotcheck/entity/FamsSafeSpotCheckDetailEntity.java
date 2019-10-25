package com.gbiac.fams.business.safecheck.spotcheck.entity;
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
 * @Description: 抽查详情
 * @author onlineGenerator
 * @date 2018-03-27 16:21:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_spot_check_detail", schema = "")
@SuppressWarnings("serial")
public class FamsSafeSpotCheckDetailEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**检查人*/
    @Excel(name="检查人",width=15)
	private java.lang.String checkPerson;
    /**航班号*/
    @Excel(name="航班号",width=15)
	private java.lang.String flightno;
    /**机号*/
    @Excel(name="机号",width=15)
	private java.lang.String immediately;
    /**机型*/
    @Excel(name="机型",width=15)
	private java.lang.String models;
    /**停机位*/
    @Excel(name="停机位",width=15)
	private java.lang.String slots;
    /**检查情况*/
    @Excel(name="检查情况",width=15)
	private java.lang.String checkDetail;
	/**检查开始时间*/
    @Excel(name="检查开始时间",width=15,format = "HH:mm:ss")
	private java.util.Date checkDateStart;
    /**检查开始时间*/
    @Excel(name="检查结束时间",width=15,format = "HH:mm:ss")
	private java.util.Date checkDateEnd;
	/**外键*/
	private java.lang.String fckId;
	
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
	 *@return: java.lang.String  检查人
	 */
	
	@Column(name ="CHECK_PERSON",nullable=false,length=100)
	public java.lang.String getCheckPerson(){
		return this.checkPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查人
	 */
	public void setCheckPerson(java.lang.String checkPerson){
		this.checkPerson = checkPerson;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  航班号
	 */
	
	@Column(name ="FLIGHTNO",nullable=false,length=100)
	public java.lang.String getFlightno(){
		return this.flightno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班号
	 */
	public void setFlightno(java.lang.String flightno){
		this.flightno = flightno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机号
	 */
	
	@Column(name ="IMMEDIATELY",nullable=false,length=100)
	public java.lang.String getImmediately(){
		return this.immediately;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机号
	 */
	public void setImmediately(java.lang.String immediately){
		this.immediately = immediately;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机型
	 */
	
	@Column(name ="MODELS",nullable=false,length=100)
	public java.lang.String getModels(){
		return this.models;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机型
	 */
	public void setModels(java.lang.String models){
		this.models = models;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  停机位
	 */
	
	@Column(name ="SLOTS",nullable=false,length=100)
	public java.lang.String getSlots(){
		return this.slots;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  停机位
	 */
	public void setSlots(java.lang.String slots){
		this.slots = slots;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  检查情况
	 */
	
	@Column(name ="CHECK_DETAIL",nullable=false,length=100)
	public java.lang.String getCheckDetail(){
		return this.checkPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  检查情况
	 */
	public void setCheckDetail(java.lang.String checkDetail){
		this.checkDetail = checkDetail;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  检查开始时间
	 */
	
	@Column(name ="CHECK_DATE_START",nullable=false,length=10)
	public java.util.Date getCheckDateStart(){
		return this.checkDateStart;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  检查开始时间
	 */
	public void setCheckDateStart(java.util.Date checkDateStart){
		this.checkDateStart = checkDateStart;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  检查结束时间
	 */
	
	@Column(name ="CHECK_DATE_END",nullable=false,length=10)
	public java.util.Date getCheckDateEnd(){
		return this.checkDateEnd;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  检查结束时间
	 */
	public void setCheckDateEnd(java.util.Date checkDateEnd){
		this.checkDateEnd = checkDateEnd;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外键
	 */
	
	@Column(name ="FCK_ID",nullable=false,length=36)
	public java.lang.String getFckId(){
		return this.fckId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外键
	 */
	public void setFckId(java.lang.String fckId){
		this.fckId = fckId;
	}
	
}
