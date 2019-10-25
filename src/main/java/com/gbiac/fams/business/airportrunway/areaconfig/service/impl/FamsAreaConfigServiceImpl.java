package com.gbiac.fams.business.airportrunway.areaconfig.service.impl;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.airportrunway.areaconfig.dao.FamsAreaConfigDao;
import com.gbiac.fams.business.airportrunway.areaconfig.entity.FamsAreaConfigEntity;
import com.gbiac.fams.business.airportrunway.areaconfig.service.FamsAreaConfigServiceI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("famsAreaConfigService")
@Transactional
public class FamsAreaConfigServiceImpl extends CommonServiceImpl implements FamsAreaConfigServiceI {

	@Autowired
	FamsAreaConfigDao famsAreaConfigDao;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsAreaConfigEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAreaConfigEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAreaConfigEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public Integer findByAreaAndCraftsite(String check_craftsite,String craftsite) {
		return famsAreaConfigDao.findByAreaAndCraftsite(check_craftsite, craftsite);
	}

	@Override
	public List<FamsAreaConfigEntity> loadAreaConfigDate() {
		return famsAreaConfigDao.loadAreaConfigDate();
	}
	
	public List<FamsAreaConfigEntity> getAllNoAttentionArea(String searchInput,String username) throws Exception {
		return famsAreaConfigDao.getAllNoAttentionArea(searchInput, username);
	}
 	
}