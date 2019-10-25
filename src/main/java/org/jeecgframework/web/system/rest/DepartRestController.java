package org.jeecgframework.web.system.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.entity.DepartEntity;
import org.jeecgframework.web.system.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "departRestController", description = "部门信息列表接口", tags = "departRestController")
@Controller
@RequestMapping("/departRestController")
public class DepartRestController {

	@Autowired
	private DepartService departService;
	
	@ApiOperation("获取部门列表信息")
	@RequestMapping(value = "getDepartInfo",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage<?> getDepartInfo(){
		
		//获取父列表
		List<DepartEntity> list1 = departService.getMainList();
		List<DepartEntity> childList = null;
		
		for(int i=0;i<list1.size();i++) {
			childList = departService.getChildList(list1.get(i).getId());
			if(childList.size()>0) {
				list1.get(i).setList(childList);
				for(int j=0;j<childList.size();j++) {
					List<DepartEntity> childList2 = departService.getChildList(childList.get(j).getId());
					if(childList2.size()>0) {
						childList.get(j).setList(childList2);
					}
				}
			}
		}
		
		Map<String, List<DepartEntity>> list = new HashMap<>();
		list.put("departList", list1);
		return Result.success(list);
		
	}
}
