package com.gbiac.fams.business.unsafeevent.tiredamage.rest;
import com.gbiac.fams.business.airportrunway.mark.entity.MarkListEntity;
import com.gbiac.fams.business.airportrunway.mark.entity.TSTypeCode;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageDto;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.service.FamsUnsafeeventTiredamageServiceI;
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
import org.apache.commons.digester.SetRootRule;
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

import org.jeecgframework.jwt.model.TokenSession;
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
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(value="famsUnsafeeventTiredamageRestController",description="轮胎损伤App",tags="famsUnsafeeventTiredamageRestController")
@Controller
@RequestMapping("/famsUnsafeeventTiredamageRestController")
public class FamsUnsafeeventTiredamageRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsUnsafeeventTiredamageRestController.class);

	@Autowired
	private FamsUnsafeeventTiredamageServiceI famsUnsafeeventTiredamageService;
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
	public ResponseMessage getDataByidAPP(@RequestBody FamsUnsafeeventTiredamageDto dto,HttpServletRequest request) {
		FamsUnsafeeventTiredamageEntity newf = systemService.get(FamsUnsafeeventTiredamageEntity.class, dto.getId());
		FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage = new FamsUnsafeeventTiredamageEntity();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(newf, famsUnsafeeventTiredamage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Session session = systemService.getSession();
		List<TSCategoryEntity> typegroupList = new ArrayList<TSCategoryEntity>();
		String sql ="SELECT * FROM  t_s_category  where PARENT_CODE like 'A09%'" ;
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
		
			for (int j = 0; j < markList.size(); j++) {
				if (null != famsUnsafeeventTiredamage.getRunway()) {
					if (famsUnsafeeventTiredamage.getRunway().equals(markList.get(j).getCode())) {
						famsUnsafeeventTiredamage.setRunway(markList.get(j).getName());
					}
				}
			}
		
		//获取附件id集合
		List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsUnsafeeventTiredamage.getId(), null, null);
		famsUnsafeeventTiredamage.setFile(files);
		return Result.success(famsUnsafeeventTiredamage);
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
			@RequestBody FamsUnsafeeventTiredamageDto dto,HttpServletRequest request) {
		List<FamsUnsafeeventTiredamageEntity> array=famsUnsafeeventTiredamageService.listApp(dto.getSearchInput().toUpperCase(), dto.getPageApp(), dto.getPageSize(), request);
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
	public ResponseMessage<?>  doAddAPP(@RequestBody FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "轮胎损伤记录添加成功";
		try{
			famsUnsafeeventTiredamage.setCreateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			famsUnsafeeventTiredamage.setCloes("1");
			famsUnsafeeventTiredamage.setDel("0");
			famsUnsafeeventTiredamage.setCreateDate(new Date());
			String files = famsUnsafeeventTiredamage.getFiles();
			Map map = new HashMap();
			map.put("files", files);
			famsUnsafeeventTiredamageService.save(famsUnsafeeventTiredamage,map);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
//			e.printStackTrace();
			message = "轮胎损伤记录添加失败";
//			throw new BusinessException(e.getMessage());
//			return Result.success(message);
			logger.error("轮胎损伤:"+message,e);
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
	public ResponseMessage<?> doCloesl(@RequestBody FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "轮胎损伤数据关闭成功";
		FamsUnsafeeventTiredamageEntity t = systemService.get(FamsUnsafeeventTiredamageEntity.class, famsUnsafeeventTiredamage.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsUnsafeeventTiredamage, t);
//			t.setPeople(ResourceUtil.getSessionUser().getUserName());
			t.setPeople(ResourceUtil.getSessionUser() == null?SessionUserUtil.getCurrentUser().getUser().getUserName():ResourceUtil.getSessionUser().getUserName());
			t.setCloes("2");
			famsUnsafeeventTiredamageService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "轮胎损伤数据关闭失败";
//			throw new BusinessException(e.getMessage());
//			return Result.success(message);
			logger.error("轮胎损伤:"+message,e);
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
	public ResponseMessage<?>  doUpdateAPP(@RequestBody FamsUnsafeeventTiredamageEntity famsUnsafeeventTiredamage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "轮胎损伤记录更新成功";
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
			t.setUpdateBy(SessionUserUtil.getCurrentUser().getUser().getUserName());
			t.setUpdateDate(new Date());
			
			String files = famsUnsafeeventTiredamage.getFiles();
			Map map = new HashMap();
			map.put("files", files);
				
			famsUnsafeeventTiredamageService.saveOrUpdate(t,map,str);
//			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
//			e.printStackTrace();
			message = "轮胎损伤记录更新失败";
//			throw new BusinessException(e.getMessage());
//			return Result.success(message);
			logger.error("轮胎损伤:"+message,e);
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
		Session session = systemService.getSession();
		List<TSCategoryEntity> typegroupList = new ArrayList<TSCategoryEntity>();
		String sql ="SELECT * FROM  t_s_category  where PARENT_CODE like 'A09%'" ;
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
		Map attributes = new HashMap();
		attributes.put("yesno", getTypeGroupGrid("is_main",request));
		attributes.put("runway", markList);
		attributes.put("cdirection", getTypeGroupGrid("cdirection",request));
		attributes.put("dresults", getTypeGroupGrid("dresults",request));
		
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
