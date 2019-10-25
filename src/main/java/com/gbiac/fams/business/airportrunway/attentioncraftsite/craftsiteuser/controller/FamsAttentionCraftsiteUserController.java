package com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
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

import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.FamsAttentionCraftsiteUserEntity;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.service.FamsAttentionCraftsiteUserServiceI;
import com.gbiac.fams.business.focusflight.entity.FamsAttentionCraftsiteViewEntity;


/**   
 * @Title: Controller  
 * @Description: 关注机位
 * @author onlineGenerator
 * @date 2019-09-20 15:28:27
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsAttentionCraftsiteUserController")
public class FamsAttentionCraftsiteUserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAttentionCraftsiteUserController.class);

	@Autowired
	private FamsAttentionCraftsiteUserServiceI famsAttentionCraftsiteUserService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 关注机位列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/airportrunway/attentioncraftsite/craftsiteuser/famsAttentionCraftsiteUserList");
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
	public void datagrid(FamsAttentionCraftsiteUserEntity famsAttentionCraftsiteUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String userName = request.getParameter("userName");
		String craftsiteSeria= request.getParameter("craftsiteSeria");
		List<FamsAttentionCraftsiteUserEntity> list =null;
		CriteriaQuery cq = new CriteriaQuery(FamsAttentionCraftsiteUserEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAttentionCraftsiteUser, request.getParameterMap());
		try{
		//自定义追加查询条件
			
			
			
			list=famsAttentionCraftsiteUserService.queryListByEntity(userName, craftsiteSeria);
			
			
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsAttentionCraftsiteUserService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除关注机位
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAttentionCraftsiteUserEntity famsAttentionCraftsiteUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAttentionCraftsiteUser = systemService.getEntity(FamsAttentionCraftsiteUserEntity.class, famsAttentionCraftsiteUser.getId());
		message = "关注机位删除成功";
		try{
			famsAttentionCraftsiteUserService.delete(famsAttentionCraftsiteUser);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "关注机位删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除关注机位
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "关注机位删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAttentionCraftsiteUserEntity famsAttentionCraftsiteUser = systemService.getEntity(FamsAttentionCraftsiteUserEntity.class, 
				id
				);
				famsAttentionCraftsiteUserService.delete(famsAttentionCraftsiteUser);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "关注机位删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加关注机位
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAttentionCraftsiteUserEntity famsAttentionCraftsiteUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "关注机位添加成功";
		try{
			famsAttentionCraftsiteUserService.save(famsAttentionCraftsiteUser);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "关注机位添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新关注机位
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAttentionCraftsiteUserEntity famsAttentionCraftsiteUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "关注机位更新成功";
		FamsAttentionCraftsiteUserEntity t = famsAttentionCraftsiteUserService.get(FamsAttentionCraftsiteUserEntity.class, famsAttentionCraftsiteUser.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAttentionCraftsiteUser, t);
			famsAttentionCraftsiteUserService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "关注机位更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 关注机位新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAttentionCraftsiteUserEntity famsAttentionCraftsiteUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAttentionCraftsiteUser.getId())) {
			famsAttentionCraftsiteUser = famsAttentionCraftsiteUserService.getEntity(FamsAttentionCraftsiteUserEntity.class, famsAttentionCraftsiteUser.getId());
			req.setAttribute("famsAttentionCraftsiteUserPage", famsAttentionCraftsiteUser);
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/attentioncraftsite/craftsiteuser/famsAttentionCraftsiteUser-add");
	}
	/**
	 * 关注机位编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAttentionCraftsiteUserEntity famsAttentionCraftsiteUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAttentionCraftsiteUser.getId())) {
			famsAttentionCraftsiteUser = famsAttentionCraftsiteUserService.getEntity(FamsAttentionCraftsiteUserEntity.class, famsAttentionCraftsiteUser.getId());
			req.setAttribute("famsAttentionCraftsiteUserPage", famsAttentionCraftsiteUser);
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/attentioncraftsite/craftsiteuser/famsAttentionCraftsiteUser-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAttentionCraftsiteUserController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAttentionCraftsiteUserEntity famsAttentionCraftsiteUser,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAttentionCraftsiteUserEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAttentionCraftsiteUser, request.getParameterMap());
		List<FamsAttentionCraftsiteUserEntity> famsAttentionCraftsiteUsers = this.famsAttentionCraftsiteUserService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"关注机位");
		modelMap.put(NormalExcelConstants.CLASS,FamsAttentionCraftsiteUserEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("关注机位列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAttentionCraftsiteUsers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAttentionCraftsiteUserEntity famsAttentionCraftsiteUser,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"关注机位");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAttentionCraftsiteUserEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("关注机位列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsAttentionCraftsiteUserEntity> listFamsAttentionCraftsiteUserEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAttentionCraftsiteUserEntity.class,params);
				for (FamsAttentionCraftsiteUserEntity famsAttentionCraftsiteUser : listFamsAttentionCraftsiteUserEntitys) {
					famsAttentionCraftsiteUserService.save(famsAttentionCraftsiteUser);
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
