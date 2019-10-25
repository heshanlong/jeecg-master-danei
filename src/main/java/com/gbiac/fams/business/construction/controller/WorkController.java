package com.gbiac.fams.business.construction.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbiac.fams.business.construction.service.FamsWorkServiceI;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveServiceI;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;

/**
 * 该controller主要为BMP流程提供接口
 */
@Controller
@RequestMapping("/famsWorkController")
public class WorkController extends BaseController {
    @Autowired
    private FamsWorkServiceI famsWorkService;
    @Autowired
    private FamsWorkApproveServiceI famsWorkApproveService;

    /**
     * 施工单位完成施工
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "s_finish_fun")
    @ResponseBody
    public AjaxJson s_finish_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        try{
            //施工单位完成施工
            famsWorkService.s_finish_fun(famsWorkApprove.getId());
            //更新施工申请的流程状态
            famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
            new Thread(){
                @Override
                public void run() {
                    try{
                        //推送
                        FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkApprove.getId());
                        //任务完成是，获取拥有施工审批权限的运行控制部用户id集合
                        List<String> userIds=famsCommonService.getCanApproveIdsByRunPartOrgId();
                        //发送系统消息
                        messageService.pushOnlyPCMessage("work",entity.getId(),"您有新的"+entity.getWorkName()+"信息",entity.getWorkDepart()+"完成了该施工。",Util.getPcOrAppUserName(),userIds,null,null,null);
                        //推送消息
                        famsCommonService.sendMessage(Util.getPcOrAppUserId(),"work",entity.getId(),userIds,"您有新的"+entity.getWorkName()+"信息",entity.getWorkDepart()+"完成了该施工。",null);
                    }catch (Exception e){
                    }
                }
            }.run();
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(e.getMessage());
        }
        return ajaxJson;
    }

    /**
     * 运行控制部确认离场
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "y_approve_out_fun")
    @ResponseBody
    public AjaxJson y_approve_out_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        //获取审批状态（通过 不通过）
        String approveState = request.getParameter("approveState");
        //获取审批意见
        String approveStateNote = request.getParameter("approveStateNote");
        try {
        	//判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"y-approve-out")){
                //运控审批出场操作
                famsWorkService.y_approve_out_fun(famsWorkApprove.getId(),approveState,approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            //推送
                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkApprove.getId());
                            //需要修改
                            List<TSBaseUser> users=famsCommonService.getApplyUser(entity.getPushUserId());
                            //推送消息
                            if("Y".equals(approveState)){
                                //发送系统消息
                                messageService.pushOnlyPCMessage("work",entity.getId(),"您提交的"+entity.getWorkName()+"离场申请已审批通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"审批通过了您提交的离场申请："+approveStateNote,Util.getPcOrAppUserName(),users,null,null);
                                famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),users,"您提交的"+entity.getWorkName()+"离场申请已审批通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"审批通过了您提交的离场申请："+approveStateNote,null);
                            }else{
                                //发送系统消息
                                messageService.pushOnlyPCMessage("work",entity.getId(),"您提交的"+entity.getWorkName()+"离场申请审批未通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"拒绝审批通过了您提交的离场申请："+approveStateNote,Util.getPcOrAppUserName(),users,null,null);
                                famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),users,"您提交的"+entity.getWorkName()+"离场申请审批未通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"拒绝审批通过了您提交的离场申请："+approveStateNote,null);
                            }
                        }catch (Exception e){
                        }
                    }
                }.run();
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.OPTIONERROR);
        }
        return ajaxJson;
    }
    
    /**
     * 机坪场道部联合检查
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "j_unioncheck_fun")
    @ResponseBody
    public AjaxJson j_unioncheck_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        //获取审批状态（通过 不通过）
        String approveState = request.getParameter("approveState");
        //获取审批意见
        String approveStateNote = request.getParameter("approveStateNote");
        try {
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"j-unioncheck")){
                //机坪场道部联合检查操作
                famsWorkService.j_unioncheck_fun(famsWorkApprove.getId(),approveState,approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
        }  catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.REPEATERROR);
        }
        return ajaxJson;
    }
    
    /**
     * 施工单位申请离场
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "s_out_fun")
    @ResponseBody
    public AjaxJson s_out_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        //获取申请备注
        String approveStateNote = request.getParameter("approveStateNote");
        try {
        	//判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"s-out")){
                //机坪场道部检查操作
                famsWorkService.s_out_fun(famsWorkApprove.getId(),approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            //推送
                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkApprove.getId());
                            //获取角色下的所有人员id
                            List<String> userIds=famsCommonService.getCanApproveIdsByRunPartOrgId();
                            if("N".equals(entity.getIsContinueWork())) {//非连续施工
                                //发送系统消息
                                messageService.pushOnlyPCMessage("work",entity.getId(),"您有新的"+entity.getWorkName()+"离场申请待审批",Util.getPcOrAppUserName()+"提交了一条离场申请，请您及时审批。",Util.getPcOrAppUserName(),userIds,null,null,null);
                                //推送消息
                                famsCommonService.sendMessage(Util.getPcOrAppUserId(),"work",entity.getId(),userIds,"您有新的"+entity.getWorkName()+"离场申请待审批",Util.getPcOrAppUserName()+"提交了一条离场申请，请您及时审批。",null);
                            }else{
                                //连续施工
                                //发送系统消息
                                messageService.pushOnlyPCMessage("work",entity.getId(),"您有新的"+entity.getWorkName()+"消息",entity.getWorkDepart()+"完成了施工。",Util.getPcOrAppUserName(),userIds,null,null,null);
                                //推送消息
                                famsCommonService.sendMessage(Util.getPcOrAppUserId(),"work",entity.getId(),userIds,"您有新的"+entity.getWorkName()+"消息",entity.getWorkDepart()+"完成了施工。",null);
                            }
                        }catch (Exception e){
                        }
                    }
                }.run();
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.OPTIONERROR);
        }
        return ajaxJson;
    }
    
    /**
     * 机坪场道部抽查
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "j_spotcheck_fun")
    @ResponseBody
    public AjaxJson j_spotcheck_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        //获取审批状态（通过 不通过）
        String approveState = request.getParameter("approveState");
        //获取审批意见
        String approveStateNote = request.getParameter("approveStateNote");
        try{
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"j-spotcheck")){
                //机坪场道部检查操作
                famsWorkService.j_spotcheck_fun(famsWorkApprove.getId(),approveState,approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
        } catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.REPEATERROR);
        }
        return ajaxJson;
    }
    
    /**
     * 机坪场道部例行检查
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "j_check_fun")
    @ResponseBody
    public AjaxJson j_check_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        //获取审批状态（通过 不通过）
        String approveState = request.getParameter("approveState");
        //获取审批意见
        String approveStateNote = request.getParameter("approveStateNote");
        try{
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"j-check")){
                //机坪场道部检查操作
                famsWorkService.j_check_fun(famsWorkApprove.getId(),approveState,approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
        }  catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.REPEATERROR);
        }
        return ajaxJson;
    }
    
    /**
     * 运行控制部审批施工单位进场
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "y_approve_in_fun")
    @ResponseBody
    public AjaxJson y_approve_in_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        //获取审批状态（通过 不通过）
        String approveState = request.getParameter("approveState");
        //获取审批意见
        String approveStateNote = request.getParameter("approveStateNote");
        try{
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"y-approve-in")){
                //运行控制部审批操作
                famsWorkService.y_approve_in_fun(famsWorkApprove.getId(),approveState,approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
                if ("Y".equals(approveState)) { //如果审批通过的话修改审批时间
                	//更新进场时间
                	famsWorkApproveService.updateDateIn(famsWorkApprove.getId(), new Date());
                }
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            //推送
                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkApprove.getId());
                            //通过创建人的id获取创建人并将其封装到List<TSBaesUser>集合中
                            List<TSBaseUser> users=famsCommonService.getApplyUser(entity.getPushUserId());
                            //推送消息
                            if("Y".equals(approveState)){
                                //发送系统消息
                                messageService.pushOnlyPCMessage("work",entity.getId(),"您提交的"+entity.getWorkName()+"进场申请已审批通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"审批通过了您提交的进场申请："+approveStateNote,Util.getPcOrAppUserName(),users,null,null);
                                famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),users,"您提交的"+entity.getWorkName()+"进场申请已审批通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"审批通过了您提交的进场申请："+approveStateNote,null);
                            }else{
                                //发送系统消息
                                messageService.pushOnlyPCMessage("work",entity.getId(),"您提交的"+entity.getWorkName()+"进场申请审批未通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"拒绝审批通过您提交的进场申请："+approveStateNote,Util.getPcOrAppUserName(),users,null,null);
                                famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),users,"您提交的"+entity.getWorkName()+"进场申请审批未通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"拒绝审批通过您提交的进场申请："+approveStateNote,null);
                            }
                        }catch (Exception e){
                        }
                    }
                }.run();
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }

        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.OPTIONERROR);
        }
        return ajaxJson;
    }

    /**
     * 运控审批撤回
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "y_approve_undo_fun")
    @ResponseBody
    public AjaxJson y_approve_undo_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        //获取审批状态（通过 不通过）
        String approveState = request.getParameter("approveState");
        //获取审批意见
        String approveStateNote = request.getParameter("approveStateNote");
        try{
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"y-approve-undo")){
                //运控审批撤回
                famsWorkService.y_approve_undo_fun(famsWorkApprove.getId(),approveState,approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.OPTIONERROR);
        }
        return ajaxJson;
    }

    /**
     * 施工单位撤回
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "s_undo_fun")
    @ResponseBody
    public AjaxJson s_undo_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        //获取撤回理由
        String approveStateNote = request.getParameter("approveStateNote");
        try{
            //施工单位申请撤回操作
            famsWorkService.s_undo_fun(famsWorkApprove.getId(),approveStateNote);
            //更新施工申请的流程状态
            famsWorkApproveService.updateTaskKey(famsWorkApprove.getId(),"s-undo");
            //入场时间置空指
            famsWorkApproveService.updateDateIn(famsWorkApprove.getId(), null);
            new Thread(){
                @Override
                public void run() {
                    try{
                        //推送
                        FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkApprove.getId());
                        //获取角色下的所有人员id
                        List<String> userIds=famsCommonService.getCanApproveIds();
                        //发送系统消息
                        messageService.pushOnlyPCMessage("work",entity.getId(),entity.getWorkName()+"已被撤回",Util.getPcOrAppUserName()+"撤回了"+entity.getWorkName()+"，撤回原因:"+approveStateNote,Util.getPcOrAppUserName(),userIds,null,null,null);
                        //推送消息
                        famsCommonService.sendMessage(Util.getPcOrAppUserId(),"work",entity.getId(),userIds,entity.getWorkName()+"已被撤回",Util.getPcOrAppUserName()+"撤回了"+entity.getWorkName()+"，撤回原因:"+approveStateNote,null);
                    }catch (Exception e){
                    }
                }
            }.run();
        }catch (BusinessException e){
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.OPTIONERROR);
        }
        return ajaxJson;
    }

    /**
     * 	施工单位申请进场
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "s_in_fun")
    @ResponseBody
    public AjaxJson s_in_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        try{
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"s-in")){
                //施工单位申请进场操作
                famsWorkService.s_in_fun(famsWorkApprove.getId());
                //更新施工申请的流程状态,并修改是否为初次进场
                famsWorkApproveService.updateTaskKeyAndIsFirst(famsWorkApprove.getId());
//                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            //推送
                            FamsWorkApproveEntity entity = famsCommonService.get(FamsWorkApproveEntity.class, famsWorkApprove.getId());
                            //获取运输控制部下所有有审批权限的人员的名单
                            List<String> userIds = famsCommonService.getCanApproveIdsByRunPartOrgId();
                            //发送系统消息
                            messageService.pushOnlyPCMessage("work", entity.getId(), "您有新的" + entity.getWorkName() + "进场申请待审批", Util.getPcOrAppUserDepartName() + "提交了一条进场申请，请您及时审批。", Util.getPcOrAppUserName(), userIds, null, null,null);
                            //推送消息
                            famsCommonService.sendMessage(Util.getPcOrAppUserId(), "work", entity.getId(), userIds, "您有新的" + entity.getWorkName() + "进场申请待审批", Util.getPcOrAppUserDepartName() + "提交了一条进场申请，请您及时审批。", null);
                        } catch (Exception e) {
                        }
                    }
                }.run();
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.OPTIONERROR);
        }
        return ajaxJson;
    }

    /**
     * 	运行控制部审批施工单位申请
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "y_approve_apply_fun")
    @ResponseBody
    public AjaxJson y_approve_apply_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        //获取审批状态（通过 不通过）
        String approveState = request.getParameter("approveState");
        //获取审批意见
        String approveStateNote = request.getParameter("approveStateNote");
        //报备类型
        String applyType = request.getParameter("applyType");
        try{
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"y-approve-apply")){
            	//运行控制部审批操作
                Integer count = famsWorkService.y_approve_apply_fun(famsWorkApprove.getId(),approveState,approveStateNote,applyType);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
                if(count>1){
                	new Thread(){
                        @Override
                        public void run() {
                            try{
                                //推送
                                FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkApprove.getId());
                                //通过 创建人id 获取对象并封装在List<TSBaseUser>中
                                List<TSBaseUser> users=famsCommonService.getApplyUser(entity.getPushUserId());
                                //运行控制部审批，如果审批通过了，通知审批提交人员；如果不通过，则给申请人发送消息
                                //推送消息
                                if("Y".equals(approveState)){
                                    //发送系统消息
                                    messageService.pushOnlyPCMessage("work",entity.getId(),"您提交的"+entity.getWorkName()+"申请运行控制部已审批通过",Util.getPcOrAppUserDepartName()+"审批通过了您提交的施工申请："+approveStateNote,Util.getPcOrAppUserName(),users,null,null);
                                    famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),users,"您提交的"+entity.getWorkName()+"申请运行控制部已审批通过",Util.getPcOrAppUserDepartName()+"审批通过了您提交的施工申请："+approveStateNote,null);
                                }else{
                                    //发送系统消息
                                    messageService.pushOnlyPCMessage("work",entity.getId(),"您提交的"+entity.getWorkName()+"申请运行控制部审批未通过",Util.getPcOrAppUserDepartName()+"拒绝审批通过您提交的施工申请："+approveStateNote,Util.getPcOrAppUserName(),users,null,null);
                                    famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),users,"您提交的"+entity.getWorkName()+"申请运行控制部审批未通过",Util.getPcOrAppUserDepartName()+"拒绝审批通过您提交的施工申请："+approveStateNote,null);
                                }
                            }catch (Exception e){
                            }
                        }
                    }.run();
                }
                
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
        }catch(BusinessException e){
            ajaxJson.setMsg(e.getMessage());
            ajaxJson.setSuccess(false);
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.OPTIONERROR);
        }
        return ajaxJson;
    }
    
    /**
     * 安全质量部审批施工单位申请
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "a_approve_apply_fun")
    @ResponseBody
    public AjaxJson a_approve_apply_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        //获取审批状态（通过 不通过）
        String approveState = request.getParameter("approveState");
        //获取审批意见
        String approveStateNote = request.getParameter("approveStateNote");
        try{
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"a-approve-apply")){
                //安全质量部审批操作
                famsWorkService.a_approve_apply_fun(famsWorkApprove.getId(),approveState,approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.OPTIONERROR);
        }
        return ajaxJson;
    }

    /**
     * 施工单位更新施工申请并提交该申请
     * @param famsWorkApprove
     * @param request
     * @return
     */
    @RequestMapping(params = "s_apply_fun")
    @ResponseBody
    public AjaxJson s_apply_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request) {
        AjaxJson ajaxJson=new AjaxJson();
        try {
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"s-apply")){
                //提交操作
                ajaxJson=famsWorkService.s_apply_fun(famsWorkApprove,request);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
                //更新提交人信息
                famsWorkApproveService.updatePushUserId(famsWorkApprove.getId(),Util.getPcOrAppUserId());
                //重置入场时间
                famsWorkApproveService.updateDateIn(famsWorkApprove.getId(), null);
                new Thread(){
                    @Override
                    public void run() {
                        try{
                        	 //推送
                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkApprove.getId());
                            //获取角色下的所有人员id
                            /**
                             * 获取运输控制部所有有权限的用户
                             * List<String> userIds=famsCommonService.getCanApproveIds();
                             * 修改为：如果用户所在的部门有审核人的话，发送给审核人；如果没有审核人的话，发送给运输控制部所有有权限的人
                             */
                            List<String> userIds = null;
                            List<String> userIds1 = famsCommonService.getCanApproveIdsByOrgCode(Util.getPcOrAppSysOrgCode());
                            if(userIds1 != null && !userIds1.isEmpty()){
                            	userIds = userIds1;
                            }else{
                            	//获取运行控制部所有有权限的数据
                            	userIds = famsCommonService.getCanApproveIdsByRunPartOrgId();
                            }
                            
                            //发送系统消息
                            messageService.pushOnlyPCMessage("work",entity.getId(),"您有新的"+entity.getWorkName()+"申请待审批",Util.getPcOrAppUserName()+"提交了一条施工申请，请您及时审批。",Util.getPcOrAppUserName(),userIds,null,null,null);
                            //推送消息
                            famsCommonService.sendMessage(Util.getPcOrAppUserId(),"work",entity.getId(),userIds,"您有新的"+entity.getWorkName()+"申请待审批",Util.getPcOrAppUserName()+"提交了一条施工申请，请您及时审批。",null);
                            //判断是否创建人为申请人
                            if(!entity.getCreateUserId().equals(Util.getPcOrAppUserId())){
                            	//通知创建人，你的消息已经被某人提交
                            	List<TSBaseUser> userPushMan = famsCommonService.getApplyUser(entity.getCreateUserId());
                            	messageService.pushOnlyPCMessage("work",entity.getId(),"您创建的"+entity.getWorkName()+"申请已经被提交",Util.getPcOrAppUserName()+"提交了您创建的作业申请，如有问题请与提交人进行联系。",Util.getPcOrAppUserName(),userPushMan,null,null);
                            	famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),userPushMan,"您创建的"+entity.getWorkName()+"申请已经被提交",Util.getPcOrAppUserName()+"提交了您创建的作业申请，如有问题请与提交人进行联系。",null);
                            }
                        }catch (Exception e){
                        }
                    }
                }.run();
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.OPTIONERROR);
        }
        return ajaxJson;
    }
    
    
    /**
     * 施工提交单位审核人 审批施工申请
     * @param famsWrokApprove
     * @param request
     * @return
     */
    @RequestMapping(params="s_department_apply_fun")
    @ResponseBody
    public AjaxJson s_department_apply_fun(FamsWorkApproveEntity famsWorkApprove, HttpServletRequest request){
    	AjaxJson ajaxJson = new AjaxJson();
    	//获取审批状态（通过/不通过）"Y"
    	String approveState = request.getParameter("approveState");
    	//获取审批意见  "审批通过"
    	String approveStateNote = request.getParameter("approveStateNote");
    	//报备类型 "apply"
    	String applyType = request.getParameter("applyType");
    	try{
    		//判断流程节点是否和指定节点相同，防止两个人重复操作
    		System.err.println("famsWorkApprove.getId():"+famsWorkApprove.getId());
            if(famsWorkService.sameTaskKey(famsWorkApprove.getId(),"s-department-apply")){
                //提交部审核人审批操作
            	famsWorkService.s_department_apply_fun(famsWorkApprove.getId(),approveState,approveStateNote,applyType);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(famsWorkApprove.getId());
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            //推送
                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkApprove.getId());
                            //推送消息
                            if("Y".equals(approveState)){
                            	//如果审核通过了，通知运输控制部中有权限的用户进行审批，并且通知申请用户，它的消息已经被部门审批人审批通过
                            	//获取运行控制部中所有有权限的用户的id
                                List<String> users = famsCommonService.getCanApproveIdsByRunPartOrgId();
                                List<TSBaseUser> userPushMan = famsCommonService.getApplyUser(entity.getPushUserId());
                                
                            	//发送系统消息
                                messageService.pushOnlyPCMessage("work",entity.getId(),"您提交的"+entity.getWorkName()+"部门申请已审批通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"审批通过了您提交的施工申请："+approveStateNote,Util.getPcOrAppUserName(),userPushMan,null,null);
                                messageService.pushOnlyPCMessage("work",entity.getId(),"您有新的"+entity.getWorkName()+"申请待审批",Util.getPcOrAppUserDepartName()+"提交了一条施工申请，请您及时审批。",Util.getPcOrAppUserName(),users,null,null,null);
                                //推送消息
                                famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),userPushMan,"您提交的"+entity.getWorkName()+"申请已审批通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"审批通过了您提交的施工申请："+approveStateNote,null);                                
                                famsCommonService.sendMessage(Util.getPcOrAppUserId(),"work",entity.getId(),users,"您有新的"+entity.getWorkName()+"申请待审批",Util.getPcOrAppUserDepartName()+"提交了一条施工申请，请您及时审批。",null);
                            }else{
                            	//如果审核没有通过，通知用户
                            	//获取提交人的用户id
                            	List<TSBaseUser> userPushMan = famsCommonService.getApplyUser(entity.getCreateUserId());
                            	 //发送系统消息
                                messageService.pushOnlyPCMessage("work",entity.getId(),"您提交的"+entity.getWorkName()+"申请审批未通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"拒绝审批通过您提交的施工申请："+approveStateNote,Util.getPcOrAppUserName(),userPushMan,null,null);
                                //推送消息
                                famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),userPushMan,"您提交的"+entity.getWorkName()+"申请审批未通过",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"拒绝审批通过您提交的施工申请："+approveStateNote,null);
                            }
                        }catch (Exception e){
                        }
                    }
                }.run();
            }else{
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg(MessageConstant.REPEATERROR);
            }
    		
    	}catch(BusinessException e){
            ajaxJson.setMsg(e.getMessage());
            ajaxJson.setSuccess(false);
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(MessageConstant.OPTIONERROR);
        }
    	
    	
    	
    	return ajaxJson;
    }
}


