package com.gbiac.fams.business.unsafeevent.updatetime.service;
import com.gbiac.fams.business.unsafeevent.updatetime.entity.FamsUpdatetimeEntity;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface FamsUpdatetimeServiceI extends CommonService{
	
 	public void delete(FamsUpdatetimeEntity entity) throws Exception;
 	
 	public Serializable save(FamsUpdatetimeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FamsUpdatetimeEntity entity) throws Exception;
 	/**
	 * 按关键字查询数据
	 * 
	 * @param str
	 * @param page
	 * @param pageSize
	 * @param request
	 */
 	public List<FamsUpdatetimeEntity>  listApp(String str,Integer page,Integer pageSize,HttpServletRequest request);
}
