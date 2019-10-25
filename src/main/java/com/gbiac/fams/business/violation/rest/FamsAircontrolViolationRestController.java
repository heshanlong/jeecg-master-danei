package com.gbiac.fams.business.violation.rest;

import com.gbiac.fams.business.violation.entity.FamsAircontrolViolationEntity;
import com.gbiac.fams.business.violation.service.FamsAircontrolViolationServiceI;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gbiac.fams.restutil.SessionUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**   
 * @Title: Controller  
 * @Description: 违章告知单
 * @author 邓正辉
 * @date 2019-01-15 11:25:33
 * @version V1.0   
 *
 */
@Api(value="FamsAircontrolViolation",description="违章告知单",tags="famsAircontrolViolationRestController")
@Controller
@RequestMapping("/famsAircontrolViolationRestController")
public class FamsAircontrolViolationRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAircontrolViolationRestController.class);


	@Autowired
	private FamsAircontrolViolationServiceI famsAircontrolViolationService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	private String[] roleCodes_duty={"dutyComPer"};
	private String[] roleCodes_JP={"jiping"};
	private String[] roleCodes_ZR={"zhunru"};

	/**
	 * 违章告知单列表，传参参数参照实体
	 * @param uriBuilder
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="违章告知单信息",produces="application/json",httpMethod="POST")
	public ResponseMessage<List<FamsAircontrolViolationEntity>> list2(@ApiParam(name="违章告知单对象")@RequestBody FamsAircontrolViolationEntity famsAircontrolViolation, UriComponentsBuilder uriBuilder) {
		List<FamsAircontrolViolationEntity> listFamsAircontrolReforms;
		String message;

		try{

			listFamsAircontrolReforms=famsAircontrolViolationService.queryListByEntity(famsAircontrolViolation);

		}catch (Exception e){
			message="查询失败";
			logger.error("违章告知单app:"+message,e);
			return Result.error(message);
		}

		return Result.success(listFamsAircontrolReforms);
	}

	/**
	 * 根据id获取违章告知单
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取违章告知单信息",notes="根据ID获取违章告知单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		String message;

		FamsAircontrolViolationEntity task;
		Map map=new HashMap();

		try {
			 task= famsAircontrolViolationService.get(FamsAircontrolViolationEntity.class, id);

			 if (task == null) {
				return Result.error("根据ID获取违章告知单信息为空");


			}
			if (task != null) {
				//整改前图片
				Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"violation",task.getId(),"1",null);
				//map=Util.filesToMap((List<FamsCommonFileEntity>)pacList);
				map.put("pacList",pacList);

				//历史动态暂时不用
				FamsCommonReplyEntity entity=new FamsCommonReplyEntity();
				entity.setModule("violation");
				entity.setBusinessId(task.getId());
				entity.setState("1");
				List<FamsCommonReplyEntity> list=FamsCommonReplyServiceI.queryListByEntity(entity);

				map.put("logs",list);

			}
		}catch (Exception e){
			message="获取详情失败";
			logger.error("违章告知单app:"+message,e);
			return Result.error(message);
		}


		return Result.success(task,	map);

	}



	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建违章告知单")
	public ResponseMessage<?> create(@ApiParam(name="违章告知单对象")@RequestBody FamsAircontrolViolationEntity famsAircontrolViolation, UriComponentsBuilder uriBuilder) {
		String message;

		//保存
		try{
			if( ! SessionUserUtil.getCurrentUserDepName().contains("机坪")){
				return Result.error("添加失败，没有权限");
			}
			famsAircontrolViolation.setNumber(systemService.getFamsNumberByTSTypegroupForApp("violation"));//编号

			famsAircontrolViolationService.save(famsAircontrolViolation);
			famsCommonService.updateMBSofFileByStr(famsAircontrolViolation.getPctureApp(),"violation",famsAircontrolViolation.getId(),"1");
			famsCommonService.saveReplyShow("violation",famsAircontrolViolation.getId(),"1","派发了违章告知单");
			//pc通知责任单位下角色编码为dutyComPer为的值班账号
			String title= SessionUserUtil.getCurrentUserDepName()+"-"+SessionUserUtil.getUserRealName()+"给您单位派发了违章告知单";
			messageService.pushMessageByDepart(MessageConstant.VIOLATION_EN,famsAircontrolViolation.getId(),title,famsAircontrolViolation.getViolationName(),SessionUserUtil.getUserRealName(), famsAircontrolViolation.getDutyId(),roleCodes_duty,null,null);
			//pc通知准入管理部角色下所有的用户
			title=SessionUserUtil.getCurrentUserDepName()+"-"+SessionUserUtil.getUserRealName()+"派发了违章告知单，请您部门及时处理";
			messageService.pushMessage(MessageConstant.VIOLATION_EN,famsAircontrolViolation.getId(),title,famsAircontrolViolation.getViolationName(),SessionUserUtil.getUserRealName(), roleCodes_ZR,null, null);


		} catch (Exception e) {
			message="获取详情失败";
			logger.error("违章告知单app:"+message,e);
			return Result.error("违章告知单信息保存失败");
		}
		return Result.success(famsAircontrolViolation);
	}

	@RequestMapping(value = "doDeal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="处理违章告知单",notes="处理违章告知单")
	public ResponseMessage<?> doDeal(@ApiParam(name="违章告知单对象")@RequestBody FamsAircontrolViolationEntity famsAircontrolViolation) {
		String message;
		//保存
		try{
			if (StringUtil.isEmpty(famsAircontrolViolation.getId())) {
				return Result.error("参数校验失败");
			}

			FamsAircontrolViolationEntity t = famsAircontrolViolationService.get(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());

			if( ! SessionUserUtil.getCurrentUserDepName().contains("准入")){
				return Result.error("处理失败，没有权限");

			}
			if( !"1".equals(t.getStatus())){
				return Result.error("处理失败，请检查告知单状态");
			}
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolViolation, t);
			t.setStatus("2");
			t.setDealName(SessionUserUtil.getUserRealName());
			t.setDealDate(new Date());

			famsAircontrolViolationService.saveOrUpdate(t);

			famsCommonService.updateMBSofFileByStr(famsAircontrolViolation.getPctureApp(),"violation",famsAircontrolViolation.getId(),"1");

			famsCommonService.saveReplyShow("violation",famsAircontrolViolation.getId(),"1","处理了违章告知单");
			//pc通知责任单位下角色编码为dutyComPer为的值班账号
			String title= SessionUserUtil.getCurrentUserDepName()+"-"+SessionUserUtil.getUserRealName()+"处理了您单位的违章告知单";
			messageService.pushMessageByDepart(MessageConstant.VIOLATION_EN,t.getId(),title,t.getViolationName(),SessionUserUtil.getUserRealName(), t.getDutyId(),roleCodes_duty,null,null);


		} catch (Exception e) {
			message="准入管理部处理违章告知单失败";
			logger.error("违章告知单app:"+message,e);
			return Result.error(message);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("准入管理部处理违章告知单成功");
	}


	@RequestMapping(value = "doReceive/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="接收违章告知单",notes="接收违章告知单")
	public ResponseMessage<?> doReceive(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		String message;


		//更新
		try{
			if (StringUtil.isEmpty(id)) {
				return Result.error("参数校验失败");
			}

			FamsAircontrolViolationEntity t = famsAircontrolViolationService.get(FamsAircontrolViolationEntity.class, id);

			if( ! "2".equals(t.getStatus())){
				return Result.error("接收失败,请检查违章告知单状态");
			}

			if(! SessionUserUtil.getUserDepId().equals(t.getDutyId())){
				return Result.error("接收失败,不能接收其他单位告知单");
			}

			t.setStatus("3");

			famsAircontrolViolationService.saveOrUpdate(t);
			//famsCommonService.updateMBSofFileByStr(pcture,"reform",famsAircontrolReform.getId(),"1");
			famsCommonService.saveReplyShow("violation",t.getId(),"1","接收了违章告知单");
			//通知准入管理部角色下所有的用户
			String title=SessionUserUtil.getCurrentUserDepName()+"接收了您部门处理的违章告知单";
			messageService.pushMessage(MessageConstant.VIOLATION_EN,t.getId(),title,t.getViolationName(),SessionUserUtil.getUserRealName(), roleCodes_ZR,null, null);


		} catch (Exception e) {

			message="接收违章告知失败";
			logger.error("违章告知单app:"+message,e);
			return Result.error(message);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("接收违章告知成功");
	}

	@RequestMapping(value = "doReback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="撤销-违章告知单",notes="撤销-违章告知单")
	public ResponseMessage<?> doReback(@ApiParam(name="违章告知对象")@RequestBody FamsAircontrolViolationEntity famsAircontrolViolation) {
		String message = "撤销成功";
		;
		String dutyTitle;
		String flyTitle;
		//开始整改
		try{
			if (StringUtil.isEmpty(famsAircontrolViolation.getId())) {
				return Result.error("参数校验失败");
			}

			FamsAircontrolViolationEntity t = famsAircontrolViolationService.get(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());

			if(  "4".equals(t.getStatus())
					||  "3".equals(t.getStatus())){
				return Result.error("撤销失败，请检查整改单状态");
			}
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolViolation, t);

			//机坪管理部可撤销已派发的单
			if(SessionUserUtil.getCurrentUserDepName().contains("机坪")){
				if(! "1".equals(t.getStatus())){
					return Result.error("撤销失败，违章告知单已被其他单位处理或接收");
				}
				t.setStatus("4");
				dutyTitle=SessionUserUtil.getCurrentUserDepName()+"-"+SessionUserUtil.getUserRealName()+"撤销了您单位的违章告知单";
				flyTitle=SessionUserUtil.getCurrentUserDepName()+"-"+SessionUserUtil.getUserRealName()+"撤销了"+t.getDutyCompany()+"的违章告知单";


			}
			//准入撤销
			else if(SessionUserUtil.getCurrentUserDepName().contains("准入")){
				t.setRebackDes(null);//撤回不保存理由
				message="撤回成功";

				if("1".equals(t.getStatus())){
					return Result.error("撤销失败，准入管理部不能撤销本部门未处理的违章告知单");

				}

				if("2".equals(t.getStatus())){
					t.setStatus("1");
				}
				dutyTitle=SessionUserUtil.getCurrentUserDepName()+"-"+SessionUserUtil.getUserRealName()+"撤回您单位的违章告知单";
				flyTitle=SessionUserUtil.getCurrentUserDepName()+"-"+SessionUserUtil.getUserRealName()+"撤回了"+t.getDutyCompany()+"的违章告知单";


			}
			else{
				return Result.error("撤销失败，没有权限");

			}

			t.setAccessPanishResult(null);
			t.setPanishRule(null);
			t.setDealDate(null);
			t.setDealName(null);
			famsAircontrolViolationService.saveOrUpdate(t);

			//famsCommonService.updateMBSofFileByStr(pcture,"violation",famsAircontrolViolation.getId(),"1");

			famsCommonService.saveReplyShow("violation",famsAircontrolViolation.getId(),"1",message+":"+famsAircontrolViolation.getRebackDes());
			//通知责任单位下角色编码为dutyComPer为的值班账号
			messageService.pushMessageByDepart(MessageConstant.VIOLATION_EN,t.getId(),dutyTitle,t.getViolationName(),SessionUserUtil.getUserRealName(), t.getDutyId(),roleCodes_duty,null,null);

			//机坪通知
			if(SessionUserUtil.getCurrentUserDepName().contains("机坪")){
				//通知准入管理部角色下所有的用户
				messageService.pushMessage(MessageConstant.VIOLATION_EN,t.getId(),flyTitle,t.getViolationName(),SessionUserUtil.getUserRealName(), roleCodes_ZR,null, null);
			}
			//准入通知
			else if(SessionUserUtil.getCurrentUserDepName().contains("准入")){
				//通知机坪监管部角色下所有的用户
				messageService.pushMessage(MessageConstant.VIOLATION_EN,t.getId(),flyTitle,t.getViolationName(),SessionUserUtil.getUserRealName(), roleCodes_JP,null, null);

			}
		} catch (Exception e) {

			message="整改单撤销失败";
			logger.error("违章告知单app:"+message,e);
			return Result.error(message);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success(message);
	}


}
