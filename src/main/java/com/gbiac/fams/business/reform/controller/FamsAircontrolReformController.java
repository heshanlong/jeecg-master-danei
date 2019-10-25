package com.gbiac.fams.business.reform.controller;
import com.alibaba.fastjson.JSONArray;
import com.gbiac.fams.business.reform.entity.FamsAircontrolReformEntity;
import com.gbiac.fams.business.reform.service.FamsAircontrolReformServiceI;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import com.gbiac.fams.common.Entity.MessageType;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.file.service.FamsCommonFileServiceI;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
import com.gbiac.fams.common.reply.service.FamsCommonReplyServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.util.*;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Validator;


/**   
 * @Title: Controller  
 * @Description: 整改单
 * @author 邓正辉
 * @date 2019-01-09 14:49:44
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsAircontrolReformController")
public class FamsAircontrolReformController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAircontrolReformController.class);

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



	/**
	 * 整改单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));
		departList.clear();
		return new ModelAndView("com/gbiac/fams/business/reform/famsAircontrolReformList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FamsAircontrolReformEntity famsAircontrolReform,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String message="";
		CriteriaQuery cq = new CriteriaQuery(FamsAircontrolReformEntity.class, dataGrid);
		//查询条件组装器
        //外部单位数据权限，待确定，后期添加
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAircontrolReform, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			message="查询失败";
			logger.error("整改单:"+message,ExceptionUtil.getExceptionMessage(e));
			throw new BusinessException(message);
		}
		cq.add();
		this.famsAircontrolReformService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	
	/**
	 * 删除整改单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest request) {
		String message;
		AjaxJson j = new AjaxJson();
		famsAircontrolReform = systemService.getEntity(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
		message = "整改单删除成功";
		try{
			famsAircontrolReformService.delete(famsAircontrolReform);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "整改单删除失败";
			throw new BusinessException(message);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除整改单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "整改单删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAircontrolReformEntity famsAircontrolReform = systemService.getEntity(FamsAircontrolReformEntity.class, 
				id
				);
				famsAircontrolReformService.delete(famsAircontrolReform);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "整改单删除失败";
			throw new BusinessException(message);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 飞管部添加整改单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "整改单添加成功";
		String pcture=request.getParameter("picture");
		try{
			if( ! Util.getUserDepName().contains("机坪")){
				message = "添加失败，没有权限";
				j.setMsg(message);
				return j;

			}

//			if( famsAircontrolReform.getDutyCompany().contains("机坪")
//					||
//					famsAircontrolReform.getDutyCompany().contains("准入")){
//				message = "添加失败，单位";
//				j.setMsg(message);
//				return j;
//
//			}


			Object o=famsAircontrolReformService.save(famsAircontrolReform);
			//更新图片
			famsCommonService.updateMBSofFileByStr(pcture,"reform",famsAircontrolReform.getId(),"1");
			//保存动态
			famsCommonService.saveReplyShow("reform",famsAircontrolReform.getId(),"1","派发了整改单");
			//pc通知责任单位下角色编码为dutyComPer为的值班账号
			String title= Util.getUserDepName()+"-"+Util.getUserName()+"给您单位派发了一张整改单";
			messageService.pushMessageByDepart(MessageConstant.REFORM_EN,famsAircontrolReform.getId(),title,famsAircontrolReform.getViolationName(),Util.getUserName(), famsAircontrolReform.getDutyId(),roleCodes_duty,null,null);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			message = "整改单添加失败";
			logger.error("整改单:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新整改单,接口废弃
	 * 

	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest request) {
		String message ;
		AjaxJson j = new AjaxJson();
		message = "整改单更新成功";
        String pcture=request.getParameter("picture");

        FamsAircontrolReformEntity t = famsAircontrolReformService.get(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolReform, t);
			famsAircontrolReformService.saveOrUpdate(t);
			famsCommonService.updateMBSofFileByStr(pcture,"reform",famsAircontrolReform.getId(),"1");
			famsCommonService.saveReplyShow("reform",famsAircontrolReform.getId(),"1","编辑了整改单");

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "整改单更新失败";
			logger.error("整改单:"+message,ExceptionUtil.getExceptionMessage(e));
			throw new BusinessException(message);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 外部单位接收整改单
	 *

	 * @return
	 */
	@RequestMapping(params = "doReceive")
	@ResponseBody
	public AjaxJson doReceive(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest request) {
		String message="整改单接收成功";
		AjaxJson j = new AjaxJson();
		//String pcture=request.getParameter("picture");

		FamsAircontrolReformEntity t = famsAircontrolReformService.get(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
		try {
			if( ! "1".equals(t.getStatus())){
				j.setMsg("接收失败,请检查整改单状态");
				return j;
			}

			if(! Util.getUserDepId().equals(t.getDutyId())){
                j.setMsg("接收失败,不能接收其他单位整改单");
                return j;
            }
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolReform, t);
			t.setStatus("2");
			famsAircontrolReformService.saveOrUpdate(t);
			//famsCommonService.updateMBSofFileByStr(pcture,"reform",famsAircontrolReform.getId(),"1");
			//保存动态
			famsCommonService.saveReplyShow("reform",famsAircontrolReform.getId(),"1","接收了整改单");
			//pc通知机坪监管部角色下所有的用户
			String title=Util.getUserDepName()+"接收了您部门派发的整改单";
			messageService.pushMessage(MessageConstant.REFORM_EN,t.getId(),title,t.getViolationName(),Util.getUserName(), roleCodes_JP,null, null);

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "整改单接收失败";
			logger.error("整改单:"+message,e);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 验收整改单
	 *

	 * @return
	 */
	@RequestMapping(params = "doCheck")
	@ResponseBody
	public AjaxJson doCheck(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "整改单验收操作成功";
		FamsAircontrolReformEntity t = famsAircontrolReformService.get(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
		try {
			if( ! Util.getUserDepName().contains("机坪")){
				message = "验收失败，没有权限";
				j.setMsg(message);
				return j;

			}
			if( ! "3".equals(t.getStatus()) &&
					! "5".equals(t.getStatus())){
				j.setMsg("验收失败，请检查整改单状态");
				return j;
			}
			t.setCheckDate(new Date());
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolReform, t);
			famsAircontrolReformService.saveOrUpdate(t);

			String content="4".equals(famsAircontrolReform.getStatus())?"验收通过--"+(famsAircontrolReform.getCheckfailDec().isEmpty()?"无描述!":famsAircontrolReform.getCheckfailDec()):"验收不通过--"+(famsAircontrolReform.getCheckfailDec().isEmpty()?"无描述!":famsAircontrolReform.getCheckfailDec());
//			message=content+"操作成功";
			//content=content+famsAircontrolReform.getCheckfailDec();
			famsCommonService.saveReplyShow("reform",famsAircontrolReform.getId(),"1",content);
            //pc通知责任单位下角色编码为dutyComPer为的值班账号
            String title= Util.getUserDepName()+"-"+Util.getUserName()+("4".equals(famsAircontrolReform.getStatus())?"验收通过了":"未验收通过")+"您单位的整改单";
            messageService.pushMessageByDepart(MessageConstant.REFORM_EN,t.getId(),title,t.getViolationName(),Util.getUserName(), t.getDutyId(),roleCodes_duty,null,null);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "整改单验收操作失败";
			logger.error("整改单:"+message,e);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 外部单位整改
	 *

	 * @return
	 */
	@RequestMapping(params = "doReform")
	@ResponseBody
	public AjaxJson doReform(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "整改单提交整改成功";

		String pictureAf=request.getParameter("pictureAf");

		FamsAircontrolReformEntity t = famsAircontrolReformService.get(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
		try {
            if(! Util.getUserDepId().equals(t.getDutyId())){
                j.setMsg("提交整改信息失败,不能提交其他单位整改单");
                return j;
            }

			if( ! "2".equals(t.getStatus())
					&& ! "5".equals(t.getStatus())){
				j.setMsg("提交整改信息失败,请检查整改单状态");
				return j;
			}

			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolReform, t);
			//只有外部单位确认完成了整改单才修改状态
//			if( !"3".equals(famsAircontrolReform.getStatus())){
//				famsAircontrolReform.setStatus(t.getStatus());
//			}
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
			famsCommonService.updateMBSofFileByStr(pictureAf,"reform",famsAircontrolReform.getId(),"2");
			//历史动态
			//String content="3".equals(famsAircontrolReform.getStatus())?"完成整改单:":"提交部分整改单:";
			famsCommonService.saveReplyShow("reform",famsAircontrolReform.getId(),"1",content);
			//pc通知机坪监管部角色下所有的用户
			String title=Util.getUserDepName()+"完成了您部门派发的整改单，请您及时验收";
			messageService.pushMessage(MessageConstant.REFORM_EN,t.getId(),title,t.getViolationName(),Util.getUserName(), roleCodes_JP,null, null);


			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "提交整改信息失败";
			logger.error("整改单:"+message,e);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 撤销整改单
	 *

	 * @return
	 */
	@RequestMapping(params = "doReback")
	@ResponseBody
	public AjaxJson doReback(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "整改单撤销成功";
		//String pcture=request.getParameter("picture");

		FamsAircontrolReformEntity t = famsAircontrolReformService.get(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
		try {
			if( ! Util.getUserDepName().contains("机坪")){
				message = "撤销失败，没有权限";
				j.setMsg(message);
				return j;

			}
			//派发 接收 验收不通过可撤销
			if( ! "1".equals(t.getStatus())
					&& ! "2".equals(t.getStatus())
					&& ! "5".equals(t.getStatus())){
				j.setMsg("撤销失败，请检查整改单状态");
				return j;
			}
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolReform, t);
			t.setStatus("6");
			t.setCheckDate(new  Date());
			famsAircontrolReformService.saveOrUpdate(t);
			//famsCommonService.updateMBSofFileByStr(pcture,"reform",famsAircontrolReform.getId(),"1");

			famsCommonService.saveReplyShow("reform",famsAircontrolReform.getId(),"1","撤销了整改单:"+famsAircontrolReform.getRebackDes());
            //pc通知责任单位下角色编码为dutyComPer为的值班账号
            String title= Util.getUserDepName()+"-"+Util.getUserName()+"撤销了派发给您单位的整改单";
            messageService.pushMessageByDepart(MessageConstant.REFORM_EN,t.getId(),title,"撤销原因:"+t.getRebackDes(),Util.getUserName(), t.getDutyId(),roleCodes_duty,null,null);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "整改单撤销失败";
			logger.error("整改单:"+message,e);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 整改单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAircontrolReform.getId())) {
			famsAircontrolReform = famsAircontrolReformService.getEntity(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
			req.setAttribute("famsAircontrolReform", famsAircontrolReform);
		}
		//systemService.getFamsNumberByTSTypegroup("reform",FamsAircontrolReformEntity.class,"decideDate");

		return new ModelAndView("com/gbiac/fams/business/reform/famsAircontrolReform-add");
	}
	/**
	 * 整改单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest req) {
		String message = null;

		try {
			if (StringUtil.isNotEmpty(famsAircontrolReform.getId())) {
				famsAircontrolReform = famsAircontrolReformService.getEntity(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
				req.setAttribute("famsAircontrolReform", famsAircontrolReform);

				//List<FamsCommonFileEntity> pacList=(List<FamsCommonFileEntity>)famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",famsAircontrolReform.getId(),"1",null);
				Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",famsAircontrolReform.getId(),"1",null);
				String picValue="";
				for(FamsCommonFileEntity entity:(List<FamsCommonFileEntity>)pacList){
					picValue=picValue+entity.getFilePath()+";";
				}

				req.setAttribute("picValue",picValue);

			}
		}catch (Exception e){
			message = "整改单编辑跳转失败";
			logger.error("整改单:"+message,e);

			throw new BusinessException(message);
		}

		return new ModelAndView("com/gbiac/fams/business/reform/famsAircontrolReform-update");
	}

	/**
	 * 整改单验收页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goCheck")
	public ModelAndView goCheck(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest req) {
		String message = null;

		try {
			if (StringUtil.isNotEmpty(famsAircontrolReform.getId())) {
				famsAircontrolReform = famsAircontrolReformService.getEntity(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
				req.setAttribute("famsAircontrolReform", famsAircontrolReform);

				//历史动态
				FamsCommonReplyEntity entity=new FamsCommonReplyEntity();
				entity.setModule("reform");
				entity.setBusinessId(famsAircontrolReform.getId());
				entity.setState("1");
				List<FamsCommonReplyEntity> list=FamsCommonReplyServiceI.queryListByEntity(entity);

				req.setAttribute("reply",list);
				//整改前图片
				Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",famsAircontrolReform.getId(),"1",null);
				Util.filesSetReq((List<FamsCommonFileEntity>)pacList,req);

				//整改后图片
				Object pacListAf=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",famsAircontrolReform.getId(),"2",null);
				Util.filesSetReqTwo((List<FamsCommonFileEntity>)pacListAf,req);

				String load=req.getParameter("load");
				req.setAttribute("funload",load);

			}
		}catch (Exception e){
			message = "整改单验收页面跳转";
			logger.error("整改单:"+message,e);
			throw new BusinessException(message);
		}


		return new ModelAndView("com/gbiac/fams/business/reform/famsAircontrolReform-check");
	}

	/**
	 * 整改单外部单位接收页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goReceive")
	public ModelAndView goReceive(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest req) {
		String message = null;

		try {
			if (StringUtil.isNotEmpty(famsAircontrolReform.getId())) {
				famsAircontrolReform = famsAircontrolReformService.getEntity(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
				req.setAttribute("famsAircontrolReform", famsAircontrolReform);

				//List<FamsCommonFileEntity> pacList=(List<FamsCommonFileEntity>)famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",famsAircontrolReform.getId(),"1",null);
				Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",famsAircontrolReform.getId(),"1",null);
				Util.filesSetReq((List<FamsCommonFileEntity>)pacList,req);


			}
		}catch (Exception e){
			message = "整改单接收跳转失败";
			logger.error("整改单:"+message,e);
			throw new BusinessException(message);
		}

		return new ModelAndView("com/gbiac/fams/business/reform/famsAircontrolReform-receive");
	}

	/**
	 * 整改单外部单位整改页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goReform")
	public ModelAndView goReform(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest req) {
		String message = null;

		try {
			if (StringUtil.isNotEmpty(famsAircontrolReform.getId())) {
				famsAircontrolReform = famsAircontrolReformService.getEntity(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
				req.setAttribute("famsAircontrolReform", famsAircontrolReform);
				//历史动态
				FamsCommonReplyEntity entity=new FamsCommonReplyEntity();
				entity.setModule("reform");
				entity.setBusinessId(famsAircontrolReform.getId());
				entity.setState("1");
				List<FamsCommonReplyEntity> list=FamsCommonReplyServiceI.queryListByEntity(entity);

				req.setAttribute("reply",list);
				//整改前图片
				Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",famsAircontrolReform.getId(),"1",null);
				Util.filesSetReq((List<FamsCommonFileEntity>)pacList,req);

				//整改后图片
				Object pacListAf=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",famsAircontrolReform.getId(),"2",null);
				Util.filesSetReqTwo((List<FamsCommonFileEntity>)pacListAf,req);

			}
		}catch (Exception e){
			message="整改单跳转整改页面失败";
			logger.error("整改单:"+message,e);

			throw new BusinessException(message);
		}


		return new ModelAndView("com/gbiac/fams/business/reform/famsAircontrolReform-reform");
	}

	/**
	 * 整改单撤销跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goReback")
	public ModelAndView goReback(FamsAircontrolReformEntity famsAircontrolReform, HttpServletRequest req) {
		String message = null;

		try {
			if (StringUtil.isNotEmpty(famsAircontrolReform.getId())) {
				famsAircontrolReform = famsAircontrolReformService.getEntity(FamsAircontrolReformEntity.class, famsAircontrolReform.getId());
				req.setAttribute("famsAircontrolReform", famsAircontrolReform);
				//历史动态
				FamsCommonReplyEntity entity=new FamsCommonReplyEntity();
				entity.setModule("reform");
				entity.setBusinessId(famsAircontrolReform.getId());
				entity.setState("1");
				List<FamsCommonReplyEntity> list=FamsCommonReplyServiceI.queryListByEntity(entity);

				req.setAttribute("reply",list);
				//整改前图片
				Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",famsAircontrolReform.getId(),"1",null);
				Util.filesSetReq((List<FamsCommonFileEntity>)pacList,req);

				//整改后图片
				Object pacListAf=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"reform",famsAircontrolReform.getId(),"2",null);
				Util.filesSetReqTwo((List<FamsCommonFileEntity>)pacListAf,req);

			}
		}catch (Exception e){
			message = "整改单验收失败";
			throw new BusinessException(message);
		}

		return new ModelAndView("com/gbiac/fams/business/reform/famsAircontrolReform-reback");
	}


	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAircontrolReformController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAircontrolReformEntity famsAircontrolReform,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAircontrolReformEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAircontrolReform, request.getParameterMap());
		List<FamsAircontrolReformEntity> famsAircontrolReforms = this.famsAircontrolReformService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"整改单");
		modelMap.put(NormalExcelConstants.CLASS,FamsAircontrolReformEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("整改单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAircontrolReforms);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAircontrolReformEntity famsAircontrolReform,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"整改单");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAircontrolReformEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("整改单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsAircontrolReformEntity> listFamsAircontrolReformEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAircontrolReformEntity.class,params);
				for (FamsAircontrolReformEntity famsAircontrolReform : listFamsAircontrolReformEntitys) {
					famsAircontrolReformService.save(famsAircontrolReform);
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


	
	
}
