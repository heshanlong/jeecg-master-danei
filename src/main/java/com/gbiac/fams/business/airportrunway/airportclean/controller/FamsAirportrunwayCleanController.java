package com.gbiac.fams.business.airportrunway.airportclean.controller;
import com.gbiac.fams.business.airportrunway.airportclean.entity.FamsAirportrunwayCleanEntity;
import com.gbiac.fams.business.airportrunway.airportclean.service.FamsAirportrunwayCleanServiceI;
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
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
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
 * @Description: fams_airportrunway_clean
 * @author 江家滨
 * @date 2019-01-16 15:12:38
 * @version V1.0   
 *
 */
@Api(value="famsAirportrunwayRestCleanController",description="跑道除胶",tags="famsAirportrunwayRestCleanController")
@Controller
@RequestMapping("/famsAirportrunwayCleanController")
public class FamsAirportrunwayCleanController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAirportrunwayCleanController.class);

	@Autowired
	private FamsAirportrunwayCleanServiceI famsAirportrunwayCleanService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * fams_airportrunway_clean列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/airportrunway/clean/famsAirportrunwayCleanList");
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
	public void datagrid(FamsAirportrunwayCleanEntity famsAirportrunwayClean,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String startTime = famsAirportrunwayClean.getStartTime();
		String endTime = famsAirportrunwayClean.getEndTime();
		famsAirportrunwayClean.setStartTime("");
		famsAirportrunwayClean.setEndTime("");
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwayCleanEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAirportrunwayClean, request.getParameterMap());
		try{
		//自定义追加查询条件
//			if (oConvertUtils.isNotEmpty(famsAirportrunwayClean.getPeople())) {
//                cq.eq("people", famsAirportrunwayClean.getPeople());
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
		this.famsAirportrunwayCleanService.getDataGridReturn(cq, true);
//		List<FamsAirportrunwayCleanEntity> famsAirportrunwayCleanList = dataGrid.getResults();
//		for (int i = 0; i < famsAirportrunwayCleanList.size(); i++) {
//			famsAirportrunwayCleanList.get(i).setDirectionCleanLocation(famsAirportrunwayCleanList.get(i).getDirection() + "第" + famsAirportrunwayCleanList.get(i).getCleanLocation() +"块" );
//		}
//        dataGrid.setResults(famsAirportrunwayCleanList);
        
//		checkListAPP("19R",1,100, request);
//		getCheckDetailAPP("402881af687ed1f801687ee1e2320009",request);
//		getTypeGroupGrid("", request);
//		doSelectAPP(famsAirportrunwayCleanList.get(0), request);
		
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除fams_airportrunway_clean
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAirportrunwayCleanEntity famsAirportrunwayClean, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAirportrunwayClean = systemService.getEntity(FamsAirportrunwayCleanEntity.class, famsAirportrunwayClean.getId());
		message = "跑道除胶记录删除成功";
		try{
			famsAirportrunwayCleanService.delete(famsAirportrunwayClean);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "跑道除胶记录删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("跑道除胶:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除fams_airportrunway_clean
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "跑道除胶记录删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAirportrunwayCleanEntity famsAirportrunwayClean = systemService.getEntity(FamsAirportrunwayCleanEntity.class, 
				id
				);
				famsAirportrunwayCleanService.delete(famsAirportrunwayClean);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
//			e.printStackTrace();
			message = "跑道除胶记录删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("跑道除胶:"+message,e);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加fams_airportrunway_clean
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAirportrunwayCleanEntity famsAirportrunwayClean, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "跑道除胶记录添加成功";
		try{
//			famsAirportrunwayClean.setCreateBy(ResourceUtil.getSessionUser().getId());
			famsAirportrunwayClean.setCreateDate(new Date());
			famsAirportrunwayClean.setCreateByCn(ResourceUtil.getSessionUser().getRealName());
			famsAirportrunwayClean.setDirectionCleanLocation(famsAirportrunwayClean.getDirection() + "第" + famsAirportrunwayClean.getCleanLocation() +"块" );
			
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsAirportrunwayCleanService.save(famsAirportrunwayClean,map);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "跑道除胶记录添加失败";
//			throw new BusinessException(e.getMessage());
			logger.error("跑道除胶:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新fams_airportrunway_clean
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAirportrunwayCleanEntity famsAirportrunwayClean, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "跑道除胶记录更新成功";
		FamsAirportrunwayCleanEntity t = famsAirportrunwayCleanService.get(FamsAirportrunwayCleanEntity.class, famsAirportrunwayClean.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAirportrunwayClean, t);
			t.setUpdateBy(ResourceUtil.getSessionUser().getId());
			t.setUpdateDate(new Date());
			t.setCreateByCn(ResourceUtil.getSessionUser().getRealName());
			t.setDirectionCleanLocation(famsAirportrunwayClean.getDirection() + "第" + famsAirportrunwayClean.getCleanLocation() +"块" );
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
				
			famsAirportrunwayCleanService.saveOrUpdate(t,map);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "跑道除胶记录更新失败";
//			throw new BusinessException(e.getMessage());
			logger.error("跑道除胶:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * fams_airportrunway_clean新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAirportrunwayCleanEntity famsAirportrunwayClean, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAirportrunwayClean.getId())) {
			famsAirportrunwayClean = famsAirportrunwayCleanService.getEntity(FamsAirportrunwayCleanEntity.class, famsAirportrunwayClean.getId());
			famsAirportrunwayClean.setPeople(ResourceUtil.getSessionUser().getUserNameEn());
			req.setAttribute("famsAirportrunwayClean", famsAirportrunwayClean);
		}else{
			String sql ="SELECT * FROM  t_s_category  where PARENT_CODE = 'A09'" ;
			Session session = systemService.getSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TSCategoryEntity.class);
			List<TSCategoryEntity> categoryList = query.list();
			req.setAttribute("categoryList", categoryList);
			
			req.setAttribute("realName", ResourceUtil.getSessionUser().getRealName());
			long enow = System.currentTimeMillis();
			long snow = System.currentTimeMillis() - 1 * 60 * 60 * 1000;
		    SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd HH:mm");
		    req.setAttribute("edf", sdf.format(enow));
		    req.setAttribute("sdf", sdf.format(snow));
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/clean/famsAirportrunwayClean-add");
	}
	/**
	 * fams_airportrunway_clean编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAirportrunwayCleanEntity famsAirportrunwayClean, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAirportrunwayClean.getId())) {
			famsAirportrunwayClean = famsAirportrunwayCleanService.getEntity(FamsAirportrunwayCleanEntity.class, famsAirportrunwayClean.getId());
			req.setAttribute("famsAirportrunwayClean", famsAirportrunwayClean);
			
			String sql ="SELECT * FROM  t_s_category  where PARENT_CODE = 'A09'" ;
			Session session = systemService.getSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TSCategoryEntity.class);
			List<TSCategoryEntity> categoryList = query.list();
			req.setAttribute("categoryList", categoryList);
			
			//获取附件id集合
			List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsAirportrunwayClean.getId(), null, null);
			req.setAttribute("files", Util.filesToMap(files));
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/clean/famsAirportrunwayClean-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAirportrunwayCleanController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAirportrunwayCleanEntity famsAirportrunwayClean,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwayCleanEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAirportrunwayClean, request.getParameterMap());
		List<FamsAirportrunwayCleanEntity> famsAirportrunwayCleans = this.famsAirportrunwayCleanService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"fams_airportrunway_clean");
		modelMap.put(NormalExcelConstants.CLASS,FamsAirportrunwayCleanEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("跑道除胶列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAirportrunwayCleans);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAirportrunwayCleanEntity famsAirportrunwayClean,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"fams_airportrunway_clean");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAirportrunwayCleanEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_airportrunway_clean列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsAirportrunwayCleanEntity> listFamsAirportrunwayCleanEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAirportrunwayCleanEntity.class,params);
				for (FamsAirportrunwayCleanEntity famsAirportrunwayClean : listFamsAirportrunwayCleanEntitys) {
					famsAirportrunwayCleanService.save(famsAirportrunwayClean);
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
