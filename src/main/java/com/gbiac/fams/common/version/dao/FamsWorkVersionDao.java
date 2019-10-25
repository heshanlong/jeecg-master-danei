package com.gbiac.fams.common.version.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import java.util.Map;

@MiniDao
public interface FamsWorkVersionDao {
    @Sql("SELECT t.`name`, t.version, t.remark, t1.file_size size, t1.file_path url FROM fams_version t LEFT JOIN fams_common_file t1 ON t.id = t1.business_id WHERE t.platform = :platform ORDER BY t.version DESC LIMIT 0, 1")
    @Arguments("platform")
    Map getLatestVersionInfoByPlatform(String platform);
}
