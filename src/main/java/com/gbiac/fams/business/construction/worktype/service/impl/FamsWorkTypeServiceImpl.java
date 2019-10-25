package com.gbiac.fams.business.construction.worktype.service.impl;

import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.construction.worktype.dao.FamsWorkTypeDao;
import com.gbiac.fams.business.construction.worktype.entity.FamsWorkTypeEntity;
import com.gbiac.fams.business.construction.worktype.service.FamsWorkTypeServiceI;

@Service("famsWorkTypeService")
@Transactional
public class FamsWorkTypeServiceImpl extends CommonServiceImpl implements FamsWorkTypeServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsWorkTypeDao famsWorkTypeDao;
	
 	public void delete(FamsWorkTypeEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsWorkTypeEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsWorkTypeEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public List getWorkTypeDate() {
		return famsWorkTypeDao.getWorkTypeDate();
	}
 	
}