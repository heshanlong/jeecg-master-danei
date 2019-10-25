package com.gbiac.fams.business.airportrunway.repair.service.impl;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;
import com.gbiac.fams.business.airportrunway.repair.service.FamsAirportrunwayRepairServiceI;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;

import com.gbiac.fams.business.airportrunway.repair.dao.FamsAirportrunwayRepairDaoI;
import com.gbiac.fams.business.airportrunway.repair.entity.FamsAirportrunwayRepairEntity;
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

@Service("famsAirportrunwayRepairService")
@Transactional
public class FamsAirportrunwayRepairServiceImpl extends CommonServiceImpl implements FamsAirportrunwayRepairServiceI {
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsAirportrunwayRepairDaoI famsAirportrunwayRepairDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
 	public void delete(FamsAirportrunwayRepairEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAirportrunwayRepairEntity entity,Map map) throws Exception{
 		entity.setNo(systemService.getFamsNumberByTSTypegroup("道面修补",FamsAirportrunwayRepairEntity.class,"createDate"));
 		Serializable t = super.save(entity);
 		String files= (String) map.get("files");
 		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAirportrunwayRepairEntity entity,Map map) throws Exception{
 		super.saveOrUpdate(entity);
 		String files= (String) map.get("files");
 		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 	}
 	@Override
    public List<FamsAirportrunwayRepairEntity>  listApp(String searchInput, Integer pageApp, Integer pageSize,HttpServletRequest request) {
// 		TSUser user = ResourceUtil.getSessionUser();
//        String sql = "SELECT * FROM  fams_airportrunway_repair  where 1 = 1 " ;
//		if (null != searchInput && searchInput.length() > 0) {
//			sql += " and (";
//			sql += " no like '%" + searchInput + "%' ";
//			sql += " or DAMAGE_TYPE like '%" + searchInput + "%' ";
//			sql += " or DAMAGE_LOCATION like '%" + searchInput + "%' ";
//			sql += " or MAINTENANCE_PLAN like '%" + searchInput + "%' ";
//			sql += " or PEOPLE like '%" + searchInput + "%' ";
//			sql += " or REPAIR_AREA like '%" + searchInput + "%' ";
//			sql += " or IRRIGATION_SEAM_LENGTH like '%" + searchInput + "%' ";
//			sql += ")";
//		}
//		sql += " order BY create_date DESC limit " + ((pageApp - 1) * pageSize) + "," + pageSize;
//		Session session = systemService.getSession();
//		SQLQuery query = session.createSQLQuery(sql);
//		query.addEntity(FamsAirportrunwayRepairEntity.class);
//        return  query.list();
 		return famsAirportrunwayRepairDao.queryListByEntity(searchInput, (pageApp - 1) * pageSize, pageSize);
    }
}