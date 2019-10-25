package com.gbiac.fams.business.construction.worknodeinfo.controller;

import com.gbiac.fams.business.construction.worknodeinfo.entity.FamsWorkNodeInfoEntity;
import com.gbiac.fams.business.construction.worknodeinfo.service.FamsWorkNodeInfoServiceI;
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
 * @Description: 施工管理流程节点信息表
 * @author onlineGenerator
 * @date 2019-03-07 09:48:20
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsWorkNodeInfoController")
public class FamsWorkNodeInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsWorkNodeInfoController.class);

	@Autowired
	private FamsWorkNodeInfoServiceI famsWorkNodeInfoService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 施工管理流程节点信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/construction/worknodeinfo/famsWorkNodeInfoList");
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
	public void datagrid(FamsWorkNodeInfoEntity famsWorkNodeInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkNodeInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkNodeInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsWorkNodeInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除施工管理流程节点信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsWorkNodeInfoEntity famsWorkNodeInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsWorkNodeInfo = systemService.getEntity(FamsWorkNodeInfoEntity.class, famsWorkNodeInfo.getId());
		message = "施工管理流程节点信息表删除成功";
		try{
			famsWorkNodeInfoService.delete(famsWorkNodeInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "施工管理流程节点信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除施工管理流程节点信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "施工管理流程节点信息表删除成功";
		try{
			for(String id:ids.split(",")){
				FamsWorkNodeInfoEntity famsWorkNodeInfo = systemService.getEntity(FamsWorkNodeInfoEntity.class, 
				id
				);
				famsWorkNodeInfoService.delete(famsWorkNodeInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "施工管理流程节点信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加施工管理流程节点信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsWorkNodeInfoEntity famsWorkNodeInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "施工管理流程节点信息表添加成功";
		try{
			famsWorkNodeInfoService.save(famsWorkNodeInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "施工管理流程节点信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新施工管理流程节点信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsWorkNodeInfoEntity famsWorkNodeInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "施工管理流程节点信息表更新成功";
		FamsWorkNodeInfoEntity t = famsWorkNodeInfoService.get(FamsWorkNodeInfoEntity.class, famsWorkNodeInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsWorkNodeInfo, t);
			famsWorkNodeInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "施工管理流程节点信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 施工管理流程节点信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsWorkNodeInfoEntity famsWorkNodeInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsWorkNodeInfo.getId())) {
			famsWorkNodeInfo = famsWorkNodeInfoService.getEntity(FamsWorkNodeInfoEntity.class, famsWorkNodeInfo.getId());
			req.setAttribute("famsWorkNodeInfo", famsWorkNodeInfo);
		}
		return new ModelAndView("com/gbiac/fams/business/construction/worknodeinfo/famsWorkNodeInfo-add");
	}
	/**
	 * 施工管理流程节点信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsWorkNodeInfoEntity famsWorkNodeInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsWorkNodeInfo.getId())) {
			famsWorkNodeInfo = famsWorkNodeInfoService.getEntity(FamsWorkNodeInfoEntity.class, famsWorkNodeInfo.getId());
			req.setAttribute("famsWorkNodeInfo", famsWorkNodeInfo);
		}
		return new ModelAndView("com/gbiac/fams/business/construction/worknodeinfo/famsWorkNodeInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsWorkNodeInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsWorkNodeInfoEntity famsWorkNodeInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkNodeInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkNodeInfo, request.getParameterMap());
		List<FamsWorkNodeInfoEntity> famsWorkNodeInfos = this.famsWorkNodeInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"施工管理流程节点信息表");
		modelMap.put(NormalExcelConstants.CLASS,FamsWorkNodeInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("施工管理流程节点信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsWorkNodeInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsWorkNodeInfoEntity famsWorkNodeInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"施工管理流程节点信息表");
    	modelMap.put(NormalExcelConstants.CLASS,FamsWorkNodeInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("施工管理流程节点信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsWorkNodeInfoEntity> listFamsWorkNodeInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsWorkNodeInfoEntity.class,params);
				for (FamsWorkNodeInfoEntity famsWorkNodeInfo : listFamsWorkNodeInfoEntitys) {
					famsWorkNodeInfoService.save(famsWorkNodeInfo);
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
