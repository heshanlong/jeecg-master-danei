package com.gbiac.fams.business.airportrunway.areaconfig.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import com.gbiac.fams.business.airportrunway.areaconfig.entity.FamsAreaConfigEntity;

@MiniDao
public interface FamsAreaConfigDao {

	@Arguments({ "check_craftsite", "craftsite" })
	@Sql("select count(0) from fams_attention_craftsite where craftsite = :craftsite and check_craftsite = 1")
	Integer findByAreaAndCraftsite(String check_craftsite, String craftsite);

	/*
	 * @Sql("select * FROM fams_area_config WHERE craftsite LIKE :seat")
	 * 
	 * @Arguments("seat") String getAreaNameBySeat(String seat);
	 */
	
	@Sql("SELECT * from fams_area_config")
	List<FamsAreaConfigEntity> loadAreaConfigDate();
	
	@Arguments({"searchInput","userName"})
	List<FamsAreaConfigEntity> getAllNoAttentionArea(String searchInput, String userName);

}
