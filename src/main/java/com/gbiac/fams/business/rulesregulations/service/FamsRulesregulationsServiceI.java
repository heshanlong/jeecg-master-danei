package com.gbiac.fams.business.rulesregulations.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.gbiac.fams.business.rulesregulations.entity.FamsRulesregulationsEntity;
import com.gbiac.fams.business.rulesregulations.entity.dto.RulesregulationsAppDTO;

public interface FamsRulesregulationsServiceI extends CommonService{
	
 	public void delete(FamsRulesregulationsEntity entity) throws Exception;
 	
 	public Serializable save(FamsRulesregulationsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsRulesregulationsEntity entity) throws Exception;
 	
 	//删除目录里的子目录文件
 	public void deletePid(String pId);
 	
 	//获取主目录
 	public List<FamsRulesregulationsEntity> getPidNullList(Integer pageNo,Integer pageSize);
 	
 	//根据PID获取子目录与文件
 	public List<FamsRulesregulationsEntity> getByPidList(String pId,String roleCode,String orgCode);
 	
 	//逻辑删除
 	public Integer updateDelType(String id);
 	
 	//获取Pid下的文件总数 --用户删除前判断目录下是否还有文件
 	public Integer getByPidCount(String pId);
 	
 	//获取Pid下的文件总数 --用户app端统计目录里的文件数量
 	public Integer getByPidAllCount(String pId);
 	
 	/**
 	 * 判断此目录在该层下是否存在
 	 * @param pid
 	 * @param rulesName  名称
 	 * @param rulesType  0目录   1文件 
 	 * @return
 	 */
 	public Integer ifNameExist(String pid,String rulesName,Integer rulesType);
 	
 	//检索信息
 	public List<FamsRulesregulationsEntity> retrieveList(RulesregulationsAppDTO rulesregulationsAppDTO,String roleCode,String orgCode);
 	
}
	