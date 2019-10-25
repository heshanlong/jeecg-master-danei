package com.gbiac.fams.business.airportrunway.repair.controller;
import com.gbiac.fams.business.airportrunway.repair.entity.FamsAirportrunwayRepairEntity;
import com.gbiac.fams.business.airportrunway.repair.service.FamsAirportrunwayRepairServiceI;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.SimpleDateFormat;
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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;

import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: fams_airportrunway_repair
 * @author 江家滨
 * @date 2019-01-21 10:39:34
 * @version V1.0   
 *
 */
@Api(value="famsAirportrunwayRepairController",description="道面修补",tags="famsAirportrunwayRepairController")
@Controller
@RequestMapping("/famsAirportrunwayRepairController")
public class FamsAirportrunwayRepairController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAirportrunwayRepairController.class);

	@Autowired
	private FamsAirportrunwayRepairServiceI famsAirportrunwayRepairService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * fams_airportrunway_repair列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/airportrunway/repair/famsAirportrunwayRepairList");
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
	public void datagrid(FamsAirportrunwayRepairEntity famsAirportrunwayRepair,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String startTime = famsAirportrunwayRepair.getStartTime();
		String endTime = famsAirportrunwayRepair.getEndTime();
		famsAirportrunwayRepair.setStartTime("");
		famsAirportrunwayRepair.setEndTime("");
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwayRepairEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAirportrunwayRepair, request.getParameterMap());
		try{
		//自定义追加查询条件
			if (oConvertUtils.isNotEmpty(startTime)) {
                cq.ge("startTime", startTime);
            }
			if (oConvertUtils.isNotEmpty(endTime)) {
                cq.le("endTime", endTime);
            }
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsAirportrunwayRepairService.getDataGridReturn(cq, true);
		
		List<FamsAirportrunwayRepairEntity> famsAirportrunwayRepairList = dataGrid.getResults();
		for (int i = 0; i < famsAirportrunwayRepairList.size(); i++) {
//			if (famsAirportrunwayRepairList.get(i).getDamageTypeOther() !="" ) {//&& famsAirportrunwayRepairList.get(i).getDamageTypeOther().length() > 0
//				famsAirportrunwayRepairList.get(i).setDamageTypeModel(famsAirportrunwayRepairList.get(i).getDamageTypeOther());
//			}else{
//				famsAirportrunwayRepairList.get(i).setDamageTypeModel(famsAirportrunwayRepairList.get(i).getDamageType());
//			}
//			
//			if (famsAirportrunwayRepairList.get(i).getMaintenancePlanOther() !="" ) {//&& famsAirportrunwayRepairList.get(i).getMaintenancePlanOther().length() > 0
//				famsAirportrunwayRepairList.get(i).setMaintenancePlanModel(famsAirportrunwayRepairList.get(i).getMaintenancePlanOther());
//			}else{
//				famsAirportrunwayRepairList.get(i).setMaintenancePlanModel(famsAirportrunwayRepairList.get(i).getMaintenancePlan());
//			}
			
			famsAirportrunwayRepairList.get(i).setWorkload("面积" + famsAirportrunwayRepairList.get(i).getRepairArea() + "㎡,长度" + famsAirportrunwayRepairList.get(i).getIrrigationSeamLength() +"m" );
		}
        dataGrid.setResults(famsAirportrunwayRepairList);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除fams_airportrunway_repair
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAirportrunwayRepairEntity famsAirportrunwayRepair, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAirportrunwayRepair = systemService.getEntity(FamsAirportrunwayRepairEntity.class, famsAirportrunwayRepair.getId());
		message = "道面修补数据删除成功";
		try{
			famsAirportrunwayRepairService.delete(famsAirportrunwayRepair);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "道面修补数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("道面修补:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除fams_airportrunway_repair
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "道面修补数据删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAirportrunwayRepairEntity famsAirportrunwayRepair = systemService.getEntity(FamsAirportrunwayRepairEntity.class, 
				id
				);
				famsAirportrunwayRepairService.delete(famsAirportrunwayRepair);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
//			e.printStackTrace();
			message = "道面修补数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("道面修补:"+message,e);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加fams_airportrunway_repair
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAirportrunwayRepairEntity famsAirportrunwayRepair, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "道面修补数据添加成功";
		try{
			famsAirportrunwayRepair.setCreateByCn(ResourceUtil.getSessionUser().getRealName());
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsAirportrunwayRepairService.save(famsAirportrunwayRepair,map);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "道面修补数据添加失败";
//			throw new BusinessException(e.getMessage());
			logger.error("道面修补:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新fams_airportrunway_repair
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAirportrunwayRepairEntity famsAirportrunwayRepair, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "道面修补数据更新成功";
		FamsAirportrunwayRepairEntity t = famsAirportrunwayRepairService.get(FamsAirportrunwayRepairEntity.class, famsAirportrunwayRepair.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAirportrunwayRepair, t);
			t.setCreateByCn(ResourceUtil.getSessionUser().getRealName());
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsAirportrunwayRepairService.saveOrUpdate(t,map);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "道面修补数据更新失败";
//			throw new BusinessException(e.getMessage());
			logger.error("道面修补:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * fams_airportrunway_repair新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAirportrunwayRepairEntity famsAirportrunwayRepair, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAirportrunwayRepair.getId())) {
			famsAirportrunwayRepair = famsAirportrunwayRepairService.getEntity(FamsAirportrunwayRepairEntity.class, famsAirportrunwayRepair.getId());
			req.setAttribute("famsAirportrunwayRepair", famsAirportrunwayRepair);
		}else{
			req.setAttribute("realName", ResourceUtil.getSessionUser().getRealName());
			long enow = System.currentTimeMillis();
			long snow = System.currentTimeMillis() - 1 * 60 * 60 * 1000;
		    SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd HH:mm");
		    req.setAttribute("edf", sdf.format(enow));
		    req.setAttribute("sdf", sdf.format(snow));
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/repair/famsAirportrunwayRepair-add");
	}
	/**
	 * fams_airportrunway_repair编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAirportrunwayRepairEntity famsAirportrunwayRepair, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAirportrunwayRepair.getId())) {
			famsAirportrunwayRepair = famsAirportrunwayRepairService.getEntity(FamsAirportrunwayRepairEntity.class, famsAirportrunwayRepair.getId());
			req.setAttribute("famsAirportrunwayRepair", famsAirportrunwayRepair);
			
			//获取附件id集合
			List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsAirportrunwayRepair.getId(), null, null);
			req.setAttribute("files", Util.filesToMap(files));
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/repair/famsAirportrunwayRepair-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAirportrunwayRepairController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAirportrunwayRepairEntity famsAirportrunwayRepair,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwayRepairEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAirportrunwayRepair, request.getParameterMap());
		List<FamsAirportrunwayRepairEntity> famsAirportrunwayRepairs = this.famsAirportrunwayRepairService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"fams_airportrunway_repair");
		modelMap.put(NormalExcelConstants.CLASS,FamsAirportrunwayRepairEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_airportrunway_repair道面修补列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAirportrunwayRepairs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAirportrunwayRepairEntity famsAirportrunwayRepair,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"fams_airportrunway_repair");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAirportrunwayRepairEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_airportrunway_repair标志线维护列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsAirportrunwayRepairEntity> listFamsAirportrunwayRepairEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAirportrunwayRepairEntity.class,params);
				for (FamsAirportrunwayRepairEntity famsAirportrunwayRepair : listFamsAirportrunwayRepairEntitys) {
					famsAirportrunwayRepairService.save(famsAirportrunwayRepair);
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
