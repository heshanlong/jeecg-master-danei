package com.gbiac.fams.business.airportrunway.areaconfig.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.gbiac.fams.business.airportrunway.areaconfig.entity.FamsAreaConfigEntity;

public interface FamsAreaConfigServiceI extends CommonService{
	
 	public void delete(FamsAreaConfigEntity entity) throws Exception;
 	
 	public Serializable save(FamsAreaConfigEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAreaConfigEntity entity) throws Exception;
 	
 	/**
 	 * 查询是否已存在
 	 * @param area  区域
 	 * @param craftsite  机位
 	 * @return
 	 */
 	public Integer findByAreaAndCraftsite(String check_craftsite,String craftsite);
 	
 	public List<FamsAreaConfigEntity> loadAreaConfigDate();
 	
 	public List getAllNoAttentionArea(String searchInput, String username) throws Exception;
 	
 	
}
