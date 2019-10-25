package com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.FamsAttentionCraftsiteUserEntity;

@MiniDao
public interface FamsUserAttentionCraftsiteDao {
	
	
	@Arguments("userName")
	@Sql("select * from fams_attention_craftsite_user where user_name = :userName")
	FamsAttentionCraftsiteUserEntity getAttentionCraftsiteByUserId(String userName);

	
}
