package com.gbiac.fams.business.construction.workcheck.service;

import com.gbiac.fams.business.construction.workcheck.entity.FamsWorkCheckEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.Map;

public interface FamsWorkCheckServiceI extends CommonService{

	public void delete(FamsWorkCheckEntity entity) throws Exception;

	public Serializable save(FamsWorkCheckEntity entity,Map<String,String> itemValues,String files) throws Exception;

	public void saveOrUpdate(FamsWorkCheckEntity entity,Map<String,String> itemValues,String files) throws Exception;
	/**
	 * 获取分类表中的数据并做成树形结构
	 * @param code
	 * @return
	 */
	Map getCategory(String code);

	/**
	 * 获取分类表中的数据并做成树形结构
	 * 并带出检查的结果
	 * @param code
	 * @param bid
	 * @return
	 */
	Map getCategory(String code,String bid);


}
