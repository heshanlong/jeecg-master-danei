package com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.AreaEntity;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.FamsAttentionCraftsiteUserEntity;

public interface FamsAttentionCraftsiteUserServiceI extends CommonService{
	
 	public void delete(FamsAttentionCraftsiteUserEntity entity) throws Exception;
 	
 	public Serializable save(FamsAttentionCraftsiteUserEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAttentionCraftsiteUserEntity entity) throws Exception;
 	
 	public List<FamsAttentionCraftsiteUserEntity> queryListByEntity(String userName,String craftsiteSeria) throws Exception;
 	
 	public String[] getAttentionArea(String userName) throws Exception;
}
