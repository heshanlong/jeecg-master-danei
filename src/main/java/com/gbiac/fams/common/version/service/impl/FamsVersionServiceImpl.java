package com.gbiac.fams.common.version.service.impl;

import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.version.entity.FamsVersionEntity;
import com.gbiac.fams.common.version.service.FamsVersionServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service("famsVersionService")
@Transactional
public class FamsVersionServiceImpl extends CommonServiceImpl implements FamsVersionServiceI {
	@Autowired
	private FamsCommonService famsCommonService;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsVersionEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsVersionEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}

	public Serializable save(FamsVersionEntity entity,String files) throws Exception{
		Serializable t = super.save(entity);
		if(files!=null){
			//如果文件对象在数据库中已经有业务对应了，则将该数据进行备份
			List<FamsCommonFileEntity> famsCommonFileEntitys = famsCommonService.getEntitiesByProperty(FamsCommonFileEntity.class, "id".split(","), files.split(";"));
			for(FamsCommonFileEntity f:famsCommonFileEntitys){
				if((f.getBusinessId()!=null&&!f.getBusinessId().equals(""))||(f.getModule()!=null&&!f.getModule().equals(""))){
					this.save(new FamsCommonFileEntity(f));
				}
			}
			//更新附件归属
			famsCommonService.updateMBSofFileByStr(files,"version",t.toString(), MessageConstant.STATEBEFORE);
		}
		return t;
	}
 	
 	public void saveOrUpdate(FamsVersionEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}