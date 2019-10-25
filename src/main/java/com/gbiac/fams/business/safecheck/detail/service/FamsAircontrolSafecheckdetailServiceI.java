package com.gbiac.fams.business.safecheck.detail.service;
import com.gbiac.fams.business.safecheck.detail.entity.FamsAircontrolSafecheckdetailEntity;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.minidao.annotation.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface FamsAircontrolSafecheckdetailServiceI extends CommonService{
	
 	public void delete(FamsAircontrolSafecheckdetailEntity entity) throws Exception;
 	
 	public Serializable save(FamsAircontrolSafecheckdetailEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsAircontrolSafecheckdetailEntity entity) throws Exception;

	public List<Map> querySafecheckDetail(String checkId, String checkProject);

	public void updateRes( String id, String result);
	
	public void updateDetailEntity(FamsAircontrolSafecheckdetailEntity entity);

}
