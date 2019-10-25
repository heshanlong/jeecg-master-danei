package com.gbiac.fams.business.construction.worknodeinfo.service.impl;

import com.gbiac.fams.business.construction.worknodeinfo.dao.FamsWorkNodeInfoDao;
import com.gbiac.fams.business.construction.worknodeinfo.entity.FamsWorkNodeInfoEntity;
import com.gbiac.fams.business.construction.worknodeinfo.service.FamsWorkNodeInfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("famsWorkNodeInfoService")
@Transactional
public class FamsWorkNodeInfoServiceImpl extends CommonServiceImpl implements FamsWorkNodeInfoServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsWorkNodeInfoDao famsWorkNodeInfoDao;
 	public void delete(FamsWorkNodeInfoEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsWorkNodeInfoEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsWorkNodeInfoEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public String getStateByTaskKey(String taskKey) {
		return famsWorkNodeInfoDao.getStateByTaskKey(taskKey);
	}

}