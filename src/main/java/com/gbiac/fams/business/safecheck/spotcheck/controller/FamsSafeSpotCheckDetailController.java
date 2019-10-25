package com.gbiac.fams.business.safecheck.spotcheck.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONArray;
import com.gbiac.fams.business.safecheck.spotcheck.service.FamsSafeSpotCheckServiceI;
import com.jeecg.demo.entity.JformOrderTicket2Entity;
import com.jeecg.demo.page.JformOrderMain2Page;
import com.jeecg.demo.service.JformOrderMain2ServiceI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 抽查详情
 * @author onlineGenerator
 * @date 2018-03-27 17:03:29
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsSafeSpotCheckDetailController")
//@Api(value="JformOrderTicket2",description="抽查详情",tags="famsSafeSpotCheckDetailController")
public class FamsSafeSpotCheckDetailController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsSafeSpotCheckDetailController.class);

	@Autowired
	private FamsSafeSpotCheckServiceI famsSafeSpotCheckService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 抽查详情列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/safecheck/spotcheck/famsSafeSpotCheckDetail/list");
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
	public void datagrid(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JformOrderTicket2Entity.class, dataGrid);
		String mainId = request.getParameter("mainId");
		if(oConvertUtils.isNotEmpty(mainId)){
			//查询条件组装器
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jformOrderTicket2, request.getParameterMap());
			try{
			//自定义追加查询条件
				cq.eq("fckId", mainId);
			}catch (Exception e) {
				throw new BusinessException(e.getMessage());
			}
			cq.add();
			this.famsSafeSpotCheckService.getDataGridReturn(cq, true);
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除抽查详情
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(JformOrderTicket2Entity jformOrderTicket2, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		jformOrderTicket2 = systemService.getEntity(JformOrderTicket2Entity.class, jformOrderTicket2.getId());
		message = "抽查详情删除成功";
		try{
			if(jformOrderTicket2!=null){
				famsSafeSpotCheckService.delete(jformOrderTicket2);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "抽查详情删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除抽查详情
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "抽查详情删除成功";
		try{
			for(String id:ids.split(",")){
				JformOrderTicket2Entity jformOrderTicket2 = systemService.getEntity(JformOrderTicket2Entity.class, 
				id
				);
				if(jformOrderTicket2!=null){
					famsSafeSpotCheckService.delete(jformOrderTicket2);
					systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "抽查详情删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加抽查详情
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(JformOrderTicket2Entity jformOrderTicket2, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "抽查详情添加成功";
		try{
			famsSafeSpotCheckService.save(jformOrderTicket2);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "抽查详情添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新抽查详情
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(JformOrderTicket2Entity jformOrderTicket2, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "抽查详情更新成功";
		JformOrderTicket2Entity t = famsSafeSpotCheckService.get(JformOrderTicket2Entity.class, jformOrderTicket2.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(jformOrderTicket2, t);
			famsSafeSpotCheckService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "抽查详情更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 抽查详情新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(JformOrderTicket2Entity jformOrderTicket2, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jformOrderTicket2.getId())) {
			jformOrderTicket2 = famsSafeSpotCheckService.getEntity(JformOrderTicket2Entity.class, jformOrderTicket2.getId());
			req.setAttribute("jformOrderTicket2Page", jformOrderTicket2);
		}
		req.setAttribute("mainId", req.getParameter("mainId"));
		return new ModelAndView("com/gbiac/fams/business/safecheck/spotcheck/famsSafeSpotCheckDetail/add");
	}
	/**
	 * 抽查详情编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(JformOrderTicket2Entity jformOrderTicket2, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jformOrderTicket2.getId())) {
			jformOrderTicket2 = famsSafeSpotCheckService.getEntity(JformOrderTicket2Entity.class, jformOrderTicket2.getId());
			req.setAttribute("jformOrderTicket2Page", jformOrderTicket2);
		}
		return new ModelAndView("com/gbiac/fams/business/safecheck/spotcheck/famsSafeSpotCheckDetail/update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","jformOrderTicket2Controller");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(JformOrderTicket2Entity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jformOrderTicket2, request.getParameterMap());
		List<JformOrderTicket2Entity> jformOrderTicket2s = this.famsSafeSpotCheckService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"抽查详情");
		modelMap.put(NormalExcelConstants.CLASS,JformOrderTicket2Entity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("抽查详情列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,jformOrderTicket2s);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(JformOrderTicket2Entity jformOrderTicket2,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"抽查详情");
    	modelMap.put(NormalExcelConstants.CLASS,JformOrderTicket2Entity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("抽查详情列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<JformOrderTicket2Entity> listJformOrderTicket2Entitys = ExcelImportUtil.importExcel(file.getInputStream(),JformOrderTicket2Entity.class,params);
				for (JformOrderTicket2Entity jformOrderTicket2 : listJformOrderTicket2Entitys) {
					famsSafeSpotCheckService.save(jformOrderTicket2);
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
	
	/**
	 * 行编辑保存
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(JformOrderMain2Page page,HttpServletRequest req){
		String message = "操作成功！";
		List<JformOrderTicket2Entity> lists=page.getJformOrderTicket2List();
		AjaxJson j = new AjaxJson();
		String mainId = req.getParameter("mainId");
		if(CollectionUtils.isNotEmpty(lists)){
			for(JformOrderTicket2Entity temp:lists){
				if (StringUtil.isNotEmpty(temp.getId())) {
					JformOrderTicket2Entity t =this.systemService.get(JformOrderTicket2Entity.class, temp.getId());
					try {
						MyBeanUtils.copyBeanNotNull2Bean(temp, t);
						systemService.saveOrUpdate(t);
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						//temp.setDelFlag(0);若有则需要加
						temp.setFckId(mainId);
						systemService.save(temp);
						systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="抽查详情列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<JformOrderTicket2Entity>> list() {
		List<JformOrderTicket2Entity> listJformOrderTicket2s=famsSafeSpotCheckService.getList(JformOrderTicket2Entity.class);
		return Result.success(listJformOrderTicket2s);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取抽查详情信息",notes="根据ID获取抽查详情信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		JformOrderTicket2Entity task = famsSafeSpotCheckService.get(JformOrderTicket2Entity.class, id);
		if (task == null) {
			return Result.error("根据ID获取抽查详情信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建抽查详情")
	public ResponseMessage<?> create(@ApiParam(name="抽查详情对象")@RequestBody JformOrderTicket2Entity jformOrderTicket2, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<JformOrderTicket2Entity>> failures = validator.validate(jformOrderTicket2);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			famsSafeSpotCheckService.save(jformOrderTicket2);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("抽查详情信息保存失败");
		}
		return Result.success(jformOrderTicket2);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新抽查详情",notes="更新抽查详情")
	public ResponseMessage<?> update(@ApiParam(name="抽查详情对象")@RequestBody JformOrderTicket2Entity jformOrderTicket2) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<JformOrderTicket2Entity>> failures = validator.validate(jformOrderTicket2);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			famsSafeSpotCheckService.saveOrUpdate(jformOrderTicket2);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新抽查详情信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新抽查详情信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除抽查详情")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" , id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			famsSafeSpotCheckService.deleteEntityById(JformOrderTicket2Entity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("抽查详情删除失败");
		}

		return Result.success();
	}
}
