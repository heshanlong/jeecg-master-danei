package com.gbiac.fams.business.airportrunway.mark.service.impl;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;
import com.gbiac.fams.business.airportrunway.mark.service.FamsAirportrunwayMarkServiceI;

import net.sf.json.JSONArray;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;

import com.gbiac.fams.business.airportrunway.mark.dao.FamsAirportrunwayMarkDaoI;
import com.gbiac.fams.business.airportrunway.mark.entity.FamsAirportrunwayMarkEntity;
import com.gbiac.fams.business.anounce.entity.NotifyContant;
import com.gbiac.fams.business.unsafeevent.tiredamage.dao.FamsUnsafeeventTiredamageDaoI;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.constant.MessageConstant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("famsAirportrunwayMarkService")
@Transactional
public class FamsAirportrunwayMarkServiceImpl extends CommonServiceImpl implements FamsAirportrunwayMarkServiceI {
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsAirportrunwayMarkDaoI famsAirportrunwayMarkDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
 	public void delete(FamsAirportrunwayMarkEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAirportrunwayMarkEntity entity,Map map) throws Exception{
 		entity.setNo(systemService.getFamsNumberByTSTypegroup("标志线维护",FamsAirportrunwayMarkEntity.class,"createDate"));
 		
 		Serializable t = super.save(entity);
 		String files= (String) map.get("files");
 		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAirportrunwayMarkEntity entity,Map map) throws Exception{
 		super.saveOrUpdate(entity);
 		String files= (String) map.get("files");
 		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 	}
 	
 	@Override
    public List<FamsAirportrunwayMarkEntity>  listApp(String searchInput, Integer pageApp, Integer pageSize,HttpServletRequest request) {
// 		TSUser user = ResourceUtil.getSessionUser();
//        String sql = "SELECT * FROM  fams_airportrunway_mark  where 1 = 1 " ;
//		if (null != searchInput && searchInput.length() > 0) {
//			sql += " and (";
//			sql += " no like '%" + searchInput + "%' ";
//			sql += " or RUNWAY_LOGO like '%" + searchInput + "%' ";
//			sql += " or LOCATION like '%" + searchInput + "%' ";
//			sql += " or TAXIWAY_MARK like '%" + searchInput + "%' ";
//			sql += " or PEOPLE like '%" + searchInput + "%' ";
//			sql += " or AREA like '%" + searchInput + "%' ";
//			sql += ")";
//		}
//		sql += " order BY create_date DESC limit " + ((pageApp - 1) * pageSize) + "," + pageSize;
//		Session session = systemService.getSession();
//		SQLQuery query = session.createSQLQuery(sql);
//		query.addEntity(FamsAirportrunwayMarkEntity.class);
//        return query.list();
 		return famsAirportrunwayMarkDao.queryListByEntity(searchInput, (pageApp - 1) * pageSize, pageSize);
    }
}