package com.gbiac.fams.common.push.service;
import com.gbiac.fams.common.push.entity.FamsCommonPushEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FamsCommonPushServiceI extends CommonService{
	
 	public void delete(FamsCommonPushEntity entity) throws Exception;
 	
 	public Serializable save(FamsCommonPushEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsCommonPushEntity entity) throws Exception;
 	
}
