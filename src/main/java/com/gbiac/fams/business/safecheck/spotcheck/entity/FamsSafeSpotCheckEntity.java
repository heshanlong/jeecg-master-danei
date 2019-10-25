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
 * @Description: 抽查记录
 * @author onlineGenerator
 * @date 2018-03-27 16:21:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_spot_check", schema = "")
@SuppressWarnings("serial")
public class FamsSafeSpotCheckEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**订单号*/
    @Excel(name="当日小结",width=15)
	private java.lang.String checkDetail;
	/**订单日期*/
    @Excel(name="抽查日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date checkDate;
	
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
	 *@return: java.lang.String  当日小结
	 */
	
	@Column(name ="CHECK_DETAIL",nullable=true,length=50)
	public java.lang.String getCheckDetail(){
		return this.checkDetail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当日小结
	 */
	public void setCheckDetail(java.lang.String checkDetail){
		this.checkDetail = checkDetail;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  抽查日期
	 */
	
	@Column(name ="CHECK_DATE",nullable=true,length=20)
	public java.util.Date getCheckDate(){
		return this.checkDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  抽查日期
	 */
	public void setCheckDate(java.util.Date checkDate){
		this.checkDate = checkDate;
	}
	
}
