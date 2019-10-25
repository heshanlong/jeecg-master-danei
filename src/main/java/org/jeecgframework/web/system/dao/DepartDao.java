package org.jeecgframework.web.system.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.web.system.entity.DepartEntity;
import org.jeecgframework.web.system.pojo.base.TSDepart;

/**
 * 部门列表dao层
 * @author sir
 *
 */

@MiniDao
public interface DepartDao {
	/**
	 * 查询父列表
	 * @return
	 */
	@Sql("select id,departname FROM t_s_depart WHERE parentdepartId is null;")
	public List<DepartEntity> getMainList();
	
	@Sql("select id,departname FROM t_s_depart WHERE parentdepartId = :parentId;")
	@Arguments("parentId")
	public List<DepartEntity> getChildList(String parentId);
	
	@Sql("select * From t_s_depart WHERE org_code = :orgCode;")
	@Arguments("orgCode")
	public TSDepart getDepartByOrgCode(String orgCode);
	
}
