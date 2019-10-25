package com.gbiac.fams.business.construction.workvindicatecheck.rest;

import com.gbiac.fams.business.construction.workapprove.entity.ApplyDto;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveServiceI;
import com.gbiac.fams.business.construction.workcheck.entity.CheckDTO;
import com.gbiac.fams.business.construction.workcheck.service.FamsWorkCheckServiceI;
import com.gbiac.fams.business.construction.workcheckitem.entity.FamsWorkCheckItemEntity;
import com.gbiac.fams.business.construction.workvindicatecheck.entity.FamsWorkVindicatecheckEntity;
import com.gbiac.fams.business.construction.workvindicatecheck.service.FamsWorkVindicatecheckServiceI;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.MessageService;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/famsWorkVindicateCheckRestController")
@Api(value = "famsWorkVindicateCheckRestController", description = "施工维护检查相关接口", tags = "FamsWorkVindicateCheckRestController")
public class FamsWorkVindicateCheckRestController {
    @Autowired
    private FamsWorkCheckServiceI famsWorkCheckService;
    @Autowired
    private FamsWorkApproveServiceI famsWorkApproveService;
    @Autowired
    private FamsWorkVindicatecheckServiceI famsWorkVindicatecheckService;
    @Autowired
    private FamsCommonService famsCommonService;
    @Autowired
    protected MessageService messageService;
    /**
     * 获取施工维护检查内容
     * @param dto.workingItemID 施工申请id
     */
    @ResponseBody
    @ApiOperation(value="获取施工维护检查内容")
    @RequestMapping(value = "getCheckVindicateData",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> getCheckVindicateData(@RequestBody ApplyDto dto){
        try{
            //查询分类表中的数据("不停航施工现场联合检查登记表")
            Map map=famsWorkCheckService.getCategory("A07%");
            //通过申请id获取该申请的相关信息
            Map approveInfo=famsWorkApproveService.getApproveInfo(dto.getWorkingItemID());
            map.put("approveInfo",approveInfo);
            map.put("currentTime",new Date());
            return Result.success(map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
    }

    /**
     * 保存维护检查记录
     * @param params 检查记录
     * @return
     */
    @ResponseBody
    @ApiOperation(value="保存维护检查记录")
    @RequestMapping(value = "saveVindicateCheck",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> saveVindicateCheck(@RequestBody Map<String,Object> params){
        try{
            //组装对象
            FamsWorkVindicatecheckEntity famsVindicateWorkCheck=new FamsWorkVindicatecheckEntity(params);
            //获取具体检查小项的检查结果
            Map itemValues=this.getCheckItems((List<Map>)params.get("item"));
            //获取附件id集合
            String files = params.get("files").toString();
            famsWorkVindicatecheckService.save(famsVindicateWorkCheck,itemValues,files);
            new Thread(){
                @Override
                public void run() {
                    try{
                        //推送
                        FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsVindicateWorkCheck.getBid());
                        //获取角色下的所有人员id
                        List<TSBaseUser> users=famsCommonService.getApplyUser(entity.getCreateUserId());
                        //发送系统消息
                        messageService.pushOnlyPCMessage("vindicateCheck",famsVindicateWorkCheck.getId(),"您有新的"+entity.getWorkName()+"检查记录",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"添加了一条维护抽查记录，检查项目："+entity.getWorkName(),Util.getPcOrAppUserName(),users,null,null);
                        //推送消息
                        famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"vindicateCheck",entity.getId(),users,"您有新的"+entity.getWorkName()+"检查记录",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"添加了一条维护抽查记录，检查项目："+entity.getWorkName(),null);
                    }catch (Exception e){
                    }
                }
            }.run();
            return Result.success();
        }catch(BusinessException e){
            return Result.error(e.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
    }

    /**
     * 获取维护抽查内容
     * @param dto.checkId 检查记录id
     */
    @ResponseBody
    @ApiOperation(value="获取维护抽查内容")
    @RequestMapping(value = "getVindicateCheckDetail",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> getVindicateCheckDetail(@RequestBody CheckDTO dto){
        try{
            Map m=new HashMap();
            FamsWorkVindicatecheckEntity famsWorkVindicatecheck = famsWorkVindicatecheckService.getEntity(FamsWorkVindicatecheckEntity.class, dto.getCheckId());
            m.put("check", famsWorkVindicatecheck);
            //查询分类表中的数据("不停航施工现场联合检查登记表")
            Map map=famsWorkCheckService.getCategory("A07%",famsWorkVindicatecheck.getId());
            m.put("detail",map);
            //查询检查小项结果
            List<FamsWorkCheckItemEntity> entities = famsCommonService.getEntitiesByProperty(FamsWorkCheckItemEntity.class, "checkId".split(","), famsWorkVindicatecheck.getId().split(","));
            Map checkItems=new HashMap();
            for(FamsWorkCheckItemEntity f:entities){
                checkItems.put(f.getCategoryId(),f.getCheckResult());
            }
            m.put("checkItems",checkItems);
            //获取附件id集合
            List<FamsCommonFileEntity> files = famsCommonService.getEntitiesByProperty(FamsCommonFileEntity.class,"businessId".split(","),famsWorkVindicatecheck.getId().split(","));
            m.put("files", files);
            return Result.success(m);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
    }

    /**
     * 获取具体检查小项的检查结果
     * @param params
     * @return
     */
    private Map getCheckItems(List<Map> params) {
        Map result=new HashMap();
        for(Map m:params){
            result.put(m.get("name"),m.get("value"));
        }
        return result;
    }
}

