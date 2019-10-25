package com.gbiac.fams.business.unsafeevent.aircraftleakage.entity;

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

import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;

/**   
 * @Title: Entity
 * @Description: fams_unsafeevent_tiredamage
 * @author onlineGenerator
 * @date 2019-02-01 10:26:36
 * @version V1.0   
 *
 */
public class FamsUnsafeeventAircraftleakageDto implements java.io.Serializable {
	private String  searchInput;
	private Integer pageApp;
	private Integer pageSize;
	private String  id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSearchInput() {
		return searchInput;
	}
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}
	public Integer getPageApp() {
		return pageApp;
	}
	public void setPageApp(Integer pageApp) {
		this.pageApp = pageApp;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}