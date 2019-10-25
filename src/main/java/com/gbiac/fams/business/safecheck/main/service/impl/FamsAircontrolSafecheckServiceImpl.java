package com.gbiac.fams.business.safecheck.main.service.impl;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbiac.fams.business.safecheck.detail.entity.FamsAircontrolSafecheckdetailEntity;
import com.gbiac.fams.business.safecheck.detail.service.FamsAircontrolSafecheckdetailServiceI;
import com.gbiac.fams.business.safecheck.main.dao.FamsAircontrolSafecheckDao;
import com.gbiac.fams.business.safecheck.main.entity.FamsAircontrolSafecheckEntity;
import com.gbiac.fams.business.safecheck.main.service.FamsAircontrolSafecheckServiceI;
import com.gbiac.fams.common.FamsCommonService;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.restutil.SessionUserUtil;

@Service("famsAircontrolSafecheckService")
@Transactional
public class FamsAircontrolSafecheckServiceImpl extends CommonServiceImpl implements FamsAircontrolSafecheckServiceI {
	@Autowired
	private FamsAircontrolSafecheckdetailServiceI fdamsAircontrolSafecheckdetailServiceI;
	@Autowired
	protected FamsCommonService famsCommonService;
	@Autowired
	SystemService systemService;
	@Autowired
	FamsAircontrolSafecheckDao famsAircontrolSafecheckDao;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(FamsAircontrolSafecheckEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(FamsAircontrolSafecheckEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(FamsAircontrolSafecheckEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
	@Override
	public Serializable save(FamsAircontrolSafecheckEntity entity,String result,String pcture) throws Exception{
		int falsenum=0;
		//int notuseNum=0;
		entity.setStatus("1");
		entity.setResult("1");
		entity.setResultDes("全部符合");
		entity.setCheckerName(Util.getUserName().isEmpty()? SessionUserUtil.getUserRealName():Util.getUserName());
		entity.setNumber(systemService.getFamsNumberByTSTypegroup("航班保障检查单",FamsAircontrolSafecheckEntity.class,"createDate"));
		Serializable t = super.save(entity);
		//图片文件
		famsCommonService.updateMBSofFileByStr(pcture,"safecheck",entity.getId(),"1");
		//单项结果保存
		//页面传参格式CheckConfigiId:result;CheckConfigiId:result
		String[] resultArray=result.split(";");
		FamsAircontrolSafecheckdetailEntity detai;
		for (String str:resultArray) {
			//页面传参格式CheckConfigiId:result
			System.err.println(str);
			String[] strArray=str.split("/",5);
			System.err.println(strArray.length);
			detai=new FamsAircontrolSafecheckdetailEntity();
			//str += aa[j].value+":"+result+":"+arrayTime+":"+leaveTime+";";
			detai.setCheckId(entity.getId());
			detai.setCheckConfigiId(strArray[0]);
			detai.setResult(strArray[1]);
			detai.setArrayTime(strArray[3]);
			detai.setCompleteTime(strArray[4]);
			//2"不符合"则为异常
			if( "2".equals(detai.getResult())){
				detai.setDealResult(strArray[2]);
				falsenum+=1;
			}
			fdamsAircontrolSafecheckdetailServiceI.saveOrUpdate(detai);

		}
		//更新检查结果，检查结果描述
		if(falsenum!=0){

			entity.setResult("2");
			entity.setResultDes(falsenum+"项异常");
			fdamsAircontrolSafecheckdetailServiceI.saveOrUpdate(entity);
		}
		return t;
	}
	
	@Override
	public Serializable save(FamsAircontrolSafecheckEntity entity, List<FamsAircontrolSafecheckdetailEntity> details,String pcture) throws Exception{
		int falsenum=0;
		//int notuseNum=0;
		entity.setStatus("1");
		entity.setResult("1");
		entity.setResultDes("全部符合");
		entity.setCheckerName(Util.getUserName().isEmpty()? SessionUserUtil.getUserRealName():Util.getUserName());
		entity.setNumber(systemService.getFamsNumberByTSTypegroup("航班保障检查单",FamsAircontrolSafecheckEntity.class,"createDate"));
		Serializable t = super.save(entity);
		//图片文件
		famsCommonService.updateMBSofFileByStr(pcture,"safecheck",entity.getId(),"1");
		//单项结果保存
		//页面传参格式CheckConfigiId:result;CheckConfigiId:result
		for(FamsAircontrolSafecheckdetailEntity detail:details) {
			detail.setCheckId(entity.getId());
			if("2".equals(detail.getResult())) {
				falsenum+=1;
			}
			fdamsAircontrolSafecheckdetailServiceI.saveOrUpdate(detail);
		}
		//更新检查结果，检查结果描述
		if(falsenum!=0){
			entity.setResult("2");
			entity.setResultDes(falsenum+"项异常");
			fdamsAircontrolSafecheckdetailServiceI.saveOrUpdate(entity);
		}
		return t;
	}

	@Override
	public void update(FamsAircontrolSafecheckEntity entity, String result, String pcture) throws Exception {
		int falsenum=0;
		//int notuseNum=0;
		entity.setResult("1");
		entity.setResultDes("全部符合");
		entity.setCheckerName(Util.getUserName().isEmpty()? SessionUserUtil.getUserRealName():Util.getUserName());

		super.saveOrUpdate(entity);
		//图片文件
		famsCommonService.updateMBSofFileByStr(pcture,"safecheck",entity.getId(),"1");
		
		String[] resultArray=result.split(";");
		FamsAircontrolSafecheckdetailEntity detai;
		for (String str:resultArray) {
			//页面传参格式CheckConfigiId:result
			String[] strArray=str.split("/",5);
			System.err.println(strArray.length);
			detai=new FamsAircontrolSafecheckdetailEntity();
			detai.setId(strArray[0]);
			detai.setResult(strArray[1]);
			detai.setArrayTime(strArray[3]);
			detai.setCompleteTime(strArray[4]);
			//2"不符合"则为异常
			if( "2".equals(detai.getResult())){
				detai.setDealResult(strArray[2]);
				falsenum+=1;
			}else {
				detai.setDealResult(null);
			}
			fdamsAircontrolSafecheckdetailServiceI.updateDetailEntity(detai);
		}
		
		/*
		 * //单项结果保存 //id:result;id:result String[] resultArray=result.split(";");
		 * FamsAircontrolSafecheckdetailEntity detai; for (String str:resultArray ) {
		 * //"id:result" String[] strArray=str.split(":"); //2"不符合"则为异常 if(
		 * "2".equals(strArray[1])){ falsenum+=1; }
		 * fdamsAircontrolSafecheckdetailServiceI.updateRes(strArray[0],strArray[1]);
		 * 
		 * }
		 */
		//更新检查结果，检查结果描述
		if(falsenum!=0){

			entity.setResult("2");
			entity.setResultDes(falsenum+"项异常");
			fdamsAircontrolSafecheckdetailServiceI.saveOrUpdate(entity);
		}
	}

	@Override
	public List<FamsAircontrolSafecheckEntity> queryListByEntity(FamsAircontrolSafecheckEntity entity) {
		if(entity!=null
				&& entity.getPageNoApp()!=null
				&& entity.getPageSizeApp()!=null){
			entity.setPageNoApp( (entity.getPageNoApp()-1)*entity.getPageSizeApp() );

		}
		return famsAircontrolSafecheckDao.queryListByEntity(entity);
	}
}