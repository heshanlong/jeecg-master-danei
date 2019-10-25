package com.gbiac.fams.business.airarrivalreminder.dao;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import com.gbiac.fams.business.airarrivalreminder.entity.FamsAirportrunwayPlaceEntity;
import com.gbiac.fams.business.airarrivalreminder.entity.FamsAirportrunwayPlaceSendMessageEntity;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.rulesregulations.entity.FamsRulesregulationsEntity;
import com.gbiac.fams.business.rulesregulations.entity.dto.RulesregulationsAppDTO;

@MiniDao
public interface FamsAirportrunwayPlaceDao {
	
	//获取相关部门负责人的提醒时间
	@Sql("SELECT reminder_time FROM fams_airportrunway_place")
	List<FamsAirportrunwayPlaceEntity> getReminderTime();

	//获取航班降落时间
	@Sql("SELECT a.AIRL_ARPT_FPLT FROM di_aomip_flight_snapshot t,di_aomip_flight_airl_snapshot a WHERE t.AFSS_FFID = a.AIRL_FFID AND t.AFSS_FLIO = 'A'")
	List<FamsAirportrunwayPlaceSendMessageEntity> getLandingTime();
	 	
}
