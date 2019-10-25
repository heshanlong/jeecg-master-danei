package org.jeecgframework.jwt.web;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.model.LoginSuccessDTO;
import org.jeecgframework.jwt.service.TokenManager;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.pojo.base.*;
import org.jeecgframework.web.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获取和删除token的请求地址， 
 * 在Restful设计中其实就对应着登录和退出登录的资源映射
 * 
 * @author scott
 * @date 2015/7/30.
 */
@Api(value = "token", description = "鉴权token接口", tags = "tokenAPI")
@Controller
@RequestMapping("/tokens")
public class TokenController {
	private static final Logger logger = LoggerFactory.getLogger(TokenController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private TokenManager tokenManager;

	@ApiOperation(value = "获取TOKEN")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password,@RequestParam String imei) {
		logger.info("获取TOKEN[{}]" , username);
		// 验证
		if (StringUtils.isEmpty(username)) {
			AjaxJson ajJson = new AjaxJson();
			ajJson.setMsg("用户账号不能为空!");
			ajJson.setSuccess(false);
			return new ResponseEntity(ajJson, HttpStatus.OK);
		}
		// 验证
		if (StringUtils.isEmpty(username)) {
			AjaxJson ajJson = new AjaxJson();
			ajJson.setMsg("用户密码不能为空!");
			ajJson.setSuccess(false);
			return new ResponseEntity(ajJson, HttpStatus.OK);
		}
		Assert.notNull(username, "username can not be empty");
		Assert.notNull(password, "password can not be empty");

		TSUser user = userService.checkUserExits(username, password);
		TSRole role = new TSRole();
		if (user == null) {
			// 提示用户名或密码错误
			logger.info("获取TOKEN,户账号密码错误[{}]" , username);
			AjaxJson ajJson = new AjaxJson();
			ajJson.setMsg("用户账号密码错误!");
			ajJson.setSuccess(false);
			return new ResponseEntity(ajJson, HttpStatus.OK);
		}else{
			//获取机构
			String departSql="select org_id from t_s_user_org  t where t.user_id='"+user.getId()+"'";
			Map map=userService.findOneForJdbc(departSql);

			user.getCurrentDepart().getId();
			TSDepart org = userService.getEntity(TSDepart.class,map.get("org_id").toString());
			user.setCurrentDepart(org);
			
			//获取角色
			List<TSRoleUser> roleUser = userService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			if(roleUser!=null){
				role = userService.get(TSRole.class, roleUser.get(0).getTSRole().getId());
			}
			
			//更新IMEI
			String sql = "update t_s_user_detail set imei = '"+imei+"' where id = '"+user.getId()+"'";
			userService.updateBySqlString(sql);
			
		}
		// 生成一个token，保存用户登录状态
		//String token = tokenManager.createToken(user);
		
		// 生成一个token，保存用户登录状态
        String token = tokenManager.createToken(user,role);
        LoginSuccessDTO dto = new LoginSuccessDTO(token, user,role);
        AjaxJson ajJson = new AjaxJson();
        ajJson.setObj(dto);

		return new ResponseEntity(ajJson, HttpStatus.OK);
	}

	@ApiOperation(value = "销毁TOKEN")
	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseMessage<?> logout(@ApiParam(name = "username", value = "用户账号", required = true) @PathVariable("username") String username) {
		logger.info("deleteToken[{}]" , username);
		// 验证
		if (StringUtils.isEmpty(username)) {
			return Result.error("用户账号，不能为空!");
		}
		try {
			tokenManager.deleteToken(username);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("销毁TOKEN失败");
		}
		return Result.success();
	}

	@ApiOperation(value = "销毁TOKEN，销毁IMEI")
	@RequestMapping(value = "loginout/{username}/{userid}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage<?> loginout(@ApiParam(name = "username", value = "用户账号", required = true) @PathVariable("username") String username,
									 @ApiParam(name = "userid", value = "用户id", required = true) @PathVariable("userid") String userid) {
		logger.info("deleteToken[{}]" , username);
		// 验证
		if (StringUtils.isEmpty(username)) {
			return Result.error("用户账号，不能为空!");
		}
		try {

			tokenManager.deleteToken(username);
			//邓正辉
			if(StringUtil.isNotEmpty(userid)){
				TSUuserDetail tsUuserDetail=userService.getEntity(TSUuserDetail.class,userid);
				if(tsUuserDetail!=null
						&&
						StringUtil.isNotEmpty(tsUuserDetail.getImei())){
					//置空退出用户的imei（所有）
					String sql = "update t_s_user_detail set imei = '' where imei = '"+tsUuserDetail.getImei()+"'";
					userService.updateBySqlString(sql);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("销毁TOKEN失败");
		}
		return Result.success();
	}

}
