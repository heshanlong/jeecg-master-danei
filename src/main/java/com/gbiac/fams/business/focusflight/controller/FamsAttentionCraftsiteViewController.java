package com.gbiac.fams.business.focusflight.controller;
import com.gbiac.fams.business.focusflight.dao.FamsAttentionCraftsiteViewDao;
import com.gbiac.fams.business.focusflight.entity.FamsAttentionCraftsiteViewEntity;
import com.gbiac.fams.business.focusflight.service.FamsAttentionCraftsiteViewServiceI;

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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
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
 * @Description: 查看关注
 * @author onlineGenerator
 * @date 2019-08-27 08:43:32
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsAttentionCraftsiteViewController")
public class FamsAttentionCraftsiteViewController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAttentionCraftsiteViewController.class);


	
	@Autowired
	private FamsAttentionCraftsiteViewServiceI famsAttentionCraftsiteViewService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsAttentionCraftsiteViewDao famsAttentionCraftsiteViewDao;
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FamsAttentionCraftsiteViewEntity famsAttentionCraftsiteView,FamsAttentionCraftsiteViewDao famsAttentionCraftsiteViewDao,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String name = request.getParameter("name");
		String flycode= request.getParameter("flycode");
		String serial = request.getParameter("serial");
		List<FamsAttentionCraftsiteViewEntity> list =null;
		CriteriaQuery cq = new CriteriaQuery(FamsAttentionCraftsiteViewEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAttentionCraftsiteView, request.getParameterMap());
		try {
			list=famsAttentionCraftsiteViewService.queryListByEntity(name, serial, flycode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cq.add();
		this.famsAttentionCraftsiteViewService.getDataGridReturn(cq, true);
		dataGrid.setResults(list);
		TagUtil.datagrid(response, dataGrid);
		
		
	}

	/**
	 * 查看关注列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/focusflight/famsAttentionCraftsiteViewList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */


	
	/**
	 * 删除查看关注
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAttentionCraftsiteViewEntity famsAttentionCraftsiteView, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAttentionCraftsiteView = systemService.getEntity(FamsAttentionCraftsiteViewEntity.class, famsAttentionCraftsiteView.getId());
		message = "查看关注删除成功";
		try{
			famsAttentionCraftsiteViewService.delete(famsAttentionCraftsiteView);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "查看关注删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 更新查看关注
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAttentionCraftsiteViewEntity famsAttentionCraftsiteView, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "查看关注更新成功";
		FamsAttentionCraftsiteViewEntity t = famsAttentionCraftsiteViewService.get(FamsAttentionCraftsiteViewEntity.class, famsAttentionCraftsiteView.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAttentionCraftsiteView, t);
			famsAttentionCraftsiteViewService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "查看关注更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 查看关注编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAttentionCraftsiteViewEntity famsAttentionCraftsiteView, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAttentionCraftsiteView.getId())) {
			System.out.println("张大喵");
			
			try {
				List<FamsAttentionCraftsiteViewEntity> list = list=famsAttentionCraftsiteViewService.queryListByEntity("", "", "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			famsAttentionCraftsiteView = famsAttentionCraftsiteViewService.getEntity(FamsAttentionCraftsiteViewEntity.class, famsAttentionCraftsiteView.getId());
			req.setAttribute("list", famsAttentionCraftsiteView);
		}
		return new ModelAndView("com/gbiac/fams/business/focusflight/famsAttentionCraftsiteView-update");
	}
	

	
	
}
