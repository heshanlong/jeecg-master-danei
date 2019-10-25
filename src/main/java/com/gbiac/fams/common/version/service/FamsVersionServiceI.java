package com.gbiac.fams.common.version.service;
import com.gbiac.fams.common.version.entity.FamsVersionEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FamsVersionServiceI extends CommonService{
	
 	public void delete(FamsVersionEntity entity) throws Exception;
 	
 	public Serializable save(FamsVersionEntity entity) throws Exception;

 	public Serializable save(FamsVersionEntity entity,String files) throws Exception;

 	public void saveOrUpdate(FamsVersionEntity entity) throws Exception;
 	
}
