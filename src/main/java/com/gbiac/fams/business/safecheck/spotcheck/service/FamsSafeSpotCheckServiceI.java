package com.gbiac.fams.business.safecheck.spotcheck.service;
import com.gbiac.fams.business.safecheck.spotcheck.entity.FamsSafeSpotCheckDetailEntity;
import com.gbiac.fams.business.safecheck.spotcheck.entity.FamsSafeSpotCheckEntity;
import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface FamsSafeSpotCheckServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	/*public <T> void add(T entity);
 	
 	public <T> void addOrUpdate(T entity);*/
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(FamsSafeSpotCheckEntity famsSafeSpotCheck,
	        List<FamsSafeSpotCheckDetailEntity> famsSafeSpotCheckDetail) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(FamsSafeSpotCheckEntity famsSafeSpotCheck,
	        List<FamsSafeSpotCheckDetailEntity> famsSafeSpotCheckDetail);
	
	public void delMain (FamsSafeSpotCheckEntity famsSafeSpotCheck);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(FamsSafeSpotCheckEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(FamsSafeSpotCheckEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(FamsSafeSpotCheckEntity t);
}
