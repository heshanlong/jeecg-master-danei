package com.gbiac.fams.business.construction.workapprove.service;

import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveNoPassEntity;

public interface FamsWorkApproveNoPassServiceI extends CommonService{
	
	public Serializable save(FamsWorkApproveNoPassEntity entity) throws Exception;
	
	public List<?> getNoPassApprove(String searchInput, Integer pageNum, Integer pageSize, String userName) throws Exception;

}
