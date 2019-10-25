package com.gbiac.fams.common.map.controller;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.map.entity.FamsCommonMapEntity;
import com.gbiac.fams.common.map.service.FamsCommonMapServiceI;
import io.swagger.annotations.ApiOperation;
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
 * @Description: 通用地图标注
 * @author 龚道海
 * @date 2019-01-09 11:34:18
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsCommonMapController")
public class FamsCommonMapController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsCommonMapController.class);

	@Autowired
	private FamsCommonMapServiceI famsCommonMapService;
	@Autowired
	private SystemService systemService;

	/**
	 * 根据施工时间获取该时间当天的所有施工地图信息
	 * @param request
	 * @param resp
	 * @param time 时间戳 eg:1344887103
	 * @return
	 */
	@RequestMapping(params = "getMaps")
	@ResponseBody
	public AjaxJson getMaps(HttpServletRequest request, HttpServletResponse resp,String time){
		AjaxJson json=new AjaxJson();
		try {
			//根据施工时间获取该时间当天的所有施工地图信息集合
			List<Map> list=famsCommonMapService.getMaps(time);
			json.setObj(list);
		}catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(MessageConstant.OPTIONERROR);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 通用地图标注列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/common/map/famsCommonMapList");
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
	public void datagrid(FamsCommonMapEntity famsCommonMap,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonMapEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonMap, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsCommonMapService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除通用地图标注
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsCommonMapEntity famsCommonMap, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsCommonMap = systemService.getEntity(FamsCommonMapEntity.class, famsCommonMap.getId());
		message = "通用地图标注删除成功";
		try{
			famsCommonMapService.delete(famsCommonMap);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用地图标注删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除通用地图标注
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用地图标注删除成功";
		try{
			for(String id:ids.split(",")){
				FamsCommonMapEntity famsCommonMap = systemService.getEntity(FamsCommonMapEntity.class, 
				id
				);
				famsCommonMapService.delete(famsCommonMap);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "通用地图标注删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加通用地图标注
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsCommonMapEntity famsCommonMap, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用地图标注添加成功";
		try{
			famsCommonMapService.save(famsCommonMap);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "通用地图标注添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新通用地图标注
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsCommonMapEntity famsCommonMap, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通用地图标注更新成功";
		FamsCommonMapEntity t = famsCommonMapService.get(FamsCommonMapEntity.class, famsCommonMap.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsCommonMap, t);
			famsCommonMapService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "通用地图标注更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 通用地图标注新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsCommonMapEntity famsCommonMap, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsCommonMap.getId())) {
			famsCommonMap = famsCommonMapService.getEntity(FamsCommonMapEntity.class, famsCommonMap.getId());
			req.setAttribute("famsCommonMap", famsCommonMap);
		}
		return new ModelAndView("com/gbiac/fams/common/map/famsCommonMap-add");
	}
	/**
	 * 通用地图标注编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsCommonMapEntity famsCommonMap, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsCommonMap.getId())) {
			famsCommonMap = famsCommonMapService.getEntity(FamsCommonMapEntity.class, famsCommonMap.getId());
			req.setAttribute("famsCommonMap", famsCommonMap);
		}
		return new ModelAndView("com/gbiac/fams/common/map/famsCommonMap-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsCommonMapController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsCommonMapEntity famsCommonMap,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsCommonMapEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsCommonMap, request.getParameterMap());
		List<FamsCommonMapEntity> famsCommonMaps = this.famsCommonMapService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"通用地图标注");
		modelMap.put(NormalExcelConstants.CLASS,FamsCommonMapEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通用地图标注列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsCommonMaps);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsCommonMapEntity famsCommonMap,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"通用地图标注");
    	modelMap.put(NormalExcelConstants.CLASS,FamsCommonMapEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("通用地图标注列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsCommonMapEntity> listFamsCommonMapEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsCommonMapEntity.class,params);
				for (FamsCommonMapEntity famsCommonMap : listFamsCommonMapEntitys) {
					famsCommonMapService.save(famsCommonMap);
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
