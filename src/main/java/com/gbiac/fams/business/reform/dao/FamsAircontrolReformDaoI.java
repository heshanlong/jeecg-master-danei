package com.gbiac.fams.business.reform.dao;

import com.gbiac.fams.business.reform.entity.FamsAircontrolReformEntity;
import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamsAircontrolReformDaoI {
    @Arguments("entity")
    List<FamsAircontrolReformEntity> queryListByEntity(FamsAircontrolReformEntity entity);
}
