package com.gbiac.fams.business.airportrunway.airportclean.service;
import com.gbiac.fams.business.airportrunway.airportclean.entity.FamsAirportrunwayCleanEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FamsAirportrunwayCleanServiceI extends CommonService{
	
 	public void delete(FamsAirportrunwayCleanEntity entity) throws Exception;
 	
 	public Serializable save(FamsAirportrunwayCleanEntity entity, Map map) throws Exception;
 	
 	public void saveOrUpdate(FamsAirportrunwayCleanEntity entity, Map map) throws Exception;
 	/**
	 * 按关键字查询数据
	 * 
	 * @param str
	 * @param page
	 * @param pageSize
	 * @param request
	 */
 	public List<FamsAirportrunwayCleanEntity>  listApp(String str,Integer page,Integer pageSize,HttpServletRequest request);
}
