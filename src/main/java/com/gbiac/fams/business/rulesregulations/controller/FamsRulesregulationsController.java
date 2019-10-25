package com.gbiac.fams.business.rulesregulations.controller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSUser;
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

import com.gbiac.fams.business.construction.workrole.service.FamsWorkRoleServiceI;
import com.gbiac.fams.business.rulesregulations.entity.FamsRulesregulationsEntity;
import com.gbiac.fams.business.rulesregulations.service.FamsRulesregulationsServiceI;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;


/**   
 * @Title: Controller  
 * @Description: 规章制度
 * @author onlineGenerator
 * @date 2019-01-09 15:45:08
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsRulesregulationsController")
public class FamsRulesregulationsController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsRulesregulationsController.class);

	@Autowired
	private FamsRulesregulationsServiceI famsRulesregulationsService;
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private FamsWorkRoleServiceI famsWorkRoleService;
	


	/**
	 * 规章制度列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/rulesregulations/famsRulesregulationsList");
	}

	/**
	 * 规章制度列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "famsRulesregulationsBackgroundList")
	public ModelAndView famsRulesregulationsBackgroundList(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/rulesregulations/famsRulesregulationsBackgroundList");
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
	public void datagrid(FamsRulesregulationsEntity famsRulesregulations,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsRulesregulationsEntity.class, dataGrid);
		String id = famsRulesregulations.getId();
		famsRulesregulations.setId(null);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsRulesregulations, request.getParameterMap());
		try{
			//自定义追加查询条件
			//假如  RulesName不为空，则为查询时
			if(famsRulesregulations.getKeyword()==null && famsRulesregulations.getRulesName()==null){ //没有查询
				if(StringUtil.isEmpty(id)){
					cq.eq("rulesType", 0);
					cq.isNull("pid");
				}else{
					cq.eq("pid", id);
				}
		 		//主要是在添加目录的时候使用,添加目录时  只查看目录的列表
				//if(cq.getField().contains("addFile_rulesType")){
				//	cq.eq("rulesType", 0);
				//}
			}else{  //查询
				if(famsRulesregulations.getKeyword().equals("") && famsRulesregulations.getRulesName().equals("")){
					Map<String, Object> map = cq.getMap();
					if(map!=null && map.size()>0){
						Iterator keys = map.keySet().iterator(); 
						while(keys.hasNext()){  
						     String key = (String)keys.next();
						     if("createDate".equals(key)){  
						        cq.eq("rulesType", 1);
						     }
						}  
					}else{
						 cq.eq("rulesType", 0);
				    	 cq.isNull("pid");
				    } 
				}else{
					cq.eq("rulesType", 1);
				}
			}
			
			String roleCode = famsWorkRoleService.getRoleCodeByUserId(ResourceUtil.getSessionUser().getId());
			if(roleCode.equals("admin") || roleCode.equals("anquan")){
				//admin 与 anquan  角色能看所有
				cq.or(Restrictions.eq("visible", 0), Restrictions.eq("visible", 1));
			}else{
				//判断是否是飞管部门的人
				String orgCode = ResourceUtil.getSessionUser().getCurrentDepart().getOrgCode();
				if(orgCode.startsWith("A39A15")){
					//飞管部门的人能看所有
					cq.or(Restrictions.eq("visible", 0), Restrictions.eq("visible", 1));
				}else{
					//其它人只能看所有的文件可见的
					cq.eq("visible", 0);
				}
				
			}
			
			//未删除状态
			cq.eq("delType", 0);
			
			//排序
			Map<String, Object> ordermap = new HashMap<String, Object>();
			ordermap.put("rulesType",SortDirection.asc);
			cq.setOrder(ordermap);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsRulesregulationsService.getDataGridReturn(cq, true);
		
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +  request.getContextPath();
		List<FamsRulesregulationsEntity> rList = dataGrid.getResults();
		for(int i=0;i<rList.size();i++){
			FamsRulesregulationsEntity r1 = rList.get(i);
			if(r1.getRulesType() == 0){  //0为目录
				r1.setRulesFileIcon(basePath+"/upload/rulesFileIcon/catalogue.png");
			}else{ //1 为文件
				FamsCommonFileEntity fc = famsRulesregulationsService.getEntity(FamsCommonFileEntity.class, r1.getRulesPathUrl());
				if(fc!=null){
					if(fc.getFileType()!=null && !"".equals(fc.getFileType())){
						r1.setRulesFileIcon(basePath+"/upload/rulesFileIcon/"+ fc.getFileType() +".png");
						r1.setRulesSuffix(fc.getFileType());
						r1.setIconCls(iconCls(fc.getFileType()));
						r1.setRulesPathUrlName(fc.getFilePath());
					}
				}
			}
		}
		
		TagUtil.treegrid(response, dataGrid);
	}
	
	/**
	 * 附件预览页面打开链接
	 * 
	 * @return
	 */
	@RequestMapping(params = "openViewFile")
	public ModelAndView openViewFile(HttpServletRequest request) {
 		String fileid = request.getParameter("fileid");
		FamsCommonFileEntity fileEntity = systemService.getEntity(FamsCommonFileEntity.class, fileid);
		if(fileEntity!=null){
			if ("dwg".equals(fileEntity.getFileType())) {
				
				//request.setAttribute("realpath", rulesPathUrl);
				return new ModelAndView("common/upload/dwgView");
			}else if (FileUtils.isPicture(fileEntity.getFileType())) {
				String rulesPathUrl = fileEntity.getFilePath();
				request.setAttribute("realpath", rulesPathUrl);
				return new ModelAndView("common/upload/imageView");
			}else{
				
				String dd = fileEntity.getFilePath();;
				String swfpath = dd.substring(0, dd.indexOf("."));
				swfpath += ".swf";
				System.out.println(swfpath);
				
				request.setAttribute("swfpath", swfpath);
				return new ModelAndView("common/upload/swfView");
			}
		}
		return null;
	}
	
	
