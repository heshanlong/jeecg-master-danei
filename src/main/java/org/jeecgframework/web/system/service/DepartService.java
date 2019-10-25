package org.jeecgframework.web.system.service;

import java.util.List;

import org.jeecgframework.web.system.entity.DepartEntity;
import org.jeecgframework.web.system.pojo.base.TSDepart;
/**
 * 部门列表信息
 * @author sir
 *
 */
public interface DepartService {

	public List<DepartEntity> getMainList();
	
	public List<DepartEntity> getChildList(String parentId);
	
	public TSDepart getDepartByorgCode(String orgCode);
}
