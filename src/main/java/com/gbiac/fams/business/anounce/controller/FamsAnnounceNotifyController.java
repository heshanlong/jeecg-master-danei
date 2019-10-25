package com.gbiac.fams.business.anounce.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.converter.ContentTypeResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONArray;
import com.gbiac.fams.business.anounce.entity.CommonState;
import com.gbiac.fams.business.anounce.entity.FamsAnnounceNotifyEntity;
import com.gbiac.fams.business.anounce.entity.FamsAnounceGroupEntity;
import com.gbiac.fams.business.anounce.entity.FamsAnounceGroupUser;
import com.gbiac.fams.business.anounce.entity.FamsAnounceReceive;
import com.gbiac.fams.business.anounce.entity.NotifyContant;
import com.gbiac.fams.business.anounce.enums.NotifyType;
import com.gbiac.fams.business.anounce.enums.SendPattern;
import com.gbiac.fams.business.anounce.service.FamsAnnounceNotifyServiceI;
import com.gbiac.fams.business.anounce.vo.NotifyReceiveVO;
import com.gbiac.fams.business.anounce.vo.ReceiveVO;
import com.gbiac.fams.business.anounce.vo.ReplyInfoVO;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
import com.gbiac.fams.common.reply.service.FamsCommonReplyServiceI;
import com.gbiac.fams.restutil.SessionUserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @Title: Controller
 * @Description: 通知通告功能模块，用户向指定用户发送信息
 * @author Mchen
 * @date 2019-01-23 13:47:56
 * @version V1.0
 *
 */
