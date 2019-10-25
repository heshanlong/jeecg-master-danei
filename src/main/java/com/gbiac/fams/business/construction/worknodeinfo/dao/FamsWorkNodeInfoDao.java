package com.gbiac.fams.business.construction.worknodeinfo.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

@MiniDao
public interface FamsWorkNodeInfoDao {
    @Sql("SELECT t.task_state FROM fams_work_node_info t WHERE t.task_key = :taskKey limit 0,1")
    @Arguments("taskKey")
    String getStateByTaskKey(String taskKey);
}
