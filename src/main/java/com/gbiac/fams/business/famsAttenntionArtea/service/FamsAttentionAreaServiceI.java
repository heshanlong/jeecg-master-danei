package com.gbiac.fams.business.famsAttenntionArtea.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.gbiac.fams.business.famsAttenntionArtea.entity.FamsAttentionAreaEntity;

public interface FamsAttentionAreaServiceI extends CommonService{
	
 	public void delete(FamsAttentionAreaEntity entity) throws Exception;
 	
 	public Serializable save(FamsAttentionAreaEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAttentionAreaEntity entity) throws Exception;
 	
 	public List getAllAttentionArea(String searchInput, String username) throws Exception;
 	
 	
 	
 	
}
