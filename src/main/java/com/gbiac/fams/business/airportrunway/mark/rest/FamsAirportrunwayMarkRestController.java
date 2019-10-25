package com.gbiac.fams.business.airportrunway.mark.rest;
import com.gbiac.fams.business.airportrunway.mark.entity.FamsAirportrunwayMarkDto;
import com.gbiac.fams.business.airportrunway.mark.entity.FamsAirportrunwayMarkEntity;
import com.gbiac.fams.business.airportrunway.mark.entity.MarkListEntity;
import com.gbiac.fams.business.airportrunway.mark.entity.TSTypeCode;
import com.gbiac.fams.business.airportrunway.mark.service.FamsAirportrunwayMarkServiceI;
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
 * @Description: fams_airportrunway_mark
 * @author 江家滨
 * @date 2019-01-22 11:17:47
 * @version V1.0   
 *
 */
@Api(value="famsAirportrunwayMarkRestController",description="标志线维护App",tags="famsAirportrunwayMarkRestController")
@Controller
@RequestMapping("/famsAirportrunwayMarkRestController")
public class FamsAirportrunwayMarkRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAirportrunwayMarkRestController.class);
	@Autowired
	private FamsAirportrunwayMarkServiceI famsAirportrunwayMarkService;
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
	public ResponseMessage getDataByidAPP(@RequestBody FamsAirportrunwayMarkDto dto,HttpServletRequest request) {
		Session session = systemService.getSession();
		FamsAirportrunwayMarkEntity famsAirportrunwayMark = systemService.get(FamsAirportrunwayMarkEntity.class, dto.getId());
		
		famsAirportrunwayMark.setMarkList(getMarkList("A08",famsAirportrunwayMark.getCouldSign(),request));
		famsAirportrunwayMark.setTaxiwayMarkList(getMarkList("A10",famsAirportrunwayMark.getTaxiwayMark(),request));
		//获取附件id集合
		List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsAirportrunwayMark.getId(), null, null);
		famsAirportrunwayMark.setFile(files);
		return Result.success(famsAirportrunwayMark);
	}
	
	/**
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	public List<MarkListEntity> getMarkList(String code,String couldSign,HttpServletRequest request) {
		Session session = systemService.getSession();
		List<TSCategoryEntity> typegroupList = new ArrayList<TSCategoryEntity>();
		String sql ="SELECT * FROM  t_s_category  where PARENT_CODE like '" + code + "%'" ;
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TSCategoryEntity.class);
		typegroupList = query.list();
		List<MarkListEntity> markList = new ArrayList<MarkListEntity>();
		if (null != couldSign) {
			if (couldSign.length() > 0) {
				String[] couldSignArray =couldSign.split(",");
				for (int i = 0; i < typegroupList.size(); i++) {
					for (int o = 0; o < couldSignArray.length; o++) {
					if (couldSignArray[o].equals(typegroupList.get(i).getCode()) && typegroupList.get(i).getCode().length() == 6) {
						List<MarkListEntity> markList2 = new ArrayList<MarkListEntity>();
						MarkListEntity mark = new MarkListEntity();
						mark.setCode(typegroupList.get(i).getCode());
						mark.setName(typegroupList.get(i).getName());
						for (int j = 0; j < typegroupList.size(); j++) {
							for (int k = 0; k < couldSignArray.length; k++) {
							if (typegroupList.get(j).getCode().length() == 9) {
								if (couldSignArray[k].equals(typegroupList.get(j).getCode()) && typegroupList.get(i).getCode().substring(0, 6).equals(typegroupList.get(j).getCode().substring(0, 6))) {
									MarkListEntity mark2 = new MarkListEntity();
									mark2.setCode(typegroupList.get(j).getCode());
									mark2.setName(typegroupList.get(j).getName());
									markList2.add(mark2);
								}
							}
							}
						}
						mark.setMarkList(markList2);
						markList.add(mark);
					}
					}
				}
			}
		}
		return markList;
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
			@RequestBody FamsAirportrunwayMarkDto dto,HttpServletRequest request) {
		List<FamsAirportrunwayMarkEntity> array=famsAirportrunwayMarkService.listApp(dto.getSearchInput(), dto.getPageApp(), dto.getPageSize(), request);
		
		Session session = systemService.getSession();
		List<TSCategoryEntity> typegroupList = new ArrayList<TSCategoryEntity>();
		String sql ="SELECT * FROM  t_s_category  where PARENT_CODE like 'A08%'" ;
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TSCategoryEntity.class);
		typegroupList = query.list();
		for (int a = 0; a < array.size(); a++) {
			String couldSign = array.get(a).getCouldSign();
			if (null != couldSign) {
				if (couldSign.length() > 0) {
					String[] couldSignArray =couldSign.split(",");
					String str = "";
					for (int i = 0; i < typegroupList.size(); i++) {
						for (int o = 0; o < couldSignArray.length; o++) {
							if (couldSignArray[o].equals(typegroupList.get(i).getCode()) ) {
								if (str.length() > 0 ) {
									str += ",";
								}
								str += typegroupList.get(i).getName();
							}
						}
					}
					array.get(a).setCouldSign(str);
				}
			}
		}
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
	public ResponseMessage<?> doAddAPP(@RequestBody FamsAirportrunwayMarkEntity famsAirportrunwayMark, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "标志线维护记录添加成功";
		try{
			Session session = systemService.getSession();
			List<TSCategoryEntity> typegroupList = new ArrayList<TSCategoryEntity>();
			String sql ="SELECT * FROM  t_s_category  where PARENT_CODE like 'A08%'" ;
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TSCategoryEntity.class);
			typegroupList = query.list();
			
//			String couldSign = famsAirportrunwayMark.getCouldSign();
//			String[] couldSignArray =couldSign.split(",");
//			String str = "";
//			for (int i = 0; i < typegroupList.size(); i++) {
//				for (int o = 0; o < couldSignArray.length; o++) {
//					if (couldSignArray[o].equals(typegroupList.get(i).getName()) ) {
//						if (str.length() > 0 ) {
//							str += ",";
//						}
//						str += typegroupList.get(i).getCode();
//					}
//				}
//			}
			famsAirportrunwayMark.setCreateByCn(SessionUserUtil.getCurrentUser().getUser().getRealName());
//			famsAirportrunwayMark.setCouldSign(str);
			famsAirportrunwayMark.setCreateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			famsAirportrunwayMark.setCreateDate(new Date());
			String files = famsAirportrunwayMark.getFiles();
			Map map = new HashMap();
			map.put("files", files);
			famsAirportrunwayMarkService.save(famsAirportrunwayMark,map);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "标志线维护记录添加失败";
//			return Result.success(message);
//			throw new BusinessException(e.getMessage());
			logger.error("标志线维护:"+message,e);
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
	public ResponseMessage<?> doUpdateAPP(@RequestBody FamsAirportrunwayMarkEntity famsAirportrunwayMark, HttpServletRequest request) {
		String message = null;
		message = "标志线维护记录更新成功";
		FamsAirportrunwayMarkEntity t = famsAirportrunwayMarkService.get(FamsAirportrunwayMarkEntity.class, famsAirportrunwayMark.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAirportrunwayMark, t);
			t.setUpdateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			t.setUpdateDate(new Date());
			String files = famsAirportrunwayMark.getFiles();
			Map map = new HashMap();
			map.put("files", files);
				
			famsAirportrunwayMarkService.saveOrUpdate(t,map);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "标志线维护记录更新失败";
//			return Result.success(message);
//			throw new BusinessException(e.getMessage());
			logger.error("标志线维护:"+message,e);
		}
		return Result.success(message);
	}
	
	/**
	 * APP单详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getTypeGroupGrid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "APP根据code查询数据字典相应的选项",notes="id")
	public ResponseMessage goAdd(HttpServletRequest request) {
		Map attributes = new HashMap();
//		attributes.put("滑行道标志", getTypeGroupGrid("taxiway",request));
		Map m = new HashMap();
		m.put("name", "跑道标志");
		m.put("value", getTypeGroupGrid("runwayLogo",request));
		attributes.put("runwayLogo", m);
		
		Map m1 = new HashMap();
		m1.put("name", "机坪服务车道标志");
		m1.put("value", getMarkList("A08",request));
		attributes.put("couldSign", m1);
		
		Map m2 = new HashMap();
		m2.put("name", "滑行道标志");
		m2.put("value", getMarkList("A10",request));
		attributes.put("taxiwayMark", m2);
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
				mark.setName(typegroupList.get(i).getName());
				for (int j = 0; j < typegroupList.size(); j++) {
					if (typegroupList.get(j).getCode().length() == 9) {
						if (typegroupList.get(i).getCode().substring(0, 6).equals(typegroupList.get(j).getCode().substring(0, 6))) {
							MarkListEntity mark2 = new MarkListEntity();
							mark2.setCode(typegroupList.get(j).getCode());
							mark2.setName(typegroupList.get(j).getName());
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
	 * 
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
