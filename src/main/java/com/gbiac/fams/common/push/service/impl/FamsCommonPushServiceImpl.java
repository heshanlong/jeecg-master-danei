package com.gbiac.fams.common.push.service.impl;

import com.gbiac.fams.common.push.entity.FamsCommonPushEntity;
import com.gbiac.fams.common.push.service.FamsCommonPushServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("famsCommonPushService")
@Transactional
public class FamsCommonPushServiceImpl extends CommonServiceImpl implements FamsCommonPushServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsCommonPushEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsCommonPushEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsCommonPushEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}