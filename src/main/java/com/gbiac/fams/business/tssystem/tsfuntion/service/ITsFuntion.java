package com.gbiac.fams.business.tssystem.tsfuntion.service;

import java.util.List;

import com.gbiac.fams.business.tssystem.tsfuntion.entity.TSFunction;


public interface ITsFuntion {

	//app获取功能菜单
	List<TSFunction> appGetMenu(String userid,String pid);
	
	//根据Pid获取
	List<TSFunction> appByPidGetMenu(String pid);
}
