package com.gbiac.fams.business.construction.workunioncheck.rest;

import com.gbiac.fams.business.construction.workapprove.entity.ApplyDto;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveServiceI;
import com.gbiac.fams.business.construction.workcheck.entity.CheckDTO;
import com.gbiac.fams.business.construction.workcheck.service.FamsWorkCheckServiceI;
import com.gbiac.fams.business.construction.workcheckitem.entity.FamsWorkCheckItemEntity;
import com.gbiac.fams.business.construction.workunioncheck.entity.FamsWorkUnioncheckEntity;
import com.gbiac.fams.business.construction.workunioncheck.service.FamsWorkUnioncheckServiceI;
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
@RequestMapping(value = "/famsWorkUnionCheckRestController")
@Api(value = "famsWorkUnionCheckRestController", description = "施工联合检查相关接口", tags = "FamsWorkUnionCheckRestController")
public class FamsWorkUnionCheckRestController {
    @Autowired
    private FamsWorkCheckServiceI famsWorkCheckService;
    @Autowired
    private FamsWorkUnioncheckServiceI famsWorkUnioncheckServiceI;
    @Autowired
    private FamsWorkApproveServiceI famsWorkApproveService;
    @Autowired
    private FamsWorkUnioncheckServiceI famsWorkUnioncheckService;
    @Autowired
    protected FamsCommonService famsCommonService;
    @Autowired
    protected MessageService messageService;
    /**
     * 获取联合检查内容
     * @param dto.workingItemID 施工申请id
     */
    @ResponseBody
    @ApiOperation(value="获取联合检查内容")
    @RequestMapping(value = "getCheckTogetherData",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> getCheckTogetherData(@RequestBody ApplyDto dto){
        try{
            //查询分类表中的数据("不停航施工现场联合检查登记表")
            Map map=famsWorkCheckService.getCategory("A06%");
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
     * 保存联合检查记录
     * @param params 检查记录
     * @return
     */
    @ResponseBody
    @ApiOperation(value="保存联合检查记录")
    @RequestMapping(value = "saveUnionCheck",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> saveUnionCheck(@RequestBody Map<String,Object> params){
        try{
            //组装对象
            FamsWorkUnioncheckEntity famsWorkUnionCheck=new FamsWorkUnioncheckEntity(params);
            //获取具体检查小项的检查结果
            Map itemValues=this.getCheckItems((List<Map>)params.get("item"));
            //获取附件id集合
            String files = params.get("files").toString();
            famsWorkUnioncheckServiceI.save(famsWorkUnionCheck,itemValues,files);
            new Thread(){
                @Override
                public void run() {
                    try{
                        //推送
                        FamsWorkApproveEntity entity=famsCommonService.get(FamsWorkApproveEntity.class,famsWorkUnionCheck.getBid());
                        //获取角色下的所有人员id
                        List<TSBaseUser> users=famsCommonService.getApplyUser(entity.getCreateUserId());
                        //发送系统消息
                        messageService.pushOnlyPCMessage("unionCheck",famsWorkUnionCheck.getId(),"您有新的"+entity.getWorkName()+"检查记录",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"添加了一条联合检查记录，检查项目："+entity.getWorkName(),Util.getPcOrAppUserName(),users,null,null);
                        //推送消息
                        famsCommonService.sendMessageByUsers(Util.getPcOrAppUserId(),"unionCheck",entity.getId(),users,"您有新的"+entity.getWorkName()+"检查记录",Util.getPcOrAppUserDepartName()+"-"+Util.getPcOrAppUserName()+"添加了一条联合检查记录，检查项目："+entity.getWorkName(),null);
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
     * 获取联合检查内容
     * @param dto.checkId 检查记录id
     */
    @ResponseBody
    @ApiOperation(value="获取联合检查内容")
    @RequestMapping(value = "getUnionCheckDetail",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> getUnionCheckDetail(@RequestBody CheckDTO dto){
        try{
            Map m=new HashMap();
            FamsWorkUnioncheckEntity famsWorkUnioncheck = famsWorkUnioncheckService.getEntity(FamsWorkUnioncheckEntity.class, dto.getCheckId());
            m.put("check", famsWorkUnioncheck);
            //查询分类表中的数据("不停航施工现场联合检查登记表")
            Map map=famsWorkCheckService.getCategory("A06%",famsWorkUnioncheck.getId());
            m.put("detail",map);
            //查询检查小项结果
            List<FamsWorkCheckItemEntity> entities = famsCommonService.getEntitiesByProperty(FamsWorkCheckItemEntity.class, "checkId".split(","), famsWorkUnioncheck.getId().split(","));
            Map checkItems=new HashMap();
            for(FamsWorkCheckItemEntity f:entities){
                checkItems.put(f.getCategoryId(),f.getCheckResult());
            }
            m.put("checkItems",checkItems);
            //获取附件id集合
            List<FamsCommonFileEntity> files = famsCommonService.getEntitiesByProperty(FamsCommonFileEntity.class,"businessId".split(","),famsWorkUnioncheck.getId().split(","));
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

