package com.gbiac.fams.business.diaomipstndinfo.service;
import com.gbiac.fams.business.diaomipstndinfo.entity.DiAomipStndInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface DiAomipStndInfoServiceI extends CommonService{
	
 	public void delete(DiAomipStndInfoEntity entity) throws Exception;
 	
 	public Serializable save(DiAomipStndInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(DiAomipStndInfoEntity entity) throws Exception;
 	
}
