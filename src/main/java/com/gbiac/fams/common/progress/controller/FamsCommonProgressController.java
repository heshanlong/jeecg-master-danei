package com.gbiac.fams.common.progress.controller;

import com.gbiac.fams.common.progress.entity.FamsCommonProgressEntity;
import com.gbiac.fams.common.progress.service.FamsCommonProgressServiceI;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**   
 * @Title: Controller  
 * @Description: 通用进度表
 * @author 龚道海
 * @date 2019-01-09 10:44:26
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsCommonProgressController")
public class FamsCommonProgressController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsCommonProgressController.class);

	@Autowired
	private FamsCommonProgressServiceI famsCommonProgressService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 通用进度表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/common/progress/famsCommonProgressList");
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
	public void datagrid(FamsCommonProgressEntity famsCommonProgress,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonProgressEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonProgress, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsCommonProgressService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除通用进度表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsCommonProgressEntity famsCommonProgress, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsCommonProgress = systemService.getEntity(FamsCommonProgressEntity.class, famsCommonProgress.getId());
		message = "通用进度表删除成功";
		try{
			famsCommonProgressService.delete(famsCommonProgress);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用进度表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除通用进度表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用进度表删除成功";
		try{
			for(String id:ids.split(",")){
				FamsCommonProgressEntity famsCommonProgress = systemService.getEntity(FamsCommonProgressEntity.class, 
				id
				);
				famsCommonProgressService.delete(famsCommonProgress);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "通用进度表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加通用进度表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsCommonProgressEntity famsCommonProgress, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用进度表添加成功";
		try{
			famsCommonProgressService.save(famsCommonProgress);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用进度表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新通用进度表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsCommonProgressEntity famsCommonProgress, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用进度表更新成功";
		FamsCommonProgressEntity t = famsCommonProgressService.get(FamsCommonProgressEntity.class, famsCommonProgress.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsCommonProgress, t);
			famsCommonProgressService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "通用进度表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 通用进度表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsCommonProgressEntity famsCommonProgress, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsCommonProgress.getId())) {
			famsCommonProgress = famsCommonProgressService.getEntity(FamsCommonProgressEntity.class, famsCommonProgress.getId());
			req.setAttribute("famsCommonProgress", famsCommonProgress);
		}
		return new ModelAndView("com/gbiac/fams/common/progress/famsCommonProgress-add");
	}
	/**
	 * 通用进度表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsCommonProgressEntity famsCommonProgress, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsCommonProgress.getId())) {
			famsCommonProgress = famsCommonProgressService.getEntity(FamsCommonProgressEntity.class, famsCommonProgress.getId());
			req.setAttribute("famsCommonProgress", famsCommonProgress);
		}
		return new ModelAndView("com/gbiac/fams/common/progress/famsCommonProgress-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsCommonProgressController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsCommonProgressEntity famsCommonProgress,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonProgressEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonProgress, request.getParameterMap());
		List<FamsCommonProgressEntity> famsCommonProgresss = this.famsCommonProgressService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"通用进度表");
		modelMap.put(NormalExcelConstants.CLASS,FamsCommonProgressEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通用进度表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsCommonProgresss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsCommonProgressEntity famsCommonProgress,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"通用进度表");
    	modelMap.put(NormalExcelConstants.CLASS,FamsCommonProgressEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通用进度表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsCommonProgressEntity> listFamsCommonProgressEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsCommonProgressEntity.class,params);
				for (FamsCommonProgressEntity famsCommonProgress : listFamsCommonProgressEntitys) {
					famsCommonProgressService.save(famsCommonProgress);
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
