package com.gbiac.fams.business.main.index.controller;

import com.gbiac.fams.business.main.index.service.IndexServiceI;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**   
 * @Title: Controller  
 * @Description: fams_unsafeevent_aircraftleakage
 * @author onlineGenerator
 * @date 2019-02-14 15:27:42
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/indexController")
public class IndexController extends BaseController {
	@Autowired
	private IndexServiceI indexService;
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	/**
	 * 首页施工数据接口
	 * @return
	 */
	@RequestMapping(params = "workInfo")
	@ResponseBody
	public AjaxJson workInfo() {
		AjaxJson ajaxJson=new AjaxJson();
		try{
			//查询首页的施工数据
			List list=indexService.getWorkInfo();
			ajaxJson.setObj(list);
		}catch (Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(e.getMessage());
		}
		return ajaxJson;
	}
	
}
