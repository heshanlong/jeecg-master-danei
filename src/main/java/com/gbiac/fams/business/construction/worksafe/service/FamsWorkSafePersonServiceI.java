package com.gbiac.fams.business.construction.worksafe.service;
import com.gbiac.fams.business.construction.worksafe.entity.FamsWorkSafePersonEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FamsWorkSafePersonServiceI extends CommonService{
	
 	public void delete(FamsWorkSafePersonEntity entity) throws Exception;
 	
 	public Serializable save(FamsWorkSafePersonEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsWorkSafePersonEntity entity) throws Exception;
 	
}
