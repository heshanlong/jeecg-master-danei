package com.gbiac.fams.business.tssystem.tsfuntion.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbiac.fams.business.anounce.service.FamsAnnounceNotifyServiceI;
import com.gbiac.fams.business.tssystem.tsfuntion.entity.TSFunction;
import com.gbiac.fams.business.tssystem.tsfuntion.service.ITsFuntion;
import com.gbiac.fams.restutil.SessionUserUtil;

@Scope("prototype")
@Api(value="FamsRulesregulations",description="APP功能菜单",tags="tsFuntionRestController")
@Controller
@RequestMapping("/tsFuntionRestController")
public class TsFuntionController {
	
	@Autowired
	private ITsFuntion iTsFuntion;
	@Autowired
	private SystemService systemService;
	
	List<TSFunction> listDaGe = new ArrayList<TSFunction>();
	
	@RequestMapping(value="/appGetMenu", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="APP功能菜单列表",produces="application/json",httpMethod="POST")
	public AjaxJson appGetMenu(HttpServletRequest request) {
		AjaxJson aj = new AjaxJson();
		try{
			//先查出第一层(功能菜单)
			List<TSFunction> funtionList = iTsFuntion.appGetMenu(SessionUserUtil.getCurrentUser().getUser().getId(),"8a0790e8693e12e801693e2cf6370001");
			tsfunctionList(funtionList);
			aj.setObj(funtionList);
		}catch(Exception e){
			aj.setSuccess(false);
		}
		return aj;
	}
	
	
	//递归循环获取子菜单
	private void tsfunctionList(List<TSFunction> tsList){
		for (int i = 0; i < tsList.size(); i++) {
			TSFunction tsf = tsList.get(i);
			//System.out.println(tsf.getFunctionName());
			List<TSFunction> tsList1 = iTsFuntion.appGetMenu(SessionUserUtil.getCurrentUser().getUser().getId(),tsf.getId());
			tsf.setTSFunctions(tsList1);
			tsfunctionList(tsList1);
		}
	}
	
}
