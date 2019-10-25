package com.gbiac.fams.common.flow.service;

import com.gbiac.fams.common.flow.entity.FamsCommonFlowEntity;
import com.gbiac.fams.common.flow.page.FamsCommonFlowPage;
import org.jeecgframework.core.common.service.CommonService;

public interface FamsCommonFlowServiceI extends CommonService{
 	public void delete(FamsCommonFlowEntity entity) throws Exception;
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(FamsCommonFlowPage famsCommonFlowPage) throws Exception;
	        
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(FamsCommonFlowPage famsCommonFlowPage) throws Exception;
	
	/**
	 * 删除一对多
	 * 
	 */
	public void delMain (FamsCommonFlowEntity famsCommonFlow) throws Exception;
}
