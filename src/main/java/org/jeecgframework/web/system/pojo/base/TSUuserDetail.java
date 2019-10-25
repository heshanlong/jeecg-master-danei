package org.jeecgframework.web.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;


/**
 * 系统用户表
 *  @author 
 */
@Entity
@Table(name = "t_s_user_detail")
public class TSUuserDetail{

	private String id;
	private String imei;// 
	private String loginState;// 是否在线
	private String loginAddress;// 登陆地址
	
	
	@Id
	@Column(name ="ID",nullable=true,length=255)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="IMEI",nullable=true,length=255)
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	@Column(name ="LOGIN_STATE",nullable=true,length=255)
	public String getLoginState() {
		return loginState;
	}
	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}
	
	@Column(name ="LOGIN_ADDRESS",nullable=true,length=255)
	public String getLoginAddress() {
		return loginAddress;
	}
	public void setLoginAddress(String loginAddress) {
		this.loginAddress = loginAddress;
	}
	
	
	
	
	
}
