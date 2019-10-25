package com.gbiac.fams.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.Util.JpushUtil;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.dao.FamsCommonDao;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.map.entity.FamsCommonMapEntity;
import com.gbiac.fams.common.progress.entity.FamsCommonProgressEntity;
import com.gbiac.fams.common.push.entity.FamsCommonPushEntity;
import com.gbiac.fams.common.reply.entity.FamsCommonReplyEntity;
import com.gbiac.fams.restutil.SessionUserUtil;

@Service("famsCommonService")
@Transactional
public class FamsCommonServiceImpl extends CommonServiceImpl implements FamsCommonService {
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsCommonDao famsCommonDao;

	@Override
	public Integer updateMBSofFile(List ids, String module, String businessId, String state) throws Exception {
		int num=0;
		for(String id:(List<String>)ids){
			FamsCommonFileEntity f=super.get(FamsCommonFileEntity.class,id);
			if(f!=null){
				f.setModule(module);
				f.setBusinessId(businessId);
				f.setState(state);
				super.saveOrUpdate(f);
				num++;
			}
		}
		return num;
	}

	@Override
	public Integer updateMBSofFileByStr(String idStr, String module, String businessId, String state) throws Exception {
		String[] strList=idStr.split(";");
		//更新之前重置以前的BusinessId；
		if(strList.length>0){
			List<FamsCommonFileEntity> pacList=this.getCommonObject(FamsCommonFileEntity.class,module,businessId,state,null);
			for (FamsCommonFileEntity entiy:pacList) {
				entiy.setBusinessId(null);
				super.updateEntitie(entiy);
			}

		}
		int num=0;
		for(String id:strList){
			FamsCommonFileEntity f=super.get(FamsCommonFileEntity.class,id);
			if(f!=null){
				f.setModule(module);
				f.setBusinessId(businessId);
				f.setState(state);
				super.saveOrUpdate(f);
				num++;
			}
		}
		return null;
	}

	@Override
	public Boolean hasUpdateFile(String idStr, String module, String businessId, String state) throws Exception {
		String[] strList=idStr.split(";");
		//根据module，BusinessId查找修改之前的图片
		List<FamsCommonFileEntity> pacList=this.getCommonObject(FamsCommonFileEntity.class,module,businessId,state,null);
		//把已经有的图片id暂存入list
		ArrayList<String> oldIdLsit=new ArrayList<String>();
		for (FamsCommonFileEntity et:
				pacList) {
			oldIdLsit.add(et.getId());

		}

		//数量不一样，肯定修改
		if(strList.length!=pacList.size()){
			return true;

		}

		//数量一样，id不一样，肯定修改
		for (String id:strList) {
				if(! oldIdLsit.contains(id) )
					return true;
		}

		return false;
	}

	@Override
	public void savePushs(String module, String businessId, Map<String,String> idAndImei, String pushContent) throws Exception {
		//当userIds为null时，为全员发送
		if(idAndImei==null){
			super.save(new FamsCommonPushEntity(Util.getPcOrAppUserId(),module,businessId,"ALL",null,pushContent));
		}else{
			List<FamsCommonPushEntity> list=new ArrayList();
			for(String userId:idAndImei.keySet()){
				list.add(new FamsCommonPushEntity(Util.getPcOrAppUserId(),module,businessId,userId,idAndImei.get(userId),pushContent));
			}
			super.batchSave(list);
		}
	}

	@Override
	public void savePushs(String sendUserId,String module, String businessId, Map<String,String> idAndImei, String pushContent) throws Exception {
		//当userIds为null时，为全员发送
		if(idAndImei==null){
			super.save(new FamsCommonPushEntity(Util.getPcOrAppUserId(),module,businessId,"ALL",null,pushContent));
		}else{
			List<FamsCommonPushEntity> list=new ArrayList();
			for(String userId:idAndImei.keySet()){
				list.add(new FamsCommonPushEntity(sendUserId,module,businessId,userId,idAndImei.get(userId),pushContent));
			}
			super.batchSave(list);
		}
	}

