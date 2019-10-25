package com.gbiac.fams.business.construction.workappstate.service;

import com.gbiac.fams.business.construction.workappstate.entity.FamsWorkAppStateEntity;
import com.gbiac.fams.business.construction.workappstate.page.FamsWorkAppStatePage;
import org.jeecgframework.core.common.service.CommonService;

public interface FamsWorkAppStateServiceI extends CommonService{
 	public void delete(FamsWorkAppStateEntity entity) throws Exception;
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(FamsWorkAppStatePage famsWorkAppStatePage) throws Exception;
	        
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(FamsWorkAppStatePage famsWorkAppStatePage) throws Exception;
	
	/**
	 * 删除一对多
	 * 
	 */
	public void delMain (FamsWorkAppStateEntity famsWorkAppState) throws Exception;
	
	/**
	 * 根据角色code称和状态获取该角色拥有的节点  eg: s-in,s-out
	 * @param roleCode
	 * @param workingState
	 * @return
	 */
	String getNodesByRoleCodeAndState(String roleCode,Integer workFlag,Integer workingState);
}
