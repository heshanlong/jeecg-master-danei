package com.gbiac.fams.business.tssystem.tsfuntion.service.inpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbiac.fams.business.tssystem.tsfuntion.dao.TsFunctionMiniDao;
import com.gbiac.fams.business.tssystem.tsfuntion.entity.TSFunction;
import com.gbiac.fams.business.tssystem.tsfuntion.service.ITsFuntion;

@Service
public class ITsFuntionImpl implements ITsFuntion {

	@Autowired
	TsFunctionMiniDao tsFunctionMiniDao;
	
	@Override
	public List<TSFunction> appGetMenu(String userid,String pid) {
		return tsFunctionMiniDao.appGetMenu(userid,pid);
	}

	@Override
	public List<TSFunction> appByPidGetMenu(String pid) {
		// TODO Auto-generated method stub
		return tsFunctionMiniDao.appByPidGetMenu(pid);
	}

}
