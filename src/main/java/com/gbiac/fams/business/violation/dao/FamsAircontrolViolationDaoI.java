package com.gbiac.fams.business.violation.dao;

import com.gbiac.fams.business.violation.entity.FamsAircontrolViolationEntity;
import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FamsAircontrolViolationDaoI {
    @Arguments("entity")
    List<FamsAircontrolViolationEntity> queryListByEntity(FamsAircontrolViolationEntity entity);

}
