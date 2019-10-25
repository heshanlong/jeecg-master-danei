package com.gbiac.fams.business.safecheck.safecheckconfig.controller;
import com.gbiac.fams.business.safecheck.safecheckconfig.entity.FamsAircontrolSafecheckconfigEntity;
import com.gbiac.fams.business.safecheck.safecheckconfig.service.FamsAircontrolSafecheckconfigServiceI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: 航班保障作业检查表单配置信息
 * @author onlineGenerator
 * @date 2019-01-21 11:00:18
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsAircontrolSafecheckconfigController")
public class FamsAircontrolSafecheckconfigController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAircontrolSafecheckconfigController.class);

	@Autowired
	private FamsAircontrolSafecheckconfigServiceI famsAircontrolSafecheckconfigService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 航班保障作业检查表单配置信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/safecheck/safecheckconfig/famsAircontrolSafecheckconfigList");
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
	public void datagrid(FamsAircontrolSafecheckconfigEntity famsAircontrolSafecheckconfig,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsAircontrolSafecheckconfigEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAircontrolSafecheckconfig, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsAircontrolSafecheckconfigService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除航班保障作业检查表单配置信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAircontrolSafecheckconfigEntity famsAircontrolSafecheckconfig, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAircontrolSafecheckconfig = systemService.getEntity(FamsAircontrolSafecheckconfigEntity.class, famsAircontrolSafecheckconfig.getId());
		message = "航班保障作业检查表单配置信息删除成功";
		try{
			famsAircontrolSafecheckconfigService.delete(famsAircontrolSafecheckconfig);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "航班保障作业检查表单配置信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除航班保障作业检查表单配置信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航班保障作业检查表单配置信息删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAircontrolSafecheckconfigEntity famsAircontrolSafecheckconfig = systemService.getEntity(FamsAircontrolSafecheckconfigEntity.class, 
				id
				);
				famsAircontrolSafecheckconfigService.delete(famsAircontrolSafecheckconfig);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "航班保障作业检查表单配置信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加航班保障作业检查表单配置信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAircontrolSafecheckconfigEntity famsAircontrolSafecheckconfig, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航班保障作业检查表单配置信息添加成功";
		try{
			famsAircontrolSafecheckconfigService.save(famsAircontrolSafecheckconfig);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "航班保障作业检查表单配置信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新航班保障作业检查表单配置信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAircontrolSafecheckconfigEntity famsAircontrolSafecheckconfig, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航班保障作业检查表单配置信息更新成功";
		FamsAircontrolSafecheckconfigEntity t = famsAircontrolSafecheckconfigService.get(FamsAircontrolSafecheckconfigEntity.class, famsAircontrolSafecheckconfig.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolSafecheckconfig, t);
			famsAircontrolSafecheckconfigService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "航班保障作业检查表单配置信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 航班保障作业检查表单配置信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAircontrolSafecheckconfigEntity famsAircontrolSafecheckconfig, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAircontrolSafecheckconfig.getId())) {
			famsAircontrolSafecheckconfig = famsAircontrolSafecheckconfigService.getEntity(FamsAircontrolSafecheckconfigEntity.class, famsAircontrolSafecheckconfig.getId());
			req.setAttribute("famsAircontrolSafecheckconfig", famsAircontrolSafecheckconfig);
		}
		return new ModelAndView("com/gbiac/fams/business/safecheck/safecheckconfig/famsAircontrolSafecheckconfig-add");
	}
	/**
	 * 航班保障作业检查表单配置信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAircontrolSafecheckconfigEntity famsAircontrolSafecheckconfig, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAircontrolSafecheckconfig.getId())) {
			famsAircontrolSafecheckconfig = famsAircontrolSafecheckconfigService.getEntity(FamsAircontrolSafecheckconfigEntity.class, famsAircontrolSafecheckconfig.getId());
			req.setAttribute("famsAircontrolSafecheckconfig", famsAircontrolSafecheckconfig);
		}
		return new ModelAndView("com/gbiac/fams/business/safecheck/safecheckconfig/famsAircontrolSafecheckconfig-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAircontrolSafecheckconfigController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAircontrolSafecheckconfigEntity famsAircontrolSafecheckconfig,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAircontrolSafecheckconfigEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAircontrolSafecheckconfig, request.getParameterMap());
		List<FamsAircontrolSafecheckconfigEntity> famsAircontrolSafecheckconfigs = this.famsAircontrolSafecheckconfigService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"航班保障作业检查表单配置信息");
		modelMap.put(NormalExcelConstants.CLASS,FamsAircontrolSafecheckconfigEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("航班保障作业检查表单配置信息列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAircontrolSafecheckconfigs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAircontrolSafecheckconfigEntity famsAircontrolSafecheckconfig,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"航班保障作业检查表单配置信息");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAircontrolSafecheckconfigEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("航班保障作业检查表单配置信息列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsAircontrolSafecheckconfigEntity> listFamsAircontrolSafecheckconfigEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAircontrolSafecheckconfigEntity.class,params);
				for (FamsAircontrolSafecheckconfigEntity famsAircontrolSafecheckconfig : listFamsAircontrolSafecheckconfigEntitys) {
					famsAircontrolSafecheckconfigService.save(famsAircontrolSafecheckconfig);
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
