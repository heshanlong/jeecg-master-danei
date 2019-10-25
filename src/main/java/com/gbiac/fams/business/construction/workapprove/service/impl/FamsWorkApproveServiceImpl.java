package com.gbiac.fams.business.construction.workapprove.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MD5Util;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.construction.service.FamsWorkServiceI;
import com.gbiac.fams.business.construction.workapprove.controller.FamsWorkApproveController;
import com.gbiac.fams.business.construction.workapprove.dao.FamsWorkApproveDao;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveExportEntity;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveHistoryEntity;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveServiceI;
import com.gbiac.fams.business.construction.workappstate.service.FamsWorkAppStateServiceI;
import com.gbiac.fams.business.construction.workcheck.dao.FamsWorkCheckDao;
import com.gbiac.fams.business.construction.workrole.service.FamsWorkRoleServiceI;
import com.gbiac.fams.business.construction.worksafe.entity.FamsWorkSafePersonEntity;
import com.gbiac.fams.business.construction.worksafe.service.FamsWorkSafePersonServiceI;
import com.gbiac.fams.business.construction.workunioncheck.dao.FamsWorkUnionCheckDao;
import com.gbiac.fams.business.construction.workvindicatecheck.dao.FamsWorkVindicateCheckDao;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.Entity.PageEntity;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.BMPConstant;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.map.entity.FamsCommonMapEntity;

@Service("famsWorkApproveService")
@Transactional
public class FamsWorkApproveServiceImpl extends CommonServiceImpl implements FamsWorkApproveServiceI {
	@Autowired
	private FamsWorkApproveController famsWorkApproveController;
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private FamsWorkSafePersonServiceI famsWorkSafePersonService;
	@Autowired
	private FamsWorkRoleServiceI famsWorkRoleService;
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	SystemService systemService;
	@Autowired
	private FamsWorkApproveDao famsWorkApproveDao;
	@Autowired
	private FamsWorkServiceI famsWorkService;
	@Autowired
	private FamsWorkAppStateServiceI famsWorkAppStateService;
	@Autowired
	private FamsWorkCheckDao famsWorkCheckDao;
	@Autowired
	private FamsWorkUnionCheckDao famsWorkUnionCheckDao;
	@Autowired
	private FamsWorkVindicateCheckDao famsWorkVindicateCheckDao;

	public void delete(FamsWorkApproveEntity entity) throws Exception {
		// 判断该施工是否已离场，离场的数据才可以删除
		if (!this.isFinish(entity.getProcinstId())) {
			throw new BusinessException("不能删除未离场的数据");
		}
		super.delete(entity);
		// 删除附件记录
		famsCommonService.deleteByProperty(FamsCommonFileEntity.class, "businessId".split(","),
				entity.getId().split(","));
		// 删除地图标记记录
		famsCommonService.deleteByProperty(FamsCommonMapEntity.class, "businessId".split(","),
				entity.getId().split(","));
		// 删除安全员记录
		famsCommonService.deleteByProperty(FamsWorkSafePersonEntity.class, "approveId".split(","),
				entity.getId().split(","));
		// 删除备份历史记录
		famsCommonService.deleteByProperty(FamsWorkApproveHistoryEntity.class, "bid".split(","),
				entity.getId().split(","));
		// 删除流程实例
		/*
		 * if(entity.getProcinstId()!=null&&!entity.getProcinstId().equals("")){
		 * processEngine.getRuntimeService().deleteProcessInstance(entity.getProcinstId(
		 * ), "删除记录和流程！"); }
		 */
	}

	/**
	 * 通过流程id查询该记录是否已离场
	 * 
	 * @param procinstId
	 * @return
	 */
	private boolean isFinish(String procinstId) {
		Map m = famsWorkApproveDao.getExecutionById(procinstId);
		return m == null ? true : false;
	}

	public Serializable save(FamsWorkApproveEntity entity) throws Exception {
		// 设置编号
		entity.setNumber(systemService.getFamsNumberByTSTypegroup("施工管理", FamsWorkApproveEntity.class, "createDate"));
		Serializable t = super.save(entity);
		return t;
	}