/*	@RequestMapping(params = "openViewFile")
	public ModelAndView openViewFile(HttpServletRequest request) {
		String fileid = request.getParameter("fileid");
		String subclassname = oConvertUtils.getString(request.getParameter("subclassname"), "com.gbiac.fams.business.rulesregulations.entity.FamsRulesregulationsEntity");
		String contentfield = oConvertUtils.getString(request.getParameter("contentfield"));
		Class<?> fileClass = MyClassLoader.getClassByScn(subclassname);// 附件的实际类
		Object fileobj = systemService.getEntity(fileClass, fileid);
		ReflectHelper reflectHelper = new ReflectHelper(fileobj);
		String extend = oConvertUtils.getString(reflectHelper.getMethodValue("extend"));
		
		String rulesPathUrl = request.getParameter("rulesPathUrl");
		extend = rulesPathUrl.split("\\.")[1];
//		extend = "png";
		if ("dwg".equals(extend)) {
			String realpath = oConvertUtils.getString(reflectHelper.getMethodValue("realpath"));
			request.setAttribute("realpath", rulesPathUrl);
			return new ModelAndView("common/upload/dwgView");
		} else if (FileUtils.isPicture(extend)) {
			String realpath = oConvertUtils.getString(reflectHelper.getMethodValue("realpath"));
			request.setAttribute("realpath", rulesPathUrl);
			request.setAttribute("fileid", fileid);
			request.setAttribute("subclassname", subclassname);
			request.setAttribute("contentfield", contentfield);
			return new ModelAndView("common/upload/imageView");
		} else {
			String swfpath = oConvertUtils.getString(reflectHelper.getMethodValue("swfpath"));

			swfpath=swfpath.replace("\\","/");

			request.setAttribute("swfpath", swfpath);
			return new ModelAndView("common/upload/swfView");
		}
	}*/
	
	//获取图标
	public String iconCls(String fileType){
		String iconCls = "";
		switch (fileType) {
		case "doc":
			iconCls = "fa-file-word-o";
			break;
		case "docx":
			iconCls = "fa-file-word-o";
			break;
		case "jpg":
			iconCls = "fa-file-image-o";
			break;
		case "pdf":
			iconCls = "fa-file-pdf-o";
			break;
		case "png":
			iconCls = "fa-file-image-o";
			break;
		case "ppt":
			iconCls = "fa-file-word-o";
			break;
		case "psd":
			iconCls = "fa-file-code-o";
			break;
		case "rar":
			iconCls = "fa-file-zip-o";
			break;
		case "txt":
			iconCls = "fa-file-text-o";
			break;
		case "zip":
			iconCls = "fa-file-zip-o";
			break;
		case "xls":
			iconCls = "fa-file-excel-o";
			break;
		default:
			iconCls = "fa-file-o";
			break;
		}
		return iconCls;
	}
	
	/**
	 * 删除规章制度
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsRulesregulationsEntity famsRulesregulations, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsRulesregulations = systemService.getEntity(FamsRulesregulationsEntity.class, famsRulesregulations.getId());
		message = "规章制度删除成功";
		try{
			//删除前要判断目录里是否有文件
			Integer count = famsRulesregulationsService.getByPidCount(famsRulesregulations.getId());
			if(count == 0){
				famsRulesregulationsService.updateDelType(famsRulesregulations.getId());
				String zg = "";
				if(famsRulesregulations.getRulesType() == 0){
					if(famsRulesregulations.getPid()==null){
						zg = "根目录";
					}else{
						zg = "子目录";
					}
				}else if(famsRulesregulations.getRulesType() == 1){
					zg = "文件";
				}
				message = "["+famsRulesregulations.getRulesName()+"]"+zg+"删除成功。";
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}else{
				//返回提示语
				j.setSuccess(false);
				message = "["+famsRulesregulations.getRulesName()+"]目录下还有子目录或文件存在，无法删除。";
			}
			
			//删除时把目录里的文件都删除
			//famsRulesregulationsService.deletePid(famsRulesregulations.getId());
			
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除规章制度
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "";
		try{
			for(String id:ids.split(",")){
				FamsRulesregulationsEntity famsRulesregulations = systemService.getEntity(FamsRulesregulationsEntity.class, id);
				famsRulesregulationsService.delete(famsRulesregulations);
				//删除时把目录里的文件都删除
				famsRulesregulationsService.deletePid(famsRulesregulations.getId());
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "规章制度删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 
	@RequestMapping(params = "ifNameExist")
	@ResponseBody
	public AjaxJson ifNameExist(FamsRulesregulationsEntity famsRulesregulations, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try{
			String rulesName = famsRulesregulations.getRulesName().replaceAll(" ", "");
			Integer count = famsRulesregulationsService.ifNameExist(famsRulesregulations.getPid(), rulesName,famsRulesregulations.getRulesType());
			j.setObj(count);
			if(count > 0){
				j.setSuccess(false);
				j.setMsg(rulesName+" 文件已存在，请重新上传");
			}
		}catch(Exception e){
			e.printStackTrace();
			j.setMsg("");
			j.setSuccess(false);
		}
		return j;
	}


	/**
	 * 添加规章制度
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsRulesregulationsEntity famsRulesregulations, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "";
		try{
			if(StringUtil.isEmpty(famsRulesregulations.getPid())){
				famsRulesregulations.setPid(null);
			}
			//判断此目录在该层下是否存在
			Integer count = famsRulesregulationsService.ifNameExist(famsRulesregulations.getPid(), famsRulesregulations.getRulesName(),famsRulesregulations.getRulesType());
			if(count==0){
				TSUser tsuser = ResourceUtil.getSessionUser();
				famsRulesregulations.setSysCompanyCode(tsuser.getCurrentDepart().getId());
				famsRulesregulations.setDelType(0);
				if(famsRulesregulations.getRulesType() == 0){  //目录
					if(famsRulesregulations.getPid()==null){
						message = "添加根目录成功";
					}else{
						message = "添加子目录成功";
					}
					famsRulesregulations.setVisible(0);
					String rulesName = famsRulesregulations.getRulesName().replaceAll(" ","");
					famsRulesregulations.setRulesName(rulesName);
					famsRulesregulationsService.save(famsRulesregulations);
				}else if(famsRulesregulations.getRulesType() == 1){  //文件
					
					String rulesPathUrl[] = famsRulesregulations.getRulesPathUrl().split(";");
					for(int i=0;i<rulesPathUrl.length;i++){
						FamsCommonFileEntity fcfe = famsRulesregulationsService.getEntity(FamsCommonFileEntity.class, rulesPathUrl[i]);
						String fileName = fcfe.getFileName().replaceAll(" ","");
						famsRulesregulations.setRulesName(fileName);
						famsRulesregulations.setRulesPathUrl(rulesPathUrl[i]);
						
						FamsRulesregulationsEntity fr = new FamsRulesregulationsEntity();
						fr.setRulesName(famsRulesregulations.getRulesName());
						fr.setRulesType(famsRulesregulations.getRulesType());
						fr.setDelType(famsRulesregulations.getDelType());
						fr.setPid(famsRulesregulations.getPid());
						fr.setRulesPathUrl(rulesPathUrl[i]);
						fr.setVisible(famsRulesregulations.getVisible());
						fr.setKeyword(famsRulesregulations.getKeyword());
						fr.setRemark(famsRulesregulations.getRemark());
						fr.setDispatchDate(famsRulesregulations.getDispatchDate());
						fr.setArticleUnit(famsRulesregulations.getArticleUnit());
						fr.setGenreClassification(famsRulesregulations.getGenreClassification());
						fr.setReplaceRegulations(famsRulesregulations.getReplaceRegulations());
						famsRulesregulationsService.save(fr);
					}
					message = "上传文件成功";
				}
				
				//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}else{
				String fc = famsRulesregulations.getRulesType()==0?"目录":"文件";
				message = "["+famsRulesregulations.getRulesName()+"]"+fc+"名称已存在";
				j.setSuccess(false);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			message = "添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新规章制度
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsRulesregulationsEntity famsRulesregulations, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "规章制度更新成功";
		FamsRulesregulationsEntity t = famsRulesregulationsService.get(FamsRulesregulationsEntity.class, famsRulesregulations.getId());
		try {
			//判断此目录在该层下是否存在
			//Integer count = 0;
			//if(!famsRulesregulations.getRulesName().equals(t.getRulesName())){
			//	count = famsRulesregulationsService.ifNameExist(t.getPid(), famsRulesregulations.getRulesName(),t.getRulesType());
			//}
			//if(count==0){
				MyBeanUtils.copyBeanNotNull2Bean(famsRulesregulations, t);
				if(StringUtil.isEmpty(t.getPid())){
					t.setPid(null);
				}
				famsRulesregulationsService.saveOrUpdate(t);
				String fc = t.getRulesType()==0?"目录":"文件";
				message = "["+t.getRulesName()+"]"+fc+" 更新成功";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			/*}else{
				String fc = t.getRulesType()==0?"目录":"文件";
				message = "["+famsRulesregulations.getRulesName()+"]"+fc+"名称已存在";
				j.setSuccess(false);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			message = "规章制度更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 规章制度新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsRulesregulationsEntity famsRulesregulations, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsRulesregulations.getId())) {
			famsRulesregulations = famsRulesregulationsService.getEntity(FamsRulesregulationsEntity.class, famsRulesregulations.getId());
			req.setAttribute("famsRulesregulationsPage", famsRulesregulations);
		}
		return new ModelAndView("com/gbiac/fams/business/rulesregulations/famsRulesregulations-add");
	}
	
	/**
	 * 规章制度新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddFile")
	public ModelAndView goAddFile(FamsRulesregulationsEntity famsRulesregulations, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsRulesregulations.getId())) {
			famsRulesregulations = famsRulesregulationsService.getEntity(FamsRulesregulationsEntity.class, famsRulesregulations.getId());
			req.setAttribute("famsRulesregulationsPage", famsRulesregulations);
		}
		return new ModelAndView("com/gbiac/fams/business/rulesregulations/famsRulesregulations-addFile");
	}
	/**
	 * 规章制度编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsRulesregulationsEntity famsRulesregulations, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsRulesregulations.getId())) {
			famsRulesregulations = famsRulesregulationsService.getEntity(FamsRulesregulationsEntity.class, famsRulesregulations.getId());
			if(famsRulesregulations.getRulesType() == 0){
				req.setAttribute("famsRulesregulationsPage", famsRulesregulations);
				return new ModelAndView("com/gbiac/fams/business/rulesregulations/famsRulesregulations-update");
			}else{
				FamsRulesregulationsEntity pEntity = famsRulesregulationsService.getEntity(FamsRulesregulationsEntity.class, famsRulesregulations.getPid());
				famsRulesregulations.setpName(pEntity.getRulesName());
				req.setAttribute("famsRulesregulationsPage", famsRulesregulations);
				List<FamsCommonFileEntity> fileList = famsRulesregulationsService.findByProperty(FamsCommonFileEntity.class,"id" ,famsRulesregulations.getRulesPathUrl());
				req.setAttribute("files", Util.filesToMap(fileList));
				return new ModelAndView("com/gbiac/fams/business/rulesregulations/famsRulesregulations-updateFile");
			}
		}
		return null;
	}
	
	
	/**
	 * 文件下载
	 * 
	 * @return
	 */
    @RequestMapping(params = "export")
	public String export(HttpServletRequest request, HttpServletResponse response){
    	
    	String rulesPathUrl = request.getParameter("rulesPathUrl");
    	String path = "";
    	FamsCommonFileEntity fcfe = famsRulesregulationsService.getEntity(FamsCommonFileEntity.class, rulesPathUrl);
    	/*if(fcfe!=null){
    		if(StringUtil.isNotEmpty(fcfe.getFilePath())){
    			String fp = fcfe.getFilePath().substring(0,fcfe.getFilePath().lastIndexOf("/")+1);
    			//path =fp + fcfe.getFileName();
    		}
    	}*/
    	
  		// 取得文件名。
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
				
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
	
		String downLoadPath = request.getRealPath("") + "\\" + fcfe.getFilePath();  //注意不同系统的分隔符
	//	String downLoadPath =filePath.replaceAll("/", "\\\\\\\\");   //replace replaceAll区别 *****  
		System.out.println(downLoadPath);
		
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			
			String userAgent = request.getHeader("User-Agent");
			String formFileName = fcfe.getFileName();
			 if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
	                formFileName = java.net.URLEncoder.encode(formFileName, "UTF-8");
	            } else {
	                // 非IE浏览器的处理：
	                formFileName = new String(formFileName.getBytes("utf-8"), "ISO-8859-1");
	            }
			
			//response.setHeader("Content-disposition", "attachment; filename=" + new String(fcfe.getFileName().getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-disposition", "attachment; filename=" + formFileName);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;	
	}
  

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsRulesregulationsController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsRulesregulationsEntity famsRulesregulations,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsRulesregulationsEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsRulesregulations, request.getParameterMap());
		List<FamsRulesregulationsEntity> famsRulesregulationss = this.famsRulesregulationsService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"规章制度");
		modelMap.put(NormalExcelConstants.CLASS,FamsRulesregulationsEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("规章制度列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsRulesregulationss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsRulesregulationsEntity famsRulesregulations,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"规章制度");
    	modelMap.put(NormalExcelConstants.CLASS,FamsRulesregulationsEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("规章制度列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsRulesregulationsEntity> listFamsRulesregulationsEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsRulesregulationsEntity.class,params);
				for (FamsRulesregulationsEntity famsRulesregulations : listFamsRulesregulationsEntitys) {
					famsRulesregulationsService.save(famsRulesregulations);
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
