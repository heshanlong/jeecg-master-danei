package com.gbiac.fams.business.main.index.dao;

import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

import java.util.List;

@MiniDao("indexDao")
public interface IndexDaoI {
	/**
  	* 按关键字匹配数据分页查询
	* @param searchInput
	* @param pageApp
	* @param pageSize
	* @return
	*/
	@Arguments({"searchInput","pageApp","pageSize"})
    List<FamsUnsafeeventAircraftleakageEntity> queryListByEntity(String searchInput,int pageApp, int pageSize);
}

