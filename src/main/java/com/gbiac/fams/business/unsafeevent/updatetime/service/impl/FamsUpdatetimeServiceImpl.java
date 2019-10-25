package com.gbiac.fams.business.unsafeevent.updatetime.service.impl;
import com.gbiac.fams.business.unsafeevent.tiredamage.dao.FamsUnsafeeventTiredamageDaoI;
import com.gbiac.fams.business.unsafeevent.updatetime.service.FamsUpdatetimeServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.gbiac.fams.business.unsafeevent.updatetime.dao.FamsUpdatetimeDaoI;
import com.gbiac.fams.business.unsafeevent.updatetime.entity.FamsUpdatetimeEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("famsUpdatetimeService")
@Transactional
public class FamsUpdatetimeServiceImpl extends CommonServiceImpl implements FamsUpdatetimeServiceI {
	@Autowired
	private FamsUpdatetimeDaoI famsUpdatetimeDao;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsUpdatetimeEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsUpdatetimeEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsUpdatetimeEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public List<FamsUpdatetimeEntity> listApp(String str, Integer pageApp,Integer pageSize, HttpServletRequest request) {
		return famsUpdatetimeDao.queryListByEntity(str, (pageApp - 1) * pageSize, pageSize);
	}
 	
}