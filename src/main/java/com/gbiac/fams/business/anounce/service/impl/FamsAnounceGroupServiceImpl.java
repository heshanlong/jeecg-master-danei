package com.gbiac.fams.business.anounce.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.anounce.dao.FamsAnounceGroupDAO;
import com.gbiac.fams.business.anounce.entity.CommonState;
import com.gbiac.fams.business.anounce.entity.FamsAnounceGroupEntity;
import com.gbiac.fams.business.anounce.service.FamsAnounceGroupServiceI;
import com.gbiac.fams.business.anounce.vo.GroupVO;
import com.gbiac.fams.common.Util.Util;
import com.sun.star.uno.Exception;
import com.sun.star.uno.RuntimeException;

/**
 * 分组维护的业务层
 * 
 * @author Mchen
 *
 */
@Service("famsAnounceGroupService")
@Transactional
public class FamsAnounceGroupServiceImpl extends CommonServiceImpl implements FamsAnounceGroupServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private FamsAnounceGroupDAO famsAnounceGroupDAO;

	public void delete(FamsAnounceGroupEntity entity) throws Exception {
		if (entity==null||StringUtils.isBlank(entity.getState())) {
			throw new RuntimeException("数据不存在");
		}
		//改用逻辑删除
		if (CommonState.DELETE_STATE.equals(entity.getState())) {
			entity.setState(CommonState.NORMAL_STATE);
		}else if(CommonState.NORMAL_STATE.equals(entity.getState())) {
			entity.setState(CommonState.DELETE_STATE);
		}else {
			throw new Exception("传入数据非法");
		}
		super.updateEntitie(entity);
	}

	/**
	 * 保存分组数据
	 * 
	 * @param name
	 * @param userIds
	 * @return
	 * @throws Exception
	 */
	private Integer save(String name, String userIds) throws Exception {
		// 检查数据是否为空，为空返回1;
		if (StringUtils.isBlank(name) || StringUtils.isBlank(userIds)) {
			return 1;
		}
		// 去除首尾空格
		name = name.trim();
		userIds = userIds.trim();

		String[] ids = userIds.trim().split(",");
		List<TSBaseUser> users = new ArrayList<>(ids.length);
		for (int i = 0; i < ids.length; i++) {
			TSBaseUser user = this.findUniqueByProperty(TSBaseUser.class, "id", ids[i]);
			if (user != null&&Globals.Delete_Normal==user.getDeleteFlag()) {
				users.add(user);
			}
		}
		// 判断是否有非法id
		if (ids.length == 0 || users.size() < ids.length) {
			return 1;
		}
		// 名称重复
		if (isRepeatName(name,ResourceUtil.getSessionUser().getUserName())) {
			return 2;
		}
		String groupId = UUID.randomUUID().toString().replaceAll("-", "");
		Date date = new Date();
		// 数据库添加数据
		famsAnounceGroupDAO.addGroupUsers(groupId, name, ids, ResourceUtil.getSessionUser().getUserName(), date);
		return 0;
	}

	/**
	 * 获取所有的分组信息
	 */
	@Override
	public List<GroupVO> selectAlls() throws Exception {
		return famsAnounceGroupDAO.list();
	}

	/**
	 * 通告id获取分组信息
	 */
	@Override
	public GroupVO selectById(String id) throws Exception {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		return famsAnounceGroupDAO.listById(id);
	}

	/**
	 * 保存或更新分组的信息
	 */
	@Override
	public Integer saveOrUpdate(String id, String name, String userIds) throws Exception {
		if (StringUtils.isBlank(id)) {
			return save(name, userIds);
		} else {
			return update(id, name, userIds);
		}

	}

	/**
	 * 更新分组
	 * 
	 * @param id
	 * @param name
	 * @param userIds
	 * @return
	 * @throws Exception
	 */
	private Integer update(String id, String name, String userIds) throws Exception {
		// 判断数据是否为空
		if (StringUtils.isBlank(name) || StringUtils.isBlank(userIds)) {
			return 1;
		}
		// 去除两边的空格
		name = name.trim();
		userIds = userIds.trim();
		id = id.trim();
		// 判断id是否正确
		GroupVO vo = selectById(id);
		if (vo == null) {
			return 1;
		}
		// 名称重复
		if (!name.equals(vo.getName()) && isRepeatName(name,ResourceUtil.getSessionUser().getUserName())) {
			return 2;
		}
		// 删除分组;
		famsAnounceGroupDAO.deleteById(id);
		// 添加分组;
		String[] ids = userIds.trim().split(",");
		List<TSBaseUser> users = new ArrayList<>(ids.length);
		for (int i = 0; i < ids.length; i++) {
			TSBaseUser user = this.findUniqueByProperty(TSBaseUser.class, "id", ids[i]);
			if (user != null&&Globals.Delete_Normal==user.getDeleteFlag()) {
				users.add(user);
			}
		}
		// 判断是否有非法id
		if (ids.length == 0 || users.size() < ids.length) {
			return 1;
		}
		// 数据库添加数据
		Date date = new Date();
		famsAnounceGroupDAO.addGroupUsers(id, name, ids, ResourceUtil.getSessionUser().getUserName(), date);
		return 0;
	}

	/**
	 * 根据分组数据包装分组显示数据
	 * 
	 * @param groupEntities
	 * @return GroupVO {@link com.gbiac.fams.business.anounce.vo.GroupVO}
	 */
	@Override
	public List<GroupVO> selectGroupVOBy(List<FamsAnounceGroupEntity> groupEntities) throws Exception {
		List<GroupVO> res = new ArrayList<>();
		if (groupEntities == null || groupEntities.isEmpty()) {
			return res;
		}
		for(FamsAnounceGroupEntity entity : groupEntities) {
			GroupVO vo = famsAnounceGroupDAO.listById(entity.getId());
			if ("-1".equals(vo.getId())) {
				vo.setId(entity.getId());
				vo.setCount(0);
				vo.setCreateBy(entity.getCreateBy());
				vo.setCreateDate(entity.getCreateDate());
				vo.setCreateName(entity.getCreateName());
				vo.setName(entity.getName());
				vo.setState(entity.getState());
				vo.setUpdateBy(entity.getUpdateBy());
				vo.setUpdateDate(entity.getUpdateDate());
				vo.setUser("无");
				vo.setUserIds("");
				
			}
			res.add(vo);
			
		}
		return res;
	}
	/**
	 * @param name 分组名称
	 * @param userName 用户名称
	 */
	@Override
	public boolean isRepeatName(String name, String userName) {
		return famsAnounceGroupDAO.containName(name,userName);
	}

}