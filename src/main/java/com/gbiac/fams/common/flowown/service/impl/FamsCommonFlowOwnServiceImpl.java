package com.gbiac.fams.common.flowown.service.impl;

import com.gbiac.fams.common.flowown.entity.FamsCommonFlowOwnDetailEntity;
import com.gbiac.fams.common.flowown.entity.FamsCommonFlowOwnEntity;
import com.gbiac.fams.common.flowown.page.FamsCommonFlowOwnPage;
import com.gbiac.fams.common.flowown.service.FamsCommonFlowOwnServiceI;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("famsCommonFlowOwnService")
@Transactional
public class FamsCommonFlowOwnServiceImpl extends CommonServiceImpl implements FamsCommonFlowOwnServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsCommonFlowOwnEntity entity) throws Exception{
 		super.delete(entity);
 	}
	
	public void addMain(FamsCommonFlowOwnPage famsCommonFlowOwnPage) throws Exception {
		FamsCommonFlowOwnEntity famsCommonFlowOwn = new FamsCommonFlowOwnEntity();
		MyBeanUtils.copyBeanNotNull2Bean(famsCommonFlowOwnPage, famsCommonFlowOwn);
		//保存主信息
		this.save(famsCommonFlowOwn);
		/**保存-通用流程权限拥有附表*/
		List<FamsCommonFlowOwnDetailEntity> famsCommonFlowOwnDetailList = famsCommonFlowOwnPage.getFamsCommonFlowOwnDetailList();
		for(FamsCommonFlowOwnDetailEntity famsCommonFlowOwnDetail:famsCommonFlowOwnDetailList){
			//外键设置
			famsCommonFlowOwnDetail.setFlowOwnId(famsCommonFlowOwn.getId());
			this.save(famsCommonFlowOwnDetail);
		}
	}

	
	public void updateMain(FamsCommonFlowOwnPage famsCommonFlowOwnPage) throws Exception{
		FamsCommonFlowOwnEntity famsCommonFlowOwn = new FamsCommonFlowOwnEntity();
		//保存主表信息
		if(StringUtil.isNotEmpty(famsCommonFlowOwnPage.getId())){
			famsCommonFlowOwn = findUniqueByProperty(FamsCommonFlowOwnEntity.class, "id", famsCommonFlowOwnPage.getId());
			MyBeanUtils.copyBeanNotNull2Bean(famsCommonFlowOwnPage, famsCommonFlowOwn);
			this.saveOrUpdate(famsCommonFlowOwn);
		}else{
			this.saveOrUpdate(famsCommonFlowOwn);
		}
		//===================================================================================
		//获取参数
		Object id0 = famsCommonFlowOwn.getId();
		//===================================================================================
		//1.通用流程权限拥有附表数据库的数据
	    String hql0 = "from FamsCommonFlowOwnDetailEntity where 1 = 1 AND flowOwnId = ? ";
	    List<FamsCommonFlowOwnDetailEntity> famsCommonFlowOwnDetailOldList = this.findHql(hql0,id0);
		//2.通用流程权限拥有附表新的数据
		List<FamsCommonFlowOwnDetailEntity> famsCommonFlowOwnDetailList = famsCommonFlowOwnPage.getFamsCommonFlowOwnDetailList();
	    //3.筛选更新明细数据-通用流程权限拥有附表
		if(famsCommonFlowOwnDetailList!=null &&famsCommonFlowOwnDetailList.size()>0){
			for(FamsCommonFlowOwnDetailEntity oldE:famsCommonFlowOwnDetailOldList){
				boolean isUpdate = false;
				for(FamsCommonFlowOwnDetailEntity sendE:famsCommonFlowOwnDetailList){
					//需要更新的明细数据-通用流程权限拥有附表
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-通用流程权限拥有附表
		    		super.delete(oldE);
	    		}
	    		
			}
			//4.持久化新增的数据-通用流程权限拥有附表
			for(FamsCommonFlowOwnDetailEntity famsCommonFlowOwnDetail:famsCommonFlowOwnDetailList){
				if(oConvertUtils.isEmpty(famsCommonFlowOwnDetail.getId())){
					//外键设置
					famsCommonFlowOwnDetail.setFlowOwnId(famsCommonFlowOwn.getId());
					this.save(famsCommonFlowOwnDetail);
				}
			}
		}
	}

	
	public void delMain(FamsCommonFlowOwnEntity famsCommonFlowOwn) throws Exception{
		//删除主表信息
		this.delete(famsCommonFlowOwn);
		//===================================================================================
		//获取参数
		Object id0 = famsCommonFlowOwn.getId();
		//===================================================================================
		//删除-通用流程权限拥有附表
	    String hql0 = "from FamsCommonFlowOwnDetailEntity where 1 = 1 AND flowOwnId = ? ";
	    List<FamsCommonFlowOwnDetailEntity> famsCommonFlowOwnDetailOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(famsCommonFlowOwnDetailOldList);
	}
	
 	
}