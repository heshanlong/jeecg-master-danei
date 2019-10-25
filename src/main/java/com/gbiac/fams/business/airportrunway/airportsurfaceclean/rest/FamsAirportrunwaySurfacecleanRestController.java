package com.gbiac.fams.business.airportrunway.airportsurfaceclean.rest;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanDto;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.service.FamsAirportrunwaySurfacecleanServiceI;
import com.gbiac.fams.business.airportrunway.mark.entity.TSTypeCode;
import com.gbiac.fams.business.airportrunway.repair.entity.FamsAirportrunwayRepairEntity;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.restutil.SessionUserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;

import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelExportUtil;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: fams_airportrunway_surfaceclean
 * @author 江家滨
 * @date 2019-01-17 17:07:01
 * @version V1.0   
 *
 */
@Api(value="famsAirportrunwaySurfacecleanRestController",description="道面清扫App",tags="famsAirportrunwaySurfacecleanRestController")
@Controller
@RequestMapping("/famsAirportrunwaySurfacecleanRestController")
public class FamsAirportrunwaySurfacecleanRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAirportrunwaySurfacecleanRestController.class);

	@Autowired
	private FamsAirportrunwaySurfacecleanServiceI FamsAirportrunwaySurfacecleanService;
	@Autowired
	private SystemService systemService;

//    /**
//	 * APP单详情
//	 * @param id
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "getAppDate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	@ApiOperation(value = "APP根据id查询单内容",notes="id")
//	public ResponseMessage getCheckDetailAPP(@RequestParam(value = "id") String id,HttpServletRequest request) {
//		FamsAirportrunwaySurfacecleanEntity famsAirportrunwayClean = FamsAirportrunwaySurfacecleanService.get(FamsAirportrunwaySurfacecleanEntity.class, id);
//		//获取附件id集合
//		List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsAirportrunwayClean.getId(), null, null);
//		famsAirportrunwayClean.setFile(files);
//		return Result.success(famsAirportrunwayClean);
//	}
	
	/**
	 * APP检查单详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getDataByidAPP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "APP根据id查询检查单内容")
	public ResponseMessage getDataByidAPP(@RequestBody FamsAirportrunwaySurfacecleanDto dto,HttpServletRequest request) {
		FamsAirportrunwaySurfacecleanEntity famsAirportrunwayClean = systemService.get(FamsAirportrunwaySurfacecleanEntity.class, dto.getId());
		//获取附件id集合
				List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsAirportrunwayClean.getId(), null, null);
				famsAirportrunwayClean.setFile(files);
		return Result.success(famsAirportrunwayClean);
	}
	
	/**
	 * 按条件查询
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "doSelectAPP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "按条件查询数据")
	public ResponseMessage<?> doSelectAPP(
			@RequestBody FamsAirportrunwaySurfacecleanDto dto,HttpServletRequest request) {
		List<FamsAirportrunwaySurfacecleanEntity> array=FamsAirportrunwaySurfacecleanService.listApp(dto.getSearchInput(), dto.getPageApp(), dto.getPageSize(), request);
		return Result.success(array);
	}
	
	/**
	 * 添加fams_airportrunway_surfaceclean
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "doAddAPP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "APP添加数据")
	public ResponseMessage<?> doAddAPP(@RequestBody FamsAirportrunwaySurfacecleanEntity famsAirportrunwaySurfaceclean, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "道面清扫记录添加成功";
		try{
			famsAirportrunwaySurfaceclean.setCreateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			famsAirportrunwaySurfaceclean.setCreateDate(new Date());
			famsAirportrunwaySurfaceclean.setCreateByCn(SessionUserUtil.getCurrentUser().getUser().getRealName());
			String files = famsAirportrunwaySurfaceclean.getFiles();
			Map map = new HashMap();
			map.put("files", files);
			FamsAirportrunwaySurfacecleanService.save(famsAirportrunwaySurfaceclean,map);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "道面清扫记录添加失败";
//			throw new BusinessException(e.getMessage());
//			return Result.success(message);
			logger.error("道面清扫:"+message,e);
		}
		j.setMsg(message);
		return Result.success(message);
	}
	
	/**
	 * 更新fams_airportrunway_surfaceclean
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "doUpdateAPP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Update添加数据")
	public ResponseMessage<?> doUpdateAPP(@RequestBody FamsAirportrunwaySurfacecleanEntity famsAirportrunwayClean, HttpServletRequest request) {
		String message = null;
		message = "道面清扫记录更新成功";
		FamsAirportrunwaySurfacecleanEntity t = FamsAirportrunwaySurfacecleanService.get(FamsAirportrunwaySurfacecleanEntity.class, famsAirportrunwayClean.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAirportrunwayClean, t);
			t.setUpdateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			t.setUpdateDate(new Date());
			String files = famsAirportrunwayClean.getFiles();
			Map map = new HashMap();
			map.put("files", files);
				
			FamsAirportrunwaySurfacecleanService.saveOrUpdate(t,map);
//			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "道面清扫记录更新失败";
//			throw new BusinessException(e.getMessage());
//			return Result.success(message);
			logger.error("道面清扫:"+message,e);
		}
		return Result.success(message);
	}
	
	/**
	 * APP单详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goAddApp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "添加方法获取页面相关元素",notes="id")
	public ResponseMessage goAddApp(HttpServletRequest request) {
		Map attributes = new HashMap();
		attributes.put("清扫位置", getTypeGroupGrid("cLocation",request));
		attributes.put("清扫原因", getTypeGroupGrid("cleanWhy",request));
		attributes.put("清扫设备", getTypeGroupGrid("cEquipment",request));
		return Result.success(attributes);
	}
	
	/**
	 * APP单详情
	 * @param id
	 * @param request
	 * @return
	 */
	public List<TSTypeCode> getTypeGroupGrid(String code,HttpServletRequest request) {
		List<TSTypegroup> tSCategoryList = systemService.findByProperty(TSTypegroup.class, "typegroupcode", code);
		List<TSType> typegroupList = new ArrayList<TSType>();
		List<TSTypeCode> typegroupCodeList = new ArrayList<TSTypeCode>();
		if (null != tSCategoryList && tSCategoryList.size() > 0) {
			String sql ="SELECT * FROM  t_s_type  where typegroupid = '" + tSCategoryList.get(0).getId() + "'" ;
			Session session = systemService.getSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TSType.class);
			typegroupList = query.list();
			for (int i = 0; i < typegroupList.size(); i++) {
				TSTypeCode tSTypeCode = new TSTypeCode();
				tSTypeCode.setOrderNum(typegroupList.get(i).getOrderNum());
				tSTypeCode.setTypecode(typegroupList.get(i).getTypecode());
				tSTypeCode.setTypename(typegroupList.get(i).getTypename());
				typegroupCodeList.add(tSTypeCode);
			}
		}
		return typegroupCodeList;
	}
	
}
