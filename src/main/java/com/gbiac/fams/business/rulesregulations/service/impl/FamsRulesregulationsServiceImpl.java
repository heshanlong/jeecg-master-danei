package com.gbiac.fams.business.rulesregulations.service.impl;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.rulesregulations.dao.RulesregulationsMiniDao;
import com.gbiac.fams.business.rulesregulations.entity.FamsRulesregulationsEntity;
import com.gbiac.fams.business.rulesregulations.entity.dto.RulesregulationsAppDTO;
import com.gbiac.fams.business.rulesregulations.service.FamsRulesregulationsServiceI;

@Service("famsRulesregulationsService")
@Transactional
public class FamsRulesregulationsServiceImpl extends CommonServiceImpl implements FamsRulesregulationsServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private RulesregulationsMiniDao rulesregulationsMiniDao;
	
 	public void delete(FamsRulesregulationsEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsRulesregulationsEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsRulesregulationsEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

	@Override
	public void deletePid(String pId) {
		rulesregulationsMiniDao.deletePid(pId);
	}

	@Override
	public List<FamsRulesregulationsEntity> getPidNullList(Integer pageNo,Integer pageSize) {
		return rulesregulationsMiniDao.getPidNullList(pageNo, pageSize);
	}

	@Override
	public List<FamsRulesregulationsEntity> getByPidList(String pId,String roleCode,String orgCode) {
		return rulesregulationsMiniDao.getByPidList(pId,roleCode,orgCode);
	}

	@Override
	public Integer updateDelType(String id) {
		return rulesregulationsMiniDao.updateDelType(id);
	}

	@Override
	public Integer getByPidCount(String pId) {
		return rulesregulationsMiniDao.getByPidCount(pId);
	}

	@Override
	public Integer ifNameExist(String pid, String rulesName,Integer rulesType) {
		return rulesregulationsMiniDao.ifNameExist(pid, rulesName,rulesType);
	}

	@Override
	public Integer getByPidAllCount(String pId) {
		return rulesregulationsMiniDao.getByPidAllCount(pId);
	}

	@Override
	public List<FamsRulesregulationsEntity> retrieveList(RulesregulationsAppDTO rulesregulationsAppDTO,String roleCode,String orgCode) {
		return rulesregulationsMiniDao.retrieveList(rulesregulationsAppDTO,roleCode,orgCode);
	}
 	
}