package com.gbiac.fams.business.safecheck.safecheckconfig.service.impl;
import com.gbiac.fams.business.safecheck.safecheckconfig.service.FamsAircontrolSafecheckconfigServiceI;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.gbiac.fams.business.safecheck.safecheckconfig.entity.FamsAircontrolSafecheckconfigEntity;
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

@Service("famsAircontrolSafecheckconfigService")
@Transactional
public class FamsAircontrolSafecheckconfigServiceImpl extends CommonServiceImpl implements FamsAircontrolSafecheckconfigServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsAircontrolSafecheckconfigEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAircontrolSafecheckconfigEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAircontrolSafecheckconfigEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public <T> List<T> getConfigList(Class<T> clazz, String checkProject, String type, String status){
		Session session = super.getSession();
		Criteria cri = session.createCriteria(clazz);
		//条件查询
		if(checkProject!=null) cri.add(Restrictions.eq("checkProject", checkProject));
		if(type!=null) cri.add(Restrictions.eq("type", type));
		if(status!=null) cri.add(Restrictions.eq("status", status));

		List list = cri.list();
		//session.close();
		return list;
	}
 	
}