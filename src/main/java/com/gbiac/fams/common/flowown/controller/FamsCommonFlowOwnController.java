package com.gbiac.fams.common.flowown.controller;

import com.gbiac.fams.common.flowown.entity.FamsCommonFlowOwnDetailEntity;
import com.gbiac.fams.common.flowown.entity.FamsCommonFlowOwnEntity;
import com.gbiac.fams.common.flowown.page.FamsCommonFlowOwnPage;
import com.gbiac.fams.common.flowown.service.FamsCommonFlowOwnServiceI;
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
 * @Description: 通用流程权限拥有表
 * @author onlineGenerator
 * @date 2019-01-15 15:28:59
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsCommonFlowOwnController")
public class FamsCommonFlowOwnController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(FamsCommonFlowOwnController.class);

	@Autowired
	private FamsCommonFlowOwnServiceI famsCommonFlowOwnService;
	@Autowired
	private SystemService systemService;

	/**
	 * 通用流程权限拥有表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/common/flowown/famsCommonFlowOwnList");
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
	public void datagrid(FamsCommonFlowOwnEntity famsCommonFlowOwn,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonFlowOwnEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonFlowOwn,request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsCommonFlowOwnService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除通用流程权限拥有表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsCommonFlowOwnEntity famsCommonFlowOwn, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		famsCommonFlowOwn = systemService.getEntity(FamsCommonFlowOwnEntity.class, famsCommonFlowOwn.getId());
		String message = "通用流程权限拥有表删除成功";
		try{
			famsCommonFlowOwnService.delMain(famsCommonFlowOwn);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用流程权限拥有表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除通用流程权限拥有表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "通用流程权限拥有表删除成功";
		try{
			for(String id:ids.split(",")){
				FamsCommonFlowOwnEntity famsCommonFlowOwn = systemService.getEntity(FamsCommonFlowOwnEntity.class,
				id
				);
				famsCommonFlowOwnService.delMain(famsCommonFlowOwn);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "通用流程权限拥有表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加通用流程权限拥有表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HttpServletRequest request,FamsCommonFlowOwnPage famsCommonFlowOwnPage) {
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try {
			famsCommonFlowOwnService.addMain(famsCommonFlowOwnPage);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "通用流程权限拥有表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新通用流程权限拥有表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HttpServletRequest request,FamsCommonFlowOwnPage famsCommonFlowOwnPage) {
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			famsCommonFlowOwnService.updateMain(famsCommonFlowOwnPage);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新通用流程权限拥有表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 通用流程权限拥有表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HttpServletRequest req) {
		return new ModelAndView("com/gbiac/fams/common/flowown/famsCommonFlowOwn-add");
	}
	
	/**
	 * 通用流程权限拥有表编辑页面跳转
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HttpServletRequest req) {
		String id = req.getParameter("id");
		FamsCommonFlowOwnPage famsCommonFlowOwnPage = new FamsCommonFlowOwnPage();
		if (StringUtil.isNotEmpty(id)) {
			FamsCommonFlowOwnEntity famsCommonFlowOwn = famsCommonFlowOwnService.getEntity(FamsCommonFlowOwnEntity.class,id);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(famsCommonFlowOwn,famsCommonFlowOwnPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//===================================================================================
			//获取参数
			Object id0 = famsCommonFlowOwn.getId();
			//===================================================================================
		    //查询-通用流程权限拥有附表
		    String hql0 = "from FamsCommonFlowOwnDetailEntity where 1 = 1 AND flowOwnId = ? ";
		    List<FamsCommonFlowOwnDetailEntity> famsCommonFlowOwnDetailEntityList = systemService.findHql(hql0,id0);
		    if(famsCommonFlowOwnDetailEntityList==null || famsCommonFlowOwnDetailEntityList.size()<=0){
		    	famsCommonFlowOwnDetailEntityList = new ArrayList<FamsCommonFlowOwnDetailEntity>();
		    	famsCommonFlowOwnDetailEntityList.add(new FamsCommonFlowOwnDetailEntity());
		    }
		    famsCommonFlowOwnPage.setFamsCommonFlowOwnDetailList(famsCommonFlowOwnDetailEntityList);
		}
		req.setAttribute("famsCommonFlowOwnPage", famsCommonFlowOwnPage);
		return new ModelAndView("com/gbiac/fams/common/flowown/famsCommonFlowOwn-update");
	}
	
    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(FamsCommonFlowOwnEntity famsCommonFlowOwn,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(FamsCommonFlowOwnEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonFlowOwn);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<FamsCommonFlowOwnEntity> list=this.famsCommonFlowOwnService.getListByCriteriaQuery(cq, false);
    	List<FamsCommonFlowOwnPage> pageList=new ArrayList<FamsCommonFlowOwnPage>();
        if(list!=null&&list.size()>0){
        	for(FamsCommonFlowOwnEntity entity:list){
        		try{
        			FamsCommonFlowOwnPage page=new FamsCommonFlowOwnPage();
        			MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            		Object id0 = entity.getId();
					String hql0 = "from FamsCommonFlowOwnDetailEntity where 1 = 1 AND flowOwnId = ? ";
					List<FamsCommonFlowOwnDetailEntity> famsCommonFlowOwnDetailEntityList = systemService.findHql(hql0,id0);
					page.setFamsCommonFlowOwnDetailList(famsCommonFlowOwnDetailEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"通用流程权限拥有表");
        map.put(NormalExcelConstants.CLASS,FamsCommonFlowOwnPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("通用流程权限拥有表列表", "导出人:Jeecg", "导出信息"));
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
				List<FamsCommonFlowOwnPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), FamsCommonFlowOwnPage.class, params);
				for (FamsCommonFlowOwnPage page : list) {
		            famsCommonFlowOwnService.addMain(page);
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
		map.put(NormalExcelConstants.FILE_NAME,"通用流程权限拥有表");
		map.put(NormalExcelConstants.CLASS,FamsCommonFlowOwnPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("通用流程权限拥有表列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
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
		req.setAttribute("controller_name", "famsCommonFlowOwnController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
	
}
