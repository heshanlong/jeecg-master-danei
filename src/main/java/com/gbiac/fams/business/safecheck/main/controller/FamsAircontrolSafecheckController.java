package com.gbiac.fams.business.safecheck.main.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.gbiac.fams.business.safecheck.detail.entity.FamsAircontrolSafecheckdetailEntity;
import com.gbiac.fams.business.safecheck.detail.service.FamsAircontrolSafecheckdetailServiceI;
import com.gbiac.fams.business.safecheck.main.entity.FamsAircontrolSafecheckEntity;
import com.gbiac.fams.business.safecheck.main.service.FamsAircontrolSafecheckServiceI;
import com.gbiac.fams.business.safecheck.safecheckconfig.entity.FamsAircontrolSafecheckconfigEntity;
import com.gbiac.fams.business.safecheck.safecheckconfig.entity.FamsAircontrolSafecheckconfigListEntity;
import com.gbiac.fams.business.safecheck.safecheckconfig.service.FamsAircontrolSafecheckconfigServiceI;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;

/**
 * @Title: Controller
 * @Description: 航班保障作业检查单主记录
 * @author 邓正辉
 * @date 2019-01-21 13:50:38
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/famsAircontrolSafecheckController")
public class FamsAircontrolSafecheckController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAircontrolSafecheckController.class);

	@Autowired
	private FamsAircontrolSafecheckServiceI famsAircontrolSafecheckService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsAircontrolSafecheckdetailServiceI safecheckdetailServiceI;
	@Autowired
	private FamsAircontrolSafecheckconfigServiceI famsAircontrolSafecheckconfigService;

	/**
	 * 航班保障作业检查单主记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/safecheck/main/famsAircontrolSafecheckList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FamsAircontrolSafecheckEntity famsAircontrolSafecheck, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String message = "";

		if (!famsAircontrolSafecheck.getAirNumber().isEmpty()) {
			famsAircontrolSafecheck.setAirNumber(famsAircontrolSafecheck.getAirNumber().toUpperCase());
		}
		CriteriaQuery cq = new CriteriaQuery(FamsAircontrolSafecheckEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAircontrolSafecheck,
				request.getParameterMap());
		try {
			// 自定义追加查询条件
			cq.notEq("status", "2");
		} catch (Exception e) {
			message = "查询失败";
			throw new BusinessException(message);
		}
		cq.add();
		this.famsAircontrolSafecheckService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

//	/**
//	 * 删除航班保障作业检查单主记录
//	 *
//	 * @return
//	 */
//	@RequestMapping(params = "doDel")
//	@ResponseBody
//	public AjaxJson doDel(FamsAircontrolSafecheckEntity famsAircontrolSafecheck, HttpServletRequest request) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		famsAircontrolSafecheck = systemService.getEntity(FamsAircontrolSafecheckEntity.class, famsAircontrolSafecheck.getId());
//		message = "航班保障作业检查单主记录删除成功";
//		try{
//			famsAircontrolSafecheckService.delete(famsAircontrolSafecheck);
//			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
//		}catch(Exception e){
//			e.printStackTrace();
//			message = "航班保障作业检查单主记录删除失败";
//			throw new BusinessException(message);
//		}
//		j.setMsg(message);
//		return j;
//	}
//
//	/**
//	 * 批量删除航班保障作业检查单主记录
//	 *
//	 * @return
//	 */
//	 @RequestMapping(params = "doBatchDel")
//	@ResponseBody
//	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "航班保障作业检查单主记录删除成功";
//		try{
//			for(String id:ids.split(",")){
//				FamsAircontrolSafecheckEntity famsAircontrolSafecheck = systemService.getEntity(FamsAircontrolSafecheckEntity.class,
//				id
//				);
//				famsAircontrolSafecheckService.delete(famsAircontrolSafecheck);
//				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			message = "航班保障作业检查单主记录删除失败";
//			throw new BusinessException(message);
//		}
//		j.setMsg(message);
//		return j;
//	}

	/**
	 * 删除航班保障作业检查单主记录
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAircontrolSafecheckEntity famsAircontrolSafecheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAircontrolSafecheck = systemService.getEntity(FamsAircontrolSafecheckEntity.class,
				famsAircontrolSafecheck.getId());
		message = "航班保障作业检查单主记录删除成功";
		try {
			famsAircontrolSafecheck.setStatus("2");
			famsAircontrolSafecheckService.saveOrUpdate(famsAircontrolSafecheck);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "航班保障作业检查单主记录删除失败";
			throw new BusinessException(message);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除航班保障作业检查单主记录
	 *
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航班保障作业检查单主记录删除成功";
		try {
			for (String id : ids.split(",")) {
				FamsAircontrolSafecheckEntity famsAircontrolSafecheck = systemService
						.getEntity(FamsAircontrolSafecheckEntity.class, id);
				famsAircontrolSafecheck.setStatus("2");
				famsAircontrolSafecheckService.saveOrUpdate(famsAircontrolSafecheck);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "航班保障作业检查单主记录删除失败";
			throw new BusinessException(message);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加航班保障作业检查单主记录
	 *  为测试
	 * @return
	 */
	
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAircontrolSafecheckEntity famsAircontrolSafecheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String radioResult=request.getParameter("radioResult");
		try {
			String pcture = request.getParameter("picture");
			famsAircontrolSafecheckService.save(famsAircontrolSafecheck,radioResult,pcture);

			message = "航班保障作业检查单主记录添加成功";
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "航班保障作业检查单主记录添加失败";
			logger.error("航班保障作业检查单:" + message, e);
		}
		j.setMsg(message);
		return j;
		
	}
