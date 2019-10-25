package com.gbiac.fams.business.construction.workapprove.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;

import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveEntity;
import com.gbiac.fams.business.construction.workapprove.entity.FamsWorkApproveExportEntity;
import com.gbiac.fams.business.construction.worksafe.entity.FamsWorkSafePersonEntity;
import com.gbiac.fams.common.Entity.PageEntity;

public interface
FamsWorkApproveServiceI extends CommonService{
	
 	public void delete(FamsWorkApproveEntity entity) throws Exception;
 	
 	public Serializable save(FamsWorkApproveEntity entity) throws Exception;

	public Serializable save(FamsWorkApproveEntity entity,Map map) throws Exception;

	public Serializable save(FamsWorkApproveEntity entity,List<FamsWorkSafePersonEntity> safePersons) throws Exception;
	
 	public void saveOrUpdate(FamsWorkApproveEntity entity) throws Exception;

	public void saveOrUpdate(FamsWorkApproveEntity entity,Map map) throws Exception;
	public void saveOrUpdate(FamsWorkApproveEntity entity,List<FamsWorkSafePersonEntity> safePersons) throws Exception;

	/**
	 * 根据流程权限表过滤数据
	 * @author 龚道海
	 * @param cq
	 */
    void filterData(CriteriaQuery cq);

	/**
	 * 给数据设置节点信息
	 * @author 龚道海
	 * @param dataGrid
	 */
    void setNodeInfoForApply(DataGrid dataGrid);

	/**
	 * 给数据设置节点信息
	 * @author 龚道海
	 * @param dataGrid
	 */
    void setNodeInfoForApprove(DataGrid dataGrid);

	/**
	 * 获取历史动态
	 * @author 龚道海
	 * @param id
	 * @return
	 */
    List getProcess(String id);

	/**
	 * 自动生成流程图
	 * @author 龚道海
	 * @param resp
	 */
    void createViewBMP(HttpServletResponse resp) throws IOException;

    /**
     * 获取对应作业审批流程的流程图
     * @author 王振
     * @param resp
     * @param processType
     * 
     */
    void getViewBMP(HttpServletResponse resp,String processType) throws IOException;
    
	/**
	 * 下拉菜单数据接口
	 * @author 龚道海
	 * @param workType 施工类型
	 * @param keyword 模糊值
	 * @param maxNum 最大数量
	 * @return
	 */
    List bsSuggest(String workType,String keyword, Integer maxNum);

	/**
	 * 施工管理列表接口
	 * @author 龚道海
	 * @param workFlag 页面标识 0  我的施工  1 施工管理
	 * @param searchInput 搜索的字符
	 * @param workingState 施工状态 0：待审批 1：进行中 2：已完成
	 * @param page 分页对象
	 * @return
	 */
    List searchWorkingList(Integer workFlag,String searchInput, Integer workingState,PageEntity page);

	/**
	 * 根据施工问题id获取详情信息
	 * @author 龚道海
	 * @param workingItemID 施工申请id
	 * @param workFlag 施工申请id
	 * @param workingState 施工申请id
	 * @return
	 */
    Map getWorkingDetail(String workingItemID,String roleCode,Integer workFlag,Integer workingState);

	/**
	 * 获取所有施工地点的经纬度
	 * @author 龚道海
	 * @return
	 */
	List getAllWorkingAreaLocation();

	/**
	 * 获取单个施工点经纬度
	 * @author 龚道海
	 * @param workingItemID 施工申请id
	 * @return
	 */
	Map getSingleWorkingAreaLocation(String workingItemID);

	/**
	 * 查询是否拥有例行、联合或抽查的权限，
	 * @param applyId
	 * @param pcOrAppUserId
	 * @return
	 */
    Map getCheckFunctions(String applyId,String pcOrAppUserId);

	/**
	 * 通过申请id获取该申请的相关信息
	 * @param workingItemID
	 * @return
	 */
    Map getApproveInfo(String workingItemID);

	/**
	 * 更新施工申请的流程状态，从工作流中查询出来后赋值
	 * @param id 施工申请id
	 */
	void updateTaskKey(String id);
	
	/**
	 * 更新入场时间
	 * @param id
	 * @param dateIn
	 */
	void updateDateIn(String id, Date dateIn);
	
	
	/**
	 * 更新施工申请，并将是否第一次录入设置为N
	 * @param id
	 */
	void updateTaskKeyAndIsFirst(String id);

	/**
	 * 更新施工申请的流程状态，赋值为s
	 * @param id
	 * @param s
	 */
	void updateTaskKey(String id, String s);

	/**
	 * 获取检查记录信息
	 * @param id 施工申请id
	 * @return
	 */
    List getCheckInfo(String id);

	/**
	 * 获取运控首页数据
	 * @return
	 */
	List getYWorkInfo();

	/**
	 * 获取施工首页数据
	 * @return
	 */
	List getSWorkInfo();

	/**
	 * 查询待审批的施工申请数量
	 * @return
	 */
    int getNeedApproveApplyNum();

	/**
	 * 查询待进场的施工申请数量
	 * @return
	 */
	int getNeedApproveInNum();

	/**
	 * 查询待离场的施工申请数量
	 * @return
	 */
	int getNeedApproveOutNum();

	/**
	 * 根据审批类型获取数据集合
	 * @param applyType
	 * @return
	 */
	List<FamsWorkApproveExportEntity> getDataByApplyType(String applyType);

    Map getDepartMap();

	Map getWorkTypeMap();

	Map getApplyTypeMap();

	Map getStakKeyMap();
	
	/**
	 * 判断施工时间是否到期
	 * @return
	 */
	boolean ifWorkDateExpire(String id);

	/**
	 * 更新提交人的信息
	 * @param approveId
	 * @param pushUserId
	 */
	public Integer updatePushUserId(String approveId, String userId);
	
	/**
	 * 判断是否当前id为不停航作业的id
	 * @param workTypeId
	 * @return
	 */
	public boolean isContinue24NoStopWork(String workTypeId);
	
	/**
	 *  根据用户名获取他发布的所有作业
	 * @param pushBy
	 * @return
	 */
	List<?> getMyWorkByCreateMan(String createName, Integer page, Integer pageSize);
	
	/**
	 * 通过approveId删除用户提交的申请表
	 * @param approveId
	 * @return
	 */
	Boolean deleteByApproveId(String approveId);
	
	/**
	 * 通过状态获取我的作业列表
	 * @param workState
	 * @return
	 */
	List<?> getMyWorkByWorkState(String createName,Integer workState,String searchInput,Integer page, Integer pageSize);
	
	/**
	 * 获取我的作业，状态为待提交
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<?> getmyWorkByStateEqApplyAndCreateName(String createName,String searchInput, Integer page, Integer pageSize);
}
