package com.gbiac.fams.business.construction.worksafe.controller;

import com.gbiac.fams.business.construction.worksafe.entity.FamsWorkSafePersonEntity;
import com.gbiac.fams.business.construction.worksafe.service.FamsWorkSafePersonServiceI;
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
 * @Description: 施工安全员信息表
 * @author 龚道海
 * @date 2019-01-14 14:24:54
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsWorkSafePersonController")
public class FamsWorkSafePersonController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsWorkSafePersonController.class);

	@Autowired
	private FamsWorkSafePersonServiceI famsWorkSafePersonService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 施工安全员信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/construction/worksafe/famsWorkSafePersonList");
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
	public void datagrid(FamsWorkSafePersonEntity famsWorkSafePerson,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkSafePersonEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkSafePerson, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsWorkSafePersonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除施工安全员信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsWorkSafePersonEntity famsWorkSafePerson, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsWorkSafePerson = systemService.getEntity(FamsWorkSafePersonEntity.class, famsWorkSafePerson.getId());
		message = "施工安全员信息表删除成功";
		try{
			famsWorkSafePersonService.delete(famsWorkSafePerson);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "施工安全员信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除施工安全员信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "施工安全员信息表删除成功";
		try{
			for(String id:ids.split(",")){
				FamsWorkSafePersonEntity famsWorkSafePerson = systemService.getEntity(FamsWorkSafePersonEntity.class, 
				id
				);
				famsWorkSafePersonService.delete(famsWorkSafePerson);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "施工安全员信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加施工安全员信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsWorkSafePersonEntity famsWorkSafePerson, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "施工安全员信息表添加成功";
		try{
			famsWorkSafePersonService.save(famsWorkSafePerson);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "施工安全员信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新施工安全员信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsWorkSafePersonEntity famsWorkSafePerson, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "施工安全员信息表更新成功";
		FamsWorkSafePersonEntity t = famsWorkSafePersonService.get(FamsWorkSafePersonEntity.class, famsWorkSafePerson.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsWorkSafePerson, t);
			famsWorkSafePersonService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "施工安全员信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 施工安全员信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsWorkSafePersonEntity famsWorkSafePerson, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsWorkSafePerson.getId())) {
			famsWorkSafePerson = famsWorkSafePersonService.getEntity(FamsWorkSafePersonEntity.class, famsWorkSafePerson.getId());
			req.setAttribute("famsWorkSafePerson", famsWorkSafePerson);
		}
		return new ModelAndView("com/gbiac/fams/business/construction/worksafe/famsWorkSafePerson-add");
	}
	/**
	 * 施工安全员信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsWorkSafePersonEntity famsWorkSafePerson, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsWorkSafePerson.getId())) {
			famsWorkSafePerson = famsWorkSafePersonService.getEntity(FamsWorkSafePersonEntity.class, famsWorkSafePerson.getId());
			req.setAttribute("famsWorkSafePerson", famsWorkSafePerson);
		}
		return new ModelAndView("com/gbiac/fams/business/construction/worksafe/famsWorkSafePerson-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsWorkSafePersonController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsWorkSafePersonEntity famsWorkSafePerson,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkSafePersonEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkSafePerson, request.getParameterMap());
		List<FamsWorkSafePersonEntity> famsWorkSafePersons = this.famsWorkSafePersonService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"施工安全员信息表");
		modelMap.put(NormalExcelConstants.CLASS,FamsWorkSafePersonEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("施工安全员信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsWorkSafePersons);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsWorkSafePersonEntity famsWorkSafePerson,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"施工安全员信息表");
    	modelMap.put(NormalExcelConstants.CLASS,FamsWorkSafePersonEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("施工安全员信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsWorkSafePersonEntity> listFamsWorkSafePersonEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsWorkSafePersonEntity.class,params);
				for (FamsWorkSafePersonEntity famsWorkSafePerson : listFamsWorkSafePersonEntitys) {
					famsWorkSafePersonService.save(famsWorkSafePerson);
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
