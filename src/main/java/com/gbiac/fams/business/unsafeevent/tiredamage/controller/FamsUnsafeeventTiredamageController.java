package com.gbiac.fams.business.unsafeevent.tiredamage.controller;
import com.gbiac.fams.business.airportrunway.mark.entity.MarkListEntity;
import com.gbiac.fams.business.airportrunway.mark.entity.TSTypeCode;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.service.FamsUnsafeeventTiredamageServiceI;
import com.gbiac.fams.common.MessageService;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.restutil.SessionUserUtil;

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
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;
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
 * @Description: fams_unsafeevent_tiredamage
 * @author 江家滨
 * @date 2019-02-01 10:26:36
 * @version V1.0   
 *
 */
@Api(value="famsUnsafeeventTiredamageController",description="轮胎损伤",tags="famsUnsafeeventTiredamageController")
@Controller
@RequestMapping("/famsUnsafeeventTiredamageController")
public class FamsUnsafeeventTiredamageController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsUnsafeeventTiredamageController.class);

	@Autowired
	private FamsUnsafeeventTiredamageServiceI famsUnsafeeventTiredamageService;
	@Autowired
	private SystemService systemService;
	@Autowired
    private MessageService messageService;
	


	/**
	 * fams_unsafeevent_tiredamage列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		request.setAttribute("jcxj", famsCommonService.jcxj(ResourceUtil.getSessionUser().getId(),0));
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/tiredamage/famsUnsafeeventTiredamageList");
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
	public void datagrid(FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsUnsafeeventTiredamageEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsUnsafeeventTiredamage, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.eq("del", "0");
//			cq.notEq("del", "1");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsUnsafeeventTiredamageService.getDataGridReturn(cq, true);
//		List<FamsUnsafeeventTiredamageEntity> a = dataGrid.getResults();
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 关闭
	 * @return
	 */
	@RequestMapping(params = "doCloesl")
	@ResponseBody
	public AjaxJson doCloesl(FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "轮胎损伤数据关闭成功";
		FamsUnsafeeventTiredamageEntity t = systemService.get(FamsUnsafeeventTiredamageEntity.class, famsUnsafeeventTiredamage.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsUnsafeeventTiredamage, t);
			t.setPeople(ResourceUtil.getSessionUser().getUserName());
			t.setCloes("2");
			famsUnsafeeventTiredamageService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "轮胎损伤数据关闭失败";
//			throw new BusinessException(e.getMessage());
			logger.error("轮胎损伤:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 删除fams_unsafeevent_tiredamage
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsUnsafeeventTiredamage = systemService.getEntity(FamsUnsafeeventTiredamageEntity.class, famsUnsafeeventTiredamage.getId());
		message = "轮胎损伤数据删除成功";
		try{
			famsUnsafeeventTiredamageService.delete(famsUnsafeeventTiredamage);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "轮胎损伤数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("轮胎损伤:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除fams_unsafeevent_tiredamage
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "轮胎损伤数据删除成功";
		try{
			for(String id:ids.split(",")){
				FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage = systemService.getEntity(FamsUnsafeeventTiredamageEntity.class, 
				id
				);
				famsUnsafeeventTiredamageService.delete(famsUnsafeeventTiredamage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
//			e.printStackTrace();
			message = "轮胎损伤数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("轮胎损伤:"+message,e);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加fams_unsafeevent_tiredamage
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "轮胎损伤数据添加成功";
		try{
			famsUnsafeeventTiredamage.setCloes("1");
			famsUnsafeeventTiredamage.setDel("0");
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsUnsafeeventTiredamageService.save(famsUnsafeeventTiredamage,map);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "轮胎损伤数据添加失败";
//			throw new BusinessException(e.getMessage());
			logger.error("轮胎损伤:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新fams_unsafeevent_tiredamage
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "轮胎损伤数据更新成功";
		FamsUnsafeeventTiredamageEntity t = famsUnsafeeventTiredamageService.get(FamsUnsafeeventTiredamageEntity.class, famsUnsafeeventTiredamage.getId());
		try {
			String str = "";
			//得到所有属性
	 		java.lang.reflect.Field[] fields = t.getClass().getDeclaredFields();
	 			java.lang.reflect.Field[] fieldutd = famsUnsafeeventTiredamage.getClass().getDeclaredFields();
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
		 		               Object valueutd = fieldut.get(famsUnsafeeventTiredamage);
		 		               
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
		 		            			  .replaceAll("damagedlocation", "受损位置")
		 		            			  .replaceAll("woundsizelong", "伤口尺寸长度")
		 		            			  .replaceAll("woundsizewide", "伤口尺寸宽度")
		 		            			  .replaceAll("woundsizedeep", "伤口尺寸深度")
		 		            			  .replaceAll("foreignmatter", "有无携带外来物")
		 		            			  .replaceAll("renovationtire", "翻新胎")
		 		            			  .replaceAll("announcetracktime", "通报场道时间")
		 		            			  .replaceAll("trackfeedbacktime", "场道反馈时间")
		 		            			  .replaceAll("announcedutytime", "通报值班领导时间")
		 		            			  .replaceAll("notificationaoctime", "通报AOC时间")
		 		            			  .replaceAll("impactsubsequentflights", "影响后续航班")
		 		            			  .replaceAll("determineresults", "判定结果")
		 		            			  .replaceAll("localroute", "本场运行路线")
		 		            			  .replaceAll("checkrunway", "检查跑道")
		 		            			  .replaceAll("runway", "跑道")
		 		            			  .replaceAll("trackstarttime", "场道查道起时间")
		 		            			  .replaceAll("trackcheckstoptime", "场道查道止时间")
		 		            			  .replaceAll("checkresults", "查道结果")
		 		            			  .replaceAll("followupflightschedule", "后续航班计划")
		 		            			  .replaceAll("subsequentcourse", "后续航线")
		 		            			  .replaceAll("subsequentplanneddeparture", "后续计划出港时间")
		 		            			  .replaceAll("subsequentactualdeparture", "后续实际出港时间")
		 		            			  .replaceAll("checdirection", "检查方向")
		 		            			  .replaceAll("added", "补充说明")
		 		            			  .replaceAll("delaytime", "延误时间")
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
		 		            	  
		 		            	  if ("checdirection".equals(fieldut.getName())) {
		 		            		 value = getTypeGroupGrid("cdirection",value.toString());
		 		            		 valueutd = getTypeGroupGrid("cdirection",valueutd.toString());
								  }
		 		            	  if ("determineresults".equals(fieldut.getName())) {
		 		            		 value = getTypeGroupGrid("dresults",value.toString());
		 		            		 valueutd = getTypeGroupGrid("dresults",valueutd.toString());
								  }
		 		            	  if ("foreignmatter".equals(fieldut.getName()) || "renovationtire".equals(fieldut.getName()) || "impactsubsequentflights".equals(fieldut.getName())
		 		            			 || "checkrunway".equals(fieldut.getName())
		 		            			 ) {
		 		            		 value = getTypeGroupGrid("is_main",value.toString());
		 		            		 valueutd = getTypeGroupGrid("is_main",valueutd.toString());
								  }
		 		            	 if ("runway".equals(fieldut.getName())) {
			 			            	Session session = systemService.getSession();
			 			    			List<TSCategoryEntity> typegroupList = new ArrayList<TSCategoryEntity>();
			 			    			String sql ="SELECT * FROM  t_s_category  where PARENT_CODE like 'A09%'" ;
			 			    			SQLQuery query = session.createSQLQuery(sql);
			 			    			query.addEntity(TSCategoryEntity.class);
			 			    			typegroupList = query.list();
			 			    			List<MarkListEntity> markList = new ArrayList<MarkListEntity>();
			 			    			for (int y = 0; y < typegroupList.size(); y++) {
			 			    				if (typegroupList.get(y).getCode().length() == 6) {
			 			    					List<MarkListEntity> markList2 = new ArrayList<MarkListEntity>();
			 			    					MarkListEntity mark = new MarkListEntity();
			 			    					mark.setCode(typegroupList.get(y).getCode());
			 			    					mark.setName(typegroupList.get(y).getName());
			 			    					for (int nn = 0; nn < typegroupList.size(); nn++) {
			 			    						if (typegroupList.get(nn).getCode().length() == 9) {
			 			    							if (typegroupList.get(y).getCode().substring(0, 6).equals(typegroupList.get(nn).getCode().substring(0, 6))) {
			 			    								MarkListEntity mark2 = new MarkListEntity();
			 			    								mark2.setCode(typegroupList.get(nn).getCode());
			 			    								mark2.setName(typegroupList.get(nn).getName());
			 			    								markList2.add(mark2);
			 			    							}
			 			    						}
			 			    					}
			 			    					mark.setMarkList(markList2);
			 			    					markList.add(mark);
			 			    				}
			 			    			}
			 			    			
			 			    			for (int p = 0; p < markList.size(); p++) {
				 		            		if (null != valueutd) {
			 			    					if (valueutd.equals(markList.get(p).getCode())) {
			 			    						valueutd = markList.get(p).getName();
			 			    					}
			 			    				}
			 			    				if (null != value) {
			 			    					if (value.equals(markList.get(p).getCode())) {
			 			    						value = markList.get(p).getName();
			 			    					}
			 			    				}
			 			    			}
			 		            	 }
		 		            	  str += n + " 由 \"" + value + "\" 变为 \"" + valueutd+"\"  ";
		 		               }
		 		           } catch (IllegalAccessException e) {
		 		               e.printStackTrace();
		 		           }
	 					}
	 		       }
	 			}
			MyBeanUtils.copyBeanNotNull2Bean(famsUnsafeeventTiredamage, t);
			
			
			t.setRunway(famsUnsafeeventTiredamage.getRunway());
			t.setChecdirection(famsUnsafeeventTiredamage.getChecdirection());
			t.setUpdateBy(ResourceUtil.getSessionUser().getUserName());
			t.setUpdateDate(new Date());
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsUnsafeeventTiredamageService.saveOrUpdate(t,map,str);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			//推送
			FamsUnsafeeventTiredamageEntity entity=famsCommonService.get(FamsUnsafeeventTiredamageEntity.class,famsUnsafeeventTiredamage.getId());
			
			//获取接收者
			List<String> userIds=famsCommonService.getCanApproveIdsByRunPartOrgId();
            //List<String> users=famsCommonService.getYunXingUsers();
			//获取修改的航班号
            String flightno = request.getParameter("flightno");
			//判断当前登录用户是否属于运行控制部
			if(!Util.getUserDepId().equals("8a0790ec692289be016922ab955c0056")){
				messageService.pushOnlyPCMessage("tiredamage",entity.getId(),"推送提醒",
						Util.getUserDepName()+"部"+Util.getUserName()+"用户修改了"+flightno+"航班轮胎损伤事件类型，请及时查看。",
						Util.getPcOrAppUserName(),userIds,null,null,null);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			message = "轮胎损伤数据更新失败";
//			throw new BusinessException(e.getMessage());
			logger.error("轮胎损伤:"+message,e);
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
	 * fams_unsafeevent_tiredamage新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsUnsafeeventTiredamage.getId())) {
			famsUnsafeeventTiredamage = famsUnsafeeventTiredamageService.getEntity(FamsUnsafeeventTiredamageEntity.class, famsUnsafeeventTiredamage.getId());
			req.setAttribute("famsUnsafeeventTiredamage", famsUnsafeeventTiredamage);
		}
		String sql ="SELECT * FROM  t_s_category  where PARENT_CODE = 'A09'" ;
		Session session = systemService.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TSCategoryEntity.class);
		List<TSCategoryEntity> categoryList = query.list();
		req.setAttribute("categoryList", categoryList);
		
		long now = System.currentTimeMillis();
	    SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
	    req.setAttribute("sdf", sdf.format(now));
	    SimpleDateFormat sdff = new  SimpleDateFormat("HH:mm");
	    req.setAttribute("sdff", sdff.format(now));
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/tiredamage/famsUnsafeeventTiredamage-add");
	}
	
	/**
     * 企业详细信息跳转
     * @param request
     * @param did
     * @return
     */
	@RequestMapping(params = "getflightno")
	@ResponseBody
	public ResponseMessage getflightno(@RequestParam("flightno") String flightno) {
		ResponseMessage msg = null;
		try {
			msg = Result.success(famsUnsafeeventTiredamageService.getflightno(flightno));
		} catch (Exception e) {
			msg = Result.error();
		}
		return msg;

	}
	/**
	 * fams_unsafeevent_tiredamage编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsUnsafeeventTiredamage.getId())) {
			famsUnsafeeventTiredamage = famsUnsafeeventTiredamageService.getEntity(FamsUnsafeeventTiredamageEntity.class, famsUnsafeeventTiredamage.getId());
			req.setAttribute("famsUnsafeeventTiredamage", famsUnsafeeventTiredamage);
			
			
			List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsUnsafeeventTiredamage.getId(), null, null);
			req.setAttribute("files", Util.filesToMap(files));
		}
		String sql ="SELECT * FROM  t_s_category  where PARENT_CODE = 'A09'" ;
		Session session = systemService.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TSCategoryEntity.class);
		List<TSCategoryEntity> categoryList = query.list();
		req.setAttribute("categoryList", categoryList);
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/tiredamage/famsUnsafeeventTiredamage-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsUnsafeeventTiredamageController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsUnsafeeventTiredamageEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsUnsafeeventTiredamage, request.getParameterMap());
		List<FamsUnsafeeventTiredamageEntity> famsUnsafeeventTiredamages = this.famsUnsafeeventTiredamageService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"fams_unsafeevent_tiredamage");
		modelMap.put(NormalExcelConstants.CLASS,FamsUnsafeeventTiredamageEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_unsafeevent_tiredamage列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsUnsafeeventTiredamages);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"fams_unsafeevent_tiredamage");
    	modelMap.put(NormalExcelConstants.CLASS,FamsUnsafeeventTiredamageEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_unsafeevent_tiredamage列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsUnsafeeventTiredamageEntity> listFamsUnsafeeventTiredamageEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsUnsafeeventTiredamageEntity.class,params);
				for (FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage : listFamsUnsafeeventTiredamageEntitys) {
					famsUnsafeeventTiredamageService.save(famsUnsafeeventTiredamage);
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
