package com.gbiac.fams.business.anounce.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.springframework.stereotype.Repository;

import com.gbiac.fams.business.anounce.entity.CommonState;
import com.gbiac.fams.business.anounce.vo.GroupVO;
import com.gbiac.fams.common.Util.Util;

/**
 * 处理分组维护的数据层
 * 
 * @author Mchen
 *
 */
@Repository
public class FamsAnounceGroupDAO extends CommonDao {

	/**
	 * 一次性添加分组附表
	 * @param ids
	 * @param groupId
	 * @param userId
	 * @param userName
	 * @param date
	 */
	public void addGroupUsers(String groupId,String name,String[] userIds,String userName,Date date) {
		String  gSql = "insert into fams_anounce_group (id,name,create_name,create_date,create_by,update_date,update_by,state) values (?,?,?,?,?,?,?,?)";
		Query query = this.getSession().createSQLQuery(gSql);
		query.setString(0, groupId);
		query.setString(1, name);
		query.setString(2, Util.getUserName());
		query.setDate(3, date);
		query.setString(4, userName);
		query.setDate(5, date);
		query.setString(6, userName);
		query.setString(7, CommonState.NORMAL_STATE);
		query.executeUpdate();
		String  sql = "insert into fams_anounce_group_user (id,group_id,user_id,create_by,create_date,update_by,update_date) values ";
		StringBuilder sqls = new StringBuilder(userIds.length*40);
		sqls.append(sql);
		for(String userId:userIds) {
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			sqls.append("('").append(id).append("','").append(groupId).append("','").append(userId).append("','").append(userName).append("','").append(dateText(date)).append("','").append(userName).append("','").append(dateText(date)).append("'),");
		}
		sql = sqls.substring(0, sqls.length()-1);
		query = this.getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	/**
	 * 判断数据库是否有相同的名称
	 * @param name
	 * @return
	 */
	public boolean containName(String name,String userName) {
		String sql = "SELECT count(id) FROM fams_anounce_group WHERE name = ? and create_by = ?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setString(0, name);
		query.setString(1, userName);
		String cnt = query.list().get(0).toString();
		int count = Integer.valueOf(cnt);
		if (count==0) {
			return false;
		}
		return true;
	}
	/**
	 * 获取所有的分组列表
	 * @return
	 */
	public List<GroupVO> list(){
		List<GroupVO> lists = new ArrayList<>();
		String sql = "SELECT g.id,g.name,b.realname,g.create_name,g.create_date,g.create_by,g.update_date,g.update_by,b.id as bid, g.state as state from fams_anounce_group g,fams_anounce_group_user u,t_s_base_user b where g.id = u.group_id and u.user_id = b.ID order by g.id";
		Query query = this.getSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		List<String> names = new ArrayList<String>();
		List<String> userIds = new ArrayList<String>();
		String id = "-1";
		String name="";
		String realName="";
		String createName = "";
		Date createDate = null;
		String createBy = "";
		Date updateDate = null;
		String updateBy = "";
		String state = "";
		boolean first = true;
		for(Object[] obj:list) {
			if(!id.equals((String)obj[0])){
				if (!first) {
					GroupVO groupVO = new GroupVO();
					groupVO.setId(id);
					groupVO.setName(name);
					groupVO.setCount(names.size());
					groupVO.setUser(String.join(",", names));
					groupVO.setUserIds(String.join(",", userIds));
					groupVO.setCreateName(createName);
					groupVO.setCreateDate(createDate);
					groupVO.setCreateBy(createBy);
					groupVO.setUpdateDate(updateDate);
					groupVO.setUpdateBy(updateBy);
					groupVO.setState(state);
					lists.add(groupVO);
				}else {
					first = false;
				}
				names.clear();
				userIds.clear();
				id = (String) obj[0];
				name = (String) obj[1];
				names.add((String) obj[2]);
				userIds.add((String) obj[8]);
				createName = (String) obj[3];
				createDate = (Date) obj[4];
				createBy = (String) obj[5];
				updateDate = (Date) obj[6];
				updateBy = (String) obj[7];
				state = (String) obj[9];
			}else {
				names.add((String) obj[2]);
				userIds.add((String) obj[8]);
			}
		}
		//最后的数据
		GroupVO groupVO = new GroupVO();
		groupVO.setId(id);
		groupVO.setName(name);
		groupVO.setCount(names.size());
		groupVO.setUser(String.join(",", names));
		groupVO.setUserIds(String.join(",", userIds));
		groupVO.setCreateName(createName);
		groupVO.setCreateDate(createDate);
		groupVO.setCreateBy(createBy);
		groupVO.setUpdateDate(updateDate);
		groupVO.setUpdateBy(updateBy);
		groupVO.setState(state);
		lists.add(groupVO);
		return lists;
	}
	/**
	 * 通过id获取分组信息
	 * @param ids
	 * @return
	 */
	public GroupVO listById(String gId) {
		//与上面方法代码重复高，如有需要进行优化
		String sql = "SELECT g.id,g.name,b.realname,g.create_name,g.create_date,g.create_by,g.update_date,g.update_by,b.id as bid ,g.state as state from fams_anounce_group g,fams_anounce_group_user u,t_s_base_user b where g.id = u.group_id and u.user_id = b.ID and g.id=? ";
		Query query = this.getSession().createSQLQuery(sql);
		query.setString(0, gId);
		List<Object[]> list = query.list();
		List<String> names = new ArrayList<String>();
		List<String> userIds = new ArrayList<String>();		
		String id = "-1";
		String name="";
		String realName="";
		String createName = "";
		Date createDate = null;
		String createBy = "";
		Date updateDate = null;
		String updateBy = "";
		String state = "";
		boolean first = true;
		for(Object[] obj:list) {
			if(!id.equals((String)obj[0])){
				id = (String) obj[0];
				name = (String) obj[1];
				names.add((String) obj[2]);
				createName = (String) obj[3];
				createDate = (Date) obj[4];
				createBy = (String) obj[5];
				updateDate = (Date) obj[6];
				updateBy = (String) obj[7];
				userIds.add((String)obj[8]);
				state = (String)obj[9];
			}else {
				names.add((String) obj[2]);
				userIds.add((String)obj[8]);
			}
		}
		//最后的数据
		GroupVO groupVO = new GroupVO();
		groupVO.setId(id);
		groupVO.setName(name);
		groupVO.setCount(names.size());
		groupVO.setUser(String.join(",", names));
		groupVO.setUserIds(String.join(",", userIds));
		groupVO.setCreateName(createName);
		groupVO.setCreateDate(createDate);
		groupVO.setCreateBy(createBy);
		groupVO.setUpdateDate(updateDate);
		groupVO.setUpdateBy(updateBy);
		groupVO.setState(state);
		return groupVO;
	}
	/**
	 * 根据id删除数据
	 * @param id
	 */
	public void deleteById(String id) {
		String sql = "delete from fams_anounce_group  where id = ?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setString(0, id);
		query.executeUpdate();
	}
	/**
	 *日期转字符串
	 * @param date
	 * @return
	 */
	public String dateText(Date date) {
		//SimpleDateFormat 线程不安全
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
}
