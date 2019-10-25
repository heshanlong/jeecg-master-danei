package com.gbiac.fams.business.construction.service;

import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface FamsWorkServiceI extends CommonService {

    /**
     * 完成工作流
     * @author 龚道海
     * @param procinstId 流程实例id
     * @param map
     */
    void completeTask(String procinstId,Map map);

    /**
     * 完成工作流
     * @author 龚道海
     * @param id 申请id
     * @param map
     */
    void completeTaskByApproveId(String id, Map map);

    /**
     * 检查业务id对应的工作流任务节点是否相同
     * @author 龚道海
     * @param approveId 施工申请id
     * @param taskKey 任务节点名称
     * @return
     */
    Boolean sameTaskKey(String approveId, String taskKey);

    /**
     * 施工单位更新施工申请并提交该申请
     * @author 龚道海
     * @param famsWorkApprove
     * @param request
     * @return
     */
    AjaxJson s_apply_fun(FamsWorkApproveEntity famsWorkApprove,HttpServletRequest request) throws Exception;

    /**
     * 施工单位提交施工申请
     * @param approveId
     * @return
     * @throws Exception
     */
    void s_apply_fun(String approveId) throws Exception;

    /**
     * 安全质量部审批施工单位申请
     * @author 龚道海
     * @param id
     * @param approveState
     * @param approveStateNote
     */
    void a_approve_apply_fun(String id, String approveState, String approveStateNote) throws Exception;
    
    /**
     * 运行控制部确认离场
     * @author 龚道海
     * @param id
     * @param approveState
     * @param approveStateNote
     * @return
     */
    void y_approve_out_fun(String id,String approveState, String approveStateNote) throws Exception;
    /**
     * 机坪场道部联合检查
     * @author 龚道海
     * @param id
     * @return
     */
    void j_unioncheck_fun(String id,String approveState, String approveStateNote) throws Exception;
    /**
     * 施工单位申请离场
     * @author 龚道海
     * @param id
     * @return
     */
    void s_out_fun(String id, String approveStateNote) throws Exception;
    /**
     * 机坪场道部抽查
     * @author 龚道海
     * @param id
     * @return
     */
    void j_spotcheck_fun(String id,String approveState, String approveStateNote) throws Exception;
    
    /**
     * 运行控制部审批施工单位申请
     * @author 龚道海
     * @param id
     * @param approveState
     * @param approveStateNote
     * @param applyType
     */
    Integer y_approve_apply_fun(String id, String approveState, String approveStateNote,String applyType) throws Exception;

    /**
     * 施工单位申请进场
     * @author 龚道海
     * @param id
     */
    void s_in_fun(String id) throws Exception;

    /**
     * 运行控制部审批施工单位进场
     * @author 龚道海
     * @param id
     * @param approveState
     * @param approveStateNote
     */
    void y_approve_in_fun(String id, String approveState, String approveStateNote) throws Exception;

    /**
     * 机坪场道部例行检查
     * @author 龚道海
     * @param id
     * @param approveState
     * @param approveStateNote
     */
    void j_check_fun(String id, String approveState, String approveStateNote) throws Exception;

    /**
     * 施工单位申请撤回操作
     * @author 龚道海
     * @param id
     * @param approveStateNote
     */
    void s_undo_fun(String id, String approveStateNote) throws Exception;

    /**
     * 运控审批撤回
     * @author 龚道海
     * @param id
     * @param approveState
     * @param approveStateNote
     */
    void y_approve_undo_fun(String id, String approveState, String approveStateNote) throws Exception;

    /**
     * 施工单位完成施工
     * @param id
     */
    void s_finish_fun(String id) throws Exception;

    /**
     * 定时自动完成24小时施工类型的施工完成节点
     * @throws Exception
     */
    void autoFinish() throws Exception;

    /**
     * 通过申请id判断该id是否可以撤回
     * @param id
     * @return
     * @throws Exception
     */
    boolean isCanUndo(String id) throws Exception;

	void s_department_apply_fun(String id, String approveState, String approveStateNote, String applyType) throws Exception;
}
