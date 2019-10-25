package com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbiac.fams.business.airportrunway.areaconfig.entity.FamsAreaConfigEntity;
import com.gbiac.fams.business.airportrunway.areaconfig.service.FamsAreaConfigServiceI;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.AreaDto;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.entity.AreaEntity;
import com.gbiac.fams.business.airportrunway.attentioncraftsite.craftsiteuser.service.FamsAttentionCraftsiteUserServiceI;
import com.gbiac.fams.business.famsAttenntionArtea.entity.FamsAttentionAreaEntity;
import com.gbiac.fams.business.famsAttenntionArtea.service.FamsAttentionAreaServiceI;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.Util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 关注机位API接口，控制器层
 * @author sir
 *
 */
@Api(value="famsAttentionCraftsiteUserRestController",description="关注机位App",tags="famsAttentionCraftsiteUserRestController")
@Controller
@RequestMapping("/famsAttentionCraftsiteUserRestController")
public class FamsAttentionCraftsiteUserRestController {
	private static final Logger logger = LoggerFactory.getLogger(FamsAttentionCraftsiteUserRestController.class);

	private static Map<String,String> areaDate = null;
	
	@Autowired
	private UserService userService;
	@Autowired
	private FamsAttentionCraftsiteUserServiceI famsAttentionCraftsiteUserService;
	@Autowired
	protected FamsCommonService famsCommonService;
	@Autowired
	private FamsAttentionAreaServiceI famsAttentionAreaService;
	@Autowired
	private FamsAreaConfigServiceI famsAreaConfigService;
	@Autowired
	private SystemService systemService;
	
