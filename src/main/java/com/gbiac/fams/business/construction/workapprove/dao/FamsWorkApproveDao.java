package com.gbiac.fams.business.construction.workapprove.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveExportEntity;
import com.gbiac.fams.common.Entity.PageEntity;

@MiniDao
public interface FamsWorkApproveDao {
    /**
     * 获取当前用户拥有的流程节点数组
     * @author 龚道海
     * @param userId
     * @return
     */
    @Arguments("userId")
    List<String> getActRuTaskExecutionIds(String userId);

    /**
     * 获取PC端特定角色对指定数据的操作权限
     * @author 龚道海
     * @param famsWorkApproveEntitys
     * @param roleCode
     * @return
     */
    @Arguments({"famsWorkApproveEntitys","roleCode"})
    List<Map> getPcDataOfNodeInfo(List<FamsWorkApproveEntity> famsWorkApproveEntitys,String roleCode);

    /**
     * 获取APP端特定角色对指定数据的操作权限
     * @author 龚道海
     * @param famsWorkApproveEntitys
     * @param roleCode
     * @return
     */
    @Arguments({"famsWorkApproveEntitys","roleCode","workFlag","workingState"})
    List<Map> getAppDataOfNodeInfo(List<FamsWorkApproveEntity> famsWorkApproveEntitys,String roleCode,String workFlag,String workingState);

    /**
     * 获取历史动态
     * @author 龚道海
     * @param busId
     * @return
     */
    @Arguments("busId")
    @Sql("SELECT ( SELECT departname FROM t_s_depart WHERE id = t.option_depart_id ) departname, ( SELECT realname FROM t_s_base_user WHERE id = t.option_user_id ) realname, t.option_note note, t.option_flag flag, t.option_content content, t.option_time time FROM fams_common_progress t WHERE t.business_id = :busId AND t.module = 'work' ORDER BY t.option_time DESC")
    List<Map> getProcess(String busId);

    /**
     *通用下拉菜单数据接口
     * @param workType
     * @param keyword
     * @param maxNum
     * @return
     */
    @Arguments({"workType","keyword","maxNum"})
    List<Map> bsSuggest(String workType,String keyword, Integer maxNum);

    /**
     * 获取施工申请列表
     * @param searchInput
     * @param workingState
     * @param userId
     * @param flags
     * @param pageEntity
     * @return
     */
    @Arguments({"searchInput","workingState","userId","flags","pageEntity"})
    List<Map> getWorkingList(String searchInput,Integer workingState, String userId,String[] flags,PageEntity pageEntity);

    /**
     * 获取所有施工地点的经纬度
     * @return
     */
    List<Map> getAllWorkingAreaLocation();

    /**
     * 获取单个施工点经纬度
     * @param workingItemID 施工申请id
     * @return
     */
    @Sql("SELECT t.map_points location, t1.work_name workingTitle FROM fams_common_map t, fams_work_approve t1 WHERE t.business_id = t1.id AND t1.id = :workingItemID")
    @Arguments("workingItemID")
    Map getSingleWorkingAreaLocation(String workingItemID);

    /**
     * 根据申请申请id获取实例对象
     * @param approveId 申请id
     * @return
     */
    @Sql("SELECT t1.* FROM fams_work_approve t, act_ru_execution t1 WHERE t.procinst_id = t1.ID_ AND t.id = :approveId limit 0,1")
    @Arguments("approveId")
    Map getExecutions(String approveId);

    /**
     * 根据用户id查看是否拥有'例行检查', '联合检查', '维护检查'检查
     * @param userId
     * @return
     */
    @Sql("SELECT t2.functionname FROM t_s_role_user t, t_s_role_function t1, t_s_function t2 WHERE t.roleid = t1.roleid AND t1.functionid = t2.ID AND t.userid = :userId AND t2.functionname IN ( '例行检查', '联合检查', '维护检查' )")
    @Arguments("userId")
    List<Map> getCheckFunctions(String userId);

    /**
     * 根据用户id查看是否拥有'施工审批'
     * @param userId
     * @return
     */
    @Sql("SELECT t2.functionname FROM t_s_role_user t, t_s_role_function t1, t_s_function t2 WHERE t.roleid = t1.roleid AND t1.functionid = t2.ID AND t.userid = :userId AND t2.functionname IN ( '作业审批')")
    @Arguments("userId")
    List<Map> getApproveFunctions(String userId);

