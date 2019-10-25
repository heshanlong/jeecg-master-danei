package com.gbiac.fams.business.construction.dao;

import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import java.util.List;
import java.util.Map;

@MiniDao
public interface FamsWorkDao {
    @Arguments("procinstId")
    @Sql("SELECT t.ID_ id FROM act_ru_task t WHERE t.EXECUTION_ID_ = :procinstId")
    String getTaskIdByProId(String procinstId);

    @Arguments("approveId")
    @Sql("SELECT t.TASK_DEF_KEY_ FROM act_ru_task t, fams_work_approve t1 WHERE t.EXECUTION_ID_ = t1.procinst_id AND t1.id = :approveId")
    String getTaskKeyByBusId(String approveId);

    @Sql("SELECT t.* FROM fams_work_approve t, act_ru_task t1 WHERE t.procinst_id = t1.EXECUTION_ID_ AND t1.TASK_DEF_KEY_ = 's-out' AND t.is_continue_work = 'Y'")
    List<FamsWorkApproveEntity> getFinishProIds();
    
    @Arguments("approveId")
    Map getWorkApprove(String approveId);
    
    @Sql("SELECT t.* FROM fams_work_approve t, act_ru_task t1 WHERE t.procinst_id = t1.EXECUTION_ID_ AND t1.TASK_DEF_KEY_ = 's-out'")
    List<FamsWorkApproveEntity> getNodeIdEqS_OUTProIds();
}
