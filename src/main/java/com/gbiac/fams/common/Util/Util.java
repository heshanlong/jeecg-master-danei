package com.gbiac.fams.common.Util;

import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;
import com.gbiac.fams.common.map.entity.FamsCommonMapEntity;
import org.jeecgframework.core.util.ResourceUtil;

import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.gbiac.fams.restutil.SessionUserUtil;

/**
 * 获取用户逻辑，所有开发者谨慎修改类中方法
 */
public class Util {
	private static DecimalFormat df = new DecimalFormat("#.00");
	
	//返回的数据为String类型，需要进行格式化： 1998-12-20 12:12
    private static DateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat formatTime = new SimpleDateFormat("HH:mm");
	
	/**
	 * 获取当前登录用户id
	 * 
	 * @author 龚道海
	 * @return
	 */
	public static String getUserId() {
		return ResourceUtil.getSessionUser() == null ? "" : ResourceUtil.getSessionUser().getId();
	}

    /**
     * 获取当前登录用户id,如果PC方法获取位空，则从SessionUserUtil中去拿
     * @return
     */
    public static String getPcOrAppUserId() {
        String userId = getUserId();
        if("".equals(userId)){
            return SessionUserUtil.getCurrentUser().getUser().getId();
        }
        return userId;
    }

	/**
	 * 获取当前登录用户中文名
	 * 
	 * @author 龚道海
	 * @return
	 */
	public static String getUserName() {
		return ResourceUtil.getSessionUser() == null ? "" : ResourceUtil.getSessionUser().getRealName();
	}

	/**
	 * 获取当前登录用户名,如果PC方法获取位空，则从SessionUserUtil中去拿
	 * @return
	 */
	public static String getPcOrAppUserName() {
		String userName = getUserName();
		if("".equals(userName)){
			return SessionUserUtil.getCurrentUser().getUser().getRealName();
		}
		return userName;
	}

	/**
	 * 获取当前登录用户的登录账户名
	 *
	 * @return
	 */
	public static String getCurrentUserName() {
		return ResourceUtil.getSessionUser() == null ? "" : ResourceUtil.getSessionUser().getUserName();
	}


	/**
	 * 获取当前登录用户所属部门id
	 * 
	 * @author 龚道海
	 * @return
	 */
	public static String getUserDepId() {
		return ResourceUtil.getSessionUser() == null ? "" : ResourceUtil.getSessionUser().getDepartid();
	}

	/**
	 * 获取当前登录用户所属部门id,如果PC方法获取位空，则从SessionUserUtil中去拿
	 * @return
	 */
	public static String getPcOrAppUserDepId() {
		String departId = getUserDepId();
		if("".equals(departId)){
			return SessionUserUtil.getCurrentUser().getUser().getCurrentDepart().getId();
		}
		return departId;
	}

	/**
	 * 获取当前登录用户所属部门的orgcode
	 * 
	 * @return
	 */
	public static String getSysOrgCode() {
		return ResourceUtil.getSessionUser() == null ? ""
				: ResourceUtil.getSessionUser().getCurrentDepart().getOrgCode();
	}

	/**
	 * 获取当前登录用户所属部门的orgcode,如果PC方法获取位空，则从SessionUserUtil中去拿
	 * @return
	 */
	public static String getPcOrAppSysOrgCode() {
		String sysOrgCode = getSysOrgCode();
		if("".equals(sysOrgCode)){
			return SessionUserUtil.getCurrentUser().getUser().getCurrentDepart().getOrgCode();
		}
		return sysOrgCode;
	}

	/**
	 * 获取当前登录用户所属公司的CompanyCode
	 * 
	 * @return
	 */
	public static String getCompanyCode() {
		return ResourceUtil.getSessionUser() == null ? ""
				: ResourceUtil.getSessionUser().getCurrentDepart().getSysCompanyCode();
	}

	/**
	 * 获取当前登录用户所属部门名称
	 * 
	 * @author 龚道海
	 * @return
	 */
	public static String getUserDepName() {
		return ResourceUtil.getSessionUser() == null ? ""
				: ResourceUtil.getSessionUser().getCurrentDepart().getDepartname();
	}

	/**
	 * 获取当前登录用户所属部门名称,如果PC方法获取位空，则从SessionUserUtil中去拿
	 * @return
	 */
	public static String getPcOrAppUserDepartName() {
		String userDepName = getUserDepName();
		if("".equals(userDepName)){
			return SessionUserUtil.getCurrentUser().getUser().getCurrentDepart().getDepartname();
		}
		return userDepName;
	}

