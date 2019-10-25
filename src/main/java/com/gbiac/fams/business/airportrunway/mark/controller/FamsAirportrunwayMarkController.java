package com.gbiac.fams.business.airportrunway.mark.controller;
import com.gbiac.fams.business.airportrunway.mark.entity.FamsAirportrunwayMarkEntity;
import com.gbiac.fams.business.airportrunway.mark.service.FamsAirportrunwayMarkServiceI;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;

import org.jeecgframework.jwt.util.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;
import java.util.HashMap;

import org.jeecgframework.core.util.ExceptionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: fams_airportrunway_mark
 * @author 江家滨
 * @date 2019-01-22 11:17:47
 * @version V1.0   
 *
 */
@Api(value="famsAirportrunwayMarkController",description="标志线维护",tags="famsAirportrunwayMarkController")

@Controller
@RequestMapping("/famsAirportrunwayMarkController")
public class FamsAirportrunwayMarkController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAirportrunwayMarkController.class);

	@Autowired
	private FamsAirportrunwayMarkServiceI famsAirportrunwayMarkService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * fams_airportrunway_mark列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/airportrunway/mark/famsAirportrunwayMarkList");
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
	public void datagrid(FamsAirportrunwayMarkEntity famsAirportrunwayMark,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String startTime = famsAirportrunwayMark.getStartTime();
		String endTime = famsAirportrunwayMark.getEndTime();
		famsAirportrunwayMark.setStartTime("");
		famsAirportrunwayMark.setEndTime("");
		
//		String taxiwayMark1 = famsAirportrunwayMark.getTaxiwayMark();
//		famsAirportrunwayMark.setTaxiwayMark("");
		
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwayMarkEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAirportrunwayMark, request.getParameterMap());
		try{
		//自定义追加查询条件
//			if (oConvertUtils.isNotEmpty(taxiwayMark1)) {
//                cq.like("taxiwayMark", "%"+taxiwayMark1+"%");
//            }
			if (oConvertUtils.isNotEmpty(startTime)) {
                cq.ge("startTime", startTime);
            }
			if (oConvertUtils.isNotEmpty(endTime)) {
                cq.le("endTime", endTime);
            }
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsAirportrunwayMarkService.getDataGridReturn(cq, true);
		
		List<FamsAirportrunwayMarkEntity> famsAirportrunwayMarkList = dataGrid.getResults();
		for (int i = 0; i < famsAirportrunwayMarkList.size(); i++) {
			FamsAirportrunwayMarkEntity mark = famsAirportrunwayMarkList.get(i);
			String taxiwayMark = mark.getTaxiwayMark();
			
			Session session = systemService.getSession();
			List<TSCategoryEntity> typegroupList = new ArrayList<TSCategoryEntity>();
			String sql ="SELECT * FROM  t_s_category  where PARENT_CODE like 'A10%'" ;
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TSCategoryEntity.class);
			typegroupList = query.list();

			if (null != taxiwayMark) {
				if (taxiwayMark.length() > 0) {
					String[] couldSignArray =taxiwayMark.split(",");
					String str = "";
					for (int e = 0; e < typegroupList.size(); e++) {
						for (int o = 0; o < couldSignArray.length; o++) {
							if (couldSignArray[o].equals(typegroupList.get(e).getCode()) ) {
								if (str.length() > 0 ) {
									str += ",";
								}
								str += typegroupList.get(e).getName();
							}
						}
					}
					mark.setNewSign(str);
//					taxiwayMark=str;
				}
			}
		
//			String newSign = "";
//			if (null != mark.getRunwayLogo() && !mark.getRunwayLogo().isEmpty()) {
//				newSign += "跑道标志";
//			}
//			if (null != mark.getTaxiwayMark() && !mark.getTaxiwayMark().isEmpty()) {
//				if (newSign.length() > 0 ) {
//					newSign += ",";
//				}
//				newSign += "滑行道标志";
//			}
//			if (null != mark.getCouldSign() && !mark.getCouldSign().isEmpty()) {
//				if (newSign.length() > 0 ) {
//					newSign += ",";
//				}
//				newSign += "机坪/服务车道标志";
//			}
//			famsAirportrunwayMarkList.get(i).setNewSign(newSign);
		}
        dataGrid.setResults(famsAirportrunwayMarkList);
        
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除fams_airportrunway_mark
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAirportrunwayMarkEntity famsAirportrunwayMark, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAirportrunwayMark = systemService.getEntity(FamsAirportrunwayMarkEntity.class, famsAirportrunwayMark.getId());
		message = "标志线维护数据删除成功";
		try{
			famsAirportrunwayMarkService.delete(famsAirportrunwayMark);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "标志线维护数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("标志线维护:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除fams_airportrunway_mark
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "标志线维护数据删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAirportrunwayMarkEntity famsAirportrunwayMark = systemService.getEntity(FamsAirportrunwayMarkEntity.class, 
				id
				);
				famsAirportrunwayMarkService.delete(famsAirportrunwayMark);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
