package com.gbiac.fams.business.focusflight.service;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.FamsAttentionCraftsiteUserEntity;
import com.gbiac.fams.business.anounce.entity.FamsAnounceGroupEntity;
import com.gbiac.fams.business.anounce.vo.GroupVO;
import com.gbiac.fams.business.focusflight.entity.FamsAttentionCraftsiteViewEntity;


import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface FamsAttentionCraftsiteViewServiceI extends CommonService{
	
 	public void delete(FamsAttentionCraftsiteViewEntity entity) throws Exception;
 	
 	public Serializable save(FamsAttentionCraftsiteViewEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAttentionCraftsiteViewEntity entity) throws Exception;
 	
 	public List<FamsAttentionCraftsiteViewEntity> queryListByEntity(String name,String serial,String flycode) throws Exception;
 	
	
	
}
