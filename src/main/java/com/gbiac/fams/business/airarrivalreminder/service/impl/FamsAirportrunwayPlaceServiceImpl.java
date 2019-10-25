package com.gbiac.fams.business.airarrivalreminder.service.impl;
import com.gbiac.fams.business.airarrivalreminder.service.FamsAirportrunwayPlaceServiceI;

import jxl.write.DateFormat;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.gbiac.fams.business.airarrivalreminder.dao.FamsAirportrunwayPlaceDao;
import com.gbiac.fams.business.airarrivalreminder.entity.FamsAirportrunwayPlaceEntity;
import com.gbiac.fams.business.airarrivalreminder.entity.FamsAirportrunwayPlaceSendMessageEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("famsAirportrunwayPlaceService")
@Transactional
public class FamsAirportrunwayPlaceServiceImpl extends CommonServiceImpl implements FamsAirportrunwayPlaceServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsAirportrunwayPlaceDao famsAirportrunwayPlaceDao;
	
 	public void delete(FamsAirportrunwayPlaceEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAirportrunwayPlaceEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAirportrunwayPlaceEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
	/**
	 * 发送短信业务
	 * 
	 * 1.查询到每个部门对应人的提醒时间
 	 * 2.查询到航班预计降落时间
 	 * 3.对航班提醒时间与当前时间进行相减，获得时间差
 	 * 4.若在时间差内发送短信，若不在时间差内，返回
	 * 判断人数，若取得用户人数等于一，进行单发短信
	 * 			若用户人数大于一，进行群发短信
	 */
	public void sendPhoneMessage() throws Exception {
		//获取航班降落时间
		List<FamsAirportrunwayPlaceSendMessageEntity> landingTime = famsAirportrunwayPlaceDao.getLandingTime();
		//获取相关部门负责人的提醒时间
		List<FamsAirportrunwayPlaceEntity> reminderTime = famsAirportrunwayPlaceDao.getReminderTime();
		long lands = 0l;
		for(FamsAirportrunwayPlaceSendMessageEntity landt : landingTime){
			Date landDat = landt.getAirlArptFplt();
			lands = landDat.getTime();
		}
		long currents = System.currentTimeMillis();
		long rs = 0l;
		for(FamsAirportrunwayPlaceEntity rt : reminderTime){
			String rtt = rt.getReminderTime();
			rs  = Integer.parseInt(rtt) * 60 * 1000;
		}
		if(lands - currents == rs){
			
		}
		
		
		
		
		
	}

 	
	
	
	
}