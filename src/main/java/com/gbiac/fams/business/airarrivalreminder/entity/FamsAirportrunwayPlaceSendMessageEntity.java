package com.gbiac.fams.business.airarrivalreminder.entity;

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
 * @Description: 航班入位提醒发送短信
 * @author onlineGenerator
 * @date 2019-08-06 18:00:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "di_aomip_flight_airl_snapshot", schema = "")
@SuppressWarnings("serial")
public class FamsAirportrunwayPlaceSendMessageEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**航班计划降落时间*/
	private java.util.Date airlArptFplt;
	
	private java.lang.String afssFfid;
	
	private java.lang.String airlFfid;

	@Id
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	@Column(name ="AIRL_ARPT_FPLT")
	public java.util.Date getAirlArptFplt() {
		return airlArptFplt;
	}
	public void setAirlArptFplt(java.util.Date airlArptFplt) {
		this.airlArptFplt = airlArptFplt;
	}
	@Column(name ="AFSS_FFID",nullable=false,length=28)
	public java.lang.String getAfssFfid() {
		return afssFfid;
	}
	public void setAfssFfid(java.lang.String afssFfid) {
		this.afssFfid = afssFfid;
	}
	@Column(name ="AIRL_FFID",nullable=false,length=28)
	public java.lang.String getAirlFfid() {
		return airlFfid;
	}
	public void setAirlFfid(java.lang.String airlFfid) {
		this.airlFfid = airlFfid;
	}
	
	
	
	
	
	

	
	
	
}
