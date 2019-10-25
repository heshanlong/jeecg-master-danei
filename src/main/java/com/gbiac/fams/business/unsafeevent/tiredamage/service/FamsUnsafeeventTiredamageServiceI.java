package com.gbiac.fams.business.unsafeevent.tiredamage.service;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FocFlightDataEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FamsUnsafeeventTiredamageServiceI extends CommonService{
	
 	public void delete(FamsUnsafeeventTiredamageEntity entity) throws Exception;
 	
 	public Serializable save(FamsUnsafeeventTiredamageEntity entity, Map map) throws Exception;
 	
 	public void saveOrUpdate(FamsUnsafeeventTiredamageEntity entity, Map map,String str) throws Exception;
 	
 	public List<FocFlightDataEntity> getflightno(String flightno) throws Exception;
 	/**
	 * 按关键字查询数据
	 * 
	 * @param str
	 * @param page
	 * @param pageSize
	 * @param request
	 */
 	public List<FamsUnsafeeventTiredamageEntity>  listApp(String str,Integer page,Integer pageSize,HttpServletRequest request);
 	
 	/**
	 * 获取特定日期内的数据
	 * @param day
	 * @param request
	 */
 	public List<FamsUnsafeeventTiredamageEntity> getDateByDay(Integer day,HttpServletRequest request);
}
