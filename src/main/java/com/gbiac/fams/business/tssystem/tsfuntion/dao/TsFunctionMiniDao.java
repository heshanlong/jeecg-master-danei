package com.gbiac.fams.business.tssystem.tsfuntion.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import com.gbiac.fams.business.tssystem.tsfuntion.entity.TSFunction;

@MiniDao
public interface TsFunctionMiniDao {
	
	//app获取功能菜单
	@Arguments({"userid","pid"})
	List<TSFunction> appGetMenu(String userid,String pid);
	
	//根据Pid获取
	@Arguments("pid")
	@Sql("select * from t_s_function where parentfunctionid = :pid")
	List<TSFunction> appByPidGetMenu(String pid);
	
}
