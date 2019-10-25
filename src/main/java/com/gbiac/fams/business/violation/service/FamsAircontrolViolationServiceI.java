package com.gbiac.fams.business.violation.service;
import com.gbiac.fams.business.violation.entity.FamsAircontrolViolationEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface FamsAircontrolViolationServiceI extends CommonService{
	
 	public void delete(FamsAircontrolViolationEntity entity) throws Exception;
 	
 	public Serializable save(FamsAircontrolViolationEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAircontrolViolationEntity entity) throws Exception;

	List<FamsAircontrolViolationEntity> queryListByEntity(FamsAircontrolViolationEntity entity);

}
