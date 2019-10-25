package com.gbiac.fams.business.construction.workrole.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

@MiniDao
public interface FamsWorkRoleDao {
    @Sql("SELECT t1.rolecode FROM t_s_role_user t, t_s_role t1 WHERE t.roleid = t1.ID AND t.userid = :userId limit 0,1")
    @Arguments("userId")
    String getRoleCodeByUserId(String userId);
}
