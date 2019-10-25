package com.gbiac.fams.business.construction.workapprove.service.impl;

import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.construction.workapprove.dao.FamsWorkApproveNoPassDao;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveNoPassEntity;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveNoPassServiceI;

@Service("famsWorkApproveNoPassService")
@Transactional
public class FamsWorkApproveNoPassServiceImpl extends CommonServiceImpl implements FamsWorkApproveNoPassServiceI{

	@Autowired
	private FamsWorkApproveNoPassDao famsWorkApproveNoPassDao;
	
	@Override
	public Serializable save(FamsWorkApproveNoPassEntity entity) throws Exception {
		Serializable t = super.save(entity);
		return t;
	}

	@Override
	public List<?> getNoPassApprove(String searchInput, Integer pageNum, Integer pageSize, String userName) throws Exception {
		return famsWorkApproveNoPassDao.getNoPassApprove(searchInput, pageNum, pageSize, userName);
	}

}
