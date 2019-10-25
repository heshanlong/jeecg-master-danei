package com.gbiac.fams.business.reform.rest;

import com.alibaba.fastjson.JSONArray;
import com.gbiac.fams.business.reform.entity.FamsAircontrolReformEntity;
import com.gbiac.fams.business.reform.service.FamsAircontrolReformServiceI;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
import com.gbiac.fams.common.reply.service.FamsCommonReplyServiceI;
import com.gbiac.fams.restutil.SessionUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;


/**   
 * @Title: Controller  
 * @Description: 整改单
 * @author 邓正辉
 * @date 2019-01-09 14:49:44
 * @version V1.0   
 *
 */
@Api(value="FamsAircontrolReform",description="rest整改单",tags="famsAircontrolReformRestController")
@Controller
@RequestMapping("/famsAircontrolReformRestController")
public class FamsAircontrolReformRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAircontrolReformRestController.class);

	@Autowired
	private FamsAircontrolReformServiceI famsAircontrolReformService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsCommonReplyServiceI FamsCommonReplyServiceI;
	@Autowired
	private Validator validator;
	private String[] roleCodes_duty={"dutyComPer"};
	private String[] roleCodes_JP={"jiping"};



	@RequestMapping(value="/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="整改单列表信息",produces="application/json",httpMethod="POST")
	public ResponseMessage<List<FamsAircontrolReformEntity>> list(@ApiParam(name="整改单对象")@RequestBody FamsAircontrolReformEntity famsAircontrolReform, UriComponentsBuilder uriBuilder) {
		List<FamsAircontrolReformEntity> listFamsAircontrolReforms=null;
		String message;
		try {

			listFamsAircontrolReforms=famsAircontrolReformService.queryListByEntity(famsAircontrolReform);


		}catch (Exception e){
			message="查询失败";
			logger.error("整改单app:"+message,e);
			return Result.error(message);

		}

		return Result.success(listFamsAircontrolReforms);

	}



	/**
	 * 根据id获取整改单
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取整改单信息",notes="根据ID获取整改单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		String message;

		FamsAircontrolReformEntity task;
		Map map=new HashMap();

		try {
			task = famsAircontrolReformService.get(FamsAircontrolReformEntity.class, id);

			if (task == null) {
				return Result.error("获取整改单信息为空");


			}
			if (task != null) {
				//整改前图片
				Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",task.getId(),"1",null);
				map.put("pacList",pacList);
				//map=Util.filesToMap((List<FamsCommonFileEntity>)pacList);

				//整改后图片
				Object pacListAf=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",task.getId(),"2",null);

				//map= Util.filesToMap( (List<FamsCommonFileEntity>)pacListAf, map);;
				map.put("pacListAf",pacListAf);

				//历史动态
				FamsCommonReplyEntity entity=new FamsCommonReplyEntity();
				entity.setModule("reform");
				entity.setBusinessId(task.getId());
				entity.setState("1");
				List<FamsCommonReplyEntity> list=FamsCommonReplyServiceI.queryListByEntity(entity);

				map.put("logs",list);


			}
		}catch (Exception e){
			message="获取详情失败";
			logger.error("整改单app:"+message,e);
			return Result.error(message);
		}

		return Result.success(task,	map);

	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建整改单")
	public ResponseMessage<?> create(@ApiParam(name="整改单对象")@RequestBody FamsAircontrolReformEntity famsAircontrolReform, UriComponentsBuilder uriBuilder) {
		String message;
		//保存
		try{
			if( ! SessionUserUtil.getCurrentUserDepName().contains("机坪")){
				return Result.error("添加失败，没有权限");
			}
			//famsAircontrolReform.setNumber(systemService.getFamsNumberByTSTypegroupForApp("reform"));//编号
			famsAircontrolReformService.save(famsAircontrolReform);
			//关联图片,更新business_id
			famsCommonService.updateMBSofFileByStr(famsAircontrolReform.getPctureApp(),"reform",famsAircontrolReform.getId(),"1");
			//保存动态
			famsCommonService.saveReplyShow("reform",famsAircontrolReform.getId(),"1","派发了整改单");
			//pc通知责任单位下角色编码为dutyComPer为的值班账号
			String title= SessionUserUtil.getCurrentUserDepName()+"-"+SessionUserUtil.getUserRealName()+"给您单位派发了一张整改单";
			messageService.pushMessageByDepart(MessageConstant.REFORM_EN,famsAircontrolReform.getId(),title,famsAircontrolReform.getViolationName(),SessionUserUtil.getUserRealName(), famsAircontrolReform.getDutyId(),roleCodes_duty,null,null);
		} catch (Exception e) {
			message="整改单信息保存失败";
			logger.error("整改单app:"+message,e);

			return Result.error(message);
		}
		return Result.success(famsAircontrolReform);
	}

	/**
	 * 接收整改单
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "doReceive/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="接收整改单",notes="接收整改单")
	public ResponseMessage<?> doReceive(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		String message;
		if (StringUtil.isEmpty(id)) {
			return Result.error("参数校验失败");
		}
		FamsAircontrolReformEntity t = famsAircontrolReformService.get(FamsAircontrolReformEntity.class, id);

		//更新
		try{
			if( ! "1".equals(t.getStatus())){
				return Result.error("接收失败,请检查整改单状态");
			}

			if(! SessionUserUtil.getUserDepId().equals(t.getDutyId())){
				return Result.error("接收失败,不能接收其他单位整改单");
			}

			t.setStatus("2");

			famsAircontrolReformService.saveOrUpdate(t);
			//famsCommonService.updateMBSofFileByStr(pcture,"reform",famsAircontrolReform.getId(),"1");
			famsCommonService.saveReplyShow("reform",t.getId(),"1","接收了整改单");
			//pc通知机坪监管部角色下所有的用户
			String title=SessionUserUtil.getCurrentUserDepName()+"接收了您部门派发的整改单";
			messageService.pushMessage(MessageConstant.REFORM_EN,t.getId(),title,t.getViolationName(),SessionUserUtil.getUserRealName(), roleCodes_JP,null, null);


		} catch (Exception e) {

			message="接收整改单失败";
			logger.error("整改单app:"+message,e);

			return Result.error(message);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("接收整改单成功");
	}


	/**
	 * 整改整改单
	 * @return
	 */
	@RequestMapping(value = "doReform", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="整改-整改单",notes="整改-整改单")
	public ResponseMessage<?> doReform(@ApiParam(name="整改单对象")@RequestBody FamsAircontrolReformEntity famsAircontrolReform) {
		String message;
		try {
			if (StringUtil.isEmpty(famsAircontrolReform.getId())) {
				return Result.error("参数校验失败");
			}
			FamsAircontrolReformEntity t = famsAircontrolReformService.get(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());

			//开始整改

			if(! SessionUserUtil.getUserDepId().equals(t.getDutyId())){

				return Result.error("提交整改信息失败,不能提交其他单位整改单");

			}

			if( ! "2".equals(t.getStatus())
					&& ! "5".equals(t.getStatus())){

				return Result.error("提交整改信息失败,请检查整改单状态");

			}

			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolReform, t);
			String content="";

			//前端没传状态直接设置为整改状态，传了判断一下动态内容
			if(famsAircontrolReform.getStatus()==null){
				t.setStatus("3");
				content="完成了整改";
			}else{
				//只有外部单位确认完成了整改单才修改状态
				if( !"3".equals(famsAircontrolReform.getStatus())){
					t.setStatus(famsAircontrolReform.getStatus());
				}
				content="3".equals(famsAircontrolReform.getStatus())?"完成了整改":"提交了部分整改";
			}
			t.setReformDate(new Date());
			famsAircontrolReformService.saveOrUpdate(t);
			//整改后图片state=2
			famsCommonService.updateMBSofFileByStr(t.getPctureAfApp(),"reform",famsAircontrolReform.getId(),"2");
			//历史动态
			//String content="3".equals(famsAircontrolReform.getStatus())?"完成了整改单:":"提交了部分整改单:";
			//String content="完成了整改单:";

			famsCommonService.saveReplyShow("reform",famsAircontrolReform.getId(),"1",content);

			//pc通知机坪监管部角色下所有的用户
			String title=SessionUserUtil.getCurrentUserDepName()+"完成了您部门派发的整改单，请您及时验收";
			messageService.pushMessage(MessageConstant.REFORM_EN,t.getId(),title,t.getViolationName(),SessionUserUtil.getUserRealName(), roleCodes_JP,null, null);


		}catch (Exception e){
			message="提交整改信息失败";
			logger.error("整改单app:"+message,e);

			return Result.error(message);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("提交整改信息成功");
	}

	/**
	 * 	撤销整改单
	 * @param famsAircontrolReform
	 * @return
	 */
	@RequestMapping(value = "doReback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="撤销-整改单",notes="撤销-整改单")
	public ResponseMessage<?> doReback(@ApiParam(name="整改单对象")@RequestBody FamsAircontrolReformEntity famsAircontrolReform) {
		String message;
		if (StringUtil.isEmpty(famsAircontrolReform.getId())) {
			return Result.error("参数校验失败");
		}
		FamsAircontrolReformEntity t = famsAircontrolReformService.get(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());

		//开始整改
		try{
			if( ! SessionUserUtil.getCurrentUserDepName().contains("机坪")){
				return Result.error("撤销失败，没有权限");

			}
			//派发 接收 验收不通过可撤销
			if( ! "1".equals(t.getStatus())
					&& ! "2".equals(t.getStatus())
					&& ! "5".equals(t.getStatus())){
				return Result.error("撤销失败，请检查整改单状态");
			}
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolReform, t);
			t.setStatus("6");
			t.setCheckDate(new  Date());
			famsAircontrolReformService.saveOrUpdate(t);
			//famsCommonService.updateMBSofFileByStr(pcture,"reform",famsAircontrolReform.getId(),"1");

			famsCommonService.saveReplyShow("reform",famsAircontrolReform.getId(),"1","撤销了整改单:"+famsAircontrolReform.getRebackDes());
			//pc通知责任单位下角色编码为dutyComPer为的值班账号
			String title= SessionUserUtil.getCurrentUserDepName()+"-"+SessionUserUtil.getUserRealName()+"撤销了派发给您单位的整改单";
			messageService.pushMessageByDepart(MessageConstant.REFORM_EN,t.getId(),title,"撤销原因:"+t.getRebackDes(),SessionUserUtil.getUserRealName(), t.getDutyId(),roleCodes_duty,null,null);


		} catch (Exception e) {

			message="整改单撤销失败";
			logger.error("整改单app:"+message,e);

			return Result.error(message);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("整改单撤销成功");
	}

	@RequestMapping(value = "doCheck", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="验收-整改单",notes="验收-整改单")
	public ResponseMessage<?> doCheck(@ApiParam(name="整改单对象")@RequestBody FamsAircontrolReformEntity famsAircontrolReform) {
		String message="";
		if (StringUtil.isEmpty(famsAircontrolReform.getId())) {
			return Result.error("参数校验失败");
		}
		FamsAircontrolReformEntity t = famsAircontrolReformService.get(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());

		//开始整改
		try{
			if( ! SessionUserUtil.getCurrentUserDepName().contains("机坪")){
				return Result.error("验收失败，没有权限");

			}
			if( ! "3".equals(t.getStatus())
					&&! "5".equals(t.getStatus()) ){
				return Result.error("验收失败，请检查整改单状态");

			}
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolReform, t);
			t.setCheckDate(new Date());


			famsAircontrolReformService.saveOrUpdate(t);

			String content="4".equals(famsAircontrolReform.getStatus())?"验收通过整改单":"验收不通过整改单:"+famsAircontrolReform.getCheckfailDec();
			message=content+"操作成功";

			//content=content+famsAircontrolReform.getCheckfailDec();
			famsCommonService.saveReplyShow("reform",famsAircontrolReform.getId(),"1",content);
			//通知责任单位下角色编码为dutyComPer为的值班账号
			String title= SessionUserUtil.getCurrentUserDepName()+"-"+SessionUserUtil.getUserRealName()+("4".equals(famsAircontrolReform.getStatus())?"验收通过了":"未验收通过")+"您单位的整改单";
			messageService.pushMessageByDepart(MessageConstant.REFORM_EN,famsAircontrolReform.getId(),title,t.getViolationName(),SessionUserUtil.getUserRealName(), t.getDutyId(),roleCodes_duty,null,null);
		} catch (Exception e) {

			message="整改单验收失败";
			logger.error("整改单app:"+message,e);

			return Result.error(message);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success(message);
	}

	
}