	@Override
	public Serializable save(FamsWorkApproveEntity entity, Map map) throws Exception {
		// 设置创建日期,防止重新提交时时间不更新
		entity.setCreateDate(new Date());
		// 设置编号
		entity.setNumber(systemService.getFamsNumberByTSTypegroup("施工管理", FamsWorkApproveEntity.class, "createDate"));
		// 设置项目唯一编号
		entity.setWorkNameOnly(entity.getWorkNameOnly() == null ? MD5Util.MD5Encode(new Date().toString(), "UTF-8")
				: entity.getWorkNameOnly());
		// 设置是否第一次提交标志
		entity.setIsFirst(entity.getIsFirst() == null ? "Y" : "N");
		// 开始工作流
//		String processId=startProcess(processEngine,entity.getIsContinueWork());
		String processId = startProcess(processEngine, entity.getIsContinueWork(), entity.getWorkTypeId());
		entity.setProcinstId(processId);
		Serializable t = super.save(entity);
		String workAffectAreaMap = (String) map.get("workAffectAreaMap");
		String[] workSafePerson_roles = (String[]) map.get("workSafePerson_roles");
		String[] workSafePerson_names = (String[]) map.get("workSafePerson_names");
		String[] workSafePerson_phones = (String[]) map.get("workSafePerson_phones");
		String[] workSafePerson_intercoms = (String[]) map.get("workSafePerson_intercoms");
		String files = (String) map.get("files");
		if (workAffectAreaMap != null) {
			// 保存地图
			famsCommonService.saveMap("work", t.toString(), workAffectAreaMap, null);
		}
		if (workSafePerson_roles.length > 0) {
			// 保存人员列表
			for (int i = 0; i < workSafePerson_roles.length; i++) {
				if (!workSafePerson_roles[i].equals("") || !workSafePerson_names[i].equals("")
						|| !workSafePerson_phones[i].equals(""))
					famsWorkSafePersonService.save(new FamsWorkSafePersonEntity(t.toString(), workSafePerson_roles[i],
							workSafePerson_names[i], workSafePerson_phones[i], workSafePerson_intercoms[i]));
			}
		}
		if (files != null && files != "") {
			// 如果文件对象在数据库中已经有业务对应了，则将该数据进行备份
			List<FamsCommonFileEntity> famsCommonFileEntitys = famsCommonService
					.getEntitiesByProperty(FamsCommonFileEntity.class, "id".split(","), files.split(";"));
			for (FamsCommonFileEntity f : famsCommonFileEntitys) {
				if ((f.getBusinessId() != null && !f.getBusinessId().equals(""))
						|| (f.getModule() != null && !f.getModule().equals(""))) {
					this.save(new FamsCommonFileEntity(f));
				}
			}
			// 更新附件归属
			famsCommonService.updateMBSofFileByStr(files, "work", t.toString(), MessageConstant.STATEBEFORE);
		}
		return t;
	}

	@Override
	public Serializable save(FamsWorkApproveEntity entity, List<FamsWorkSafePersonEntity> safePersons)
			throws Exception {
		// 设置创建日期,防止重新提交时时间不更新
		entity.setCreateDate(new Date());
		// 设置编号
		entity.setNumber(systemService.getFamsNumberByTSTypegroup("施工管理", FamsWorkApproveEntity.class, "createDate"));
		// 设置项目唯一编号
		entity.setWorkNameOnly(entity.getWorkNameOnly() == null ? MD5Util.MD5Encode(new Date().toString(), "UTF-8")
				: entity.getWorkNameOnly());
		// 设置是否第一次提交标志
		entity.setIsFirst(entity.getIsFirst() == null ? "Y" : "N");
		// 开始工作流
		// String processId=startProcess(processEngine,entity.getIsContinueWork());
		String processId = startProcess(processEngine, entity.getIsContinueWork(), entity.getWorkTypeId());
		entity.setProcinstId(processId);
		Serializable t = super.save(entity);
		String workAffectAreaMap = entity.getWorkAffectAreaMap();

		if (workAffectAreaMap != null) {
			// 保存地图
			famsCommonService.saveMap("work", t.toString(), workAffectAreaMap, null);
		}
		if (safePersons.size() > 0) {
			// 保存人员列表
			for (int i = 0; i < safePersons.size(); i++) {
				safePersons.get(i).setApproveId(t.toString());
				famsWorkSafePersonService.save(safePersons.get(i));
			}
		}
		return t;
	}

