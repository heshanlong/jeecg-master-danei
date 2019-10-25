package com.gbiac.fams.business.airportrunway.repair.rest;
import com.gbiac.fams.business.airportrunway.mark.entity.FamsAirportrunwayMarkEntity;
import com.gbiac.fams.business.airportrunway.mark.entity.MarkListEntity;
import com.gbiac.fams.business.airportrunway.mark.entity.TSTypeCode;
import com.gbiac.fams.business.airportrunway.repair.entity.FamsAirportrunwayRepairDto;
import com.gbiac.fams.business.airportrunway.repair.entity.FamsAirportrunwayRepairEntity;
import com.gbiac.fams.business.airportrunway.repair.service.FamsAirportrunwayRepairServiceI;
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
import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;
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
 * @Description: fams_airportrunway_repair
 * @author 江家滨
 * @date 2019-01-21 10:39:34
 * @version V1.0   
 *
 */
@Api(value="famsAirportrunwayRepairRestController",description="道面修补App",tags="famsAirportrunwayRepairRestController")
@Controller
@RequestMapping("/famsAirportrunwayRepairRestController")
public class FamsAirportrunwayRepairRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAirportrunwayRepairRestController.class);

	@Autowired
	private FamsAirportrunwayRepairServiceI famsAirportrunwayRepairService;
	@Autowired
	private SystemService systemService;
	
	/**
	 * APP检查单详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getDataByidAPP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "APP根据id查询检查单内容")
	public ResponseMessage getDataByidAPP(@RequestBody FamsAirportrunwayRepairDto dto,HttpServletRequest request) {
		FamsAirportrunwayRepairEntity famsAirportrunwayRepair = systemService.get(FamsAirportrunwayRepairEntity.class, dto.getId());
		//获取附件id集合
		List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsAirportrunwayRepair.getId(), null, null);
		famsAirportrunwayRepair.setFile(files);
		return Result.success(famsAirportrunwayRepair);
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
			@RequestBody FamsAirportrunwayRepairDto dto,HttpServletRequest request) {
		List<FamsAirportrunwayRepairEntity>  array=famsAirportrunwayRepairService.listApp(dto.getSearchInput(), dto.getPageApp(), dto.getPageSize(), request);
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
	public ResponseMessage<?> doAddAPP(@RequestBody FamsAirportrunwayRepairEntity famsAirportrunwayRepair, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "道面修补记录添加成功";
		try{
			famsAirportrunwayRepair.setCreateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			famsAirportrunwayRepair.setCreateDate(new Date());
			famsAirportrunwayRepair.setCreateByCn(SessionUserUtil.getCurrentUser().getUser().getRealName());
			String files = famsAirportrunwayRepair.getFiles();
			Map map = new HashMap();
			map.put("files", files);
			famsAirportrunwayRepairService.save(famsAirportrunwayRepair,map);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "道面修补记录添加失败";
//			throw new BusinessException(e.getMessage());
//			return Result.success(message);
			logger.error("道面修补:"+message,e);
		}
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
	public ResponseMessage<?> doUpdateAPP(@RequestBody FamsAirportrunwayRepairEntity famsAirportrunwayRepair, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "道面修补记录更新成功";
		FamsAirportrunwayRepairEntity t = famsAirportrunwayRepairService.get(FamsAirportrunwayRepairEntity.class, famsAirportrunwayRepair.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAirportrunwayRepair, t);
			t.setUpdateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			t.setUpdateDate(new Date());
			
			String files = famsAirportrunwayRepair.getFiles();
			Map map = new HashMap();
			map.put("files", files);
				
			famsAirportrunwayRepairService.saveOrUpdate(t,map);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "道面修补记录更新失败";
//			throw new BusinessException(e.getMessage());
//			return Result.success(message);
			logger.error("道面修补:"+message,e);
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
		attributes.put("破损类型", getTypeGroupGrid("damageType",request));
		attributes.put("维修方案", getTypeGroupGrid("mainPlan",request));
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
