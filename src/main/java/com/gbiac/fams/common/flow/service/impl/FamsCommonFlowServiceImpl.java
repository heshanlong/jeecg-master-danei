package com.gbiac.fams.common.flow.service.impl;

import com.gbiac.fams.common.flow.entity.FamsCommonFlowDetailEntity;
import com.gbiac.fams.common.flow.entity.FamsCommonFlowEntity;
import com.gbiac.fams.common.flow.page.FamsCommonFlowPage;
import com.gbiac.fams.common.flow.service.FamsCommonFlowServiceI;
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

@Service("famsCommonFlowService")
@Transactional
public class FamsCommonFlowServiceImpl extends CommonServiceImpl implements FamsCommonFlowServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsCommonFlowEntity entity) throws Exception{
 		super.delete(entity);
 	}
	
	public void addMain(FamsCommonFlowPage famsCommonFlowPage) throws Exception {
		FamsCommonFlowEntity famsCommonFlow = new FamsCommonFlowEntity();
		MyBeanUtils.copyBeanNotNull2Bean(famsCommonFlowPage, famsCommonFlow);
		//保存主信息
		this.save(famsCommonFlow);
		/**保存-通用流程附表*/
		List<FamsCommonFlowDetailEntity> famsCommonFlowDetailList = famsCommonFlowPage.getFamsCommonFlowDetailList();
		for(FamsCommonFlowDetailEntity famsCommonFlowDetail:famsCommonFlowDetailList){
			//外键设置
			famsCommonFlowDetail.setFlowId(famsCommonFlow.getId());
			this.save(famsCommonFlowDetail);
		}
	}

	
	public void updateMain(FamsCommonFlowPage famsCommonFlowPage) throws Exception{
		FamsCommonFlowEntity famsCommonFlow = new FamsCommonFlowEntity();
		//保存主表信息
		if(StringUtil.isNotEmpty(famsCommonFlowPage.getId())){
			famsCommonFlow = findUniqueByProperty(FamsCommonFlowEntity.class, "id", famsCommonFlowPage.getId());
			MyBeanUtils.copyBeanNotNull2Bean(famsCommonFlowPage, famsCommonFlow);
			this.saveOrUpdate(famsCommonFlow);
		}else{
			this.saveOrUpdate(famsCommonFlow);
		}
		//===================================================================================
		//获取参数
		Object id0 = famsCommonFlow.getId();
		//===================================================================================
		//1.通用流程附表数据库的数据
	    String hql0 = "from FamsCommonFlowDetailEntity where 1 = 1 AND flowId = ? ";
	    List<FamsCommonFlowDetailEntity> famsCommonFlowDetailOldList = this.findHql(hql0,id0);
		//2.通用流程附表新的数据
		List<FamsCommonFlowDetailEntity> famsCommonFlowDetailList = famsCommonFlowPage.getFamsCommonFlowDetailList();
	    //3.筛选更新明细数据-通用流程附表
		if(famsCommonFlowDetailList!=null &&famsCommonFlowDetailList.size()>0){
			for(FamsCommonFlowDetailEntity oldE:famsCommonFlowDetailOldList){
				boolean isUpdate = false;
				for(FamsCommonFlowDetailEntity sendE:famsCommonFlowDetailList){
					//需要更新的明细数据-通用流程附表
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-通用流程附表
		    		super.delete(oldE);
	    		}
	    		
			}
			//4.持久化新增的数据-通用流程附表
			for(FamsCommonFlowDetailEntity famsCommonFlowDetail:famsCommonFlowDetailList){
				if(oConvertUtils.isEmpty(famsCommonFlowDetail.getId())){
					//外键设置
					famsCommonFlowDetail.setFlowId(famsCommonFlow.getId());
					this.save(famsCommonFlowDetail);
				}
			}
		}
	}

	
	public void delMain(FamsCommonFlowEntity famsCommonFlow) throws Exception{
		//删除主表信息
		this.delete(famsCommonFlow);
		//===================================================================================
		//获取参数
		Object id0 = famsCommonFlow.getId();
		//===================================================================================
		//删除-通用流程附表
	    String hql0 = "from FamsCommonFlowDetailEntity where 1 = 1 AND flowId = ? ";
	    List<FamsCommonFlowDetailEntity> famsCommonFlowDetailOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(famsCommonFlowDetailOldList);
	}
	
 	
}