    /**
     * 是否拥有部门审批的权限
     */
    @Sql("SELECT t2.functionname FROM t_s_role_user t, t_s_role_function t1, t_s_function t2 WHERE t.roleid = t1.roleid AND t1.functionid = t2.ID AND t.userid = :userId AND t2.functionname IN ( '部门作业审批')")
    @Arguments("userId")
    List<Map> getdepartApproveFunctions(String userId);
    
    /**
     * 根据用户id查看是否拥有'施工申请'
     * @param userId
     * @return
     */
    @Sql("SELECT t2.functionname FROM t_s_role_user t, t_s_role_function t1, t_s_function t2 WHERE t.roleid = t1.roleid AND t1.functionid = t2.ID AND t.userid = :userId AND t2.functionname IN ( '作业申请')")
    @Arguments("userId")
    List<Map> getApplyFunctions(String userId);
    /**
     * 通过申请id获取该申请的相关信息
     * @param approveId
     * @return
     */
    @Arguments("approveId")
    Map getApproveInfo(String approveId);

    /**
     * 更新施工申请的流程状态
     * @param approveId
     */
    @Sql("UPDATE fams_work_approve SET task_key = :taskKey WHERE id = :approveId")
    @Arguments({"approveId","taskKey"})
    void updateTaskKey(String approveId,String taskKey);
    
    @Sql("UPDATE fams_work_approve SET date_in = :dateIn WHERE id = :id")
    @Arguments({"id","dateIn"})
    void updateDateIn(String id, Date dateIn);
    /**
     * 根据施工申请id获取任务key
     * @param approveId
     * @return
     */
    @Sql("SELECT t1.TASK_DEF_KEY_ FROM fams_work_approve t, act_ru_task t1 WHERE t.procinst_id = t1.EXECUTION_ID_ AND t.id = :approveId")
    @Arguments("approveId")
    String getTaskKeyByApproveId(String approveId);
    
    
    @Sql("UPDATE fams_work_approve SET is_first='N' WHERE id = :id")
    @Arguments("id")
    void updateIsFirst(String id);
    
    /**
     * 根据登陆人获取同部门中作业申请
     * @param companyCode
     * @return
     */
    @Sql("SELECT t.number,t.work_name,t.work_depart,t.work_area,t.use_fire_start_time,"
    		+ "t.use_fire_end_time,t.create_date,t.is_continue_work,t1.type_name,t2.task_state "
    		+ "FROM fams_work_approve t, fams_work_type t1,fams_work_node_info t2 "
    		+ "WHERE t.work_type_id = t1.id AND t1.sys_org_code = t2.sys_org_code AND t.sys_org_code = :sysOrgCode")
    @Arguments("sysOrgCode")
    String getTaskKeyBycompanyCode(String sysOrgCode);

    /**
     * 根据申请主表id获取检查记录信息
     * @param approveId
     * @return
     */
    @Sql("SELECT t1.TASK_DEF_KEY_ FROM fams_work_approve t, act_ru_task t1 WHERE t.procinst_id = t1.EXECUTION_ID_ AND t.id = :approveId")
    @Arguments("approveId")
    List<Map> getCheckInfo(String approveId);

    @Sql("SELECT t.id, t.number, t.work_name, t.work_depart, t.work_area, t.work_affect_area, t.work_start_time, t.work_end_time, ( SELECT person_name FROM fams_work_safe_person WHERE approve_id = t.id LIMIT 0, 1 ) person_name FROM fams_work_approve t WHERE DATE_FORMAT(now(), '%Y-%m-%d') = DATE_FORMAT( t.work_start_time, '%Y-%m-%d' ) AND t.task_key IN ('s-out') ORDER BY t.work_start_time DESC")
    List<Map> getYWorkInfo();

    @Sql("SELECT IFNULL(( SELECT departname FROM t_s_depart WHERE id = t1.option_depart_id ), '系统' ) departname, IFNULL(( SELECT realname FROM t_s_base_user WHERE id = t1.option_user_id ), '管理员' ) username, t1.business_id bid, t1.option_time time, t1.option_note note, t1.option_content content, t.work_name workname FROM fams_work_approve t, fams_common_progress t1 WHERE t.id = t1.business_id AND t.create_user_id = :userId AND t.task_key NOT IN ( 's-undo', 's-apply', 'y-approve-apply' ) ORDER BY t1.option_time DESC LIMIT 0, 5")
    @Arguments("userId")
    List<Map> getSWorkInfo(String userId);

