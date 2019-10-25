package com.gbiac.fams.business.construction.worksafe.service.impl;

import com.gbiac.fams.business.construction.worksafe.entity.FamsWorkSafePersonEntity;
import com.gbiac.fams.business.construction.worksafe.service.FamsWorkSafePersonServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("famsWorkSafePersonService")
@Transactional
public class FamsWorkSafePersonServiceImpl extends CommonServiceImpl implements FamsWorkSafePersonServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsWorkSafePersonEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsWorkSafePersonEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsWorkSafePersonEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}