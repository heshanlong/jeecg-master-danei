package com.gbiac.fams.business.construction.workapprove.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.DepartService;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.gbiac.fams.business.construction.service.FamsWorkServiceI;
import com.gbiac.fams.business.construction.workapprove.dao.FamsWorkApproveDao;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveExportEntity;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveServiceI;
import com.gbiac.fams.business.construction.worknodeinfo.service.FamsWorkNodeInfoServiceI;
import com.gbiac.fams.business.construction.worksafe.entity.FamsWorkSafePersonEntity;
import com.gbiac.fams.business.construction.worksafe.service.FamsWorkSafePersonServiceI;
import com.gbiac.fams.business.construction.worktype.entity.FamsWorkTypeEntity;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.MessageService;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.map.entity.FamsCommonMapEntity;

import io.swagger.annotations.ApiOperation;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**   
 * @Title: Controller  
 * @Description: 施工申请
 * @author 龚道海
 * @date 2019-01-22 15:04:11
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsWorkApproveController")
public class FamsWorkApproveController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsWorkApproveController.class);

	@Autowired
	private FamsWorkApproveServiceI famsWorkApproveService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private FamsWorkSafePersonServiceI famsWorkSafePersonService;
	@Autowired
	private FamsWorkServiceI famsWorkService;
	@Autowired
	private FamsWorkApproveDao famsWorkApproveDao;
	@Autowired
	private FamsWorkNodeInfoServiceI famsWorkNodeInfoService;
	@Autowired
	protected MessageService messageService;
	@Autowired
	private DepartService departService;
	/**
	 * 施工 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		//获取需要跳转的页面
		String toPage = request.getParameter("toPage");
		return new ModelAndView("com/gbiac/fams/business/construction/workapprove/famsWork"+toPage);
	}

	//施工申请 页面跳转
	@RequestMapping(params = "applyPage")
	public ModelAndView applyPage(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/construction/workapprove/famsWorkApply");
	}
	//施工审批 页面跳转
	@RequestMapping(params = "approvePage")
	public ModelAndView approvePage(HttpServletRequest request) {
		//获取需要跳转的页面
		return new ModelAndView("com/gbiac/fams/business/construction/workapprove/famsWorkApprove");
	}
	@RequestMapping(params = "approvePage1")
	public ModelAndView approvePage1(HttpServletRequest request) {
		//获取需要跳转的页面
		return new ModelAndView("com/gbiac/fams/business/construction/workapprove/famsWorkApprove1");
	}
	//施工管理 页面跳转
	@RequestMapping(params = "managerPage")
	public ModelAndView managerPage(HttpServletRequest request) {
		//获取需要跳转的页面
		return new ModelAndView("com/gbiac/fams/business/construction/workapprove/famsWorkManager");
	}

	/**
	 * easyui AJAX请求数据
	 * 申请
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */

	@RequestMapping(params = "apply")
	public void apply(FamsWorkApproveEntity famsWorkApprove,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkApproveEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkApprove, request.getParameterMap());
		try{
			//自定义追加查询条件
			
			cq.eq("sysOrgCode", Util.getSysOrgCode());
			
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsWorkApproveService.getDataGridReturn(cq, true);
		//根据查询出来的数据设置每一条数据与之关联的节点信息
		famsWorkApproveService.setNodeInfoForApply(dataGrid);
		TagUtil.datagrid(response, dataGrid);
		
	}

	/**
	 * easyui AJAX请求数据
	 * 审批
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */
	@RequestMapping(params = "approve")
	public void approve(FamsWorkApproveEntity famsWorkApprove,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkApproveEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkApprove, request.getParameterMap());
		try{
			//自定义追加查询条件
			//只能看到需要审批的记录
			cq.in("taskKey","y-approve-apply,y-approve-out,y-approve-in".split(","));
			//判断用户是否为指定部门下
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsWorkApproveService.getDataGridReturn(cq, true);
		//根据查询出来的数据设置每一条数据与之关联的节点信息
		famsWorkApproveService.setNodeInfoForApprove(dataGrid);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * easyui AJAX请求数据
	 * 部门负责人审批
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */
	@RequestMapping(params = "approve1")
	public void approve1(FamsWorkApproveEntity famsWorkApprove,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkApproveEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkApprove, request.getParameterMap());
		try{
			//自定义追加查询条件
			//只能看到需要审批的记录
			cq.in("taskKey","s-department-apply".split(","));
			
			//只显示本部门需要审核的申请单
			System.err.println("####################### "+Util.getPcOrAppSysOrgCode()+" #######################");
			//获取角色是admin的用户ID
			List<String> adminUserIds = famsCommonService.getAdminUsers();
			if(!adminUserIds.contains(Util.getUserId())){
				System.err.println("#### 当前登录的用户不属于管理员 ########");
				cq.eq("sysOrgCode", Util.getPcOrAppSysOrgCode());
			}else{
				System.err.println("#### 当前登录的用户属于管理员 ########");
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsWorkApproveService.getDataGridReturn(cq, true);
		//根据查询出来的数据设置每一条数据与之关联的节点信息
		famsWorkApproveService.setNodeInfoForApprove(dataGrid);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * easyui AJAX请求数据
	 * 管理
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */
	@RequestMapping(params = "manager")
	public void manager(FamsWorkApproveEntity famsWorkApprove,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkApproveEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkApprove, request.getParameterMap());
		try{
			//自定义追加查询条件
			//不能看到待提交的记录
			cq.notEq("taskKey","s-apply");
			cq.notEq("taskKey","s-undo");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsWorkApproveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除施工申请
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsWorkApprove = systemService.getEntity(FamsWorkApproveEntity.class, famsWorkApprove.getId());
		message = "施工申请删除成功";
		try{
			famsWorkApproveService.delete(famsWorkApprove);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "施工申请删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除施工申请
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "施工申请删除成功";
		try{
			for(String id:ids.split(",")){
				FamsWorkApproveEntity famsWorkApprove = systemService.getEntity(FamsWorkApproveEntity.class,id);
				famsWorkApproveService.delete(famsWorkApprove);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "施工申请删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加施工申请
	 * 
	 * @param
	 * @return
	 */
	 @RequestMapping(params = "doAdd")
	 @ResponseBody
	 public AjaxJson doAdd(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
		 String message = null;
		 AjaxJson j = new AjaxJson();
		 message = "施工申请添加成功";
		 try{
			 //判断前端传递过来的时间是否正确
			 if(!this.checkTime(famsWorkApprove,j)){
				 j.setSuccess(false);
				 return j;
			 }
			 //是否从暂存按钮过来的
			 String submit = request.getParameter("comfrom");
			 //将前端传递过来的参数组装成maprams(request);
			 Map map=getParams(request);
			 if(map!=null) {
				 //保存施工申请并开始工作流并创建动态跟踪记录
	 				Serializable t = famsWorkApproveService.save(famsWorkApprove, map);
	 				if(submit!=null&&"submit".equals(submit)){//需要直接提交申请,即完成该节点
	 					Map<String,Object> subMap = new HashMap<>();
	 					subMap.put("recall", "N");
	 					String result = null;
	 					//判断是否申请人部门是否存在审核人，通过申请人所在的部门获取对应的审核人id
	 		        	List<String> userIds1 = famsCommonService.getCanApproveIdsByOrgCode(Util.getPcOrAppSysOrgCode());
	 					if(userIds1!=null && !userIds1.isEmpty()){
	 						result = "Y";
	 					}else{
	 						result = "N";
	 					}
	 					subMap.put("result", result);
	 					famsWorkService.completeTaskByApproveId(t.toString(),subMap);
	 					//记录流程进度
	 					famsCommonService.saveProgress("work",t.toString(),null,"提交施工申请", null,null,"s-apply");
	 					//更新提交人信息
	 					famsWorkApproveService.updatePushUserId(famsWorkApprove.getId(),Util.getPcOrAppUserId());
	 					 //入场时间置空指
	 		            famsWorkApproveService.updateDateIn(famsWorkApprove.getId(), null);
	 					new Thread(){
	 						@Override
	 						public void run() {
	 							try {
	 								 //推送
	 	                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkApprove.getId());
	 	                            //获取角色下的所有人员id
	 	                            /**
	 	                             * 获取运输控制部所有有权限的用户
	 	                             * List<String> userIds=famsCommonService.getCanApproveIds();
	 	                             * 修改为：如果用户所在的部门有审核人的话，发送给审核人；如果没有审核人的话，发送给运输控制部所有有权限的人
	 	                             */
	 	                            List<String> userIds = null;
	 	                            List<String> userIds1 = famsCommonService.getCanApproveIdsByOrgCode(Util.getPcOrAppSysOrgCode());
	 	                            if(userIds1 != null && !userIds1.isEmpty()){
	 	                            	userIds = userIds1;
	 	                            }else{
	 	                            	//获取运行控制部所有有权限的数据
	 	                            	userIds = famsCommonService.getCanApproveIdsByRunPartOrgId();
	 	                            }
	 	                            //判断是否创建人为申请人
	 	                            if(!entity.getCreateUserId().equals(Util.getPcOrAppUserId())){
	 	                            	//通知创建人，你的消息已经被某人提交
	 	                            	 List<TSBaseUser> userPushMan = famsCommonService.getApplyUser(entity.getCreateUserId());
	 	                            	 messageService.pushOnlyPCMessage("work",entity.getId(),"您创建的"+entity.getWorkName()+"申请已经被提交",Util.getPcOrAppUserName()+"提交了您创建的作业申请，如有问题请与提交人进行联系。",Util.getPcOrAppUserName(),userPushMan,null,null);
	 	                            	 famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),userPushMan,"您创建的"+entity.getWorkName()+"申请已经被提交",Util.getPcOrAppUserName()+"提交了您创建的作业申请，如有问题请与提交人进行联系。",null);
	 	                            }
	 	                            
	 	                            //发送系统消息
	 	                            messageService.pushOnlyPCMessage("work",entity.getId(),"您有新的"+entity.getWorkName()+"申请待审批",Util.getPcOrAppUserName()+"提交了一条施工申请，请您及时审批。",Util.getPcOrAppUserName(),userIds,null,null,null);
	 	                            //推送消息
	 	                            famsCommonService.sendMessage(Util.getPcOrAppUserId(),"work",entity.getId(),userIds,"您有新的"+entity.getWorkName()+"申请待审批",Util.getPcOrAppUserName()+"提交了一条施工申请，请您及时审批。",null);
	 	                        }catch (Exception e){
	 							}
	 						}
	 					}.run();
	 				}
	 				//更新施工申请的流程状态
	 				famsWorkApproveService.updateTaskKey(t.toString());
	 				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
	 			}else{
	 				message = MessageConstant.ERRORARGS;
	 			}
	 		}catch(Exception e){
	 			e.printStackTrace();
	 			message = "施工申请添加失败";
	 			throw new BusinessException(e.getMessage());
	 		}
	 		j.setMsg(message);
	 		System.err.println("###########添加逻辑的输出到页面的内容："+j.getJsonStr()+"########");
	 		return j;
	 	}

	/**
	 * 检查前端传递过来的时间是否正确
	 * @param famsWorkApprove
	 * @return
	 */
	private boolean checkTime(FamsWorkApproveEntity famsWorkApprove,AjaxJson j) {
		String isUseFire = famsWorkApprove.getIsUseFire();
		Date dateStart = famsWorkApprove.getDateStart();
		Date dateEnd = famsWorkApprove.getDateEnd();
		Date workStartTime = famsWorkApprove.getWorkStartTime();
		Date workEndTime = famsWorkApprove.getWorkEndTime();
		Date useFireStartTime = famsWorkApprove.getUseFireStartTime();
		Date useFireEndTime = famsWorkApprove.getUseFireEndTime();
		if(workStartTime!=null&&workEndTime!=null){
			if(workStartTime.getTime()>=workEndTime.getTime()){
				j.setMsg("作业开始时间必须小于作业结束时间！");
				return false;
			}
			if(dateEnd.getTime()>(dateStart.getTime()+6l*24*60*60*1000)) {
				j.setMsg("最多允许申请一周的作业计划，请修改！");
				return false;
			}
			if(isUseFire!=null&&"Y".equals(isUseFire)){
				if(useFireStartTime!=null&&useFireEndTime!=null){
					if(useFireStartTime.getTime()>=useFireEndTime.getTime()){
						j.setMsg("动火开始时间必须小于动火结束时间！");
						return false;
					}
					if(useFireStartTime.getTime()<workStartTime.getTime()||useFireEndTime.getTime()>workEndTime.getTime()){
						j.setMsg("动火开始时间和动火结束时间必须在施工时间区间内！");
						return false;
					}
				}else{
					j.setMsg("动火开始时间必须小于动火结束时间！");
					return false;
				}
			}
		}else{
			j.setMsg("作业开始时间和结束时间不能为空！");
			return false;
		}
		return true;
	}

	/**
	 * 更新施工申请
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		
		message = "施工申请更新成功";
		FamsWorkApproveEntity t = famsWorkApproveService.get(FamsWorkApproveEntity.class, famsWorkApprove.getId());
		try {
			//将前端传递过来的参数组装成maprams(request);
			Map map=getParams(request);
			if(map!=null) {
				MyBeanUtils.copyBeanNotNull2Bean(famsWorkApprove, t);
				//添加对时间的修改操作，判断对应的时间是否一致，如果不一致就进行修改更新；
				t.setDateStart(famsWorkApprove.getDateStart());
				t.setDateEnd(famsWorkApprove.getDateEnd());
				t.setWorkStartTime(famsWorkApprove.getWorkStartTime());
				t.setWorkEndTime(famsWorkApprove.getWorkEndTime());
				t.setUseFireStartTime(famsWorkApprove.getUseFireStartTime());
				t.setUseFireEndTime(famsWorkApprove.getUseFireEndTime());
				
				famsWorkApproveService.saveOrUpdate(t,map);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}else{
				message = MessageConstant.ERRORARGS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "施工申请更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 施工申请新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsWorkApprove.getId())) {
			famsWorkApprove = famsWorkApproveService.getEntity(FamsWorkApproveEntity.class, famsWorkApprove.getId());
			req.setAttribute("famsWorkApprove", famsWorkApprove);
		}else {
			//获取用户所属的部门信息
			//获取施工申报单位名称
			TSDepart tDepart = departService.getDepartByorgCode(Util.getPcOrAppSysOrgCode());
			famsWorkApprove.setWorkApproveDepart(tDepart.getId());
			famsWorkApprove.setWorkApproveDepartStr(tDepart.getDepartname());
			req.setAttribute("famsWorkApprove", famsWorkApprove);
		}
		return new ModelAndView("com/gbiac/fams/business/construction/workapprove/famsWorkApprove-add");
	}
	/**
	 * 施工申请编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest req) {
		getEntityDetail(famsWorkApprove,req,null);
		return new ModelAndView("com/gbiac/fams/business/construction/workapprove/famsWorkApprove-update");
	}

	/**
	 * 重新提交施工申请编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdateForApplyAgain")
	public ModelAndView goUpdateForApplyAgain(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest req) {
		getEntityDetail(famsWorkApprove,req,null);
		//将施工时间和动火时间加一天
		/*
		famsWorkApprove= (FamsWorkApproveEntity) req.getAttribute("famsWorkApprove");
		famsWorkApprove.setWorkStartTime(famsWorkApprove.getWorkStartTime()!=null?Util.getNextDate(famsWorkApprove.getWorkStartTime()):null);
		famsWorkApprove.setWorkEndTime(famsWorkApprove.getWorkEndTime()!=null?Util.getNextDate(famsWorkApprove.getWorkEndTime()):null);
		famsWorkApprove.setUseFireStartTime(famsWorkApprove.getUseFireStartTime()!=null?Util.getNextDate(famsWorkApprove.getUseFireStartTime()):null);
		famsWorkApprove.setUseFireEndTime(famsWorkApprove.getUseFireEndTime()!=null?Util.getNextDate(famsWorkApprove.getUseFireEndTime()):null);
		*/
		return new ModelAndView("com/gbiac/fams/business/construction/workapprove/famsWorkApprove-updateForApplyAgain");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsWorkApproveController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsWorkApproveExportEntity famsWorkApprove, HttpServletRequest request, HttpServletResponse response
			, DataGrid dataGrid, ModelMap modelMap) {
		famsWorkApprove.setId("");
		//导出类型
		String applyType = request.getParameter("exportType");
		//获取部门map
		Map departMap=famsWorkApproveService.getDepartMap();
		//获取施工类型
		Map workTypeMap=famsWorkApproveService.getWorkTypeMap();
		//获取审批类型
		Map applyTypeMap=famsWorkApproveService.getApplyTypeMap();
		//获取状态类型
		Map stakKeyMap=famsWorkApproveService.getStakKeyMap();
		if("all".equals(applyType)){
			CriteriaQuery cq = new CriteriaQuery(FamsWorkApproveEntity.class, dataGrid);
			//不能看到待提交的记录
			cq.notEq("taskKey","s-apply");
			cq.notEq("taskKey","s-undo");
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkApprove, request.getParameterMap());
			List<FamsWorkApproveEntity> famsWorkApproves = this.famsWorkApproveService.getListByCriteriaQuery(cq,false);
			for(FamsWorkApproveEntity f:famsWorkApproves){
				f.setWorkApproveDepart(departMap.get(f.getWorkApproveDepart())==null?"":departMap.get(f.getWorkApproveDepart()).toString());
				f.setWorkTypeId(workTypeMap.get(f.getWorkTypeId()).toString()==null?"":workTypeMap.get(f.getWorkTypeId()).toString().toLowerCase());
				f.setApplyType(applyTypeMap.get(f.getApplyType())==null?"":applyTypeMap.get(f.getApplyType()).toString());
				f.setTaskKey(stakKeyMap.get(f.getTaskKey())==null?"":stakKeyMap.get(f.getTaskKey()).toString());
			}
			modelMap.put(NormalExcelConstants.FILE_NAME,"施工申请");
			modelMap.put(NormalExcelConstants.CLASS,FamsWorkApproveEntity.class);
			StringBuffer space=new StringBuffer("");
			for(int i=0;i<10;i++){
				space.append(" ");
			}
			modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("施工申请列表", "导出人:"+ResourceUtil.getSessionUser().getRealName()+space+"导出时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
					"导出信息"));
			modelMap.put(NormalExcelConstants.DATA_LIST,famsWorkApproves);
			return NormalExcelConstants.JEECG_EXCEL_VIEW;
		}else{
			
			String fileName=new SimpleDateFormat("yyyy年MM月dd日").format(Util.getNextDate(new Date()))+"飞管部（"+("apply".equals(applyType)?"审批类":"报备类")+"）施工和维护作业申请表";
			List<FamsWorkApproveExportEntity> famsWorkApproves = this.famsWorkApproveService.getDataByApplyType(applyType);
			int num=1;
			for(FamsWorkApproveExportEntity f:famsWorkApproves){
				System.out.println("作业类型："+f.getWorkTypeId()+f.getWorkTypeStr());
				f.setArea("飞行区 ");
				f.setNumberContent(""+num++);
				f.setWorkAffectArea(("".equals(f.getWorkAffectArea())||f.getWorkAffectArea()==null)?"无影响区域":f.getWorkAffectArea());
				f.setWork_time(new SimpleDateFormat("HH:mm").format(f.getWorkStartTime())+"-"+new SimpleDateFormat("HH:mm").format(f.getWorkEndTime())+("Y".equals(f.getIsContinueWork())?"\n(24小时施工)":""));
				String workNote=f.getWorkNote();//备注
				String isUseFire = f.getIsUseFire();//是否动火
				String isHigh = f.getIsHigh();//是否高空
				String workTypeId = f.getWorkTypeId();//施工类型
				if("Y".equals(isUseFire)){
					workNote=("".equals(workNote)||workNote==null)?"":workNote+"\n";
					workNote=workNote+"动火时间\n";
					workNote=workNote+new SimpleDateFormat("HH:mm").format(f.getUseFireStartTime())+"-"+new SimpleDateFormat("HH:mm").format(f.getUseFireEndTime())+"\n";
				}
				if("Y".equals(isHigh)){
					workNote=("".equals(workNote)||workNote==null)?"":workNote+"\n";
					workNote=workNote+"高空作业\n";
				}
				//不停航施工id
				if("8a0790e9684a3d5301684a64bde60001".equals(workTypeId)){
					workNote=("".equals(workNote)||workNote==null)?"":workNote+"\n";
					workNote=workNote+"不停航施工";
				}
				f.setWorkNote(("".equals(workNote)||workNote==null)?"/":workNote);
			}
			modelMap.put(NormalExcelConstants.FILE_NAME,fileName);
			modelMap.put(NormalExcelConstants.CLASS,FamsWorkApproveExportEntity.class);
			StringBuffer space=new StringBuffer("");
			for(int i=0;i<20;i++){
				space.append(" ");
			}
			modelMap.put(NormalExcelConstants.PARAMS,new ExportParams(fileName, "报备单位:飞行区管理部"+space.toString()+"报备日期:"+new SimpleDateFormat("yyyy.MM.dd").format(new Date()),
					"导出信息"));
			modelMap.put(NormalExcelConstants.DATA_LIST,famsWorkApproves);
			
			return NormalExcelConstants.JEECG_EXCEL_VIEW;
		}
	}
	
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsWorkApproveEntity famsWorkApprove,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"施工申请");
    	modelMap.put(NormalExcelConstants.CLASS,FamsWorkApproveEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("施工申请列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<FamsWorkApproveEntity> listFamsWorkApproveEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsWorkApproveEntity.class,params);
				for (FamsWorkApproveEntity famsWorkApprove : listFamsWorkApproveEntitys) {
					famsWorkApproveService.save(famsWorkApprove);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}

	/**
	 * 施工申请流程页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "jumpBMPPage")
	public ModelAndView jumpBMPPage(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest req) {
		getEntityDetail(famsWorkApprove,req,null);
		req.setAttribute("flag", req.getParameter("flag"));
		req.setAttribute("funname", req.getParameter("funname"));
		req.setAttribute("taskkey", req.getParameter("taskkey"));
		req.setAttribute("jumpurl", req.getParameter("jumpurl"));
		req.setAttribute("taskname", req.getParameter("taskname"));
		return new ModelAndView("com/gbiac/fams/business/construction/workapprove/"+req.getParameter("jumpurl"));
	}

	/**
	 * 跳转流程图预览页面
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "viewBMP")
	public ModelAndView viewBMP(HttpServletRequest req) {
		String isContinueWork = req.getParameter("isContinueWork");
		String workTypeId= req.getParameter("workTypeId");
		
		//判断如果连续作业就返回连续作业的流程图，如果不是就返回非连续作业的流程图
		if("Y".equals(isContinueWork)){
			return new ModelAndView("com/gbiac/fams/business/construction/workapprove/viewContinueWorkBMP");
		}else{
			if(famsWorkApproveService.isContinue24NoStopWork(workTypeId)){
				return new ModelAndView("com/gbiac/fams/business/construction/workapprove/viewContinue24NoStopWork");
			}else{
				return new ModelAndView("com/gbiac/fams/business/construction/workapprove/viewWorkBMP");
			}
		}
	}

	/**
	 * 8/11: 不进行使用
	 * 自动生成流程图
	 * @param resp
	 * @return
	 */
	@RequestMapping(params = "createViewBMP")
	public void createViewBMP(HttpServletResponse resp) {
		try {
			famsWorkApproveService.createViewBMP(resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连续工作的流程图
	 * @param resp
	 */
	@RequestMapping(params = "viewContinueWorkBMP")
	public void viewContinueWorkBMP(HttpServletResponse resp){
		try{
			famsWorkApproveService.getViewBMP(resp, "continueWorkProcess");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取非连续工作的流程图
	 * @param resp
	 */
	@RequestMapping(params = "viewWorkBMP")
	public void viewWorkContine(HttpServletResponse resp){
		try{
			famsWorkApproveService.getViewBMP(resp, "workProcess");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取24小时不停航作业的流程图
	 */
	@RequestMapping(params="viewContinue24NoStopWork")
	public void viewContinue24NoStopWork(HttpServletResponse resp){
		try{
			famsWorkApproveService.getViewBMP(resp,"continue24NoStopWork");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取实体详情
	 * @param famsWorkApprove
	 * @param req
	 */
	public void getEntityDetail(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest req,Map map){
		if (StringUtil.isNotEmpty(famsWorkApprove.getId())) {
			famsWorkApprove = famsWorkApproveService.getEntity(FamsWorkApproveEntity.class, famsWorkApprove.getId());
			//获取施工申报单位名称
			TSDepart tsDepart = famsCommonService.get(TSDepart.class, famsWorkApprove.getWorkApproveDepart());
			//获取地图点位信息
			List<FamsCommonMapEntity> maps = famsCommonService.getCommonObject(FamsCommonMapEntity.class, null, famsWorkApprove.getId(), null, null);
			//获取值班及安全员信息
			List<FamsWorkSafePersonEntity> workSafes = famsWorkSafePersonService.findByProperty(FamsWorkSafePersonEntity.class, "approveId", famsWorkApprove.getId());
			//获取附件id集合
			List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsWorkApprove.getId(), null, null);
			//获取历史动态
			List process=famsWorkApproveService.getProcess(famsWorkApprove.getId());
			//获取检查记录信息
			List checkInfo=famsWorkApproveService.getCheckInfo(famsWorkApprove.getId());
			
			//获取该申请状态
			String state=famsWorkNodeInfoService.getStateByTaskKey(famsWorkApprove.getTaskKey());
			if(tsDepart!=null)
				famsWorkApprove.setWorkApproveDepartStr(tsDepart.getDepartname());
			if(req!=null){
				req.setAttribute("tsDepart", tsDepart);
				req.setAttribute("famsWorkApprove", famsWorkApprove);
				req.setAttribute("maps", Util.mapsToMap(maps));
				req.setAttribute("workSafes", workSafes);
				req.setAttribute("files", Util.filesToMap(files));
				req.setAttribute("process", process);
				req.setAttribute("checkInfo", checkInfo);
				req.setAttribute("state", state);
			}
			if(map!=null){
				//设置部门中文名
				famsWorkApprove.setWorkApproveDepartStr(tsDepart.getDepartname());
				System.err.println("workApproveDepartStr:"+famsWorkApprove.getWorkApproveDepartStr());
				//查询权限
				List<FamsWorkApproveEntity> list=new ArrayList();
				list.add(famsWorkApprove);
				//根据用户id获取用户对指定数据的操作权限
				List<Map> results=famsWorkApproveDao.getAppDataOfNodeInfo(list,map.get("roleCode").toString(),map.get("workFlag").toString(),map.get("workingState").toString());
				Map m=results.size()>0?results.get(0):null;
				famsWorkApprove.setNodeInfo(m);
				//获取施工类型
				FamsWorkTypeEntity workTypeEntity = famsCommonService.getEntityByProperty(FamsWorkTypeEntity.class, "id".split(","), famsWorkApprove.getWorkTypeId().split(","));
				famsWorkApprove.setWorkTypeStr(workTypeEntity.getTypeName());
				//查询是否拥有例行、联合或抽查的权限，
				Map canCheckList=famsWorkApproveService.getCheckFunctions(famsWorkApprove.getId(),Util.getPcOrAppUserId());
				map.put("canCheckList", canCheckList);
				map.put("famsWorkApprove", famsWorkApprove);
				map.put("maps", Util.mapsToMap(maps));
				map.put("workSafes", workSafes);
				map.put("files", files);
				map.put("process", process);
				map.put("checkInfo", checkInfo);
				map.put("state", state);
			}
		}
	}

	/**
	 * 将前端传递过来的参数组装成map
	 * @author 龚道海
	 * @param request
	 * @return
	 */
	public Map getParams(HttpServletRequest request) {
		//获取地图点位信息
		String workAffectAreaMap = request.getParameter("workAffectAreaMap");
		//获取值班及安全员信息
		String[] workSafePerson_roles = request.getParameterValues("workSafePerson_role");
		String[] workSafePerson_names = request.getParameterValues("workSafePerson_name");
		String[] workSafePerson_phones = request.getParameterValues("workSafePerson_phone");
		String[] workSafePerson_intercoms = request.getParameterValues("workSafePerson_intercom");
		//获取附件id集合
		String files = request.getParameter("files");
		if(workSafePerson_roles.length==workSafePerson_names.length&&workSafePerson_names.length==workSafePerson_phones.length) {
			Map map = new HashMap();
			map.put("workAffectAreaMap", workAffectAreaMap);
			map.put("workSafePerson_roles", workSafePerson_roles);
			map.put("workSafePerson_names", workSafePerson_names);
			map.put("workSafePerson_phones", workSafePerson_phones);
			map.put("workSafePerson_intercoms", workSafePerson_intercoms);
			map.put("files", files);
			return map;
		}
		return null;
	}

	/**
	 * 下拉菜单数据接口
	 * @param request
	 * @param resp
	 * @param workType 施工类型
	 * @param keyword 模糊值
	 * @param maxNum 展示的条数
	 * @return
	 */
	@RequestMapping(params = "bsSuggest", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="通用下拉菜单数据接口")
	public Object bsSuggest(HttpServletRequest request, HttpServletResponse resp,String workType,String keyword,Integer maxNum){
		maxNum=maxNum==null?20:maxNum;
		Map object = new HashMap();
		List data=famsWorkApproveService.bsSuggest(workType,keyword,maxNum);
		object.put("message", "");
		object.put("value", data);
		object.put("code", 200);
		object.put("redirect", "");
		return object;
	}

	/**
	 * 根据id查询该记录是否可以进行撤回操作
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "canUndo")
	@ResponseBody
	public AjaxJson canUndo(String id) {
		AjaxJson j = new AjaxJson();
		try{
			if(!famsWorkService.isCanUndo(id)){
				j.setSuccess(false);
				j.setMsg("该申请不能被撤回！");
			}
		}catch(Exception e){
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg(MessageConstant.OPTIONERROR);
		}
		return j;
	}

	/**
	 * 暂时未用到
	 * 导出excell文件
	 * @param list 数据
	 * @param fileName 文件名
	 * @param response
	 * @throws Exception
	 */
	private void exportExcel(List<Map> list, String fileName,HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1"));
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 创建写工作簿对象，这里直接采用流输出，而不会再生成一个文件
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
		// 工作表
		WritableSheet sheet = workbook.createSheet("Sheet1", 0);
		// 设置字体;
		WritableFont font = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat cellFormat = new WritableCellFormat(font);
		// 设置背景颜色;
		cellFormat.setBackground(Colour.WHITE);
		// 设置边框;
		cellFormat.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
		// 设置文字居中对齐方式;
		cellFormat.setAlignment(Alignment.CENTRE);
		// 设置垂直居中;
		cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 分别给列设置不同的宽度;
		sheet.setColumnView(0, 60);
		sheet.setColumnView(1, 30);
		sheet.setColumnView(2, 30);
		sheet.setColumnView(3, 30);
		// 给sheet电子版中所有的列设置默认的列的宽度;
		sheet.getSettings().setDefaultColumnWidth(20);
		// 给sheet电子版中所有的行设置默认的高度，高度的单位是1/20个像素点,但设置这个貌似就不能自动换行了
		// sheet.getSettings().setDefaultRowHeight(30 * 20);
		// 设置自动换行;
		cellFormat.setWrap(true);
		// 单元格
		Label label0 = new Label(0, 0, "部门名称", cellFormat);
		Label label1 = new Label(1, 0, "值班时间", cellFormat);
		Label label2 = new Label(2, 0, "班次", cellFormat);
		Label label3 = new Label(3, 0, "值班用户", cellFormat);
		sheet.addCell(label0);
		sheet.addCell(label1);
		sheet.addCell(label2);
		sheet.addCell(label3);
		// 给第二行设置背景、字体颜色、对齐方式等等;
		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
		// 设置文字居中对齐方式;
		cellFormat2.setAlignment(Alignment.CENTRE);
		// 设置垂直居中;
		cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
		cellFormat2.setBackground(Colour.WHITE);
		cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
		cellFormat2.setWrap(true);
		// 记录行数
		int n=1;
		for(Map m:list){
			String departname = m.get("departname")==null?"":m.get("departname").toString();
			String time = m.get("time")==null?"":m.get("time").toString();
			String shiftname = m.get("shiftname")==null?"":m.get("shiftname").toString();
			String username = m.get("username")==null?"": m.get("username").toString();
			Label lt0 = new Label(0, n, departname, cellFormat2);
			Label lt1 = new Label(1, n, time, cellFormat2);
			Label lt2 = new Label(2, n, shiftname, cellFormat2);
			Label lt3 = new Label(3, n, username, cellFormat2);
			sheet.addCell(lt0);
			sheet.addCell(lt1);
			sheet.addCell(lt2);
			sheet.addCell(lt3);
			n++;
		}
		//开始执行写入操作
		workbook.write();
		//关闭流
		workbook.close();
	}

	
	
}
