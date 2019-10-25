package com.gbiac.fams.business.airportrunway.mark.dao;

import com.gbiac.fams.business.airportrunway.mark.entity.FamsAirportrunwayMarkEntity;
import com.gbiac.fams.business.airportrunway.repair.entity.FamsAirportrunwayRepairEntity;
import com.gbiac.fams.business.reform.entity.FamsAircontrolReformEntity;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@MiniDao("famsAirportrunwayMarkDao")
public interface FamsAirportrunwayMarkDaoI {
    /**
  	* 按关键字匹配数据分页查询
	* @param searchInput
	* @param pageApp
	* @param pageSize
	* @return
	*/
	@Arguments({"searchInput","pageApp","pageSize"})
    List<FamsAirportrunwayMarkEntity> queryListByEntity(String searchInput,int pageApp, int pageSize);
}