	@Override
	public void sendMessage(String module, String businessId, String userId, String title, String content, Map<String, String> extrasMap) throws Exception {
		if(StringUtil.isEmpty(userId)){
			return;
		}
		List userIds=new ArrayList();
		userIds.add(userId);
		Map idAndImeis = getIdAndImeis(userIds);
		if(idAndImeis!=null){
			//发送推送
			JpushUtil.sendMessage(idAndImeis.values(),title,content,null);
			//保存推送记录
			this.savePushs(module,businessId,idAndImeis,"标题："+title+"\t内容："+content);
		}
	}

	@Override
	public void sendMessage(String sendUserId,String module, String businessId, List<String> userIds, String title, String content, Map<String, String> extrasMap) throws Exception {
		Map idAndImeis = getIdAndImeis(userIds);
		if(idAndImeis!=null){
			//发送推送
			JpushUtil.sendMessage(idAndImeis.values(),title,content,null);
			//保存推送记录
			this.savePushs(sendUserId,module,businessId,idAndImeis,"标题："+title+"\t内容："+content);
		}
	}

	@Override
	public void sendMessageByUsers(String sendUserId,String module, String businessId, List<TSBaseUser> users, String title, String content, Map<String, String> extrasMap) throws Exception {
		List<String> userIds=new ArrayList();
		for(TSBaseUser t:users){
			userIds.add(t.getId());
		}
		this.sendMessage(sendUserId,module,businessId,userIds,title,content,extrasMap);
	}

	@Override
	public void sendMessageToAll(String module, String businessId, String title, String content, Map<String, String> extrasMap) throws Exception {
		//发送推送
		JpushUtil.sendMessageToAll(title,content,null);
		//保存推送记录
		this.savePushs(module,businessId,null,"标题："+title+"\t内容："+content);
	}

	@Override
	public Map getIdAndImeis(List userIds){
		if(userIds!=null&&userIds.size()>0){
			List<Map> idAndImeis=famsCommonDao.getIdAndImeis(userIds);
			if(idAndImeis.size()>0){
				Map map=new HashMap();
				for(Map m:idAndImeis){
					map.put(m.get("id"),m.get("imei"));
				}
				return map;
			}
		}
		return null;
	}

	@Override
	public String saveReply(String module, String businessId, String replyContent) throws Exception {
		return super.save(new FamsCommonReplyEntity(Util.getPcOrAppUserId(),Util.getPcOrAppUserDepId(),module,businessId,null,replyContent)).toString();
	}

	@Override
	public String saveReply(String module, String businessId, String replyToUserId, String replyContent) throws Exception {
		return super.save(new FamsCommonReplyEntity(Util.getPcOrAppUserId(),Util.getPcOrAppUserDepId(),module,businessId,replyToUserId,replyContent)).toString();
	}

	@Override
	public String saveReplyShow(String module, String businessId, String state, String replyContent) throws Exception {
		return super.save(new FamsCommonReplyEntity(Util.getUserName().isEmpty()? SessionUserUtil.getUserRealName():Util.getUserName() ,Util.getUserDepName().isEmpty()?SessionUserUtil.getCurrentUserDepName():Util.getUserDepName(),module,businessId,state,null,replyContent)).toString();
	}
	
	@Override
	public String saveReplyShow(String module, String businessId, String toUserName, String toDepartName, String state,
			String replyContent) throws Exception {
		FamsCommonReplyEntity replyEntity = new FamsCommonReplyEntity(
				Util.getUserName().isEmpty() ? SessionUserUtil.getUserRealName() : Util.getUserName(),
				Util.getUserDepName().isEmpty() ? SessionUserUtil.getCurrentUserDepName() : Util.getUserDepName(), module, businessId,
				state, toUserName, replyContent);
		//将被回复对象的部门信息保存在冗余字段
		replyEntity.setFiled1(toDepartName);
		return super.save(replyEntity).toString();
	}

    @Override
    public String saveProgress(String userId,String departId,String module, String businessId, String flag, String code, String content, String nodeId,String state) throws Exception {
        return super.save(new FamsCommonProgressEntity(userId,departId,module,businessId,flag,code,content,nodeId,state)).toString();
    }

