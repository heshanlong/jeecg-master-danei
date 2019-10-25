package com.gbiac.fams.business.violation.service.impl;
import com.gbiac.fams.business.violation.dao.FamsAircontrolViolationDaoI;
import com.gbiac.fams.business.violation.service.FamsAircontrolViolationServiceI;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.restutil.SessionUserUtil;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.gbiac.fams.business.violation.entity.FamsAircontrolViolationEntity;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("famsAircontrolViolationService")
@Transactional
public class FamsAircontrolViolationServiceImpl extends CommonServiceImpl implements FamsAircontrolViolationServiceI {
	@Autowired
	private FamsAircontrolViolationDaoI famsAircontrolViolationDaoI;
	@Autowired
	SystemService systemService;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void delete(FamsAircontrolViolationEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAircontrolViolationEntity entity) throws Exception{
 		entity.setStatus("1");
		entity.setDecideName(Util.getUserName().isEmpty()? SessionUserUtil.getUserRealName():Util.getUserName());
		entity.setNumber(systemService.getFamsNumberByTSTypegroup("违章告知单",FamsAircontrolViolationEntity.class,"createDate"));
		entity.setUpdateDate(new Date());

		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAircontrolViolationEntity entity) throws Exception{
		entity.setUpdateDate(new Date());
 		super.saveOrUpdate(entity);
 	}

	@Override
	public List<FamsAircontrolViolationEntity> queryListByEntity(FamsAircontrolViolationEntity entity) {
		if(entity!=null
				&& entity.getPageNoApp()!=null
				&& entity.getPageSizeApp()!=null){
			entity.setPageNoApp( (entity.getPageNoApp()-1)*entity.getPageSizeApp() );

		}
		return famsAircontrolViolationDaoI.queryListByEntity(entity);
	}

}