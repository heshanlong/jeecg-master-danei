
package com.gbiac.fams.business.safecheck.spotcheck.page;
import java.util.ArrayList;
import java.util.List;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeecg.demo.entity.JformOrderCustomer2Entity;
import com.gbiac.fams.business.safecheck.spotcheck.entity.FamsSafeSpotCheckDetailEntity;
import com.gbiac.fams.business.safecheck.spotcheck.entity.FamsSafeSpotCheckEntity;

/**   
 * @Title: Entity
 * @Description: 订单主信息
 * @author onlineGenerator
 * @date 2018-03-27 16:21:58
 * @version V1.0   
 *
 */
public class FamsSafeSpotCheckPage implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**订单号*/
    @Excel(name="当日小结")
	private java.lang.String checkDetail;
	/**订单日期*/
    @Excel(name="抽查日期",format = "yyyy-MM-dd")
	private java.util.Date checkDate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
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
	 *@return: java.lang.String  订单号
	 */
	public java.lang.String getCheckDetail(){
		return this.checkDetail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单号
	 */
	public void setCheckDetail(java.lang.String checkDetail){
		this.checkDetail = checkDetail;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  订单日期
	 */
	public java.util.Date getCheckDate(){
		return this.checkDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  订单日期
	 */
	public void setCheckDate(java.util.Date checkDate){
		this.checkDate = checkDate;
	}

	/**保存-订单机票信息*/
    @ExcelCollection(name="抽查详情")
	private List<FamsSafeSpotCheckDetailEntity> famsSafeSpotCheckDetailList = new ArrayList<FamsSafeSpotCheckDetailEntity>();
	public List<FamsSafeSpotCheckDetailEntity> getFamsSafeSpotCheckDetailList() {
		return famsSafeSpotCheckDetailList;
	}
	public void setFamsSafeSpotCheckDetailList(List<FamsSafeSpotCheckDetailEntity> famsSafeSpotCheckDetailList) {
		this.famsSafeSpotCheckDetailList = famsSafeSpotCheckDetailList;
	}
	
	@JsonIgnore
	private List<FamsSafeSpotCheckEntity> famsSafeSpotCheckList = new ArrayList<FamsSafeSpotCheckEntity>();
	public List<FamsSafeSpotCheckEntity> getFamsSafeSpotCheckList() {
		return famsSafeSpotCheckList;
	}
	public void setJformOrderMain2List(List<FamsSafeSpotCheckEntity> famsSafeSpotCheckList) {
		this.famsSafeSpotCheckList = famsSafeSpotCheckList;
	}
}
