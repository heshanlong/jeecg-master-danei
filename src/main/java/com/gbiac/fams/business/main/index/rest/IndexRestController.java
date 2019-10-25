package com.gbiac.fams.business.main.index.rest;

import com.gbiac.fams.business.airportrunway.mark.entity.TSTypeCode;
import com.gbiac.fams.business.anounce.service.FamsAnnounceNotifyServiceI;
import com.gbiac.fams.business.construction.workapprove.dao.FamsWorkApproveDao;
import com.gbiac.fams.business.construction.workapprove.service.FamsWorkApproveServiceI;
import com.gbiac.fams.business.construction.workrole.service.FamsWorkRoleServiceI;
import com.gbiac.fams.business.main.index.entity.IndexCakeDto;
import com.gbiac.fams.business.main.index.entity.IndexDto;
import com.gbiac.fams.business.main.index.entity.MainDto;
import com.gbiac.fams.business.main.index.service.IndexServiceI;
import com.gbiac.fams.business.reform.entity.FamsAircontrolReformEntity;
import com.gbiac.fams.business.reform.service.FamsAircontrolReformServiceI;
import com.gbiac.fams.business.safecheck.main.entity.FamsAircontrolSafecheckEntity;
import com.gbiac.fams.business.safecheck.main.service.FamsAircontrolSafecheckServiceI;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.entity.FamsUnsafeeventAircraftleakageEntity;
import com.gbiac.fams.business.unsafeevent.aircraftleakage.service.FamsUnsafeeventAircraftleakageServiceI;
import com.gbiac.fams.business.unsafeevent.birdstrike.entity.FamsUnsafeeventBirdstrikeEntity;
import com.gbiac.fams.business.unsafeevent.birdstrike.service.FamsUnsafeeventBirdstrikeServiceI;
import com.gbiac.fams.business.unsafeevent.tiredamage.entity.FamsUnsafeeventTiredamageEntity;
import com.gbiac.fams.business.unsafeevent.tiredamage.service.FamsUnsafeeventTiredamageServiceI;
import com.gbiac.fams.business.violation.entity.FamsAircontrolViolationEntity;
import com.gbiac.fams.business.violation.service.FamsAircontrolViolationServiceI;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
import com.gbiac.fams.restutil.SessionUserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
@Api(value = "indexRestController", description = "首页展示App", tags = "indexRestController")
@Controller
@RequestMapping("/indexRestController")
public class IndexRestController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(IndexRestController.class);

	@Autowired
	private IndexServiceI indexRestService;
	@Autowired
	private SystemService systemService;
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private FamsAircontrolReformServiceI famsAircontrolReformServiceI;
	@Autowired
	private FamsAircontrolViolationServiceI famsAircontrolViolationServiceI;
	@Autowired
	private FamsAircontrolSafecheckServiceI famsAircontrolSafecheckServiceI;

	@Autowired
	private FamsUnsafeeventAircraftleakageServiceI famsUnsafeeventAircraftleakageService;
	@Autowired
	private FamsUnsafeeventBirdstrikeServiceI famsUnsafeeventBirdstrikeService;
	@Autowired
	private FamsUnsafeeventTiredamageServiceI famsUnsafeeventTiredamageService;
	@Autowired
	private com.gbiac.fams.common.reply.service.FamsCommonReplyServiceI famsCommonReplyServiceI;
	@Autowired
	private FamsWorkRoleServiceI famsWorkRoleService;
	@Autowired
	private FamsWorkApproveServiceI famsWorkApproveService;
	@Autowired
	private FamsWorkApproveDao famsWorkApproveDao;
	@Autowired
	private FamsAnnounceNotifyServiceI famsAnnounceNotifyService;

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	/**
	 * APP单详情
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getMain", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "添加方法获取页面相关元素", notes = "id")
	public ResponseMessage getMain(
			@ApiParam(required = true, name = "day", value = "天数") @RequestParam("day") Integer day,
			HttpServletRequest request) {
//		Map attributes = new HashMap();
//		attributes.put("漏油原因", getTypeGroupGrid("leakreason",request));
//		attributes.put("漏油种类", getTypeGroupGrid("oiltypes",request));
//		attributes.put("是否", getTypeGroupGrid("is_main",request));
		List<IndexDto> indexDtoList = new ArrayList<IndexDto>();
		List<IndexCakeDto> indexCakeList = new ArrayList<IndexCakeDto>();
		List<FamsUnsafeeventAircraftleakageEntity> aircraftleakage = famsUnsafeeventAircraftleakageService
				.getDateByDay(day, request);
		List<IndexDto> indexDtoHList = new ArrayList<IndexDto>();
		for (int i = 0; i < aircraftleakage.size(); i++) {
			IndexDto dto = new IndexDto();
			dto.setId(aircraftleakage.get(i).getId());
			dto.setCode("aircraftleakage");
			dto.setTime(aircraftleakage.get(i).getThedate() + " " + aircraftleakage.get(i).getSettime());
//			String str = "CAN".equals(aircraftleakage.get(i).getTerminalstation()) ? "进港" : "出港";
			dto.setTitle( aircraftleakage.get(i).getFlightno() + "航班" + slotsReturn(aircraftleakage.get(i).getSlots()) + "有航空器漏油");
			indexDtoHList.add(dto);
			indexDtoList.add(dto);
		}
		List<FamsUnsafeeventBirdstrikeEntity> birdstrike = famsUnsafeeventBirdstrikeService.getDateByDay(day, request);
		List<IndexDto> indexDtoBList = new ArrayList<IndexDto>();
		for (int i = 0; i < birdstrike.size(); i++) {
			IndexDto dto = new IndexDto();
			dto.setId(birdstrike.get(i).getId());
			dto.setCode("birdstrike");
			dto.setTime(birdstrike.get(i).getThedate() + " " + birdstrike.get(i).getSettime());
//			String str = "CAN".equals(birdstrike.get(i).getTerminalstation()) ? "进港" : "出港";
			dto.setTitle(birdstrike.get(i).getFlightno() + "航班"+slotsReturn(birdstrike.get(i).getSlots())+"有鸟击");
			indexDtoBList.add(dto);
			indexDtoList.add(dto);
		}
		List<FamsUnsafeeventTiredamageEntity> tiredamage = famsUnsafeeventTiredamageService.getDateByDay(day, request);
		List<IndexDto> indexDtoTList = new ArrayList<IndexDto>();
		for (int i = 0; i < tiredamage.size(); i++) {
			IndexDto dto = new IndexDto();
			dto.setId(tiredamage.get(i).getId());
			dto.setCode("tiredamage");
			dto.setTime(tiredamage.get(i).getThedate() + " " + tiredamage.get(i).getSettime());
//			String str = "CAN".equals(tiredamage.get(i).getTerminalstation()) ? "进港" : "出港";
			dto.setTitle(tiredamage.get(i).getFlightno() + "航班"+slotsReturn(tiredamage.get(i).getSlots())+"有轮胎损伤");
			indexDtoTList.add(dto);
			indexDtoList.add(dto);
		}
		indexDtoList = ListSort(indexDtoList);
		IndexCakeDto indexCaket = new IndexCakeDto();
		indexCaket.setVal(indexDtoTList.size());
		indexCaket.setName("轮胎损伤");
		indexCaket.setIndexAll(indexDtoTList);
		indexCaket.setIndexFive(indexDtoList);
		indexCakeList.add(indexCaket);

		IndexCakeDto indexCaken = new IndexCakeDto();
		indexCaken.setVal(indexDtoBList.size());
		indexCaken.setName("鸟击");
		indexCaken.setIndexAll(indexDtoBList);
		indexCakeList.add(indexCaken);

		IndexCakeDto indexCakeh = new IndexCakeDto();
		indexCakeh.setVal(indexDtoHList.size());
		indexCakeh.setName("航空器漏油");
		indexCakeh.setIndexAll(indexDtoHList);
		indexCakeList.add(indexCakeh);
		return Result.success(indexCakeList);
	}

	private static String slotsReturn(String slots){
		if(StringUtil.isNotEmpty(slots)){
			return "(机位"+slots+")";
		}

		return "";

	}

	/**
	 * 按照部门展示APP首页统计
	 * 邓正辉
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "indexCountApp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "app统计首页", notes = "id")
	public ResponseMessage<Map> indexCountApp(HttpServletRequest request) {
		String message;
		//查询是否拥有施工审批权限
		List<Map> approveFunctions = famsWorkApproveDao.getApproveFunctions(Util.getPcOrAppUserId());
		boolean canApprove=approveFunctions.size()>0?true:false;
		// 获取当前用户的Id author -mchen
		String userId = SessionUserUtil.getCurrentUserId();
		List<String> countMsg = new ArrayList<String>();// 我的待办，包括未读的通知通告、待接收的整改单和违章告知单。例如您有几条什么待什么
		Map msgReturn = new HashMap();// 功能菜单，当有新的待处理内容时，对应的功能菜单要有未读消息提示。
		try {
			if (canApprove) {// 该用户拥有审批权限,有审批权限为内部单位
				// 查询待审批的施工申请数量
				int approveNum = famsWorkApproveService.getNeedApproveApplyNum();
				// 查询待进场数量
				int approveInNum = famsWorkApproveService.getNeedApproveInNum();
				// 查询待离场数量
				int approveOutNum = famsWorkApproveService.getNeedApproveOutNum();
				if (approveNum != 0) {
					msgReturn.put(MessageConstant.APPLY,approveNum);
					countMsg.add("您有" + approveNum + "条施工申请待审批");
				}
				if (approveInNum != 0) {
					msgReturn.put(MessageConstant.IN,approveInNum);
					countMsg.add("您有" + approveInNum + "条进场申请待审批");
				}
				if (approveOutNum != 0) {
					msgReturn.put(MessageConstant.OUT,approveOutNum);
					countMsg.add("您有" + approveOutNum + "条离场申请待审批");
				}
			}
			//首页整改单和违章单根据部门统计，邓正辉
			if (SessionUserUtil.getCurrentUserDepName().contains("机坪")) {
				FamsAircontrolReformEntity entity = new FamsAircontrolReformEntity();
				entity.setStatus("3");// 待验收的整改单
				List<FamsAircontrolReformEntity> list = famsAircontrolReformServiceI.queryListByEntity(entity);
				countMsg.add("您有" + list.size() + "条整改单待验收");
				msgReturn.put(MessageConstant.REFORM_EN, list.size());

			} else if (SessionUserUtil.getCurrentUserDepName().contains("准入")) {
				FamsAircontrolViolationEntity entity = new FamsAircontrolViolationEntity();
				entity.setStatus("1");// 待处理的违章告知单
				List<FamsAircontrolViolationEntity> list = famsAircontrolViolationServiceI.queryListByEntity(entity);
				countMsg.add("您有" + list.size() + "条违章告知单待处理");
				msgReturn.put(MessageConstant.VIOLATION_EN, list.size());
			}
			// 外部单位
			else if("dutyComPer".equals(SessionUserUtil.getCurrentUser().getRole().getRoleCode())){
				FamsAircontrolReformEntity entityRef = new FamsAircontrolReformEntity();
				entityRef.setStatus("1");// 待接收的整改单
				entityRef.setDutyId(SessionUserUtil.getUserDepId());
				List<FamsAircontrolReformEntity> list = famsAircontrolReformServiceI.queryListByEntity(entityRef);
				if(list.size()>0){
					countMsg.add("您有" + list.size() + "条整改单待接收");
					msgReturn.put(MessageConstant.REFORM_EN, list.size());

				}

				FamsAircontrolViolationEntity entityVio = new FamsAircontrolViolationEntity();
				entityVio.setStatus("2");// 待接收的违章告知单
				entityVio.setDutyId(SessionUserUtil.getUserDepId());
				List<FamsAircontrolViolationEntity> listVio = famsAircontrolViolationServiceI
						.queryListByEntity(entityVio);

				if(listVio.size()>0){
					countMsg.add("您有" + listVio.size() + "条违章告知单待接收");
					msgReturn.put(MessageConstant.VIOLATION_EN, listVio.size());
				}

			}
			//首页整改单和违章单根据部门统计结束，邓正辉

			// 添加未读的通知通告 author-mchen
			int count = famsAnnounceNotifyService.countNoReadAnnounce(userId);
			msgReturn.put(MessageConstant.NOTIFY_EN, count);
			countMsg.add("您有" + count + "条通知通告未读");

			msgReturn.put("msgList", countMsg);

		} catch (Exception e) {
			message = "获取首页信息失败";
			logger.error("获取首页信息失败app:" + message, e);
			return Result.error(message);
		}

		return Result.success(msgReturn);
	}

	/**
	 * 按照部门展示机坪监管统计
	 * 邓正辉
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "jpModulCountApp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "机坪监管统计", notes = "id")
	public ResponseMessage<List<String>> jpModulCountApp(HttpServletRequest request) {
		String message;

		List<String> countMsg = new ArrayList<String>();
		try {
			if (SessionUserUtil.getCurrentUserDepName().contains("机坪")) {
				FamsAircontrolReformEntity entityRef = new FamsAircontrolReformEntity();
				entityRef.setStatus("1,2,3,4,5");// 除了撤销的整改单
				entityRef.setDateStart(DateUtils.formatDate() + " 00:00:00");
				entityRef.setDateEnd(DateUtils.formatDate() + " 23:59:59");
				List<FamsAircontrolReformEntity> list = famsAircontrolReformServiceI.queryListByEntity(entityRef);
				countMsg.add("今天已派发整改单" + list.size() + "条");

				FamsAircontrolViolationEntity entityVio = new FamsAircontrolViolationEntity();
				entityVio.setStatus("1,2,3");// 待接收的违章告知单
				entityVio.setDateStart(DateUtils.formatDate() + " 00:00:00");
				entityVio.setDateEnd(DateUtils.formatDate() + " 23:59:59");
				List<FamsAircontrolViolationEntity> listVio = famsAircontrolViolationServiceI
						.queryListByEntity(entityVio);
				countMsg.add("今天已派发违章告知单" + listVio.size() + "条");

				FamsAircontrolSafecheckEntity entitySaf = new FamsAircontrolSafecheckEntity();
				entitySaf.setDateStart(DateUtils.formatDate() + " 00:00:00");
				entitySaf.setDateEnd(DateUtils.formatDate() + " 23:59:59");
				List<FamsAircontrolSafecheckEntity> listSaf = famsAircontrolSafecheckServiceI
						.queryListByEntity(entitySaf);
				countMsg.add("今天已完成航班保障检查单" + listSaf.size() + "条");
			}
//			}else if (SessionUserUtil.getCurrentUserDepName().contains("准入")){
//				FamsAircontrolViolationEntity entity=new FamsAircontrolViolationEntity();
//				entity.setStatus("1");//待处理的违章告知单
//				List<FamsAircontrolViolationEntity> list=famsAircontrolViolationServiceI.queryListByEntity(entity);
//				countMsg.add("您有"+list.size()+"条违章告知单待处理");
//			}
//			//外部单位
//			else{
//				FamsAircontrolReformEntity entityRef=new FamsAircontrolReformEntity();
//				entityRef.setStatus("1");//待接收的整改单
//				List<FamsAircontrolReformEntity> list=famsAircontrolReformServiceI.queryListByEntity(entityRef);
//				countMsg.add("您有"+list.size()+"条整改单待接收");
//
//				FamsAircontrolViolationEntity entityVio=new FamsAircontrolViolationEntity();
//				entityVio.setStatus("2");//待接收的违章告知单
//				List<FamsAircontrolViolationEntity> listVio=famsAircontrolViolationServiceI.queryListByEntity(entityVio);
//				countMsg.add("您有"+listVio.size()+"条违章告知单待接收");
//			}

		} catch (Exception e) {
			message = "获取机坪监管统计失败";
			logger.error("获取机坪监管统计app:" + message, e);
			return Result.error(message);
		}

		return Result.success(countMsg);
	}

	/**
	 * 邓正辉 首页获取整改单和为违章告知单
	 * 
	 * @param famsCommonReplyEntity
	 * @return
	 */
	@RequestMapping(params = "list")
	@ResponseBody
