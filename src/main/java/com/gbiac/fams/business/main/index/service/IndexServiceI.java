package com.gbiac.fams.business.main.index.service;

import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IndexServiceI extends CommonService{
	
 	public void delete(FamsUnsafeeventAircraftleakageEntity entity) throws Exception;
 	
 	public Serializable save(FamsUnsafeeventAircraftleakageEntity entity, Map map) throws Exception;
 	
 	public void saveOrUpdate(FamsUnsafeeventAircraftleakageEntity entity, Map map) throws Exception;

	/**
	 * 查询首页的施工数据
	 * @return
	 */
	List getWorkInfo();
	/**
	 * 查询首页的场道数据
	 * @return
	 */
	public Map getAirportrunway() throws Exception;

}
