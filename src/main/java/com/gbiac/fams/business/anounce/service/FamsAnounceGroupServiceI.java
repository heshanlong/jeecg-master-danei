package com.gbiac.fams.business.anounce.service;


import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.gbiac.fams.business.anounce.entity.FamsAnounceGroupEntity;
import com.gbiac.fams.business.anounce.vo.GroupVO;
/**
 * 分组维护模块的业务层
 * 
 * @author mchen
 *
 */
public interface FamsAnounceGroupServiceI extends CommonService{
	
	/**
	 * 根据实体的状态禁用或者开启分组
	 * @param entity	
	 * @throws Exception
	 */
 	public void delete(FamsAnounceGroupEntity entity) throws Exception;
 	/**
 	 * 保存或更新分组维护的表
 	 * @param id 分组的Id 为{@code null 表示添加},否则表示更新
 	 * @param name	 分组名称
 	 * @param userIds	组内的用户的Id
 	 * @return
 	 * @throws Exception
 	 */
 	public Integer saveOrUpdate(String id,String name, String userIds) throws Exception;
 	/**
 	 * 返回所有分组维护的vo
 	 * @return
 	 * @throws Exception
 	 */
 	public List<GroupVO> selectAlls()throws Exception;
 	/**
 	 * 查询分组的Vo
 	 * @param id	分组实体的Id
 	 * @return
 	 * @throws Exception
 	 */
 	public GroupVO selectById(String id)throws Exception;
 	/**
 	 * 查询分组的Vo
 	 * @param groupEntities 分组实体列表
 	 * @return
 	 * @throws Exception
 	 */
 	public List<GroupVO> selectGroupVOBy(List<FamsAnounceGroupEntity> groupEntities)throws Exception;
 	
 	/**
 	 * 判断分组名称是否已经存在
 	 * @param name
 	 * @param userName
 	 * @return
 	 */
 	public boolean isRepeatName(String name, String userName);
}
