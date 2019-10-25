package com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.airportrunway.areaconfig.dao.FamsAreaConfigDao;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.dao.FamsAttentionCraftsiteUserDao;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.dao.FamsUserAttentionCraftsiteDao;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.AreaEntity;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.FamsAttentionCraftsiteUserEntity;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.service.FamsAttentionCraftsiteUserServiceI;

@Service("famsAttentionCraftsiteUserService")
@Transactional
public class FamsAttentionCraftsiteUserServiceImpl extends CommonServiceImpl implements FamsAttentionCraftsiteUserServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsAttentionCraftsiteUserDao famsAttentionCraftsiteUserDao;
	@Autowired
	private FamsUserAttentionCraftsiteDao famsUserAttentionCraftsiteDao;
	@Autowired
	private FamsAreaConfigDao famsAreaConfigDao;
	
 	public void delete(FamsAttentionCraftsiteUserEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAttentionCraftsiteUserEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAttentionCraftsiteUserEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public List<FamsAttentionCraftsiteUserEntity> queryListByEntity(String userName, String craftsiteSeria)
			throws Exception {
		return famsAttentionCraftsiteUserDao.queryListByEntity(userName,craftsiteSeria);
	}
 	
	/**
	 * 根据用户id获取到机位并进行拆分返回数据
	 */
	@Override
	public String[] getAttentionArea(String userName) {
		
		String[] seats = null;
		//1.根据用户名获取到机位，根据机位从机位配置中查找对应的区域名
		FamsAttentionCraftsiteUserEntity entity = famsUserAttentionCraftsiteDao.getAttentionCraftsiteByUserId(userName);
		if(entity != null && entity.getCraftsiteSeria()!=null) {
			//2. 根据获取到的机位数据,对数据进行拆分，
			seats = entity.getCraftsiteSeria().split(",");
		}
		return seats;
		
	}
	
	
}