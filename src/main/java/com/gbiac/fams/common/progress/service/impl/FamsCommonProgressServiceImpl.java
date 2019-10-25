package com.gbiac.fams.common.progress.service.impl;

import com.gbiac.fams.common.progress.dao.FamsCommonProgressDao;
import com.gbiac.fams.common.progress.entity.FamsCommonProgressEntity;
import com.gbiac.fams.common.progress.service.FamsCommonProgressServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("famsCommonProgressService")
@Transactional
public class FamsCommonProgressServiceImpl extends CommonServiceImpl implements FamsCommonProgressServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsCommonProgressDao famsCommonProgressDao;
 	public void delete(FamsCommonProgressEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsCommonProgressEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsCommonProgressEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public FamsCommonProgressEntity getRecentEntityByBid(String bid,String state) {
		return famsCommonProgressDao.getRecentEntityByBid(bid,state);
	}

}