	/**
	 * 获取关注的机位区域数据（xxx区          xxx机位、xxx机位）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAttentionCraftsite", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "APP根据登陆用户的id获取关注的机位号")
	public ResponseMessage<?> getAttentionCraftsite(){
		
		String message = null;
		List<AreaEntity> seats = new ArrayList<>();
		try {
			//重新修改思路：页面加载的时候获取到所有的区域机位对应的数据，将数据封装到一个Map中或者其他格式的封装数据中
			
			this.initAreaDate();
			String[] seat_s = famsAttentionCraftsiteUserService.getAttentionArea(Util.getPcOrAppUserName());
			if(seat_s!=null && seat_s.length>0) {
			//判断机位是在哪个区域，将其进行添加
				for(String seat:seat_s) {
					String area = areaDate.get(seat);
					if(area!=null && !ifExistArea(seats, area, seat)) {
						List<String> l1 = new ArrayList<>();
						l1.add(seat);
						seats.add(new AreaEntity(area, l1));
					}
				}
			}
		} catch (Exception e) {
			message = "关注机位数据获取失败";
			logger.error("关注机位:"+message,e);
			return Result.error(message);
		}
		return Result.success(seats);
	}
	
	/**
	 * 获取机位将要停放的航班数据（xx区域、包含机位、发送时间、航班号、航线、机号、预达时间、状态）
	 * @param seat
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getFlightDate", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "APP根据机位号获取将要停放的航班数据")
	public ResponseMessage<?> getFlightDate(@RequestParam("craftsites") String craftsites){
		String message = null;
		List<Map<String,List<Map<String, Object>>>> seats = new ArrayList<>();
		try {
			String[] craftsite_s = craftsites.split(",");
			if(craftsite_s!=null && craftsite_s.length>0) {
				for(String craftsite: craftsite_s) {
					if(craftsite!=null || craftsite!="") {
						List<Map<String, Object>> res =  famsCommonService.getFlightDateByCraftsiteFromAomip(craftsite);
						Map<String,List<Map<String, Object>>> ele = new HashMap<String,List<Map<String, Object>>>();
						ele.put(craftsite, res);
						seats.add(ele);
					}
				}
			}
		} catch (Exception e) {
			message = "关注机位数据获取失败";
			logger.error("关注机位:"+message,e);
			return Result.error(message);
		}
		return Result.success(seats);
	}
	
	/**
	 * 获取所有的区域,默认获取用户已经关注了的区域,如果进行了查询,则显示用户没有关注的区域,如果查询的内容为空,则显示所有未关注的区域
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "获取用户关注的所有区域")
	@RequestMapping(value="/getAllAttentionArea", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> getAllAttentionArea(@RequestBody AreaDto dto){
		try {
			@SuppressWarnings("unchecked")
			List<FamsAttentionAreaEntity> lists = famsAttentionAreaService.getAllAttentionArea(dto.getSearchInput(), Util.getPcOrAppUserName());
			System.out.println(Util.getCurrentUserName());
			return Result.success(lists);
		} catch (Exception e) {
			return Result.error("获取失败!");
		}
	}
	
	@ResponseBody
	@ApiOperation(value="获取用户未关注的所有区域")
	@RequestMapping(value="/getAllNoAttentionArea", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> getAllNoAttentionArea(@RequestBody AreaDto dto){
		try {
			@SuppressWarnings("unchecked")
			List<FamsAreaConfigEntity> lists = famsAreaConfigService.getAllNoAttentionArea(dto.getSearchInput(),  Util.getPcOrAppUserName());
			return Result.success(lists);
		} catch (Exception e) {
			return Result.error("获取失败！");
		}
		
	}
	
	/**
	 * 添加区域关注
	 * @param id
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="添加关注区域")
	@RequestMapping(value="/addAttentionArea", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> addAttentionArea(@RequestParam("id")String id){
		try {
			FamsAttentionAreaEntity entity = new FamsAttentionAreaEntity();
			entity.setAttentionArea(id);
			//设置用户的角色信息
			entity.setRole(userService.getRoleNameByUserId(Util.getPcOrAppUserId()));
			//设置联系方式
			entity.setPhone(userService.getMobilePhoenByUserId(Util.getPcOrAppUserId()));
			System.err.println(userService.getMobilePhoenByUserId(Util.getPcOrAppUserId()));
			famsAttentionAreaService.save(entity);
			return Result.success("关注成功");
		} catch (Exception e) {
			return Result.success("关注失败");
		}
	}
	
	/**
	 * 删除用户关注的区域
	 * @param id
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="删除关注区域")
	@RequestMapping(value="/deleteAttentionArea", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage<?> deleteAttentionArea(@RequestParam("id")String id){
		try {
			FamsAttentionAreaEntity entity = systemService.getEntity(FamsAttentionAreaEntity.class, id);
			famsAttentionAreaService.delete(entity);
			return Result.success("取消成功！");
		} catch (Exception e) {
			return Result.error("取消失败!");
		}
	}
	
	
	/**
	 * 初始化机位数据
	 */
	private void initAreaDate() {
		//从区域配置表中获取数据
		List<FamsAreaConfigEntity> areas = famsAreaConfigService.loadAreaConfigDate();
		Map<String,String> areaDate = new HashMap<>();
		for(FamsAreaConfigEntity entity:areas) {
			String[] seats = entity.getCraftsite().split(",");
			if(seats!=null && seats.length>0) {
				for(String seat: seats) {
					if(seat!=null && !areaDate.containsKey(seat)) {
						areaDate.put(seat, entity.getArea());
					}
					
				}
			}
		}
		
		if(areaDate.size()>0) {
			Set<Entry<String, String>> sets = areaDate.entrySet();
			for(Entry<String, String> entry: sets) {
				System.err.println(entry.getKey()+"  --  "+entry.getValue());
			}
		}
		
		this.areaDate = areaDate;
	}
	
	/**
	 * 判断区域是否存在于指定集合中
	 * @param list
	 * @param areaName
	 * @param seatNumber
	 * @return
	 */
	private boolean ifExistArea(List<AreaEntity> list,String areaName,String seatNumber) {
		boolean exist = false;
		if(list.size()==0 || list == null) {
			return false;
		}
		for(int i=0;i<list.size();i++) {
			if(areaName.equals(list.get(i).getName())) {
				exist = true;
				//将seat添加到对应的seat集合中
				list.get(i).addSeat(seatNumber);
				break;
			}
		}
		return exist;
		
	}
	
	
}
