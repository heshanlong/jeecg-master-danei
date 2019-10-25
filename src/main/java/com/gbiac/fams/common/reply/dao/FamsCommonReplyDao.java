package com.gbiac.fams.common.reply.dao;

import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FamsCommonReplyDao {
    @Arguments("entity")
    List<FamsCommonReplyEntity> queryListByEntity(FamsCommonReplyEntity entity);

    /**
     * 给首页查询整改单和违章告知单定制的查询
     * @param entity
     * @return
     */
    @Arguments("entity")
    List<Map> queryListByEntityForIndex(FamsCommonReplyEntity entity);

}
