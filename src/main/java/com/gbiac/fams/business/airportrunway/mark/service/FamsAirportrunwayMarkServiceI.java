package com.gbiac.fams.business.airportrunway.mark.service;
import com.alibaba.fastjson.JSONArray;
import com.gbiac.fams.business.airportrunway.mark.entity.FamsAirportrunwayMarkEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FamsAirportrunwayMarkServiceI extends CommonService{
	
 	public void delete(FamsAirportrunwayMarkEntity entity) throws Exception;
 	
 	public Serializable save(FamsAirportrunwayMarkEntity entity,Map map) throws Exception;
 	
 	public void saveOrUpdate(FamsAirportrunwayMarkEntity entity,Map map) throws Exception;
 	/**
	 * 按关键字查询数据
	 * 
	 * @param str
	 * @param page
	 * @param pageSize
	 * @param request
	 */
 	public List<FamsAirportrunwayMarkEntity>  listApp(String str,Integer page,Integer pageSize,HttpServletRequest request);
}
