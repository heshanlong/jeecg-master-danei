package com.gbiac.fams.business.unsafeevent.birdstrike.controller;

import com.gbiac.fams.business.airportrunway.mark.entity.MarkListEntity;
import com.gbiac.fams.business.airportrunway.mark.entity.TSTypeCode;
import com.gbiac.fams.business.construction.workrole.entity.FamsWorkRoleDetailEntity;
import com.gbiac.fams.business.construction.workrole.entity.FamsWorkRoleEntity;
import com.gbiac.fams.business.construction.workrole.page.FamsWorkRolePage;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;
import com.gbiac.fams.business.unsafeevent.birdstrike.service.FamsUnsafeeventBirdstrikeServiceI;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageEntity;
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
 * @Description: fams_unsafeevent_birdstrike
 * @author onlineGenerator
 * @date 2019-02-12 16:54:50
 * @version V1.0   
 *
 */
@Api(value="famsUnsafeeventBirdstrikeController",description="鸟击",tags="famsUnsafeeventBirdstrikeController")
@Controller
@RequestMapping("/famsUnsafeeventBirdstrikeController")
public class FamsUnsafeeventBirdstrikeController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsUnsafeeventBirdstrikeController.class);

	@Autowired
	private FamsUnsafeeventBirdstrikeServiceI famsUnsafeeventBirdstrikeService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * fams_unsafeevent_birdstrike列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		request.setAttribute("jcxj", famsCommonService.jcxj(ResourceUtil.getSessionUser().getId(),0));
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/birdstrike/famsUnsafeeventBirdstrikeList");
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
	public void datagrid(FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsUnsafeeventBirdstrikeEntity.class, dataGrid);		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsUnsafeeventBirdstrike, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.eq("del", "0");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsUnsafeeventBirdstrikeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除fams_unsafeevent_birdstrike
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsUnsafeeventBirdstrike = systemService.getEntity(FamsUnsafeeventBirdstrikeEntity.class, famsUnsafeeventBirdstrike.getId());
		message = "鸟击数据删除成功";
		try{
			famsUnsafeeventBirdstrikeService.delete(famsUnsafeeventBirdstrike);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "鸟击数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("鸟击:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 关闭
	 * @return
	 */
	@RequestMapping(params = "doCloesl")
	@ResponseBody
	public AjaxJson doCloesl(FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "鸟击数据关闭成功";
		FamsUnsafeeventBirdstrikeEntity t = famsUnsafeeventBirdstrikeService.get(FamsUnsafeeventBirdstrikeEntity.class, famsUnsafeeventBirdstrike.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsUnsafeeventBirdstrike, t);
			t.setPeople(ResourceUtil.getSessionUser().getUserName());
			t.setCloes("2");
			famsUnsafeeventBirdstrikeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "鸟击数据关闭失败";
//			throw new BusinessException(e.getMessage());
			logger.error("鸟击:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除fams_unsafeevent_birdstrike
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "鸟击数据删除成功";
		try{
			for(String id:ids.split(",")){
				FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike = systemService.getEntity(FamsUnsafeeventBirdstrikeEntity.class, 
				id
				);
				famsUnsafeeventBirdstrikeService.delete(famsUnsafeeventBirdstrike);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
//			e.printStackTrace();
			message = "鸟击数据删除失败";
//			throw new BusinessException(e.getMessage());
			logger.error("鸟击:"+message,e);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加fams_unsafeevent_birdstrike
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "鸟击数据添加成功";
		try{
			famsUnsafeeventBirdstrike.setCloes("1");
			famsUnsafeeventBirdstrike.setDel("0");
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsUnsafeeventBirdstrikeService.save(famsUnsafeeventBirdstrike,map);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "鸟击数据添加失败";
//			throw new BusinessException(e.getMessage());
			logger.error("鸟击:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新fams_unsafeevent_birdstrike
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "鸟击数据更新成功";
		FamsUnsafeeventBirdstrikeEntity t = famsUnsafeeventBirdstrikeService.get(FamsUnsafeeventBirdstrikeEntity.class, famsUnsafeeventBirdstrike.getId());
		try {
			String str = "";
			//得到所有属性
	 		java.lang.reflect.Field[] fields = t.getClass().getDeclaredFields();
	 			java.lang.reflect.Field[] fieldutd = famsUnsafeeventBirdstrike.getClass().getDeclaredFields();
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
		 		               Object valueutd = fieldut.get(famsUnsafeeventBirdstrike);
		 		               
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
		 		            			  .replaceAll("damagedlocation", "痕迹部位")
		 		            			  .replaceAll("abody", "鸟尸")
		 		            			  .replaceAll("birdfeathers", "鸟毛")
		 		            			  .replaceAll("blood", "血迹")
		 		            			  .replaceAll("aircraftdamage", "航空器损伤")
		 		            			  .replaceAll("injury", "损伤部位")
		 		            			  .replaceAll("woundsizelong", "损伤尺寸长度")
		 		            			  .replaceAll("woundsizewide", "损伤尺寸宽度")
		 		            			  .replaceAll("woundsizedeep", "损伤尺寸深度")
										  .replaceAll("birdhighlyDetail", "鸟击详细高度")

										  .replaceAll("birdhighly", "鸟击高度")
										  .replaceAll("notificationecologicaltime", "通报生态时间")
		 		            			  .replaceAll("announcedutytime", "通报值班领导时间")
		 		            			  .replaceAll("notificationaoctime", "通报AOC时间")
		 		            			  .replaceAll("announcetracktime", "通报场道时间")
		 		            			  .replaceAll("checkrunway", "跑道检查")
		 		            			  .replaceAll("runwayforeign", "跑道异物")
		 		            			  .replaceAll("runway", "跑道")
		 		            			  .replaceAll("checkdirection", "检查方向")
		 		            			  .replaceAll("trackstarttime", "查道起时间")
		 		            			  .replaceAll("trackcheckstoptime", "查道止时间")
		 		            			  .replaceAll("eventtype", "事件类型")
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

//		 		            	  n="birdhighly".equals(fieldut.getName())?"鸟击高度":"鸟击高度详细";


								   if ("birdhighly".equals(fieldut.getName())) {
		 		            		 value = getTypeGroupGrid("birdhighly",value.toString());
		 		            		 valueutd = getTypeGroupGrid("birdhighly",valueutd.toString());
								  }
		 		            	  if ("abody".equals(fieldut.getName()) || "birdfeathers".equals(fieldut.getName())
		 		            			  || "aircraftdamage".equals(fieldut.getName()) || "runwayforeign".equals(fieldut.getName()) ) {
		 		            		 value = getTypeGroupGrid("youwu",value.toString());
		 		            		 valueutd = getTypeGroupGrid("youwu",valueutd.toString());
								  }
								   if ( "blood".equals(fieldut.getName())
										   ) {
									   value = getTypeGroupGrid("njsj_xueji",value.toString());
									   valueutd = getTypeGroupGrid("njsj_xueji",valueutd.toString());
								   }
		 		            	  if ("checkdirection".equals(fieldut.getName())) {
		 		            		 value = getTypeGroupGrid("checkd",value.toString());
		 		            		 valueutd = getTypeGroupGrid("checkd",valueutd.toString());
								  }
		 		            	  if ("eventtype".equals(fieldut.getName())) {
		 		            		 value = getTypeGroupGrid("eventtype",value.toString());
		 		            		 valueutd = getTypeGroupGrid("eventtype",valueutd.toString());
								  }
		 		            	  if ("checkrunway".equals(fieldut.getName())) {
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
		 		            	  str += n + " 由 \"" + value + "\" 变为 \"" + valueutd+"\"";
		 		               }
		 		            
		 		           } catch (IllegalAccessException e) {
		 		               e.printStackTrace();
		 		           }
	 					}
	 		       }
	 			}
	 			
			MyBeanUtils.copyBeanNotNull2Bean(famsUnsafeeventBirdstrike, t);
			t.setRunway(famsUnsafeeventBirdstrike.getRunway());
			t.setCheckdirection(famsUnsafeeventBirdstrike.getCheckdirection());
			t.setUpdateBy(ResourceUtil.getSessionUser().getUserName());
			t.setUpdateDate(new Date());
			String files = request.getParameter("files");
			Map map = new HashMap();
			map.put("files", files);
			famsUnsafeeventBirdstrikeService.saveOrUpdate(t,map,str);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			//推送
             FamsUnsafeeventBirdstrikeEntity entity=famsCommonService.get(FamsUnsafeeventBirdstrikeEntity.class,famsUnsafeeventBirdstrike.getId());
           //获取接收者
 			List<String> userIds=famsCommonService.getCanApproveIdsByRunPartOrgId();
             //List<String> users=famsCommonService.getYunXingUsers();
 			//获取修改的航班号
             String flightno = request.getParameter("flightno");
 			//判断当前登录用户是否属于运行控制部
 			if(!Util.getUserDepId().equals("8a0790ec692289be016922ab955c0056")){
 				messageService.pushOnlyPCMessage("birdstrike",entity.getId(),"推送提醒",
 						Util.getUserDepName()+"部"+Util.getUserName()+"用户修改了"+flightno+"航班鸟击事件类型，请及时查看。",
 						Util.getPcOrAppUserName(),userIds,null,null,null);
 			}
		} catch (Exception e) {
//			e.printStackTrace();
			message = "鸟击数据更新失败";
//			throw new BusinessException(e.getMessage());
			logger.error("鸟击:"+message,e);
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
	 * fams_unsafeevent_birdstrike新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsUnsafeeventBirdstrike.getId())) {
			famsUnsafeeventBirdstrike = famsUnsafeeventBirdstrikeService.getEntity(FamsUnsafeeventBirdstrikeEntity.class, famsUnsafeeventBirdstrike.getId());
			req.setAttribute("famsUnsafeeventBirdstrike", famsUnsafeeventBirdstrike);
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
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/birdstrike/famsUnsafeeventBirdstrike-add");
	}
	/**
	 * fams_unsafeevent_birdstrike编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsUnsafeeventBirdstrike.getId())) {
			famsUnsafeeventBirdstrike = famsUnsafeeventBirdstrikeService.getEntity(FamsUnsafeeventBirdstrikeEntity.class, famsUnsafeeventBirdstrike.getId());
			req.setAttribute("famsUnsafeeventBirdstrike", famsUnsafeeventBirdstrike);
			String sql ="SELECT * FROM  t_s_category  where PARENT_CODE = 'A09'" ;
			Session session = systemService.getSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TSCategoryEntity.class);
			List<TSCategoryEntity> categoryList = query.list();
			req.setAttribute("categoryList", categoryList);
			
			List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsUnsafeeventBirdstrike.getId(), null, null);
			req.setAttribute("files", Util.filesToMap(files));
		}
		return new ModelAndView("com/gbiac/fams/business/unsafeevent/birdstrike/famsUnsafeeventBirdstrike-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsUnsafeeventBirdstrikeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsUnsafeeventBirdstrikeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsUnsafeeventBirdstrike, request.getParameterMap());		    
		List<FamsUnsafeeventBirdstrikeEntity> famsUnsafeeventBirdstrikes= this.famsUnsafeeventBirdstrikeService.getListByCriteriaQuery(cq,true);		
		modelMap.put(NormalExcelConstants.FILE_NAME,"fams_unsafeevent_birdstrike");
		modelMap.put(NormalExcelConstants.CLASS,FamsUnsafeeventBirdstrikeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_unsafeevent_birdstrike列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsUnsafeeventBirdstrikes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"fams_unsafeevent_birdstrike");
    	modelMap.put(NormalExcelConstants.CLASS,FamsUnsafeeventBirdstrikeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("fams_unsafeevent_birdstrike列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsUnsafeeventBirdstrikeEntity> listFamsUnsafeeventBirdstrikeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsUnsafeeventBirdstrikeEntity.class,params);
				for (FamsUnsafeeventBirdstrikeEntity famsUnsafeeventBirdstrike : listFamsUnsafeeventBirdstrikeEntitys) {
					famsUnsafeeventBirdstrikeService.save(famsUnsafeeventBirdstrike);
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
