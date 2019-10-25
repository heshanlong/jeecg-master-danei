package com.gbiac.fams.business.safecheck.detail.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FamsAircontrolSafecheckdetailDaoI {
    @Arguments({"checkId","checkProject"})
    List<Map> querySafecheckDetail(String checkId, String checkProject);

    @Sql("update fams_aircontrol_safecheckdetail  set result= :result  where id = :id")
    public void updateRes(@Param("id") String id,@Param("result") String result);
    
    @Sql("update fams_aircontrol_safecheckdetail set result=:result, arrayTime=:arrayTime, completeTime=:completeTime, dealResult=:dealResult where id=:id")
    public void updateDetail(@Param("id")String id, @Param("result")String result,@Param("arrayTime")String arrayTime, 
    		@Param("completeTime")String completeTime,@Param("dealResult")String dealResult);

}
