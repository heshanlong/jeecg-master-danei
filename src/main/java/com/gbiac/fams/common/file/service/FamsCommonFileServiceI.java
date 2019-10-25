package com.gbiac.fams.common.file.service;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FamsCommonFileServiceI extends CommonService{
	
 	public void delete(FamsCommonFileEntity entity) throws Exception;
 	
 	public Serializable save(FamsCommonFileEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsCommonFileEntity entity) throws Exception;
 	
}
