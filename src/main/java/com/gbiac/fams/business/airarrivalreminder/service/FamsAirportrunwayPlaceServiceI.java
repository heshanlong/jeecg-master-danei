package com.gbiac.fams.business.airarrivalreminder.service;
import com.gbiac.fams.business.airarrivalreminder.entity.FamsAirportrunwayPlaceEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface FamsAirportrunwayPlaceServiceI extends CommonService{
	
 	public void delete(FamsAirportrunwayPlaceEntity entity) throws Exception;
 	
 	public Serializable save(FamsAirportrunwayPlaceEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAirportrunwayPlaceEntity entity) throws Exception;
 	
 	public void sendPhoneMessage() throws Exception;
 	
}
