package com.gbiac.fams.common;

import com.gbiac.fams.business.rulesregulations.service.FamsRulesregulationsServiceI;
import com.gbiac.fams.common.Util.Util;
import com.gbiac.fams.common.constant.MessageConstant;
import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.file.service.FamsCommonFileServiceI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.extend.swftools.SwfToolsUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**   
 * @Title: Controller  
 * @Description: 通用文件表
 * @author 龚道海
 * @date 2019-01-08 15:17:08
 * @version V1.0   
 *
 */
@Api(value="FamsCommonController",description="通用接口",tags="famsCommonController")
@Controller
@RequestMapping("/famsCommonController")
public class FamsCommonController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FamsCommonController.class);

	@Autowired
	private FamsCommonFileServiceI famsCommonFileService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FamsRulesregulationsServiceI famsRulesregulationsService;

	/**
	 * 上传文件通用接口
	 * @param files 文件流
	 * @param directory 存放目录
	 * @param module 所属模块名
	 * @param state 状态
	 * @throws IOException
	 */
	@RequestMapping(params = "fileUpload", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="上传文件通用接口")
	public AjaxJson upload(HttpServletRequest request, HttpServletResponse resp, @RequestParam(value = "files", required = false) MultipartFile[] files, String directory, String module, String businessId,String state){
		AjaxJson json=new AjaxJson();
		Map info=new HashMap();
		List ids=new ArrayList();
		List paths=new ArrayList();
		try {
			if(directory==null){
				directory="common";
			}
			//项目在容器中实际发布运行的根路径
			String realPath=request.getSession().getServletContext().getRealPath("/");
			System.out.println(request.getRealPath("/"));
			//上传目录路径
			String uploadPath = "upload/"+directory+"/";
			for(MultipartFile file:files){
				//文件原名称
				String fileName=file.getOriginalFilename();
				//文件类型
				String fileType=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
				//文件大小
				Long fileSize = file.getSize();
				//自定义的文件名称
				String trueFileName=UUID.randomUUID() +"."+fileType;
				//创建目录
				File Directory = new File(realPath+uploadPath);
				if(!Directory.exists()){
					Directory.mkdirs();
				}
				//设置存放图片文件的路径
				String filePath=realPath+uploadPath+trueFileName;
				//转存文件到指定的路径
				file.transferTo(new File(filePath));
				//获取文件路径
				String path=uploadPath+trueFileName;
				//存数据库
				String id=famsCommonFileService.save(new FamsCommonFileEntity(Util.getPcOrAppUserId(),module,businessId,state,fileName,fileType,fileSize.doubleValue(),path)).toString();
				ids.add(id);
				paths.add(path);
				//不使用在线预览这个功能，问题太多 。
				/*String type = request.getParameter("typeSwf");
				if(type!=null && !"".equals(type) && !type.equals("null")){
					if(type.equals("1")){
						SwfToolsUtil.convert2SWF(filePath);
					}
				}*/
			}
			info.put("ids",ids);
			info.put("paths",paths);
			json.setMsg(MessageConstant.UPLOADSUCCESS);
			json.setObj(info);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(MessageConstant.UPLOADERROR);
			e.printStackTrace();
		}
		return json;
	}

    /**
     * 文件删除通用接口
     * @param request
     * @param resp
     * @param fileId
     * @return
     */
    @RequestMapping(params = "removeFile", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="文件删除通用接口")
    public AjaxJson removeFile(HttpServletRequest request, HttpServletResponse resp,String fileId){
        AjaxJson json=new AjaxJson();
        try {
            FamsCommonFileEntity famsCommonFileEntity = famsCommonFileService.get(FamsCommonFileEntity.class, fileId);
            if(famsCommonFileEntity!=null){
            	/*
                String filePath = famsCommonFileEntity.getFilePath();
                
                //原文件实际路径
                String realPath=request.getSession().getServletContext().getRealPath("/")+filePath;
                
                //转换的pdf
				String pdfPath = realPath.substring(0, realPath.indexOf("."));
				pdfPath += ".pdf";
				System.out.println(pdfPath);
				
				//转换的swf
				String swfPath = realPath.substring(0, realPath.indexOf("."));
				swfPath += ".swf";
				System.out.println(swfPath);
				
				String dataType[] = {realPath, pdfPath,swfPath};
				
                for (int i = 0; i < dataType.length; i++) {
					File file=new File(dataType[i]);
					if(file.exists()){
						file.delete();
					}
				}
                famsCommonFileService.delete(famsCommonFileEntity);
                */
				json.setMsg(MessageConstant.REMOVESUCCESS);
            }else{
                json.setSuccess(false);
                json.setMsg(MessageConstant.REMOVEERROR);
            }
        }catch (Exception e) {
            json.setSuccess(false);
			json.setMsg(MessageConstant.REMOVEERROR);
            e.printStackTrace();
        }
        return json;
    }

	/**
	 * 通用下拉菜单数据接口
	 * @param request
	 * @param resp
	 * @param tableName 表名 eg: fams_work_approve
	 * @param showFields	查询的字段  eg: id,number,work_name,work_start_time
	 * @param likeField 模糊查询的字段名
	 * @param keyword 模糊值
	 * @param conditions 条件
	 * @param maxNum 展示的条数
	 * @return
	 */
	@RequestMapping(params = "bsSuggest", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="通用下拉菜单数据接口")
	public Object bsSuggest(HttpServletRequest request, HttpServletResponse resp,String tableName,String showFields,String likeField,String keyword,String conditions,Integer maxNum){
        conditions=conditions==null||conditions.equals("")?"1=1":conditions;
		maxNum=maxNum==null?20:maxNum;
		JSONObject object = new JSONObject();
		object.put("message", "");
		StringBuffer sql=new StringBuffer("select ");
		String[] fields = showFields.split(",");
		for(int i=0;i<fields.length;i++){
			if(i!=fields.length-1){
				sql.append(fields[i]+",");
			}else{
				sql.append(fields[i]+" ");
			}
		}
		if(keyword==null||keyword.equals("")){
			sql.append("from "+tableName +" where "+conditions+" limit 0,"+maxNum);
		}else{
			sql.append("from "+tableName +" where "+conditions+" and "+likeField +" like '%"+keyword+"%' limit 0,"+maxNum);
		}
		try {
			List<Map<String,Object>> data = this.systemService.findForJdbc(sql.toString());
			for (Map<String, Object> map : data) {
				for (String key : map.keySet()) {
					if(null == map.get(key)){
						map.put(key,"");
					}
				}
			}
			net.sf.json.JSONArray array = net.sf.json.JSONArray.fromObject(data);
			object.put("value", array);
			object.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		object.put("redirect", "");
		return object;
	}
	/**PC仅根据航班号模糊查找航班数据
	 * @author邓正辉
	 * @param keyword
	 * @param request
	 * @return
	 */
	//@RequestMapping(value = "/loadFlightData/{keyword}", method = RequestMethod.GET)
	@RequestMapping(value = "loadFlightData", method = RequestMethod.GET)
	@ResponseBody
	public Object loadFlightData(@RequestParam(required=false,value = "keyword") String keyword, HttpServletRequest request) {
	//public Object loadFlightData(@ApiParam(required=true,name="keyword",value="航班号")@PathVariable("keyword")String keyword, HttpServletRequest request) {


		JSONObject object = new JSONObject();
		object.put("message", "");
		try {
			List<Map<String,Object>> data = famsCommonService.loadFlightDataFromAomip(keyword);

			net.sf.json.JSONArray array = net.sf.json.JSONArray.fromObject(data);
			object.put("value", array);
			object.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		object.put("redirect", "");
		return object;
	}

	/**
	 * @author邓正辉
	 * @param keyword
	 * @param request
	 * @return
	 */
	//@RequestMapping(value = "/loadFlightData/{keyword}", method = RequestMethod.GET)
	@RequestMapping(value = "loadFlightDataForApp", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="仅根据航班号模糊查找航班数据")
	public ResponseMessage<List<Map<String,Object>>> loadFlightDataForApp(@ApiParam(required=false,name="keyword",value="航班号")@RequestParam(required=false,value = "keyword") String keyword, HttpServletRequest request) {
		//public Object loadFlightData(@ApiParam(required=true,name="keyword",value="航班号")@PathVariable("keyword")String keyword, HttpServletRequest request) {


		List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
		try {
			 data = famsCommonService.loadFlightDataFromAomip(keyword);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(data);
	}

	/**
	 * @author邓正辉
	 * @param departname
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadAllTsdepartByName", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="仅根据公司名模糊查找公司部门信息")
	public ResponseMessage<List<Map<String,Object>>> loadAllTsdepartByName(@ApiParam(required=false,name="departname",value="公司名")@RequestParam(required=false,value = "departname") String departname, HttpServletRequest request) {


		List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
		try {
			 data = famsCommonService.loadAllTsdepartByName(departname);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(data);
	}

	/**
	 * 通过id获取通用实体对象
	 * @param request
	 * @param resp
	 * @param entityName 实体对象名 eg: "FamsCommonFileEntity"
	 * @param id 实体对象id
	 * @return
	 */
	@RequestMapping(params = "getEntity", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="通过id获取实体对象")
	public AjaxJson getEntity(HttpServletRequest request, HttpServletResponse resp,String entityName,String id){
		AjaxJson json=new AjaxJson();
		try {
			Object entity = systemService.get(Class.forName(entityName).newInstance().getClass(), id);
			if(entity!=null){
				json.setObj(entity);
			}else {
				json.setSuccess(false);
				json.setMsg(MessageConstant.OPTIONERROR);
			}
		}catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(MessageConstant.OPTIONERROR);
			e.printStackTrace();
		}
		return json;
	}
}
