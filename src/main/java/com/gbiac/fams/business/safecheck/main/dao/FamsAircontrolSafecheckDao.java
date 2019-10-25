package com.gbiac.fams.business.safecheck.main.dao;

import com.gbiac.fams.business.safecheck.main.entity.FamsAircontrolSafecheckEntity;
import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamsAircontrolSafecheckDao {
    @Arguments("entity")
    List<FamsAircontrolSafecheckEntity> queryListByEntity(FamsAircontrolSafecheckEntity entity);


}
