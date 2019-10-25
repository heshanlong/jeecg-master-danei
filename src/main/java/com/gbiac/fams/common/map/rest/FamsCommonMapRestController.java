package com.gbiac.fams.common.map.rest;

import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.map.entity.TimeDTO;
import com.gbiac.fams.common.map.service.FamsCommonMapServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Title: Controller
 * @Description: 通用地图标注
 * @author 龚道海
 * @date 2019-01-09 11:34:18
 * @version V1.0
 *
 */
@Controller
@RequestMapping(value = "/famsCommonMapRestController")
@Api(value = "famsCommonMapRestController", description = "地图相关接口", tags = "FamsCommonMapRestController")
public class FamsCommonMapRestController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(FamsCommonMapRestController.class);

    @Autowired
    private FamsCommonMapServiceI famsCommonMapService;

    /**
     * 根据施工时间获取该时间当天的所有施工地图信息
     * @param dto.time 时间戳 eg:1344887103000
     * @return
     */
    @ResponseBody
    @ApiOperation(value="根据时间获取施工地图信息")
    @RequestMapping(value = "getMaps",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage<?> getMaps(@RequestBody TimeDTO dto){
        try {
            String time = dto.getTime();
            if(time!=null){
                time=time.length()>10?time.substring(0,10):time;
                //根据施工时间获取该时间当天的所有施工地图信息集合
                List<Map> list=famsCommonMapService.getMaps(time);
                return Result.success(list);
            }else{
                return Result.error(MessageConstant.OPTIONERROR);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error(MessageConstant.OPTIONERROR);
        }
    }
}
