package com.gbiac.fams.common.flowown.service;

import com.gbiac.fams.common.flowown.entity.FamsCommonFlowOwnEntity;
import com.gbiac.fams.common.flowown.page.FamsCommonFlowOwnPage;
import org.jeecgframework.core.common.service.CommonService;

public interface FamsCommonFlowOwnServiceI extends CommonService{
 	public void delete(FamsCommonFlowOwnEntity entity) throws Exception;
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(FamsCommonFlowOwnPage famsCommonFlowOwnPage) throws Exception;
	        
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(FamsCommonFlowOwnPage famsCommonFlowOwnPage) throws Exception;
	
	/**
	 * 删除一对多
	 * 
	 */
	public void delMain (FamsCommonFlowOwnEntity famsCommonFlowOwn) throws Exception;
}
