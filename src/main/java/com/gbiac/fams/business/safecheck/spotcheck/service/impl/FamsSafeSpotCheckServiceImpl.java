package com.gbiac.fams.business.safecheck.spotcheck.service.impl;
import java.util.List;
import java.util.UUID;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.safecheck.spotcheck.service.FamsSafeSpotCheckServiceI;
import com.gbiac.fams.business.safecheck.spotcheck.entity.FamsSafeSpotCheckDetailEntity;
import com.gbiac.fams.business.safecheck.spotcheck.entity.FamsSafeSpotCheckEntity;

@Service("famsSafeSpotCheckService")
@Transactional
public class FamsSafeSpotCheckServiceImpl extends CommonServiceImpl implements FamsSafeSpotCheckServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 	}
	
	public void addMain(FamsSafeSpotCheckEntity jformOrderMain2,
	        List<FamsSafeSpotCheckDetailEntity> jformOrderTicket2List){
			//保存主信息
			this.save(jformOrderMain2);
		
			/**保存-订单机票信息*/
			for(FamsSafeSpotCheckDetailEntity jformOrderTicket2:jformOrderTicket2List){
				//外键设置
				jformOrderTicket2.setFckId(jformOrderMain2.getId());
				this.save(jformOrderTicket2);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(jformOrderMain2);
	}

	
	public void updateMain(FamsSafeSpotCheckEntity jformOrderMain2,
	        List<FamsSafeSpotCheckDetailEntity> jformOrderTicket2List) {
		//保存主表信息
		if(StringUtil.isNotEmpty(jformOrderMain2.getId())){
			try {
				FamsSafeSpotCheckEntity temp = findUniqueByProperty(FamsSafeSpotCheckEntity.class, "id", jformOrderMain2.getId());
				MyBeanUtils.copyBeanNotNull2Bean(jformOrderMain2, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(jformOrderMain2);
		}
		//===================================================================================
		//获取参数
		Object id0 = jformOrderMain2.getId();
		Object id1 = jformOrderMain2.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-订单机票信息
	    String hql0 = "from FamsSafeSpotCheckDetailEntity where 1 = 1 AND fCK_ID = ? ";
	    List<FamsSafeSpotCheckDetailEntity> jformOrderTicket2OldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-订单机票信息
		if(jformOrderTicket2List!=null&&jformOrderTicket2List.size()>0){
		for(FamsSafeSpotCheckDetailEntity oldE:jformOrderTicket2OldList){
			boolean isUpdate = false;
				for(FamsSafeSpotCheckDetailEntity sendE:jformOrderTicket2List){
					//需要更新的明细数据-订单机票信息
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-订单机票信息
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-订单机票信息
			for(FamsSafeSpotCheckDetailEntity jformOrderTicket2:jformOrderTicket2List){
				if(oConvertUtils.isEmpty(jformOrderTicket2.getId())){
					//外键设置
					jformOrderTicket2.setFckId(jformOrderMain2.getId());
					this.save(jformOrderTicket2);
				}
			}
		}
		
		//执行更新操作配置的sql增强
 		this.doUpdateSql(jformOrderMain2);
	}

	
	public void delMain(FamsSafeSpotCheckEntity jformOrderMain2) {
		//删除主表信息
		this.delete(jformOrderMain2);
		//===================================================================================
		//获取参数
		Object id0 = jformOrderMain2.getId();
		Object id1 = jformOrderMain2.getId();
		//===================================================================================
		//删除-订单机票信息
	    String hql0 = "from FamsSafeSpotCheckDetailEntity where 1 = 1 AND fCK_ID = ? ";
	    List<FamsSafeSpotCheckDetailEntity> jformOrderTicket2OldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(jformOrderTicket2OldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(FamsSafeSpotCheckEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(FamsSafeSpotCheckEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(FamsSafeSpotCheckEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,FamsSafeSpotCheckEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{check_date}",String.valueOf(t.getCheckDetail()));
 		sql  = sql.replace("#{check_date}",String.valueOf(t.getCheckDate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}