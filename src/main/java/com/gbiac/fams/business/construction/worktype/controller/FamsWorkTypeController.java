package com.gbiac.fams.business.construction.worktype.controller;

import com.gbiac.fams.business.construction.worktype.entity.FamsWorkTypeEntity;
import com.gbiac.fams.business.construction.worktype.service.FamsWorkTypeServiceI;
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
 * @Description: 施工类型表
 * @author 龚道海
 * @date 2019-01-22 15:11:56
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsWorkTypeController")
public class FamsWorkTypeController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsWorkTypeController.class);

	@Autowired
	private FamsWorkTypeServiceI famsWorkTypeService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 施工类型表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/construction/worktype/famsWorkTypeList");
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
	public void datagrid(FamsWorkTypeEntity famsWorkType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkType, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsWorkTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除施工类型表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsWorkTypeEntity famsWorkType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsWorkType = systemService.getEntity(FamsWorkTypeEntity.class, famsWorkType.getId());
		message = "施工类型表删除成功";
		try{
			famsWorkTypeService.delete(famsWorkType);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "施工类型表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除施工类型表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "施工类型表删除成功";
		try{
			for(String id:ids.split(",")){
				FamsWorkTypeEntity famsWorkType = systemService.getEntity(FamsWorkTypeEntity.class, 
				id
				);
				famsWorkTypeService.delete(famsWorkType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "施工类型表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加施工类型表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsWorkTypeEntity famsWorkType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "施工类型表添加成功";
		try{
			famsWorkTypeService.save(famsWorkType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "施工类型表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新施工类型表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsWorkTypeEntity famsWorkType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "施工类型表更新成功";
		FamsWorkTypeEntity t = famsWorkTypeService.get(FamsWorkTypeEntity.class, famsWorkType.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsWorkType, t);
			famsWorkTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "施工类型表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 施工类型表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsWorkTypeEntity famsWorkType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsWorkType.getId())) {
			famsWorkType = famsWorkTypeService.getEntity(FamsWorkTypeEntity.class, famsWorkType.getId());
			req.setAttribute("famsWorkType", famsWorkType);
		}
		return new ModelAndView("com/gbiac/fams/business/construction/worktype/famsWorkType-add");
	}
	/**
	 * 施工类型表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsWorkTypeEntity famsWorkType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsWorkType.getId())) {
			famsWorkType = famsWorkTypeService.getEntity(FamsWorkTypeEntity.class, famsWorkType.getId());
			req.setAttribute("famsWorkType", famsWorkType);
		}
		return new ModelAndView("com/gbiac/fams/business/construction/worktype/famsWorkType-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsWorkTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsWorkTypeEntity famsWorkType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkType, request.getParameterMap());
		List<FamsWorkTypeEntity> famsWorkTypes = this.famsWorkTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"施工类型表");
		modelMap.put(NormalExcelConstants.CLASS,FamsWorkTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("施工类型表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsWorkTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsWorkTypeEntity famsWorkType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"施工类型表");
    	modelMap.put(NormalExcelConstants.CLASS,FamsWorkTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("施工类型表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsWorkTypeEntity> listFamsWorkTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsWorkTypeEntity.class,params);
				for (FamsWorkTypeEntity famsWorkType : listFamsWorkTypeEntitys) {
					famsWorkTypeService.save(famsWorkType);
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