@Api(value = "FamsAnnounceNotify", description = "通知通告表", tags = "famsAnnounceNotifyController")
@Controller
@RequestMapping("/famsAnnounceNotifyController")
public class FamsAnnounceNotifyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAnnounceNotifyController.class);

	@Autowired
	private FamsAnnounceNotifyServiceI famsAnnounceNotifyService;
	@Autowired
	private FamsCommonService famsCommonService;
	// 获取回复信息的服务
	@Autowired
	private FamsCommonReplyServiceI famsCommonReplyService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 把转换日期类型为(yyyy-MM-dd HH:mm)的字符串
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		format.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}

	/**
	 * 通知通告表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/anounce/famsAnnounceNotifyList");
	}

	/**
	 * easyui AJAX请求数据 返回自己创建的通知通告(权限由jeecg配置)
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FamsAnnounceNotifyEntity famsAnnounceNotify, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsAnnounceNotifyEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAnnounceNotify,
				request.getParameterMap());
		try {
			cq.eq("updateBy", ResourceUtil.getSessionUser().getUserName());
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsAnnounceNotifyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 撤回通知通告表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAnnounceNotifyEntity famsAnnounceNotify, @RequestParam("reason") String reason, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAnnounceNotify = systemService.getEntity(FamsAnnounceNotifyEntity.class, famsAnnounceNotify.getId());
		message = "通知通告撤回成功";
		try {
			famsAnnounceNotifyService.delete(famsAnnounceNotify,reason);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "通知通告撤回失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除通知通告表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通知通告表删除成功";
		try {
			for (String id : ids.split(",")) {
				FamsAnnounceNotifyEntity famsAnnounceNotify = systemService.getEntity(FamsAnnounceNotifyEntity.class,
						id);
				famsAnnounceNotifyService.delete(famsAnnounceNotify);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "通知通告表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加通知通告表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(@Valid FamsAnnounceNotifyEntity famsAnnounceNotify, BindingResult br,
			@RequestParam(value = "files", defaultValue = "") String fileId,
			@RequestParam(value = "userId") String userId, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通知通告发送成功";
		// 判断是否有验证不合格的数据
		if (br.hasErrors() || !validator.validate(famsAnnounceNotify).isEmpty()) {
			message = "通知通告发送失败:";
			logger.error(br.getAllErrors().toString());
			j.setMsg(message);
			System.out.println(validator.validate(famsAnnounceNotify));
			System.out.println(validator.validate(famsAnnounceNotify).size());
			return j;
		}
		// 如果是current模式 将userId放到groupId中
				if (NotifyContant.SEND_PATTERN_CURRENT.equals(famsAnnounceNotify.getSendPattern())) {
					famsAnnounceNotify.setGroupId(userId);
				}

		try {
			// 保存通知消息
			Date date = new Date();
//			famsAnnounceNotify.setCreateBy(ResourceUtil.getSessionUser().getUserName());
//			famsAnnounceNotify.setCreateDate(date);
			famsAnnounceNotify.setUpdateBy(ResourceUtil.getSessionUser().getUserName());
			famsAnnounceNotify.setUpdateDate(date);
			famsAnnounceNotify.setSendTime(date);
			famsAnnounceNotify.setState(CommonState.NORMAL_STATE);
			int code = 0;
			synchronized (this) {
				code = famsAnnounceNotifyService.saveOrUpdate(famsAnnounceNotify, fileId);
			}
			if (code != 0) {
				message = "通知通告发送失败";
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "通知通告发送失败";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新通知通告表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(@Validated FamsAnnounceNotifyEntity famsAnnounceNotify, BindingResult br,
			@RequestParam(value = "files", defaultValue = "") String fileId,
			@RequestParam(value = "userId") String userId, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "通知通告发送成功";
		// 判断是否有验证不合格的数据
		if (StringUtils.isBlank(famsAnnounceNotify.getId()) || br.hasErrors()) {
			message = "通知通告发送失败:";
			message += br.getAllErrors();
			j.setMsg(message);
			return j;
		}
		// 如果是current模式 将userId放到groupId中
		if (NotifyContant.SEND_PATTERN_CURRENT.equals(famsAnnounceNotify.getSendPattern())) {
			famsAnnounceNotify.setGroupId(userId);
		}
		try {
			FamsAnnounceNotifyEntity entity = famsAnnounceNotifyService.getEntity(FamsAnnounceNotifyEntity.class,
					famsAnnounceNotify.getId());
			famsAnnounceNotify.setCreateBy(entity.getCreateBy());
			famsAnnounceNotify.setCreateDate(entity.getCreateDate());
			famsAnnounceNotify.setState(CommonState.NORMAL_STATE);
			famsAnnounceNotify.setSendTime(new Date());
			// 保存通知消息
			int code = famsAnnounceNotifyService.saveOrUpdate(famsAnnounceNotify, fileId);
			if (code != 0) {
				message = "通知通告发送失败";
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "通知通告发送失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 通知通告表新增页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAnnounceNotifyEntity famsAnnounceNotify, HttpServletRequest req) throws Exception {
		if (StringUtil.isNotEmpty(famsAnnounceNotify.getId())) {
			famsAnnounceNotify = famsAnnounceNotifyService.getEntity(FamsAnnounceNotifyEntity.class,
					famsAnnounceNotify.getId());
			// 设置信息
			req.setAttribute("famsAnnounceNotify", famsAnnounceNotify);

		}
		try {
			// 获取全员人数
			List<TSBaseUser> users = famsCommonService.findByProperty(TSBaseUser.class, "deleteFlag", Globals.Delete_Normal);
			// 获取飞管内部
			List<TSBaseUser> departUsers = famsAnnounceNotifyService.getAllUsersOfDepart();
			// 设置全员人数
			req.setAttribute("allNumbers", users.size());
			req.setAttribute("internalNumbers", departUsers.size());
			//设置当前人
			req.setAttribute("curUserName", Util.getUserName());
		} catch (Exception e) {
			throw new Exception("获取人数错误");
		}
		return new ModelAndView("com/gbiac/fams/business/anounce/famsAnnounceNotify-add");
	}
	
	/**
	 * 通知通告表编辑页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "goDetail")
	public ModelAndView goDetail(FamsAnnounceNotifyEntity famsAnnounceNotify, HttpServletRequest req,@RequestParam(value="withdraw",required = false)String withdraw) throws Exception{
		ModelAndView mv = new ModelAndView("com/gbiac/fams/business/anounce/famsAnnounceNotify-read");
		try {
			// 根据用户Id和通告Id获取接收情况
			String[] pros = new String[] { "userId", "notifyId" };
			String[] vals = new String[] { Util.getUserId(), famsAnnounceNotify.getId() };
			FamsAnounceReceive receive = famsCommonService.getEntityByProperty(FamsAnounceReceive.class, pros,
					vals);
			if (receive != null && NotifyContant.ANOUNCE_NO_READ.equals(receive.getState())) {
				receive.setState(NotifyContant.ANOUNCE_READ);
				Date date = new Date();
				receive.setReadTime(date);
				receive.setUpdateDate(date);
				receive.setUpdateBy(Util.getUserName());
				famsCommonService.updateEntitie(receive);
			}
			Map<String, Object> map = getNotifyDetailMap(famsAnnounceNotify.getId(), new Date());
			map.put("files", Util.filesToMap((List<FamsCommonFileEntity>)(map.get("files"))));
			if (withdraw!=null) {
				map.put("withdraw", withdraw);
			}
			mv.addAllObjects(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	

	/**
	 * 通知通告表编辑页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAnnounceNotifyEntity famsAnnounceNotify, HttpServletRequest req) throws Exception {
		ModelAndView mv = new ModelAndView("com/gbiac/fams/business/anounce/famsAnnounceNotify-update");
		String detail = req.getParameter("load");
		if (StringUtil.isNotEmpty(famsAnnounceNotify.getId())) {
			if (StringUtils.isBlank(detail)) {
				// 获取通知通告详情
				famsAnnounceNotify = famsAnnounceNotifyService.getEntity(FamsAnnounceNotifyEntity.class,
						famsAnnounceNotify.getId());
				//刷新缓存
				famsAnnounceNotifyService.getSession().clear();

				// 获取全员人数
				List<TSBaseUser> users = famsCommonService.findByProperty(TSBaseUser.class, "deleteFlag", Globals.Delete_Normal);
				// 设置全员人数
				req.setAttribute("receiveNumbers", users.size());
				req.setAttribute("allNumbers", users.size());
				// 设置飞管部门人数
				List<TSBaseUser> departUsers = famsAnnounceNotifyService.getAllUsersOfDepart();
				req.setAttribute("internalNumbers", departUsers.size());
				// 判断接收人并查询接收人
				if (NotifyContant.SEND_PATTERN_CURRENT.equals(famsAnnounceNotify.getSendPattern())) {
					// 根据通告id查接收人
					List<FamsAnounceReceive> reList = famsCommonService.findByProperty(FamsAnounceReceive.class,
							"notifyId", famsAnnounceNotify.getId());
					StringBuilder userIdBuilder = new StringBuilder(reList.size() * 36);
					StringBuilder realNames = new StringBuilder(reList.size() * 36);
					for (FamsAnounceReceive uId : reList) {
						realNames.append(
								((TSUser) (famsCommonService.getEntity(TSUser.class, uId.getUserId()))).getRealName());
						userIdBuilder.append(uId.getUserId());
						realNames.append(",");
						userIdBuilder.append(",");
					}
					String realName = realNames.length() > 0 ? realNames.substring(0, realNames.length() - 1) : "";
					String userIds = userIdBuilder.length() > 0 ? userIdBuilder.substring(0, userIdBuilder.length() - 1)
							: "";
					mv.addObject("realName", realName);
					mv.addObject("userIds", userIds);
					// 重设接收人数
					req.setAttribute("receiveNumbers", reList.size());

				}
				if (NotifyContant.SEND_PATTERN_INTERNAL.equals(famsAnnounceNotify.getSendPattern())) {
					// 重设接收人数
					req.setAttribute("receiveNumbers", departUsers.size());
				}
				if (NotifyContant.SEND_PATTERN_CUSTOM.equals(famsAnnounceNotify.getSendPattern())
						&& !NotifyContant.SEND_PATTERN_CUSTOM.equals(famsAnnounceNotify.getGroupId())) {
					FamsAnounceGroupEntity famsAnounceGroupEntity = systemService
							.getEntity(FamsAnounceGroupEntity.class, famsAnnounceNotify.getGroupId());
					req.setAttribute("famsAnounceGroup", famsAnounceGroupEntity);
				}
				// 获取上传的文件
				List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class,
						NotifyContant.MODULE_NAME, famsAnnounceNotify.getId(), "1", null);
				//修改通知通告的发送时间
				famsAnnounceNotify.setSendTime(new Date());
				mv.addObject("famsAnnounceNotify", famsAnnounceNotify);
				mv.addObject("files", Util.filesToMap(files));
			} 

		}
		return mv;
	}

	/**
	 * 暂时不用的方法
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "famsAnnounceNotifyController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 暂时不用的方法 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAnnounceNotifyEntity famsAnnounceNotify, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAnnounceNotifyEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAnnounceNotify,
				request.getParameterMap());
		List<FamsAnnounceNotifyEntity> famsAnnounceNotifys = this.famsAnnounceNotifyService.getListByCriteriaQuery(cq,
				false);
		modelMap.put(NormalExcelConstants.FILE_NAME, "通知通告表");
		modelMap.put(NormalExcelConstants.CLASS, FamsAnnounceNotifyEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("通知通告表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, famsAnnounceNotifys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 暂时不用的方法 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAnnounceNotifyEntity famsAnnounceNotify, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME, "通知通告表");
		modelMap.put(NormalExcelConstants.CLASS, FamsAnnounceNotifyEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("通知通告表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 暂时不用的方法
	 */
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
				List<FamsAnnounceNotifyEntity> listFamsAnnounceNotifyEntitys = ExcelImportUtil
						.importExcel(file.getInputStream(), FamsAnnounceNotifyEntity.class, params);
				for (FamsAnnounceNotifyEntity famsAnnounceNotify : listFamsAnnounceNotifyEntitys) {
					famsAnnounceNotifyService.save(famsAnnounceNotify);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}

	/**
	 * 暂时不用的方法
	 */
	@RequestMapping(value = "/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	// @ApiOperation(value = "通知通告表列表信息", produces = "application/json", httpMethod
	// = "GET")
	public ResponseMessage<List<FamsAnnounceNotifyEntity>> list(@PathVariable("pageNo") int pageNo,
			@PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if (pageSize > Globals.MAX_PAGESIZE) {
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(FamsAnnounceNotifyEntity.class);
		query.setCurPage(pageNo <= 0 ? 1 : pageNo);
		query.setPageSize(pageSize < 1 ? 1 : pageSize);
		List<FamsAnnounceNotifyEntity> listFamsAnnounceNotifys = this.famsAnnounceNotifyService
				.getListByCriteriaQuery(query, true);
		return Result.success(listFamsAnnounceNotifys);
	}

	/**
	 * 暂时不用的方法
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据ID获取通知通告表信息", notes = "根据ID获取通知通告表信息", httpMethod = "GET", produces = "application/json")
	public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
		FamsAnnounceNotifyEntity task = famsAnnounceNotifyService.get(FamsAnnounceNotifyEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取通知通告表信息为空");
		}
		return Result.success(task);
	}

	/**
	 * 暂时不用的方法
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	// @ApiOperation(value = "创建通知通告表")
	public ResponseMessage<?> create(
			@ApiParam(name = "通知通告表对象") @RequestBody FamsAnnounceNotifyEntity famsAnnounceNotify,
			UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FamsAnnounceNotifyEntity>> failures = validator.validate(famsAnnounceNotify);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		// 保存
		try {
			famsAnnounceNotifyService.save(famsAnnounceNotify);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("通知通告表信息保存失败");
		}
		return Result.success(famsAnnounceNotify);
	}

	/**
	 * 暂时不用的方法
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	// @ApiOperation(value = "更新通知通告表", notes = "更新通知通告表")
	public ResponseMessage<?> update(
			@ApiParam(name = "通知通告表对象") @RequestBody FamsAnnounceNotifyEntity famsAnnounceNotify) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FamsAnnounceNotifyEntity>> failures = validator.validate(famsAnnounceNotify);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		// 保存
		try {
			famsAnnounceNotifyService.saveOrUpdate(famsAnnounceNotify);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新通知通告表信息失败");
		}

		// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新通知通告表信息成功");
	}

	/**
	 * 暂时不用的方法
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	// @ApiOperation(value = "删除通知通告表")
	public ResponseMessage<?> delete(
			@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
		logger.info("delete[{}]", id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			famsAnnounceNotifyService.deleteEntityById(FamsAnnounceNotifyEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("通知通告表删除失败");
		}

		return Result.success();
	}

	/**
	 * app用于获取详细的通知消息的信息
	 * 
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/anounce/getNoticeDetail")
	@ApiOperation(value = "阅读通告信息", notes = "阅读通告信息", httpMethod = "GET")
	@ResponseBody
	public ResponseMessage<?> readAnnounce(
			@ApiParam(name = "id", value = "通知通告ID", required = true) @RequestParam String id, HttpServletRequest req) {
		// 获取当前用户的id
		String uid = SessionUserUtil.getCurrentUserId();
		try {
			Date date = new Date();
			// 修改当前用户通告接收的状态
			FamsAnounceReceive receive = famsAnnounceNotifyService.getAnounceReceive(id, uid);
			if (receive != null && NotifyContant.ANOUNCE_NO_READ.equals(receive.getState())) {
				receive.setState(NotifyContant.ANOUNCE_READ);
				receive.setReadTime(date);
				systemService.updateEntitie(receive);
			}

			return Result.success(getAppNotifyDetailMap(id, date));
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("获取信息失败");
		}

	}

	/**
	 * 获取用户通告详情 注意：controller层组装结果导致请求数据多次，建议挪到sql组装。 这里结构混乱，建议分成通知消息和回复消息分开处理。
	 * 
	 * @param id   通知通告Id
	 * @param date 当前日期
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getAppNotifyDetailMap(String id, Date date) throws Exception {
		Map<String, Object> tmpMap = getNotifyDetailMap(id, date);
		Map<String, Object> resMap = new HashMap<>();
		
		// 获取通知通告对象
		FamsAnnounceNotifyEntity famsAnnounceNotifyEntity = (FamsAnnounceNotifyEntity) tmpMap.get("famsAnnounceNotify");
		// 放置通告信息
		resMap.put("id", famsAnnounceNotifyEntity.getId());
		resMap.put("title", famsAnnounceNotifyEntity.getTitle());
		resMap.put("content", famsAnnounceNotifyEntity.getContent());
		resMap.put("noticeType", famsAnnounceNotifyEntity.getType());
		resMap.put("signer", famsAnnounceNotifyEntity.getSender());
		resMap.put("issueTime", famsAnnounceNotifyEntity.getSendTime());
	//	resMap.put("sender", famsAnnounceNotifyEntity.getUpdateBy());
		resMap.put("dateline", famsAnnounceNotifyEntity.getUpdateDate());
		resMap.put("acceptor", tmpMap.get("realName"));
		resMap.put("receive", tmpMap.get("receive"));
		resMap.put("rate", tmpMap.get("readSum") + "/" + tmpMap.get("receiveSum") + "已阅");
		// 放置附件信息
		resMap.put("attachment", Util.filesToArray((List<FamsCommonFileEntity>)(tmpMap.get("files"))));
		return resMap;
	}

	/**
	 * 根据通知通告发送模式{@link com.gbiac.fams.business.anounce.entity.NotifyContant}返回用户信息的字符串
	 * 
	 * @param entity
	 * @return
	 * @see com.gbiac.fams.business.anounce.entity.NotifyContant;
	 */
	private String queryUserInfoBySendPattern(FamsAnnounceNotifyEntity entity) {
		List<TSBaseUser> users = null;
		// 根据发送模式获取接收用户的信息
		switch (entity.getSendPattern()) {
		case NotifyContant.SEND_PATTERN_ALL:
			// 全员
			users = systemService.getList(TSBaseUser.class);
			break;
		case NotifyContant.SEND_PATTERN_INTERNAL:
		case NotifyContant.SEND_PATTERN_CURRENT:
			// 值班用户
			List<FamsAnounceReceive> receives = systemService.findByProperty(FamsAnounceReceive.class, "notifyId",
					entity.getId());
			users = new ArrayList<>(receives.size());
			for (FamsAnounceReceive receive : receives) {
				users.add(systemService.getEntity(TSBaseUser.class, receive.getUserId()));
			}
			break;
		case NotifyContant.SEND_PATTERN_CUSTOM:
			// 维护分组
			List<FamsAnounceGroupUser> list = systemService.findByProperty(FamsAnounceGroupUser.class, "groupId",
					entity.getGroupId());
			users = new ArrayList<>(list.size());
			for (FamsAnounceGroupUser fid : list) {
				users.add(systemService.getEntity(TSBaseUser.class, fid.getUserId()));
			}
			break;
		default:
			// 设置空数组
			users = new ArrayList<>();
			break;
		}
		// 构造用户名字符串
		StringBuilder sb = new StringBuilder(users.size() * 10);
		for (TSBaseUser user : users) {
			sb.append(user.getRealName()).append(",");
		}
		return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
	}

	/**
	 * 转换通知类型成相应的字符串{@link com.gbiac.fams.business.anounce.enums.NotifyType}}
	 * 
	 * @param noticeType
	 * @return
	 */
	private String noticeTypeConverse(String noticeType) {
		if (noticeType.equals(NotifyType.GENERAL.getName())) {
			return NotifyType.GENERAL.getValue();
		} else if (noticeType.equals(NotifyType.RUN.getName())) {
			return NotifyType.RUN.getValue();
		} else if (noticeType.equals(NotifyType.VOYAGE.getName())) {
			return NotifyType.VOYAGE.getValue();
		}
		return "";
	}
	/**
	 * 将发送模式进行转换
	 * @param sendPattern 发送模式
	 * @return
	 */
	private String sendPatternConverse(String sendPattern) {
		if (sendPattern.equals(SendPattern.All.getPattern())) {
			return SendPattern.All.getName();
		} else if (sendPattern.equals(SendPattern.Current.getPattern())) {
			return SendPattern.Current.getName();
		} else if (sendPattern.equals(SendPattern.Custom.getPattern())) {
			return SendPattern.Custom.getName();
		}else if(sendPattern.equals(SendPattern.Internal.getPattern())) {
			return SendPattern.Internal.getName();
		}
		return "";
	}

	/**
	 * 获取用户通告详情
	 * 
	 * @param id   通知通告Id
	 * @param date 当前日期
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getNotifyDetailMap(String id, Date date) throws Exception {
		Map<String, Object> map = new HashMap<>();
		// 获取通告信息
		FamsAnnounceNotifyEntity notifyEntity = famsAnnounceNotifyService.getEntity(FamsAnnounceNotifyEntity.class, id);
		//清除缓存
		famsAnnounceNotifyService.getSession().clear();
		// 获取阅读数量
		Integer[] sum = famsAnnounceNotifyService.countAnnounce(id);
		// 获取文件信息
		List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class,
				NotifyContant.MODULE_NAME, notifyEntity.getId(), "1", null);
		// 获取回复的消息
//关闭		List<Map<String, Object>> replys = famsCommonReplyService.selectRepliesByBusinessId(notifyEntity.getId());
		// 获取当前用户的信息
		TSUser currentUser = famsCommonService.getEntity(TSUser.class, Util.getUserId());

		// 获取通知通告的阅读情况
		List<ReceiveVO> receiveVOs= famsAnnounceNotifyService.listReceiveVOs(id);
		StringBuilder realNames = new StringBuilder(receiveVOs.size() * 36);
		for (ReceiveVO uId : receiveVOs) {
			realNames.append(uId.getRealName());
			realNames.append(",");
		}
		String realName = realNames.length() > 0 ? realNames.substring(0, realNames.length() - 1) : "";
		map.put("realName", realName);
		map.put("receiverCount", receiveVOs.size());
		map.put("receive", receiveVOs);
		//转换通告类型和发送模式
		notifyEntity.setType(noticeTypeConverse(notifyEntity.getType()));
		notifyEntity.setSendPattern(sendPatternConverse(notifyEntity.getSendPattern()));
		//将内容的换行转换为</br>标签
		notifyEntity.setContent(notifyEntity.getContent().replaceAll("\\r\\n", "<br/>"));
	
		map.put("famsAnnounceNotify", notifyEntity);
		map.put("files", files);
		map.put("receiveSum", sum[0]);
		map.put("readSum", sum[1]);
		map.put("currentUser", currentUser);
		map.put("receiveNumbers", "0");
		return map;
	}

	/**
	 * app用于获取用户接收的消息列表
	 * 
	 * @param offSet
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/anounce/getNoticeList")
	@ResponseBody
	@ApiOperation(value = "获取通告通告列表", notes = "获取通告通告列表", httpMethod = "GET")
	public ResponseMessage<?> listAnnounce(
			@ApiParam(name = "pageStart", value = "偏移量", required = true) @RequestParam(value = "pageStart") Integer offSet,
			@ApiParam(name = "pageEnd", value = "返回数量", required = true) @RequestParam(value = "pageEnd") Integer limit,
			@ApiParam(name = "searchInput", value = "搜索信息(标题或者签发人)", required = false) @RequestParam(value = "searchInput", required = false) String searchInfo) {
		List<Map<String, Object>> res = new ArrayList<>();
		// 获取app用户
		String userId = SessionUserUtil.getCurrentUserId();
		try {
			offSet = (offSet - 1) * limit;
			List<Map<String, Object>> lists = famsAnnounceNotifyService.selectNotifiesByUserId(userId, offSet, limit,
					searchInfo);
			return Result.success(lists);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("获取消息失败");
		}

	}

	/**
	 * app用户获取用户未读的消息数量
	 * 
	 * @return
	 */
	@RequestMapping(value = "/anounce/count")
	@ResponseBody
	@ApiOperation(value = "获取未读通告数量", notes = "获取未读通告数量", httpMethod = "GET")
	public ResponseMessage<?> countAnnounce() {
		String userId = SessionUserUtil.getCurrentUserId();
		int count = famsAnnounceNotifyService.countNoReadAnnounce(userId);
		Map<String, Integer> res = new HashMap<String, Integer>();
		res.put("count", count);
		return Result.success(res);
	}

	/**********************************
	 * 回复接口
	 *********************************************************************************/
	/**************************************************************************************************************************/
	/*************************************************************************************************************************/

	/**关闭
	 * app用户对表单或者信息进行回复
	 * 
	 * @author Mchen
	 * @param businessId
	 * @param content
	 * @return
	 */
//	@RequestMapping(params = "replyToBusiness")
//	@ResponseBody
	public ResponseMessage<?> replyForm(@RequestParam(value = "id") String businessId,
			@RequestParam(value = "module") String moduleName, @RequestParam(required = false) String state,
			@RequestParam String content) {
		try {
			return saveReplyInfo(businessId, content, state, moduleName);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("回复失败");
		}

	}

	/**关闭
	 * app用户对信息进行回复
	 * 
	 * @author Mchen
	 * @param businessId
	 * @param content
	 * @return
	 */
//	@RequestMapping(params = "replyToReply")
//	@ResponseBody
	public ResponseMessage<?> replyInfo(@RequestParam(value = "id") String businessId,
			@RequestParam(value = "module") String moduleName, @RequestParam(required = false) String state,
			@RequestParam String content) {
		try {
			return saveReplyInfoForUser(businessId, content, state, moduleName);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("回复失败");
		}

	}
//关闭
//	@RequestMapping(value = "/listReplyInfos")
//	@ResponseBody
	public ResponseMessage<?> listReplyInfos(@RequestParam(value = "id") String businessId) {
		if (org.apache.commons.lang.StringUtils.isBlank(businessId)) {
			return Result.error("表单Id不能为空");
		}
		try {
			List<Map<String, Object>> res = famsCommonReplyService.selectRepliesByBusinessId(businessId);
			return Result.success(res);
		} catch (Exception e) {
			return Result.error("获取回复消息失败");
		}
	}

	/**关闭
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
		FamsCommonReplyEntity entity = famsCommonService.getEntity(FamsCommonReplyEntity.class, id);
		ReplyInfoVO vo = converterInfoVO(entity);
		return Result.success(vo);
	}

	/**关闭
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
		// 获取带回复的消息
		FamsCommonReplyEntity toReplyEntity = famsCommonReplyService.getEntity(FamsCommonReplyEntity.class, businessId);
		if (toReplyEntity == null || org.apache.commons.lang.StringUtils.isBlank(content)
				|| org.apache.commons.lang.StringUtils.isBlank(moduleName) || content.length() > 200
				|| moduleName.length() > 50) {
			return Result.error("回复失败");
		} else if (StringUtils.isBlank(state)) {
			state = null;
		}
		// 回复消息
		id = famsCommonService.saveReplyShow(moduleName, businessId, toReplyEntity.getReplyUserId(),
				toReplyEntity.getReplyDepartId(), state, content);
		FamsCommonReplyEntity entity = famsCommonService.getEntity(FamsCommonReplyEntity.class, id);
		ReplyInfoVO vo = converterInfoVO(entity);
		return Result.success(vo);
	}
	//关闭
	private ReplyInfoVO converterInfoVO(FamsCommonReplyEntity entity) {
		// 将回复实体转换为回复VO
		ReplyInfoVO vo = new ReplyInfoVO();
		vo.setId(entity.getId());
		vo.setContent(entity.getReplyContent());
		vo.setDate(entity.getCreateTime());
		vo.setName(entity.getReplyUserId());
		vo.setDepartment(entity.getReplyDepartId());
		vo.setReplyName(entity.getReplyToUserId());
		vo.setReplyDepartment(entity.getFiled1());
		return vo;
	}

}
