package com.gbiac.fams.business.construction.workrole.service;

import com.gbiac.fams.business.construction.workrole.entity.FamsWorkRoleEntity;
import com.gbiac.fams.business.construction.workrole.page.FamsWorkRolePage;
import org.jeecgframework.core.common.service.CommonService;

public interface FamsWorkRoleServiceI extends CommonService{
 	public void delete(FamsWorkRoleEntity entity) throws Exception;
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(FamsWorkRolePage famsWorkRolePage) throws Exception;
	        
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(FamsWorkRolePage famsWorkRolePage) throws Exception;
	
	/**
	 * 删除一对多
	 * 
	 */
	public void delMain (FamsWorkRoleEntity famsWorkRole) throws Exception;
	
	/**
	 * 根据用户id获取用户的角色code
	 * @param userId
	 * @return
	 */
	String getRoleCodeByUserId(String userId);
}
