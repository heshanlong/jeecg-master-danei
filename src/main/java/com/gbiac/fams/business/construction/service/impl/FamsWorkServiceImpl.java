package com.gbiac.fams.business.construction.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.construction.dao.FamsWorkDao;
import com.gbiac.fams.business.construction.service.FamsWorkServiceI;
import com.gbiac.fams.business.construction.workapprove.controller.FamsWorkApproveController;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveNoPassEntity;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveNoPassServiceI;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveServiceI;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.progress.entity.FamsCommonProgressEntity;
import com.gbiac.fams.common.progress.service.FamsCommonProgressServiceI;

@Service("famsWorkService")
@Transactional
public class FamsWorkServiceImpl extends CommonServiceImpl implements FamsWorkServiceI {
	@Autowired
	private FamsWorkApproveServiceI famsWorkApproveService;
	@Autowired
	private FamsWorkDao famsWorkDao;
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private FamsWorkApproveController famsWorkApproveController;
	@Autowired
	private FamsCommonProgressServiceI famsCommonProgressService;
	@Autowired
	private FamsWorkApproveNoPassServiceI famsWorkApproveNoPassService;

	@Override
	public void completeTask(String procinstId, Map map) {
		// 根据流程实例id获取流程节点id
		String taskId = famsWorkDao.getTaskIdByProId(procinstId);
		if (taskId != null) {
			processEngine.getTaskService()// 与正在执行的任务管理相关的Service
					.complete(taskId, map);
		}
	}

	@Override
	public void completeTaskByApproveId(String id, Map map) {
		// 获取施工申请对象
		FamsWorkApproveEntity famsWorkApproveEntity = famsWorkApproveService.get(FamsWorkApproveEntity.class, id);
		if (famsWorkApproveEntity != null && famsWorkApproveEntity.getProcinstId() != null
				&& !famsWorkApproveEntity.getProcinstId().equals("")) {
			// 通过流程实例id完成该节点流程
			completeTask(famsWorkApproveEntity.getProcinstId(), map);
		}
	}

	@Override
	public Boolean sameTaskKey(String approveId, String taskKey) {
		// 通过施工申请id获取流程任务key
		String key = famsWorkDao.getTaskKeyByBusId(approveId);
		if (key != null && key.equals(taskKey)) {
			return true;
		}
		return false;
	}

