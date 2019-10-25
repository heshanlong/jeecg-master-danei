package com.gbiac.fams.business.unsafeevent.aircraftleakage.service;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FamsUnsafeeventAircraftleakageServiceI extends CommonService{
	
 	public void delete(FamsUnsafeeventAircraftleakageEntity entity) throws Exception;
 	
 	public Serializable save(FamsUnsafeeventAircraftleakageEntity entity, Map map) throws Exception;
 	
 	public void saveOrUpdate(FamsUnsafeeventAircraftleakageEntity entity, Map map,String str) throws Exception;
 	/**
	 * 按关键字查询数据
	 * 
	 * @param str
	 * @param page
	 * @param pageSize
	 * @param request
	 */
 	public List<FamsUnsafeeventAircraftleakageEntity> listApp(String str,Integer page,Integer pageSize,HttpServletRequest request);
 	
 	/**
	 * 获取特定日期内的数据
	 * @param day
	 * @param request
	 */
 	public List<FamsUnsafeeventAircraftleakageEntity> getDateByDay(Integer day,HttpServletRequest request);
}
