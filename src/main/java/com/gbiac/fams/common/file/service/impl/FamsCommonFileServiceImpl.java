package com.gbiac.fams.common.file.service.impl;

import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.file.service.FamsCommonFileServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("famsCommonFileService")
@Transactional
public class FamsCommonFileServiceImpl extends CommonServiceImpl implements FamsCommonFileServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsCommonFileEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsCommonFileEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsCommonFileEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}