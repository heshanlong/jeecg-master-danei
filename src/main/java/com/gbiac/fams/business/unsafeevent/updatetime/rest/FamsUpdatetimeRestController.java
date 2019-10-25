package com.gbiac.fams.business.unsafeevent.updatetime.rest;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageDto;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageEntity;
import com.gbiac.fams.business.unsafeevent.updatetime.entity.FamsUpdatetimeEntity;
import com.gbiac.fams.business.unsafeevent.updatetime.service.FamsUpdatetimeServiceI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: fams_updatetime
 * @author onlineGenerator
 * @date 2019-03-11 16:07:52
 * @version V1.0   
 *
 */
@Api(value="famsUpdatetimeRestController",description="异常事件编辑记录",tags="famsUpdatetimeRestController")
@Controller
@RequestMapping("/famsUpdatetimeRestController")
public class FamsUpdatetimeRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsUpdatetimeRestController.class);

	@Autowired
	private FamsUpdatetimeServiceI famsUpdatetimeService;
	@Autowired
	private SystemService systemService;
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
		List<FamsUpdatetimeEntity> array=famsUpdatetimeService.listApp(dto.getId(), dto.getPageApp(), dto.getPageSize(), request);

		return Result.success(array);
	}
	
}
