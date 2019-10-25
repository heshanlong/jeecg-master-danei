package com.gbiac.fams.business.airportrunway.repair.service;
import com.gbiac.fams.business.airportrunway.repair.entity.FamsAirportrunwayRepairEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FamsAirportrunwayRepairServiceI extends CommonService{
	
 	public void delete(FamsAirportrunwayRepairEntity entity) throws Exception;
 	
 	public Serializable save(FamsAirportrunwayRepairEntity entity,Map map) throws Exception;
 	
 	public void saveOrUpdate(FamsAirportrunwayRepairEntity entity,Map map) throws Exception;
 	/**
	 * 按关键字查询数据
	 * 
	 * @param str
	 * @param page
	 * @param pageSize
	 * @param request
	 */
 	public List<FamsAirportrunwayRepairEntity>  listApp(String str,Integer page,Integer pageSize,HttpServletRequest request);
}
