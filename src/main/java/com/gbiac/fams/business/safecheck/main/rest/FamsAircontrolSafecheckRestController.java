package com.gbiac.fams.business.safecheck.main.rest;

import com.gbiac.fams.business.safecheck.detail.service.FamsAircontrolSafecheckdetailServiceI;
import com.gbiac.fams.business.safecheck.main.entity.FamsAircontrolSafecheckEntity;
import com.gbiac.fams.business.safecheck.main.service.FamsAircontrolSafecheckServiceI;

import com.gbiac.fams.business.safecheck.safecheckconfig.entity.FamsAircontrolSafecheckconfigEntity;
import com.gbiac.fams.business.safecheck.safecheckconfig.entity.FamsAircontrolSafecheckconfigListEntity;
import com.gbiac.fams.business.safecheck.safecheckconfig.service.FamsAircontrolSafecheckconfigServiceI;

import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import io.swagger.annotations.ApiImplicitParam;
import org.jeecgframework.core.common.controller.BaseController;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.service.SystemService;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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
 * @Description: 航班保障作业检查单主记录
 * @author 邓正辉
 * @date 2019-02-20 10:42:10
 * @version V1.0
 *
 */
@Api(value="FamsAircontrolSafecheck",description="航班保障作业检查单主记录",tags="famsAircontrolSafecheckRestController")
@Controller
@RequestMapping("/famsAircontrolSafecheckRestController")
public class FamsAircontrolSafecheckRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAircontrolSafecheckRestController.class);

	@Autowired
	private FamsAircontrolSafecheckServiceI famsAircontrolSafecheckService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsAircontrolSafecheckdetailServiceI safecheckdetailServiceI;
	@Autowired
	private  FamsAircontrolSafecheckconfigServiceI famsAircontrolSafecheckconfigService;
	@Autowired
	private Validator validator;

	@RequestMapping(value="/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="航班保障检查单列表信息",produces="application/json",httpMethod="POST")
	public ResponseMessage<List<FamsAircontrolSafecheckEntity>> list(@ApiParam(name="航班保障检查单对象")@RequestBody FamsAircontrolSafecheckEntity famsAircontrolReform, UriComponentsBuilder uriBuilder) {
		List<FamsAircontrolSafecheckEntity> listFamsAircontrolReforms;
		String message;

		try {
			listFamsAircontrolReforms=famsAircontrolSafecheckService.queryListByEntity(famsAircontrolReform);

		}catch (Exception e){

			message="航班保障检查单获取列表失败";
			logger.error("航班保障检查单app:"+message,e);
			return Result.error(message);

		}


		return Result.success(listFamsAircontrolReforms);
	}


	@RequestMapping(value="/config", method = RequestMethod.GET)
	@ResponseBody
//	@ApiOperation(value="航班保障检查单配置信息",notes="航班保障检查单配置信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<List<FamsAircontrolSafecheckconfigListEntity>> config() {
		String message;
		List<FamsAircontrolSafecheckconfigListEntity> config=new ArrayList<FamsAircontrolSafecheckconfigListEntity>();

		try {
			//通用部分
			List<FamsAircontrolSafecheckconfigEntity> one=famsAircontrolSafecheckconfigService.getConfigList(FamsAircontrolSafecheckconfigEntity.class,"通用部分","1","1");
			//航班保障前
			List<FamsAircontrolSafecheckconfigEntity> two=famsAircontrolSafecheckconfigService.getConfigList(FamsAircontrolSafecheckconfigEntity.class,"航班保障前","1","1");;
			//航班保障过程
			List<FamsAircontrolSafecheckconfigEntity> three=famsAircontrolSafecheckconfigService.getConfigList(FamsAircontrolSafecheckconfigEntity.class,"航班保障过程","1","1");;
			//航班保障结束后
			List<FamsAircontrolSafecheckconfigEntity> firth=famsAircontrolSafecheckconfigService.getConfigList(FamsAircontrolSafecheckconfigEntity.class,"航班保障结束后","1","1");;


			config.add( new FamsAircontrolSafecheckconfigListEntity("通用部分",one,null));
			config.add (new FamsAircontrolSafecheckconfigListEntity("航班保障前",two,null));
			config.add( new FamsAircontrolSafecheckconfigListEntity("航班保障过程",three,null));
			config.add( new FamsAircontrolSafecheckconfigListEntity("航班保障结束后",firth,null));
		}catch(Exception e) {
			message="航班保障获取配置失败";
			logger.error("航班保障检查单app:"+message,e);
			return Result.error(message);

		}

		return Result.success(config);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建航班保障检查单")
	public ResponseMessage<?> create(@ApiParam(name="航班保障检查单对象")@RequestBody FamsAircontrolSafecheckEntity famsAircontrolSafecheck) {

		String message;
		//保存
		try{
			famsAircontrolSafecheckService.save(famsAircontrolSafecheck,famsAircontrolSafecheck.getRadioResult(),famsAircontrolSafecheck.getPctureApp());

		} catch (Exception e) {

			message="航班保障检查单保存失败";
			logger.error("航班保障检查单app:"+message,e);
			return Result.error(message);
		}
		return Result.success(famsAircontrolSafecheck);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取航班保障检查单",notes="根据ID获取航班保障检查单",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		String message;
		FamsAircontrolSafecheckEntity task;
		Map map=new HashMap();

		try{
			task= famsAircontrolSafecheckService.get(FamsAircontrolSafecheckEntity.class, id);
			if (task == null) {
				return Result.error("根据ID获取航班保障检查单为空");


			}
			if (task != null) {
				//图片
				Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"safecheck",task.getId(),"1",null);
				//map=Util.filesToMap((List<FamsCommonFileEntity>)pacList);
				map.put("pacList",pacList);


				//通用部分
				List<Map> one=safecheckdetailServiceI.querySafecheckDetail(id,"通用部分");
				//航班保障前
				List<Map> two=safecheckdetailServiceI.querySafecheckDetail(id,"航班保障前");;
				//航班保障过程
				List<Map> three=safecheckdetailServiceI.querySafecheckDetail(id,"航班保障过程");;
				//航班保障结束后
				List<Map> firth=safecheckdetailServiceI.querySafecheckDetail(id,"航班保障结束后");;

				List<FamsAircontrolSafecheckconfigListEntity> config=new ArrayList<FamsAircontrolSafecheckconfigListEntity>();

				config.add( new FamsAircontrolSafecheckconfigListEntity("通用部分",null,one));
				config.add (new FamsAircontrolSafecheckconfigListEntity("航班保障前",null,two));
				config.add( new FamsAircontrolSafecheckconfigListEntity("航班保障过程",null,three));
				config.add( new FamsAircontrolSafecheckconfigListEntity("航班保障结束后",null,firth));

				map.put("famsAircontrolSafecheckconfigListEntity",config);

			}
		}catch (Exception e){
			message="获取航班保障检查单详细信息失败";
			logger.error("航班保障检查单app:"+message,e);
			return Result.error(message);
		}


		return Result.success(task,	map);

	}
	@RequestMapping(value = "doUpdate",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新航班保障检查单")
	public ResponseMessage<?> doUpdate(@ApiParam(name="航班保障检查单对象")@RequestBody FamsAircontrolSafecheckEntity famsAircontrolSafecheck) {
		FamsAircontrolSafecheckEntity t = famsAircontrolSafecheckService.get(FamsAircontrolSafecheckEntity.class, famsAircontrolSafecheck.getId());
		String message;

		//保存
		try{
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolSafecheck, t);
			//自定义的更新
			famsAircontrolSafecheckService.update(t,t.getRadioResult(),t.getPctureApp());

		} catch (Exception e) {
			message="航班保障检查单更新失败";
			logger.error("航班保障检查单app:"+message,e);
			return Result.error(message);
		}
		return Result.success(famsAircontrolSafecheck);
	}





}