    @Sql("SELECT t.* FROM fams_work_approve t WHERE t.task_key = 'y-approve-apply'")
    List<Map> getNeedApproveApplyList();

    @Sql("SELECT * FROM act_ru_execution t WHERE t.ID_ = :procinstId LIMIT 0, 1")
    @Arguments("procinstId")
    Map getExecutionById(String procinstId);

    @Sql("SELECT t.* FROM fams_work_approve t WHERE t.task_key = 'y-approve-in'")
    List<Map> getNeedApproveInList();

    @Sql("SELECT t.* FROM fams_work_approve t WHERE t.task_key = 'y-approve-out'")
    List<Map> getNeedApproveOutList();

    @Arguments("applyType")
    List<FamsWorkApproveExportEntity> getDataByApplyType(String applyType);

    @Sql("SELECT t.id, t.departname name FROM t_s_depart t")
    List<Map> getDepartMap();

    @Sql("SELECT t.id, t.type_name name FROM fams_work_type t")
    List<Map> getWorkTypeMap();

    @Sql("SELECT t1.typecode code, t1.typename name FROM t_s_typegroup t, t_s_type t1 WHERE t.ID = t1.typegroupid AND t.typegroupcode = 'apply_type'")
    List<Map> getApplyTypeMap();

    @Sql("SELECT t.task_key keyname, t.task_state name FROM fams_work_node_info t")
    List<Map> getStakKeyMap();
    
    @Sql("SELECT count(t.id) FROM fams_work_approve t WHERE t.work_end_time<=t.date_end AND t.id = :id")
    @Arguments("id")
	Integer ifWorkDateExpire(String id);
    
    @Sql("SELECT t.date_end FROM fams_work_approve t WHERE t.id = :id")
    @Arguments("id")
    Date getDateEnd(String id);
    
    /**
     * 更新提交人
     * @param approveId
     * @param userid
     */
    @Sql("UPDATE fams_work_approve a,t_s_base_user t SET a.push_user_id = t.id, a.push_name = t.realname, a.push_by = t.username WHERE t.id = :userid AND a.id = :approveId")
    @Arguments({"approveId","userid"})
    Integer updatePushUserId(String approveId,String userid);
    
    /**
     * 	通过id获取作业类型名称
     * @param workTypeId
     * @return
     */
    @Sql("SELECT t.type_name FROM fams_work_type t WHERE t.id = :workTypeId")
    @Arguments("workTypeId")
    String getTypeNameById(String workTypeId);
    
    @Sql("SELECT * FROM fams_work_approve t WHERE t.create_name = :createName ORDER BY create_date DESC LIMIT :page,:pageSize")
    @Arguments({"createName","page","pageSize"})
    List<FamsWorkApproveEntity> getMyWorkByCreateMan(String createName,Integer page, Integer pageSize);
    
    @Sql("DELETE FROM fams_work_approve WHERE id = :approveId")
    @Arguments("approveId")
    Integer deleteByApproveId(String approveId);
    
    @Arguments({"createName","inStr","page","pageSize"})
    List<FamsWorkApproveEntity> getMyWorkByWorkState(String createName, String inStr,Integer page, Integer pageSize);
    
    @Arguments({"createName","inStr","searchInput", "page", "pageSize"})
    List<FamsWorkApproveEntity> getMyWorkByWorkStateAndSearchInput(String createName, String inStr, String searchInput, Integer page, Integer pageSize);
    
    @Sql("SELECT * FROM fams_work_approve t WHERE t.create_name = :createName AND t.task_key in ('s-apply','s-undo','s-in','s-out') ORDER BY create_date DESC LIMIT :page,:pageSize")
    @Arguments({"createName","page","pageSize"})
    List<FamsWorkApproveEntity> getmyWorkByStateEqApplyAndCreateName(String createName, Integer page, Integer pageSize);
    
    @Arguments({"createName","searchInput","page","pageSize"})
    List<FamsWorkApproveEntity> getmyWorkByStateEqApplyAndCreateNameAndSearchInput(String createName, String searchInput, Integer page, Integer pageSize);
    
}
