package com.gbiac.fams.business.construction.workvindicatecheck.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import java.util.Map;

@MiniDao
public interface FamsWorkVindicateCheckDao {
    @Sql("SELECT t.id, t.check_time, ( SELECT departname FROM t_s_depart WHERE org_code = t.sys_org_code ) departname, t.create_name username, t.check_result_note result, t.check_repair_note note FROM fams_work_vindicatecheck t WHERE t.bid = :id LIMIT 0, 1")
    @Arguments("id")
    Map getCheckInfo(String id);

    @Sql("SELECT t.id FROM fams_work_vindicatecheck t WHERE t.bid = :applyId limit 0,1")
    @Arguments("applyId")
    String getWorkVindicateCheckId(String applyId);
}
