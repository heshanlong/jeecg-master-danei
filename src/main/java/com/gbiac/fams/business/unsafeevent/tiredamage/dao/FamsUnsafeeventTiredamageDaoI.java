package com.gbiac.fams.business.unsafeevent.tiredamage.dao;

import com.gbiac.fams.business.reform.entity.FamsAircontrolReformEntity;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageEntity;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@MiniDao("famsUnsafeeventTiredamageDao")
public interface FamsUnsafeeventTiredamageDaoI {
    /**
  	* 按关键字匹配数据分页查询
	* @param searchInput
	* @param pageApp
	* @param pageSize
	* @return
	*/
	@Arguments({"searchInput","pageApp","pageSize"})
    List<FamsUnsafeeventTiredamageEntity> queryListByEntity(String searchInput,int pageApp, int pageSize);
	
	/**
  	* 获取特定日期内的数据
	* @param day
	* @return
	*/
	@Arguments({"day"})
    List<FamsUnsafeeventTiredamageEntity> queryDateByDay(int day);
	/**
  	* 获取本月的数据
	* @return
	*/
    List<FamsUnsafeeventTiredamageEntity> queryDateByMoon();
}
