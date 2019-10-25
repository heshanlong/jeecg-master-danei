package com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.dao;

import java.util.List;

import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.minidao.annotation.MiniDao;

import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.FamsAttentionCraftsiteUserEntity;

@MiniDao
public class FamsAttentionCraftsiteUserDao extends CommonDao {


	public List<FamsAttentionCraftsiteUserEntity> queryListByEntity(String userName, String craftsiteSeria) {
		
		String sql = "SELECT *	FROM fams_attention_craftsite_user where ";
		if (!StringUtil.isEmpty(userName)) {
			sql += "user_name like '%"+userName+"%' AND ";
		}
		if (!StringUtil.isEmpty(craftsiteSeria)) {
			sql += "craftsite_seria like '%"+craftsiteSeria+"%' AND ";
		}
		sql += "1 = 1 ORDER BY create_date desc;";
		List<FamsAttentionCraftsiteUserEntity> list = this.getSession().createSQLQuery(sql).addEntity(FamsAttentionCraftsiteUserEntity.class).list();
		return  list;
		
	}

}