	/**
	 * 开始流程
	 * 
	 * @param processEngine
	 * @return 返回流程实例ID
	 */
	private String startProcess(ProcessEngine processEngine, String isContinueWork) {
		String key = isContinueWork.equals("Y") ? BMPConstant.CONTINUEWORKPROCESS : BMPConstant.WORKPROCESS;
		ProcessInstance pi = processEngine.getRuntimeService()// 与正在执行的流程实例和执行对象相关的Service
				.startProcessInstanceByKey(key);// 使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
		System.out.println("流程实例ID:" + pi.getId());// 流程实例ID 101
		System.out.println("流程定义ID:" + pi.getProcessDefinitionId());// 流程定义ID helloworld:1:4
		return pi.getId();
	}

	/**
	 * 对开始流程进行修改，添加24小时不停航作业审批流程
	 * 
	 * @param processEnging
	 * @param isContinueWork
	 * @param workTypeStr
	 */
	private String startProcess(ProcessEngine processEngine, String isContinueWork, String workTypeId) {
		String key = null;
		if ("Y".equals(isContinueWork)) {
			key = BMPConstant.CONTINUEWORKPROCESS;
		} else {

			if (this.isContinue24NoStopWork(workTypeId)) {
				key = BMPConstant.CONTINUE24NOSTOPWORK;
			} else {
				key = BMPConstant.WORKPROCESS;
			}
		}
		ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(key);
		return pi.getId();

	}

