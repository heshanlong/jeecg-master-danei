package com.gbiac.fams.business.anounce.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gbiac.fams.business.anounce.entity.CommonState;
import com.gbiac.fams.business.anounce.entity.FamsAnnounceNotifyEntity;
import com.gbiac.fams.business.anounce.entity.FamsAnounceReceive;
import com.gbiac.fams.business.anounce.service.FamsAnnounceNotifyServiceI;
import com.gbiac.fams.business.anounce.vo.NotifyReceiveVO;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.reply.service.FamsCommonReplyServiceI;

/**
 * 接收通知通告的控制类
 * 用于查看当前用户需要接收的表单
 * @author Mchen
 *
 */
@Controller
@RequestMapping("/famsAnnounceNotifyReceiveController")
public class FamsAnnounceNotifyReceiveController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(FamsAnnounceNotifyController.class);

	@Autowired
	private FamsAnnounceNotifyServiceI famsAnnounceNotifyService;
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	/**
	 * 接收通知通告表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/anounce/famsAnnounceNotifyReceiveList");
	}
	
	

	/**
	 * easyui AJAX请求数据 返回自己接收的通知通告
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagridReceive(FamsAnnounceNotifyEntity famsAnnounceNotify, @RequestParam String readState, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String sortField = dataGrid.getSort();
		//屏蔽readState排序
		if ("readState".equals(dataGrid.getSort())) {
			dataGrid.setSort(null);
		}
		CriteriaQuery cq = new CriteriaQuery(FamsAnnounceNotifyEntity.class, dataGrid);
		// notifyId --> FamsAnounceReceive
		HashMap<String, FamsAnounceReceive> map = new HashMap<>();
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAnnounceNotify,
				request.getParameterMap());
		try {
			// 获取当前用户的接收的通告
			List<FamsAnounceReceive> recs = null;
			//判断是否有阅读状态的查询
			if (!StringUtils.isBlank(readState)) {
				recs = famsAnnounceNotifyService.listReceivesByUserIdAndReadState(Util.getUserId(), readState);
			}else {
				recs = famsAnnounceNotifyService.listReceivesByUserId(Util.getUserId());
			}
			String[] notifyIds = new String[recs.size()];
			for (int i = 0; i < notifyIds.length; i++) {
				notifyIds[i] = recs.get(i).getNotifyId();
				map.put(notifyIds[i], recs.get(i));
			}
			// 添加接收条件
			//如果没有接收的通告,强制设置没有数据
			if (notifyIds.length==0) {
				cq.eq("id", "-1");
			}else {
				cq.in("id", notifyIds);
			}
			cq.eq("state", CommonState.NORMAL_STATE);
			
		

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsAnnounceNotifyService.getDataGridReturn(cq, true);
		try {
			// 转换对象 将FamsAnnounceNotifyEntity对象转换成NotifyReceiveVO
			List<NotifyReceiveVO> recVOs = new ArrayList<>(dataGrid.getResults().size());
			for (Object obj : dataGrid.getResults()) {
				FamsAnnounceNotifyEntity entity = (FamsAnnounceNotifyEntity) obj;
				NotifyReceiveVO recVO = new NotifyReceiveVO();
				BeanUtils.copyProperties(recVO, entity);
				// 添加通告的阅读状态
				recVO.setReadState(map.get(entity.getId()).getState());
				recVOs.add(recVO);
			}
			// 按照阅读排序
			if ("readState".equals(sortField)) {
				if ("desc".equals(dataGrid.getOrder())) {
					Collections.sort(recVOs, new Comparator<NotifyReceiveVO>() {

						@Override
						public int compare(NotifyReceiveVO o1, NotifyReceiveVO o2) {
							//先根据阅读状态去比较，如果相同根据发送时间排序
							return o2.getReadState().compareTo(o1.getReadState()) == 0
									? o2.getSendTime().compareTo(o1.getSendTime())
									: o2.getReadState().compareTo(o1.getReadState());
						}

					});
				}else {
					Collections.sort(recVOs, new Comparator<NotifyReceiveVO>() {

						@Override
						public int compare(NotifyReceiveVO o1, NotifyReceiveVO o2) {
							//先根据阅读状态去比较，如果相同根据发送时间排序
							return o2.getReadState().compareTo(o1.getReadState()) == 0
									? o2.getSendTime().compareTo(o1.getSendTime())
									: o1.getReadState().compareTo(o2.getReadState());
						}

					});
				}
				
			}
			dataGrid.setResults(recVOs);
		} catch (Exception e) {
			logger.error("属性转换错误" + e);
		}

		TagUtil.datagrid(response, dataGrid);
	}
}
