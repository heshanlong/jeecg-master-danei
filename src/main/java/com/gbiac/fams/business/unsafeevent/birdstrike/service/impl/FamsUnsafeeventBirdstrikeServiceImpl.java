package com.gbiac.fams.business.unsafeevent.birdstrike.service.impl;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.dao.FamsUnsafeeventAircraftleakageDaoI;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;
import com.gbiac.fams.business.unsafeevent.birdstrike.service.FamsUnsafeeventBirdstrikeServiceI;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;

import com.gbiac.fams.business.unsafeevent.birdstrike.dao.FamsUnsafeeventBirdstrikeDaoI;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;
import com.gbiac.fams.business.unsafeevent.updatetime.entity.FamsUpdatetimeEntity;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.constant.MessageConstant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("famsUnsafeeventBirdstrikeService")
@Transactional
public class FamsUnsafeeventBirdstrikeServiceImpl extends CommonServiceImpl implements FamsUnsafeeventBirdstrikeServiceI {
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsUnsafeeventBirdstrikeDaoI famsUnsafeeventBirdstrikeDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
 	public void delete(FamsUnsafeeventBirdstrikeEntity entity) throws Exception{
// 		super.delete(entity);
 		entity.setDel("1");
 		super.saveOrUpdate(entity);
 	}
 	
 	public Serializable save(FamsUnsafeeventBirdstrikeEntity entity, Map map) throws Exception{
 		entity.setNo(systemService.getFamsNumberByTSTypegroup("鸟击",FamsUnsafeeventBirdstrikeEntity.class,"createDate"));
 		Serializable t = super.save(entity);
 		String files= (String) map.get("files");
 		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsUnsafeeventBirdstrikeEntity entity, Map map,String str) throws Exception{
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
 	 		List<TSBaseUser> tSBaseUserList = systemService.findByProperty(TSBaseUser.class, "userName", entity.getUpdateBy());
 	 		if (tSBaseUserList.size() > 0) {
 	 			ut.setPeople(tSBaseUserList.get(0).getRealName());
			}
 	 		ut.setModule("birdstrike");
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
    public List<FamsUnsafeeventBirdstrikeEntity> listApp(String searchInput, Integer pageApp, Integer pageSize,HttpServletRequest request) {
		return famsUnsafeeventBirdstrikeDao.queryListByEntity(searchInput, (pageApp - 1) * pageSize, pageSize);
    }
 	
 	@Override
 	public List<FamsUnsafeeventBirdstrikeEntity> getDateByDay(Integer day,HttpServletRequest request){
 		if (day < 30) {
			return famsUnsafeeventBirdstrikeDao.queryDateByDay(day);
		}
		return famsUnsafeeventBirdstrikeDao.queryDateByMoon();
 	}
}