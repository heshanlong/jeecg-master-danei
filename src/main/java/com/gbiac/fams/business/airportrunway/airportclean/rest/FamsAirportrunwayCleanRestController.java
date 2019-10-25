package com.gbiac.fams.business.airportrunway.airportclean.rest;
import com.gbiac.fams.business.airportrunway.airportclean.entity.FamsAirportrunwayCleanDto;
import com.gbiac.fams.business.airportrunway.airportclean.entity.FamsAirportrunwayCleanEntity;
import com.gbiac.fams.business.airportrunway.airportclean.service.FamsAirportrunwayCleanServiceI;
import com.gbiac.fams.business.airportrunway.airportsurfaceclean.entity.FamsAirportrunwaySurfacecleanEntity;
import com.gbiac.fams.business.airportrunway.mark.entity.MarkListEntity;
import com.gbiac.fams.business.airportrunway.mark.entity.TSTypeCode;
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
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
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
 * @Description: fams_airportrunway_clean
 * @author 江家滨
 * @date 2019-01-16 15:12:38
 * @version V1.0   
 *
 */
@Api(value="famsAirportrunwayCleanRestController",description="跑道除胶App",tags="famsAirportrunwayCleanRestController")
@Controller
@RequestMapping("/famsAirportrunwayCleanRestController")
public class FamsAirportrunwayCleanRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAirportrunwayCleanRestController.class);

	@Autowired
	private FamsAirportrunwayCleanServiceI famsAirportrunwayCleanService;
	@Autowired
	private SystemService systemService;
	
	/**
	 * APP单详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getTypeGroupGrid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "APP根据code查询数据字典相应的选项",notes="id")
	public ResponseMessage<?> getTypeGroupGrid(@RequestBody FamsAirportrunwayCleanDto dto,HttpServletRequest request) {
//	public List<TSTypeCode> getTypeGroupGrid(String code,HttpServletRequest request) {
//		code = "sf_yn";
		List<TSTypegroup> tSCategoryList = systemService.findByProperty(TSTypegroup.class, "typegroupcode", dto.getCode());
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
		return Result.success(typegroupCodeList);
	}
	
	 
	
	/**
	 * APP检查单详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getDataByidAPP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "APP根据id查询检查单内容")
	public ResponseMessage getDataByidAPP(@RequestBody FamsAirportrunwayCleanDto dto,HttpServletRequest request) {
		FamsAirportrunwayCleanEntity famsAirportrunwayClean = systemService.get(FamsAirportrunwayCleanEntity.class, dto.getId());
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
			@RequestBody FamsAirportrunwayCleanDto dto,HttpServletRequest request) {
		List<FamsAirportrunwayCleanEntity>  array=famsAirportrunwayCleanService.listApp(dto.getSearchInput(), dto.getPageApp(), dto.getPageSize(), request);
		return Result.success(array);
	}
	
	/**
	 * 添加fams_airportrunway_clean
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "doAddAPP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "APP添加数据")
	public ResponseMessage<?>  doAddAPP(@RequestBody FamsAirportrunwayCleanEntity famsAirportrunwayClean, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "跑道除胶记录添加成功";
		try{
			famsAirportrunwayClean.setCreateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			famsAirportrunwayClean.setCreateDate(new Date());
			famsAirportrunwayClean.setCreateByCn(SessionUserUtil.getCurrentUser().getUser().getRealName());
			famsAirportrunwayClean.setDirectionCleanLocation(famsAirportrunwayClean.getDirection() + "第" + famsAirportrunwayClean.getCleanLocation() +"块" );
			String files = famsAirportrunwayClean.getFiles();
			files = files.replaceAll(",", ";");
			Map map = new HashMap();
			map.put("files", files);
			famsAirportrunwayCleanService.save(famsAirportrunwayClean,map);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "跑道除胶记录添加失败";
//			throw new BusinessException(e.getMessage());
//			return Result.success(message);
			logger.error("跑道除胶:"+message,e);
		}
		return Result.success(message);
	}
	
	/**
	 * 更新fams_airportrunway_clean
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "doUpdateAPP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Update添加数据")
	public ResponseMessage<?>  doUpdateAPP(@RequestBody FamsAirportrunwayCleanEntity famsAirportrunwayClean, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "跑道除胶记录更新成功";
		FamsAirportrunwayCleanEntity t = famsAirportrunwayCleanService.get(FamsAirportrunwayCleanEntity.class, famsAirportrunwayClean.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAirportrunwayClean, t);
			t.setUpdateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			t.setUpdateDate(new Date());
			t.setDirectionCleanLocation(famsAirportrunwayClean.getDirection() + "第" + famsAirportrunwayClean.getCleanLocation() +"块" );
			String files = famsAirportrunwayClean.getFiles();
			files = files.replaceAll(",", ";");
			Map map = new HashMap();
			map.put("files", files);
				
			famsAirportrunwayCleanService.saveOrUpdate(t,map);
//			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "跑道除胶记录更新失败";
//			throw new BusinessException(e.getMessage());
//			return Result.success(message);
			logger.error("跑道除胶:"+message,e);
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
		attributes.put("除胶跑道", getMarkList("A09",request));
		attributes.put("除胶位置", getTypeGroupGrid("direction",request));
		return Result.success(attributes);
	}
	/**
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	public List<MarkListEntity> getMarkList(String code,HttpServletRequest request) {
		Session session = systemService.getSession();
		List<TSCategoryEntity> typegroupList = new ArrayList<TSCategoryEntity>();
		String sql ="SELECT * FROM  t_s_category  where PARENT_CODE like '" + code + "%'" ;
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TSCategoryEntity.class);
		typegroupList = query.list();
		List<MarkListEntity> markList = new ArrayList<MarkListEntity>();
		for (int i = 0; i < typegroupList.size(); i++) {
			if (typegroupList.get(i).getCode().length() == 6) {
				List<MarkListEntity> markList2 = new ArrayList<MarkListEntity>();
				MarkListEntity mark = new MarkListEntity();
				mark.setCode(typegroupList.get(i).getCode());
				mark.setTypename(typegroupList.get(i).getName());
				for (int j = 0; j < typegroupList.size(); j++) {
					if (typegroupList.get(j).getCode().length() == 9) {
						if (typegroupList.get(i).getCode().substring(0, 6).equals(typegroupList.get(j).getCode().substring(0, 6))) {
							MarkListEntity mark2 = new MarkListEntity();
							mark2.setCode(typegroupList.get(j).getCode());
							mark2.setTypename(typegroupList.get(j).getName());
							markList2.add(mark2);
						}
					}
				}
				mark.setMarkList(markList2);
				markList.add(mark);
			}
		}
		return markList;
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
