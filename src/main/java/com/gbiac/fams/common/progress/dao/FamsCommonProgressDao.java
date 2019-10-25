package com.gbiac.fams.common.progress.dao;

import com.gbiac.fams.common.progress.entity.FamsCommonProgressEntity;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

@MiniDao
public interface FamsCommonProgressDao {
    @Sql("SELECT * FROM ( SELECT * FROM fams_common_progress WHERE business_id = :bid ORDER BY option_time DESC LIMIT 0, 1 ) a WHERE a.state = :state")
    @Arguments({"bid","state"})
    FamsCommonProgressEntity getRecentEntityByBid(String bid,String state);
}
