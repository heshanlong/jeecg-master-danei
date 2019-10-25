package com.gbiac.fams.common.progress.service;
import com.gbiac.fams.common.progress.entity.FamsCommonProgressEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FamsCommonProgressServiceI extends CommonService{
	
 	public void delete(FamsCommonProgressEntity entity) throws Exception;
 	
 	public Serializable save(FamsCommonProgressEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsCommonProgressEntity entity) throws Exception;

	/**
	 * 通过bid获取最近的一条数据
	 * @param id
	 * @return
	 */
    FamsCommonProgressEntity getRecentEntityByBid(String id,String state);
}
