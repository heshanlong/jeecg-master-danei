package org.jeecgframework.jwt.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.def.JwtConstants;
import org.jeecgframework.jwt.model.TokenModel;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 使用 guva 的缓存进行 token 管理
 *
 * @author 甘桂祥
 * @date 2018/11/20
 */
@Component
@Slf4j
public class GuavaCacheTokenManager implements TokenManager {

    private static final Cache<String, Object> TOKEN_CACHE;

    static {
        // 初始化 guava cache
        TOKEN_CACHE = CacheBuilder.newBuilder().maximumSize(10000)
                // token 30天过期
                .expireAfterAccess(30, TimeUnit.DAYS)
                .initialCapacity(10)
                .removalListener((RemovalListener<String, Object>) removalNotification -> {
                    if (log.isInfoEnabled()) {
                        log.info("remove cache [{}:{}]", removalNotification.getKey(), removalNotification.getValue());
                    }
                }).build();
    }

    @Override
    public String createToken(TSUser user) {
        // 使用uuid作为源token
        String token = Jwts.builder().setId(user.getUserName()).setSubject(user.getUserName()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, JwtConstants.JWT_SECRET).compact();
        TokenModel tokenModel = new TokenModel(token, user);
        // 存储到guava缓存
        TOKEN_CACHE.put(user.getUserName(), tokenModel);
        return token;
    }
    
    @Override
	public String createToken(TSUser user, TSRole role) {
    	// 使用uuid作为源token
        String token = Jwts.builder().setId(user.getUserName()).setSubject(user.getUserName()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, JwtConstants.JWT_SECRET).compact();
        TokenModel tokenModel = new TokenModel(token, user,role);
        // 存储到guava缓存
        TOKEN_CACHE.put(user.getUserName(), tokenModel);
        return token;
	}

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = (String) TOKEN_CACHE.getIfPresent(model.getUsername());
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        return true;
    }

    @Override
    public TokenModel getToken(String token, String username) {
        if (StringUtils.isBlank(token) || StringUtils.isBlank(username)) {
            return null;
        }
        TokenModel tokenModel = (TokenModel) TOKEN_CACHE.getIfPresent(username);
        if (tokenModel == null || !token.equals(tokenModel.getToken())) {
            return null;
        }
        return tokenModel;
    }

    @Override
    public void deleteToken(String username) {
        TOKEN_CACHE.invalidate(username);
    }

	
}
