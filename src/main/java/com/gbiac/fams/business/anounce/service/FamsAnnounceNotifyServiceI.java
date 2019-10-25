package com.gbiac.fams.business.anounce.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;

import com.gbiac.fams.business.anounce.entity.FamsAnnounceNotifyEntity;
import com.gbiac.fams.business.anounce.entity.FamsAnounceReceive;
import com.gbiac.fams.business.anounce.vo.ReceiveVO;
import com.gbiac.fams.business.anounce.vo.ReplyInfoVO;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
/**
 * 通知通告的业务层
 * 用于对通知通告的服务提供接口
 * @author Mchen
 *
 */
public interface FamsAnnounceNotifyServiceI extends CommonService {
	/**
	 * 删除通知通告的实体
	 * @param entity 通知通告实体\
	 * @param reason 撤回原因
	 * @throws Exception
	 */
	public void delete(FamsAnnounceNotifyEntity entity,String reason) throws Exception;
	/**
	 * 保存或更新通知通告的实体和文件
	 * @param entity
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	public Integer saveOrUpdate(FamsAnnounceNotifyEntity entity, String fileId) throws Exception;
	/**
	 * 根据用户的Id 返回用户未读的通知通告的数量
	 * @param userId
	 * @return
	 */
	public Integer countNoReadAnnounce(String userId);
	
	/**
	 * 统计通知通告的已读和未读的数量
	 * @param notifyId 通知通告Id
	 * @return
	 */
	public Integer[] countAnnounce(String notifyId);

	/**
	 * 获取用户的通知通告列表
	 * @param userId	用户Id
	 * @param offSet	偏移值
	 * @param limit		返回数量
	 * @param searchInfo	查询的信息
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectNotifiesByUserId(String userId, int offSet, int limit, String searchInfo)
			throws Exception;
	/**
	 * 获取接收实体
	 * @param notifyId	通知通告Id
	 * @param userId	用户Id
	 * @return
	 */
	public FamsAnounceReceive getAnounceReceive(String notifyId, String userId);
	/**暂时不用的接口
	 * 	
	 * @param businessId 
	 * @param list
	 * @throws Exception
	 */
	public void selectRepliesByBusinessId(String businessId, List<FamsCommonReplyEntity> list) throws Exception;
	/**暂时不用的接口
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<ReplyInfoVO> listReplyInfoVOs(List<FamsCommonReplyEntity> list) throws Exception;
	/**
	 * 暂时不用的接口
	 * @param notifyId
	 * @return
	 */
	public List<ReceiveVO> listReceiveVOs(String notifyId);
	/**
	 * 获取飞管部门下的所有用户
	 * @return
	 * @throws Exception
	 */
	public List<TSBaseUser> getAllUsersOfDepart()throws Exception;
	/**
	 * 获取用户接收的实体信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<FamsAnounceReceive> listReceivesByUserId(String userId)throws Exception;
	/**
	 * 根据阅读状态获取用户的接收实体
	 * @param userId 用户Id
	 * @param readState	阅读状态	("1"已读,其他都表示未读)
	 * @return
	 * @throws Exception
	 */
	public List<FamsAnounceReceive> listReceivesByUserIdAndReadState(String userId,String readState)throws Exception;
}
