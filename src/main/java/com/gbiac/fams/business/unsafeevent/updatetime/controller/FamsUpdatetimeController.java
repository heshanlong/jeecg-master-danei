package com.gbiac.fams.business.unsafeevent.updatetime.controller;
import com.gbiac.fams.business.unsafeevent.updatetime.entity.FamsUpdatetimeEntity;
import com.gbiac.fams.business.unsafeevent.updatetime.service.FamsUpdatetimeServiceI;

import java.util.ArrayList;
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
 * @Description: fams_updatetime
 * @author onlineGenerator
 * @date 2019-03-11 16:07:52
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsUpdatetimeController")
public class FamsUpdatetimeController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsUpdatetimeController.class);

	@Autowired
	private FamsUpdatetimeServiceI famsUpdatetimeService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * fams_updatetime列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		request.setAttribute("idd", request.getParameter("id"));
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/updatetime/famsUpdatetimeList");
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
	public void datagrid(FamsUpdatetimeEntity famsUpdatetime,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsUpdatetimeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsUpdatetime, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.eq("no", famsUpdatetime.getNo());
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsUpdatetimeService.getDataGridReturn(cq, true);
		List<FamsUpdatetimeEntity> a = dataGrid.getResults();
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除fams_updatetime
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsUpdatetimeEntity famsUpdatetime, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsUpdatetime = systemService.getEntity(FamsUpdatetimeEntity.class, famsUpdatetime.getId());
		message = "fams_updatetime删除成功";
		try{
			famsUpdatetimeService.delete(famsUpdatetime);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "fams_updatetime删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除fams_updatetime
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "fams_updatetime删除成功";
		try{
			for(String id:ids.split(",")){
				FamsUpdatetimeEntity famsUpdatetime = systemService.getEntity(FamsUpdatetimeEntity.class, 
				id
				);
				famsUpdatetimeService.delete(famsUpdatetime);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "fams_updatetime删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加fams_updatetime
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsUpdatetimeEntity famsUpdatetime, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "fams_updatetime添加成功";
		try{
			famsUpdatetimeService.save(famsUpdatetime);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "fams_updatetime添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新fams_updatetime
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsUpdatetimeEntity famsUpdatetime, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "fams_updatetime更新成功";
		FamsUpdatetimeEntity t = famsUpdatetimeService.get(FamsUpdatetimeEntity.class, famsUpdatetime.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsUpdatetime, t);
			famsUpdatetimeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "fams_updatetime更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * fams_updatetime新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsUpdatetimeEntity famsUpdatetime, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsUpdatetime.getId())) {
			famsUpdatetime = famsUpdatetimeService.getEntity(FamsUpdatetimeEntity.class, famsUpdatetime.getId());
			req.setAttribute("famsUpdatetime", famsUpdatetime);
		}
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/updatetime/famsUpdatetime-add");
	}
	/**
	 * fams_updatetime编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsUpdatetimeEntity famsUpdatetime, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsUpdatetime.getId())) {
			famsUpdatetime = famsUpdatetimeService.getEntity(FamsUpdatetimeEntity.class, famsUpdatetime.getId());
			req.setAttribute("famsUpdatetime", famsUpdatetime);
		}
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/updatetime/famsUpdatetime-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsUpdatetimeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsUpdatetimeEntity famsUpdatetime,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsUpdatetimeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsUpdatetime, request.getParameterMap());
		List<FamsUpdatetimeEntity> famsUpdatetimes = this.famsUpdatetimeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"fams_updatetime");
		modelMap.put(NormalExcelConstants.CLASS,FamsUpdatetimeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_updatetime列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsUpdatetimes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsUpdatetimeEntity famsUpdatetime,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"fams_updatetime");
    	modelMap.put(NormalExcelConstants.CLASS,FamsUpdatetimeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_updatetime列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsUpdatetimeEntity> listFamsUpdatetimeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsUpdatetimeEntity.class,params);
				for (FamsUpdatetimeEntity famsUpdatetime : listFamsUpdatetimeEntitys) {
					famsUpdatetimeService.save(famsUpdatetime);
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
