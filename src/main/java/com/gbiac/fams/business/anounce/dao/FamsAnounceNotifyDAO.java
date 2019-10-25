package com.gbiac.fams.business.anounce.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.springframework.stereotype.Repository;

import com.gbiac.fams.business.anounce.entity.CommonState;
import com.gbiac.fams.business.anounce.entity.FamsAnounceReceive;
import com.gbiac.fams.business.anounce.entity.NotifyContant;

/**
 * 通知通告的数据访问层
 * 
 * @author Mchen
 *
 */
@Repository
public class FamsAnounceNotifyDAO extends CommonDao {
	/**
	 * 判断数据库是否有这个编号
	 * 
	 * @param number
	 * @return
	 */
	public boolean containNumber(String number) {
		String sql = "select id from fams_announce_notify where number = ?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setString(0, number);
		List<Object[]> list = query.list();
		return !list.isEmpty();
	}

	/**
	 * 获取用户的未读通告数量
	 */
	public Integer countNoReadAnnounce(String userId) {
		String sql = "SELECT r.id from fams_anounce_receive r INNER JOIN fams_announce_notify n on r.notify_id = n.id where r.state=? AND r.user_id = ? and n.state = ?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setString(0, NotifyContant.ANOUNCE_NO_READ);
		query.setString(1, userId);
		query.setString(2, CommonState.NORMAL_STATE);
		return query.list().size();
	}

	/**
	 * 获取用户的接收的通告
	 */
	public List<FamsAnounceReceive> getNoReadAnnounceByUserId(String userId, int offSet, int limit) {
		StringBuilder sql = new StringBuilder(30);
		sql.append("SELECT id, notify_id, user_id, state, read_time, create_by  from fams_anounce_receive where  user_id = ?  ");
		sql.append("order by state, create_date desc limit ?,?");
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setString(0, userId);
		query.setInteger(1, offSet);
		query.setInteger(2, limit);
		List<Object[]> lists = query.list();
		List<FamsAnounceReceive> recs = new ArrayList<FamsAnounceReceive>();
		for (Object[] obj : lists) {
			FamsAnounceReceive rc = new FamsAnounceReceive();
			rc.setId((String) obj[0]);
			rc.setNotifyId((String) obj[1]);
			rc.setUserId((String) obj[2]);
			rc.setState((String) obj[3]);
			rc.setReadTime((Date) obj[4]);
			rc.setCreateBy((String) obj[5]);
			recs.add(rc);
		}
		return recs;
	}
	
	
	
	/**
	 * 获取用户的接收的通告
	 * @param userId 用户Id
	 * @param offset 偏移量
	 * @param limit 返回数据量
	 * @param searchInfo 搜索信息(标题或者发送人)
	 */
	public List<Map<String, Object>> getAnnounceByUserIdAndSearchInfo(String userId, int offSet, int limit,String searchInfo) {
		StringBuilder sql = new StringBuilder(30);
		sql.append("SELECT n.id,n.title,n.sender,n.send_time,r.state FROM fams_announce_notify n,fams_anounce_receive r where r.notify_id = n.id AND r.user_id =:userId AND n.state = 1 ");
		if (!StringUtils.isBlank(searchInfo)) {
			sql.append(" and (n.title LIKE \"%"+searchInfo+"%\" OR n.sender LIKE \"%"+searchInfo+"%\") ");
		}
		sql.append(" order by r.state desc, n.send_time desc limit :offSet,:limit");
		Query query = this.getSession().createSQLQuery(sql.toString());
		query.setString("userId", userId);
		query.setInteger("offSet", offSet);
		query.setInteger("limit", limit);
		/*if (!StringUtils.isBlank(searchInfo)) {
			query.setString(0, searchInfo);
			query.setString(1, searchInfo);
		}*/
		List<Object[]> lists = query.list();
		List<Map<String, Object>> recs = new ArrayList<>();
		for (Object[] obj : lists) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", obj[0]);
			map.put("title", obj[1]);
			map.put("signer", obj[2]);
			map.put("issuTime", obj[3]);
			map.put("readState", obj[4]);
			recs.add(map);
		}
		return recs;
	}

	/**
	 * 统计通告接收人的数量
	 * 
	 * @param notifyId
	 * @return
	 */
	public Integer countAnnounceByNotifyId(String notifyId) {
		String sql = "SELECT id from fams_anounce_receive where notify_id=?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setString(0, notifyId);
		return query.list().size();

	}

	/**
	 * 统计通告已读数量
	 * 
	 * @param notifyId
	 * @return
	 */
	public Integer countReadAnnounceByNotifyId(String notifyId) {
		String sql = "SELECT id from fams_anounce_receive where notify_id=? and state = ?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setString(0, notifyId);
		query.setString(1, NotifyContant.ANOUNCE_READ);
		return query.list().size();
	}

	/**
	 * 根据通告id和用户id返回通告对象
	 * 
	 * @param notifyId
	 * @param userId
	 * @return
	 */
	public FamsAnounceReceive getFamsAnounceReceiveByNotifyIdAndUserId(String notifyId, String userId) {
		String sql = "SELECT id, notify_id, user_id, state, read_time, create_by, create_date, update_by, update_date from fams_anounce_receive where  user_id = ? and notify_id=?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setString(0, userId);
		query.setString(1, notifyId);
		List<Object[]> lists = query.list();
		if (lists.isEmpty()) {
			return null;
		}
		Object[] obj = lists.get(0);
		FamsAnounceReceive rc = new FamsAnounceReceive();
		rc.setId((String) obj[0]);
		rc.setNotifyId((String) obj[1]);
		rc.setUserId((String) obj[2]);
		rc.setState((String) obj[3]);
		rc.setReadTime((Date) obj[4]);
		rc.setCreateBy((String) obj[5]);
		rc.setCreateDate((Date) obj[6]);
		rc.setUpdateBy((String) obj[7]);
		rc.setUpdateDate((Date) obj[8]);
		return rc;
	}

}
