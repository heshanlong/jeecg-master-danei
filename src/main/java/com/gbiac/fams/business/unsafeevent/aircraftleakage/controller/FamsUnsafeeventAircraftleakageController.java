package com.gbiac.fams.business.unsafeevent.aircraftleakage.controller;
import com.gbiac.fams.business.airportrunway.mark.entity.TSTypeCode;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.service.FamsUnsafeeventAircraftleakageServiceI;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageEntity;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.Util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;

import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.annotation.Excel;
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
@Api(value="famsUnsafeeventAircraftleakageController",description="航空器漏油",tags="famsUnsafeeventAircraftleakageController")
@Controller
@RequestMapping("/famsUnsafeeventAircraftleakageController")
public class FamsUnsafeeventAircraftleakageController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsUnsafeeventAircraftleakageController.class);

	@Autowired
	private FamsUnsafeeventAircraftleakageServiceI famsUnsafeeventAircraftleakageService;
	@Autowired
	private SystemService systemService;

	/**
	 * fams_unsafeevent_aircraftleakage列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		request.setAttribute("jcxj", famsCommonService.jcxj(ResourceUtil.getSessionUser().getId(),0));
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/aircraftleakage/famsUnsafeeventAircraftleakageList");
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
	public void datagrid(FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		//根据编号模糊查询
//		if (famsUnsafeeventAircraftleakage != null && famsUnsafeeventAircraftleakage.getNo() != null && famsUnsafeeventAircraftleakage.getNo().length() > 0) {
//			famsUnsafeeventAircraftleakage.setNo("*" + famsUnsafeeventAircraftleakage.getNo() +"*");
//		}
//		//根据航班号模糊查询
//		if (famsUnsafeeventAircraftleakage != null && famsUnsafeeventAircraftleakage.getImmediately() != null && famsUnsafeeventAircraftleakage.getImmediately().length() > 0) {
//			famsUnsafeeventAircraftleakage.setImmediately("*" + famsUnsafeeventAircraftleakage.getImmediately() +"*");
//		}
//		//根据跑道标志模糊查询
//		if (famsUnsafeeventAircraftleakage != null && famsUnsafeeventAircraftleakage.getOiltypes() != null && famsUnsafeeventAircraftleakage.getOiltypes().length() > 0) {
//			famsUnsafeeventAircraftleakage.setOiltypes("*" + famsUnsafeeventAircraftleakage.getOiltypes() +"*");
//		}
//		//根据滑行道标志模糊查询
//		if (famsUnsafeeventAircraftleakage != null && famsUnsafeeventAircraftleakage.getSourceformation() != null && famsUnsafeeventAircraftleakage.getSourceformation().length() > 0) {
//			famsUnsafeeventAircraftleakage.setSourceformation("*" + famsUnsafeeventAircraftleakage.getSourceformation() +"*");
//		}		
		CriteriaQuery cq = new CriteriaQuery(FamsUnsafeeventAircraftleakageEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsUnsafeeventAircraftleakage, request.getParameterMap());
		try{
		//自定义追加查询条件
	
			cq.eq("del", "0");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsUnsafeeventAircraftleakageService.getDataGridReturn(cq, true);
		
		
//		List<FamsUnsafeeventAircraftleakageEntity> a = dataGrid.getResults();
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 关闭
	 * @return
	 */
	@RequestMapping(params = "doCloesl")
	@ResponseBody
	public AjaxJson doCloesl(FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航空器漏油数据关闭成功";
		FamsUnsafeeventAircraftleakageEntity t = systemService.get(FamsUnsafeeventAircraftleakageEntity.class, famsUnsafeeventAircraftleakage.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsUnsafeeventAircraftleakage, t);
			t.setPeople(ResourceUtil.getSessionUser().getUserName());
			t.setCloes("2");
			famsUnsafeeventAircraftleakageService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "航空器漏油数据关闭失败";
//			throw new BusinessException(e.getMessage());
			logger.error("航空器漏油:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 删除fams_unsafeevent_aircraftleakage
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsUnsafeeventAircraftleakage = systemService.getEntity(FamsUnsafeeventAircraftleakageEntity.class, famsUnsafeeventAircraftleakage.getId());
		message = "航空器漏油数据删除成功";
		try{
			famsUnsafeeventAircraftleakageService.delete(famsUnsafeeventAircraftleakage);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "航空器漏油数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("航空器漏油:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除fams_unsafeevent_aircraftleakage
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航空器漏油数据删除成功";
		try{
			for(String id:ids.split(",")){
				FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage = systemService.getEntity(FamsUnsafeeventAircraftleakageEntity.class, 
				id
				);
				famsUnsafeeventAircraftleakageService.delete(famsUnsafeeventAircraftleakage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
//			e.printStackTrace();
			message = "航空器漏油数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("航空器漏油:"+message,e);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加fams_unsafeevent_aircraftleakage
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航空器漏油数据添加成功";
		try{
			famsUnsafeeventAircraftleakage.setCloes("1");
			famsUnsafeeventAircraftleakage.setDel("0");
			if ("N".equals(famsUnsafeeventAircraftleakage.getInitiatefuelspillplan())) {
				famsUnsafeeventAircraftleakage.setStartuptime(null);
				famsUnsafeeventAircraftleakage.setLifttime(null);
			}
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsUnsafeeventAircraftleakageService.save(famsUnsafeeventAircraftleakage,map);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "航空器漏油数据添加失败";
//			throw new BusinessException(e.getMessage());
			logger.error("航空器漏油:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新fams_unsafeevent_aircraftleakage
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "航空器漏油数据更新成功";
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
		 		            			  .replaceAll("airlines", "航空公司二字码");
		 		            	  
		 		            		
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
		 		        		
		 		            	  str += n + " 由 \"" + value + "\" 变为 \"" + valueutd+"\" " ;
		 		               }
		 		           } catch (IllegalAccessException e) {
		 		               e.printStackTrace();
		 		           }
	 					}
	 		       }
	 			}
			MyBeanUtils.copyBeanNotNull2Bean(famsUnsafeeventAircraftleakage, t);
			t.setUpdateBy(ResourceUtil.getSessionUser().getUserName());
			t.setUpdateDate(new Date());
			if ("N".equals(t.getInitiatefuelspillplan())) {
				t.setStartuptime(null);
				t.setLifttime(null);
			}
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsUnsafeeventAircraftleakageService.saveOrUpdate(t,map,str);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			
			//推送
			FamsUnsafeeventAircraftleakageEntity entity=famsCommonService.get(FamsUnsafeeventAircraftleakageEntity.class,famsUnsafeeventAircraftleakage.getId());
			//获取接收者
			List<String> userIds=famsCommonService.getCanApproveIdsByRunPartOrgId();
            //List<String> users=famsCommonService.getYunXingUsers();
			//获取修改的航班号
            String flightno = request.getParameter("flightno");
			//判断当前登录用户是否属于运行控制部
			if(!Util.getUserDepId().equals("8a0790ec692289be016922ab955c0056")){
				messageService.pushOnlyPCMessage("aircraftleakage",entity.getId(),"推送提醒",
						Util.getUserDepName()+"部"+Util.getUserName()+"用户修改了"+flightno+"航班航空器漏油事件类型，请及时查看。",
						Util.getPcOrAppUserName(),userIds,null,null,null);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			message = "航空器漏油数据更新失败";
//			throw new BusinessException(e.getMessage());
			logger.error("航空器漏油:"+message,e);
		}
		j.setMsg(message);
		return j;
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
	 * fams_unsafeevent_aircraftleakage新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsUnsafeeventAircraftleakage.getId())) {
			famsUnsafeeventAircraftleakage = famsUnsafeeventAircraftleakageService.getEntity(FamsUnsafeeventAircraftleakageEntity.class, famsUnsafeeventAircraftleakage.getId());
			req.setAttribute("famsUnsafeeventAircraftleakage", famsUnsafeeventAircraftleakage);
		}
		long now = System.currentTimeMillis();
	    SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
	    req.setAttribute("sdf", sdf.format(now));
	    SimpleDateFormat sdff = new  SimpleDateFormat("HH:mm");
	    req.setAttribute("sdff", sdff.format(now));
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/aircraftleakage/famsUnsafeeventAircraftleakage-add");
	}
	/**
	 * fams_unsafeevent_aircraftleakage编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsUnsafeeventAircraftleakage.getId())) {
			famsUnsafeeventAircraftleakage = famsUnsafeeventAircraftleakageService.getEntity(FamsUnsafeeventAircraftleakageEntity.class, famsUnsafeeventAircraftleakage.getId());
			req.setAttribute("famsUnsafeeventAircraftleakage", famsUnsafeeventAircraftleakage);
			List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsUnsafeeventAircraftleakage.getId(), null, null);
			req.setAttribute("files", Util.filesToMap(files));
		}
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/aircraftleakage/famsUnsafeeventAircraftleakage-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsUnsafeeventAircraftleakageController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsUnsafeeventAircraftleakageEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsUnsafeeventAircraftleakage, request.getParameterMap());
		List<FamsUnsafeeventAircraftleakageEntity> famsUnsafeeventAircraftleakages = this.famsUnsafeeventAircraftleakageService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"fams_unsafeevent_aircraftleakage");
		modelMap.put(NormalExcelConstants.CLASS,FamsUnsafeeventAircraftleakageEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_unsafeevent_aircraftleakage列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsUnsafeeventAircraftleakages);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"fams_unsafeevent_aircraftleakage");
    	modelMap.put(NormalExcelConstants.CLASS,FamsUnsafeeventAircraftleakageEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_unsafeevent_aircraftleakage列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsUnsafeeventAircraftleakageEntity> listFamsUnsafeeventAircraftleakageEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsUnsafeeventAircraftleakageEntity.class,params);
				for (FamsUnsafeeventAircraftleakageEntity famsUnsafeeventAircraftleakage : listFamsUnsafeeventAircraftleakageEntitys) {
					famsUnsafeeventAircraftleakageService.save(famsUnsafeeventAircraftleakage);
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
