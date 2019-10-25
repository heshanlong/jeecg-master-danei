package com.gbiac.fams.business.reform.service;
import com.gbiac.fams.business.reform.entity.FamsAircontrolReformEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface FamsAircontrolReformServiceI extends CommonService{
	
 	public void delete(FamsAircontrolReformEntity entity) throws Exception;
 	
 	public Serializable save(FamsAircontrolReformEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAircontrolReformEntity entity) throws Exception;

	public List<FamsAircontrolReformEntity> queryListByEntity(FamsAircontrolReformEntity entity);

}