	@Override
	public AjaxJson s_apply_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) throws Exception {
		// 更新数据
		AjaxJson ajaxJson = famsWorkApproveController.doUpdate(famsWorkApprove, request);
		if (ajaxJson.isSuccess()) {

			// 获取该用户部门下审核人的数量
			Map<String, Object> map = new HashMap<>();
			map.put("recall", "N");
			String result = null;
			// 判断是否申请人部门是否存在审核人，通过申请人所在的部门获取对应的审核人id
			List<String> userIds1 = famsCommonService.getCanApproveIdsByOrgCode(Util.getPcOrAppSysOrgCode());
			if (userIds1 != null && !userIds1.isEmpty()) {
				result = "Y";
			} else {
				result = "N";
			}
			map.put("result", result);
			completeTaskByApproveId(famsWorkApprove.getId(), map);
			// 记录流程进度
			famsCommonService.saveProgress("work", famsWorkApprove.getId(), null, "提交施工申请", null, null, "s-apply");
		}
		return ajaxJson;
	}

	@Override
	public void s_apply_fun(String approveId) throws Exception {
		// 获取该用户部门下审核人的数量
		Map<String, Object> map = new HashMap<>();
		map.put("recall", "N");
		String result = null;
		// 判断是否申请人部门是否存在审核人，通过申请人所在的部门获取对应的审核人id
		List<String> userIds1 = famsCommonService.getCanApproveIdsByOrgCode(Util.getPcOrAppSysOrgCode());
		if (userIds1 != null && !userIds1.isEmpty()) {
			result = "Y";
		} else {
			result = "N";
		}
		map.put("result", result);
		completeTaskByApproveId(approveId, map);
		// 记录流程进度
		famsCommonService.saveProgress("work", approveId, null, "提交施工申请", null, null, "s-apply");
	}

	@Override
	public void a_approve_apply_fun(String id, String approveState, String approveStateNote) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if ("Y".equals(approveState)) {
			map.put("result", "Y");
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "审批通过了施工申请", approveStateNote, null,
					"a-approve-apply");
		}
		if ("N".equals(approveState)) {
			map.put("result", "N");
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "审批未通过该施工申请", approveStateNote, null,
					"a-approve-apply");
		}
		// 工作流完成当前节点
		completeTaskByApproveId(id, map);
	}

	@Override
	public void y_approve_out_fun(String id, String approveState, String approveStateNote) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if ("Y".equals(approveState)) {
			map.put("result", "Y");
			// 判断是否施工到期
			if (famsWorkApproveService.ifWorkDateExpire(id)) {
				// 到期的话，完成
				map.put("arraytime", "Y");
				// 记录流程进度
				famsCommonService.saveProgress("work", id, approveState, "同意离场", approveStateNote, null,
						"y-approve-out");
			} else {
				// 没到期的话，回到申请节点
				map.put("arraytime", "N");
				// 记录流程进度
				famsCommonService.saveProgress("work", id, null, "同意离场", approveStateNote, null, "y-approve-out");
			}
		}
		if ("N".equals(approveState)) {
			map.put("result", "N");
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "不同意离场", approveStateNote, null, "y-approve-out");
		}
		// 工作流完成当前节点
		completeTaskByApproveId(id, map);
	}

	@Override
	public void j_unioncheck_fun(String id, String approveState, String approveStateNote) throws Exception {
		if ("Y".equals(approveState)) {
			// 检查通过才可以进行下一个工作流完成当前节点
			completeTaskByApproveId(id, null);
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "联合检查通过", approveStateNote, null, "j-unioncheck");
		}
		if ("N".equals(approveState)) {
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "联合检查不通过", approveStateNote, null, "j-unioncheck");
		}
	}

	@Override
	public void s_out_fun(String id, String approveStateNote) throws Exception {
		// 获取施工类型对象
		FamsWorkApproveEntity famsWorkApproveEntity = famsWorkApproveService.get(FamsWorkApproveEntity.class, id);
		//8/21修改
		// 工作流完成当前节点
		Map<String,Object> variables = new HashMap<>();
		variables.put("timeout", "N");
		completeTaskByApproveId(id, variables);
		// 记录流程进度
		if ("Y".equals(famsWorkApproveEntity.getIsContinueWork())) {// 连续施工
			famsCommonService.saveProgress("work", id, null, "提交了完成施工", approveStateNote, null, "s-out");
		} else {
			famsCommonService.saveProgress("work", id, null, "提交了离场申请", approveStateNote, null, "s-out");
		}
	}

	@Override
	public void j_spotcheck_fun(String id, String approveState, String approveStateNote) throws Exception {
		if ("Y".equals(approveState)) {
			// 检查通过才可以进行下一个工作流完成当前节点
			completeTaskByApproveId(id, null);
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "抽查通过", approveStateNote, null, "j-spotcheck");
		}
		if ("N".equals(approveState)) {
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "抽查不通过", approveStateNote, null, "j-spotcheck");
		}
	}

	@Override
	public Integer y_approve_apply_fun(String id, String approveState, String approveStateNote, String applyType)
			throws Exception {
		FamsWorkApproveEntity entity = famsCommonService.get(FamsWorkApproveEntity.class, id);
		// 此处需要两运控两个人审批才能通过
		Map<String, String> map = new HashMap();
		map.put("recall", "N");
		// 判断该用户是否之前审批过
		FamsCommonProgressEntity famsCommonProgressEntitys = famsCommonProgressService.getRecentEntityByBid(id,
				"y-approve-apply");
		int count = 0;
		if (famsCommonProgressEntitys == null) {// 没有人审批过
			if ("Y".equals(approveState)) {
				// 记录流程进度
				famsCommonService.saveProgress("work", id, approveState, "审批通过该施工申请", approveStateNote, null,
						"y-approve-apply");
			}
			if ("N".equals(approveState)) {
				applyType = null;
				map.put("result", "N");
				// 记录流程进度
				famsCommonService.saveProgress("work", id, approveState, "审批未通过该施工申请", approveStateNote, null,
						"y-approve-apply");
				// 工作流完成当前节点
				completeTaskByApproveId(id, map);
				/**
				 * 	运行控制部审批未通过，获取信息，进行登记
				 */
				this.saveNoPassApprove(entity);
			}
			count = 1;
		} else {// 已经有人审批了
			if (famsCommonProgressEntitys.getOptionUserId().equals(Util.getPcOrAppUserId())) {
				throw new BusinessException("同一个用户不能审批两次！");
			}
			// 如果审批通过了
			if ("Y".equals(approveState)) {
				//8/21修改
				// 判断是否是为，非24小时作业中的不停航作业审批流程
				if (!"Y".equals(entity.getIsContinueWork())) {
					// 判断那时候是初次申请
					if ("Y".equals(entity.getIsFirst())) {
						map.put("firstApply", "Y");
					} else {
						map.put("firstApply", "N");
					}
				}
				if ("N".equals(famsCommonProgressEntitys.getOptionFlag())) {
					throw new BusinessException("不能审批已不通过的申请！");
				}
				map.put("result", "Y");
				// 记录流程进度
				famsCommonService.saveProgress("work", id, approveState, "审批通过该施工申请", approveStateNote, null,
						"y-approve-apply");
				// 工作流完成当前节点
				completeTaskByApproveId(id, map);
				//保存未通过的状态
				this.saveNoPassApprove(entity);

			}
			if ("N".equals(approveState)) {
				applyType = null;
				map.put("result", "N");
				// 记录流程进度
				famsCommonService.saveProgress("work", id, approveState, "审批未通过该施工申请", approveStateNote, null,
						"y-approve-apply");
				// 工作流完成当前节点
				completeTaskByApproveId(id, map);
			}
			count = 2;
		}
		// 更新审批类型
		entity.setApplyType(applyType);
		famsCommonService.saveOrUpdate(entity);
		return count;
	}

	@Override
	public void s_department_apply_fun(String id, String approveState, String approveStateNote, String applyType)
			throws Exception {
		FamsWorkApproveEntity entity = famsCommonService.get(FamsWorkApproveEntity.class, id);

		Map<String, String> map = new HashMap();
		map.put("recall", "N");
		// 获取该用户所在的部门是否有审核人

		List<String> list = famsCommonService.getCanApproveIdsByOrgCode(Util.getPcOrAppSysOrgCode());
		if (list != null && !list.isEmpty()) {
			// 判断该用户是否之前审批过
			FamsCommonProgressEntity famsCommonProgressEntitys = famsCommonProgressService.getRecentEntityByBid(id,
					"s-department-apply");
			if ("Y".equals(approveState)) {
				map.put("result", "Y");
				// 记录流程进度
				famsCommonService.saveProgress("work", id, approveState, "部门审批通过该施工申请", approveStateNote, null,
						"s-department-apply");
			}
			if ("N".equals(approveState)) {
				applyType = null;
				map.put("result", "N");
				// 记录流程进度
				famsCommonService.saveProgress("work", id, approveState, "部门审批未通过该施工申请", approveStateNote, null,
						"s-department-apply");
			}
		}
		// 工作流完成当前节点
		completeTaskByApproveId(id, map);
		// 更新审批类型
		entity.setApplyType(applyType);
		famsCommonService.saveOrUpdate(entity);

	}

	@Override
	public void s_in_fun(String id) throws Exception {
		// 工作流完成当前节点
		completeTaskByApproveId(id, null);
		// 记录流程进度
		famsCommonService.saveProgress("work", id, null, "提交了施工进场申请", null, null, "s-in");
	}

	@Override
	public void y_approve_in_fun(String id, String approveState, String approveStateNote) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("recall", "N");
		if ("Y".equals(approveState)) {
			map.put("result", "Y");
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "审批通过了施工进场申请", approveStateNote, null,
					"y-approve-in");
		}
		if ("N".equals(approveState)) {
			map.put("result", "N");
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "审批未通过施工进场申请", approveStateNote, null,
					"y-approve-in");
		}
		// 工作流完成当前节点
		completeTaskByApproveId(id, map);
	}

	@Override
	public void j_check_fun(String id, String approveState, String approveStateNote) throws Exception {
		if ("Y".equals(approveState)) {
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "例行检查通过", approveStateNote, null, "j-check");
			// 检查通过才可以进行下一个工作流完成当前节点
			completeTaskByApproveId(id, null);
		}
		if ("N".equals(approveState)) {
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "例行检查不通过", approveStateNote, null, "j-check");
		}
	}

	@Override
	public void s_undo_fun(String id, String approveStateNote) throws Exception {
		// 判断该申请能否被撤回，处于施工前的节点可以撤回
		if (!this.isCanUndo(id)) {
			throw new BusinessException("该申请无法被撤回！");
		}
		FamsWorkApproveEntity entity = famsCommonService.get(FamsWorkApproveEntity.class, id);
		// 查询要跳转的节点
		HistoricTaskInstance hisTask = null;
		List<HistoricTaskInstance> hisTasks = processEngine.getHistoryService().createHistoricTaskInstanceQuery()
				.executionId(entity.getProcinstId()).orderByHistoricTaskInstanceStartTime().desc().list();
		if(hisTasks!=null && hisTasks.size()>0) {
			hisTask = hisTasks.get(0);
		}else {
			throw new BusinessException("该流程由于已经终止了!");
		}
		// 进而获取流程实例
		ProcessInstance instance = processEngine.getRuntimeService().createProcessInstanceQuery()
				.processInstanceId(hisTask.getProcessInstanceId()).singleResult();
		if (instance == null)
			throw new BusinessException("不能撤回已完成的记录！");
		// 取得流程定义
		// ProcessDefinitionEntity definition = (ProcessDefinitionEntity)
		// processEngine
		// .getRepositoryService().getProcessDefinition(
		// hisTask.getProcessDefinitionId());
		// // 获取历史任务的Activity
		// ActivityImpl hisActivity = definition.findActivity(hisTask
		// .getTaskDefinitionKey());
		// processEngine.getManagementService().executeCommand(new
		// JumpCmd(instance.getId(), hisActivity.getId()));

		// 执行撤回操作
		Map<String, Object> map = new HashMap<>();
		map.put("recall", "Y");
		processEngine.getTaskService().complete(hisTask.getId(), map);
		// 记录流程进度，撤回之后会回到申请页面
		famsCommonService.saveProgress("work", id, null, "申请撤回", approveStateNote, null, "s-apply");
		// famsCommonService.saveProgress("work",id,null,"申请撤回",approveStateNote,null,"s-undo");
	}

	/**
	 * 是否可以被撤回 false 不能 true 能
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean isCanUndo(String id) {
		FamsWorkApproveEntity entity = famsCommonService.get(FamsWorkApproveEntity.class, id);
		if (entity.getTaskKey() != null && ("s-undo".equals(entity.getTaskKey())
				|| "s-apply".equals(entity.getTaskKey()) || "s-out".equals(entity.getTaskKey()) || 
				"y-approve-out".equals(entity.getTaskKey()))) {
			//|| "y-approve-in".equals(entity.getTaskKey())
			return false;
		} else {
			Map map = famsWorkDao.getWorkApprove(id);
			return map != null ? true : false;
		}
	}

	@Override
	public void y_approve_undo_fun(String id, String approveState, String approveStateNote) throws Exception {
		if ("Y".equals(approveState)) {
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "撤回通过", approveStateNote, null, "y-approve-undo");
		}
		if ("N".equals(approveState)) {
			// 记录流程进度
			famsCommonService.saveProgress("work", id, approveState, "撤回不通过", approveStateNote, null, "y-approve-undo");
		}
		// 完成该节点
		completeTaskByApproveId(id, null);
	}

	@Override
	public void s_finish_fun(String id) throws Exception {
		// 记录流程进度
		famsCommonService.saveProgress("work", id, null, "施工单位完成施工", null, null, "s-finish");
		// 完成该节点
		completeTaskByApproveId(id, null);
	}

	/**
	 * 8/21修改
	 */
	@Override
	public void autoFinish() throws Exception {
		/**
		 * 自动提交任务节点：
		 * 1. 24小时作业的话当天未进行提交的，到了晚上12点自动提交完成任务节点；
		 * 2. 非24小时作业的话，当天未进行提交的，到了晚上12点自动提交；如果提交日期不是作业的最后一天的话，显示已离场，作业单位可以继续申请入场；如果是最后一天的话，显示完成作业，作业单位不能申请入场。
		 * 3. 24小时不停航作业的话，当天未进行提交的话，到了晚上12点自动提交，显示未已离场，第二天依旧可以申请进场；如果提交的话，显示完成任务。
		 * 
		 */
		List<FamsWorkApproveEntity> entitys = famsWorkDao.getNodeIdEqS_OUTProIds();
		Map<String,Object> variables = new HashMap<>();
		//设置是否超时
		variables.put("timeout", "Y");
		String state = "end";
		String taskKey = "end";
		for(FamsWorkApproveEntity entity: entitys){
			//获取节点是否为非24小时作业
			String isContinueWork = entity.getIsContinueWork();
			if("Y".equals(isContinueWork)){
				//连续作业，即非24小时作业
				//判断施工是否到期,判断当前时间的日期是否大于等于作业结束日期，如果等于就说明是当天结束
				Date nowDate = new Date();
				//当前时间超过了预期时间，显示完成任务
				entity.setWorkEndTime(nowDate);
				if(nowDate.getTime()>=entity.getDateEnd().getTime()){
					//作业到期
					variables.put("arraytime", "Y");
					taskKey = "end";
				}else{
					variables.put("arraytime", "N");
					taskKey = "leave";
					state = "leave";
				}
			}else{
				if(famsWorkApproveService.isContinue24NoStopWork(entity.getWorkTypeId())){
					//如果是24小时不停航作业的话，设置状态为待提交
					taskKey = "leave";
					state = "leave";
				}else{
					taskKey = "end";
				}
			}
			entity.setTaskKey(taskKey);
			famsCommonService.saveOrUpdate(entity);
			//记录流程进度
			famsCommonService.saveProgress(null,null,"work",entity.getId(),null,"自动完成作业","系统自动提交并审批完成作业",null,state);
			//完成该节点
			completeTaskByApproveId(entity.getId(),variables);
            //更新节点信息
//            famsWorkApproveService.updateTaskKey(entity.getId(),taskKey);
		}
	}
	
	/**
	 * 保存没有通过的审批
	 * @param entity
	 * @throws Exception 
	 */
	private void saveNoPassApprove(FamsWorkApproveEntity entity) throws Exception {
		FamsWorkApproveNoPassEntity famsWorkApproveNoPassEntity = new FamsWorkApproveNoPassEntity(entity);
		famsWorkApproveNoPassService.save(famsWorkApproveNoPassEntity);
	}
}
