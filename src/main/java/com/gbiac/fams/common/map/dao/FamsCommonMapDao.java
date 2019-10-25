package com.gbiac.fams.common.map.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import java.util.List;
import java.util.Map;

@MiniDao
public interface FamsCommonMapDao {
    @Sql("SELECT t.id, t.work_name, t.work_area, t.work_start_time, t.work_end_time, t1.map_points, t.is_affect_flight FROM fams_work_approve t, fams_common_map t1 WHERE t.id = t1.business_id AND FROM_UNIXTIME(:time, '%Y-%m-%d') IN ( DATE_FORMAT( t.work_start_time, '%Y-%m-%d' ), DATE_FORMAT(t.work_end_time, '%Y-%m-%d')) AND t1.map_points != '' AND t.task_key IN('s-in','y-approve-in','s-out','y-approve-out','end') ORDER BY t.work_start_time")
    @Arguments("time")
    List<Map> getMaps(String time);
}