	@Override
	public String saveProgress(String module, String businessId, String flag, String code, String content, String nodeId,String state) throws Exception {
		return super.save(new FamsCommonProgressEntity(Util.getPcOrAppUserId(),Util.getPcOrAppUserDepId(),module,businessId,flag,code,content,nodeId,state)).toString();
	}

	@Override
	public String saveMap(String module, String businessId, String mapPoints, String mapPositions) throws Exception {
		return super.save(new FamsCommonMapEntity(Util.getPcOrAppUserId(),Util.getPcOrAppUserDepId(),module,businessId,mapPoints,mapPositions)).toString();
	}

	@Override
	public <T> List<T> getCommonObject(Class<T> clazz, String module, String businessId, String state,List<String> order){
		Session session = super.getSession();
		Criteria cri = session.createCriteria(clazz);
		//条件查询
        if(module!=null) cri.add(Restrictions.eq("module", module));
        if(businessId!=null) cri.add(Restrictions.eq("businessId", businessId));
        if(state!=null) cri.add(Restrictions.eq("state", state));
		//排序
		if(order!=null&&order.size()>0){
			for(String s:order){
				String fieldName=s.split(",")[0];
				String flag=s.split(",")[1];
				if(flag.equals("desc")){
					cri.addOrder(Order.desc(fieldName));
				}
				if(flag.equals("asc")){
					cri.addOrder(Order.asc(fieldName));
				}
			}
		}
		List list = cri.list();
		//session.close();
		return list;
	}

	@Override
	public <T> List<T> getEntitiesByProperty(Class<T> clazz, String[] propertys, String[] values) {
		Session session = super.getSession();
		Criteria cri = session.createCriteria(clazz);
		if(propertys.length==1&&values.length>1){
			cri.add(Restrictions.in(propertys[0],values));
		}else{
			for(int i=0;i<propertys.length;i++){
				cri.add(Restrictions.eq(propertys[i], values[i]));
			}
		}
		List list = cri.list();
		return list;
	}

