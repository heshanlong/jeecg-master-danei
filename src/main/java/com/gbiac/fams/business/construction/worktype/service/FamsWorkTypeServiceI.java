package com.gbiac.fams.business.construction.worktype.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.gbiac.fams.business.construction.worktype.entity.FamsWorkTypeEntity;

public interface FamsWorkTypeServiceI extends CommonService{
	
 	public void delete(FamsWorkTypeEntity entity) throws Exception;
 	
 	public Serializable save(FamsWorkTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsWorkTypeEntity entity) throws Exception;
 	
 	public List getWorkTypeDate();
 	
}
