package com.gbiac.fams.common.push.controller;

import com.gbiac.fams.common.push.entity.FamsCommonPushEntity;
import com.gbiac.fams.common.push.service.FamsCommonPushServiceI;
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
 * @Description: 通用推送日志
 * @author 龚道海
 * @date 2019-01-09 09:52:57
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsCommonPushController")
public class FamsCommonPushController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsCommonPushController.class);

	@Autowired
	private FamsCommonPushServiceI famsCommonPushService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 通用推送日志列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/common/push/famsCommonPushList");
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
	public void datagrid(FamsCommonPushEntity famsCommonPush,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonPushEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonPush, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsCommonPushService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除通用推送日志
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsCommonPushEntity famsCommonPush, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsCommonPush = systemService.getEntity(FamsCommonPushEntity.class, famsCommonPush.getId());
		message = "通用推送日志删除成功";
		try{
			famsCommonPushService.delete(famsCommonPush);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用推送日志删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除通用推送日志
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用推送日志删除成功";
		try{
			for(String id:ids.split(",")){
				FamsCommonPushEntity famsCommonPush = systemService.getEntity(FamsCommonPushEntity.class, 
				id
				);
				famsCommonPushService.delete(famsCommonPush);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "通用推送日志删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加通用推送日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsCommonPushEntity famsCommonPush, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用推送日志添加成功";
		try{
			famsCommonPushService.save(famsCommonPush);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用推送日志添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新通用推送日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsCommonPushEntity famsCommonPush, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用推送日志更新成功";
		FamsCommonPushEntity t = famsCommonPushService.get(FamsCommonPushEntity.class, famsCommonPush.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsCommonPush, t);
			famsCommonPushService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "通用推送日志更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 通用推送日志新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsCommonPushEntity famsCommonPush, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsCommonPush.getId())) {
			famsCommonPush = famsCommonPushService.getEntity(FamsCommonPushEntity.class, famsCommonPush.getId());
			req.setAttribute("famsCommonPush", famsCommonPush);
		}
		return new ModelAndView("com/gbiac/fams/common/push/famsCommonPush-add");
	}
	/**
	 * 通用推送日志编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsCommonPushEntity famsCommonPush, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsCommonPush.getId())) {
			famsCommonPush = famsCommonPushService.getEntity(FamsCommonPushEntity.class, famsCommonPush.getId());
			req.setAttribute("famsCommonPush", famsCommonPush);
		}
		return new ModelAndView("com/gbiac/fams/common/push/famsCommonPush-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsCommonPushController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsCommonPushEntity famsCommonPush,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonPushEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonPush, request.getParameterMap());
		List<FamsCommonPushEntity> famsCommonPushs = this.famsCommonPushService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"通用推送日志");
		modelMap.put(NormalExcelConstants.CLASS,FamsCommonPushEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通用推送日志列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsCommonPushs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsCommonPushEntity famsCommonPush,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"通用推送日志");
    	modelMap.put(NormalExcelConstants.CLASS,FamsCommonPushEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通用推送日志列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsCommonPushEntity> listFamsCommonPushEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsCommonPushEntity.class,params);
				for (FamsCommonPushEntity famsCommonPush : listFamsCommonPushEntitys) {
					famsCommonPushService.save(famsCommonPush);
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
