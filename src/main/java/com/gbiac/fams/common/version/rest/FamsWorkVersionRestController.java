package com.gbiac.fams.common.version.rest;

import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.MessageService;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.version.dao.FamsWorkVersionDao;
import com.gbiac.fams.common.version.entity.VersionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/famsWorkVersionRestController")
@Api(value = "famsWorkVersionRestController", description = "版本更新相关接口", tags = "FamsWorkVersionRestController")
public class FamsWorkVersionRestController {
    @Autowired
    protected MessageService messageService;
    @Autowired
    protected FamsWorkVersionDao famsWorkVersionDao;
    @Autowired
    protected FamsCommonService famsCommonService;


    /**
     * 版本升级
     * dto.version 版本
     * dto.platform 平台
     */
    @ResponseBody
    @ApiOperation(value="版本信息查询")
    @RequestMapping(value = "upgrade",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> upgrade(@RequestBody VersionDto dto){
        try{
            //版本
            String version=dto.getVersion();
            //平台
            String platform=dto.getPlatform();
            //手机的版本
            long oldVersion=convertVersionFromStringToLong(version);
            //获取该平台的最新版本信息
            Map map=famsWorkVersionDao.getLatestVersionInfoByPlatform(platform);
            if(map!=null){
                //服务器的版本
                long newVersion=convertVersionFromStringToLong(map.get("version").toString());
                if(newVersion>oldVersion){
                    return Result.success(map);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error(MessageConstant.OPTIONERROR);
    }

    /**
     * str 2 long
     * @param version
     * @return
     */
    private long convertVersionFromStringToLong(String version){
        if(version==null) return 0;
        String v=version;
        v=v.replace(".", "");
        return Long.parseLong(v);
    }
}

