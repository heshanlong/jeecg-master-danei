package com.gbiac.fams.common.reply.service;
import com.gbiac.fams.business.anounce.vo.ReplyInfoVO;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface FamsCommonReplyServiceI extends CommonService{
	
 	public void delete(FamsCommonReplyEntity entity) throws Exception;
 	
 	public Serializable save(FamsCommonReplyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsCommonReplyEntity entity) throws Exception;
 
	public List<FamsCommonReplyEntity> queryListByEntity(FamsCommonReplyEntity entity);
	/**
	 * 暂时不用的方法
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectRepliesByBusinessId(String businessId) throws Exception;
	/**暂时不用的方法
	 * 
	 * @param businessId
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectRepliesByBusinessId(String businessId,String state) throws Exception;
	/**
	 * 暂时不用的方法
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<ReplyInfoVO> listReplyInfoVOs(List<FamsCommonReplyEntity> list) throws Exception;

	/**
	 * 整改单和违章告知单为首页定制的方法
	 * @param entity
	 * @return
	 */
	List<Map> queryListByEntityForIndex(FamsCommonReplyEntity entity);


}