//	@ApiOperation(value = "通用回复表列表信息")
	public ResponseMessage<List<Map>> list(@ApiParam(name = "通用回复对象ggg") FamsCommonReplyEntity famsCommonReplyEntity) {

		famsCommonReplyEntity.setDutyId(Util.getUserDepId());
		List<Map> listFamsCommonReplys = famsCommonReplyServiceI.queryListByEntityForIndex(famsCommonReplyEntity);
		return Result.success(listFamsCommonReplys);
	}

	private List<IndexDto> ListSort(List<IndexDto> list) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				try {
					if (dateFormat.parse(list.get(i).getTime()).getTime() < dateFormat.parse(list.get(j).getTime()).getTime()) {
						IndexDto t = list.get(i);
						list.set(i, list.get(j));
						list.set(j, t);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * APP单详情
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public List<TSTypeCode> getTypeGroupGrid(String code, HttpServletRequest request) {
		List<TSTypegroup> tSCategoryList = systemService.findByProperty(TSTypegroup.class, "typegroupcode", code);
		List<TSType> typegroupList = new ArrayList<TSType>();
		List<TSTypeCode> typegroupCodeList = new ArrayList<TSTypeCode>();
		if (null != tSCategoryList && tSCategoryList.size() > 0) {
			String sql = "SELECT * FROM  t_s_type  where typegroupid = '" + tSCategoryList.get(0).getId() + "'";
			Session session = systemService.getSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TSType.class);
			typegroupList = query.list();
			for (int i = 0; i < typegroupList.size(); i++) {
				TSTypeCode tSTypeCode = new TSTypeCode();
				tSTypeCode.setOrderNum(typegroupList.get(i).getOrderNum());
				tSTypeCode.setTypecode(typegroupList.get(i).getTypecode());
				tSTypeCode.setTypename(typegroupList.get(i).getTypename());
				typegroupCodeList.add(tSTypeCode);
			}
		}
		return typegroupCodeList;
	}

	/**
	 * APP异常统计
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAPPMain", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "APP异常统计")
	public ResponseMessage getAPPMain(@RequestBody MainDto dto, HttpServletRequest request) {
//		day = 0;
		Map msgReturn = new HashMap();
		List<FamsUnsafeeventAircraftleakageEntity> aircraftleakage = famsUnsafeeventAircraftleakageService.getDateByDay(dto.getVal(), request);
		msgReturn.put("aircraftleakage", aircraftleakage.size());

		List<FamsUnsafeeventBirdstrikeEntity> birdstrike = famsUnsafeeventBirdstrikeService.getDateByDay(dto.getVal(),request);
		msgReturn.put("birdstrike", birdstrike.size());

		List<FamsUnsafeeventTiredamageEntity> tiredamage = famsUnsafeeventTiredamageService.getDateByDay(dto.getVal(),request);
		IndexCakeDto dto3 = new IndexCakeDto();
		msgReturn.put("tiredamage", tiredamage.size());

		return Result.success(msgReturn);
	}

	/**
	 * APP场道统计
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAPPairportrunwayMain", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "APP场道统计")
	public ResponseMessage getAPPairportrunwayMain(HttpServletRequest request) {
//		day = 0;
//		System.out.println();
		Map indexCakeList = null;
		try {
			indexCakeList = indexRestService.getAirportrunway();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Result.success(indexCakeList);
	}

}
