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
import org.hibernate.criterion.Restrictions;
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
import org.jeecgframework.web.superquery.util.SuperQueryUtil;
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
import com.gbiac.fams.business.safecheck.spotcheck.entity.FamsSafeSpotCheckDetailEntity;
import com.gbiac.fams.business.safecheck.spotcheck.entity.FamsSafeSpotCheckEntity;
import com.gbiac.fams.business.safecheck.spotcheck.page.FamsSafeSpotCheckPage;
import com.gbiac.fams.business.safecheck.spotcheck.service.FamsSafeSpotCheckServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller
 * @Description: 航班保障作业抽查
 * @author onlineGenerator
 * @date 2018-03-27 16:21:58
 * @version V1.0   
 *
 */
//@Api(value="famsSafeSpotCheck",description="抽查记录",tags="famsSafeSpotCheckController")
@Controller
@RequestMapping("/famsSafeSpotCheckController")
public class FamsSafeSpotCheckController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsSafeSpotCheckController.class);
	
	@Autowired
	private FamsSafeSpotCheckServiceI famsSafeSpotCheckService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	/**
	 *  航班保障作业抽查列表 首页跳转
	 * @return
	 */
	@RequestMapping(params = "index")
	public ModelAndView index(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/safecheck/spotcheck/famsSafeSpotCheckIndex");
	}
	/**
	 * 航班保障作业抽查记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/safecheck/spotcheck/list");
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
	public void datagrid(FamsSafeSpotCheckEntity famsSafeSpotCheck,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FamsSafeSpotCheckEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsSafeSpotCheck);
		try{
			//自定义追加查询条件
			String sql = SuperQueryUtil.getComplxSuperQuerySQL(request);
			if(oConvertUtils.isNotEmpty(sql)) {
				cq.add(Restrictions.sqlRestriction(" id in ("+sql+")"));
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.famsSafeSpotCheckService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除抽查记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsSafeSpotCheckEntity famsSafeSpotCheck, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		famsSafeSpotCheck = systemService.getEntity(FamsSafeSpotCheckEntity.class, famsSafeSpotCheck.getId());
		String message = "抽查记录删除成功";
		try{
			if(famsSafeSpotCheck!=null){
				famsSafeSpotCheckService.delMain(famsSafeSpotCheck);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "抽查记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除抽查记录
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "抽查记录删除成功";
		try{
			for(String id:ids.split(",")){
				FamsSafeSpotCheckEntity famsSafeSpotCheck = systemService.getEntity(FamsSafeSpotCheckEntity.class,
				id
				);
				if(famsSafeSpotCheck!=null){
					famsSafeSpotCheckService.delMain(famsSafeSpotCheck);
					systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "抽查记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加抽查记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsSafeSpotCheckEntity famsSafeSpotCheck,FamsSafeSpotCheckPage famsSafeSpotCheckPage, HttpServletRequest request) {
		List<FamsSafeSpotCheckDetailEntity> famsSafeSpotCheckList =  famsSafeSpotCheckPage.getFamsSafeSpotCheckDetailList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			famsSafeSpotCheckService.addMain(famsSafeSpotCheck, famsSafeSpotCheckList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "抽查记录添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新抽查记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsSafeSpotCheckEntity famsSafeSpotCheck,FamsSafeSpotCheckPage famsSafeSpotCheckPage, HttpServletRequest request) {
		List<FamsSafeSpotCheckDetailEntity> jformOrderTicket2List =  famsSafeSpotCheckPage.getFamsSafeSpotCheckDetailList();
		
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			famsSafeSpotCheckService.updateMain(famsSafeSpotCheck, jformOrderTicket2List);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新抽查记录失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HttpServletRequest req) {
		//跳转主页面
		return new ModelAndView("com/gbiac/fams/business/safecheck/spotcheck/addOrUpdate");
	}
	
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HttpServletRequest req) {
		//跳转主页面
		String id = req.getParameter("id");
		req.setAttribute("mainId",id);
		req.setAttribute("load", req.getParameter("load"));
		return new ModelAndView("com/gbiac/fams/business/safecheck/spotcheck/addOrUpdate");
	}
	
	/**
	 * 抽查记录新增编辑字段页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "mainPage")
	public ModelAndView mainPage(FamsSafeSpotCheckEntity famsSafeSpotCheck, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsSafeSpotCheck.getId())) {
			famsSafeSpotCheck = famsSafeSpotCheckService.getEntity(FamsSafeSpotCheckEntity.class, famsSafeSpotCheck.getId());
			req.setAttribute("famsSafeSpotCheckPage", famsSafeSpotCheck);
		}
		return new ModelAndView("com/gbiac/fams/business/safecheck/spotcheck/jformOrderMain2");
	}
	
	
	/**
	 * 加载明细列表[订单机票信息]
	 * 
	 * @return
	 */
	@RequestMapping(params = "jformOrderTicket2List")
	public ModelAndView jformOrderTicket2List(FamsSafeSpotCheckEntity famsSafeSpotCheck, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = famsSafeSpotCheck.getId();
		//===================================================================================
		//查询-订单机票信息
	    String hql0 = "from FamsSafeSpotCheckDetailEntity where 1 = 1 AND fCK_ID = ? ";
	    try{
	    	List<FamsSafeSpotCheckDetailEntity> FamsSafeSpotCheckDetailEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("jformOrderTicket2List", FamsSafeSpotCheckDetailEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/gbiac/fams/business/safecheck/spotcheck/jformOrderTicket2");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(FamsSafeSpotCheckEntity famsSafeSpotCheck,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(FamsSafeSpotCheckEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsSafeSpotCheck);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<FamsSafeSpotCheckEntity> list=this.famsSafeSpotCheckService.getListByCriteriaQuery(cq, false);
    	List<FamsSafeSpotCheckPage> pageList=new ArrayList<FamsSafeSpotCheckPage>();
        if(list!=null&&list.size()>0){
        	for(FamsSafeSpotCheckEntity entity:list){
        		try{
        		FamsSafeSpotCheckPage page=new FamsSafeSpotCheckPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from FamsSafeSpotCheckDetailEntity where 1 = 1 AND fCK_ID = ? ";
        	        List<FamsSafeSpotCheckDetailEntity> FamsSafeSpotCheckDetailEntityList = systemService.findHql(hql0,id0);
            		page.setFamsSafeSpotCheckDetailList(FamsSafeSpotCheckDetailEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"抽查记录");
        map.put(NormalExcelConstants.CLASS,FamsSafeSpotCheckPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("抽查记录列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
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
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<FamsSafeSpotCheckPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), FamsSafeSpotCheckPage.class, params);
				FamsSafeSpotCheckEntity entity1=null;
				for (FamsSafeSpotCheckPage page : list) {
					entity1=new FamsSafeSpotCheckEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
					famsSafeSpotCheckService.addMain(entity1, page.getFamsSafeSpotCheckDetailList());
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
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"抽查记录");
		map.put(NormalExcelConstants.CLASS,FamsSafeSpotCheckPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("抽查记录列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "famsSafeSpotCheckController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 行编辑保存操作
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(FamsSafeSpotCheckPage page,HttpServletRequest req){
		String message = "操作成功！";
		List<FamsSafeSpotCheckEntity> lists=page.getFamsSafeSpotCheckList();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(lists)){
			for(FamsSafeSpotCheckEntity temp:lists){
				if (StringUtil.isNotEmpty(temp.getId())) {
					FamsSafeSpotCheckEntity t =this.systemService.get(FamsSafeSpotCheckEntity.class, temp.getId());
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
	@ApiOperation(value="抽查记录列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<FamsSafeSpotCheckPage>> list() {
		List<FamsSafeSpotCheckEntity> list= famsSafeSpotCheckService.getList(FamsSafeSpotCheckEntity.class);
    	List<FamsSafeSpotCheckPage> pageList=new ArrayList<FamsSafeSpotCheckPage>();
        if(list!=null&&list.size()>0){
        	for(FamsSafeSpotCheckEntity entity:list){
        		try{
        			FamsSafeSpotCheckPage page=new FamsSafeSpotCheckPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object id0 = entity.getId();
					Object id1 = entity.getId();
				     String hql0 = "from FamsSafeSpotCheckDetailEntity where 1 = 1 AND fCK_ID = ? ";
	    			List<FamsSafeSpotCheckDetailEntity> jformOrderTicket2OldList = this.famsSafeSpotCheckService.findHql(hql0,id0);
            		page.setFamsSafeSpotCheckDetailList(jformOrderTicket2OldList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
		return Result.success(pageList);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取抽查记录信息",notes="根据ID获取抽查记录信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		FamsSafeSpotCheckEntity task = famsSafeSpotCheckService.get(FamsSafeSpotCheckEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取抽查记录信息为空");
		}
		FamsSafeSpotCheckPage page = new FamsSafeSpotCheckPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object id0 = task.getId();
				Object id1 = task.getId();
		    String hql0 = "from FamsSafeSpotCheckDetailEntity where 1 = 1 AND fCK_ID = ? ";
			List<FamsSafeSpotCheckDetailEntity> jformOrderTicket2OldList = this.famsSafeSpotCheckService.findHql(hql0,id0);
    		page.setFamsSafeSpotCheckDetailList(jformOrderTicket2OldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建抽查记录")
	public ResponseMessage<?> create(@ApiParam(name="抽查记录对象")@RequestBody FamsSafeSpotCheckPage famsSafeSpotCheckPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FamsSafeSpotCheckPage>> failures = validator.validate(famsSafeSpotCheckPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<FamsSafeSpotCheckDetailEntity> jformOrderTicket2List =  famsSafeSpotCheckPage.getFamsSafeSpotCheckDetailList();
		
		FamsSafeSpotCheckEntity famsSafeSpotCheck = new FamsSafeSpotCheckEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(famsSafeSpotCheckPage,famsSafeSpotCheck);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存抽查记录失败");
        }
		famsSafeSpotCheckService.addMain(famsSafeSpotCheck, jformOrderTicket2List);

		return Result.success(famsSafeSpotCheck);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新抽查记录",notes="更新抽查记录")
	public ResponseMessage<?> update(@RequestBody FamsSafeSpotCheckPage famsSafeSpotCheckPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FamsSafeSpotCheckPage>> failures = validator.validate(famsSafeSpotCheckPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<FamsSafeSpotCheckDetailEntity> jformOrderTicket2List =  famsSafeSpotCheckPage.getFamsSafeSpotCheckDetailList();
		
		FamsSafeSpotCheckEntity famsSafeSpotCheck = new FamsSafeSpotCheckEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(famsSafeSpotCheckPage,famsSafeSpotCheck);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("抽查记录更新失败");
        }
		famsSafeSpotCheckService.updateMain(famsSafeSpotCheck, jformOrderTicket2List);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除抽查记录")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" , id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			FamsSafeSpotCheckEntity famsSafeSpotCheck = famsSafeSpotCheckService.get(FamsSafeSpotCheckEntity.class, id);
			famsSafeSpotCheckService.delMain(famsSafeSpotCheck);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("抽查记录删除失败");
		}

		return Result.success();
	}
}
