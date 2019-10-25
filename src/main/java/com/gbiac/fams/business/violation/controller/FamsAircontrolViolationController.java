package com.gbiac.fams.business.violation.controller;
import com.gbiac.fams.business.violation.entity.FamsAircontrolViolationEntity;
import com.gbiac.fams.business.violation.service.FamsAircontrolViolationServiceI;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
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

/**   
 * @Title: Controller  
 * @Description: 违章告知单
 * @author 邓正辉
 * @date 2019-01-15 11:25:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/famsAircontrolViolationController")
public class FamsAircontrolViolationController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAircontrolViolationController.class);

	@Autowired
	private FamsAircontrolViolationServiceI famsAircontrolViolationService;
	@Autowired
	private SystemService systemService;
	private String[] roleCodes_duty={"dutyComPer"};
	private String[] roleCodes_JP={"jiping"};
	private String[] roleCodes_ZR={"zhunru"};



	/**
	 * 违章告知单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/gbiac/fams/business/violation/famsAircontrolViolationList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FamsAircontrolViolationEntity famsAircontrolViolation,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String message="";
		CriteriaQuery cq = new CriteriaQuery(FamsAircontrolViolationEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAircontrolViolation, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			message="查询失败";
			throw new BusinessException(message);
		}

		cq.add();
		this.famsAircontrolViolationService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除违章告知单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		famsAircontrolViolation = systemService.getEntity(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());
		message = "违章告知单删除成功";
		try{
			famsAircontrolViolationService.delete(famsAircontrolViolation);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "违章告知单删除失败";
			throw new BusinessException(message);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除违章告知单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "违章告知单删除成功";
		try{
			for(String id:ids.split(",")){
				FamsAircontrolViolationEntity famsAircontrolViolation = systemService.getEntity(FamsAircontrolViolationEntity.class, 
				id
				);
				famsAircontrolViolationService.delete(famsAircontrolViolation);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "违章告知单删除失败";
			throw new BusinessException(message);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加违章告知单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "违章告知单添加成功";
		String pcture=request.getParameter("picture");

		try{
            if( ! Util.getUserDepName().contains("机坪")){
                    message = "添加失败，没有权限";
                    j.setMsg(message);
                    return j;

            }
			famsAircontrolViolationService.save(famsAircontrolViolation);
            //更新图片
			famsCommonService.updateMBSofFileByStr(pcture,"violation",famsAircontrolViolation.getId(),"1");
			//插入动态
			famsCommonService.saveReplyShow("violation",famsAircontrolViolation.getId(),"1","派发了违章告知单");
			//pc通知责任单位下角色编码为dutyComPer为的值班账号
			String title= Util.getUserDepName()+"-"+Util.getUserName()+"给您单位派发了违章告知单";
			messageService.pushMessageByDepart(MessageConstant.VIOLATION_EN,famsAircontrolViolation.getId(),title,famsAircontrolViolation.getViolationName(),Util.getUserName(), famsAircontrolViolation.getDutyId(),roleCodes_duty,null,null);
			//pc通知准入管理部角色下所有的用户
			title=Util.getUserDepName()+"-"+Util.getUserName()+"派发了违章告知单，请您部门及时处理";
			messageService.pushMessage(MessageConstant.VIOLATION_EN,famsAircontrolViolation.getId(),title,famsAircontrolViolation.getViolationName(),Util.getUserName(), roleCodes_ZR,null, null);

			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			message = "违章告知单添加失败";
			logger.error("违章告知单:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新违章告知单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "违章告知单更新成功";

		FamsAircontrolViolationEntity t = famsAircontrolViolationService.get(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolViolation, t);
			famsAircontrolViolationService.saveOrUpdate(t);


			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "违章告知单更新失败";

			throw new BusinessException(message);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 处理违章告知单
	 *
	 * @return
	 */
	@RequestMapping(params = "doDeal")
	@ResponseBody
	public AjaxJson doDeal(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "准入管理部处理违章告知单成功";
		String pcture=request.getParameter("picture");
		String panishRule=request.getParameter("panishRule");

		FamsAircontrolViolationEntity t = famsAircontrolViolationService.get(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());
		try {
            if( ! Util.getUserDepName().contains("准入")){
                message = "处理失败，没有权限";
                j.setMsg(message);
                return j;

            }
            if( !"1".equals(t.getStatus())){
                j.setMsg("处理失败，请检查告知单状态");
                return j;
            }
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolViolation, t);
			t.setStatus("2");
			t.setDealName(Util.getUserName());
			t.setDealDate(new Date());
			t.setRebackDes(null);
			famsAircontrolViolationService.saveOrUpdate(t);
			//更新图片
			famsCommonService.updateMBSofFileByStr(pcture,"violation",famsAircontrolViolation.getId(),"1");
			//保存动态
			famsCommonService.saveReplyShow("violation",famsAircontrolViolation.getId(),"1","处理了违章告知单");
			//pc通知责任单位下角色编码为dutyComPer为的值班账号
			String title= Util.getUserDepName()+"-"+Util.getUserName()+"处理了您单位的违章告知单";
			messageService.pushMessageByDepart(MessageConstant.VIOLATION_EN,t.getId(),title,t.getViolationName(),Util.getUserName(), t.getDutyId(),roleCodes_duty,null,null);

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "准入管理部处理违章告知单失败";
			logger.error("违章告知单:"+message,e);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 撤销违章告知单
	 *
	 * @return
	 */
	@RequestMapping(params = "doReback")
	@ResponseBody
	public AjaxJson doReback(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String pcture=request.getParameter("picture");
		message = "撤销成功";
		String dutyTitle;
		String flyTitle;

		FamsAircontrolViolationEntity t = famsAircontrolViolationService.get(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());
		try {
			if( "4".equals(t.getStatus())||"3".equals(t.getStatus())){
				j.setMsg("撤销失败，请检查违章告知单状态");
				return j;
			}
			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolViolation, t);
			//机坪管理部可撤销已派发的单
			if(Util.getUserDepName().contains("机坪")){
                if(! "1".equals(t.getStatus())){
                    message = "撤销失败，违章告知单已被其他单位处理或接收";
                    j.setMsg(message);
                    return j;
                }
				t.setStatus("4");
				dutyTitle=Util.getUserDepName()+"-"+Util.getUserName()+"撤销了您单位的违章告知单";
				flyTitle=Util.getUserDepName()+"-"+Util.getUserName()+"撤销了"+t.getDutyCompany()+"的违章告知单";


			}
			//准入撤回
			else if(Util.getUserDepName().contains("准入")){
//				t.setRebackDes(null);//撤回不保存理由
				message="撤回成功";
				if("1".equals(t.getStatus())){
					message = "撤回失败，准入管理部不能撤销本部门未处理的违章告知单";
					j.setMsg(message);
					return j;
				}

				if("2".equals(t.getStatus())){
					t.setStatus("1");
				}
				dutyTitle=Util.getUserDepName()+"-"+Util.getUserName()+"撤回您单位的违章告知单";
				flyTitle=Util.getUserDepName()+"-"+Util.getUserName()+"撤回了"+t.getDutyCompany()+"的违章告知单";

			}
			else{
				message = "撤销失败，没有权限";
				j.setMsg(message);
				return j;
			}
			t.setAccessPanishResult(null);
			t.setPanishRule(null);
			t.setDealDate(null);
			t.setDealName(null);
			famsAircontrolViolationService.saveOrUpdate(t);

			//famsCommonService.updateMBSofFileByStr(pcture,"violation",famsAircontrolViolation.getId(),"1");

			famsCommonService.saveReplyShow("violation",famsAircontrolViolation.getId(),"1",message+":"+famsAircontrolViolation.getRebackDes());
			//pc通知责任单位下角色编码为dutyComPer为的值班账号
			messageService.pushMessageByDepart(MessageConstant.VIOLATION_EN,t.getId(),dutyTitle,t.getViolationName(),Util.getUserName(), t.getDutyId(),roleCodes_duty,null,null);

			//机坪通知
			if(Util.getUserDepName().contains("机坪")){
				//pc通知准入管理部角色下所有的用户
				messageService.pushMessage(MessageConstant.VIOLATION_EN,t.getId(),flyTitle,t.getViolationName(),Util.getUserName(), roleCodes_ZR,null, null);
			}
			//准入通知
			else if(Util.getUserDepName().contains("准入")){
				//pc通知机坪监管部角色下所有的用户
				messageService.pushMessage(MessageConstant.VIOLATION_EN,t.getId(),flyTitle,t.getViolationName(),Util.getUserName(), roleCodes_JP,null, null);

			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "撤销违章告知单失败";
			logger.error("违章告知单:"+message,e);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 接收违章告知单
	 *
	 * @return
	 */
	@RequestMapping(params = "doReceive")
	@ResponseBody
	public AjaxJson doReceive(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "接收告知单成功";

		FamsAircontrolViolationEntity t = famsAircontrolViolationService.get(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());
		try {
            if(! Util.getUserDepId().equals(t.getDutyId())){
                j.setMsg("接收失败,不能接收其他单位告知单");
                return j;
            }

            if( !"2".equals(t.getStatus())){
                j.setMsg("接收失败，请检查告知单状态");
                return j;
            }

			MyBeanUtils.copyBeanNotNull2Bean(famsAircontrolViolation, t);
			t.setStatus("3");
			famsAircontrolViolationService.saveOrUpdate(t);

			famsCommonService.saveReplyShow("violation",famsAircontrolViolation.getId(),"1","接收了违章告知单");
			//pc通知准入管理部角色下所有的用户
			String title=Util.getUserDepName()+"接收了您部门处理的违章告知单";
			messageService.pushMessage(MessageConstant.VIOLATION_EN,t.getId(),title,t.getViolationName(),Util.getUserName(), roleCodes_ZR,null, null);

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			message = "接收违章告知单失败";
			logger.error("违章告知单:"+message,e);
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 违章告知单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAircontrolViolation.getId())) {
			famsAircontrolViolation = famsAircontrolViolationService.getEntity(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());
			req.setAttribute("famsAircontrolViolation", famsAircontrolViolation);
		}
//		systemService.getFamsNumberByTSTypegroup("violation",req);
		return new ModelAndView("com/gbiac/fams/business/violation/famsAircontrolViolation-add");
	}
	/**
	 * 违章告知单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAircontrolViolation.getId())) {
			famsAircontrolViolation = famsAircontrolViolationService.getEntity(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());
			req.setAttribute("famsAircontrolViolation", famsAircontrolViolation);
		}
		return new ModelAndView("com/gbiac/fams/business/violation/famsAircontrolViolation-update");
	}

    /**
     * 违章告知单处理页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goDeal")
    public ModelAndView goDeal(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest req) {
        String message = null;

        try {
            if (StringUtil.isNotEmpty(famsAircontrolViolation.getId())) {
                famsAircontrolViolation = famsAircontrolViolationService.getEntity(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());
                req.setAttribute("famsAircontrolViolation", famsAircontrolViolation);

                //历史动态暂时不用
                FamsCommonReplyEntity entity=new FamsCommonReplyEntity();
                entity.setModule("violation");
                entity.setBusinessId(famsAircontrolViolation.getId());
                entity.setState("1");
                List<FamsCommonReplyEntity> list=FamsCommonReplyServiceI.queryListByEntity(entity);

                req.setAttribute("reply",list);
                //图片
                Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"violation",famsAircontrolViolation.getId(),"1",null);
                Util.filesSetReq((List<FamsCommonFileEntity>)pacList,req);


            }
        }catch (Exception e){
			message="违章告知单处理页面跳转失败";
			logger.error("违章告知单:"+message,e);

			throw new BusinessException(message);
        }


        return new ModelAndView("com/gbiac/fams/business/violation/famsAircontrolViolation-deal");
    }

	/**
	 * 违章告知单撤销页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goReback")
	public ModelAndView goReback(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(famsAircontrolViolation.getId())) {
			if (StringUtil.isNotEmpty(famsAircontrolViolation.getId())) {
				famsAircontrolViolation = famsAircontrolViolationService.getEntity(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());
				req.setAttribute("famsAircontrolViolation", famsAircontrolViolation);

				//历史动态暂时不用
				FamsCommonReplyEntity entity=new FamsCommonReplyEntity();
				entity.setModule("violation");
				entity.setBusinessId(famsAircontrolViolation.getId());
				entity.setState("1");
				List<FamsCommonReplyEntity> list=FamsCommonReplyServiceI.queryListByEntity(entity);

				req.setAttribute("reply",list);
				//图片
				Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"violation",famsAircontrolViolation.getId(),"1",null);
				Util.filesSetReq((List<FamsCommonFileEntity>)pacList,req);


			}
		}

		if(Util.getUserDepName().contains("准入")){
			return new ModelAndView("com/gbiac/fams/business/violation/famsAircontrolViolation-recall");

		}

		return new ModelAndView("com/gbiac/fams/business/violation/famsAircontrolViolation-reback");
	}

	/**
	 * 违章告知单接收页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goReceive")
	public ModelAndView goReceive(FamsAircontrolViolationEntity famsAircontrolViolation, HttpServletRequest req) {
		String message = null;

		try {
			if (StringUtil.isNotEmpty(famsAircontrolViolation.getId())) {
				famsAircontrolViolation = famsAircontrolViolationService.getEntity(FamsAircontrolViolationEntity.class, famsAircontrolViolation.getId());
				req.setAttribute("famsAircontrolViolation", famsAircontrolViolation);

				//历史动态暂时不用
				FamsCommonReplyEntity entity=new FamsCommonReplyEntity();
				entity.setModule("violation");
				entity.setBusinessId(famsAircontrolViolation.getId());
				entity.setState("1");
				List<FamsCommonReplyEntity> list=FamsCommonReplyServiceI.queryListByEntity(entity);

				req.setAttribute("reply",list);
				//图片
				Object pacList=famsCommonService.getCommonObject(FamsCommonFileEntity.class,"violation",famsAircontrolViolation.getId(),"1",null);
                Util.filesSetReq((List<FamsCommonFileEntity>)pacList,req);



            }
		}catch (Exception e){
			message="违章告知单接收页面跳转失败";
			logger.error("违章告知单:"+message,e);
			throw new BusinessException(message);
		}


		return new ModelAndView("com/gbiac/fams/business/violation/famsAircontrolViolation-receive");
	}


    /**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","famsAircontrolViolationController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FamsAircontrolViolationEntity famsAircontrolViolation,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FamsAircontrolViolationEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, famsAircontrolViolation, request.getParameterMap());
		List<FamsAircontrolViolationEntity> famsAircontrolViolations = this.famsAircontrolViolationService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"违章告知单");
		modelMap.put(NormalExcelConstants.CLASS,FamsAircontrolViolationEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("违章告知单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,famsAircontrolViolations);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FamsAircontrolViolationEntity famsAircontrolViolation,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"违章告知单");
    	modelMap.put(NormalExcelConstants.CLASS,FamsAircontrolViolationEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("违章告知单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FamsAircontrolViolationEntity> listFamsAircontrolViolationEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FamsAircontrolViolationEntity.class,params);
				for (FamsAircontrolViolationEntity famsAircontrolViolation : listFamsAircontrolViolationEntitys) {
					famsAircontrolViolationService.save(famsAircontrolViolation);
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
