package com.gbiac.fams.common.map.service;
import com.gbiac.fams.common.map.entity.FamsCommonMapEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface FamsCommonMapServiceI extends CommonService{
	
 	public void delete(FamsCommonMapEntity entity) throws Exception;
 	
 	public Serializable save(FamsCommonMapEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsCommonMapEntity entity) throws Exception;

	/**
	 * 根据施工时间获取该时间当天的所有施工地图信息集合
	 * @param time 时间戳
	 * @return
	 */
    List<Map> getMaps(String time);
}
