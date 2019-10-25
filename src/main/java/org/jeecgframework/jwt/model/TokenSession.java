package org.jeecgframework.jwt.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.collections.CollectionUtils;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 甘桂祥
 * @date 2018/11/21
 */
public class TokenSession implements Serializable {

    private static final long serialVersionUID = 2462787887634178063L;

    private String userName;

    private String userId;

    private String departname;

    private  String userDepId;

    private String realName;

    private List<String> orgCodeList;

    public TokenSession(TSUser user) {
        this.userName = user.getUserName();
        this.userId = user.getId();
        this.departname=user.getCurrentDepart().getDepartname();
        this.userDepId=user.getDepartid();
        this.realName=user.getRealName();
        this.user = user;
        if (CollectionUtils.isNotEmpty(user.getUserOrgList())) {
            this.orgCodeList = user.getUserOrgList().stream().map(userOrg -> {
                return userOrg.getTsDepart().getOrgCode();
            }).collect(Collectors.toList());
        }
    }
    
    public TokenSession(TSUser user,TSRole role) {
        this.userName = user.getUserName();
        this.userId = user.getId();
        this.departname=user.getCurrentDepart().getDepartname();
        this.userDepId=user.getDepartid();
        this.realName=user.getRealName();
        this.user = user;
        this.role = role;
        if (CollectionUtils.isNotEmpty(user.getUserOrgList())) {
            this.orgCodeList = user.getUserOrgList().stream().map(userOrg -> {
                return userOrg.getTsDepart().getOrgCode();
            }).collect(Collectors.toList());
        }
    }
    
    private TSUser user;
    private TSRole role;
    

 

	public TSRole getRole() {
		return role;
	}

	public void setRole(TSRole role) {
		this.role = role;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getOrgCodeList() {
        return orgCodeList;
    }

    public void setOrgCodeList(List<String> orgCodeList) {
        this.orgCodeList = orgCodeList;
    }

    public TSUser getUser() {
        return user;
    }

    public void setUser(TSUser user) {
        this.user = user;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getUserDepId() {
        return userDepId;
    }

    public void setUserDepId(String userDepId) {
        this.userDepId = userDepId;
    }

    public String getRealName() {
        return realName;
    }


    public void setRealName(String realName) {
        this.realName = realName;
    }
}
