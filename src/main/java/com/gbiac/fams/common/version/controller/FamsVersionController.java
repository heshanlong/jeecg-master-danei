package com.gbiac.fams.common.version.controller;

import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.version.dao.FamsWorkVersionDao;
import com.gbiac.fams.common.version.entity.FamsVersionEntity;
import com.gbiac.fams.common.version.entity.VersionDto;
import com.gbiac.fams.common.version.service.FamsVersionServiceI;
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
 * @Description: APP版本管理
 * @author onlineGenerator
 * @date 2019-04-01 13:54:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsVersionController")
public class FamsVersionController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsVersionController.class);

	@Autowired
	private FamsVersionServiceI famsVersionService;
	@Autowired
	private SystemService systemService;
	@Autowired
	protected FamsWorkVersionDao famsWorkVersionDao;


	/**
	 * APP版本管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/common/version/famsVersionList");
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
	public void datagrid(FamsVersionEntity famsVersion,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsVersionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsVersion, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsVersionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除APP版本管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsVersionEntity famsVersion, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsVersion = systemService.getEntity(FamsVersionEntity.class, famsVersion.getId());
		message = "APP版本管理删除成功";
		try{
			famsVersionService.delete(famsVersion);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "APP版本管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除APP版本管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "APP版本管理删除成功";
		try{
			for(String id:ids.split(",")){
				FamsVersionEntity famsVersion = systemService.getEntity(FamsVersionEntity.class, 
				id
				);
				famsVersionService.delete(famsVersion);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "APP版本管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加APP版本管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsVersionEntity famsVersion, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "APP版本管理添加成功";
		try{
			//获取前端传过来的文件id
			String files = request.getParameter("files");
			famsVersionService.save(famsVersion,files);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "APP版本管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新APP版本管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsVersionEntity famsVersion, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "APP版本管理更新成功";
		FamsVersionEntity t = famsVersionService.get(FamsVersionEntity.class, famsVersion.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsVersion, t);
			famsVersionService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "APP版本管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * APP版本管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsVersionEntity famsVersion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsVersion.getId())) {
			famsVersion = famsVersionService.getEntity(FamsVersionEntity.class, famsVersion.getId());
			req.setAttribute("famsVersion", famsVersion);
		}
		return new ModelAndView("com/gbiac/fams/common/version/famsVersion-add");
	}
	/**
	 * APP版本管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsVersionEntity famsVersion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsVersion.getId())) {
			famsVersion = famsVersionService.getEntity(FamsVersionEntity.class, famsVersion.getId());
			List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsVersion.getId(), null, null);
			req.setAttribute("famsVersion", famsVersion);
			req.setAttribute("files", Util.filesToMap(files));
		}
		return new ModelAndView("com/gbiac/fams/common/version/famsVersion-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsVersionController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsVersionEntity famsVersion,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsVersionEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsVersion, request.getParameterMap());
		List<FamsVersionEntity> famsVersions = this.famsVersionService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"APP版本管理");
		modelMap.put(NormalExcelConstants.CLASS,FamsVersionEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("APP版本管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsVersions);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsVersionEntity famsVersion,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"APP版本管理");
    	modelMap.put(NormalExcelConstants.CLASS,FamsVersionEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("APP版本管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsVersionEntity> listFamsVersionEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsVersionEntity.class,params);
				for (FamsVersionEntity famsVersion : listFamsVersionEntitys) {
					famsVersionService.save(famsVersion);
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
	 * 版本更新
	 * @param famsVersion
	 * @param request
	 * @param dto.version 版本
	 * @param dto.platform 平台 0苹果  1安卓
	 * @return
	 */
	@RequestMapping(params = "upgrade")
	@ResponseBody
	public AjaxJson upgrade(VersionDto dto) {
		AjaxJson j = new AjaxJson();
		try {
			//版本
			String version=dto.getVersion();
			//平台
			String platform=dto.getPlatform();
			//手机的版本
			long oldVersion=convertVersionFromStringToLong(version);
			//获取该平台的最新版本信息
			Map map=famsWorkVersionDao.getLatestVersionInfoByPlatform(platform);
			if(map!=null){
				//服务器的版本
				long newVersion=convertVersionFromStringToLong(map.get("version").toString());
				if(newVersion>oldVersion){
					j.setObj(map);
					return j;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		j.setSuccess(false);
		j.setMsg("已是最新版本！");
		return j;
	}


	/**
	 * str 2 long
	 * @param version
	 * @return
	 */
	private long convertVersionFromStringToLong(String version){
		if(version==null) return 0;
		String v=version;
		v=v.replace(".", "");
		return Long.parseLong(v);
	}
}
