package com.gbiac.fams.business.airportrunway.airportsurfaceclean.controller;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.service.FamsAirportrunwaySurfacecleanServiceI;
import com.gbiac.fams.business.airportrunway.repair.entity.FamsAirportrunwayRepairEntity;
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
 * @Description: fams_airportrunway_surfaceclean
 * @author 江家滨
 * @date 2019-01-17 17:07:01
 * @version V1.0   
 *
 */
@Api(value="famsAirportrunwaySurfacecleanController",description="道面清扫",tags="famsAirportrunwaySurfacecleanController")
@Controller
@RequestMapping("/famsAirportrunwaySurfacecleanController")
public class FamsAirportrunwaySurfacecleanController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAirportrunwaySurfacecleanController.class);

	@Autowired
	private FamsAirportrunwaySurfacecleanServiceI FamsAirportrunwaySurfacecleanService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * fams_airportrunway_surfaceclean列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/airportrunway/surfaceClean/famsAirportrunwaySurfacecleanList");
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
	public void datagrid(FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String startTime = famsAirportrunwaySurfaceclean.getStartTime();
		String endTime = famsAirportrunwaySurfaceclean.getEndTime();
		famsAirportrunwaySurfaceclean.setStartTime("");
		famsAirportrunwaySurfaceclean.setEndTime("");
		
//		String cleanEquipment = famsAirportrunwaySurfaceclean.getCleanEquipmentOther();
//		String cleanLocation = famsAirportrunwaySurfaceclean.getCleanLocationOther();
//		String cleanWhy = famsAirportrunwaySurfaceclean.getCleanWhyOther();
//		famsAirportrunwaySurfaceclean.setCleanEquipmentOther("");
//		famsAirportrunwaySurfaceclean.setCleanLocationOther("");
//		famsAirportrunwaySurfaceclean.setCleanWhyOther("");
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwaySurfacecleanEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAirportrunwaySurfaceclean, request.getParameterMap());
		try{
		//自定义追加查询条件
//			if (oConvertUtils.isNotEmpty(cleanEquipment)) {
//                cq.like("cleanEquipment", "%" + cleanEquipment + "%");
//            }
//			if (oConvertUtils.isNotEmpty(cleanLocation)) {
//                cq.like("cleanLocation", "%" + cleanLocation + "%");
//            }
//			if (oConvertUtils.isNotEmpty(cleanWhy)) {
//                cq.like("cleanWhy", "%" + cleanWhy + "%");
//            }			
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
		this.FamsAirportrunwaySurfacecleanService.getDataGridReturn(cq, true);
		
		List<FamsAirportrunwaySurfacecleanEntity> famsAirportrunwaySurfacecleanList = dataGrid.getResults();
		for (int i = 0; i < famsAirportrunwaySurfacecleanList.size(); i++) {
			if (null != famsAirportrunwaySurfacecleanList.get(i).getCleanWhyOther()) {
				if (famsAirportrunwaySurfacecleanList.get(i).getCleanWhyOther() !="" && famsAirportrunwaySurfacecleanList.get(i).getCleanWhyOther().length() > 0) {
					famsAirportrunwaySurfacecleanList.get(i).setCleanWhyOther(famsAirportrunwaySurfacecleanList.get(i).getCleanWhy().replaceAll("其他", famsAirportrunwaySurfacecleanList.get(i).getCleanWhyOther()) );
				}else{
					famsAirportrunwaySurfacecleanList.get(i).setCleanWhyOther(famsAirportrunwaySurfacecleanList.get(i).getCleanWhy());
				}
			}else{
				famsAirportrunwaySurfacecleanList.get(i).setCleanWhyOther(famsAirportrunwaySurfacecleanList.get(i).getCleanWhy());
			}
			String location = "";
			String locationOther = "";
			if (null != famsAirportrunwaySurfacecleanList.get(i).getCleanLocationOther() ) {
				locationOther = famsAirportrunwaySurfacecleanList.get(i).getCleanLocationOther();
			}
			if (null != famsAirportrunwaySurfacecleanList.get(i).getCleanLocation() ) {
				location = famsAirportrunwaySurfacecleanList.get(i).getCleanLocation();
			}
			String[] locationArray = location.split(",");
			String[] locationOtherArray = locationOther.split(",");
			String locationStr = "";
			if (locationArray.length == locationOtherArray.length) {
				for (int j = 0; j < locationOtherArray.length; j++) {
					locationStr += locationArray[j] + ":" + locationOtherArray[j] + ";";
				}
			}
			famsAirportrunwaySurfacecleanList.get(i).setCleanLocationOther(locationStr);
			
			if (null != famsAirportrunwaySurfacecleanList.get(i).getCleanEquipmentOther()) {
				if (famsAirportrunwaySurfacecleanList.get(i).getCleanEquipmentOther() !="" && famsAirportrunwaySurfacecleanList.get(i).getCleanEquipmentOther().length() > 0) {
					famsAirportrunwaySurfacecleanList.get(i).setCleanEquipmentOther(famsAirportrunwaySurfacecleanList.get(i).getCleanEquipment().replaceAll("其他", famsAirportrunwaySurfacecleanList.get(i).getCleanEquipmentOther()));
				}else{
					famsAirportrunwaySurfacecleanList.get(i).setCleanEquipmentOther(famsAirportrunwaySurfacecleanList.get(i).getCleanEquipment());
				}
			}else{
				famsAirportrunwaySurfacecleanList.get(i).setCleanEquipmentOther(famsAirportrunwaySurfacecleanList.get(i).getCleanEquipment());
			}
			
			
		}
        dataGrid.setResults(famsAirportrunwaySurfacecleanList);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除fams_airportrunway_surfaceclean
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAirportrunwaySurfaceclean = systemService.getEntity(FamsAirportrunwaySurfacecleanEntity.class, famsAirportrunwaySurfaceclean.getId());
		message = "道面清扫数据删除成功";
		try{
			FamsAirportrunwaySurfacecleanService.delete(famsAirportrunwaySurfaceclean);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "道面清扫数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("道面清扫:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除fams_airportrunway_surfaceclean
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "道面清扫数据删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean = systemService.getEntity(FamsAirportrunwaySurfacecleanEntity.class, 
				id
				);
				FamsAirportrunwaySurfacecleanService.delete(famsAirportrunwaySurfaceclean);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
//			e.printStackTrace();
			message = "道面清扫数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("道面清扫:"+message,e);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加fams_airportrunway_surfaceclean
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "道面清扫数据添加成功";
		try{
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			
			String cleanLocationOther = famsAirportrunwaySurfaceclean.getCleanLocationOther();
			String[] cleanLocationOtherArray = cleanLocationOther.split(",");
			String str = "";
			for (int i = 0; i < cleanLocationOtherArray.length; i++) {
				if (cleanLocationOtherArray[i].length() > 0) {
					if (str.length() > 0) {
						str += ",";
					}
					str += cleanLocationOtherArray[i];
				}
			}
			famsAirportrunwaySurfaceclean.setCleanLocationOther(str);
			famsAirportrunwaySurfaceclean.setCreateByCn(ResourceUtil.getSessionUser().getRealName());
			FamsAirportrunwaySurfacecleanService.save(famsAirportrunwaySurfaceclean,map);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "道面清扫数据添加失败";
//			throw new BusinessException(e.getMessage());
			logger.error("道面清扫:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新fams_airportrunway_surfaceclean
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "道面清扫数据更新成功";
//		FamsAirportrunwaySurfacecleanEntity t = FamsAirportrunwaySurfacecleanService.get(FamsAirportrunwaySurfacecleanEntity.class, famsAirportrunwaySurfaceclean.getId());
		try {
			String cleanLocationOther = famsAirportrunwaySurfaceclean.getCleanLocationOther();
			String[] cleanLocationOtherArray = cleanLocationOther.split(",");
			String str = "";
			for (int i = 0; i < cleanLocationOtherArray.length; i++) {
				if (cleanLocationOtherArray[i].length() > 0) {
					if (str.length() > 0) {
						str += ",";
					}
					str += cleanLocationOtherArray[i];
				}
			}
			famsAirportrunwaySurfaceclean.setCleanLocationOther(str);
			famsAirportrunwaySurfaceclean.setCreateByCn(ResourceUtil.getSessionUser().getRealName());
//			MyBeanUtils.copyBeanNotNull2Bean(famsAirportrunwaySurfaceclean, t);
//			t.setCleanWhy(t.getCleanWhy().replaceAll(",", ""));
//			t.setCleanLocation(t.getCleanLocation().replaceAll(",", ""));
//			t.setCleanEquipment(t.getCleanEquipment().replaceAll(",", ""));
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			FamsAirportrunwaySurfacecleanService.saveOrUpdate(famsAirportrunwaySurfaceclean,map);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "道面清扫数据更新失败";
//			throw new BusinessException(e.getMessage());
			logger.error("道面清扫:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * fams_airportrunway_surfaceclean新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAirportrunwaySurfaceclean.getId())) {
			famsAirportrunwaySurfaceclean = FamsAirportrunwaySurfacecleanService.getEntity(FamsAirportrunwaySurfacecleanEntity.class, famsAirportrunwaySurfaceclean.getId());
			req.setAttribute("famsAirportrunwaySurfaceclean", famsAirportrunwaySurfaceclean);
		}else{
			long enow = System.currentTimeMillis();
			long snow = System.currentTimeMillis() - 1 * 60 * 60 * 1000;
		    SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd HH:mm");
		    req.setAttribute("edf", sdf.format(enow));
		    req.setAttribute("sdf", sdf.format(snow));
		    
			req.setAttribute("realName", ResourceUtil.getSessionUser().getRealName());
			List<TSTypegroup> tSTypegroupList =systemService.findByProperty(TSTypegroup.class, "typegroupcode", "cLocation");
			if (tSTypegroupList!=null && tSTypegroupList.size() > 0) {
				List<TSType> tSTypeList = tSTypegroupList.get(0).getTSTypes();
				req.setAttribute("tSTypeList", tSTypeList);
				String typename = "";
				for (int i = 0; i < tSTypeList.size(); i++) {
					if (typename.length() > 0) {
						typename += ",";
					}
					typename += tSTypeList.get(i).getTypename();
				}
				req.setAttribute("typename", typename);
			}
		}

		return new ModelAndView("com/gbiac/fams/business/airportrunway/surfaceClean/famsAirportrunwaySurfaceclean-add");
	}
	/**
	 * fams_airportrunway_surfaceclean编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAirportrunwaySurfaceclean.getId())) {
			famsAirportrunwaySurfaceclean = FamsAirportrunwaySurfacecleanService.getEntity(FamsAirportrunwaySurfacecleanEntity.class, famsAirportrunwaySurfaceclean.getId());
			req.setAttribute("famsAirportrunwaySurfaceclean", famsAirportrunwaySurfaceclean);
			
			List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsAirportrunwaySurfaceclean.getId(), null, null);
			req.setAttribute("files", Util.filesToMap(files));
			
			List<TSTypegroup> tSTypegroupList =systemService.findByProperty(TSTypegroup.class, "typegroupcode", "cLocation");
			if (tSTypegroupList!=null && tSTypegroupList.size() > 0) {
				List<TSType> tSTypeList = tSTypegroupList.get(0).getTSTypes();
				req.setAttribute("tSTypeList", tSTypeList);
				String typename = "";
				for (int i = 0; i < tSTypeList.size(); i++) {
					if (typename.length() > 0) {
						typename += ",";
					}
					typename += tSTypeList.get(i).getTypename();
				}
				req.setAttribute("typename", typename);
			}
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/surfaceClean/famsAirportrunwaySurfaceclean-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAirportrunwaySurfacecleanController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwaySurfacecleanEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAirportrunwaySurfaceclean, request.getParameterMap());
		List<FamsAirportrunwaySurfacecleanEntity> famsAirportrunwaySurfacecleans = this.FamsAirportrunwaySurfacecleanService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"fams_airportrunway_surfaceclean");
		modelMap.put(NormalExcelConstants.CLASS,FamsAirportrunwaySurfacecleanEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_airportrunway_surfaceclean列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAirportrunwaySurfacecleans);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"fams_airportrunway_surfaceclean");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAirportrunwaySurfacecleanEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_airportrunway_surfaceclean列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsAirportrunwaySurfacecleanEntity> listFamsAirportrunwaySurfacecleanEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAirportrunwaySurfacecleanEntity.class,params);
				for (FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean : listFamsAirportrunwaySurfacecleanEntitys) {
					FamsAirportrunwaySurfacecleanService.save(famsAirportrunwaySurfaceclean);
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
