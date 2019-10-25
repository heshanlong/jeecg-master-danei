package com.gbiac.fams.business.famsAttenntionArtea.service.impl;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.airportrunway.areaconfig.entity.FamsAreaConfigEntity;
import com.gbiac.fams.business.famsAttenntionArtea.dao.FamsAttentionAreaDao;
import com.gbiac.fams.business.famsAttenntionArtea.entity.FamsAttentionAreaEntity;
import com.gbiac.fams.business.famsAttenntionArtea.service.FamsAttentionAreaServiceI;

@Service("famsAttentionAreaService")
@Transactional
public class FamsAttentionAreaServiceImpl extends CommonServiceImpl implements FamsAttentionAreaServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsAttentionAreaDao famsAttentionAreaDao;
	
 	public void delete(FamsAttentionAreaEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAttentionAreaEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAttentionAreaEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public List<FamsAttentionAreaEntity> getAllAttentionArea(String searchInput,String username) throws Exception {
		return famsAttentionAreaDao.getAllAttentionArea(searchInput, username);
	}
	
	
	
 	
}