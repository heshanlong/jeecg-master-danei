package com.gbiac.fams.business.airportrunway.mark.entity;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

import com.gbiac.fams.common.file.entity.FamsCommonFileEntity;

/**   
 * @Title: Entity
 * @Description: fams_airportrunway_mark
 * @author 江家滨
 * @date 2019-01-22 11:17:47
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fams_airportrunway_mark", schema = "")
@SuppressWarnings("serial")
public class FamsAirportrunwayMarkEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String no;
	/**作业人*/
	@Excel(name="作业人",width=25)
	private java.lang.String people;
	/**作业开始时间*/
	@Excel(name="作业开始时间",width=15)
	private java.lang.String startTime;
	/**作业结束时间*/
	@Excel(name="作业结束时间",width=15)
	private java.lang.String endTime;
	/**跑道标志*/
	@Excel(name="跑道标志",width=50)
	private java.lang.String runwayLogo;
	/**滑行道标志*/
	@Excel(name="滑行道标志",width=50)
	private java.lang.String taxiwayMark;
	/**机坪标志*/
	@Excel(name="机坪标志",width=50)
	private java.lang.String couldSign;
	
	/**翻新标志类型*/
	@Excel(name="翻新标志类型",width=10)
	private java.lang.String newSign;
	
	/**其他标志*/
	@Excel(name="其他标志",width=10)
	private java.lang.String otherSigns;
	/**位置*/
	@Excel(name="位置",width=10)
	private java.lang.String location;
	/**面积*/
	@Excel(name="面积",width=10)
	private java.lang.String area;
	/**油漆*/
	@Excel(name="油漆",width=10)
	private java.lang.String paint;
	/**天那水*/
	@Excel(name="天那水",width=10)
	private java.lang.String dayWater;
	/**图片路径*/
	@Excel(name="图片路径",width=15)
	private java.lang.String files;
	/**备注*/
	@Excel(name="备注",width=20)
	private java.lang.String note;
	/**创建人登录名称*/
	@Excel(name="创建人登录名称",width=15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人登录名称*/
	@Excel(name="更新人登录名称",width=15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name="更新日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**破损类型其他*/
	@Excel(name="破损类型其他",width=20)
	private java.lang.String damageTypeOther;
	/**维修方案其他*/
	@Excel(name="维修方案其他",width=20)
	private java.lang.String maintenancePlanOther;
	/**创建人登录中文名*/
	@Excel(name="创建人登录中文名",width=50)
	private java.lang.String createByCn;
	private List<MarkListEntity> markList;
	private List<MarkListEntity> taxiwayMarkList;
	private List<FamsCommonFileEntity> file;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编号
	 */

	@Column(name ="NO",nullable=true,length=255)
	public java.lang.String getNo(){
		return this.no;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setNo(java.lang.String no){
		this.no = no;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作业人
	 */

	@Column(name ="PEOPLE",nullable=true,length=255)
	public java.lang.String getPeople(){
		return this.people;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作业人
	 */
	public void setPeople(java.lang.String people){
		this.people = people;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  作业开始时间
	 */

	@Column(name ="START_TIME",nullable=true)
	public java.lang.String getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  作业开始时间
	 */
	public void setStartTime(java.lang.String startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  作业结束时间
	 */

	@Column(name ="END_TIME",nullable=true)
	public java.lang.String getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  作业结束时间
	 */
	public void setEndTime(java.lang.String endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  跑道标志
	 */

	@Column(name ="RUNWAY_LOGO",nullable=true,length=1000)
	public java.lang.String getRunwayLogo(){
		return this.runwayLogo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  跑道标志
	 */
	public void setRunwayLogo(java.lang.String runwayLogo){
		this.runwayLogo = runwayLogo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  滑行道标志
	 */

	@Column(name ="TAXIWAY_MARK",nullable=true,length=1000)
	public java.lang.String getTaxiwayMark(){
		return this.taxiwayMark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  滑行道标志
	 */
	public void setTaxiwayMark(java.lang.String taxiwayMark){
		this.taxiwayMark = taxiwayMark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机坪标志
	 */

	@Column(name ="COULD_SIGN",nullable=true,length=1000)
	public java.lang.String getCouldSign(){
		return this.couldSign;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机坪标志
	 */
	public void setCouldSign(java.lang.String couldSign){
		this.couldSign = couldSign;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其他标志
	 */

	@Column(name ="OTHER_SIGNS",nullable=true,length=1000)
	public java.lang.String getOtherSigns(){
		return this.otherSigns;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其他标志
	 */
	public void setOtherSigns(java.lang.String otherSigns){
		this.otherSigns = otherSigns;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  位置
	 */

	@Column(name ="LOCATION",nullable=true,length=1000)
	public java.lang.String getLocation(){
		return this.location;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  位置
	 */
	public void setLocation(java.lang.String location){
		this.location = location;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  面积
	 */

	@Column(name ="AREA",nullable=true,length=100)
	public java.lang.String getArea(){
		return this.area;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  面积
	 */
	public void setArea(java.lang.String area){
		this.area = area;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  油漆
	 */

	@Column(name ="PAINT",nullable=true,length=100)
	public java.lang.String getPaint(){
		return this.paint;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  油漆
	 */
	public void setPaint(java.lang.String paint){
		this.paint = paint;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  天那水
	 */

	@Column(name ="DAY_WATER",nullable=true,length=100)
	public java.lang.String getDayWater(){
		return this.dayWater;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  天那水
	 */
	public void setDayWater(java.lang.String dayWater){
		this.dayWater = dayWater;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片路径
	 */

	@Column(name ="FILES",nullable=true,length=255)
	public java.lang.String getFiles(){
		return this.files;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片路径
	 */
	public void setFiles(java.lang.String files){
		this.files = files;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="NOTE",nullable=true,length=2000)
	public java.lang.String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setNote(java.lang.String note){
		this.note = note;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  破损类型其他
	 */

	@Column(name ="DAMAGE_TYPE_OTHER",nullable=true,length=2000)
	public java.lang.String getDamageTypeOther(){
		return this.damageTypeOther;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  破损类型其他
	 */
	public void setDamageTypeOther(java.lang.String damageTypeOther){
		this.damageTypeOther = damageTypeOther;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维修方案其他
	 */

	@Column(name ="MAINTENANCE_PLAN_OTHER",nullable=true,length=2000)
	public java.lang.String getMaintenancePlanOther(){
		return this.maintenancePlanOther;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维修方案其他
	 */
	public void setMaintenancePlanOther(java.lang.String maintenancePlanOther){
		this.maintenancePlanOther = maintenancePlanOther;
	}
	@Column(name ="create_by_cn",nullable=true)
	public java.lang.String getCreateByCn() {
		return createByCn;
	}

	public void setCreateByCn(java.lang.String createByCn) {
		this.createByCn = createByCn;
	}
	@Transient
	public java.lang.String getNewSign() {
		return newSign;
	}

	public void setNewSign(java.lang.String newSign) {
		this.newSign = newSign;
	}
	@Transient
	public List<MarkListEntity> getMarkList() {
		return markList;
	}

	public void setMarkList(List<MarkListEntity> markList) {
		this.markList = markList;
	}
	@Transient
	public List<MarkListEntity> getTaxiwayMarkList() {
		return taxiwayMarkList;
	}

	public void setTaxiwayMarkList(List<MarkListEntity> taxiwayMarkList) {
		this.taxiwayMarkList = taxiwayMarkList;
	}

	@Transient
	public List<FamsCommonFileEntity> getFile() {
		return file;
	}

	public void setFile(List<FamsCommonFileEntity> file) {
		this.file = file;
	}
	
}