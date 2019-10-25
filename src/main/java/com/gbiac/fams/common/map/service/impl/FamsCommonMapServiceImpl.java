package com.gbiac.fams.common.map.service.impl;

import com.gbiac.fams.common.map.dao.FamsCommonMapDao;
import com.gbiac.fams.common.map.entity.FamsCommonMapEntity;
import com.gbiac.fams.common.map.service.FamsCommonMapServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service("famsCommonMapService")
@Transactional
public class FamsCommonMapServiceImpl extends CommonServiceImpl implements FamsCommonMapServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsCommonMapDao famsCommonMapDao;
 	public void delete(FamsCommonMapEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsCommonMapEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsCommonMapEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public List<Map> getMaps(String time) {
		return famsCommonMapDao.getMaps(time);
	}

}