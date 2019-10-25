package com.gbiac.fams.business.tssystem.tsfuntion.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.entity.IdEntity;

/**
 *菜单权限表
 * @author shanlong_he
 * ========================注意：专门为app功能菜单提供的，看需求引用
 * ========================注意：专门为app功能菜单提供的，看需求引用
 * ========================注意：专门为app功能菜单提供的，看需求引用
 */
public class TSFunction {
	private String id;
	private String functionName;//菜单名称
	private Short functionLevel;//菜单等级
	private String functionUrl;//菜单地址
	private Short functionIframe;//菜单地址打开方式
	private String functionOrder;//菜单排序
	private Short functionType;//菜单类型
	private String function_icon_style; //菜单图标样式
	//private TSFunction TSFunction;//父菜单
	private String parentfunctionid;//父菜单
	private String iconid; //图标ID
	private String desk_iconid;//桌面图标ID
	
	private List<TSFunction> TSFunctions = new ArrayList<TSFunction>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Short getFunctionLevel() {
		return functionLevel;
	}

	public void setFunctionLevel(Short functionLevel) {
		this.functionLevel = functionLevel;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public Short getFunctionIframe() {
		return functionIframe;
	}

	public void setFunctionIframe(Short functionIframe) {
		this.functionIframe = functionIframe;
	}

	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}

	public Short getFunctionType() {
		return functionType;
	}

	public void setFunctionType(Short functionType) {
		this.functionType = functionType;
	}

	public String getFunction_icon_style() {
		return function_icon_style;
	}

	public void setFunction_icon_style(String function_icon_style) {
		this.function_icon_style = function_icon_style;
	}

	public String getParentfunctionid() {
		return parentfunctionid;
	}

	public void setParentfunctionid(String parentfunctionid) {
		this.parentfunctionid = parentfunctionid;
	}

	public String getIconid() {
		return iconid;
	}

	public void setIconid(String iconid) {
		this.iconid = iconid;
	}

	public String getDesk_iconid() {
		return desk_iconid;
	}

	public void setDesk_iconid(String desk_iconid) {
		this.desk_iconid = desk_iconid;
	}

	public List<TSFunction> getTSFunctions() {
		return TSFunctions;
	}

	public void setTSFunctions(List<TSFunction> tSFunctions) {
		TSFunctions = tSFunctions;
	}

	

	
}