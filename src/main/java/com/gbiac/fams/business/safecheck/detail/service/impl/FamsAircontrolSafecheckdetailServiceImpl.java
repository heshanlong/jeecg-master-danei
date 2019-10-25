package com.gbiac.fams.business.safecheck.detail.service.impl;
import com.gbiac.fams.business.safecheck.detail.dao.FamsAircontrolSafecheckdetailDaoI;
import com.gbiac.fams.business.safecheck.detail.service.FamsAircontrolSafecheckdetailServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.gbiac.fams.business.safecheck.detail.entity.FamsAircontrolSafecheckdetailEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Service("famsAircontrolSafecheckdetailService")
@Transactional
public class FamsAircontrolSafecheckdetailServiceImpl extends CommonServiceImpl implements FamsAircontrolSafecheckdetailServiceI {

	@Autowired
	private FamsAircontrolSafecheckdetailDaoI famsAircontrolSafecheckdetailDaoI;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsAircontrolSafecheckdetailEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAircontrolSafecheckdetailEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAircontrolSafecheckdetailEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public List<Map> querySafecheckDetail(String checkId, String checkProject) {
		return famsAircontrolSafecheckdetailDaoI.querySafecheckDetail(checkId,checkProject);
	}

	@Override
	public void updateRes(String id, String result) {
		famsAircontrolSafecheckdetailDaoI.updateRes(id,result);
	}
	
	public void updateDetailEntity(FamsAircontrolSafecheckdetailEntity entity) {
		famsAircontrolSafecheckdetailDaoI.updateDetail(entity.getId(),entity.getResult(),entity.getArrayTime(),entity.getCompleteTime(),entity.getDealResult());
	}

}