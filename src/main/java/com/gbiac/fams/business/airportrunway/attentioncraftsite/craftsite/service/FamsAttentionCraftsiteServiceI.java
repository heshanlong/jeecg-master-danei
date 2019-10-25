package com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsite.service;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsite.entity.FamsAttentionCraftsiteEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FamsAttentionCraftsiteServiceI extends CommonService{
	
 	public void delete(FamsAttentionCraftsiteEntity entity) throws Exception;
 	
 	public Serializable save(FamsAttentionCraftsiteEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAttentionCraftsiteEntity entity) throws Exception;
 	
}
