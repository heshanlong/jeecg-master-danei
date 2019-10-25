package com.gbiac.fams.business.construction.workrole.service.impl;

import com.gbiac.fams.business.construction.workrole.dao.FamsWorkRoleDao;
import com.gbiac.fams.business.construction.workrole.entity.FamsWorkRoleDetailEntity;
import com.gbiac.fams.business.construction.workrole.entity.FamsWorkRoleEntity;
import com.gbiac.fams.business.construction.workrole.page.FamsWorkRolePage;
import com.gbiac.fams.business.construction.workrole.service.FamsWorkRoleServiceI;
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

@Service("famsWorkRoleService")
@Transactional
public class FamsWorkRoleServiceImpl extends CommonServiceImpl implements FamsWorkRoleServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsWorkRoleDao famsWorkRoleDao;
 	public void delete(FamsWorkRoleEntity entity) throws Exception{
 		super.delete(entity);
 	}
	
	public void addMain(FamsWorkRolePage famsWorkRolePage) throws Exception {
		FamsWorkRoleEntity famsWorkRole = new FamsWorkRoleEntity();
		MyBeanUtils.copyBeanNotNull2Bean(famsWorkRolePage, famsWorkRole);
		//保存主信息
		this.save(famsWorkRole);
		/**保存-施工角色附表*/
		List<FamsWorkRoleDetailEntity> famsWorkRoleDetailList = famsWorkRolePage.getFamsWorkRoleDetailList();
		for(FamsWorkRoleDetailEntity famsWorkRoleDetail:famsWorkRoleDetailList){
			//外键设置
			famsWorkRoleDetail.setWorkRoleId(famsWorkRole.getId());
			this.save(famsWorkRoleDetail);
		}
	}

	
	public void updateMain(FamsWorkRolePage famsWorkRolePage) throws Exception{
		FamsWorkRoleEntity famsWorkRole = new FamsWorkRoleEntity();
		//保存主表信息
		if(StringUtil.isNotEmpty(famsWorkRolePage.getId())){
			famsWorkRole = findUniqueByProperty(FamsWorkRoleEntity.class, "id", famsWorkRolePage.getId());
			MyBeanUtils.copyBeanNotNull2Bean(famsWorkRolePage, famsWorkRole);
			this.saveOrUpdate(famsWorkRole);
		}else{
			this.saveOrUpdate(famsWorkRole);
		}
		//===================================================================================
		//获取参数
		Object id0 = famsWorkRole.getId();
		//===================================================================================
		//1.施工角色附表数据库的数据
	    String hql0 = "from FamsWorkRoleDetailEntity where 1 = 1 AND workRoleId = ? ";
	    List<FamsWorkRoleDetailEntity> famsWorkRoleDetailOldList = this.findHql(hql0,id0);
		//2.施工角色附表新的数据
		List<FamsWorkRoleDetailEntity> famsWorkRoleDetailList = famsWorkRolePage.getFamsWorkRoleDetailList();
	    //3.筛选更新明细数据-施工角色附表
		if(famsWorkRoleDetailList!=null &&famsWorkRoleDetailList.size()>0){
			for(FamsWorkRoleDetailEntity oldE:famsWorkRoleDetailOldList){
				boolean isUpdate = false;
				for(FamsWorkRoleDetailEntity sendE:famsWorkRoleDetailList){
					//需要更新的明细数据-施工角色附表
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-施工角色附表
		    		super.delete(oldE);
	    		}
	    		
			}
			//4.持久化新增的数据-施工角色附表
			for(FamsWorkRoleDetailEntity famsWorkRoleDetail:famsWorkRoleDetailList){
				if(oConvertUtils.isEmpty(famsWorkRoleDetail.getId())){
					//外键设置
					famsWorkRoleDetail.setWorkRoleId(famsWorkRole.getId());
					this.save(famsWorkRoleDetail);
				}
			}
		}
	}

	
	public void delMain(FamsWorkRoleEntity famsWorkRole) throws Exception{
		//删除主表信息
		this.delete(famsWorkRole);
		//===================================================================================
		//获取参数
		Object id0 = famsWorkRole.getId();
		//===================================================================================
		//删除-施工角色附表
	    String hql0 = "from FamsWorkRoleDetailEntity where 1 = 1 AND workRoleId = ? ";
	    List<FamsWorkRoleDetailEntity> famsWorkRoleDetailOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(famsWorkRoleDetailOldList);
	}
	
	@Override
	public String getRoleCodeByUserId(String userId) {
		return famsWorkRoleDao.getRoleCodeByUserId(userId);
	}
}