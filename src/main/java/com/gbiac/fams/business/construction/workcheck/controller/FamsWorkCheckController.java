package com.gbiac.fams.business.construction.workcheck.controller;

import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.construction.workcheck.entity.FamsWorkCheckEntity;
import com.gbiac.fams.business.construction.workcheck.service.FamsWorkCheckServiceI;
import com.gbiac.fams.business.construction.workcheckitem.entity.FamsWorkCheckItemEntity;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Title: Controller
 * @Description: 例行检查表
 * @author onlineGenerator
 * @date 2019-02-18 10:46:10
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/famsWorkCheckController")
public class FamsWorkCheckController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsWorkCheckController.class);

	@Autowired
	private FamsWorkCheckServiceI famsWorkCheckService;
	@Autowired
	private SystemService systemService;



	/**
	 * 例行检查表列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/construction/workcheck/famsWorkCheckList");
	}

	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FamsWorkCheckEntity famsWorkCheck,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkCheckEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkCheck, request.getParameterMap());
		try{
			//自定义追加查询条件

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsWorkCheckService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除例行检查表
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsWorkCheckEntity famsWorkCheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsWorkCheck = systemService.getEntity(FamsWorkCheckEntity.class, famsWorkCheck.getId());
		message = "例行检查删除成功";
		try{
			famsWorkCheckService.delete(famsWorkCheck);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "例行检查删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除例行检查表
	 *
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "例行检查删除成功";
		try{
			for(String id:ids.split(",")){
				FamsWorkCheckEntity famsWorkCheck = systemService.getEntity(FamsWorkCheckEntity.class,
						id
				);
				famsWorkCheckService.delete(famsWorkCheck);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "例行检查删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加例行检查表
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsWorkCheckEntity famsWorkCheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "例行检查添加成功";
		try{
			//获取具体检查小项的检查结果
            Map itemValues=this.getCheckItems(request);
			//获取附件id集合
			String files = request.getParameter("files");
			famsWorkCheckService.save(famsWorkCheck,itemValues,files);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			new Thread(){
				@Override
				public void run() {
					try{
						//推送
						FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkCheck.getBid());
						//获取角色下的所有人员id
						List<TSBaseUser> users=famsCommonService.getApplyUser(entity.getCreateUserId());
						//发送系统消息
						messageService.pushOnlyPCMessage("workCheck",famsWorkCheck.getId(),"您有新的"+entity.getWorkName()+"检查记录",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"添加了一条例行检查记录，检查项目："+entity.getWorkName(),Util.getPcOrAppUserName(),users,null,null);
						//推送消息
						famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"workCheck",entity.getId(),users,"您有新的"+entity.getWorkName()+"检查记录",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"添加了一条例行检查记录，检查项目："+entity.getWorkName(),null);
					}catch (Exception e){
					}
				}
			}.run();
		}catch(BusinessException e){
			message = e.getMessage();
			j.setSuccess(false);
		}catch(Exception e){
			e.printStackTrace();
			message = "例行检查添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 获取具体检查小项的检查结果
	 * @param request
	 * @return
	 */
	private Map getCheckItems(HttpServletRequest request) {
        Map result=new HashMap();
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String name = parameterNames.nextElement();
			if(name.startsWith("item[")){
                result.put(name.substring(5,name.length()-1),request.getParameter(name));
			}
		}
		return result;
	}

	/**
	 * 更新例行检查表
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsWorkCheckEntity famsWorkCheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "例行检查更新成功";
		FamsWorkCheckEntity t = famsWorkCheckService.get(FamsWorkCheckEntity.class, famsWorkCheck.getId());
		try {
            //获取具体检查小项的检查结果
            Map itemValues=this.getCheckItems(request);
			//获取附件id集合
			String files = request.getParameter("files");
			MyBeanUtils.copyBeanNotNull2Bean(famsWorkCheck, t);
			famsWorkCheckService.saveOrUpdate(t,itemValues,files);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "例行检查更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 例行检查表新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsWorkCheckEntity famsWorkCheck, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsWorkCheck.getId())) {
			famsWorkCheck = famsWorkCheckService.getEntity(FamsWorkCheckEntity.class, famsWorkCheck.getId());
			req.setAttribute("famsWorkCheck", famsWorkCheck);
		}
		//查询分类表中的数据("不停航施工现场例行检查登记表")
		Map map=famsWorkCheckService.getCategory("A05%");
		req.setAttribute("detail",map);
		return new ModelAndView("com/gbiac/fams/business/construction/workcheck/famsWorkCheck-add");
	}
	/**
	 * 例行检查表编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsWorkCheckEntity famsWorkCheck, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsWorkCheck.getId())) {
			famsWorkCheck = famsWorkCheckService.getEntity(FamsWorkCheckEntity.class, famsWorkCheck.getId());
			req.setAttribute("famsWorkCheck", famsWorkCheck);
            //查询分类表中的数据("不停航施工现场例行检查登记表")
            Map map=famsWorkCheckService.getCategory("A05%",famsWorkCheck.getId());
            req.setAttribute("detail",map);
            //查询检查小项结果
            List<FamsWorkCheckItemEntity> entities = famsCommonService.getEntitiesByProperty(FamsWorkCheckItemEntity.class, "checkId".split(","), famsWorkCheck.getId().split(","));
            Map checkItems=new HashMap();
            for(FamsWorkCheckItemEntity f:entities){
                checkItems.put(f.getCategoryId(),f.getCheckResult());
            }
            req.setAttribute("checkItems",checkItems);
			//获取附件id集合
			List<FamsCommonFileEntity> files = famsCommonService.getEntitiesByProperty(FamsCommonFileEntity.class,"businessId".split(","),famsWorkCheck.getId().split(","));
			req.setAttribute("files", Util.filesToMap(files));
        }
        return new ModelAndView("com/gbiac/fams/business/construction/workcheck/famsWorkCheck-update");
	}

	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsWorkCheckController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsWorkCheckEntity famsWorkCheck,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkCheckEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkCheck, request.getParameterMap());
		List<FamsWorkCheckEntity> famsWorkChecks = this.famsWorkCheckService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"例行检查表");
		modelMap.put(NormalExcelConstants.CLASS,FamsWorkCheckEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("例行检查表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
				"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsWorkChecks);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsWorkCheckEntity famsWorkCheck,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME,"例行检查表");
		modelMap.put(NormalExcelConstants.CLASS,FamsWorkCheckEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("例行检查表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsWorkCheckEntity> listFamsWorkCheckEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsWorkCheckEntity.class,params);
				for (FamsWorkCheckEntity famsWorkCheck : listFamsWorkCheckEntitys) {
					famsWorkCheckService.save(famsWorkCheck);
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


}
