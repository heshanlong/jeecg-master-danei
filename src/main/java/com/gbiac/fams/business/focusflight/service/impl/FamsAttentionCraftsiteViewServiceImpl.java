package com.gbiac.fams.business.focusflight.service.impl;
import com.gbiac.fams.business.focusflight.service.FamsAttentionCraftsiteViewServiceI;
import com.sun.star.uno.Exception;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.dao.FamsAttentionCraftsiteUserDao;
import com.gbiac.fams.business.anounce.entity.FamsAnounceGroupEntity;
import com.gbiac.fams.business.anounce.vo.GroupVO;
import com.gbiac.fams.business.focusflight.dao.FamsAttentionCraftsiteViewDao;
import com.gbiac.fams.business.focusflight.entity.FamsAttentionCraftsiteViewEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

@Service("famsAttentionCraftsiteViewService")
@Transactional
public class FamsAttentionCraftsiteViewServiceImpl extends CommonServiceImpl implements FamsAttentionCraftsiteViewServiceI {

	@Autowired
	FamsAttentionCraftsiteViewDao famsAttentionCraftsiteViewDao;
	
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public List<FamsAttentionCraftsiteViewEntity> queryListByEntity(String name,String serial,String flycode)
			throws Exception {
		
		return famsAttentionCraftsiteViewDao.queryListByEntity(name, serial, flycode);
	}

	@Override
	public void saveOrUpdate(FamsAttentionCraftsiteViewEntity entity) throws java.lang.Exception {
		super.saveOrUpdate(entity);
		
	}

	@Override
	public void delete(FamsAttentionCraftsiteViewEntity entity) throws java.lang.Exception {
		super.delete(entity);
		
	}

	@Override
	public Serializable save(FamsAttentionCraftsiteViewEntity entity) throws java.lang.Exception {
		Serializable t = super.save(entity);
 		return t;
	}
 	
}











