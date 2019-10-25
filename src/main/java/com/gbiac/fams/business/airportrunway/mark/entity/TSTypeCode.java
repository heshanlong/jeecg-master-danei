package com.gbiac.fams.business.airportrunway.mark.entity;
// default package

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

/**
 * 通用类型字典表
 *  @author  张代浩
 */
@Entity
public class TSTypeCode extends IdEntity implements java.io.Serializable {

	private String typename;//类型名称
	private String typecode;//类型编码

	private Date createDate;//创建时间
	private String createName;//创建用户

//	private List<TPProcess> TSProcesses = new ArrayList();
//	private List<TSType> TSTypes =new ArrayList();

	private Integer orderNum;//序号

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	

}