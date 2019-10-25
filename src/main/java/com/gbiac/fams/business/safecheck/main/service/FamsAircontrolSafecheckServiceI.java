package com.gbiac.fams.business.safecheck.main.service;
import com.gbiac.fams.business.safecheck.detail.entity.FamsAircontrolSafecheckdetailEntity;
import com.gbiac.fams.business.safecheck.main.entity.FamsAircontrolSafecheckEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface FamsAircontrolSafecheckServiceI extends CommonService{
	
 	public void delete(FamsAircontrolSafecheckEntity entity) throws Exception;
 	
 	public Serializable save(FamsAircontrolSafecheckEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAircontrolSafecheckEntity entity) throws Exception;

	public Serializable save(FamsAircontrolSafecheckEntity entity,String ressult,String pcture) throws Exception;
	
	public Serializable save(FamsAircontrolSafecheckEntity entity, List<FamsAircontrolSafecheckdetailEntity> details, String pcture) throws Exception;

	public void update(FamsAircontrolSafecheckEntity entity,String result,String pcture) throws Exception;

	List<FamsAircontrolSafecheckEntity> queryListByEntity(FamsAircontrolSafecheckEntity entity);


}
