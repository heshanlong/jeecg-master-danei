package com.gbiac.fams.business.anounce.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.anounce.dao.FamsAnounceNotifyDAO;
import com.gbiac.fams.business.anounce.entity.CommonState;
import com.gbiac.fams.business.anounce.entity.FamsAnnounceNotifyEntity;
import com.gbiac.fams.business.anounce.entity.FamsAnounceGroupUser;
import com.gbiac.fams.business.anounce.entity.FamsAnounceReceive;
import com.gbiac.fams.business.anounce.entity.NotifyContant;
import com.gbiac.fams.business.anounce.enums.NotifyStatus;
import com.gbiac.fams.business.anounce.enums.NotifyType;
import com.gbiac.fams.business.anounce.service.FamsAnnounceNotifyServiceI;
import com.gbiac.fams.business.anounce.vo.ReceiveVO;
import com.gbiac.fams.business.anounce.vo.ReplyInfoVO;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.MessageService;
import com.gbiac.fams.common.Entity.MessageType;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;

/**
 * 通知通告的业务层
 * 
 * @author Mchen
 *
 */
@Service("famsAnnounceNotifyService")
@Transactional
public class FamsAnnounceNotifyServiceImpl extends CommonServiceImpl implements FamsAnnounceNotifyServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsAnounceNotifyDAO famsAnounceNotifyDAO;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FamsCommonService famsCommonService;

	public void delete(FamsAnnounceNotifyEntity entity, String reason) throws Exception {

		/**
		 * 撤回逻辑
		 */
		List<TSBaseUser> users = new ArrayList<>();

		// 查询通知通告发送的接收信息
		List<FamsAnounceReceive> recs = famsCommonService.findByProperty(FamsAnounceReceive.class, "notifyId",
				entity.getId());

		// 获取用户id
		for (FamsAnounceReceive re : recs) {
			users.add(famsCommonService.getEntity(TSBaseUser.class, re.getUserId()));
		}
		// 删除接收表中的数据
//		famsCommonService.deleteAllEntitie(recs);
		// 更新通知通告的状态为撤销状态
		entity.setState(NotifyContant.WITHDRAW_STATE);
		famsCommonService.updateEntitie(entity);
		// 为撤回消息推送信息
		// 推送消息
		messageService.pushMessage(
				NotifyContant.MODULE_NAME, entity.getId(), "“" + Util.getUserDepName() + "”-“" + Util.getUserName()
						+ "”撤回了“" + noticeTypeConverse(entity.getType()) + "”",
				reason, entity.getSender(), users, null, null);

	}

	/**
	 * 保存通告
	 * 
	 * @param entity
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	private Integer save(FamsAnnounceNotifyEntity entity, String fileId) throws Exception {
		// 生成id
		String id = UUID.randomUUID().toString().replace("-", "");
		String uIds = "";
		if (NotifyContant.SEND_PATTERN_CURRENT.equals(entity.getSendPattern())) {
			uIds = entity.getGroupId();
			entity.setGroupId(NotifyContant.SEND_PATTERN_CURRENT);
		}
		entity.setId(id);
		// 放置并发获取number错误
		synchronized (this) {
			entity.setNumber(systemService.getFamsNumberByTSTypegroup(NotifyContant.ANOUNCE_DATA_PRE,
					FamsAnnounceNotifyEntity.class, "sendTime"));
		}

		super.save(entity);
		pushAndupdate(entity, fileId, uIds);
		return 0;
	}

	/**
	 * 更新通告
	 * 
	 * @param entity
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	private Integer update(FamsAnnounceNotifyEntity entity, String fileId) throws Exception {
		String uIds = "";
		if (NotifyContant.SEND_PATTERN_CURRENT.equals(entity.getSendPattern())) {
			uIds = entity.getGroupId();
			entity.setGroupId(NotifyContant.SEND_PATTERN_CURRENT);
		}
		super.getSession().clear();
		super.updateEntitie(entity);
		// 删除接收人的消息 由于表管理删除子表数据会更新主表的字段
		List<FamsAnounceReceive> lists = super.findByProperty(FamsAnounceReceive.class, "notifyId", entity.getId());
		super.deleteAllEntitie(lists);
		pushAndupdate(entity, fileId, uIds);
		return 0;
	}

	/**
	 * 推送和接收信息更新
	 * 
	 * @param entity
	 * @param fileId 保存的文件id
	 * @param uIds   发送模式是选择人员的时候的用户id
	 * @throws java.lang.Exception
	 */
	private void pushAndupdate(FamsAnnounceNotifyEntity entity, String fileId, String uIds) throws java.lang.Exception {
		List<String> userIds = new ArrayList<String>();
		// 根据发送模式推送信息 switch编译后效率高于ifelse
		switch (entity.getSendPattern()) {
		case NotifyContant.SEND_PATTERN_ALL:
			// 推送给所有人
			List<TSBaseUser> users = super.findByProperty(TSBaseUser.class, "deleteFlag", Globals.Delete_Normal);
			for (TSBaseUser user : users) {
				userIds.add(user.getId());
			}
			break;
		case NotifyContant.SEND_PATTERN_INTERNAL:
			// 推送给飞管内部的人
			List<TSBaseUser> userList = getAllUsersOfDepart();
			for (TSBaseUser user : userList) {
				userIds.add(user.getId());
			}
			break;
		case NotifyContant.SEND_PATTERN_CURRENT:
			// 推送给选择的人员
			String[] userId = uIds.split(",");
			for (String uId : userId) {
				userIds.add(uId);
			}
			break;
		case NotifyContant.SEND_PATTERN_CUSTOM:
			// 根据分组推送
			if (entity.getGroupId() != null && !NotifyContant.SEND_PATTERN_CUSTOM.equals(entity.getGroupId())) {
				List<FamsAnounceGroupUser> list = super.findByProperty(FamsAnounceGroupUser.class, "groupId",
						entity.getGroupId());
				for (FamsAnounceGroupUser fid : list) {
					userIds.add(fid.getUserId());
				}
			}
		default:
			break;
		}
		// 填写人员接收表和保存推送信息 建议一个sql,防止多次连接降低效率
		List<TSBaseUser> receivers = new ArrayList<>();
		for (String uid : userIds) {
			TSBaseUser user = super.getEntity(TSBaseUser.class, uid);
			receivers.add(user);
			if (user != null) {
				FamsAnounceReceive receive = new FamsAnounceReceive();
				receive.setId(UUID.randomUUID().toString().replace("-", ""));
				receive.setNotifyId(entity.getId());
				receive.setUserId(uid);
				receive.setState(NotifyContant.ANOUNCE_NO_READ);
				super.save(receive);
			}

		}

		// 更新文件表的bussinessid字段
		famsCommonService.updateMBSofFileByStr(fileId, NotifyContant.MODULE_NAME, entity.getId(),
				CommonState.NORMAL_STATE);
		// 推送消息
		messageService.pushMessage(NotifyContant.MODULE_NAME, entity.getId(),
				"“" + Util.getUserDepName() + "”-“" + Util.getUserName() + "”给您发送了“"
						+ noticeTypeConverse(entity.getType()) + "”",
				entity.getContent(), entity.getSender(), receivers, null, null);
	}

	/**
	 * 保存或者更新通告
	 */
	public Integer saveOrUpdate(FamsAnnounceNotifyEntity entity, String fileId) throws Exception {
		if (StringUtils.isBlank(entity.getId())) {
			return save(entity, fileId);
		} else {
			return update(entity, fileId);
		}
	}

	/**
	 * 获取用户的未读通告的数量
	 * 
	 * @param userId 用户id
	 */
	@Override
	public Integer countNoReadAnnounce(String userId) {
		if (StringUtils.isBlank(userId)) {
			return 0;
		}
		return famsAnounceNotifyDAO.countNoReadAnnounce(userId);
	}

	/**
	 * 获取用户接收的通告
	 * 
	 * @param userId     用户Id
	 * @param offSet     数据偏移量
	 * @param limit      返回数据量
	 * @param searchInfo 搜索信息(标题或者签发人)
	 */
	@Override
	public List<Map<String, Object>> selectNotifiesByUserId(String userId, int offSet, int limit, String searchInfo)
			throws Exception {
		List<Map<String, Object>> lists = new ArrayList<>();
		if (StringUtils.isBlank(userId) || offSet < 0) {
			return lists;
		}
		List<Map<String, Object>> recs = famsAnounceNotifyDAO.getAnnounceByUserIdAndSearchInfo(userId, offSet, limit,
				searchInfo);
		return recs;
	}

	/**
	 * 统计通告的接收和阅读数量
	 */
	@Override
	public Integer[] countAnnounce(String notifyId) {
		Integer[] cnts = new Integer[2];
		cnts[0] = new Integer(0);
		cnts[1] = new Integer(0);
		if (StringUtils.isBlank(notifyId) || super.getEntity(FamsAnnounceNotifyEntity.class, notifyId) == null) {
			return cnts;
		}
		// 总的数量
		cnts[0] = famsAnounceNotifyDAO.countAnnounceByNotifyId(notifyId);
		// 阅读过的数量
		cnts[1] = famsAnounceNotifyDAO.countReadAnnounceByNotifyId(notifyId);
		return cnts;
	}

	/**
	 * 通告通告id和用户id获取接收对象
	 */
	@Override
	public FamsAnounceReceive getAnounceReceive(String notifyId, String userId) {
		if (StringUtils.isBlank(notifyId) || StringUtils.isBlank(userId)) {
			return null;
		}
		return famsAnounceNotifyDAO.getFamsAnounceReceiveByNotifyIdAndUserId(notifyId, userId);
	}

	/**
	 * 弃用方法 递归获取业务id下的所有回复
	 * 
	 * @param businessId 业务Id
	 * @param list       存放结果
	 */
	@Override
	public void selectRepliesByBusinessId(String businessId, List<FamsCommonReplyEntity> list) throws Exception {
		// 终止条件
		if (!StringUtils.isBlank(businessId)) {
			List<FamsCommonReplyEntity> entitys = super.findByProperty(FamsCommonReplyEntity.class, "businessId",
					businessId);
			if (entitys != null && !entitys.isEmpty()) {
				list.addAll(entitys);
				for (FamsCommonReplyEntity entity : entitys) {
					selectRepliesByBusinessId(entity.getId(), list);
				}
			}
		}

	}

	/**
	 * 包装回复信息成{@code ReplyInfoVO}
	 * 
	 * @param list 回复信息
	 */
	@Override
	public List<ReplyInfoVO> listReplyInfoVOs(List<FamsCommonReplyEntity> list) throws Exception {
		List<ReplyInfoVO> vos = new ArrayList<>();
		for (FamsCommonReplyEntity entity : list) {
			ReplyInfoVO vo = new ReplyInfoVO();
			TSUser user = super.getEntity(TSUser.class, entity.getReplyUserId());
			TSUser toUser = super.getEntity(TSUser.class, entity.getReplyToUserId());
			if (user != null && toUser != null) {
				TSDepart depart = super.getEntity(TSDepart.class, user.getDepartid());
				TSDepart toDepart = super.getEntity(TSDepart.class, toUser.getDepartid());
				vo.setName(user.getRealName());
				vo.setDepartment(depart.getDepartname());
				vo.setReplyName(toUser.getRealName());
				vo.setReplyDepartment(toDepart.getDepartname());
				vo.setDate(entity.getCreateTime());
				vo.setContent(entity.getReplyContent());
				vo.setId(entity.getId());
				vos.add(vo);
			}
		}
		return vos;
	}

	/**
	 * 根据通知通告的Id查询接收的信息
	 */
	@Override
	public List<ReceiveVO> listReceiveVOs(String notifyId) {
		List<ReceiveVO> res = new ArrayList<>();
		if (StringUtils.isBlank(notifyId)) {
			return res;
		}
		// 格式化时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<FamsAnounceReceive> list = super.findByProperty(FamsAnounceReceive.class, "notifyId", notifyId);
		Collections.sort(list, new Comparator<FamsAnounceReceive>() {

			@Override
			public int compare(FamsAnounceReceive o1, FamsAnounceReceive o2) {
				if (o2.getReadTime()!=null&&o1.getReadTime()!=null) {
					return o2.getReadTime().compareTo(o1.getReadTime());
				}else if(o1.getReadTime()!=null) {
					return -1;
				}else if(o2.getReadTime()!=null) {
					return 1;
				}else {
					return o1.getState().compareTo(o2.getState());
				}
			}
		});
		for (FamsAnounceReceive rc : list) {
			ReceiveVO vo = new ReceiveVO();
			vo.setId(rc.getId());
			vo.setUserId(rc.getUserId());
			vo.setState(rc.getState());
			if (rc.getReadTime() != null) {
				vo.setViewTime(format.format(rc.getReadTime()));
			}
			TSUser user = super.getEntity(TSUser.class, rc.getUserId());
			if (user != null) {
				vo.setRealName(user.getRealName());
				vo.setHeadUrl(user.getPortrait());
				vo.setUserName(user.getUserName());
				res.add(vo);
			}
		}
		return res;
	}

	/**
	 * 递归获取当前部门和子部门下的所有用户
	 * 
	 * @param depart 部门
	 * @param list   结果集
	 */
	private void dfsDeparts(TSDepart depart, List<TSBaseUser> list) {
		if (depart != null && !StringUtils.isBlank(depart.getId())) {
			List<TSBaseUser> users = famsCommonService.findByProperty(TSBaseUser.class, "departid", depart.getId());
			// 过滤掉被删除的用户
			for (TSBaseUser user : users) {
				// 用户没被删除
				if (Globals.Delete_Normal == user.getDeleteFlag()) {
					list.add(user);
				}
			}
			for (TSDepart d : depart.getTSDeparts()) {
				dfsDeparts(d, list);
			}
		}
	}

	/**
	 * 获取飞管部门的所有人员
	 */
	@Override
	public List<TSBaseUser> getAllUsersOfDepart() throws Exception {

		// 获取数据字典定义的飞管部门的名称
		TSTypegroup tsTypegroup = famsCommonService.findUniqueByProperty(TSTypegroup.class, "typegroupcode",
				NotifyContant.DATA_DIR__FLY_MANAGE);
		if (tsTypegroup == null) {
			throw new Exception("飞管内部部门名称数据字典配置不对");
		}
		// 根据名称获取飞管的部门对象
		TSDepart depart = findUniqueByProperty(TSDepart.class, "departname", tsTypegroup.getTypegroupname());
		// 查询飞管部门下的所有人员
		List<TSBaseUser> userList = new ArrayList<>();
		dfsDeparts(depart, userList);
		return userList;
	}

	/**
	 * 根据用户的Id返回用户需要接收的通知通告 如果userId为空({@code null}或者{@code ""})返回空数组
	 * 
	 * @param userId 用户Id
	 */
	@Override
	public List<FamsAnounceReceive> listReceivesByUserId(String userId) throws Exception {
		return listReceivesByUserIdAndReadState(userId, null);
	}

	@Override
	public List<FamsAnounceReceive> listReceivesByUserIdAndReadState(String userId, String readState) throws Exception {
		List<FamsAnounceReceive> list = new ArrayList<>();
		if (StringUtils.isBlank(userId)) {
			return list;
		}
		String[] pros = null;
		String[] vals = null;
		if (!StringUtils.isBlank(readState)) {
			pros = new String[] { "userId", "state" };
			vals = new String[] { userId, readState };
		} else {
			pros = new String[] { "userId" };
			vals = new String[] { userId };
		}
		// 获取接收情况
		list = famsCommonService.getEntitiesByProperty(FamsAnounceReceive.class, pros, vals);
		return list;
	}

	/**
	 * 转换通知类型成相应的字符串{@link com.gbiac.fams.business.anounce.enums.NotifyType}}
	 * 
	 * @param noticeType
	 * @return
	 */
	private String noticeTypeConverse(String noticeType) {
		if (noticeType.equals(NotifyType.GENERAL.getName())) {
			return NotifyType.GENERAL.getValue();
		} else if (noticeType.equals(NotifyType.RUN.getName())) {
			return NotifyType.RUN.getValue();
		} else if (noticeType.equals(NotifyType.VOYAGE.getName())) {
			return NotifyType.VOYAGE.getValue();
		}
		return "";
	}

}