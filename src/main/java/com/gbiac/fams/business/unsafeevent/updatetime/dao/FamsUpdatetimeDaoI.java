package com.gbiac.fams.business.unsafeevent.updatetime.dao;

import com.gbiac.fams.business.reform.entity.FamsAircontrolReformEntity;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageEntity;
import com.gbiac.fams.business.unsafeevent.updatetime.entity.FamsUpdatetimeEntity;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@MiniDao("famsUpdatetimeDao")
public interface FamsUpdatetimeDaoI {
    /**
  	* 按关键字匹配数据分页查询
	* @param searchInput
	* @param pageApp
	* @param pageSize
	* @return
	*/
	@Arguments({"searchInput","pageApp","pageSize"})
    List<FamsUpdatetimeEntity> queryListByEntity(String searchInput,int pageApp, int pageSize);
	
}
