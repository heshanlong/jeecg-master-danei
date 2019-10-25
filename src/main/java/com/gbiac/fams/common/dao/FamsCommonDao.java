package com.gbiac.fams.common.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;

import java.util.List;
import java.util.Map;

@MiniDao
public interface FamsCommonDao {
    @Arguments("userIds")
    List<Map> getIdAndImeis(List userIds);

    /**
     * 邓正辉，迟大路，查找Aomip的基础信息
     * @param keyword
     * @param dateStart
     * @param dateEnd
     * @return
     */
    List<Map<String,Object>> loadAomipFlight(@Param("keyword") String keyword, @Param("dateStart")String dateStart,@Param("dateEnd") String dateEnd);

    /**
     * 	根据机位号获取Aomip的基础信息
     * @param craftsite
     * @param dateStart
     * @param dateEnd
     * @return
     */
    List<Map<String,Object>> getAomipFlightByCraftsite(@Param("craftsite") String craftsite, @Param("dateStart")String dateStart,@Param("dateEnd") String dateEnd);
    
    
    /**
     * 邓正辉，迟大路，根据loadAomipFlight找出的AIRL_FLID循环查找查找Aomip的航段
     * @param AIRL_FLID
     * @return
     */
    List<Map<String,Object>> loadAomipFlightairl(@Param("airlFlid")String airlFlid);


    @Sql("SELECT t.* FROM t_s_base_user t, t_s_role_user t1, t_s_role t2, t_s_role_function t3, t_s_function t4 WHERE t.id = t1.userid AND t1.roleid = t2.id AND t2.id = t3.roleid AND t3.functionid = t4.ID AND t4.functionname = '作业审批' AND t2.rolecode != 'admin' GROUP BY t.ID")
    List<Map> getCanApproveIds();
    
    /**
     * 获取申请用户部门下有审核权限的用户的id集合
     * @param ordId
     * @return
     */
    @Sql("SELECT t.*  FROM t_s_base_user t, t_s_role_user t1, t_s_role t2, t_s_role_function t3, t_s_function t4,t_s_user_org t5 WHERE t.id = t1.userid AND t1.roleid = t2.id AND t2.id = t3.roleid AND t3.functionid = t4.ID AND t4.functionname = '部门作业审批' AND t2.rolecode != 'admin' AND t5.user_id = t.id AND t5.org_id = :orgId GROUP BY t.ID")
    @Arguments("orgId")
    List<Map> getCanApproveIdsByOrgId(String orgId);
    
    /**
     * 获取申请用户部门下有审核权限的用户的id集合
     * @param sysOrgCode
     * @return
     */
    @Sql("SELECT t.*  FROM t_s_base_user t, t_s_role_user t1, t_s_role t2, t_s_role_function t3, t_s_function t4,t_s_user_org t5, t_s_depart t6 WHERE t.id = t1.userid AND t1.roleid = t2.id AND t2.id = t3.roleid AND t3.functionid = t4.ID AND t4.functionname = '部门作业审批' AND t2.rolecode != 'admin' AND t5.user_id = t.id AND t5.org_id = t6.id AND t6.org_code = :orgCode GROUP BY t.ID")
    @Arguments("orgCode")
    List<Map> getCanApproveIdsByOrgCode(String orgCode);
    
    /**
     * 获取运行控制部下审核人的用户id集合
     * @return
     */
    @Sql("SELECT t.*  FROM t_s_base_user t, t_s_role_user t1, t_s_role t2, t_s_role_function t3, t_s_function t4,t_s_user_org t5 WHERE t.id = t1.userid AND t1.roleid = t2.id AND t2.id = t3.roleid AND t3.functionid = t4.ID AND t4.functionname = '作业审批' AND t2.rolecode != 'admin' AND t5.user_id = t.id AND t5.org_id = '8a0790ec692289be016922ab955c0056' GROUP BY t.ID")
    List<Map> getCanApproveIdsByRunPartOrgId();

    //@Sql("SELECT t.user_id id FROM t_s_user_org t WHERE t.org_id IN ( SELECT t1.org_id FROM t_s_user_org t1 WHERE t1.user_id = :userId ) AND t.user_id IN ( SELECT t3.userid FROM t_s_role t2, t_s_role_user t3 WHERE t2.id = t3.roleid AND t2.rolecode = :roleCode )")
    @Sql("SELECT t.* FROM t_s_base_user t, t_s_role_user t1, t_s_role t2, t_s_role_function t3, t_s_function t4 WHERE t.id = t1.userid AND t1.roleid = t2.id AND t2.id = t3.roleid AND t3.functionid = t4.ID AND t4.functionname = '作业申请' AND t2.rolecode != 'admin' AND t.departid IN ( SELECT t5.org_id FROM t_s_user_org t5 WHERE t5.user_id = :userId )")
    @Arguments("userId")
    List<TSBaseUser> getApplyUsers(String userId);

    @Sql("SELECT t.* FROM t_s_base_user t WHERE t.id= :userId")
    @Arguments("userId")
    List<TSBaseUser> getApplyUser(String userId);

    /**
     * 根据用户id集合获取用户对象
     * @param userIds
     * @return
     */
    @Arguments("userIds")
    List<TSBaseUser> getUsersByIds(List<String> userIds);
    
    @Sql("SELECT t.ID FROM t_s_base_user t,t_s_role_user t1,t_s_role t2,t_s_role_function t3,t_s_function t4 WHERE t.id = t1.userid AND t1.roleid = t2.id AND t2.id = t3.roleid AND t3.functionid = t4.ID AND t4.functionname = '作业审批' AND t2.rolecode = 'admin'")
	List<String> getAdminUsers();
}