//			e.printStackTrace();
			message = "标志线维护数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("标志线维护:"+message,e);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加fams_airportrunway_mark
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAirportrunwayMarkEntity famsAirportrunwayMark, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "标志线维护数据添加成功";
		try{
			famsAirportrunwayMark.setCreateByCn(ResourceUtil.getSessionUser().getRealName());
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsAirportrunwayMarkService.save(famsAirportrunwayMark,map);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "标志线维护数据添加失败";
//			throw new BusinessException(e.getMessage());
			logger.error("标志线维护:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新fams_airportrunway_mark
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAirportrunwayMarkEntity famsAirportrunwayMark, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "标志线维护数据更新成功";
		FamsAirportrunwayMarkEntity t = famsAirportrunwayMarkService.get(FamsAirportrunwayMarkEntity.class, famsAirportrunwayMark.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAirportrunwayMark, t);
			t.setCreateByCn(ResourceUtil.getSessionUser().getRealName());
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsAirportrunwayMarkService.saveOrUpdate(t,map);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "标志线维护数据更新失败";
//			throw new BusinessException(e.getMessage());
			logger.error("标志线维护:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * fams_airportrunway_mark新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAirportrunwayMarkEntity famsAirportrunwayMark, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAirportrunwayMark.getId())) {
			famsAirportrunwayMark = famsAirportrunwayMarkService.getEntity(FamsAirportrunwayMarkEntity.class, famsAirportrunwayMark.getId());
			req.setAttribute("famsAirportrunwayMark", famsAirportrunwayMark);
		}else{
			String sql ="SELECT * FROM  t_s_category  where PARENT_CODE = 'A08'" ;
			Session session = systemService.getSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TSCategoryEntity.class);
			List<TSCategoryEntity> categoryList = query.list();
			req.setAttribute("categoryList", categoryList);
			
			String sql2 ="SELECT * FROM  t_s_category  where PARENT_CODE = 'A10'" ;
			Session session2 = systemService.getSession();
			SQLQuery query2 = session2.createSQLQuery(sql2);
			query2.addEntity(TSCategoryEntity.class);
			List<TSCategoryEntity> categoryList2 = query2.list();
			req.setAttribute("categoryList2", categoryList2);
			
			req.setAttribute("realName", ResourceUtil.getSessionUser().getRealName());
			
			long enow = System.currentTimeMillis();
			long snow = System.currentTimeMillis() - 1 * 60 * 60 * 1000;
		    SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd HH:mm");
		    req.setAttribute("edf", sdf.format(enow));
		    req.setAttribute("sdf", sdf.format(snow));
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/mark/famsAirportrunwayMark-add");
	}
	/**
	 * fams_airportrunway_mark编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAirportrunwayMarkEntity famsAirportrunwayMark, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAirportrunwayMark.getId())) {
			famsAirportrunwayMark = famsAirportrunwayMarkService.getEntity(FamsAirportrunwayMarkEntity.class, famsAirportrunwayMark.getId());
			req.setAttribute("famsAirportrunwayMark", famsAirportrunwayMark);
			
			String sql ="SELECT * FROM  t_s_category  where PARENT_CODE = 'A08'" ;
			Session session = systemService.getSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TSCategoryEntity.class);
			List<TSCategoryEntity> categoryList = query.list();
			req.setAttribute("categoryList", categoryList);
			String sql2 ="SELECT * FROM  t_s_category  where PARENT_CODE = 'A10'" ;
			Session session2 = systemService.getSession();
			SQLQuery query2 = session2.createSQLQuery(sql2);
			query2.addEntity(TSCategoryEntity.class);
			List<TSCategoryEntity> categoryList2 = query2.list();
			req.setAttribute("categoryList2", categoryList2);
			//获取附件id集合
			List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsAirportrunwayMark.getId(), null, null);
			req.setAttribute("files", Util.filesToMap(files));
		}
		return new ModelAndView("com/gbiac/fams/business/airportrunway/mark/famsAirportrunwayMark-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAirportrunwayMarkController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAirportrunwayMarkEntity famsAirportrunwayMark,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAirportrunwayMarkEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAirportrunwayMark, request.getParameterMap());
		List<FamsAirportrunwayMarkEntity> famsAirportrunwayMarks = this.famsAirportrunwayMarkService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"fams_airportrunway_mark");
		modelMap.put(NormalExcelConstants.CLASS,FamsAirportrunwayMarkEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_airportrunway_mark列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAirportrunwayMarks);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAirportrunwayMarkEntity famsAirportrunwayMark,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"fams_airportrunway_mark");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAirportrunwayMarkEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_airportrunway_mark列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsAirportrunwayMarkEntity> listFamsAirportrunwayMarkEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAirportrunwayMarkEntity.class,params);
				for (FamsAirportrunwayMarkEntity famsAirportrunwayMark : listFamsAirportrunwayMarkEntitys) {
					famsAirportrunwayMarkService.save(famsAirportrunwayMark);
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
