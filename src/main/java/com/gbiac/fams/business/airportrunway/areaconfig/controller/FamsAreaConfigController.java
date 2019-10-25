package com.gbiac.fams.business.airportrunway.areaconfig.controller;
import com.gbiac.fams.business.airportrunway.areaconfig.entity.FamsAreaConfigEntity;
import com.gbiac.fams.business.airportrunway.areaconfig.service.FamsAreaConfigServiceI;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsite.entity.FamsAttentionCraftsiteEntity;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsite.service.FamsAttentionCraftsiteServiceI;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.FamsAttentionCraftsiteUserEntity;
import com.gbiac.fams.business.construction.workcheck.entity.FamsWorkCheckEntity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
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
 * @Description: 区域配置
 * @author onlineGenerator
 * @date 2019-02-22 11:17:23
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsAreaConfigController")
public class FamsAreaConfigController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAreaConfigController.class);

	@Autowired
	private FamsAreaConfigServiceI famsAreaConfigService;
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private FamsAttentionCraftsiteServiceI famsAttentionCraftsiteService;
	
	@RequestMapping(params = "popupCraftsiteList")
	public String popupCraftsiteList(HttpServletRequest request,String entrance){
		request.setAttribute("entrance", entrance);
		return "com/gbiac/fams/business/airportrunway/areaconfig/odlselectCraftsiteList";
	}
	@RequestMapping(params = "selectCraftsiteDatagrid")
	public void selectCraftsiteDatagrid(FamsAttentionCraftsiteEntity famsAttentionCraftsite,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsAttentionCraftsiteEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAttentionCraftsite, request.getParameterMap());
		try{
			cq.add(Restrictions.sqlRestriction("check_craftsite is null or check_craftsite != 1"));
			//自定义追加查询条件			

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsAttentionCraftsiteService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除区域配置
	 * 
	 * @return
	 */
	@RequestMapping(params = "ischeckcount")
	@ResponseBody
	public AjaxJson ischeckcount(String check_craftsite,String craftsite) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "区域配置删除成功";
		try{
			String craftsites[] = craftsite.split(",");
			Integer num = famsAreaConfigService.findByAreaAndCraftsite(check_craftsite,craftsites[0]);
			j.setObj(num);
			//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "区域配置删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 区域配置列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/airportrunway/areaconfig/famsAreaConfigList");
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
	public void datagrid(FamsAreaConfigEntity famsAreaConfig,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsAreaConfigEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAreaConfig, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsAreaConfigService.getDataGridReturn(cq, true);
		
		List<FamsAreaConfigEntity> list = dataGrid.getResults();

		dataGrid.setResults(list);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除区域配置
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAreaConfigEntity famsAreaConfig, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAreaConfig = systemService.getEntity(FamsAreaConfigEntity.class, famsAreaConfig.getId());
		message = "区域配置删除成功";
		String craftsites[] = famsAreaConfig.getCraftsite().split(",");
		try{
			for (int i = 0; i < craftsites.length; i++) {
				FamsAttentionCraftsiteEntity t = famsAttentionCraftsiteService.findUniqueByProperty(FamsAttentionCraftsiteEntity.class, "craftsite", craftsites[i]);
				t.setCheckCraftsite("0");
				famsAttentionCraftsiteService.saveOrUpdate(t);
			}
			famsAreaConfigService.delete(famsAreaConfig);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "区域配置删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除区域配置
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "区域配置删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAreaConfigEntity famsAreaConfig = systemService.getEntity(FamsAreaConfigEntity.class, 
				id
				);
				famsAreaConfigService.delete(famsAreaConfig);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "区域配置删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加区域配置
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAreaConfigEntity famsAreaConfig, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "区域配置添加成功";
		try{
			famsAreaConfigService.save(famsAreaConfig);
			String craftsites[] = famsAreaConfig.getCraftsite().split(",");
			for (int i = 0; i < craftsites.length; i++) {
				FamsAttentionCraftsiteEntity t = famsAttentionCraftsiteService.findUniqueByProperty(FamsAttentionCraftsiteEntity.class, "craftsite", craftsites[i]);
				t.setCheckCraftsite("1");
				famsAttentionCraftsiteService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "区域配置添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新区域配置
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAreaConfigEntity famsAreaConfig, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "区域配置更新成功";
		FamsAreaConfigEntity t = famsAreaConfigService.get(FamsAreaConfigEntity.class, famsAreaConfig.getId());
		String craftsites[] = famsAreaConfig.getCraftsite().split(",");
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAreaConfig, t);
			famsAreaConfigService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			for (int i = 0; i < craftsites.length; i++) {
				FamsAttentionCraftsiteEntity t2 = famsAttentionCraftsiteService.findUniqueByProperty(FamsAttentionCraftsiteEntity.class, "craftsite", craftsites[i]);
				t2.setCheckCraftsite("1");
				famsAttentionCraftsiteService.saveOrUpdate(t2);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "区域配置更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 区域配置新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAreaConfigEntity famsAreaConfig, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAreaConfig.getId())) {
			famsAreaConfig = famsAreaConfigService.getEntity(FamsAreaConfigEntity.class, famsAreaConfig.getId());
			req.setAttribute("famsAreaConfig", famsAreaConfig);
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/areaconfig/famsAreaConfig-add");
	}
	/**
	 * 区域配置编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAreaConfigEntity famsAreaConfig, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAreaConfig.getId())) {
			famsAreaConfig = famsAreaConfigService.getEntity(FamsAreaConfigEntity.class, famsAreaConfig.getId());
			req.setAttribute("famsAreaConfig", famsAreaConfig);
		}
		String craftsites[] = famsAreaConfig.getCraftsite().split(",");
		for (int i = 0; i < craftsites.length; i++) {
			FamsAttentionCraftsiteEntity t2 = famsAttentionCraftsiteService.findUniqueByProperty(FamsAttentionCraftsiteEntity.class, "craftsite", craftsites[i]);
			t2.setCheckCraftsite("0");
			try {
				famsAttentionCraftsiteService.saveOrUpdate(t2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/areaconfig/famsAreaConfig-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAreaConfigController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAreaConfigEntity famsAreaConfig,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAreaConfigEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAreaConfig, request.getParameterMap());
		List<FamsAreaConfigEntity> famsAreaConfigs = this.famsAreaConfigService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"区域配置");
		modelMap.put(NormalExcelConstants.CLASS,FamsAreaConfigEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("区域配置列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAreaConfigs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAreaConfigEntity famsAreaConfig,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"区域配置");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAreaConfigEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("区域配置列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsAreaConfigEntity> listFamsAreaConfigEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAreaConfigEntity.class,params);
				for (FamsAreaConfigEntity famsAreaConfig : listFamsAreaConfigEntitys) {
					famsAreaConfigService.save(famsAreaConfig);
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