//	@RequestMapping(params = "doAdd")
//	@ResponseBody
//	public AjaxJson doAdd(HttpServletRequest request) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		List<FamsAircontrolSafecheckdetailEntity> details = new ArrayList<>();
//		Map<String, String[]> maps = request.getParameterMap();
//		Set<Entry<String, String[]>> sets = maps.entrySet();
//		try {
//			for (Entry<String, String[]> e : sets) {
//				String name = e.getKey();
//				String[] value = e.getValue();
//				String[] splVal = value[0].split(":");
//				if (splVal.length > 1&&("1".equals(splVal[1]) || "2".equals(splVal[1]) || "3".equals(splVal[1])) ) {
//					// 获取到radio单选中的选择项，对其进行拼接
//					FamsAircontrolSafecheckdetailEntity fascdEntity = new FamsAircontrolSafecheckdetailEntity();
//					fascdEntity.setCheckConfigiId(name);
//					fascdEntity.setResult(splVal[1]);
//
//					// 根据name获取对应的传进来的值
//					String arrayTime = request.getParameter(name + ".arrayTime");
//					if (arrayTime != "" && arrayTime != null) {
//						fascdEntity.setArrayTime(formatterTime.parse(arrayTime));
//					}
//					String completeTime = request.getParameter(name + ".completeTime");
//					if (completeTime != "" && completeTime != null) {
//						fascdEntity.setCompleteTime(formatterTime.parse(completeTime));
//					}
//					if ("2".equals(splVal[1])) {
//						// 不符合项，获取对应的结果，并将其进行封装
//						fascdEntity.setDealResult(request.getParameter(name + ".action"));
//					}
//					details.add(fascdEntity);
//
//				}
//			}
//
//			FamsAircontrolSafecheckEntity fasc = new FamsAircontrolSafecheckEntity();
//
//			String checkDate = request.getParameter("checkDate");
//			if (checkDate != "" && checkDate != null) {
//				fasc.setCheckDate(formatter.parse(checkDate));
//			}
//			String actualDate = request.getParameter("actualDate");
//			if (actualDate != "" && actualDate != null) {
//				fasc.setActualDate(formatter.parse(actualDate));
//			}
//			String preDate = request.getParameter("preDate");
//			if (preDate != "" && preDate != null) {
//				fasc.setPreDate(formatter.parse(preDate));
//			}
//			
//			String checkTimeStart = request.getParameter("checkTimeStart");
//			if(checkTimeStart!="" && checkTimeStart!=null) {
//				fasc.setCheckTimeStart(formatterTime.parse(checkTimeStart));
//			}
//			String checkTimeEnd = request.getParameter("checkTimeEnd");
//			if(checkTimeEnd!="" && checkTimeEnd!=null) {
//				fasc.setCheckTimeEnd(formatterTime.parse(checkTimeEnd));
//			}
//			
//			fasc.setId(request.getParameter("id"));
//			fasc.setAirPlanId(request.getParameter("airPlanId"));
//			fasc.setAirNumber(request.getParameter("airNumber"));
//			fasc.setAirLine(request.getParameter("airLine"));
//			fasc.setAirSlots(request.getParameter("airSlots"));
//			fasc.setAirModel(request.getParameter("airModel"));
//			fasc.setAirTail(request.getParameter("airTail"));
//		
//
//			String pcture = request.getParameter("picture");
//			famsAircontrolSafecheckService.save(fasc, details, pcture);
//
//			message = "航班保障作业检查单主记录添加成功";
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
//		} catch (Exception e) {
//			message = "航班保障作业检查单主记录添加失败";
//			logger.error("航班保障作业检查单:" + message, e);
//		}
//		j.setMsg(message);
//		return j;
//	}

	/**
	 * 更新航班保障作业检查单主记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAircontrolSafecheckEntity famsAircontrolSafecheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航班保障作业检查单主记录更新成功";
		String pcture = request.getParameter("picture");

		FamsAircontrolSafecheckEntity t = famsAircontrolSafecheckService.get(FamsAircontrolSafecheckEntity.class,
				famsAircontrolSafecheck.getId());
		try {
			String radioResult = request.getParameter("radioResult");

			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolSafecheck, t);
			// 自定义的更新
			famsAircontrolSafecheckService.update(t, radioResult, pcture);

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "航班保障作业检查单主记录更新失败";
			logger.error("航班保障作业检查单:" + message, e);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 航班保障作业检查单主记录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAircontrolSafecheckEntity famsAircontrolSafecheck, HttpServletRequest req) {
		String message;
		try {
			if (StringUtil.isNotEmpty(famsAircontrolSafecheck.getId())) {
				famsAircontrolSafecheck = famsAircontrolSafecheckService.getEntity(FamsAircontrolSafecheckEntity.class,
						famsAircontrolSafecheck.getId());
				req.setAttribute("famsAircontrolSafecheck", famsAircontrolSafecheck);

			}
			// 时间点检查项
			List<FamsAircontrolSafecheckconfigEntity> one = famsAircontrolSafecheckconfigService
					.getConfigList(FamsAircontrolSafecheckconfigEntity.class, "时间点检查项", "1", "1");
			// 作业内容检查项
			List<FamsAircontrolSafecheckconfigEntity> two = famsAircontrolSafecheckconfigService
					.getConfigList(FamsAircontrolSafecheckconfigEntity.class, "作业内容检查项", "1", "1");

			List<FamsAircontrolSafecheckconfigListEntity> config = new ArrayList<FamsAircontrolSafecheckconfigListEntity>();

			config.add(new FamsAircontrolSafecheckconfigListEntity("时间点检查项", one, null));
			config.add(new FamsAircontrolSafecheckconfigListEntity("作业内容检查项", two, null));

			req.setAttribute("famsAircontrolSafecheckconfigListEntity", config);

			req.setAttribute("defaultDate", new Date());

//		systemService.getFamsNumberByTSTypegroup("safecheck",req);
		} catch (Exception e) {
			message = "航班保障作业检查单主记录新增页面跳转失败";
			logger.error("航班保障作业检查单:" + message, e);
			throw new BusinessException(message);

		}

		return new ModelAndView("com/gbiac/fams/business/safecheck/main/famsAircontrolSafecheck-add");
	}

	/**
	 * 航班保障作业检查单主记录编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAircontrolSafecheckEntity famsAircontrolSafecheck, HttpServletRequest req) {
		String message;
		try {
			if (StringUtil.isNotEmpty(famsAircontrolSafecheck.getId())) {
				famsAircontrolSafecheck = famsAircontrolSafecheckService.getEntity(FamsAircontrolSafecheckEntity.class,
						famsAircontrolSafecheck.getId());
				req.setAttribute("famsAircontrolSafecheck", famsAircontrolSafecheck);
			}

			// 图片
			Object pacList = famsCommonService.getCommonObject(FamsCommonFileEntity.class, "safecheck",
					famsAircontrolSafecheck.getId(), "1", null);
			Util.filesSetReq((List<FamsCommonFileEntity>) pacList, req);

			
			//时间点检查项
			List<Map> one = safecheckdetailServiceI.querySafecheckDetail(famsAircontrolSafecheck.getId(), "时间点检查项");
			// 作业内容检查项
			List<Map> two = safecheckdetailServiceI.querySafecheckDetail(famsAircontrolSafecheck.getId(), "作业内容检查项");

			List<FamsAircontrolSafecheckconfigListEntity> config = new ArrayList<FamsAircontrolSafecheckconfigListEntity>();

			config.add(new FamsAircontrolSafecheckconfigListEntity("时间点检查项", null, one));
			config.add(new FamsAircontrolSafecheckconfigListEntity("作业内容检查项", null, two));

			req.setAttribute("famsAircontrolSafecheckconfigListEntity", config);
		} catch (Exception e) {
			message = "航班保障作业检查单主记录编辑页面跳转失败";
			logger.error("航班保障作业检查单:" + message, e);
			throw new BusinessException(message);
		}

		return new ModelAndView("com/gbiac/fams/business/safecheck/main/famsAircontrolSafecheck-update");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "famsAircontrolSafecheckController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAircontrolSafecheckEntity famsAircontrolSafecheck, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAircontrolSafecheckEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAircontrolSafecheck,
				request.getParameterMap());
		List<FamsAircontrolSafecheckEntity> famsAircontrolSafechecks = this.famsAircontrolSafecheckService
				.getListByCriteriaQuery(cq, false);
		modelMap.put(NormalExcelConstants.FILE_NAME, "航班保障作业检查单主记录");
		modelMap.put(NormalExcelConstants.CLASS, FamsAircontrolSafecheckEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("航班保障作业检查单主记录列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, famsAircontrolSafechecks);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAircontrolSafecheckEntity famsAircontrolSafecheck, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME, "航班保障作业检查单主记录");
		modelMap.put(NormalExcelConstants.CLASS, FamsAircontrolSafecheckEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("航班保障作业检查单主记录列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
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
				List<FamsAircontrolSafecheckEntity> listFamsAircontrolSafecheckEntitys = ExcelImportUtil
						.importExcel(file.getInputStream(), FamsAircontrolSafecheckEntity.class, params);
				for (FamsAircontrolSafecheckEntity famsAircontrolSafecheck : listFamsAircontrolSafecheckEntitys) {
					famsAircontrolSafecheckService.save(famsAircontrolSafecheck);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
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
