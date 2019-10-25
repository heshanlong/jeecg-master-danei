package com.gbiac.fams.business.rulesregulations.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbiac.fams.business.rulesregulations.entity.FamsRulesregulationsEntity;
import com.gbiac.fams.business.rulesregulations.entity.dto.RulesregulationsAppDTO;
import com.gbiac.fams.business.rulesregulations.entity.dto.RulesregulationsDTO;
import com.gbiac.fams.business.rulesregulations.service.FamsRulesregulationsServiceI;
import com.gbiac.fams.business.tssystem.tsfuntion.entity.TSFunction;
import com.gbiac.fams.business.violation.entity.FamsAircontrolViolationEntity;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.restutil.SessionUserUtil;

@Api(value="FamsRulesregulations",description="规章制度",tags="famsRulesregulationsRestController")
@Controller
@RequestMapping("/famsRulesregulationsRestController")
public class FamsRulesregulationsRestController {

	@Autowired
	private FamsRulesregulationsServiceI famsRulesregulationsService;
	
	private Integer fileCount = 0;

	//获取主目录
	@RequestMapping(value="/pidNullList",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="规章制度主目录列表信息")
	public ResponseMessage<List<FamsRulesregulationsEntity>> pidNullList(@RequestBody RulesregulationsDTO rulesregulationsDto) {
		List<FamsRulesregulationsEntity> listRulesregulations = null;
		try{
			//if(rulesregulationsDto.getPageSize() > Globals.MAX_PAGESIZE){
			//	return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
			//}
			listRulesregulations = this.famsRulesregulationsService.getPidNullList(rulesregulationsDto.getPageNo(),rulesregulationsDto.getPageSize());
			for (int i = 0; i < listRulesregulations.size(); i++) {
				FamsRulesregulationsEntity element = listRulesregulations.get(i);
				if(element.getRulesType()==1){
					FamsCommonFileEntity fcf = famsRulesregulationsService.get(FamsCommonFileEntity.class,element.getRulesPathUrl());
					if(fcf!=null){
						element.setFamsCommonFileEntity(fcf);
					}
				}
			}
		}catch(Exception e){
			
		}
		return Result.success(listRulesregulations);
	}
	

	//根据PID获取子目录与文件
	@RequestMapping(value="/byPidList",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="根据父ID获取列表信息")
	public ResponseMessage<?> byPidList(@RequestBody RulesregulationsDTO rulesregulationsDto) {
		List<FamsRulesregulationsEntity> listRulesregulations = null;
		try{
			String roleCode = SessionUserUtil.getCurrentUser().getRole().getRoleCode();
			String orgCode2 = SessionUserUtil.getCurrentUser().getUser().getCurrentDepart().getOrgCode();
			String orgCode = "xxx";
			if(orgCode2.startsWith("A39A15")){
				orgCode = "A39A15";
			}
			listRulesregulations = this.famsRulesregulationsService.getByPidList(rulesregulationsDto.getId(),roleCode,orgCode);
			for (int i = 0; i < listRulesregulations.size(); i++) {
				FamsRulesregulationsEntity element = listRulesregulations.get(i);
				if(element.getRulesType()==1){
					FamsCommonFileEntity fcf = famsRulesregulationsService.get(FamsCommonFileEntity.class,element.getRulesPathUrl());
					if(fcf!=null){
						element.setFamsCommonFileEntity(fcf);
					}
				}
			}
		}catch(Exception e){
			
		}
	    return Result.success(listRulesregulations);
	}
	
	
	//根据PID获取子目录与文件
	@RequestMapping(value="/retrieveList",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="检索信息")
	public ResponseMessage<?> retrieveList(@RequestBody RulesregulationsAppDTO rulesregulationsAppDTO){
		List<FamsRulesregulationsEntity> retrieveList = null;
		try{
			
			String roleCode = SessionUserUtil.getCurrentUser().getRole().getRoleCode();
			String orgCode2 = SessionUserUtil.getCurrentUser().getUser().getCurrentDepart().getOrgCode();
			String orgCode = "xxx";
			if(orgCode2.startsWith("A39A15")){
				orgCode = "A39A15";
			}
			
			retrieveList = famsRulesregulationsService.retrieveList(rulesregulationsAppDTO,roleCode,orgCode);
			
			for (int i = 0; i < retrieveList.size(); i++) {
				FamsRulesregulationsEntity element = retrieveList.get(i);
				if(element.getRulesType()==1){
					FamsCommonFileEntity fcf = famsRulesregulationsService.get(FamsCommonFileEntity.class,element.getRulesPathUrl());
					if(fcf!=null){
						element.setFamsCommonFileEntity(fcf);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Result.success(retrieveList);
	}

}
