package com.gbiac.fams.business.airportrunway.airportclean.service.impl;
import com.gbiac.fams.business.airportrunway.airportclean.service.FamsAirportrunwayCleanServiceI;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;

import com.gbiac.fams.business.airportrunway.airportclean.dao.FamsAirportrunwayCleanDaoI;
import com.gbiac.fams.business.airportrunway.airportclean.entity.FamsAirportrunwayCleanEntity;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;
import com.gbiac.fams.business.construction.worksafe.entity.FamsWorkSafePersonEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.dao.FamsUnsafeeventTiredamageDaoI;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.constant.MessageConstant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("famsAirportrunwayCleanService")
@Transactional
public class FamsAirportrunwayCleanServiceImpl extends CommonServiceImpl implements FamsAirportrunwayCleanServiceI {
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsAirportrunwayCleanDaoI famsAirportrunwayCleanDao;
	@Autowired
	private SystemService systemService;
 	public void delete(FamsAirportrunwayCleanEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAirportrunwayCleanEntity entity, Map map) throws Exception{
 		entity.setNo(systemService.getFamsNumberByTSTypegroup("跑道除胶",FamsAirportrunwayCleanEntity.class,"createDate"));
 		Serializable t = super.save(entity);
 		String files= (String) map.get("files");
 		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAirportrunwayCleanEntity entity, Map map) throws Exception{
 		super.saveOrUpdate(entity);
		String files= (String) map.get("files");
		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 	}
 	@Override
    public List<FamsAirportrunwayCleanEntity>  listApp(String searchInput, Integer pageApp, Integer pageSize,HttpServletRequest request) {
// 		TSUser user = ResourceUtil.getSessionUser();
//        String sql = "SELECT * FROM  fams_airportrunway_clean  where 1 = 1 " ;
//		if (null != searchInput && searchInput.length() > 0) {
//			sql += " and (";
//			sql += " no like '%" + searchInput + "%' ";
//			sql += " or CLEAN_RUNWAY like '%" + searchInput + "%' ";
//			sql += " or CLEAN_LOCATION like '%" + searchInput + "%' ";
//			sql += " or CLEAN_AREA like '%" + searchInput + "%' ";
//			sql += " or PEOPLE like '%" + searchInput + "%' ";
//			sql += " or AREA like '%" + searchInput + "%' ";
//			sql += ")";
//		}
//		sql += " order BY create_date DESC limit " + ((pageApp - 1) * pageSize) + "," + pageSize;
//		Session session = systemService.getSession();
//		SQLQuery query = session.createSQLQuery(sql);
//		query.addEntity(FamsAirportrunwayCleanEntity.class);
//        return query.list();
 		return famsAirportrunwayCleanDao.queryListByEntity(searchInput, (pageApp - 1) * pageSize, pageSize);
    }
}