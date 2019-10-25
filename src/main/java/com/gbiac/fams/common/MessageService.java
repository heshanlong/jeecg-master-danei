package com.gbiac.fams.common;

import java.util.List;
import java.util.Map;

import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.springframework.stereotype.Service;

import com.gbiac.fams.common.Entity.MessageType;

/**
 * 消息推送的服务
 * 用于pc端消息推送和app端消息推送
 * 
 * @author mchen
 *
 */
public interface MessageService {
	/**
	 * 仅仅对PC端发送消息给接收人
	 * @author mchen
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param title 标题
	 * @param content 内容
	 * @param sender	发送人
	 * @param receivers	接收用户 (不能为 {@code null},可以是空数组)
	 * @param remark	备注 (可以为{@code null})
	 * @param messageType 提升消息{@link MessageType}可以自定义
	 * @param extrasMap 额外信息 没有数据传{@code null}
	 */
	void pushOnlyPCMessage(String module,String businessId,String title,String content,String sender,List<TSBaseUser> receivers,String remark,Map<String, String> extrasMap);
	/**
	 * 仅仅对PC端发送消息给接收人
	 * @author mchen
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param title 标题
	 * @param content 内容
	 * @param sender	发送人
	 * @param receivers	接收用户 (不能为 {@code null},可以是空数组)
	 * @param remark	备注 (可以为{@code null})
	 * @param messageType 提升消息{@link MessageType}可以自定义
	 * @param extrasMap 额外信息 没有数据传{@code null}
	 */
	void pushOnlyPCMessage(String module,String businessId,String title,String content,String sender,List<String> userIds,String remark,Map<String, String> extrasMap,Object o);
	/**
	 * 发送消息给接收人
	 * @author mchen
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param title 标题
	 * @param content 内容
	 * @param sender	发送人
	 * @param receivers	接收用户 (不能为 {@code null},可以是空数组)
	 * @param remark	备注 (可以为{@code null})
	 * @param messageType 提升消息{@link MessageType}可以自定义
	 * @param extrasMap 额外信息 没有数据传{@code null}
	 */
	void pushMessage(String module,String businessId,String title,String content,String sender,List<TSBaseUser> receivers,String remark,Map<String, String> extrasMap);
	/**
	 * 根据角色推送消息
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param title 标题
	 * @param content 内容
	 * @param roleCode	角色编码
	 * @param receivers	接收用户 (不能为 {@code null},可以是空数组)
	 * @param remark	备注 (可以为{@code null})
	 * @param messageType 提升消息{@link MessageType}可以自定义
	 * @param extrasMap 额外信息 没有数据传{@code null}
	 */
	void pushMessage(String module,String businessId,String title,String content,String sender,String roleCode,String remark,Map<String, String> extrasMap);

	/**
	 * 根据角色推送消息
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param title 标题
	 * @param content 内容
	 * @param userIds	用户id集合
	 * @param receivers	接收用户 (不能为 {@code null},可以是空数组)
	 * @param remark	备注 (可以为{@code null})
	 * @param messageType 提升消息{@link MessageType}可以自定义
	 * @param extrasMap 额外信息 没有数据传{@code null}
	 */
	void pushMessage(String module,String businessId,String title,String content,String sender,List<String> userIds,String remark,Map<String, String> extrasMap,Object o);
	/**
	 * 根据多个角色推送消息
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param title 标题
	 * @param content 内容
	 * @param roleCode	角色编码
	 * @param receivers	接收用户 (不能为 {@code null},可以是空数组)
	 * @param remark	备注 (可以为{@code null})
	 * @param messageType 提升消息{@link MessageType}可以自定义
	 * @param extrasMap 额外信息 没有数据传{@code null}
	 */
	void pushMessage(String module,String businessId,String title,String content,String sender,String[] roleCode,String remark,Map<String, String> extrasMap);
	
	/**
	 * 根据部门ID推送消息
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param title 标题
	 * @param content 内容
	 * @param roleCode	角色编码
	 * @param receivers	接收用户 (不能为 {@code null},可以是空数组)
	 * @param remark	备注 (可以为{@code null})
	 * @param messageType 提升消息{@link MessageType}可以自定义
	 * @param extrasMap 额外信息 没有数据传{@code null}
	 */
	void pushMessageByDepart(String module,String businessId,String title,String content,String sender,String departId,String remark,Map<String, String> extrasMap);
	
	
	/**
	 * 推送消息给当前部门下的多个角色
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param title 标题
	 * @param content 内容
	 * @param roleCode	角色编码
	 * @param receivers	接收用户 (不能为 {@code null},可以是空数组)
	 * @param remark	备注 (可以为{@code null})
	 * @param messageType 提升消息{@link MessageType}可以自定义
	 * @param extrasMap 额外信息 没有数据传{@code null}
	 */
	void pushMessageByDepart(String module,String businessId,String title,String content,String sender,String departId,String[] roleCodes,String remark,Map<String, String> extrasMap);
	



}
