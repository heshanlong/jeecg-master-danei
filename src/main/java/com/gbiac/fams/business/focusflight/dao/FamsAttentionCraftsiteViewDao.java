package com.gbiac.fams.business.focusflight.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import com.gbiac.fams.business.anounce.entity.CommonState;
import com.gbiac.fams.business.anounce.vo.GroupVO;
import com.gbiac.fams.business.focusflight.entity.FamsAttentionCraftsiteViewEntity;
import com.gbiac.fams.common.Util.Util;

@MiniDao
public class FamsAttentionCraftsiteViewDao extends CommonDao{

	public List<FamsAttentionCraftsiteViewEntity> queryListByEntity(String name,String serial,String flycode){
		  String sql = "SELECT user.id, user.create_by, user.create_date, user.create_name, user.update_name, user.update_by, user.update_date, user.serial_number," +
		    "user.sys_company_code, user.sys_org_code, user.user_id name,user.craftsite_seria serial," +
		    "GROUP_CONCAT(CONCAT(t2.AFSS_AWCD, t2.AFSS_FLNO) SEPARATOR',') flycode " +
		    "FROM fams_attention_craftsite_user AS user " +
		    "LEFT JOIN di_aomip_flight_stls_snapshot AS t1 ON t1.STLS_STND_CODE = user.craftsite_seria or t1.STLS_STND_CODE REGEXP REPLACE(craftsite_seria, ',', '|') " +
		    "LEFT JOIN di_aomip_flight_snapshot AS t2 ON t2.AFSS_FFID = t1.STLS_FFID where ";
		  if (!StringUtil.isEmpty(name)) {
		   sql += " user.user_id like '%"+name+"%' and ";
		  }
		  if (!StringUtil.isEmpty(serial)) {
		   sql += " user.craftsite_seria like '%"+serial+"%' and ";
		  }

		  sql += " t1.UPDATE_TIME between '2019-04-02 00:00:00' and '2019-04-03 23:59:59' GROUP BY USER.id ";
		  if (!StringUtil.isEmpty(flycode)) {
		   sql += " HAVING GROUP_CONCAT(CONCAT(t2.AFSS_AWCD, t2.AFSS_FLNO) SEPARATOR',') like '%"+flycode+"%' ";
		  }
		  sql += "order by USER .craftsite_seria desc;";
		  List<FamsAttentionCraftsiteViewEntity> list = this.getSession().createSQLQuery(sql).addEntity(FamsAttentionCraftsiteViewEntity.class).list();
		  return  list;

		  
		  
		  
		 }
	
	
	
	
	
	
	
	
	
	
	
	
//	@Sql("SELECT user.user_id user_id,user.craftsite_seria,GROUP_CONCAT(CONCAT(t2.AFSS_AWCD, t2.AFSS_FLNO) SEPARATOR',') flycode FROM fams_attention_craftsite_user AS user LEFT JOIN di_aomip_flight_stls_snapshot AS t1 ON t1.STLS_STND_CODE = user.craftsite_seria or t1.STLS_STND_CODE REGEXP REPLACE(craftsite_seria, ',', '|') LEFT JOIN di_aomip_flight_snapshot AS t2 ON t2.AFSS_FFID = t1.STLS_FFID where t1.UPDATE_TIME between '2019-04-02 00:00:00' and '2019-04-03 23:59:59' GROUP BY USER .id order by USER .craftsite_seria desc;")
//	List<FamsAttentionCraftsiteViewEntity> queryListByEntity(String name,String serial,String flycode) {
//		return null;
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
	
	
	

	
	
	
	
	


