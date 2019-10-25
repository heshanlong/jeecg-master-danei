package com.gbiac.fams.business.construction.worknodeinfo.service;
import com.gbiac.fams.business.construction.worknodeinfo.entity.FamsWorkNodeInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FamsWorkNodeInfoServiceI extends CommonService{
	
 	public void delete(FamsWorkNodeInfoEntity entity) throws Exception;
 	
 	public Serializable save(FamsWorkNodeInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsWorkNodeInfoEntity entity) throws Exception;

	/**
	 * 通过taskkey获取taskstate
	 * @param taskKey
	 * @return
	 */
    String getStateByTaskKey(String taskKey);
}
