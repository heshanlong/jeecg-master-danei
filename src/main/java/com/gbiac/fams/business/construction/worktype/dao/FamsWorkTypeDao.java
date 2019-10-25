package com.gbiac.fams.business.construction.worktype.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import com.gbiac.fams.business.construction.worktype.entity.FamsWorkTypeEntity;

@MiniDao
public interface FamsWorkTypeDao {

	@Sql("SELECT * FROM fams_work_type")
	List<FamsWorkTypeEntity> getWorkTypeDate();
}
