package com.gbiac.fams.restutil;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.jwt.def.JwtConstants;
import org.jeecgframework.jwt.model.TokenSession;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 甘桂祥
 * @date 2018/11/21
 */
public class SessionUserUtil {

    public static TokenSession getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        TokenSession tokenSession = (TokenSession) request.getAttribute(JwtConstants.CURRENT_TOKEN_SESSION);
        if (tokenSession == null) {
            throw new BusinessException("用户未登录");
        }
        return tokenSession;
    }

    /**
     * 获取当前登录用户的登录账户名
     *
     * @return
     */
    public static String getCurrentUserName() {
        return getCurrentUser().getUserName();
    }

    /**
     * 获取当前登录用户的部门编码列表
     *
     * @return
     */
    public static List<String> getCurrentUserOrgCodeList() {
        return getCurrentUser().getOrgCodeList();
    }

    /**
     * 获取用户id
     * @return
     */
    public static String getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    /**
     * 获取所属部门部门名
     * @return
     */
    public static String getCurrentUserDepName() {
        return getCurrentUser().getDepartname();
    }

    /**
     * 获取用户部门id
     * @return
     */
    public static String getUserDepId() {
        return getCurrentUser().getUserDepId();
    }

    /**
     * 邓正辉
     * 获取中文名
     * @return
     */
    public static String getUserRealName(){return getCurrentUser().getRealName(); }

}
