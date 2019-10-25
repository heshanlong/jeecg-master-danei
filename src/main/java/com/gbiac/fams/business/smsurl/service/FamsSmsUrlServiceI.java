package com.gbiac.fams.business.smsurl.service;
import com.gbiac.fams.business.smsurl.entity.FamsSmsUrlEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FamsSmsUrlServiceI extends CommonService{
	
 	public void delete(FamsSmsUrlEntity entity) throws Exception;
 	
 	public Serializable save(FamsSmsUrlEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsSmsUrlEntity entity) throws Exception;
 	
}
