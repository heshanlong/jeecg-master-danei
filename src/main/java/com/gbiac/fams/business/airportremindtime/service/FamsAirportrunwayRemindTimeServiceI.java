package com.gbiac.fams.business.airportremindtime.service;
import com.gbiac.fams.business.airportremindtime.entity.FamsAirportrunwayRemindTimeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FamsAirportrunwayRemindTimeServiceI extends CommonService{
	
 	public void delete(FamsAirportrunwayRemindTimeEntity entity) throws Exception;
 	
 	public Serializable save(FamsAirportrunwayRemindTimeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAirportrunwayRemindTimeEntity entity) throws Exception;
 	
}
