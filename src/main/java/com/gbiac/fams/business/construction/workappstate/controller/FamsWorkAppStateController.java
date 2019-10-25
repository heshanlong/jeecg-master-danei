package com.gbiac.fams.business.construction.workappstate.controller;

import com.gbiac.fams.business.construction.workappstate.entity.FamsWorkAppStateDetailEntity;
import com.gbiac.fams.business.construction.workappstate.entity.FamsWorkAppStateEntity;
import com.gbiac.fams.business.construction.workappstate.page.FamsWorkAppStatePage;
import com.gbiac.fams.business.construction.workappstate.service.FamsWorkAppStateServiceI;
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
 * @Description: 施工管理APP端状态配置表
 * @author onlineGenerator
 * @date 2019-03-29 15:55:23
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsWorkAppStateController")
public class FamsWorkAppStateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(FamsWorkAppStateController.class);

	@Autowired
	private FamsWorkAppStateServiceI famsWorkAppStateService;
	@Autowired
	private SystemService systemService;

	/**
	 * 施工管理APP端状态配置表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/construction/workappstate/famsWorkAppStateList");
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
	public void datagrid(FamsWorkAppStateEntity famsWorkAppState,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsWorkAppStateEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkAppState,request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsWorkAppStateService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除施工管理APP端状态配置表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsWorkAppStateEntity famsWorkAppState, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		famsWorkAppState = systemService.getEntity(FamsWorkAppStateEntity.class, famsWorkAppState.getId());
		String message = "施工管理APP端状态配置表删除成功";
		try{
			famsWorkAppStateService.delMain(famsWorkAppState);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "施工管理APP端状态配置表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除施工管理APP端状态配置表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "施工管理APP端状态配置表删除成功";
		try{
			for(String id:ids.split(",")){
				FamsWorkAppStateEntity famsWorkAppState = systemService.getEntity(FamsWorkAppStateEntity.class,
				id
				);
				famsWorkAppStateService.delMain(famsWorkAppState);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "施工管理APP端状态配置表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加施工管理APP端状态配置表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HttpServletRequest request,FamsWorkAppStatePage famsWorkAppStatePage) {
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try {
			famsWorkAppStateService.addMain(famsWorkAppStatePage);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "施工管理APP端状态配置表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新施工管理APP端状态配置表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HttpServletRequest request,FamsWorkAppStatePage famsWorkAppStatePage) {
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			famsWorkAppStateService.updateMain(famsWorkAppStatePage);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新施工管理APP端状态配置表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 施工管理APP端状态配置表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HttpServletRequest req) {
		return new ModelAndView("com/gbiac/fams/business/construction/workappstate/famsWorkAppState-add");
	}
	
	/**
	 * 施工管理APP端状态配置表编辑页面跳转
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HttpServletRequest req) {
		String id = req.getParameter("id");
		FamsWorkAppStatePage famsWorkAppStatePage = new FamsWorkAppStatePage();
		if (StringUtil.isNotEmpty(id)) {
			FamsWorkAppStateEntity famsWorkAppState = famsWorkAppStateService.getEntity(FamsWorkAppStateEntity.class,id);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(famsWorkAppState,famsWorkAppStatePage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//===================================================================================
			//获取参数
			Object id0 = famsWorkAppState.getId();
			//===================================================================================
		    //查询-施工管理APP端状态配置附表
		    String hql0 = "from FamsWorkAppStateDetailEntity where 1 = 1 AND stateId = ? ";
		    List<FamsWorkAppStateDetailEntity> famsWorkAppStateDetailEntityList = systemService.findHql(hql0,id0);
		    if(famsWorkAppStateDetailEntityList==null || famsWorkAppStateDetailEntityList.size()<=0){
		    	famsWorkAppStateDetailEntityList = new ArrayList<FamsWorkAppStateDetailEntity>();
		    	famsWorkAppStateDetailEntityList.add(new FamsWorkAppStateDetailEntity());
		    }
		    famsWorkAppStatePage.setFamsWorkAppStateDetailList(famsWorkAppStateDetailEntityList);
		}
		req.setAttribute("famsWorkAppStatePage", famsWorkAppStatePage);
		return new ModelAndView("com/gbiac/fams/business/construction/workappstate/famsWorkAppState-update");
	}
	
    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(FamsWorkAppStateEntity famsWorkAppState,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(FamsWorkAppStateEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsWorkAppState);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<FamsWorkAppStateEntity> list=this.famsWorkAppStateService.getListByCriteriaQuery(cq, false);
    	List<FamsWorkAppStatePage> pageList=new ArrayList<FamsWorkAppStatePage>();
        if(list!=null&&list.size()>0){
        	for(FamsWorkAppStateEntity entity:list){
        		try{
        			FamsWorkAppStatePage page=new FamsWorkAppStatePage();
        			MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            		Object id0 = entity.getId();
					String hql0 = "from FamsWorkAppStateDetailEntity where 1 = 1 AND stateId = ? ";
					List<FamsWorkAppStateDetailEntity> famsWorkAppStateDetailEntityList = systemService.findHql(hql0,id0);
					page.setFamsWorkAppStateDetailList(famsWorkAppStateDetailEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"施工管理APP端状态配置表");
        map.put(NormalExcelConstants.CLASS,FamsWorkAppStatePage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("施工管理APP端状态配置表列表", "导出人:Jeecg", "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
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
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<FamsWorkAppStatePage> list =  ExcelImportUtil.importExcel(file.getInputStream(), FamsWorkAppStatePage.class, params);
				for (FamsWorkAppStatePage page : list) {
		            famsWorkAppStateService.addMain(page);
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
	/**
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"施工管理APP端状态配置表");
		map.put(NormalExcelConstants.CLASS,FamsWorkAppStatePage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("施工管理APP端状态配置表列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "famsWorkAppStateController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
	
}
