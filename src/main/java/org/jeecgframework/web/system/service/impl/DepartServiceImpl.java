package org.jeecgframework.web.system.service.impl;

import java.util.List;

import org.jeecgframework.web.system.dao.DepartDao;
import org.jeecgframework.web.system.entity.DepartEntity;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("departService")
public class DepartServiceImpl implements DepartService{

	@Autowired
	private DepartDao departDao;
	
	@Override
	public List<DepartEntity> getMainList() {
		return departDao.getMainList();
	}

	@Override
	public List<DepartEntity> getChildList(String parentId) {
		return departDao.getChildList(parentId);
	}

	@Override
	public TSDepart getDepartByorgCode(String orgCode) {
		return departDao.getDepartByOrgCode(orgCode);
	}
	
}
