package com.gbiac.fams.business.unsafeevent.tiredamage.service.impl;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;
import com.gbiac.fams.business.unsafeevent.birdstrike.dao.FamsUnsafeeventBirdstrikeDaoI;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.service.FamsUnsafeeventTiredamageServiceI;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;

import com.gbiac.fams.business.unsafeevent.tiredamage.dao.FamsUnsafeeventTiredamageDaoI;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FocFlightDataEntity;
import com.gbiac.fams.business.unsafeevent.updatetime.entity.FamsUpdatetimeEntity;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.constant.MessageConstant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Service("famsUnsafeeventTiredamageService")
@Transactional
public class FamsUnsafeeventTiredamageServiceImpl extends CommonServiceImpl implements FamsUnsafeeventTiredamageServiceI {
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsUnsafeeventTiredamageDaoI famsUnsafeeventTiredamageDao;
	@Autowired
	private SystemService systemService;
 	public void delete(FamsUnsafeeventTiredamageEntity entity) throws Exception{
// 		super.delete(entity);
 		entity.setDel("1");
 		super.saveOrUpdate(entity);
 	}
 	
 	public Serializable save(FamsUnsafeeventTiredamageEntity entity, Map map) throws Exception{
 		entity.setNo(systemService.getFamsNumberByTSTypegroup("轮胎损伤",FamsUnsafeeventTiredamageEntity.class,"createDate"));
 		Serializable t = super.save(entity);
 		String files= (String) map.get("files");
 		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsUnsafeeventTiredamageEntity entity, Map map,String str) throws Exception{
 		super.saveOrUpdate(entity);
		String files= (String) map.get("files");
		if(famsCommonService.hasUpdateFile(files,"work",entity.getId(), MessageConstant.STATEBEFORE)){
			if (str.length() > 0) {
				str += " </br> " + "修改了图片";
			}
			else{
				str =  "修改了图片";

			}

		}


		if (str.length() > 0) {
 			FamsUpdatetimeEntity ut = new FamsUpdatetimeEntity();
 	 		ut.setCreateBy(entity.getUpdateBy());
 	 		ut.setCreateDate(new Date());
 	 		ut.setIntime(new Date());
 	 		List<TSBaseUser> tSBaseUserList = systemService.findByProperty(TSBaseUser.class, "userName",entity.getUpdateBy());
 	 		if (tSBaseUserList.size() > 0) {
 	 			ut.setPeople(tSBaseUserList.get(0).getRealName());
			}
 	 		ut.setModule("tiredamage");
 	 		ut.setNo(entity.getId());
 	 		ut.setAdded(str);
 	 		Serializable t = super.save(ut);
		}
 		
		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 	}

	@Override
	public List<FocFlightDataEntity> getflightno(String flightno) throws Exception {
		String sql ="SELECT * FROM fams_flight_data where flightno = ? ORDER BY creationtime DESC LIMIT  0,1"; 
		List<FocFlightDataEntity> e = jdbcTemplate.query(sql, new Object[] { flightno },new BeanPropertyRowMapper<FocFlightDataEntity>(FocFlightDataEntity.class));
//		for (int i = 0; i < e.size(); i++) {
//			FocFlightDataEntity focFlightData = e.get(0);
//			if (null != focFlightData.getSst()) {
//				if (focFlightData.getSst().length() == 16) {
//					focFlightData.setSst(focFlightData.getSst()+":00");
//				}
//			}
//			if (null != focFlightData.getSat()) {
//				if (focFlightData.getSat().length() == 16) {
//					focFlightData.setSat(focFlightData.getSat()+":00");
//				}
//			}
//			if (null != focFlightData.getEat()) {
//				if (focFlightData.getEat().length() == 16) {
//					focFlightData.setEat(focFlightData.getEat()+":00");
//				}
//			}
//			if (null != focFlightData.getEst()) {
//				if (focFlightData.getEst().length() == 16) {
//					focFlightData.setEst(focFlightData.getEst()+":00");
//				}
//			}
//		}
		return e;
	}
	@Override
    public List<FamsUnsafeeventTiredamageEntity>  listApp(String searchInput, Integer pageApp, Integer pageSize,HttpServletRequest request) {
		return famsUnsafeeventTiredamageDao.queryListByEntity(searchInput, (pageApp - 1) * pageSize, pageSize);
    }
	
	@Override
 	public List<FamsUnsafeeventTiredamageEntity> getDateByDay(Integer day,HttpServletRequest request){
		if (day < 30) {
			return famsUnsafeeventTiredamageDao.queryDateByDay(day);
		}
		return famsUnsafeeventTiredamageDao.queryDateByMoon();
 	}
}