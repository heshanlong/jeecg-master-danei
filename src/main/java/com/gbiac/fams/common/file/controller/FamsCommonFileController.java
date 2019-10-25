package com.gbiac.fams.common.file.controller;

import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.file.service.FamsCommonFileServiceI;
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
 * @Description: 通用文件表
 * @author 龚道海
 * @date 2019-01-08 16:21:48
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsCommonFileController")
public class FamsCommonFileController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsCommonFileController.class);

	@Autowired
	private FamsCommonFileServiceI famsCommonFileService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 通用文件表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/common/file/famsCommonFileList");
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
	public void datagrid(FamsCommonFileEntity famsCommonFile,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonFileEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonFile, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsCommonFileService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除通用文件表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsCommonFileEntity famsCommonFile, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsCommonFile = systemService.getEntity(FamsCommonFileEntity.class, famsCommonFile.getId());
		message = "通用文件表删除成功";
		try{
			famsCommonFileService.delete(famsCommonFile);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用文件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除通用文件表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用文件表删除成功";
		try{
			for(String id:ids.split(",")){
				FamsCommonFileEntity famsCommonFile = systemService.getEntity(FamsCommonFileEntity.class, 
				id
				);
				famsCommonFileService.delete(famsCommonFile);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "通用文件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加通用文件表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsCommonFileEntity famsCommonFile, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用文件表添加成功";
		try{
			famsCommonFileService.save(famsCommonFile);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用文件表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新通用文件表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsCommonFileEntity famsCommonFile, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用文件表更新成功";
		FamsCommonFileEntity t = famsCommonFileService.get(FamsCommonFileEntity.class, famsCommonFile.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsCommonFile, t);
			famsCommonFileService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "通用文件表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 通用文件表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsCommonFileEntity famsCommonFile, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsCommonFile.getId())) {
			famsCommonFile = famsCommonFileService.getEntity(FamsCommonFileEntity.class, famsCommonFile.getId());
			req.setAttribute("famsCommonFile", famsCommonFile);
		}
		return new ModelAndView("com/gbiac/fams/common/file/famsCommonFile-add");
	}
	/**
	 * 通用文件表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsCommonFileEntity famsCommonFile, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsCommonFile.getId())) {
			famsCommonFile = famsCommonFileService.getEntity(FamsCommonFileEntity.class, famsCommonFile.getId());
			req.setAttribute("famsCommonFile", famsCommonFile);
		}
		return new ModelAndView("com/gbiac/fams/common/file/famsCommonFile-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsCommonFileController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsCommonFileEntity famsCommonFile,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonFileEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonFile, request.getParameterMap());
		List<FamsCommonFileEntity> famsCommonFiles = this.famsCommonFileService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"通用文件表");
		modelMap.put(NormalExcelConstants.CLASS,FamsCommonFileEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通用文件表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsCommonFiles);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsCommonFileEntity famsCommonFile,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"通用文件表");
    	modelMap.put(NormalExcelConstants.CLASS,FamsCommonFileEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通用文件表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsCommonFileEntity> listFamsCommonFileEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsCommonFileEntity.class,params);
				for (FamsCommonFileEntity famsCommonFile : listFamsCommonFileEntitys) {
					famsCommonFileService.save(famsCommonFile);
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
