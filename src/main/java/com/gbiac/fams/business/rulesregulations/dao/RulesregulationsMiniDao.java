package com.gbiac.fams.business.rulesregulations.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import com.gbiac.fams.business.rulesregulations.entity.FamsRulesregulationsEntity;
import com.gbiac.fams.business.rulesregulations.entity.dto.RulesregulationsAppDTO;

@MiniDao("rulesregulationsMiniDao")
public interface RulesregulationsMiniDao {
	
	//删除pid下的目录与文件
	@Arguments("pid")
 	@Sql("DELETE from fams_rulesregulations where pid = :pid")
	void deletePid(String pid);
	
	//获取主目录
	@Arguments({"pageNo","pageSize"})
 	List<FamsRulesregulationsEntity> getPidNullList(Integer pageNo,Integer pageSize);
 	
 	//根据PID获取子目录与文件
	@Arguments({"pId","roleCode","orgCode"})
 	List<FamsRulesregulationsEntity> getByPidList(String pId,String roleCode,String orgCode);
	
	//逻辑删除
	@Arguments("id")
	@Sql("update fams_rulesregulations set del_type = 1 where id = :id")
	Integer updateDelType(String id);
	
	//获取Pid下的文件
	@Arguments("pId")
	@Sql("select count(0) from fams_rulesregulations fr_2 where del_type = 0 and pid = :pId")
	Integer getByPidCount(String pId);
	
	//获取Pid下的文件
	@Arguments("pId")
	@Sql("select count(0) from fams_rulesregulations fr_2 where fr_2.pid = :pId and fr_2.rules_type = '1' and fr_2.del_type = 0")
	Integer getByPidAllCount(String pId);
	
	//判断此目录在该层下是否存在
	@Arguments({"pid","rulesName","rulesType"})
	Integer ifNameExist(String pid,String rulesName,Integer rulesType);
	
	//检索信息
	@Arguments({"rulesregulationsAppDTO","roleCode","orgCode"})
	List<FamsRulesregulationsEntity> retrieveList(RulesregulationsAppDTO rulesregulationsAppDTO,String roleCode,String orgCode);
	 	
}
