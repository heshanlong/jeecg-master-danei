package com.gbiac.fams.business.construction.workunioncheck.service.impl;

import com.gbiac.fams.business.construction.workcheckitem.entity.FamsWorkCheckItemEntity;
import com.gbiac.fams.business.construction.workunioncheck.entity.FamsWorkUnioncheckEntity;
import com.gbiac.fams.business.construction.workunioncheck.service.FamsWorkUnioncheckServiceI;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("famsWorkUnioncheckService")
@Transactional
public class FamsWorkUnioncheckServiceImpl extends CommonServiceImpl implements FamsWorkUnioncheckServiceI {

	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsCommonService famsCommonService;
 	public void delete(FamsWorkUnioncheckEntity entity) throws Exception{
 		super.delete(entity);
		//删除检查小项记录
		famsCommonService.deleteByProperty(FamsWorkCheckItemEntity.class, "checkId".split(","), entity.getId().split(","));
		//删除附件记录
		famsCommonService.deleteByProperty(FamsCommonFileEntity.class, "businessId".split(","), entity.getId().split(","));
 	}
 	
 	public Serializable save(FamsWorkUnioncheckEntity entity, Map<String,String> itemValues, String files) throws Exception{
		//查询是否进行了重复检查
		List<FamsWorkUnioncheckEntity> famsWorkUnioncheckEntitys = famsCommonService.getEntitiesByProperty(FamsWorkUnioncheckEntity.class, "bid".split(","), entity.getBid().split(","));
		if(famsWorkUnioncheckEntitys.size()!=0){
			throw new BusinessException("该记录已经检查过了，不能重复检查！");
		}
 		//设置编号
		entity.setNumberContent(systemService.getFamsNumberByTSTypegroup("联合检查",FamsWorkUnioncheckEntity.class,"createDate"));
		Serializable t = super.save(entity);
		//保存检查小项结果
		List<FamsWorkCheckItemEntity> list=new ArrayList<FamsWorkCheckItemEntity>();
		Set<String> keys = itemValues.keySet();
		for(String key:keys){
			list.add(new FamsWorkCheckItemEntity(t.toString(),key,itemValues.get(key)));
		}
		this.batchSave(list);
		//更新附件归属
		if(files!=null){
			famsCommonService.updateMBSofFileByStr(files,"unionCheck",t.toString(), null);
		}
		return t;
 	}
 	
 	public void saveOrUpdate(FamsWorkUnioncheckEntity entity,Map<String,String> itemValues,String files) throws Exception{
		super.saveOrUpdate(entity);
		//删除原先的检查小项结果
		famsCommonService.deleteByProperty(FamsWorkCheckItemEntity.class, "checkId".split(","), entity.getId().split(","));
		//保存检查小项结果
		List<FamsWorkCheckItemEntity> list=new ArrayList<FamsWorkCheckItemEntity>();
		Set<String> keys = itemValues.keySet();
		for(String key:keys){
			list.add(new FamsWorkCheckItemEntity(entity.getId(),key,itemValues.get(key)));
		}
		this.batchSave(list);
		//更新附件归属
		if(files!=null){
			famsCommonService.updateMBSofFileByStr(files,"unionCheck",entity.getId(), MessageConstant.STATEBEFORE);
		}
 	}
 	
}