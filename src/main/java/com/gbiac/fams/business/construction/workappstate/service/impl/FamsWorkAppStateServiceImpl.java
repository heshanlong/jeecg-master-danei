package com.gbiac.fams.business.construction.workappstate.service.impl;

import com.gbiac.fams.business.construction.workappstate.dao.FamsWorkAppStateDao;
import com.gbiac.fams.business.construction.workappstate.entity.FamsWorkAppStateDetailEntity;
import com.gbiac.fams.business.construction.workappstate.entity.FamsWorkAppStateEntity;
import com.gbiac.fams.business.construction.workappstate.page.FamsWorkAppStatePage;
import com.gbiac.fams.business.construction.workappstate.service.FamsWorkAppStateServiceI;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("famsWorkAppStateService")
@Transactional
public class FamsWorkAppStateServiceImpl extends CommonServiceImpl implements FamsWorkAppStateServiceI {
	@Autowired
	private FamsWorkAppStateDao famsWorkAppStateDao;
 	public void delete(FamsWorkAppStateEntity entity) throws Exception{
 		super.delete(entity);
 	}
	
	public void addMain(FamsWorkAppStatePage famsWorkAppStatePage) throws Exception {
		FamsWorkAppStateEntity famsWorkAppState = new FamsWorkAppStateEntity();
		MyBeanUtils.copyBeanNotNull2Bean(famsWorkAppStatePage, famsWorkAppState);
		//保存主信息
		this.save(famsWorkAppState);
		/**保存-施工管理APP端状态配置附表*/
		List<FamsWorkAppStateDetailEntity> famsWorkAppStateDetailList = famsWorkAppStatePage.getFamsWorkAppStateDetailList();
		for(FamsWorkAppStateDetailEntity famsWorkAppStateDetail:famsWorkAppStateDetailList){
			//外键设置
			famsWorkAppStateDetail.setStateId(famsWorkAppState.getId());
			this.save(famsWorkAppStateDetail);
		}
	}

	
	public void updateMain(FamsWorkAppStatePage famsWorkAppStatePage) throws Exception{
		FamsWorkAppStateEntity famsWorkAppState = new FamsWorkAppStateEntity();
		//保存主表信息
		if(StringUtil.isNotEmpty(famsWorkAppStatePage.getId())){
			famsWorkAppState = findUniqueByProperty(FamsWorkAppStateEntity.class, "id", famsWorkAppStatePage.getId());
			MyBeanUtils.copyBeanNotNull2Bean(famsWorkAppStatePage, famsWorkAppState);
			this.saveOrUpdate(famsWorkAppState);
		}else{
			this.saveOrUpdate(famsWorkAppState);
		}
		//===================================================================================
		//获取参数
		Object id0 = famsWorkAppState.getId();
		//===================================================================================
		//1.施工管理APP端状态配置附表数据库的数据
	    String hql0 = "from FamsWorkAppStateDetailEntity where 1 = 1 AND stateId = ? ";
	    List<FamsWorkAppStateDetailEntity> famsWorkAppStateDetailOldList = this.findHql(hql0,id0);
		//2.施工管理APP端状态配置附表新的数据
		List<FamsWorkAppStateDetailEntity> famsWorkAppStateDetailList = famsWorkAppStatePage.getFamsWorkAppStateDetailList();
	    //3.筛选更新明细数据-施工管理APP端状态配置附表
		if(famsWorkAppStateDetailList!=null &&famsWorkAppStateDetailList.size()>0){
			for(FamsWorkAppStateDetailEntity oldE:famsWorkAppStateDetailOldList){
				boolean isUpdate = false;
				for(FamsWorkAppStateDetailEntity sendE:famsWorkAppStateDetailList){
					//需要更新的明细数据-施工管理APP端状态配置附表
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-施工管理APP端状态配置附表
		    		super.delete(oldE);
	    		}
	    		
			}
			//4.持久化新增的数据-施工管理APP端状态配置附表
			for(FamsWorkAppStateDetailEntity famsWorkAppStateDetail:famsWorkAppStateDetailList){
				if(oConvertUtils.isEmpty(famsWorkAppStateDetail.getId())){
					//外键设置
					famsWorkAppStateDetail.setStateId(famsWorkAppState.getId());
					this.save(famsWorkAppStateDetail);
				}
			}
		}
	}

	
	public void delMain(FamsWorkAppStateEntity famsWorkAppState) throws Exception{
		//删除主表信息
		this.delete(famsWorkAppState);
		//===================================================================================
		//获取参数
		Object id0 = famsWorkAppState.getId();
		//===================================================================================
		//删除-施工管理APP端状态配置附表
	    String hql0 = "from FamsWorkAppStateDetailEntity where 1 = 1 AND stateId = ? ";
	    List<FamsWorkAppStateDetailEntity> famsWorkAppStateDetailOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(famsWorkAppStateDetailOldList);
	}
	
	@Override
	public String getNodesByRoleCodeAndState(String roleCode, Integer workFlag,Integer workingState) {
 		StringBuffer s=new StringBuffer("");
		List<Map> list=famsWorkAppStateDao.getNodesByRoleCodeAndState(roleCode,workFlag,workingState);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				if(i<list.size()-1){
					s.append(list.get(i).get("taskkey")+",");
				}else{
					s.append(list.get(i).get("taskkey"));
				}
			}
		}
		return s.toString();
	}
 	
 	
}