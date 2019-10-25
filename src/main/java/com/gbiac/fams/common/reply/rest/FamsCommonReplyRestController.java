package com.gbiac.fams.common.reply.rest;

import com.alibaba.fastjson.JSONArray;
import com.gbiac.fams.business.anounce.entity.FamsAnnounceNotifyEntity;
import com.gbiac.fams.business.anounce.entity.NotifyContant;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
import com.gbiac.fams.common.reply.service.FamsCommonReplyServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.jwt.util.menu.ResponseMessageCodeEnum;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**关闭功能
 * @Title: Controller
 * @Description: 通用回复表
 * @author 邓正辉
 * @date 2019-01-31 10:48:45
 * @version V1.0
 *
 */
//@Api(value = "FamsCommonReply", description = "通用回复表", tags = "famsCommonReplyController")
//@Controller
//@RequestMapping("/famsCommonReplyRestController")
public class FamsCommonReplyRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsCommonReplyRestController.class);

	@Autowired
	private FamsCommonReplyServiceI famsCommonReplyService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 *邓正辉
	 * 此接口废弃
	 * @param famsCommonReplyEntity
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
//	@ApiOperation(value = "通用回复表列表信息", produces = "application/json", httpMethod = "POST")
	public ResponseMessage<List<FamsCommonReplyEntity>> list(
			@ApiParam(name = "通用回复对象") @RequestBody FamsCommonReplyEntity famsCommonReplyEntity) {
//		if(pageSize > Globals.MAX_PAGESIZE){
//			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
//		}

		List<FamsCommonReplyEntity> listFamsCommonReplys = famsCommonReplyService
				.queryListByEntity(famsCommonReplyEntity);
		return Result.success(listFamsCommonReplys);
	}

	/**
	 * app用户对表单或者信息进行回复
	 * 
	 * @author Mchen
	 * @param businessId
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/replyToBusiness",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "回复表单", notes = "回复表单", httpMethod = "POST")
	public ResponseMessage<?> replyForm(
			@RequestBody FamsCommonReplyEntity entity) {
		try {
			return saveReplyInfo(entity.getId(), entity.getReplyContent(), entity.getState(), entity.getModule());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("回复失败");
		}

	}
	
	
	
	/**
	 * app用户对信息进行回复
	 * 
	 * @author Mchen
	 * @param businessId
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/replyToReply",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "回复信息", notes = "回复信息", httpMethod = "POST")
	public ResponseMessage<?> replyInfo(
			@RequestBody FamsCommonReplyEntity entity) {
		try {
			return saveReplyInfoForUser(entity.getId(), entity.getReplyContent(), entity.getState(), entity.getModule());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("回复失败");
		}

	}

	@RequestMapping(value = "/listReplyInfos")
	@ApiOperation(value = "获取表单下所有的回复", notes = "获取表单下所有的回复", httpMethod = "GET")
	@ResponseBody
	public ResponseMessage<?> listReplyInfos(
			@ApiParam(name = "id", value = "回复表单的id", required = true) @RequestParam(value = "id") String businessId,
			@ApiParam(name = "state", value = "状态", required = false) @RequestParam(value = "state",required=false) String state) {
		if (org.apache.commons.lang.StringUtils.isBlank(businessId)) {
			return Result.error("表单Id不能为空");
		}
		try {
			List<Map<String, Object>> res = null;
			if (StringUtils.isBlank(state)) {
				res = famsCommonReplyService.selectRepliesByBusinessId(businessId);
			}else {
				res = famsCommonReplyService.selectRepliesByBusinessId(businessId,state);
			}
			 
			return Result.success(res);
		} catch (Exception e) {
			return Result.error("获取回复消息失败");
		}
	}

	/**
	 * 保存回复表单消息
	 * 
	 * @author Mchen
	 * 
	 * @param replyEntity
	 * @param businessId
	 * @param content
	 * @param state
	 * @param moduleName
	 * @return
	 * @throws Exception
	 */
	private ResponseMessage<?> saveReplyInfo(String businessId, String content, String state, String moduleName)
			throws Exception {
		String id = "";
		if (org.apache.commons.lang.StringUtils.isBlank(content)
				|| org.apache.commons.lang.StringUtils.isBlank(moduleName) || content.length() > 200
				|| moduleName.length() > 50) {
			return Result.error("回复失败");
		} else if (StringUtils.isBlank(state)) {
			state = null;
		}
		// 回复表单
		id = famsCommonService.saveReplyShow(moduleName, businessId, state, content);
		Map<String, String> res = new HashMap<>();
		res.put("id", id);
		return Result.success("回复成功", res);
	}
	
	/**
	 * 保存回复消息的回复
	 * 
	 * @author Mchen
	 * 
	 * @param replyEntity
	 * @param businessId
	 * @param content
	 * @param state
	 * @param moduleName
	 * @return
	 * @throws Exception
	 */
	private ResponseMessage<?> saveReplyInfoForUser(String businessId, String content, String state, String moduleName)
			throws Exception {
		String id = "";
		//获取带回复的消息
		FamsCommonReplyEntity toReplyEntity = famsCommonReplyService.getEntity(FamsCommonReplyEntity.class, businessId);
		if (toReplyEntity ==null||org.apache.commons.lang.StringUtils.isBlank(content)
				|| org.apache.commons.lang.StringUtils.isBlank(moduleName) || content.length() > 200
				|| moduleName.length() > 50) {
			return Result.error("回复失败");
		} else if (StringUtils.isBlank(state)) {
			state = null;
		}
		// 回复消息
		id = famsCommonService.saveReplyShow(moduleName, businessId, toReplyEntity.getReplyUserId(), toReplyEntity.getReplyDepartId(), state, content);
		Map<String, String> res = new HashMap<>();
		res.put("id", id);
		return Result.success("回复成功", res);
	}

}
