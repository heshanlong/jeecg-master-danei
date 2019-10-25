package com.gbiac.fams.business.unsafeevent.aircraftleakage.rest;
import com.gbiac.fams.business.airportrunway.mark.entity.TSTypeCode;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageDto;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.service.FamsUnsafeeventAircraftleakageServiceI;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.Util.Util;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
 * @Description: fams_unsafeevent_aircraftleakage
 * @author onlineGenerator
 * @date 2019-02-14 15:27:42
 * @version V1.0   
 *
 */
@Api(value="famsUnsafeeventAircraftleakageRestController",description="航空器漏油App",tags="famsUnsafeeventAircraftleakageRestController")
@Controller
@RequestMapping("/famsUnsafeeventAircraftleakageRestController")
public class FamsUnsafeeventAircraftleakageRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsUnsafeeventAircraftleakageRestController.class);

	@Autowired
	private FamsUnsafeeventAircraftleakageServiceI famsUnsafeeventAircraftleakageService;
	@Autowired
	private SystemService systemService;
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
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
	public ResponseMessage getDataByidAPP(@RequestBody FamsUnsafeeventAircraftleakageDto dto,HttpServletRequest request) {
		FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage = systemService.get(FamsUnsafeeventAircraftleakageEntity.class, dto.getId());
		//获取附件id集合
		List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsUnsafeeventAircraftleakage.getId(), null, null);
		famsUnsafeeventAircraftleakage.setFile(files);
		return Result.success(famsUnsafeeventAircraftleakage);
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
			@RequestBody FamsUnsafeeventAircraftleakageDto dto,HttpServletRequest request) {
		List<FamsUnsafeeventAircraftleakageEntity> array=famsUnsafeeventAircraftleakageService.listApp(dto.getSearchInput().toUpperCase(), dto.getPageApp(), dto.getPageSize(), request);
//		for (int i = 0; i < array.size(); i++) {
//			array.get(i).setSettimelong(array.get(i).getSettime().getTime());
//		}
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
	public ResponseMessage<?> doAddAPP(@RequestBody FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航空器漏油记录添加成功";
		try{
			famsUnsafeeventAircraftleakage.setCreateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			famsUnsafeeventAircraftleakage.setCreateDate(new Date());
			famsUnsafeeventAircraftleakage.setCloes("1");
			famsUnsafeeventAircraftleakage.setDel("0");
			String files = famsUnsafeeventAircraftleakage.getFiles();
			Map map = new HashMap();
			map.put("files", files);
			famsUnsafeeventAircraftleakageService.save(famsUnsafeeventAircraftleakage,map);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "航空器漏油记录添加失败";
//			throw new BusinessException(e.getMessage());
			return Result.success(message);
		}
		return Result.success(message);
	}
	
	/**
	 * 关闭
	 * @return
	 */
	@RequestMapping(value = "doCloesl", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "关闭数据")
	public ResponseMessage<?> doCloesl(@RequestBody FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航空器漏油数据关闭成功";
		FamsUnsafeeventAircraftleakageEntity t = systemService.get(FamsUnsafeeventAircraftleakageEntity.class, famsUnsafeeventAircraftleakage.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsUnsafeeventAircraftleakage, t);
//			t.setPeople(ResourceUtil.getSessionUser().getUserName());
			t.setPeople(ResourceUtil.getSessionUser() == null?SessionUserUtil.getCurrentUser().getUser().getUserName():ResourceUtil.getSessionUser().getUserName());
			t.setCloes("2");
			famsUnsafeeventAircraftleakageService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "航空器漏油数据关闭失败";
//			throw new BusinessException(e.getMessage());
			return Result.success(message);
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
	public ResponseMessage<?> doUpdateAPP(@RequestBody FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage, HttpServletRequest request) {
		String message = null;
		message = "航空器漏油记录更新成功";
		FamsUnsafeeventAircraftleakageEntity t = famsUnsafeeventAircraftleakageService.get(FamsUnsafeeventAircraftleakageEntity.class, famsUnsafeeventAircraftleakage.getId());
		try {
			String str = "";
			//得到所有属性
	 		java.lang.reflect.Field[] fields = t.getClass().getDeclaredFields();
	 			java.lang.reflect.Field[] fieldutd = famsUnsafeeventAircraftleakage.getClass().getDeclaredFields();
	 			for (int o = 0; o < fieldutd.length; o++) {
	 				for (int i=0;i<fields.length;i++){//遍历
	 					if(fields[i].getName().equals(fieldutd[o].getName())) {
		 		           try {
		 		               //得到属性
		 		        	   java.lang.reflect.Field field = fields[i];
		 		               //打开私有访问
		 		               field.setAccessible(true);
		 		               //获取属性值
		 		               Object value = field.get(t);
		 		               
		 		               //得到属性
		 		        	   java.lang.reflect.Field fieldut = fieldutd[o];
		 		               //打开私有访问
		 		        	   fieldut.setAccessible(true);
		 		               //获取属性值
		 		               Object valueutd = fieldut.get(famsUnsafeeventAircraftleakage);
		 		               
		 		               if (valueutd == null|| "".equals(valueutd)) {
		 		            	  valueutd = "空值";
		 		               }
		 		               if (value == null || "".equals(value)) {
		 		            	  value = "空值";
		 		               }
		 		               if (!value.equals(valueutd) 
		 		            		   && !"createBy".equals(fieldut.getName()) 
		 		            		   && !"createDate".equals(fieldut.getName()) 
		 		            		   && !"updateBy".equals(fieldut.getName()) 
		 		            		   && !"updateDate".equals(fieldut.getName()) 
		 		            		   && !"cloes".equals(fieldut.getName())
		 		            		   && !"files".equals(fieldut.getName())
		 		            		   && !"no".equals(fieldut.getName())
		 		            		   && !"del".equals(fieldut.getName())
		 		            		   && !"people".equals(fieldut.getName())
		 		            		   && !"flightregistrationnumber".equals(fieldut.getName())
		 		            		   //航班信息
//		 		            		   && !"flightregistrationnumber".equals(fieldut.getName())
//		 		            		   && !"immediately".equals(fieldut.getName())
//		 		            		   && !"models".equals(fieldut.getName())
//		 		            		   && !"slots".equals(fieldut.getName())
//		 		            		   && !"airPlanId".equals(fieldut.getName())
//		 		            		   && !"routes".equals(fieldut.getName())
//		 		            		   && !"plannedtime".equals(fieldut.getName())
//		 		            		   && !"planneddeparture".equals(fieldut.getName())
//		 		            		   && !"actualtimearrival".equals(fieldut.getName())
//		 		            		   && !"actualdeparture".equals(fieldut.getName())
//		 		            		   && !"starstation".equals(fieldut.getName())
//		 		            		   && !"starstationcn".equals(fieldut.getName())
//		 		            		   && !"terminalstation".equals(fieldut.getName())
//		 		            		   && !"terminalstationcn".equals(fieldut.getName())
//		 		            		   && !"airlinescn".equals(fieldut.getName())
//		 		            		   && !"airlines".equals(fieldut.getName())
		 		            		   ) {
		 		            		
		 		            	  if (str.length() > 0) {
		 		            		 str += " </br> ";
								  }
		 		            	  String n = fieldut.getName()
//		 		            			  .replaceAll("people", "作业人")
		 		            			  .replaceAll("thedate", "日期")
		 		            			  .replaceAll("settime", "接报时间")
		 		            			  .replaceAll("sourceformation", "信息来源")
		 		            			  .replaceAll("note", "备注")
		 		            			  .replaceAll("announcetracktime", "通报场道时间")
		 		            			  .replaceAll("announcedutytime", "通报值班领导时间")
		 		            			  .replaceAll("notificationaoctime", "通报AOC时间")
		 		            			  .replaceAll("transactiontime", "通报机坪时间")
										  .replaceAll("oiltypesOther", "漏油种类其他描述值")

										  .replaceAll("oiltypes", "漏油种类")
		 		            			  .replaceAll("spillarea", "漏油面积")
										  .replaceAll("oilleakreasonOther", "漏油原因备注")

										  .replaceAll("oilleakreason", "漏油原因")
		 		            			  .replaceAll("affectedoperationarea", "影响运行区域")
		 		            			  .replaceAll("initiatefuelspillplan", "启动燃油溢漏预案")
		 		            			  .replaceAll("startuptime", "启动时间")
		 		            			  .replaceAll("lifttime", "解除时间")
		 		            			  .replaceAll("oilcleaningsite", "现场油迹清理完毕时间")
		 		            			  .replaceAll("aircraftlaunchtime", "飞机推出时间")
		 		            			  .replaceAll("availabletime", "机位可用时间")
		 		            			  .replaceAll("relevanttaxiwayavailabilitytime", "相关滑行道可用时间")
		 		            			  .replaceAll("notificationofaoctime", "通报AOC时间")
		 		            			  .replaceAll("announcetheleaderonduty", "通报值班领导时间")
		 		            			  .replaceAll("trailerdragadded", "拖车拖动补充说明")
		 		            			  .replaceAll("autonomousturning", "飞机自主转弯")
		 		            			  .replaceAll("guidevehicleswaitingposition", "引领车辆等待位置")
		 		            			  .replaceAll("leadline", "引领路线")
		 		            			  .replaceAll("trailerarrivalwaitingtime", "拖车到达等待点时间")
		 		            			  .replaceAll("leadvehiclethewaitingpointtime", "引领车辆到达等待点时间")
		 		            			  .replaceAll("leadtime", "开始引领时间")
		 		            			  .replaceAll("aircraftcarrytime", "飞机进位时间")
		 		            			  .replaceAll("aircraftdragroute", "飞机拖动路线")
		 		            			  .replaceAll("checdirection", "检查方向")
		 		            			  .replaceAll("added", "补充说明")
		 		            			  .replaceAll("flightno", "航班号")
//		 		            			  .replaceAll("flightregistrationnumber", "航班注册号")
		 		            			  .replaceAll("immediately", "机号")
		 		            			  .replaceAll("models", "机型")
		 		            			  .replaceAll("slots", "停机位")
		 		            			  .replaceAll("airPlanId", "航班号")
		 		            			  .replaceAll("routes", "航线")
		 		            			  .replaceAll("plannedtime", "计划进港时间")
		 		            			  .replaceAll("planneddeparture", "计划出港时间")
		 		            			  .replaceAll("actualtimearrival", "实际进港时间")
		 		            			  .replaceAll("actualdeparture", "实际出港时间")
		 		            			  .replaceAll("starstationcn", "出发城市中文名")
		 		            			  .replaceAll("starstation", "出发城市编码")
		 		            			  .replaceAll("terminalstationcn", "到达城市中文名")
		 		            			  .replaceAll("terminalstation", "到达城市编码")
		 		            			  .replaceAll("airlinescn", "航空公司中文名")
		 		            			  .replaceAll("airlines", "航空公司二字码");//航空公司二字码
		 		            	  if ("oiltypes".equals(fieldut.getName())) {
		 		            		 value = getTypeGroupGrid("oiltypes",value.toString());
		 		            		 valueutd = getTypeGroupGrid("oiltypes",valueutd.toString());
								  }
		 		            	  if ("oilleakreason".equals(fieldut.getName())) {
		 		            		 value = getTypeGroupGrid("leakreason",value.toString());
		 		            		 valueutd = getTypeGroupGrid("leakreason",valueutd.toString());
								  }
		 		            	  if ("oiltypes".equals(fieldut.getName())) {
		 		            		 value = getTypeGroupGrid("oiltypes",value.toString());
		 		            		 valueutd = getTypeGroupGrid("oiltypes",valueutd.toString());
								  }
		 		            	  if ("autonomousturning".equals(fieldut.getName())) {
		 		            		 value = getTypeGroupGrid("keyi",value.toString());
		 		            		 valueutd = getTypeGroupGrid("keyi",valueutd.toString());
								  }
		 		            	  if ("initiatefuelspillplan".equals(fieldut.getName())) {
		 		            		 value = getTypeGroupGrid("is_main",value.toString());
		 		            		 valueutd = getTypeGroupGrid("is_main",valueutd.toString());
								  }
		 		            	  str += n + " 由 \"" + value + "\" 变为 \"" + valueutd+"\"  ";
		 		               }
		 		           } catch (IllegalAccessException e) {
		 		               e.printStackTrace();
		 		           }
	 					}
	 		       }
	 			}
			MyBeanUtils.copyBeanNotNull2Bean(famsUnsafeeventAircraftleakage, t);
			t.setUpdateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			t.setUpdateDate(new Date());
			String files = famsUnsafeeventAircraftleakage.getFiles();
			Map map = new HashMap();
			map.put("files", files);
			famsUnsafeeventAircraftleakageService.saveOrUpdate(t,map,str);
//			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "航空器漏油记录更新失败";
//			throw new BusinessException(e.getMessage());
			return Result.success(message);
		}
		return Result.success(message);
	}
	
	/**
	 * APP单详情
	 * @param id
	 * @param request
	 * @return
	 */
	public String getTypeGroupGrid(String code,String val) {
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
				if (val.equals(typegroupList.get(i).getTypecode())) {
					val =  typegroupList.get(i).getTypename();
					break;
				}
			}
		}
		return val;
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
		attributes.put("leakreason", getTypeGroupGrid("leakreason",request));
		attributes.put("oiltypes", getTypeGroupGrid("oiltypes",request));
		attributes.put("is_main", getTypeGroupGrid("is_main",request));
		attributes.put("keyi", getTypeGroupGrid("keyi",request));
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
