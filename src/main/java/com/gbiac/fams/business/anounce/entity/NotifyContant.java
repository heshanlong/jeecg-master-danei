package com.gbiac.fams.business.anounce.entity;

/**
 * 通知通告的常量
 * @author Mchen
 *
 */
public abstract class NotifyContant {
	/**
	 * 发送模式
	 */
	public static final String SEND_PATTERN_ALL = "all";//全员发送
	public static final String SEND_PATTERN_CURRENT = "current";//选择人员
	public static final String SEND_PATTERN_CUSTOM = "custom";//选择分组
	public static final String SEND_PATTERN_INTERNAL = "internal";//分管内部
	
	/**
	 *模块名
	 */
	public static final String MODULE_NAME="notify";
	
	/**
	 * 接收信息状态
	 */
	//读
	public static final String ANOUNCE_READ = "1";
	//未读
	public static final String ANOUNCE_NO_READ = "2";
	
	/**
	 * 通知编号的数据字典
	 */
	public static final String ANOUNCE_DATA_PRE = "通知通告";
	
	/**
	 * 通知通告的撤销状态
	 */
	public static final String WITHDRAW_STATE = "2";
	
	/**
	 * 数据字典中飞行区管理部对应的编码
	 */
	public static final String DATA_DIR__FLY_MANAGE = "internal";
}
