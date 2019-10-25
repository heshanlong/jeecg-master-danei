package com.gbiac.fams.business.main.index.service.impl;

import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;
import com.gbiac.fams.business.construction.workapprove.dao.FamsWorkApproveDao;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveServiceI;
import com.gbiac.fams.business.main.index.entity.IndexCakeDto;
import com.gbiac.fams.business.main.index.service.IndexServiceI;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("indexService")
@Transactional
public class IndexServiceImpl extends CommonServiceImpl implements IndexServiceI {
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsWorkApproveServiceI famsWorkApproveService;
	@Autowired
	private FamsWorkApproveDao famsWorkApproveDao;
 	public void delete(FamsUnsafeeventAircraftleakageEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsUnsafeeventAircraftleakageEntity entity, Map map) throws Exception{
 		entity.setNo(systemService.getFamsNumberByTSTypegroup("漏油",FamsAirportrunwaySurfacecleanEntity.class,"createDate"));
 		Serializable t = super.save(entity);
 		String files= (String) map.get("files");
 		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsUnsafeeventAircraftleakageEntity entity, Map map) throws Exception{
 		super.saveOrUpdate(entity);
 		String files= (String) map.get("files");
		if(files!=null){
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"work",entity.getId(), MessageConstant.STATEBEFORE);
		}
 	}

	@Override
	public List getWorkInfo() {
 		List result=new ArrayList();
 		//查询是否拥有施工申请权限
		List<Map> applyFunctions = famsWorkApproveDao.getApplyFunctions(Util.getPcOrAppUserId());
		boolean canApply=applyFunctions.size()>0?true:false;
		//查询是否拥有施工申请权限
		List<Map> approveFunctions = famsWorkApproveDao.getApproveFunctions(Util.getPcOrAppUserId());
		boolean canApprove=approveFunctions.size()>0?true:false;
		if(canApply){//施工单位
			Map map=new HashMap();
			List list=famsWorkApproveService.getSWorkInfo();
			map.put("name","apply");
			map.put("value",list);
			result.add(map);
		}
		if(canApprove){//审批单位
			Map map=new HashMap();
			List list=famsWorkApproveService.getYWorkInfo();
			map.put("name","approve");
			map.put("value",list);
			result.add(map);
		}
		 return result;
	}

	@Override
	public Map getAirportrunway()throws Exception{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		List<IndexCakeDto> ictList = new ArrayList<IndexCakeDto>();
		Map msgReturn=new HashMap();
		
		String[] arrayStr = new String[]{"fams_airportrunway_clean","fams_airportrunway_surfaceclean","fams_airportrunway_repair","fams_airportrunway_mark"};
		for (int i = 0; i < arrayStr.length; i++) {
			Session session = systemService.getSession();
			String str = arrayStr[i];
			String sql = "SELECT * FROM " + str + " where DATE_FORMAT(create_date,'%Y-%m-%d') = DATE_FORMAT(sysdate(),'%Y-%m-%d')" ;
			SQLQuery query = session.createSQLQuery(sql);
//			query.addEntity(FamsAirportrunwayCleanEntity.class);
			msgReturn.put(str, query.list().size());
		}
		return msgReturn;
	}

}