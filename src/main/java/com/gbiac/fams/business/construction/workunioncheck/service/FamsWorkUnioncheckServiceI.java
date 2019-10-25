package com.gbiac.fams.business.construction.workunioncheck.service;
import com.gbiac.fams.business.construction.workunioncheck.entity.FamsWorkUnioncheckEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.Map;

public interface FamsWorkUnioncheckServiceI extends CommonService{
	
 	public void delete(FamsWorkUnioncheckEntity entity) throws Exception;
 	
 	public Serializable save(FamsWorkUnioncheckEntity entity, Map<String,String> itemValues,String files) throws Exception;
 	
 	public void saveOrUpdate(FamsWorkUnioncheckEntity entity,Map<String,String> itemValues,String files) throws Exception;
 	
}
