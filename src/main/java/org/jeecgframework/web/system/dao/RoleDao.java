package org.jeecgframework.web.system.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

@MiniDao
public interface RoleDao {

	@Sql("SELECT	group_concat(r.rolename separator ',') as rolename FROM t_s_role r LEFT JOIN t_s_role_user ru ON r.id = ru.roleid WHERE ru.userid = :userId")
	@Arguments("userId")
	String getRoleNameByUserId(String userId);
	
	
	@Sql("SELECT mobilePhone FROM t_s_user WHERE id = :userId")
	@Arguments("userId")
	String getMobilePhoneByUserId(String userId);
}
