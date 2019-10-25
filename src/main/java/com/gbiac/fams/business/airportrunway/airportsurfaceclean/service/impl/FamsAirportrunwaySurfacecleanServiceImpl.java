package com.gbiac.fams.business.airportrunway.airportsurfaceclean.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gbiac.fams.business.airportrunway.airportclean.entity.FamsAirportrunwayCleanEntity;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.service.FamsAirportrunwaySurfacecleanServiceI;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;

import com.gbiac.fams.business.airportrunway.airportsurfaceclean.dao.FamsAirportrunwaySurfacecleanDaoI;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.dao.FamsUnsafeeventTiredamageDaoI;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.constant.MessageConstant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;

@Service("famsAirportrunwaySurfacecleanService")
@Transactional
public class FamsAirportrunwaySurfacecleanServiceImpl extends CommonServiceImpl implements FamsAirportrunwaySurfacecleanServiceI {
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsAirportrunwaySurfacecleanDaoI famsAirportrunwaySurfacecleanDao;
	@Autowired
	JdbcTemplate jdbcTemplate;
 	public void delete(FamsAirportrunwaySurfacecleanEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAirportrunwaySurfacecleanEntity entity, Map map) throws Exception{
 		entity.setNo(systemService.getFamsNumberByTSTypegroup("道面清扫",FamsAirportrunwaySurfacecleanEntity.class,"createDate"));

 		Serializable t = super.save(entity);
 		String files= (String) map.get("files");
		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAirportrunwaySurfacecleanEntity entity, Map map) throws Exception{
 		super.saveOrUpdate(entity);
 		String files= (String) map.get("files");
		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 	}
 	
 	@Override
    public List<FamsAirportrunwaySurfacecleanEntity>  listApp(String searchInput, Integer pageApp, Integer pageSize,HttpServletRequest request) {
// 		TSUser user = ResourceUtil.getSessionUser();
//        String sql = "SELECT * FROM  fams_airportrunway_surfaceclean  where 1 = 1 " ;
//		if (null != searchInput && searchInput.length() > 0) {
//			sql += " and (";
//			sql += " no like '%" + searchInput + "%' ";
//			sql += " or CLEAN_WHY like '%" + searchInput + "%' ";
//			sql += " or CLEAN_EQUIPMENT like '%" + searchInput + "%' ";
//			sql += " or CLEAN_LOCATION like '%" + searchInput + "%' ";
//			sql += " or CLEAN_AREA like '%" + searchInput + "%' ";
//			sql += " or PEOPLE like '%" + searchInput + "%' ";
////			sql += " or AREA like '%" + searchInput + "%' ";
//			sql += ")";
//		}
//		sql += " order BY create_date DESC limit " + ((pageApp - 1) * pageSize) + "," + pageSize;
//		Session session = systemService.getSession();
//		SQLQuery query = session.createSQLQuery(sql);
//		query.addEntity(FamsAirportrunwaySurfacecleanEntity.class);
//        return query.list();
 		return famsAirportrunwaySurfacecleanDao.queryListByEntity(searchInput, (pageApp - 1) * pageSize, pageSize);
    }
}