	@Override
	public <T> T getEntityByProperty(Class<T> clazz, String[] propertys, String[] values) {
		List<T> list = getEntitiesByProperty(clazz,propertys,values);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public <T> void deleteByProperty(Class<T> clazz, String[] propertys, String[] values) {
		List list = this.getEntitiesByProperty(clazz,propertys,values);
		if(list!=null&&list.size()>0){
			this.deleteAllEntitie(list);
		}
	}

	@Override
	public List<Map<String, Object>> loadFlightData(String flightno) {
		String sql = "select   @rownum := @rownum +1 AS rownum,ffd.*  from   (SELECT   @rownum := 0) r,fams_flight_data ffd where ffd.flightno like  "+" '%"+(flightno==null?"":flightno)+"%' ";
		List<Map<String,Object>> data = this.systemService.findForJdbc(sql);

		return data;
	}

	@Override
	public List<Map<String, Object>> loadFlightDataFromAomip(String flightno) {
		//String sql = "select   @rownum := @rownum +1 AS rownum,ffd.*  from   (SELECT   @rownum := 0) r,fams_flight_data ffd where ffd.flightno like  "+" '%"+(flightno==null?"":flightno)+"%' ";
		List<Map<String,Object>> data = famsCommonDao.loadAomipFlight(flightno!=null?flightno.toUpperCase():flightno,DateUtils.formatDate(new Date(0l))+" 00:00:00",DateUtils.formatDate()+" 23:59:59");

		for (Map<String, Object> map : data) {
			String  ffid=map.get("ffid").toString();
			String afid=map.get("afid").toString();
			List<Map<String,Object>> apot=famsCommonDao.loadAomipFlightairl(ffid);

			List<Map<String,Object>> afidApot=famsCommonDao.loadAomipFlightairl(afid);
			//找到广州是第几个航段
			int apotNumber=-1;
			for(int i=0;i<apot.size();i++){
				if("广州".equals(apot.get(i).get("city"))){
					apotNumber=i;
					break;
				}
			}
			//找到关联航班的广州是第几个航段
			int afidApotNumber=-1;
			for(int i=0;i<afidApot.size();i++){
				if("广州".equals(afidApot.get(i).get("city"))){
					afidApotNumber=i;
					break;
				}
			}
			//出港
			if("D".equals(map.get("AFSS_FLIO"))){
				if(apotNumber!=-1){
					map.put("airlinefullcn",apot.get(apotNumber).get("city")+"-"+apot.get(apotNumber+1).get("city"));//航线,广州在航段第一段
					map.put("starstation",apot.get(apotNumber).get("starstation"));//起始站三字码
					map.put("starstationcn",apot.get(apotNumber).get("city"));//起始站中文
					map.put("terminalstation",apot.get(apotNumber+1).get("starstation"));//终到站三字码
					map.put("terminalstationcn",apot.get(apotNumber+1).get("city"));//终到站中文
					map.put("sst",DateUtils.timestamptoStr( apot.get(apotNumber).get("sst"),DateUtils.datetimeFormat) );//计划出港时间：sst
					map.put("sat",DateUtils.timestamptoStr( apot.get(apotNumber).get("sat"),DateUtils.datetimeFormat) );//实际出港时间：sat

				}
				else{
					map.put("airlinefullcn",null);//航线,广州在航段第一段
					map.put("starstation",null);//起始站三字码
					map.put("starstationcn",null);//起始站中文
					map.put("terminalstation",null);//终到站三字码
					map.put("terminalstationcn",null);//终到站中文
					map.put("sst",null );//计划出港时间：sst
					map.put("sat",null );//实际出港时间：sat

				}


				if(afidApotNumber!=-1){
					map.put("est",DateUtils.timestamptoStr( afidApot.get(afidApotNumber).get("est"),DateUtils.datetimeFormat));//计划进港时间：est
					map.put("eat",DateUtils.timestamptoStr( afidApot.get(afidApotNumber).get("eat"),DateUtils.datetimeFormat));//实际进港时间：eat

				}else{
					map.put("est",null);//计划进港时间：est
					map.put("eat",null);//实际进港时间：eat

				}

			}
			//进港
			else{
				if(apotNumber!=-1){
					map.put("airlinefullcn",apot.get(apotNumber-1).get("city")+"-"+apot.get(apotNumber).get("city"));//航线,广州在后面
					map.put("starstation",apot.get(apotNumber-1).get("starstation"));//起始站三字码
					map.put("starstationcn",apot.get(apotNumber-1).get("city"));//起始站中文
					map.put("terminalstation",apot.get(apotNumber).get("starstation"));//终到站三字码
					map.put("terminalstationcn",apot.get(apotNumber).get("city"));//终到站中文
					map.put("est",DateUtils.timestamptoStr( apot.get(apotNumber).get("est"),DateUtils.datetimeFormat));//计划进港时间：est
					map.put("eat",DateUtils.timestamptoStr( apot.get(apotNumber).get("eat"),DateUtils.datetimeFormat));//实际进港时间：eat

				}else{
					map.put("airlinefullcn",null);//航线,广州在后面
					map.put("starstation",null);//起始站三字码
					map.put("starstationcn",null);//起始站中文
					map.put("terminalstation",null);//终到站三字码
					map.put("terminalstationcn",null);//终到站中文
					map.put("est",null);//计划进港时间：est
					map.put("eat",null);//实际进港时间：eat

				}


				if(afidApotNumber!=-1){
					map.put("sst",DateUtils.timestamptoStr( afidApot.get(afidApotNumber).get("sst"),DateUtils.datetimeFormat) );//计划出港时间：sst
					map.put("sat",DateUtils.timestamptoStr( afidApot.get(afidApotNumber).get("sat"),DateUtils.datetimeFormat) );//实际出港时间：sat

				}else{
					map.put("sst",null);//计划出港时间：sst
					map.put("sat",null);//实际出港时间：sat


				}


			}
			for (String key : map.keySet()) {
				if(null == map.get(key)){
					map.put(key,"");
				}
			}
		}
		return data;
	}
	
	@Override
	public List<Map<String,Object>> getFlightDateByCraftsiteFromAomip(String craftsite){
		
		//获取基本数据
		//List<Map<String,Object>> data = famsCommonDao.getAomipFlightByCraftsite(craftsite!=null?craftsite.toUpperCase():craftsite,DateUtils.formatDate(new Date(0l))+" 00:00:00",DateUtils.formatDate()+" 23:59:59");
		//测试数据
		List<Map<String,Object>> data = famsCommonDao.getAomipFlightByCraftsite(craftsite!=null?craftsite.toUpperCase():craftsite,"2019-03-29 00:00:00","2019-03-29 23:59:59");
		
		for (Map<String, Object> map : data) {
			String  ffid=map.get("ffid").toString();
			String afid=map.get("afid").toString();
			//根据航班标识获取相关的机场地区数据
			List<Map<String,Object>> apot=famsCommonDao.loadAomipFlightairl(ffid);
			//根据关联航班信息
			List<Map<String,Object>> afidApot=famsCommonDao.loadAomipFlightairl(afid);
			//找到广州是第几个航段
			int apotNumber=-1;
			for(int i=0;i<apot.size();i++){
				if("广州".equals(apot.get(i).get("city"))){
					apotNumber=i;
					break;
				}
			}
			//找到关联航班的广州是第几个航段
			int afidApotNumber=-1;
			for(int i=0;i<afidApot.size();i++){
				if("广州".equals(afidApot.get(i).get("city"))){
					afidApotNumber=i;
					break;
				}
			}
			//出港
			if("D".equals(map.get("AFSS_FLIO"))){
				if(apotNumber!=-1){
					map.put("airlinefullcn",apot.get(apotNumber).get("city")+"-"+apot.get(apotNumber+1).get("city"));//航线,广州在航段第一段
					map.put("starstation",apot.get(apotNumber).get("starstation"));//起始站三字码
					map.put("starstationcn",apot.get(apotNumber).get("city"));//起始站中文
					map.put("terminalstation",apot.get(apotNumber+1).get("starstation"));//终到站三字码
					map.put("terminalstationcn",apot.get(apotNumber+1).get("city"));//终到站中文
					map.put("sst",DateUtils.timestamptoStr( apot.get(apotNumber).get("sst"),DateUtils.datetimeFormat) );//计划出港时间：sst
					map.put("sat",DateUtils.timestamptoStr( apot.get(apotNumber).get("sat"),DateUtils.datetimeFormat) );//实际出港时间：sat

				}
				else{
					map.put("airlinefullcn",null);//航线,广州在航段第一段
					map.put("starstation",null);//起始站三字码
					map.put("starstationcn",null);//起始站中文
					map.put("terminalstation",null);//终到站三字码
					map.put("terminalstationcn",null);//终到站中文
					map.put("sst",null );//计划出港时间：sst
					map.put("sat",null );//实际出港时间：sat

				}


				if(afidApotNumber!=-1){
					map.put("est",DateUtils.timestamptoStr( afidApot.get(afidApotNumber).get("est"),DateUtils.datetimeFormat));//计划进港时间：est
					map.put("eat",DateUtils.timestamptoStr( afidApot.get(afidApotNumber).get("eat"),DateUtils.datetimeFormat));//实际进港时间：eat

				}else{
					map.put("est",null);//计划进港时间：est
					map.put("eat",null);//实际进港时间：eat

				}

			}
			//进港
			else{
				if(apotNumber!=-1){
					map.put("airlinefullcn",apot.get(apotNumber-1).get("city")+"-"+apot.get(apotNumber).get("city"));//航线,广州在后面
					map.put("starstation",apot.get(apotNumber-1).get("starstation"));//起始站三字码
					map.put("starstationcn",apot.get(apotNumber-1).get("city"));//起始站中文
					map.put("terminalstation",apot.get(apotNumber).get("starstation"));//终到站三字码
					map.put("terminalstationcn",apot.get(apotNumber).get("city"));//终到站中文
					map.put("est",DateUtils.timestamptoStr( apot.get(apotNumber).get("est"),DateUtils.datetimeFormat));//计划进港时间：est
					map.put("eat",DateUtils.timestamptoStr( apot.get(apotNumber).get("eat"),DateUtils.datetimeFormat));//实际进港时间：eat

				}else{
					map.put("airlinefullcn",null);//航线,广州在后面
					map.put("starstation",null);//起始站三字码
					map.put("starstationcn",null);//起始站中文
					map.put("terminalstation",null);//终到站三字码
					map.put("terminalstationcn",null);//终到站中文
					map.put("est",null);//计划进港时间：est
					map.put("eat",null);//实际进港时间：eat

				}


				if(afidApotNumber!=-1){
					map.put("sst",DateUtils.timestamptoStr( afidApot.get(afidApotNumber).get("sst"),DateUtils.datetimeFormat) );//计划出港时间：sst
					map.put("sat",DateUtils.timestamptoStr( afidApot.get(afidApotNumber).get("sat"),DateUtils.datetimeFormat) );//实际出港时间：sat

				}else{
					map.put("sst",null);//计划出港时间：sst
					map.put("sat",null);//实际出港时间：sat


				}


			}
			for (String key : map.keySet()) {
				if(null == map.get(key)){
					map.put(key,"");
				}
			}
		}
		return data;
	}

	@Override
	public List<Map<String, Object>> loadAllTsdepartByName(String departname) {
		String sql = "select   @rownum := @rownum +1 AS rownum,ffd.*  from   (SELECT   @rownum := 0) r,t_s_depart ffd where ffd.org_code not like 'A39A15%' and ffd.departname like "+" '%"+(departname==null?"":departname)+"%' ";
		List<Map<String,Object>> data = this.systemService.findForJdbc(sql);

		return data;
	}


	@Override
	public boolean jcxj(String id, int a) {
		Session session = systemService.getSession();
		boolean yn = false;
		if (id == null) {
			return yn;
		}
		String sql =" SELECT ru.userid,ru.ID id,ru.roleid FROM t_s_role r JOIN t_s_role_user ru ON ru.roleid = r.id JOIN t_s_base_user bu ON bu.ID = ru.userid "
				+ "WHERE  r.rolecode = 'admin'"
				+ " OR r.rolecode = 'yunxing'"
//    			+ " OR r.rolecode = 'mhj' "
//    			+ " OR r.rolecode = 'jgj' "
//    			+ " OR r.rolecode = 'glj' "
//    			+ " OR r.rolecode = 'jcj' "
//    			+ " OR r.rolecode = 'jgy' "
//    			+ " OR r.rolecode = 'xcry' "
				+ " GROUP BY  	ru.userid ,ru.ID, ru.roleid " ;
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(TSRoleUser.class);
		List<TSRoleUser> tSRoleUserList = query.list();
		for (int i = 0; i < tSRoleUserList.size(); i++) {
			if (id.equals(tSRoleUserList.get(i).getTSUser().getId())) {
				yn = true;
			}
		}
		return yn;
	}

	@Override
	public List<String> getCanApproveIds() {
		List<String> userIds=new ArrayList();
		List<Map> list = famsCommonDao.getCanApproveIds();
		for(Map m:list){
			userIds.add(m.get("id").toString());
		}
		return userIds;
	}
	
	@Override
	public List<String> getCanApproveIdsByOrgId(String orgId) {
		List<String> userIds=new ArrayList<>();
		List<Map> list = famsCommonDao.getCanApproveIdsByOrgId(orgId);
		for(Map m:list){
			userIds.add(m.get("id").toString());
		}
		return userIds;
	}

	@Override
	public List<String> getCanApproveIdsByOrgCode(String orgCode) {
		List<String> userIds=new ArrayList<>();
		List<Map> list = famsCommonDao.getCanApproveIdsByOrgCode(orgCode);
		for(Map m:list){
			userIds.add(m.get("id").toString());
		}
		return userIds;
	}
	
	@Override
	public List<String> getAdminUsers() {
		return famsCommonDao.getAdminUsers();
	}
	
	@Override
	public List<String> getCanApproveIdsByRunPartOrgId() {
		List<String> userIds=new ArrayList();
		List<Map> list = famsCommonDao.getCanApproveIdsByRunPartOrgId();
		for(Map m:list){
			userIds.add(m.get("id").toString());
		}
		return userIds;
	}

	@Override
	public List<TSBaseUser> getApplyUsers(String userId) {
		List<TSBaseUser> users=famsCommonDao.getApplyUsers(userId);
		return users;
	}

	@Override
	public List<TSBaseUser> getApplyUser(String userId) {
		List<TSBaseUser> users=famsCommonDao.getApplyUser(userId);
		return users;
	}

	
	

	

}