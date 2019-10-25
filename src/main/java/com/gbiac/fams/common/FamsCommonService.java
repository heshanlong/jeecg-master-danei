package com.gbiac.fams.common;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;

import java.util.List;
import java.util.Map;

public interface FamsCommonService extends CommonService{

	/**
	 * 通过文件id集合更新该集合中的moudle、businessid、state属性
	 * @author 龚道海
	 * @param ids 文件id集合
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param state 状态
	 * @return 返回更新的个数
	 * @throws Exception
	 */
 	public Integer updateMBSofFile(List ids, String module, String businessId, String state) throws Exception;

	/**
	 * 通过文件ids字符串更新该集合中的moudle、businessid、state属性
	 * @author 邓正辉
	 * @param idStr id字符串
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param state 状态
	 * @return 返回更新的个数
	 * @throws Exception
	 */
 	public Integer updateMBSofFileByStr(String idStr,String module, String businessId, String state) throws Exception;

	/**
	 * 邓正辉，根据ids判断是否修改了图片
	 * @param idStr
	 * @param module
	 * @param businessId
	 * @param state
	 * @return
	 * @throws Exception
	 */

 	public Boolean hasUpdateFile(String idStr,String module, String businessId, String state) throws Exception;

	/**
	 * 通用推送日志保存方法
	 * @author 龚道海
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param idAndImei 推送目标用户信息
	 * @param pushContent 推送内容
	 * @return 返回id
	 * @throws Exception
	 */
	public void savePushs(String module, String businessId,  Map<String,String> idAndImei,String pushContent) throws Exception;

	/**
	 * 通用推送日志保存方法
	 * @author 龚道海
	 * @param sendUserId 推送用户id
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param idAndImei 推送目标用户信息
	 * @param pushContent 推送内容
	 * @return 返回id
	 * @throws Exception
	 */
	public void savePushs(String sendUserId,String module, String businessId,  Map<String,String> idAndImei,String pushContent) throws Exception;

	/**
	 * 给一个用户推送消息
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param userId 推送目标用户id
	 * @param title 推送标题
	 * @param content 推送内容
	 * @param extrasMap 额外信息
	 * @throws Exception
	 */
	public void sendMessage(String module, String businessId, String userId,String title,String content,Map<String, String> extrasMap) throws Exception;

	/**
	 * 给多个用户推送消息
	 * @param sendUserId 推送用户id
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param userIds 推送目标用户id集合
	 * @param title 推送标题
	 * @param content 推送内容
	 * @param extrasMap 额外信息
	 * @throws Exception
	 */
	public void sendMessage(String sendUserId,String module, String businessId, List<String> userIds,String title,String content,Map<String, String> extrasMap) throws Exception;

	/**
	 * 给多个用户推送消息
	 * @param sendUserId 推送用户id
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param users 推送目标用户集合
	 * @param title 推送标题
	 * @param content 推送内容
	 * @param extrasMap 额外信息
	 * @throws Exception
	 */
	public void sendMessageByUsers(String sendUserId,String module, String businessId, List<TSBaseUser> users,String title,String content,Map<String, String> extrasMap) throws Exception;

	/**
	 * 给所有用户推送消息
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param title 推送标题
	 * @param content 推送内容
	 * @param extrasMap 额外信息
	 * @throws Exception
	 */
	public void sendMessageToAll(String module, String businessId, String title,String content,Map<String, String> extrasMap) throws Exception;

	/**
	 * 根据用户id集合获取imei号集合
	 * @param userIds
	 * @return
	 */
	public Map getIdAndImeis(List userIds);

	/**
	 * 通用回复保存方法
	 * 该回复对象是业务
	 * @author 龚道海
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param replyContent 回复内容
	 * @return 返回id
	 * @throws Exception
	 */
	public String saveReply(String module, String businessId, String replyContent) throws Exception;

	/**
	 * 通用回复保存方法
	 * 该回复对象是业务下的某个人
	 * @author 龚道海
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param replyToUserId 回复目标用户id
	 * @param replyContent 回复内容
	 * @return 返回id
	 * @throws Exception
	 */
	public String saveReply(String module, String businessId, String replyToUserId,String replyContent) throws Exception;


