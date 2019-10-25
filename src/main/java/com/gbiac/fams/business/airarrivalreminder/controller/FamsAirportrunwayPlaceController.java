package com.gbiac.fams.business.airarrivalreminder.controller;
import com.gbiac.fams.business.airarrivalreminder.entity.FamsAirportrunwayPlaceEntity;
import com.gbiac.fams.business.airarrivalreminder.service.FamsAirportrunwayPlaceServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.sms.entity.TSSmsEntity;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;


/**   
 * @Title: Controller  
 * @Description: 航班入位提醒
 * @author onlineGenerator
 * @date 2019-08-06 18:00:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsAirportrunwayPlaceController")
public class FamsAirportrunwayPlaceController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAirportrunwayPlaceController.class);

	@Autowired
	private FamsAirportrunwayPlaceServiceI famsAirportrunwayPlaceService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 航班入位提醒列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(FamsAirportrunwayPlaceEntity famsAirportrunwayPlace,HttpServletRequest request) {
		
		try {
			famsAirportrunwayPlaceService.sendPhoneMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("com/gbiac/fams/business/airarrivalreminder/famsAirportrunwayPlaceList");
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
	public void datagrid(FamsAirportrunwayPlaceEntity famsAirportrunwayPlace,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwayPlaceEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAirportrunwayPlace, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsAirportrunwayPlaceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除航班入位提醒
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAirportrunwayPlaceEntity famsAirportrunwayPlace, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAirportrunwayPlace = systemService.getEntity(FamsAirportrunwayPlaceEntity.class, famsAirportrunwayPlace.getId());
		message = "航班入位提醒删除成功";
		try{
			famsAirportrunwayPlaceService.delete(famsAirportrunwayPlace);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "航班入位提醒删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除航班入位提醒
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航班入位提醒删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAirportrunwayPlaceEntity famsAirportrunwayPlace = systemService.getEntity(FamsAirportrunwayPlaceEntity.class, 
				id
				);
				famsAirportrunwayPlaceService.delete(famsAirportrunwayPlace);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "航班入位提醒删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加航班入位提醒
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAirportrunwayPlaceEntity famsAirportrunwayPlace, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航班入位提醒添加成功";
		try{
			famsAirportrunwayPlaceService.save(famsAirportrunwayPlace);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "航班入位提醒添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "mydatagrid")
	public void mydatagrid(FamsAirportrunwayPlaceEntity tSSms, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwayPlaceEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSms, request.getParameterMap());
		String curUser = ResourceUtil.getSessionUser().getUserName();
		try {
			// 自定义追加查询条件
			cq.eq("esType", "3");
			cq.eq("esReceiver", curUser);
			cq.addOrder("isRead", SortDirection.asc);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsAirportrunwayPlaceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 提醒时间设置
	 */
	@RequestMapping(params = "role")
	@ResponseBody
	public AjaxJson updateSmsAllRead(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			String curUser = ResourceUtil.getSessionUser().getUserName();
			String sql = "update t_s_sms set is_read=1 where is_read=0 and es_receiver='"+curUser+"'";
			int num = systemService.updateBySqlString(sql);
			j.setObj(num);
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 更新航班入位提醒
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAirportrunwayPlaceEntity famsAirportrunwayPlace, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航班入位提醒更新成功";
		FamsAirportrunwayPlaceEntity t = famsAirportrunwayPlaceService.get(FamsAirportrunwayPlaceEntity.class, famsAirportrunwayPlace.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAirportrunwayPlace, t);
			famsAirportrunwayPlaceService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "航班入位提醒更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 航班入位提醒新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAirportrunwayPlaceEntity famsAirportrunwayPlace, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAirportrunwayPlace.getId())) {
			famsAirportrunwayPlace = famsAirportrunwayPlaceService.getEntity(FamsAirportrunwayPlaceEntity.class, famsAirportrunwayPlace.getId());
			req.setAttribute("famsAirportrunwayPlacePage", famsAirportrunwayPlace);
		}
		return new ModelAndView("com/gbiac/fams/business/airarrivalreminder/famsAirportrunwayPlace-add");
	}
	/**
	 * 航班入位提醒编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAirportrunwayPlaceEntity famsAirportrunwayPlace, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAirportrunwayPlace.getId())) {
			famsAirportrunwayPlace = famsAirportrunwayPlaceService.getEntity(FamsAirportrunwayPlaceEntity.class, famsAirportrunwayPlace.getId());
			req.setAttribute("famsAirportrunwayPlacePage", famsAirportrunwayPlace);
		}
		return new ModelAndView("com/gbiac/fams/business/airarrivalreminder/famsAirportrunwayPlace-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAirportrunwayPlaceController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAirportrunwayPlaceEntity famsAirportrunwayPlace,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwayPlaceEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAirportrunwayPlace, request.getParameterMap());
		List<FamsAirportrunwayPlaceEntity> famsAirportrunwayPlaces = this.famsAirportrunwayPlaceService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"航班入位提醒");
		modelMap.put(NormalExcelConstants.CLASS,FamsAirportrunwayPlaceEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("航班入位提醒列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAirportrunwayPlaces);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAirportrunwayPlaceEntity famsAirportrunwayPlace,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"航班入位提醒");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAirportrunwayPlaceEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("航班入位提醒列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsAirportrunwayPlaceEntity> listFamsAirportrunwayPlaceEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAirportrunwayPlaceEntity.class,params);
				for (FamsAirportrunwayPlaceEntity famsAirportrunwayPlace : listFamsAirportrunwayPlaceEntitys) {
					famsAirportrunwayPlaceService.save(famsAirportrunwayPlace);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(e.getMessage());
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
