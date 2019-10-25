package com.gbiac.fams.business.unsafeevent.aircraftleakage.dao;

import com.gbiac.fams.business.reform.entity.FamsAircontrolReformEntity;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;



import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

import java.util.List;

@MiniDao("famsUnsafeeventAircraftleakageDao")
public interface FamsUnsafeeventAircraftleakageDaoI {
	/**
  	* 按关键字匹配数据分页查询
	* @param searchInput
	* @param pageApp
	* @param pageSize
	* @return
	*/
	@Arguments({"searchInput","pageApp","pageSize"})
    List<FamsUnsafeeventAircraftleakageEntity> queryListByEntity(String searchInput,int pageApp, int pageSize);
	
	/**
  	* 获取特定日期内的数据
	* @param day
	* @return
	*/
	@Arguments({"day"})
    List<FamsUnsafeeventAircraftleakageEntity> queryDateByDay(int day);
	/**
  	* 获取本月的数据
	* @return
	*/
    List<FamsUnsafeeventAircraftleakageEntity> queryDateByMoon();
}