	/**
	 * 用于保存动态,直接存中文名，用于展示
	 * @author 邓正辉
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param replyContent 回复内容
	 * @return 返回id
	 * @throws Exception
	 */
	public String saveReplyShow(String module, String businessId,String state,String replyContent) throws Exception;
	
	
	/**
	 * 通用回复保存方法
	 * 该回复对象是业务下的某个人
	 * 
	 * @author Mchen
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param toUserName 回复对象的名称
	 * @param replyContent 回复内容
	 * @param toDepartName 回复对象的部门名称
	 * @return 返回id
	 * @throws Exception
	 */
	public String saveReplyShow(String module, String businessId,String toUserName, String toDepartName,String state,String replyContent) throws Exception;

	/**
	 * 通用流程进度保存方法
	 * @author 龚道海
	 * @param userId 用户id
	 * @param departId 用户部门id
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param flag 标识 即流程节点标识：如“提交”标识为1
	 * @param note 流程备注  如已提交
	 * @param content 内容
	 * @param nodeId 流程节点id   如果流程节点是关联的其他表，此处为其他表id
	 * @param state 状态
	 * @return 返回id
	 * @throws Exception
	 */
	public String saveProgress(String userId,String departId,String module, String businessId, String flag,String note,String content,String nodeId,String state) throws Exception;

	/**
	 * 通用流程进度保存方法
	 * @author 龚道海
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param flag 标识 即流程节点标识：如“提交”标识为1
	 * @param note 流程备注  如已提交
	 * @param content 内容
	 * @param nodeId 流程节点id   如果流程节点是关联的其他表，此处为其他表id
	 * @param state 状态
	 * @return 返回id
	 * @throws Exception
	 */
	public String saveProgress(String module, String businessId, String flag,String note,String content,String nodeId,String state) throws Exception;

	/**
	 * 通用地图标注保存方法
	 * @author 龚道海
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param mapPoints 点位信息
	 * @param mapPositions 位置信息
	 * @return 返回id
	 * @throws Exception
	 */
	public String saveMap(String module, String businessId, String mapPoints,String mapPositions) throws Exception;

	/**
	 * 根据模块名、业务id、状态获取通用对象
	 * @author 龚道海
	 * @param clazz 类class
	 * @param module 模块名
	 * @param businessId 业务id
	 * @param state 状态
	 * @param order 排序 格式为：字段名,升序|降序   eg: "createTime,desc"
	 * @return
	 */
	public <T> List<T>  getCommonObject(Class<T> clazz,String module, String businessId,String state,List<String> order);

	/**
	 * 根据实体字段名查询实体集合
	 * @author 龚道海
	 * @param clazz
	 * @param propertys
	 * @param values
	 * @param <T>
	 * @return
	 */
	public <T> List<T>  getEntitiesByProperty(Class<T> clazz, String[] propertys, String[] values);

	/**
	 * 根据实体字段名查询单个实体
	 * @author 龚道海
	 * @param clazz
	 * @param propertys
	 * @param values
	 * @param <T>
	 * @return
	 */
	public <T> T  getEntityByProperty(Class<T> clazz, String[] propertys, String[] values);

	/**
	 * 根据实体字段名删除实体表数据
	 * @author 龚道海
	 * @param clazz 实体
	 * @param propertys 属性列表  			eg:['id','name']
	 * @param values 属性列表对应的值列表	eg:['1','张三']
	 * @param <T>
	 */
	public <T> void deleteByProperty(Class<T> clazz, String[] propertys, String[] values);


	public List<Map<String,Object>>  loadFlightData(String flightno);

	public List<Map<String,Object>>  loadFlightDataFromAomip(String flightno);


	public List<Map<String,Object>>  loadAllTsdepartByName(String departname);
	
	public List<Map<String,Object>> getFlightDateByCraftsiteFromAomip(String craftsite);
	
	/**
	 * 用户id判断是否拥有某个角色
	 * @param ids
	 * @return
	 */
    public boolean jcxj(String id,int a) ;

	/**
	 * 获取拥有施工审批权限的用户id集合
	 * @return
	 */
	List<String> getCanApproveIds();

	/**
	 * 获取拥有施工申请权限的当前用户部门下的所有人员集合
	 * @param createUserId
	 * @return
	 */
	List<TSBaseUser> getApplyUsers(String createUserId);

	/**
	 * 获取用户id对应的用户对象
	 * @param createUserId
	 * @return
	 */
	List<TSBaseUser> getApplyUser(String createUserId);

	public List<String> getCanApproveIdsByOrgId(String pcOrAppSysOrgCode);

	public List<String> getCanApproveIdsByRunPartOrgId();

	public List<String> getCanApproveIdsByOrgCode(String pcOrAppSysOrgCode);
	
	public List<String> getAdminUsers();
}
