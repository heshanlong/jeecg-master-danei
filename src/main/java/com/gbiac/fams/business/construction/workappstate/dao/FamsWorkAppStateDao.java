package com.gbiac.fams.business.construction.workappstate.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import java.util.List;
import java.util.Map;

@MiniDao
public interface FamsWorkAppStateDao {
    @Sql("SELECT t2.task_key taskkey FROM fams_work_app_state t, fams_work_app_state_detail t1, fams_work_node_info t2 WHERE t.id = t1.state_id AND t1.node_id = t2.id AND t.role_code = :roleCode AND t.flag= :workFlag AND t.state = :workingState")
    @Arguments({"roleCode","workFlag","workingState"})
    List<Map> getNodesByRoleCodeAndState(String roleCode, Integer workFlag,Integer workingState);
}
