package com.gbiac.fams.business.construction.worksafe.dao;

import com.gbiac.fams.business.construction.worksafe.entity.FamsWorkSafePersonEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository("famsWorkSafeJpaDao")
public interface FamsWorkSafeJpaDao extends Specification<FamsWorkSafePersonEntity>{

}
