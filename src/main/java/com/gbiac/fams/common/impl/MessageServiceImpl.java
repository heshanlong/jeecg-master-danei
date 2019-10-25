package com.gbiac.fams.common.impl;

import com.gbiac.fams.common.Entity.MessageType;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.MessageService;
import com.gbiac.fams.common.OnlineMessageManger;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.dao.FamsCommonDao;
import com.gbiac.fams.restutil.SessionUserUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.sms.entity.TSSmsEntity;
import org.jeecgframework.web.system.sms.service.TSSmsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private TSSmsServiceI tSSmsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private FamsCommonDao famsCommonDao;

	@Override
	public void pushMessage(final String module, final String businessId, final String title, final String content,
			final String sender, final List<TSBaseUser> receivers, final String remark,
			final Map<String, String> extrasMap) {
		pushOnlyPCMessage(module, businessId, title, content, sender, receivers, remark, extrasMap);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (TSBaseUser user : receivers) {
					try {
						famsCommonService.sendMessage(module, businessId, user.getId(), title, content, extrasMap);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
		thread.start();
	}

	@Override
	public void pushOnlyPCMessage(String module, String businessId, String title, String content, String sender,
			List<TSBaseUser> receivers, String remark, Map<String, String> extrasMap) {
		final String userName = Util.getCurrentUserName().isEmpty() ? SessionUserUtil.getCurrentUserName()
				: Util.getCurrentUserName();
		final String realName = Util.getUserName().isEmpty() ? SessionUserUtil.getUserRealName() : Util.getUserName();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Date date = new Date();
				OnlineMessageManger onlineMessageManger = new OnlineMessageManger();
				for (TSBaseUser user : receivers) {
					TSSmsEntity entity = new TSSmsEntity();
					entity.setEsTitle(title);
					entity.setEsReceiver(user.getUserName());
					entity.setEsContent(content);
					entity.setEsSender(sender);
					entity.setIsRead(0);
					entity.setEsType("3");
					entity.setEsSendtime(date);
					entity.setRemark(remark);
					entity.setCreateBy(userName);
					entity.setCreateDate(date);
					entity.setCreateName(realName);
					String id = (String) tSSmsService.save(entity);
					onlineMessageManger.sendMessageAndTsmId(new MessageType(0, title), user.getUserName(), id);
				}

			}
		});
		thread.start();

	}

	@Override
	public void pushOnlyPCMessage(String module,String businessId,String title,String content,String sender,List<String> userIds,String remark,Map<String, String> extrasMap,Object o) {
		if(userIds!=null&&userIds.size()>0){
			//根据用户id集合获取用户对象
			List<TSBaseUser> users =famsCommonDao.getUsersByIds(userIds);
			if(users.size()>0){
				pushOnlyPCMessage(module, businessId, title, content, sender, users, remark, extrasMap);
			}
		}
	}

	@Override
	public void pushMessage(String module, String businessId, String title, String content, String sender,
			String roleCode, String remark, Map<String, String> extrasMap) {
		// 根据角色获取该角色的所有用户
		List<TSRole> roles = systemService.findByProperty(TSRole.class, "roleCode", roleCode);
		List<TSBaseUser> users = new ArrayList<>();
		for (TSRole role : roles) {
			List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TSRole.id", role.getId());
			for (TSRoleUser roleUser : roleUsers) {
				users.add(roleUser.getTSUser());
			}
		}
		pushMessage(module, businessId, title, content, sender, users, remark, extrasMap);
	}

	@Override
	public void pushMessage(String module, String businessId, String title, String content, String sender,
							List<String> userIds, String remark, Map<String, String> extrasMap,Object o) {
		if(userIds!=null&&userIds.size()>0){
			//根据用户id集合获取用户对象
			List<TSBaseUser> users =famsCommonDao.getUsersByIds(userIds);
			if(users.size()>0){
				pushMessage(module, businessId, title, content, sender, users, remark, extrasMap);
			}
		}
	}

	@Override
	public void pushMessage(String module, String businessId, String title, String content, String sender,
			String[] roleCodes, String remark, Map<String, String> extrasMap) {
		List<TSBaseUser> users = new ArrayList<>();
		for (String roleCode : roleCodes) {
			// 根据角色获取该角色的所有用户
			List<TSRole> roles = systemService.findByProperty(TSRole.class, "roleCode", roleCode);
			for (TSRole role : roles) {
				List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TSRole.id", role.getId());
				for (TSRoleUser roleUser : roleUsers) {
					users.add(roleUser.getTSUser());
				}
			}
		}
		pushMessage(module, businessId, title, content, sender, users, remark, extrasMap);
	}

	@Override
	public void pushMessageByDepart(String module, String businessId, String title, String content, String sender,
			String departId, String remark, Map<String, String> extrasMap) {
		// 根据部门Id查询部门下的用户

		List<TSUserOrg> userOrgs = systemService.findByProperty(TSUserOrg.class, "tsDepart.id", departId);
		List<TSBaseUser> users = new ArrayList<>(userOrgs.size());
		for (TSUserOrg tsUserOrg : userOrgs) {
			users.add(tsUserOrg.getTsUser());
		}
		pushMessage(module, businessId, title, content, sender, users, remark, extrasMap);
	}

	@Override
	public void pushMessageByDepart(String module, String businessId, String title, String content, String sender,
			String departId, String[] roleCodes, String remark, Map<String, String> extrasMap) {
		// 根据部门Id查询部门下的用户
		List<TSUserOrg> userOrgs = systemService.findByProperty(TSUserOrg.class, "tsDepart.id", departId);
		HashSet<String> userIdSet = new HashSet<>();
		for (TSUserOrg tsUserOrg : userOrgs) {
			userIdSet.add(tsUserOrg.getTsUser().getId());
		}
		List<TSBaseUser> roleusers = new ArrayList<>();
		for (String roleCode : roleCodes) {
			// 根据角色获取该角色的所有用户
			List<TSRole> roles = systemService.findByProperty(TSRole.class, "roleCode", roleCode);
			for (TSRole role : roles) {
				List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TSRole.id", role.getId());
				for (TSRoleUser roleUser : roleUsers) {
					if (userIdSet.contains(roleUser.getTSUser().getId())) {
						roleusers.add(roleUser.getTSUser());
					}
				}
			}
		}
		pushMessage(module, businessId, title, content, sender, roleusers, remark, extrasMap);

	}

}
