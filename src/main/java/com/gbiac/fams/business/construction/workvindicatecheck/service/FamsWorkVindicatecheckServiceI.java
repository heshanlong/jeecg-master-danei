package com.gbiac.fams.business.construction.workvindicatecheck.service;
import com.gbiac.fams.business.construction.workvindicatecheck.entity.FamsWorkVindicatecheckEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.Map;

public interface FamsWorkVindicatecheckServiceI extends CommonService{
	
 	public void delete(FamsWorkVindicatecheckEntity entity) throws Exception;
 	
 	public Serializable save(FamsWorkVindicatecheckEntity entity, Map<String,String> itemValues, String files) throws Exception;
 	
 	public void saveOrUpdate(FamsWorkVindicatecheckEntity entity, Map<String,String> itemValues,String files) throws Exception;
 	
}