	public void saveOrUpdate(FamsWorkApproveEntity entity) throws Exception {
		super.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdate(FamsWorkApproveEntity entity, Map map) throws Exception {
		// 判断流程节点是否和指定节点相同，防止两个人重复操作
		if (famsWorkService.sameTaskKey(entity.getId(), "a-approve-apply")
				|| famsWorkService.sameTaskKey(entity.getId(), "y-approve-apply")) {
			FamsWorkApproveEntity oldEntity = this.get(FamsWorkApproveEntity.class, entity.getId());
			// 记录流程进度
			famsCommonService.saveProgress("work", entity.getId(), null, "重新编辑", null, null, "y-approve-apply");
			// 保存原有数据
			systemService.save(new FamsWorkApproveHistoryEntity("施工单位重新编辑", oldEntity));
		}
		super.saveOrUpdate(entity);
		String workAffectAreaMap = (String) map.get("workAffectAreaMap");
		String[] workSafePerson_roles = (String[]) map.get("workSafePerson_roles");
		String[] workSafePerson_names = (String[]) map.get("workSafePerson_names");
		String[] workSafePerson_phones = (String[]) map.get("workSafePerson_phones");
		String[] workSafePerson_intercoms = (String[]) map.get("workSafePerson_intercoms");
		String files = (String) map.get("files");
		// 删除原有地图
		famsCommonService.deleteByProperty(FamsCommonMapEntity.class, "businessId".split(","),
				entity.getId().split(","));
		// 删除原有人员列表
		famsCommonService.deleteByProperty(FamsWorkSafePersonEntity.class, "approveId".split(","),
				entity.getId().split(","));
		if (workAffectAreaMap != null) {
			// 保存地图
			famsCommonService.saveMap("work", entity.getId(), workAffectAreaMap, null);
		}
		if (workSafePerson_roles.length > 0) {
			// 保存人员列表
			for (int i = 0; i < workSafePerson_roles.length; i++) {
				if (!workSafePerson_roles[i].equals("") || !workSafePerson_names[i].equals("")
						|| !workSafePerson_phones[i].equals(""))
					famsWorkSafePersonService.save(new FamsWorkSafePersonEntity(entity.getId(), workSafePerson_roles[i],
							workSafePerson_names[i], workSafePerson_phones[i], workSafePerson_intercoms[i]));
			}
		}
		if (files != null) {
			// 更新附件归属
			famsCommonService.updateMBSofFileByStr(files, "work", entity.getId(), MessageConstant.STATEBEFORE);
		}
	}

	@Override
	public void saveOrUpdate(FamsWorkApproveEntity entity, List<FamsWorkSafePersonEntity> safePersons)
			throws Exception {
		// 判断流程节点是否和指定节点相同，防止两个人重复操作
		if (famsWorkService.sameTaskKey(entity.getId(), "a-approve-apply")
				|| famsWorkService.sameTaskKey(entity.getId(), "y-approve-apply")) {
			FamsWorkApproveEntity oldEntity = this.get(FamsWorkApproveEntity.class, entity.getId());
			// 记录流程进度
			famsCommonService.saveProgress("work", entity.getId(), null, "重新编辑", null, null, "y-approve-apply");
			// 保存原有数据
			systemService.save(new FamsWorkApproveHistoryEntity("施工单位重新编辑", oldEntity));
		}
		super.saveOrUpdate(entity);
		// 删除原有地图
		famsCommonService.deleteByProperty(FamsCommonMapEntity.class, "businessId".split(","),
				entity.getId().split(","));
		// 删除原有人员列表
		famsCommonService.deleteByProperty(FamsWorkSafePersonEntity.class, "approveId".split(","),
				entity.getId().split(","));
		if (safePersons.size() > 0) {
			// 保存人员列表
			for (int i = 0; i < safePersons.size(); i++) {
				safePersons.get(i).setApproveId(entity.getId());
				famsWorkSafePersonService.save(safePersons.get(i));
			}
		}
	}

	@Override
	public void filterData(CriteriaQuery cq) {
		// 获取当前用户拥有的流程实例id数组
		List<String> executionIds = famsWorkApproveDao.getActRuTaskExecutionIds(Util.getPcOrAppUserId());
		// 施工单位拥有所有数据的参看权限
		String roleCode = famsWorkRoleService.getRoleCodeByUserId(Util.getPcOrAppUserId());
		if ("shigong".equals(roleCode)) {// 施工单位
			// 施工单位只能查看自己单位或下属单位的记录
			cq.eq("sysOrgCode", Util.getPcOrAppSysOrgCode());
		} else {// 非施工单位才有条件筛选
				// 如果没有任何权限，加入随机字段以解决为空查询出所有数据的问题
			if (executionIds.size() == 0) {
				executionIds.add("#####");
			}
			cq.in("procinstId", executionIds.toArray());
		}
	}

	@Override
	public void setNodeInfoForApply(DataGrid dataGrid) {
		List<FamsWorkApproveEntity> famsWorkApproveEntitys = dataGrid.getResults();
		if (famsWorkApproveEntitys != null && famsWorkApproveEntitys.size() > 0) {
			// 获取施工单位对指定数据的操作权限
			List<Map> results = famsWorkApproveDao.getPcDataOfNodeInfo(famsWorkApproveEntitys, "apply");
			for (FamsWorkApproveEntity f : famsWorkApproveEntitys) {
				for (Map m : results) {
					if (f.getId().equals(m.get("id"))) {
						f.setNodeInfo(m);
					}
				}
			}
		}
	}

	@Override
	public void setNodeInfoForApprove(DataGrid dataGrid) {
		List<FamsWorkApproveEntity> famsWorkApproveEntitys = dataGrid.getResults();
		if (famsWorkApproveEntitys != null && famsWorkApproveEntitys.size() > 0) {
			// 获取审批单位对指定数据的操作权限
			List<Map> results = famsWorkApproveDao.getPcDataOfNodeInfo(famsWorkApproveEntitys, "approve");
			for (FamsWorkApproveEntity f : famsWorkApproveEntitys) {
				for (Map m : results) {
					if (f.getId().equals(m.get("id"))) {
						f.setNodeInfo(m);
					}
				}
			}
		}
	}

	@Override
	public List getProcess(String id) {
		List<Map> process = famsWorkApproveDao.getProcess(id);
		// 判断该流程是否已结束
		Map execution = famsWorkApproveDao.getExecutions(id);
		boolean isFinish = false;
		if (execution == null) {// 流程已结束
			isFinish = true;
		}
		for (Map m : process) {
			m.put("isFinish", isFinish);
		}
		return process;
	}

	@Override
	public void createViewBMP(HttpServletResponse resp) throws IOException {
		ServletOutputStream outputStream = resp.getOutputStream();
		List<ProcessDefinition> processDefinitions = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
				.createProcessDefinitionQuery()// 创建一个流程定义的查询
				/** 指定查询条件,where条件 */
				.processDefinitionKey("workProcess")// 使用流程定义的key查询
				/** 排序 */
				.orderByProcessDefinitionVersion().desc()// 按照版本的降序排列
				/** 返回的结果集 */
				.listPage(0, 1);// 分页查询
		if (processDefinitions != null && processDefinitions.size() > 0) {
			ProcessDefinition processDefinition = processDefinitions.get(0);
			// 获取图片的输入流
			InputStream in = processEngine.getRepositoryService()//
					.getResourceAsStream(processDefinition.getDeploymentId(),
							processDefinition.getDiagramResourceName());
			byte[] bs = new byte[1024];
			int len = 0;
			while ((len = in.read(bs)) != -1) {
				outputStream.write(bs);
			}
			outputStream.close();
			in.close();
		}
	}

	/**
	 * 需要进行测试
	 */
	@Override
	public void getViewBMP(HttpServletResponse resp, String processType) throws IOException {
		ServletOutputStream outputStream = resp.getOutputStream();
		InputStream in = this.getClass().getResourceAsStream("/diagrams/" + processType + ".png");
		byte[] bs = new byte[8192];
		int len = 0;
		while ((len = in.read(bs)) != -1)
			outputStream.write(bs);
		outputStream.close();
		in.close();
	}

	@Override
	public List bsSuggest(String workType, String keyword, Integer maxNum) {
		return famsWorkApproveDao.bsSuggest(workType, keyword, maxNum);
	}

	@Override
	public List searchWorkingList(Integer workFlag, String searchInput, Integer workingState, PageEntity page) {
		// 用户id
		String userId = null;
		// 施工角色code
		String roleCode = null;
		// 拥有的流程节点
		String flag = null;
		// 查询是否拥有申请权限
		List<Map> applyFunctions = famsWorkApproveDao.getApplyFunctions(Util.getPcOrAppUserId());
		boolean canApply = applyFunctions.size() > 0 ? true : false;
		// 查询是否拥有审批权限
		List<Map> approveFunctions = famsWorkApproveDao.getApproveFunctions(Util.getPcOrAppUserId());
		boolean canApprove = approveFunctions.size() > 0 ? true : false;
		// 查询是否拥有部门审批的权限
		List<Map> departApproveFunctions = famsWorkApproveDao.getdepartApproveFunctions(Util.getPcOrAppUserId());
		boolean canDepartApprove = departApproveFunctions.size() > 0 ? true : false;
		// 查询是否拥有检查权限
		List<Map> checkFunctions = famsWorkApproveDao.getCheckFunctions(Util.getPcOrAppUserId());
		boolean canCheck = checkFunctions.size() > 0 ? true : false;
		// 通过权限列表给用户赋予相关权限
		if (canApply && canApprove && canCheck) {
			roleCode = "applyApproveCheck";
			if (0 == workFlag) {
				userId = Util.getPcOrAppUserId();
			}
		} else if (canApply && canApprove) {
			roleCode = "applyApprove";
			if (0 == workFlag) {
				userId = Util.getPcOrAppUserId();
			}
		} else if (canApprove && canCheck) {
			roleCode = "approveCheck";
		} else if (canApply && canCheck) {
			roleCode = "applyCheck";
			if (0 == workFlag) {
				userId = Util.getPcOrAppUserId();
			}
		} else if (canApply) {
			roleCode = "apply";
			if (0 == workFlag) {
				userId = Util.getPcOrAppUserId();
			}
		} else if (canApprove) {
			roleCode = "approve";
		} else if (canCheck) {
			roleCode = "check";
		}
		if (roleCode == null)
			return null;
		// 获取该用户对应的标识
		flag = famsWorkAppStateService.getNodesByRoleCodeAndState(roleCode, workFlag, workingState);
		if(canDepartApprove)
			flag = flag+",s-department-apply";
		List<Map> list = famsWorkApproveDao.getWorkingList(searchInput, workingState, userId, flag.split(","), page);
		for (Map m : list) {
			m.put("roleCode", roleCode);
		}
		return list;
	}

	@Override
	public Map getWorkingDetail(String workingItemID, String roleCode, Integer workFlag, Integer workingState) {
		Map map = new HashMap();
		if (roleCode == null) {// 角色为null，自动查询该角色
			// 查询是否拥有申请权限
			List<Map> applyFunctions = famsWorkApproveDao.getApplyFunctions(Util.getPcOrAppUserId());
			boolean canApply = applyFunctions.size() > 0 ? true : false;
			// 查询是否拥有审批权限
			List<Map> approveFunctions = famsWorkApproveDao.getApproveFunctions(Util.getPcOrAppUserId());
			boolean canApprove = approveFunctions.size() > 0 ? true : false;
			// 查询是否拥有检查权限
			List<Map> checkFunctions = famsWorkApproveDao.getCheckFunctions(Util.getPcOrAppUserId());
			boolean canCheck = checkFunctions.size() > 0 ? true : false;
			// 通过权限列表给用户赋予相关权限
			if (canApply && canApprove && canCheck) {
				roleCode = "applyApproveCheck";
			} else if (canApply && canApprove) {
				roleCode = "applyApprove";
			} else if (canApprove && canCheck) {
				roleCode = "approveCheck";
			} else if (canApply && canCheck) {
				roleCode = "applyCheck";
			} else if (canApply) {
				roleCode = "apply";
			} else if (canApprove) {
				roleCode = "approve";
			} else if (canCheck) {
				roleCode = "check";
			}
		}
		if (roleCode == null)
			return null;
		map.put("roleCode", roleCode);
		map.put("workFlag", workFlag);
		map.put("workingState", workingState);
		FamsWorkApproveEntity famsWorkApprove = new FamsWorkApproveEntity(workingItemID);
		famsWorkApproveController.getEntityDetail(famsWorkApprove, null, map);
		return map;
	}

	@Override
	public List getAllWorkingAreaLocation() {
		List list = famsWorkApproveDao.getAllWorkingAreaLocation();
		return list;
	}

	@Override
	public Map getSingleWorkingAreaLocation(String workingItemID) {
		Map map = famsWorkApproveDao.getSingleWorkingAreaLocation(workingItemID);
		return map;
	}

	@Override
	public Map getCheckFunctions(String applyId, String userId) {
		Map map = new HashMap();
		map.put("check", false);
		map.put("unionCheck", false);
		map.put("vindicateCheck", false);
		List<Map> checkFunctions = famsWorkApproveDao.getCheckFunctions(userId);
		// 例行检查
		String checkId = famsWorkCheckDao.getHaveCheckId(applyId);
		// 联合检查
		String unionCheckId = famsWorkUnionCheckDao.getHaveUnionCheckId(applyId);
		// 维护抽查
		String vindicateCheckId = famsWorkVindicateCheckDao.getWorkVindicateCheckId(applyId);
		for (Map m : checkFunctions) {
			if ("例行检查".equals(m.get("functionname"))) {
				map.put("check", true);
				map.put("haveCheck", checkId != null ? true : false);
			}
			if ("联合检查".equals(m.get("functionname"))) {
				map.put("unionCheck", true);
				map.put("haveUnionCheck", unionCheckId != null ? true : false);
			}
			if ("维护检查".equals(m.get("functionname"))) {
				map.put("vindicateCheck", true);
				map.put("haveVindicateCheck", vindicateCheckId != null ? true : false);
			}
		}
		return map;
	}

	@Override
	public Map getApproveInfo(String workingItemID) {
		Map map = famsWorkApproveDao.getApproveInfo(workingItemID);
		return map;
	}

	@Override
	public void updateTaskKey(String id) {
		String taskKey = famsWorkApproveDao.getTaskKeyByApproveId(id);
		taskKey = taskKey == null ? "end" : taskKey;
		famsWorkApproveDao.updateTaskKey(id, taskKey);
	}
	

	@Override
	public void updateTaskKeyAndIsFirst(String id) {
		this.updateTaskKey(id);
		famsWorkApproveDao.updateIsFirst(id);
	}

	@Override
	public void updateTaskKey(String id, String taskKey) {
		famsWorkApproveDao.updateTaskKey(id, taskKey);
	}
	
	@Override
	public void updateDateIn(String id, Date dateIn) {
		famsWorkApproveDao.updateDateIn(id, dateIn);
	}

	@Override
	public List getCheckInfo(String id) {
		List list = new ArrayList();
		// 例行检查
		Map check = famsWorkCheckDao.getCheckInfo(id);
		// 联合检查
		Map unionCheck = famsWorkUnionCheckDao.getCheckInfo(id);
		// 维护抽查
		Map vindicateCheck = famsWorkVindicateCheckDao.getCheckInfo(id);
		if (vindicateCheck != null) {
			Map map = new HashMap();
			map.put("name", "维护抽查");
			map.put("funname", "vindicateCheck");
			map.put("value", vindicateCheck);
			list.add(map);
		}
		if (unionCheck != null) {
			Map map = new HashMap();
			map.put("name", "联合检查");
			map.put("funname", "unionCheck");
			map.put("value", unionCheck);
			list.add(map);
		}
		if (check != null) {
			Map map = new HashMap();
			map.put("name", "例行检查");
			map.put("funname", "check");
			map.put("value", check);
			list.add(map);
		}
		return list;
	}

	@Override
	public List<Map> getYWorkInfo() {
		return famsWorkApproveDao.getYWorkInfo();
	}

	@Override
	public List<Map> getSWorkInfo() {
		String userId = Util.getPcOrAppUserId();
		return famsWorkApproveDao.getSWorkInfo(userId);
	}

	@Override
	public int getNeedApproveApplyNum() {
		List<Map> list = famsWorkApproveDao.getNeedApproveApplyList();
		return list.size();
	}

	@Override
	public int getNeedApproveInNum() {
		List<Map> list = famsWorkApproveDao.getNeedApproveInList();
		return list.size();
	}

	@Override
	public int getNeedApproveOutNum() {
		List<Map> list = famsWorkApproveDao.getNeedApproveOutList();
		return list.size();
	}

	@Override
	public List<FamsWorkApproveExportEntity> getDataByApplyType(String applyType) {
		List<FamsWorkApproveExportEntity> list = famsWorkApproveDao.getDataByApplyType(applyType);
		return list;
	}

	@Override
	public Map getDepartMap() {
		List<Map> list = famsWorkApproveDao.getDepartMap();
		Map map = new HashMap();
		for (Map m : list) {
			map.put(m.get("id"), m.get("name"));
		}
		return map;
	}

	@Override
	public Map getWorkTypeMap() {
		List<Map> list = famsWorkApproveDao.getWorkTypeMap();
		Map map = new HashMap();
		for (Map m : list) {
			map.put(m.get("id"), m.get("name"));
		}
		return map;
	}

	@Override
	public Map getApplyTypeMap() {
		List<Map> list = famsWorkApproveDao.getApplyTypeMap();
		Map map = new HashMap();
		for (Map m : list) {
			map.put(m.get("code"), m.get("name"));
		}
		return map;
	}

	@Override
	public Map getStakKeyMap() {
		List<Map> list = famsWorkApproveDao.getStakKeyMap();
		Map map = new HashMap();
		for (Map m : list) {
			map.put(m.get("keyname"), m.get("name"));
		}
		return map;
	}

	@Override
	public boolean ifWorkDateExpire(String id) {
		return famsWorkApproveDao.getDateEnd(id).before(new Date()) ? true : false;
	}

	@Override
	public Integer updatePushUserId(String approveId, String userId) {
		return famsWorkApproveDao.updatePushUserId(approveId, userId);
	}

	@Override
	public boolean isContinue24NoStopWork(String workTypeId) {
		return "不停航作业".equals(famsWorkApproveDao.getTypeNameById(workTypeId));
	}

	@Override
	public List getMyWorkByCreateMan(String createName, Integer page, Integer pageSize) {
		return famsWorkApproveDao.getMyWorkByCreateMan(createName, page, pageSize);
	}

	@Override
	public Boolean deleteByApproveId(String approveId) {
		return famsWorkApproveDao.deleteByApproveId(approveId) > 0 ? true : false;
	}

	@Override
	public List<?> getMyWorkByWorkState(String createName, Integer workState, String searchInput, Integer page,
			Integer pageSize) {

		// 0. 进行中：待进场>进行中，根据时间正序排列
		// 1. 待审批：部门待审批>待审批>待进场确认>离场待确认， 按提交时间顺序显示
		// 2. 已离场：离场时间倒序显示

		String inStr = null;
		switch (workState) {
		case 0:
			inStr = "('s-in','s-out')";
			break;
		case 1:
			inStr = "('s-department-apply','y-approve-apply','y-approve-in','y-approve-out')";
			break;
		case 2:
			inStr = "('leave','end')";
			break;
		default:
			inStr = "";
		}

		if (inStr != null && inStr != "") {
			if (searchInput != null && searchInput != "")
				return famsWorkApproveDao.getMyWorkByWorkStateAndSearchInput(createName, inStr, searchInput, page,
						pageSize);
			else
				return famsWorkApproveDao.getMyWorkByWorkState(createName, inStr, page, pageSize);
		}

		return null;
	}

	@Override
	public List<?> getmyWorkByStateEqApplyAndCreateName(String createName, String searchInput, Integer page,
			Integer pageSize) {
		if (searchInput != null && searchInput != "")
			return famsWorkApproveDao.getmyWorkByStateEqApplyAndCreateNameAndSearchInput(createName, searchInput, page,
					pageSize);
		else
			return famsWorkApproveDao.getmyWorkByStateEqApplyAndCreateName(createName, page, pageSize);
	}
}