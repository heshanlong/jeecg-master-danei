package com.gbiac.fams.business.construction.workapprove.rest;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbiac.fams.business.construction.dao.FamsWorkDao;
import com.gbiac.fams.business.construction.service.FamsWorkServiceI;
import com.gbiac.fams.business.construction.workapprove.dao.FamsWorkApproveDao;
import com.gbiac.fams.business.construction.workapprove.entity.ApplyDto;
import com.gbiac.fams.business.construction.workapprove.entity.ApproveDto;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveNoPassEntity;
import com.gbiac.fams.business.construction.workapprove.entity.SearchWorkingListDto;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveNoPassServiceI;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveServiceI;
import com.gbiac.fams.business.construction.worknodeinfo.service.FamsWorkNodeInfoServiceI;
import com.gbiac.fams.business.construction.worksafe.entity.FamsWorkSafePersonEntity;
import com.gbiac.fams.business.construction.worksafe.service.FamsWorkSafePersonServiceI;
import com.gbiac.fams.business.construction.worktype.entity.FamsWorkTypeEntity;
import com.gbiac.fams.business.construction.worktype.service.FamsWorkTypeServiceI;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.MessageService;
import com.gbiac.fams.common.Entity.PageEntity;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.map.entity.FamsCommonMapEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/famsWorkApproveRestController")
@Api(value = "famsWorkApproveRestController", description = "施工申请相关接口", tags = "FamsWorkApproveRestController")
public class FamsWorkApproveRestController{
    @Autowired
    private FamsWorkApproveServiceI famsWorkApproveService;
    @Autowired
    private FamsWorkServiceI famsWorkService;
    @Autowired
    protected MessageService messageService;
    @Autowired
    protected FamsCommonService famsCommonService;
    @Autowired
	private SystemService systemService;
    @Autowired
	private FamsWorkDao famsWorkDao;
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private FamsWorkSafePersonServiceI famsWorkSafePersonService;
	@Autowired
	private FamsWorkNodeInfoServiceI famsWorkNodeInfoService;
	@Autowired
	private FamsWorkApproveDao famsWorkApproveDao;
	@Autowired
	private FamsWorkTypeServiceI famsWorkTypeService;
	@Autowired
	private FamsWorkApproveNoPassServiceI famsWorkApproveNoPassService;
	/**
     * 	作业管理列表接口
     * @param dto.searchInput 搜索的字符
     * @param dto.workingState 作业状态 0：待审批 1：进行中 2：已完成
     * @param dto.pageNum 页面
     * @param dto.pageSize 页数
     * @param dto.workFlag 页面标识 0  我的作业  1 作业管理
     * @return
     */
    @ResponseBody
    @ApiOperation(value="作业列表搜索")
    @RequestMapping(value = "searchWorkingList",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> searchWorkingList(@RequestBody SearchWorkingListDto dto){
        try{
            if(0==dto.getWorkFlag()){//我的施工
                if(0== dto.getWorkingState()){
                    dto.setWorkingState(1);
                }else if(1== dto.getWorkingState()){
                    dto.setWorkingState(0);
                }
            }
            PageEntity page=new PageEntity(dto.getPageNum(),dto.getPageSize());
            List list=famsWorkApproveService.searchWorkingList(dto.getWorkFlag(),dto.getSearchInput(),dto.getWorkingState(),page);
            return Result.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
    }

    /**
     *	 获取施工问题详情
     * @param dto.workingItemID 施工申请id
     * @param dto.roleCode 施工角色
     * @param dto.workFlag 页面标识 0  我的施工  1 施工管理
     * @param dto.workingState 施工状态 0：待审批 1：进行中 2：已完成
     * @return
     */
    @ResponseBody
    @ApiOperation(value="施工问题详情页面")
    @RequestMapping(value = "getWorkingDetail",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> getWorkingDetail( @RequestBody ApplyDto dto){
        try{
            if(0==dto.getWorkFlag()){//我的施工
                if(0== dto.getWorkingState()){
                    dto.setWorkingState(1);
                }else if(1== dto.getWorkingState()){
                    dto.setWorkingState(0);
                }
            }
            Map map=famsWorkApproveService.getWorkingDetail(dto.getWorkingItemID(),dto.getRoleCode(),dto.getWorkFlag(),dto.getWorkingState());
            return Result.success(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
    }

    /**
     * 	获取所有施工地点的经纬度
     * @return
     */
    @ResponseBody
    @ApiOperation(value="获取所有进行中的施工地点经纬度")
    @RequestMapping(value = "getAllWorkingAreaLocation",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> getAllWorkingAreaLocation(){
        try{
            List list=famsWorkApproveService.getAllWorkingAreaLocation();
            return Result.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
    }

    /**
     * 	获取单个施工点经纬度
     * @param dto.workingItemID 施工申请id
     * @return
     */
    @ResponseBody
    @ApiOperation(value="获取单个施工点经纬度")
    @RequestMapping(value = "getSingleWorkingAreaLocation",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> getSingleWorkingAreaLocation(@RequestBody ApplyDto dto){
        try{
            Map map=famsWorkApproveService.getSingleWorkingAreaLocation(dto.getWorkingItemID());
            return Result.success(map);
        }catch (Exception e){
            return Result.error(MessageConstant.OPTIONERROR);
        }
    }

    /**
     * 	施工单位完成作业
     * @param dto.workingItemID 施工申请id
     * @return
     */
    @ResponseBody
    @ApiOperation(value="施工单位完成作业")
    @RequestMapping(value = "s_finish_fun",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> s_finish_fun(@RequestBody ApplyDto dto) {
        try{
            //施工单位完成施工
            famsWorkService.s_finish_fun(dto.getWorkingItemID());
            //更新施工申请的流程状态
            famsWorkApproveService.updateTaskKey(dto.getWorkingItemID());
            new Thread(){
                @Override
                public void run() {
                    try{
                        //推送
                        FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,dto.getWorkingItemID());
                        //获取角色下的所有人员id
                        List<String> userIds=famsCommonService.getCanApproveIds();
                        //发送系统消息
                        messageService.pushOnlyPCMessage("work",entity.getId(),"您有新的"+entity.getWorkName()+"信息",entity.getWorkDepart()+"完成了该施工。",Util.getPcOrAppUserName(),userIds,null,null,null);
                        //推送消息
                        famsCommonService.sendMessage(Util.getPcOrAppUserId(),"work",entity.getId(),userIds,"您有新的"+entity.getWorkName()+"信息",entity.getWorkDepart()+"完成了该施工。",null);
                    }catch (Exception e){
                    }
                }
            }.run();
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        
    }

    /**
     * 	运行控制部确认离场
     * 9/5修改 未测试
     * @param dto.workingItemID 施工申请id
     * @param dto.approveState 获取审批状态（通过 不通过）
     * @param dto.approveStateNote 获取审批意见
     * @return
     */
    @ResponseBody
    @ApiOperation(value="运行控制部确认离场")
    @RequestMapping(value = "y_approve_out_fun",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> y_approve_out_fun(@RequestBody ApproveDto dto) {
        //获取审批状态（通过 不通过）
        String approveState = dto.getApproveState();
        //获取审批意见
        String approveStateNote = dto.getApproveStateNote();
        //作业申请ID
        String id = dto.getWorkingItemID();
        try {
        	//判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(id,"y-approve-out")){
                //运控审批出场操作
                famsWorkService.y_approve_out_fun(id,approveState,approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(id);
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            //推送
                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,id);
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
                return Result.success();
            }else{
                return Result.error(MessageConstant.REPEATERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
       
    }

    /**
     * 	施工单位申请离场
     * 9/5修改 未测试
     * @param dto.workingItemID 施工申请id
     * @param dto.approveStateNote 获取审批意见
     * @return
     */
    @ResponseBody
    @ApiOperation(value="施工单位申请离场")
    @RequestMapping(value = "s_out_fun",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> s_out_fun(@RequestBody ApproveDto dto) {
        //获取申请备注
        String approveStateNote = dto.getApproveStateNote();
        //作业申请ID
        String id = dto.getWorkingItemID();
        try {
        	//判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(id,"s-out")){
                //机坪场道部检查操作
                famsWorkService.s_out_fun(id,approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(id);
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            //推送
                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,id);
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
                return Result.success();
            }else{
                return Result.error(MessageConstant.REPEATERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
       
    }

    /**
     * 	运行控制部审批施工单位进场
     * 9/5修改 未测试
     * @param dto.workingItemID 施工申请id
     * @param dto.approveState 获取审批状态（通过 不通过）
     * @param dto.approveStateNote 获取审批意见
     * @return
     */
    @ResponseBody
    @ApiOperation(value="运行控制部审批施工单位进场")
    @RequestMapping(value = "y_approve_in_fun",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> y_approve_in_fun(@RequestBody ApproveDto dto) {
        //获取审批状态（通过 不通过）
        String approveState = dto.getApproveState();
        //获取审批意见
        String approveStateNote = dto.getApproveStateNote();
        //作业申请ID
        String id = dto.getWorkingItemID();
        try{
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(id,"y-approve-in")){
                //运行控制部审批操作
                famsWorkService.y_approve_in_fun(id,approveState,approveStateNote);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(id);
                //修改进场时间
                famsWorkApproveService.updateDateIn(id, new Date());
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            //推送
                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,id);
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
                return Result.success();
            }else{
                return Result.error(MessageConstant.REPEATERROR);
            }

        }catch (Exception e){
            return Result.error(MessageConstant.OPTIONERROR);
        }
        
    }

    /**
     *	 施工单位撤回
     *	9/5修改 未测试
     * @param dto.workingItemID 施工申请id
     * @param dto.approveState 获取审批状态（通过 不通过）
     * @param dto.approveStateNote 获取审批意见
     * @return
     */
    @ResponseBody
    @ApiOperation(value="施工单位撤回")
    @RequestMapping(value = "s_undo_fun",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> s_undo_fun(@RequestBody ApproveDto dto) {
        //获取审批意见
        String approveStateNote = dto.getApproveStateNote();
        //作业申请ID
        String id = dto.getWorkingItemID();
        try{
            //施工单位申请撤回操作
            famsWorkService.s_undo_fun(id,approveStateNote);
            //更新施工申请的流程状态
            famsWorkApproveService.updateTaskKey(id,"s-undo");
            //入场时间置空指
            famsWorkApproveService.updateDateIn(id, null);
            new Thread(){
                @Override
                public void run() {
                    try{
                        //推送
                        FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,id);
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
            return Result.success();
        }catch(BusinessException e){
        	e.printStackTrace();
        	return Result.error(e.getMessage());
        }catch(Exception e){
        	e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
       
    }

    /**
     * 	施工单位申请进场
     * 9/5修改 未测试
     * @param dto.workingItemID 施工申请id
     * @return
     */
    @ResponseBody
    @ApiOperation(value="施工单位申请进场")
    @RequestMapping(value = "s_in_fun",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> s_in_fun(@RequestBody ApproveDto dto) {
    	//获取审批状态（通过 不通过）
        String approveState = dto.getApproveState();
        //获取审批意见
        String approveStateNote = dto.getApproveStateNote();
        //报备类型
        String applyType = dto.getApplyType();
        //作业申请ID
        String id = dto.getWorkingItemID();
    	try{
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(id,"s-in")){
                //施工单位申请进场操作
                famsWorkService.s_in_fun(id);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(id);
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            //推送
                            FamsWorkApproveEntity entity = famsCommonService.get(FamsWorkApproveEntity.class, id);
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
                return Result.success();
            }else{
                return Result.error(MessageConstant.REPEATERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
       
    }

    
    /**
     * 	运行控制部审批施工单位申请
     * 9/5修改 未测试1
     * @param dto.workingItemID 施工申请id
     * @param dto.approveState 获取审批状态（通过 不通过）
     * @param dto.approveStateNote 获取审批意见
     * @return
     */
    @ResponseBody
    @ApiOperation(value="运行控制部审批施工单位申请")
    @RequestMapping(value = "y_approve_apply_fun",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> y_approve_apply_fun(@RequestBody ApproveDto dto) {
        //获取审批状态（通过 不通过）
        String approveState = dto.getApproveState();
        //获取审批意见
        String approveStateNote = dto.getApproveStateNote();
        //报备类型
        String applyType = dto.getApplyType();
        //作业申请ID
        String id = dto.getWorkingItemID();
        	
        try{
        	//判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(id,"y-approve-apply")){
            	//运行控制部审批操作
                Integer count = famsWorkService.y_approve_apply_fun(id,approveState,approveStateNote,applyType);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(id);
                if(count>1){
                	new Thread(){
                        @Override
                        public void run() {
                            try{
                                //推送
                                FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,id);
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
                return Result.success();
            }else{
                return Result.error(MessageConstant.REPEATERROR);
            }
        }catch(BusinessException e){
        	e.printStackTrace();
        	return Result.error(e.getMessage());
        }catch(Exception e){
        	e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
       
    }

    /**
     * 	作业单位提交作业申请
     * 	9/5修改 未测试1
     * @param dto.workingItemID 施工申请id
     * @return
     */
    @ResponseBody
    @ApiOperation(value="施工单位提交施工申请")
    @RequestMapping(value = "s_apply_fun",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> s_apply_fun(@RequestBody ApplyDto dto) {
        //作业申请单ID
    	String id = dto.getWorkingItemID();
    	try {
            //判断流程节点是否和指定节点相同，防止两个人重复操作
            if(famsWorkService.sameTaskKey(id,"s-apply")){
                //提交操作
                famsWorkService.s_apply_fun(id);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(id);
                //更新提交人信息
                famsWorkApproveService.updatePushUserId(id,Util.getPcOrAppUserId());
                //入场时间置空指
                famsWorkApproveService.updateDateIn(id, null);
                new Thread(){
                    @Override
                    public void run() {
                        try{
                        	 //推送
                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,id);
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
                return Result.success();
            }else{
                return Result.error(MessageConstant.REPEATERROR);
            }
    	}catch(BusinessException e){
    		e.printStackTrace();
        	return Result.error(e.getMessage());
        }catch (Exception e){
        	e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
    }
    
    
    /**
     * 	作业提交部门审核人审批作业申请
     *  9/5修改 未测试1
     * @param dto.workingItemID 施工申请id
     * @param dto.approveState 获取审批状态（通过 不通过）
     * @param dto.approveStateNote 获取审批意见
     * @return
     */
    @ResponseBody
    @ApiOperation(value="提交部门审批申请")
    @RequestMapping(value = "s_department_apply_fun",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> s_department_apply_fun(@RequestBody ApproveDto dto){
    	//获取审批状态（通过 不通过）
        String approveState = dto.getApproveState();
        //获取审批意见
        String approveStateNote = dto.getApproveStateNote();
        //作业申请id
        String id = dto.getWorkingItemID();
        //报备类型
        String applyType = dto.getApplyType();
        try{
    		//判断流程节点是否和指定节点相同，防止两个人重复操作
    		System.err.println("famsWorkApprove.getId():"+id);
            if(famsWorkService.sameTaskKey(id,"s-department-apply")){
                //提交部审核人审批操作
            	famsWorkService.s_department_apply_fun(id,approveState,approveStateNote,applyType);
                //更新施工申请的流程状态
                famsWorkApproveService.updateTaskKey(id);
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            //推送
                            FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,id);
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
                return Result.success();
            }else{
            	return Result.error(MessageConstant.REPEATERROR);
            }
        }catch(BusinessException e){
        	e.printStackTrace();
        	return Result.error(e.getMessage());
        }catch (Exception e){
        	e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
       

	}
    
    
    /**
	 *  创建作业申请
	 *  9/6修改:  ✔
	 * 
	 * @param
	 * @return
	 */
	 @ResponseBody
	 @ApiOperation(value="添加作业申请内容")
	 @RequestMapping(value = "doSave",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseMessage<?> doSave(@RequestBody FamsWorkApproveEntity famsWorkApprove) {
		 String message = null;
		 AjaxJson j = new AjaxJson();
		 message = "作业申请添加成功";
		 try{
			 //判断前端传递过来的时间是否正确
			 if(!this.checkTime(famsWorkApprove,j)){
				 j.setSuccess(false);
				 message = "请检查相关的时间是否输入正确!";
				 return Result.error(message);
			 }
			 //获取安全人员的相关信息
			 List<FamsWorkSafePersonEntity> safePersons = famsWorkApprove.getSafePersons();
			 //判断是否有联系人
			 if(safePersons!=null && safePersons.size()>0) {
				 //保存施工申请并开始工作流并创建动态跟踪记录
	 				Serializable t = famsWorkApproveService.save(famsWorkApprove, safePersons);
	 				//更新施工申请的流程状态
	 				famsWorkApproveService.updateTaskKey(t.toString());
	 				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
	 			}else{
	 				return Result.error(MessageConstant.ERRORARGS);
	 			}
	 		}catch(Exception e){
	 			e.printStackTrace();
	 			message = "施工申请添加失败";
//	 			throw new BusinessException(e.getMessage());
	 			return Result.error(MessageConstant.REPEATERROR);
	 		}
	 		j.setMsg(message);
	 		System.err.println("###########添加逻辑的输出到页面的内容："+j.getJsonStr()+"########");
	 		return Result.success(message);
	 	}

	/**
	 * 创建并直接提交申请
	 * 9/6修改 ✔
	 * @param famsWorkApprove
	 * @return
	 */
	 @ResponseBody
	 @ApiOperation(value="提交作业申请内容")
	 @RequestMapping(value = "doSubmit",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseMessage<?> doSubmit(@RequestBody FamsWorkApproveEntity famsWorkApprove){
		String message = null;
		 AjaxJson j = new AjaxJson();
		 message = "作业申请添加成功";
		 try{
			 //判断前端传递过来的时间是否正确
			 if(!this.checkTime(famsWorkApprove,j)){
				 j.setSuccess(false);
				 message = "请检查相关的时间是否输入正确!";
				 return Result.error(message);
			 }

			 //获取安全人员的相关信息
			 List<FamsWorkSafePersonEntity> safePersons = famsWorkApprove.getSafePersons();
			 if(safePersons!=null && safePersons.size()>0) {
				 //保存施工申请并开始工作流并创建动态跟踪记录
	 				Serializable t = famsWorkApproveService.save(famsWorkApprove, safePersons);
	 				System.err.println("############ 提交时的任务节点名称为："+t.toString()+" 是不是s-department-apply节点，如果是的话，此处需要进行操作（传递result的值）##############");
	 				//需要直接提交申请,即完成该节点
	 					Map<String,Object> subMap = new HashMap<>();
	 					subMap.put("recall", "N");
	 					String result = null;
	 					//判断是否申请人部门是否存在审核人，通过申请人所在的部门获取对应的审核人id
	 		        	List<String> userIds1 = famsCommonService.getCanApproveIdsByOrgCode(Util.getPcOrAppSysOrgCode());
	 					if(userIds1!=null && !userIds1.isEmpty()){
	 						result = "Y";
	 					}else{
	 						result = "N";
	 					}
	 					subMap.put("result", result);
	 					famsWorkService.completeTaskByApproveId(t.toString(),subMap);
	 					//记录流程进度
	 					famsCommonService.saveProgress("work",t.toString(),null,"提交施工申请", null,null,"s-apply");
	 					//更新提交人信息
	 					famsWorkApproveService.updatePushUserId(famsWorkApprove.getId(),Util.getPcOrAppUserId());
	 					 //入场时间置空指
	 		            famsWorkApproveService.updateDateIn(famsWorkApprove.getId(), null);
	 					new Thread(){
	 						@Override
	 						public void run() {
	 							try {
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
	 	                            //判断是否创建人为申请人
	 	                            if(!entity.getCreateUserId().equals(Util.getPcOrAppUserId())){
	 	                            	//通知创建人，你的消息已经被某人提交
	 	                            	 List<TSBaseUser> userPushMan = famsCommonService.getApplyUser(entity.getCreateUserId());
	 	                            	 messageService.pushOnlyPCMessage("work",entity.getId(),"您创建的"+entity.getWorkName()+"申请已经被提交",Util.getPcOrAppUserName()+"提交了您创建的作业申请，如有问题请与提交人进行联系。",Util.getPcOrAppUserName(),userPushMan,null,null);
	 	                            	 famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"work",entity.getId(),userPushMan,"您创建的"+entity.getWorkName()+"申请已经被提交",Util.getPcOrAppUserName()+"提交了您创建的作业申请，如有问题请与提交人进行联系。",null);
	 	                            }
	 	                            
	 	                            //发送系统消息
	 	                            messageService.pushOnlyPCMessage("work",entity.getId(),"您有新的"+entity.getWorkName()+"申请待审批",Util.getPcOrAppUserName()+"提交了一条施工申请，请您及时审批。",Util.getPcOrAppUserName(),userIds,null,null,null);
	 	                            //推送消息
	 	                            famsCommonService.sendMessage(Util.getPcOrAppUserId(),"work",entity.getId(),userIds,"您有新的"+entity.getWorkName()+"申请待审批",Util.getPcOrAppUserName()+"提交了一条施工申请，请您及时审批。",null);
	 	                        }catch (Exception e){
	 							}
	 						}
	 					}.run();
	 				
	 				//更新施工申请的流程状态
	 				famsWorkApproveService.updateTaskKey(t.toString());
	 				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
	 				j.setMsg(message);
	 		 		return Result.success(message);
	 			}else{
	 				return Result.error(MessageConstant.ERRORARGS);
	 			}
	 		}catch(Exception e){
	 			e.printStackTrace();
	 			return Result.error(MessageConstant.REPEATERROR);
	 		}
	 		
		 
	 }
	 
	 
	 /**
	* 更新作业申请
	* 9/6创建： 未测试
	* @param
	* @return
	*/
	@ResponseBody
	@ApiOperation(value="更新作业申请内容")
	@RequestMapping(value = "doUpdate",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> doUpdate(@RequestBody FamsWorkApproveEntity famsWorkApprove) {
		String message = null;
		message = "施工申请更新成功";
		FamsWorkApproveEntity t = famsWorkApproveService.get(FamsWorkApproveEntity.class, famsWorkApprove.getId());
		try {
			//将前端传递过来的参数组装成maprams(request);
			List<FamsWorkSafePersonEntity> safePersons = famsWorkApprove.getSafePersons();
			if(safePersons!=null && safePersons.size()>0) {
				MyBeanUtils.copyBeanNotNull2Bean(famsWorkApprove, t);
				famsWorkApproveService.saveOrUpdate(t,safePersons);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}else{
				return Result.error(MessageConstant.ERRORARGS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("施工申请更新失败");
		}
		return Result.success(message);
	}
	 
	/**
	 * 获取我的作业列表集合
	 * 9/18添加，
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="获取该用户提交所有的作业")
	@RequestMapping(value="getMyWork", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> getMyWork(@RequestBody SearchWorkingListDto dto){
		@SuppressWarnings("unchecked")
		List<FamsWorkApproveEntity> myWorks = (List<FamsWorkApproveEntity>) famsWorkApproveService.getMyWorkByCreateMan(Util.getPcOrAppUserName(),dto.getPageNum(), dto.getPageSize());
		return Result.success(myWorks);
	}
	
	/**
	 * 通过作业id删除作业
	 * @param approveId
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="通过作业id删除作业")
	@RequestMapping(value="deleteWork", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> deleteWork(@RequestParam("approveId")String approveId){
		if(famsWorkApproveService.deleteByApproveId(approveId)) {
			return Result.success();
		}else {
			return Result.error("删除失败！");
		}
	}
	
	/**
	 * 根据作业状态获取作业信息
	 * @param workState
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="根据作业状态获取作业信息列表")
	@RequestMapping(value="getMyWorkByState", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> getMyWorkByState(@RequestBody SearchWorkingListDto dto){
		if(dto.getWorkingState()!=null && dto.getWorkingState()<3) {
			System.err.println("当前状态："+dto.getWorkingState());
			@SuppressWarnings("unchecked")
			List<FamsWorkApproveEntity> myWork = (List<FamsWorkApproveEntity>) famsWorkApproveService.getMyWorkByWorkState(Util.getPcOrAppUserName(),dto.getWorkingState(), dto.getSearchInput(), dto.getPageNum(), dto.getPageSize());
			return Result.success(myWork);
		}else {
			return Result.error("传入的参数有问题");
		}
	}
	
	/**
	 * 获取我的待提交的作业信息
	 */
	@ResponseBody
	@ApiOperation(value="获取我的作业中待提交的作业列表")
	@RequestMapping(value="getMyWorkByStateEqSApply",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> getMyWorkByStateEqSApply(@RequestBody SearchWorkingListDto dto){
		@SuppressWarnings("unchecked")
		List<FamsWorkApproveEntity> myWork = (List<FamsWorkApproveEntity>) famsWorkApproveService.getmyWorkByStateEqApplyAndCreateName(Util.getPcOrAppUserName(),dto.getSearchInput(),dto.getPageNum(),dto.getPageSize());
		if(myWork!=null && myWork.size()>0)
			return Result.success(myWork);
		else
			return Result.success();
	}
	
	/**
	 * 	根据作业ID获取实体详情
	 * @param famsWorkApprove
	 * @param req
	 */
	@ResponseBody
	@ApiOperation(value="根据作业id获取相关的作业信息")
	@RequestMapping(value="getEntityDetail", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> getEntityDetail(@RequestParam("id") String id){
		FamsWorkApproveEntity famsWorkApprove = null;
		Map map = null;
		if (StringUtil.isNotEmpty(id)) {
			famsWorkApprove = famsWorkApproveService.getEntity(FamsWorkApproveEntity.class, id);
			//获取施工申报单位名称
			TSDepart tsDepart = famsCommonService.get(TSDepart.class, famsWorkApprove.getWorkApproveDepart());
			FamsWorkTypeEntity famsWorkTypeEntity = famsCommonService.get(FamsWorkTypeEntity.class, famsWorkApprove.getWorkTypeId());
			//获取地图点位信息
			List<FamsCommonMapEntity> maps = famsCommonService.getCommonObject(FamsCommonMapEntity.class, null, famsWorkApprove.getId(), null, null);
			//获取值班及安全员信息
			List<FamsWorkSafePersonEntity> workSafes = famsWorkSafePersonService.findByProperty(FamsWorkSafePersonEntity.class, "approveId", famsWorkApprove.getId());
			//获取附件id集合
			List<FamsCommonFileEntity> files = famsCommonService.getCommonObject(FamsCommonFileEntity.class, null, famsWorkApprove.getId(), null, null);
			//获取历史动态
			List process=famsWorkApproveService.getProcess(famsWorkApprove.getId());
			//获取检查记录信息
			List checkInfo=famsWorkApproveService.getCheckInfo(famsWorkApprove.getId());
			
			//获取该申请状态
			String state=famsWorkNodeInfoService.getStateByTaskKey(famsWorkApprove.getTaskKey());
			if(tsDepart!=null)
				famsWorkApprove.setWorkApproveDepartStr(tsDepart.getDepartname());
			if(famsWorkTypeEntity!= null) {
				famsWorkApprove.setWorkTypeStr(famsWorkTypeEntity.getTypeName());
			}
			//将获取到的数据进行封装
			map = new HashMap<String,Object>();
			map.put("tsDepart", tsDepart);
			map.put("famsWorkApprove", famsWorkApprove);
			map.put("maps", Util.mapsToMap(maps));
			map.put("workSafes", workSafes);
			map.put("files", Util.filesToMap(files));
			map.put("process", process);
			map.put("checkInfo", checkInfo);
			map.put("state", state);
			return Result.success(map);
			/*if(req!=null){
				req.setAttribute("tsDepart", tsDepart);
				req.setAttribute("famsWorkApprove", famsWorkApprove);
				req.setAttribute("maps", Util.mapsToMap(maps));
				req.setAttribute("workSafes", workSafes);
				req.setAttribute("files", Util.filesToMap(files));
				req.setAttribute("process", process);
				req.setAttribute("checkInfo", checkInfo);
				req.setAttribute("state", state);
			}*/
/*			if(map!=null){
				//设置部门中文名
				famsWorkApprove.setWorkApproveDepartStr(tsDepart.getDepartname());
				System.err.println("workApproveDepartStr:"+famsWorkApprove.getWorkApproveDepartStr());
				//查询权限
				List<FamsWorkApproveEntity> list=new ArrayList();
				list.add(famsWorkApprove);
				//根据用户id获取用户对指定数据的操作权限
				List<Map> results=famsWorkApproveDao.getAppDataOfNodeInfo(list,map.get("roleCode").toString(),map.get("workFlag").toString(),map.get("workingState").toString());
				Map m=results.size()>0?results.get(0):null;
				famsWorkApprove.setNodeInfo(m);
				//获取施工类型
				FamsWorkTypeEntity workTypeEntity = famsCommonService.getEntityByProperty(FamsWorkTypeEntity.class, "id".split(","), famsWorkApprove.getWorkTypeId().split(","));
				famsWorkApprove.setWorkTypeStr(workTypeEntity.getTypeName());
				//查询是否拥有例行、联合或抽查的权限，
				Map canCheckList=famsWorkApproveService.getCheckFunctions(famsWorkApprove.getId(),Util.getPcOrAppUserId());
				map.put("canCheckList", canCheckList);
				map.put("famsWorkApprove", famsWorkApprove);
				map.put("maps", Util.mapsToMap(maps));
				map.put("workSafes", workSafes);
				map.put("files", files);
				map.put("process", process);
				map.put("checkInfo", checkInfo);
				map.put("state", state);
			}*/
		}
		return Result.success("传参有误");
	}
	
	
	@ResponseBody
	@ApiOperation(value="获取作业类型的字典集")
	@RequestMapping(value="getWorkTypeDate", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> getWorkTypeDate(){
		List<FamsWorkTypeEntity> famsWorkTypes = famsWorkTypeService.getWorkTypeDate();
		return Result.success(famsWorkTypes);
	}
	
	/**
	 * 获取运行控制部审批不通过的作业申请
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="获取运行控制部审批不通过的作业申请")
	@RequestMapping(value="getNoPassApprove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> getNoPassApprove(@RequestBody SearchWorkingListDto dto){
		
		try {
			List<FamsWorkApproveNoPassEntity> lists = (List<FamsWorkApproveNoPassEntity>) famsWorkApproveNoPassService.getNoPassApprove(dto.getSearchInput(), dto.getPageNum(), dto.getPageSize(), Util.getPcOrAppUserName());
			return Result.success(lists);
		} catch (Exception e) {
			return Result.error("获取数据失败!");
		}
		
	}
	
	 /**
	  *	 检查前端传递过来的时间是否正确
	  * @param famsWorkApprove
	  * @return
	  */
	 private boolean checkTime(FamsWorkApproveEntity famsWorkApprove,AjaxJson j) {
		String isUseFire = famsWorkApprove.getIsUseFire();
		Date dateStart = famsWorkApprove.getDateStart();
		Date dateEnd = famsWorkApprove.getDateEnd();
		Date workStartTime = famsWorkApprove.getWorkStartTime();
		Date workEndTime = famsWorkApprove.getWorkEndTime();
		Date useFireStartTime = famsWorkApprove.getUseFireStartTime();
		Date useFireEndTime = famsWorkApprove.getUseFireEndTime();
		if(workStartTime!=null&&workEndTime!=null){
			if(workStartTime.getTime()>workEndTime.getTime()){
				j.setMsg("作业开始时间必须小于作业结束时间！");
				return false;
			}
			if(dateEnd.getTime()>(dateStart.getTime()+6l*24*60*60*1000)) {
				j.setMsg("最多允许申请一周的作业计划，请修改！");
				return false;
			}
			if(isUseFire!=null&&"Y".equals(isUseFire)){
				if(useFireStartTime!=null&&useFireEndTime!=null){
					if(useFireStartTime.getTime()>=useFireEndTime.getTime()){
						j.setMsg("动火开始时间必须小于动火结束时间！");
						return false;
					}
					if(useFireStartTime.getTime()<workStartTime.getTime()||useFireEndTime.getTime()>workEndTime.getTime()){
						j.setMsg("动火开始时间和动火结束时间必须在施工时间区间内！");
						return false;
					}
				}else{
					j.setMsg("动火开始时间必须小于动火结束时间！");
					return false;
				}
			}
		}else{
			j.setMsg("作业开始时间和结束时间不能为空！");
			return false;
		}
		return true;
	}
	/**
	 * 	将前端传递过来的参数组装成map
	 * @author 龚道海
	 * @param request
	 * @return
	 */
	public Map getParams(HttpServletRequest request) {
		//获取地图点位信息
		String workAffectAreaMap = request.getParameter("workAffectAreaMap");
		//获取值班及安全员信息
		String[] workSafePerson_roles = request.getParameterValues("workSafePerson_role");
		String[] workSafePerson_names = request.getParameterValues("workSafePerson_name");
		String[] workSafePerson_phones = request.getParameterValues("workSafePerson_phone");
		String[] workSafePerson_intercoms = request.getParameterValues("workSafePerson_intercom");
		//获取附件id集合
		String files = request.getParameter("files");
		if(workSafePerson_roles.length==workSafePerson_names.length&&workSafePerson_names.length==workSafePerson_phones.length) {
			Map<String, Object> map = new HashMap<>();
			map.put("workAffectAreaMap", workAffectAreaMap);
			map.put("workSafePerson_roles", workSafePerson_roles);
			map.put("workSafePerson_names", workSafePerson_names);
			map.put("workSafePerson_phones", workSafePerson_phones);
			map.put("workSafePerson_intercoms", workSafePerson_intercoms);
			map.put("files", files);
			return map;
		}
		return null;
	}
	
	public void completeTaskByApproveId(String id, Map map) {
		// 获取施工申请对象
		FamsWorkApproveEntity famsWorkApproveEntity = famsWorkApproveService.get(FamsWorkApproveEntity.class, id);
		if (famsWorkApproveEntity != null && famsWorkApproveEntity.getProcinstId() != null
				&& !famsWorkApproveEntity.getProcinstId().equals("")) {
			// 通过流程实例id完成该节点流程
			completeTask(famsWorkApproveEntity.getProcinstId(), map);
		}
	}
	
	public void completeTask(String procinstId, Map map) {
		// 根据流程实例id获取流程节点id
		String taskId = famsWorkDao.getTaskIdByProId(procinstId);
		if (taskId != null) {
			processEngine.getTaskService()// 与正在执行的任务管理相关的Service
					.complete(taskId, map);
		}
	}
	
	
	
}

