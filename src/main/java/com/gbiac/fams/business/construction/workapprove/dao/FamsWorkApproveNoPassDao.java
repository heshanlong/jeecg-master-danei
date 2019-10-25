package com.gbiac.fams.business.construction.workapprove.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;

import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveNoPassEntity;

@MiniDao
public interface FamsWorkApproveNoPassDao {
	
	@Arguments({"searchInput","pageNum","pageSize", "userName"})
	List<FamsWorkApproveNoPassEntity> getNoPassApprove(String searchInput, Integer pageNum, Integer pageSize, String userName);

}