	/**
	 * 时间格式化
	 * 
	 * @author 龚道海
	 * @param date   时间
	 * @param format 格式
	 * @return
	 */
	public static String dataToStr(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 获取指定时间的下一天
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getNextDay(Date date, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);// +1今天的时间加一天
		date = calendar.getTime();
		return dataToStr(date, format);
	}

	/**
	 * 获取指定时间的下一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);// +1今天的时间加一天
		return calendar.getTime();
	}

	/**
	 * 文件对象转Map
	 * 
	 * @author 龚道海
	 * @param files
	 * @return
	 */
	public static Map filesToMap(List<FamsCommonFileEntity> files) {
		Map result = new HashMap();
		StringBuffer fileNames = new StringBuffer();
		StringBuffer ids = new StringBuffer();
		StringBuffer paths = new StringBuffer();
		StringBuffer sizes = new StringBuffer();
		StringBuffer types = new StringBuffer();
		for (int i = 0; i < files.size(); i++) {
			Double fileSize = files.get(i).getFileSize();
			String fileSizeStr=fileSize==null?"0k":df.format(fileSize/1024)+"k";
			if (i != files.size() - 1) {
				fileNames.append(files.get(i).getFileName() + ";");
				ids.append(files.get(i).getId() + ";");
				paths.append(files.get(i).getFilePath() + ";");
				sizes.append(fileSizeStr + ";");
				types.append(files.get(i).getFileType()+";");
			} else {
				fileNames.append(files.get(i).getFileName());
				ids.append(files.get(i).getId());
				paths.append(files.get(i).getFilePath());
				sizes.append(fileSizeStr);
				types.append(files.get(i).getFileType());
			}
		}
		result.put("fileNames", fileNames);
		result.put("ids", ids);
		result.put("paths", paths);
		result.put("sizes", sizes);
		result.put("types", types);
		return result;
	}

	/**
	 * 文件对象转Array (app使用的文件格式格式)
	 * 
	 * @author Mchen
	 * @param files
	 * @return
	 */
	public static List<Map<String, String>> filesToArray(List<FamsCommonFileEntity> files) {
		List<Map<String, String>> res = new ArrayList<>(files.size());
		if (files == null || files.isEmpty()) {
			return res;
		}
		for(FamsCommonFileEntity file:files) {
			Map<String, String> map = new HashMap<>();
			map.put("id", file.getId());
			map.put("fileName", file.getFileName());
			map.put("fileType", file.getFileType());
			map.put("filePath", file.getFilePath());
			res.add(map);
		}
		return res;
	}

	/**废弃，免维护
	 * 文件对象转Map的基础上,增加额外参数，适用于app接口,
	 * 
	 * @author 邓正辉
	 * @param files
	 * @return
	 */
	public static Map filesToMap(List<FamsCommonFileEntity> files, Map result) {
		StringBuffer fileNames = new StringBuffer();
		StringBuffer ids = new StringBuffer();
		StringBuffer paths = new StringBuffer();
		StringBuffer sizes = new StringBuffer();
		StringBuffer types = new StringBuffer();

		for (int i = 0; i < files.size(); i++) {
			Double fileSize = files.get(i).getFileSize();
			String fileSizeStr=fileSize==null?"0k":df.format(fileSize/1024)+"k";
			if (i != files.size() - 1) {
				fileNames.append(files.get(i).getFileName() + ";");
				ids.append(files.get(i).getId() + ";");
				paths.append(files.get(i).getFilePath() + ";");
				sizes.append(fileSizeStr + ";");
				types.append(files.get(i).getFileType()+";");


			} else {
				fileNames.append(files.get(i).getFileName());
				ids.append(files.get(i).getId());
				paths.append(files.get(i).getFilePath());
				sizes.append(fileSizeStr);
				types.append(files.get(i).getFileType());


			}
		}
		result.put("fileNamesAf", fileNames);
		result.put("idsAf", ids);
		result.put("pathsAf", paths);
		result.put("sizesAf", sizes);
		result.put("typesAf", types);

		return result;
	}

