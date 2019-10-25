package org.jeecgframework.jwt.model;

import lombok.Data;

import org.apache.commons.collections.CollectionUtils;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 甘桂祥
 * @date 2018/11/21
 */
//@Data
public class LoginSuccessDTO implements Serializable {
    private String token;

    private TSUser user;
    private TSRole role;

    private List<TSDepart> departList;
    
    
    public LoginSuccessDTO(String token, TSUser user) {
        this.token = token;
        this.user = user;
        if (CollectionUtils.isNotEmpty(user.getUserOrgList())) {
            this.departList = user.getUserOrgList().stream().map(tsUserOrg -> {
                TSDepart tsDepart = new TSDepart();
                tsDepart.setId(tsUserOrg.getTsDepart().getId());
                tsDepart.setDepartname(tsUserOrg.getTsDepart().getDepartname());
                tsDepart.setOrgCode(tsUserOrg.getTsDepart().getOrgCode());
                return tsDepart;
            }).collect(Collectors.toList());
        }

    }

    public LoginSuccessDTO(String token, TSUser user,TSRole role) {
        this.token = token;
        this.user = user;
        this.role = role;
        if (CollectionUtils.isNotEmpty(user.getUserOrgList())) {
            this.departList = user.getUserOrgList().stream().map(tsUserOrg -> {
                TSDepart tsDepart = new TSDepart();
                tsDepart.setId(tsUserOrg.getTsDepart().getId());
                tsDepart.setDepartname(tsUserOrg.getTsDepart().getDepartname());
                tsDepart.setOrgCode(tsUserOrg.getTsDepart().getOrgCode());
                return tsDepart;
            }).collect(Collectors.toList());
        }

    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TSUser getUser() {
		return user;
	}

	public void setUser(TSUser user) {
		this.user = user;
	}

	public TSRole getRole() {
		return role;
	}

	public void setRole(TSRole role) {
		this.role = role;
	}

	public List<TSDepart> getDepartList() {
		return departList;
	}

	public void setDepartList(List<TSDepart> departList) {
		this.departList = departList;
	}
    
    
    
}
