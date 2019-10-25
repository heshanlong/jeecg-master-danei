package com.gbiac.fams.business.safecheck.safecheckconfig.service;
import com.gbiac.fams.business.safecheck.safecheckconfig.entity.FamsAircontrolSafecheckconfigEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface FamsAircontrolSafecheckconfigServiceI extends CommonService{
	
 	public void delete(FamsAircontrolSafecheckconfigEntity entity) throws Exception;
 	
 	public Serializable save(FamsAircontrolSafecheckconfigEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAircontrolSafecheckconfigEntity entity) throws Exception;

	public <T> List<T> getConfigList(Class<T> clazz, String checkProject, String type, String status);

}