	/**
	 * 文件传递给页面
	 * 
	 * @author 邓正辉
	 * @param files
	 * @return
	 */
	public static void filesSetReq(List<FamsCommonFileEntity> files, HttpServletRequest req) {
		StringBuffer fileNames = new StringBuffer();
		StringBuffer ids = new StringBuffer();
		StringBuffer paths = new StringBuffer();
		StringBuffer sizes = new StringBuffer();
		StringBuffer types = new StringBuffer();

		for (int i = 0; i < files.size(); i++) {
			Double fileSize = files.get(i).getFileSize();
			String fileSizeStr=fileSize==null?"0k":df.format(fileSize/1024)+"k";
			if (i != files.size() - 1) {
				fileNames.append(files.get(i).getFileName() + ";");
				ids.append(files.get(i).getId() + ";");
				paths.append(files.get(i).getFilePath() + ";");
				sizes.append(fileSizeStr + ";");
				types.append(files.get(i).getFileType()+";");

			} else {
				fileNames.append(files.get(i).getFileName());
				ids.append(files.get(i).getId());
				paths.append(files.get(i).getFilePath());
				sizes.append(fileSizeStr);
				types.append(files.get(i).getFileType());

			}
		}
		req.setAttribute("picValue", paths);
		req.setAttribute("picIds", ids);
		req.setAttribute("fileNames", fileNames);
		req.setAttribute("sizes", sizes);
		req.setAttribute("types", types);

	}

	/**
	 * 文件传递给页面第二项
	 * 
	 * @author 邓正辉
	 * @param files
	 * @return
	 */
	public static void filesSetReqTwo(List<FamsCommonFileEntity> files, HttpServletRequest req) {
		StringBuffer fileNames = new StringBuffer();
		StringBuffer ids = new StringBuffer();
		StringBuffer paths = new StringBuffer();
		StringBuffer sizes = new StringBuffer();
		StringBuffer types = new StringBuffer();

		for (int i = 0; i < files.size(); i++) {
			Double fileSize = files.get(i).getFileSize();
			String fileSizeStr=fileSize==null?"0k":df.format(fileSize/1024)+"k";
			if (i != files.size() - 1) {
				fileNames.append(files.get(i).getFileName() + ";");
				ids.append(files.get(i).getId() + ";");
				paths.append(files.get(i).getFilePath() + ";");
				sizes.append(fileSizeStr + ";");
				types.append(files.get(i).getFileType()+";");

			} else {
				fileNames.append(files.get(i).getFileName());
				ids.append(files.get(i).getId());
				paths.append(files.get(i).getFilePath());
				sizes.append(files.get(i).getFileSize());
				types.append(files.get(i).getFileType());

			}
		}
		req.setAttribute("picValueAf", paths);
		req.setAttribute("picAfIds", ids);
		req.setAttribute("picAffileNames", fileNames);
		req.setAttribute("picAfsizes", sizes);
		req.setAttribute("typesAf", types);

	}

	/**
	 * 地图对象转Map
	 * 
	 * @author 龚道海
	 * @param files
	 * @return
	 */
	public static String mapsToMap(List<FamsCommonMapEntity> files) {
		StringBuffer result = new StringBuffer();
		for (FamsCommonMapEntity f : files) {
			for (int i = 0; i < files.size(); i++) {
				if (i != files.size() - 1) {
					result.append(files.get(i).getMapPoints() + ",");
				} else {
					result.append(files.get(i).getMapPoints());
				}
			}
		}
		return result.toString();
	}
	
	/**
	 * 判断是否Object对象为date类型的实例，若是则直接返回，若不是，判断是否为String类型，
	 * 若为String类型对内容进行进行格式话化，然后将String转化为Date进行返回
	 * @param obj
	 * @return
	 */
	public static java.util.Date ifObjStr2DateTime(Object obj){
		try {
			if (obj instanceof java.util.Date) {
				return (java.util.Date) obj;
			}
			if (obj instanceof java.lang.String) {
				// 返回的数据为String类型，需要进行格式化： 1998-12-20 12:12
				System.out.println("返回的数据为String类型，需要进行格式化： " + obj);
				if ((java.lang.String) obj != null) {
					return formatDateTime.parse((java.lang.String) obj);
				}

			}
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static java.util.Date ifObjStr2Date(Object obj){
		try {
			if (obj instanceof java.util.Date) {
				return (java.util.Date) obj;
			}
			if (obj instanceof java.lang.String) {
				// 返回的数据为String类型，需要进行格式化： 1998-12-20 12:12
				System.out.println("返回的数据为String类型，需要进行格式化： " + obj);
				if ((java.lang.String) obj != null) {
					return formatDate.parse((java.lang.String) obj);
				}

			}
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static java.util.Date ifObjStr2Time(Object obj){
		try {
			if (obj instanceof java.util.Date) {
				return (java.util.Date) obj;
			}
			if (obj instanceof java.lang.String) {
				// 返回的数据为String类型，需要进行格式化： 1998-12-20 12:12
				System.out.println("返回的数据为String类型，需要进行格式化： " + obj);
				if ((java.lang.String) obj != null) {
					return formatTime.parse((java.lang.String) obj);
				}

			}
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
