package com.gbiac.fams.business.famsAttenntionArtea.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

import com.gbiac.fams.business.famsAttenntionArtea.entity.FamsAttentionAreaEntity;

@MiniDao
public interface FamsAttentionAreaDao {
	
	@Arguments({"searchInput","userName"})
	List<FamsAttentionAreaEntity> getAllAttentionArea(String searchInput, String userName);
	
	
}
