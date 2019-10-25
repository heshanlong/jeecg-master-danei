package com.gbiac.fams.business.airportrunway.mark.entity;



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: fams_airportrunway_mark
 * @author 江家滨
 * @date 2019-01-22 11:17:47
 * @version V1.0   
 *
 */
public class MarkListEntity implements java.io.Serializable {
	/** 类型名称 */
	private java.lang.String name;
	/** 类型编码 */
	private java.lang.String code;
	private String typename;
	private List<MarkListEntity> markList;
	
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getCode() {
		return code;
	}
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	public List<MarkListEntity> getMarkList() {
		return markList;
	}
	public void setMarkList(List<MarkListEntity> markList) {
		this.markList = markList;
	}
	
}