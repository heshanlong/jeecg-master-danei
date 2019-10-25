package org.jeecgframework.jwt.model;

import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSUser;


/**
 * Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 * @author ScienJus
 * @date 2015/7/31.
 */
public class TokenModel {

    //用户id
    private String username;

    //随机生成的uuid
    private String token;
    
    private TokenSession tokenSession;

    public TokenModel(String username, String token) {
        this.username = username;
        this.token = token;
    }
    
    public TokenModel(String token, TSUser user) {
        this.token = token;
        this.username = user.getUserName();
        this.tokenSession = new TokenSession(user);
    }
    
    public TokenModel(String token, TSUser user,TSRole role) {
    	this.token = token;
    	this.username = user.getUserName();
    	this.tokenSession = new TokenSession(user,role);
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public TokenSession getTokenSession() {
		return tokenSession;
	}

	public void setTokenSession(TokenSession tokenSession) {
		this.tokenSession = tokenSession;
	}
    
    
}
