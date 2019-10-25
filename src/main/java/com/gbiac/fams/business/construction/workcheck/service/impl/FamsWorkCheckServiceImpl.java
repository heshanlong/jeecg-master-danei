package com.gbiac.fams.business.construction.workcheck.service.impl;

import com.gbiac.fams.business.construction.workcheck.dao.FamsWorkCheckDao;
import com.gbiac.fams.business.construction.workcheck.entity.FamsWorkCheckEntity;
import com.gbiac.fams.business.construction.workcheck.service.FamsWorkCheckServiceI;
import com.gbiac.fams.business.construction.workcheckitem.entity.FamsWorkCheckItemEntity;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

@Service("famsWorkCheckService")
@Transactional
public class FamsWorkCheckServiceImpl extends CommonServiceImpl implements FamsWorkCheckServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsWorkCheckDao famsWorkCheckDao;
	public void delete(FamsWorkCheckEntity entity) throws Exception{
		super.delete(entity);
		//删除检查小项记录
		famsCommonService.deleteByProperty(FamsWorkCheckItemEntity.class, "checkId".split(","), entity.getId().split(","));
		//删除附件记录
		famsCommonService.deleteByProperty(FamsCommonFileEntity.class, "businessId".split(","), entity.getId().split(","));
	}

	public Serializable save(FamsWorkCheckEntity entity,Map<String,String> itemValues,String files) throws Exception{
		//查询是否进行了重复检查
		List<FamsWorkCheckEntity> famsWorkCheckEntitys = famsCommonService.getEntitiesByProperty(FamsWorkCheckEntity.class, "bid".split(","), entity.getBid().split(","));
		if(famsWorkCheckEntitys.size()!=0){
			throw new BusinessException("该记录已经检查过了，不能重复检查！");
		}
		//设置编号
		entity.setNumberContent(systemService.getFamsNumberByTSTypegroup("例行检查",FamsWorkCheckEntity.class,"createDate"));
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
			famsCommonService.updateMBSofFileByStr(files,"workCheck",t.toString(), null);
		}
		return t;
	}

	public void saveOrUpdate(FamsWorkCheckEntity entity,Map<String,String> itemValues,String files) throws Exception{
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
			famsCommonService.updateMBSofFileByStr(files,"workCheck",entity.getId(), MessageConstant.STATEBEFORE);
		}
	}

	@Override
	public Map getCategory(String code) {
		List<Map> list=famsWorkCheckDao.getCategory(code,null);
		List<Map> maps = listToTree(list);
		return maps!=null?maps.get(0):null;
	}

	@Override
	public Map getCategory(String code,String bid) {
		List<Map> list=famsWorkCheckDao.getCategory(code,bid);
		List<Map> maps = listToTree(list);
		return maps!=null?maps.get(0):null;
	}
	private List<Map> listToTree(List<Map> list){
		if(list==null||list.size()==0){
			return list;
		}
		int num=0;
		while(true){
			num++;
			int size=0;
			Set<String> s=new HashSet<String>();
			if(list.size()==1||num>10){
				break;
			}
			//找出pcode最大长度
			for(Map m:list){
				int tmpsize=m.get("pcode").toString().length();
				size=tmpsize > size ? tmpsize : size;
			}
			//获取最大长度的pcode值（去重）
			for(Map m:list){
				int tmpsize=m.get("pcode").toString().length();
				if(tmpsize==size){
					s.add(m.get("pcode").toString());
				}
			}
			//循环插入
			for(String code:s){
				for(Map m:list){
					if(m.get("code").toString().equals(code)){
						List listmap=new ArrayList();
						for(Map m2:list){
							if(m2.get("pcode").toString().equals(code)){
								listmap.add(m2);
							}
						}
						m.put("children",listmap);
						break;
					}
				}
			}
			//删除已插入的节点
			for(String code:s){
				for(int i=list.size()-1;i>=0;i--){
					if(list.get(i).get("pcode").equals(code)){
						list.remove(i);
					}
				}
			}
		}
		return list;
	}
}