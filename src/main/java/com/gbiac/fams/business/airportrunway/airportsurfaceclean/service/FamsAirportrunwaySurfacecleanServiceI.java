package com.gbiac.fams.business.airportrunway.airportsurfaceclean.service;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FamsAirportrunwaySurfacecleanServiceI extends CommonService{
	
 	public void delete(FamsAirportrunwaySurfacecleanEntity entity) throws Exception;
 	
 	public Serializable save(FamsAirportrunwaySurfacecleanEntity entity, Map map) throws Exception;
 	
 	public void saveOrUpdate(FamsAirportrunwaySurfacecleanEntity entity, Map map) throws Exception;
 	/**
	 * 按关键字查询数据
	 * 
	 * @param str
	 * @param page
	 * @param pageSize
	 * @param request
	 */
 	public List<FamsAirportrunwaySurfacecleanEntity>  listApp(String str,Integer page,Integer pageSize,HttpServletRequest request);
}
