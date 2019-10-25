package com.gbiac.fams.common.reply.service.impl;

import com.gbiac.fams.business.anounce.vo.ReplyInfoVO;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.reply.dao.FamsCommonReplyDao;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
import com.gbiac.fams.common.reply.service.FamsCommonReplyServiceI;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("famsCommonReplyService")
@Transactional
public class FamsCommonReplyServiceImpl extends CommonServiceImpl implements FamsCommonReplyServiceI {
	@Autowired
	FamsCommonReplyDao famsCommonReplyDao;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired 
	private FamsCommonService famsCommonService;

	public void delete(FamsCommonReplyEntity entity) throws Exception {
		super.delete(entity);
	}

	public Serializable save(FamsCommonReplyEntity entity) throws Exception {
		Serializable t = super.save(entity);
		return t;
	}

	public void saveOrUpdate(FamsCommonReplyEntity entity) throws Exception {
		super.saveOrUpdate(entity);
	}

	@Override
	public List<FamsCommonReplyEntity> queryListByEntity(FamsCommonReplyEntity entity) {
		return famsCommonReplyDao.queryListByEntity(entity);
	}

	/**
	 * 递归获取业务id下的所有对回复的回复
	 * 
	 * @author Mchen
	 * @param businessId 业务Id
	 * @param list       存放结果
	 */

	private void selectRepliesByBusinessId(String businessId, List<FamsCommonReplyEntity> list) throws Exception {
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
	 * @author Mchen
	 * @param list 回复信息
	 */
	@Override
	public List<ReplyInfoVO> listReplyInfoVOs(List<FamsCommonReplyEntity> list) throws Exception {
		List<ReplyInfoVO> vos = new ArrayList<>();
		for (FamsCommonReplyEntity entity : list) {
			ReplyInfoVO vo = new ReplyInfoVO();
			vo.setName(entity.getReplyUserId());
			vo.setDepartment(entity.getReplyDepartId());
			vo.setReplyName(entity.getReplyToUserId());
			vo.setReplyDepartment(entity.getFiled1());
			vo.setDate(entity.getCreateTime());
			vo.setContent(entity.getReplyContent());
			vo.setId(entity.getId());
			vos.add(vo);

		}
		return vos;
	}

	/**
	 * 根据表单Id获取表单下所有回复信息
	 * 
	 * @author Mchen
	 * @param businessId 表单Id
	 */
	@Override
	public List<Map<String, Object>> selectRepliesByBusinessId(String businessId) throws Exception {
		return selectRepliesByBusinessId(businessId, "");
	}

	@Override
	public List<Map<String, Object>> selectRepliesByBusinessId(String businessId, String state) throws Exception {
		
		List<Map<String, Object>> res = new ArrayList<>();
		if (StringUtils.isBlank(businessId)) {
			return res;
		}
		List<FamsCommonReplyEntity> replyEntities = null;
		if (StringUtils.isBlank(state)) {
			replyEntities = super.findByProperty(FamsCommonReplyEntity.class, "businessId",
					businessId);
		}else {
			String[] pros = new String[] {"businessId","state"};
			String[] vals = new String[] {businessId,state};
			replyEntities = famsCommonService.getEntitiesByProperty(FamsCommonReplyEntity.class, pros, vals);
		}
		for (FamsCommonReplyEntity rp : replyEntities) {

			Map<String, Object> mp = new HashMap<>();
			// 回复的内容
			mp.put("recordContent", rp.getReplyContent());
			// 回复的时间
			mp.put("recordTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rp.getCreateTime()));
			// 回复消息的ID
			mp.put("id", rp.getId());
			// 回复人员信息
			if (rp.getReplyUserId() != null && rp.getReplyDepartId() != null) {

				mp.put("recordName", rp.getReplyUserId());
				mp.put("recordDepartName", rp.getReplyDepartId());
				// 回复人员消息的消息

				List<FamsCommonReplyEntity> list = new ArrayList<>();
				// 获取消息
				this.selectRepliesByBusinessId(rp.getId(), list);
				// 对消息排序
				Collections.sort(list, new Comparator<FamsCommonReplyEntity>() {

					@Override
					public int compare(FamsCommonReplyEntity o1, FamsCommonReplyEntity o2) {

						return o1.getCreateTime().compareTo(o2.getCreateTime());
					}
				});
				// 包装回复消息
				List<ReplyInfoVO> replyList = this.listReplyInfoVOs(list);
				mp.put("recordList", replyList);

			}

			res.add(mp);
		}
		return res;
	}

	@Override
	public List<Map> queryListByEntityForIndex(FamsCommonReplyEntity entity) {
		return famsCommonReplyDao.queryListByEntityForIndex(entity);
	}
}