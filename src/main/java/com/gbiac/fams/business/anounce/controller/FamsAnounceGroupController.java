package com.gbiac.fams.business.anounce.controller;



import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.gbiac.fams.business.anounce.service.FamsAnounceGroupServiceI;
import com.gbiac.fams.business.anounce.vo.GroupVO;
import com.gbiac.fams.common.Util.Util;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;

/**   
 * @Title: Controller  
 * @Description: 分组维护模块
 * @author Mchen
 * @date 2019-01-23 14:07:15
 * @version V1.0   
 *
 */
@Api(value="FamsAnounceGroup",description="分组维护主表",tags="famsAnounceGroupController")
@Controller
@RequestMapping("/famsAnounceGroupController")
public class FamsAnounceGroupController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAnounceGroupController.class);

	@Autowired
	@Qualifier("famsAnounceGroupService")
	private FamsAnounceGroupServiceI famsAnounceGroupService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 分组维护主表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/anounce/famsAnounceGroupList");
	}
	/**
	 * 获取所有分组信息
	 * @return
	 */
	@RequestMapping(params="lists",method= {RequestMethod.GET})
	@ResponseBody
	public ResponseMessage<List<FamsAnounceGroupEntity>> Lists(){
		try {
			List<FamsAnounceGroupEntity> list = systemService.getList(FamsAnounceGroupEntity.class);
			return Result.success(list);
		} catch (Exception e) {
			return Result.error("获取数据错误");
		}
		
	}
	
	/**
	 * 判断分组名称是否重复
	 * 
	 * @return
	 */
	@RequestMapping(params="isRepeatName",method= {RequestMethod.GET})
	@ResponseBody
	public ResponseMessage<?> isRepeatName(@RequestParam("name") String name){
	
		try {
			if (famsAnounceGroupService.isRepeatName(name, ResourceUtil.getSessionUser().getUserName())) {
				return Result.error("用户名称重复");
			}
			ResponseMessage responseMessage = Result.success();
			responseMessage.setMessage("验证通过");
			return responseMessage;
		} catch (Exception e) {
			return Result.error("获取数据错误");
		}
		
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
	public void datagrid(FamsAnounceGroupEntity famsAnounceGroup,@RequestParam(value="user") String user,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String sort = dataGrid.getSort();
		if ("count".equals(dataGrid.getSort())||"user".equals(dataGrid.getSort())) {
			dataGrid.setSort(null);
		}
		CriteriaQuery cq = new CriteriaQuery(FamsAnounceGroupEntity.class, dataGrid);
		List<GroupVO> list = null;
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAnounceGroup, request.getParameterMap());
		try{
			cq.eq("createBy", ResourceUtil.getSessionUser().getUserName());
			cq.add();
			this.famsAnounceGroupService.getDataGridReturn(cq, true);
			List<FamsAnounceGroupEntity> res = dataGrid.getResults();
			list = famsAnounceGroupService.selectGroupVOBy(res);
			//根据分组成员信息过滤
			if (!StringUtils.isBlank(user)) {
				int i = 0;
				while(i<list.size()) {
					GroupVO vo = list.get(i);
					if (!vo.getUser().contains(user)) {
						list.remove(i);
						i--;
					}
					i++;
				}
			}
			//根据数量排序
			if ("count".equals(sort)||"user".equals(sort)) {
				if ("asc".equals(dataGrid.getOrder())) {
					Collections.sort(list, new Comparator<GroupVO>() {

						@Override
						public int compare(GroupVO o1, GroupVO o2) {
							return o1.getCount()-o2.getCount();
						}
					});
				}else {
					Collections.sort(list, new Comparator<GroupVO>() {
						@Override
						public int compare(GroupVO o1, GroupVO o2) {
							return o2.getCount()-o1.getCount();
						}
					});
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		//设置对象
		dataGrid.setResults(list);
		//设置total
		dataGrid.setTotal(list.size());
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 根据分组状态来判断对分组的启用与禁用
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAnounceGroupEntity famsAnounceGroup, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAnounceGroup = systemService.getEntity(FamsAnounceGroupEntity.class, famsAnounceGroup.getId());
		message = "操作成功";
		try{
			famsAnounceGroupService.delete(famsAnounceGroup);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "请求操作失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除分组维护主表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "分组维护删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAnounceGroupEntity famsAnounceGroup = systemService.getEntity(FamsAnounceGroupEntity.class, 
				id
				);
				famsAnounceGroupService.delete(famsAnounceGroup);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "分组维护删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加分组维护主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd( @RequestParam(value="name",defaultValue="") String name,@RequestParam(value="userIds",defaultValue="") String userIds, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "分组维护添加成功";
		try{
			Integer code = famsAnounceGroupService.saveOrUpdate("", name, userIds);
			if (code == 1) {
				message = "传入参数非法";
			}
			if(code == 2) {
				message = "名称重复";
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "分组维护主表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新分组维护主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(@RequestParam(value="id",defaultValue="") String id,@RequestParam(value="name",defaultValue="") String name,@RequestParam(value="userIds",defaultValue="") String userIds, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "分组维护更新成功";
		try {
			Integer code = famsAnounceGroupService.saveOrUpdate(id,name, userIds);
			if (code == 1) {
				message = "传入参数为空";
			}
			if(code == 2) {
				message = "名称重复";
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "分组维护更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 分组维护主表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAnounceGroupEntity famsAnounceGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAnounceGroup.getId())) {
			famsAnounceGroup = famsAnounceGroupService.getEntity(FamsAnounceGroupEntity.class, famsAnounceGroup.getId());
			req.setAttribute("famsAnounceGroup", famsAnounceGroup);
		}
		return new ModelAndView("com/gbiac/fams/business/anounce/famsAnounceGroup-add");
	}
	/**
	 * 分组维护主表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ModelAndView  modelAndView,FamsAnounceGroupEntity famsAnounceGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAnounceGroup.getId())) {
			try {
				GroupVO vo  = famsAnounceGroupService.selectById(famsAnounceGroup.getId());
				//判断是否是详情页面
				if (req.getParameter("load")!=null) {
					req.setAttribute("load", 1);
				}
				if (vo!=null) {
					req.setAttribute("id", famsAnounceGroup.getId());
					req.setAttribute("userIds",vo.getUserIds());
					req.setAttribute("names", vo.getUser());
					req.setAttribute("name", vo.getName());
					req.setAttribute("count", vo.getCount());
				}
			} catch (Exception e) {
				logger.error("分组维护跳转编辑页面错误"+e);
			}
			
		}
		return new ModelAndView("com/gbiac/fams/business/anounce/famsAnounceGroup-update");
	}
	
	/**暂时不用的方法
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAnounceGroupController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**暂时不用的方法
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAnounceGroupEntity famsAnounceGroup,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAnounceGroupEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAnounceGroup, request.getParameterMap());
		List<FamsAnounceGroupEntity> famsAnounceGroups = this.famsAnounceGroupService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"分组维护主表");
		modelMap.put(NormalExcelConstants.CLASS,FamsAnounceGroupEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("分组维护主表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAnounceGroups);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**暂时不用的方法
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAnounceGroupEntity famsAnounceGroup,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"分组维护主表");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAnounceGroupEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("分组维护主表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 暂时不用的方法
	 * @param request
	 * @param response
	 * @return
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
				List<FamsAnounceGroupEntity> listFamsAnounceGroupEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAnounceGroupEntity.class,params);
				for (FamsAnounceGroupEntity famsAnounceGroup : listFamsAnounceGroupEntitys) {
					famsAnounceGroupService.save(famsAnounceGroup);
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
	 * 暂时不用的方法
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	//@ApiOperation(value="分组维护主表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<FamsAnounceGroupEntity>> list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(FamsAnounceGroupEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<FamsAnounceGroupEntity> listFamsAnounceGroups = this.famsAnounceGroupService.getListByCriteriaQuery(query,true);
		return Result.success(listFamsAnounceGroups);
	}
	/**
	 * 暂时不用的方法
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	//@ApiOperation(value="根据ID获取分组维护主表信息",notes="根据ID获取分组维护主表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		FamsAnounceGroupEntity task = famsAnounceGroupService.get(FamsAnounceGroupEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取分组维护主表信息为空");
		}
		return Result.success(task);
	}
	/**
	 * 暂时不用的方法
	 * @param famsAnounceGroup
	 * @param uriBuilder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	//@ApiOperation(value="创建分组维护主表")
	public ResponseMessage<?> create(@ApiParam(name="分组维护主表对象")@RequestBody FamsAnounceGroupEntity famsAnounceGroup, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FamsAnounceGroupEntity>> failures = validator.validate(famsAnounceGroup);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			famsAnounceGroupService.save(famsAnounceGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("分组维护主表信息保存失败");
		}
		return Result.success(famsAnounceGroup);
	}
	/**
	 * 暂时不用的方法
	 * @param famsAnounceGroup
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	//@ApiOperation(value="更新分组维护主表",notes="更新分组维护主表")
	public ResponseMessage<?> update(@ApiParam(name="分组维护主表对象")@RequestBody FamsAnounceGroupEntity famsAnounceGroup) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FamsAnounceGroupEntity>> failures = validator.validate(famsAnounceGroup);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			famsAnounceGroupService.saveOrUpdate(famsAnounceGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新分组维护主表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新分组维护主表信息成功");
	}
	/**
	 * 暂时不用的方法
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	//@ApiOperation(value="删除分组维护主表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" , id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			famsAnounceGroupService.deleteEntityById(FamsAnounceGroupEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("分组维护主表删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 跳转到添加用户页面
	 * 
	 */
	@RequestMapping(params="goAddUsers")
	public ModelAndView goAddUsers(ModelAndView modelAndView,@RequestParam("userIds") String userIds,@RequestParam("realNames") String realNames) {
		modelAndView.setViewName("com/gbiac/fams/business/anounce/userInfo");
		modelAndView.addObject("orgId", Util.getUserDepId());
		modelAndView.addObject("userIds",userIds);
		modelAndView.addObject("realNames",realNames);
		return modelAndView;
	}
}
