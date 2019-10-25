package com.gbiac.fams.business.unsafeevent.birdstrike.dao;

import com.gbiac.fams.business.reform.entity.FamsAircontrolReformEntity;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@MiniDao("famsUnsafeeventBirdstrikeDao")
public interface FamsUnsafeeventBirdstrikeDaoI {
	/**
  	* 按关键字匹配数据分页查询
	* @param searchInput
	* @param pageApp
	* @param pageSize
	* @return
	*/
	@Arguments({"searchInput","pageApp","pageSize"})
    List<FamsUnsafeeventBirdstrikeEntity> queryListByEntity(String searchInput,int pageApp, int pageSize);
	
	/**
  	* 获取特定日期内的数据
	* @param day
	* @return
	*/
	@Arguments({"day"})
    List<FamsUnsafeeventBirdstrikeEntity> queryDateByDay(int day);
	/**
  	* 获取本月的数据
	* @return
	*/
    List<FamsUnsafeeventBirdstrikeEntity> queryDateByMoon();
}
