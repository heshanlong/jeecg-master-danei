package com.gbiac.fams.common.reply.controller;

import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
import com.gbiac.fams.common.reply.service.FamsCommonReplyServiceI;
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
 * @Description: 通用回复表
 * @author 龚道海
 * @date 2019-01-09 10:48:45
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsCommonReplyController")
public class FamsCommonReplyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsCommonReplyController.class);

	@Autowired
	private FamsCommonReplyServiceI famsCommonReplyService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 通用回复表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/common/reply/famsCommonReplyList");
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
	public void datagrid(FamsCommonReplyEntity famsCommonReply,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonReplyEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonReply, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsCommonReplyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除通用回复表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsCommonReplyEntity famsCommonReply, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsCommonReply = systemService.getEntity(FamsCommonReplyEntity.class, famsCommonReply.getId());
		message = "通用回复表删除成功";
		try{
			famsCommonReplyService.delete(famsCommonReply);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用回复表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除通用回复表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用回复表删除成功";
		try{
			for(String id:ids.split(",")){
				FamsCommonReplyEntity famsCommonReply = systemService.getEntity(FamsCommonReplyEntity.class, 
				id
				);
				famsCommonReplyService.delete(famsCommonReply);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "通用回复表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加通用回复表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsCommonReplyEntity famsCommonReply, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用回复表添加成功";
		try{
			famsCommonReplyService.save(famsCommonReply);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用回复表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新通用回复表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsCommonReplyEntity famsCommonReply, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用回复表更新成功";
		FamsCommonReplyEntity t = famsCommonReplyService.get(FamsCommonReplyEntity.class, famsCommonReply.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsCommonReply, t);
			famsCommonReplyService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "通用回复表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 通用回复表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsCommonReplyEntity famsCommonReply, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsCommonReply.getId())) {
			famsCommonReply = famsCommonReplyService.getEntity(FamsCommonReplyEntity.class, famsCommonReply.getId());
			req.setAttribute("famsCommonReply", famsCommonReply);
		}
		return new ModelAndView("com/gbiac/fams/common/reply/famsCommonReply-add");
	}
	/**
	 * 通用回复表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsCommonReplyEntity famsCommonReply, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsCommonReply.getId())) {
			famsCommonReply = famsCommonReplyService.getEntity(FamsCommonReplyEntity.class, famsCommonReply.getId());
			req.setAttribute("famsCommonReply", famsCommonReply);
		}
		return new ModelAndView("com/gbiac/fams/common/reply/famsCommonReply-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsCommonReplyController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsCommonReplyEntity famsCommonReply,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonReplyEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonReply, request.getParameterMap());
		List<FamsCommonReplyEntity> famsCommonReplys = this.famsCommonReplyService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"通用回复表");
		modelMap.put(NormalExcelConstants.CLASS,FamsCommonReplyEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通用回复表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsCommonReplys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsCommonReplyEntity famsCommonReply,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"通用回复表");
    	modelMap.put(NormalExcelConstants.CLASS,FamsCommonReplyEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通用回复表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsCommonReplyEntity> listFamsCommonReplyEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsCommonReplyEntity.class,params);
				for (FamsCommonReplyEntity famsCommonReply : listFamsCommonReplyEntitys) {
					famsCommonReplyService.save(famsCommonReply);
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
