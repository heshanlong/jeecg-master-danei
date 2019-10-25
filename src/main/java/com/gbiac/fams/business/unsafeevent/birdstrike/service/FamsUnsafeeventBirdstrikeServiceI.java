package com.gbiac.fams.business.unsafeevent.birdstrike.service;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FamsUnsafeeventBirdstrikeServiceI extends CommonService{
	
 	public void delete(FamsUnsafeeventBirdstrikeEntity entity) throws Exception;
 	
 	public Serializable save(FamsUnsafeeventBirdstrikeEntity entity, Map map) throws Exception;
 	
 	public void saveOrUpdate(FamsUnsafeeventBirdstrikeEntity entity, Map map,String str) throws Exception;
 	/**
	 * 按关键字查询数据
	 * 
	 * @param str
	 * @param page
	 * @param pageSize
	 * @param request
	 */
 	public List<FamsUnsafeeventBirdstrikeEntity> listApp(String str,Integer page,Integer pageSize,HttpServletRequest request);
 	
 	/**
	 * 获取特定日期内的数据
	 * @param day
	 * @param request
	 */
 	public List<FamsUnsafeeventBirdstrikeEntity> getDateByDay(Integer day,HttpServletRequest request);
}
