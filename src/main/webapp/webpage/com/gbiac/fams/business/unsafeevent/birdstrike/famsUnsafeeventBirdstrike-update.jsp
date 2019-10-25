<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>fams_unsafeevent_birdstrike</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
<style>
        .rd1{
            padding-top: 8px;
        }
        .panel-body{
            border-style: none;
        }
        .mybutton {
        	line-height:20px;
        }
        .bird-height .iradio_square-green:not(:first-child):not(:last-of-type) {
            margin-left: 15px;
        }
</style>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsUnsafeeventBirdstrikeController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${famsUnsafeeventBirdstrike.id}"/>
		<div class="row">
		<input name="no" type="hidden" class="form-control input-sm" value="${famsUnsafeeventBirdstrike.no}"  maxlength="255"  ignore="ignore"  readonly="readonly" />
		<div style="line-height: 200%;">
            <input type="hidden" id="radioResult" name="radioResult" value="" />
            <table style="width: 100%">
                    <tr>
                        <th style="text-align: left;">录入事件</th>
                        <th></th>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" ><span style="color:red;">*</span>日期：</td>
                            <td class="rd1 col-md-3">
		                        <input name="thedate" type="text" style="height:26px"  readonly="readonly" class="form-control input-sm laydate-date"  value="${famsUnsafeeventBirdstrike.thedate}" ignore="checked" datatype="*"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" ><span style="color:red;">*</span>接报时间：</td>
                            <td class="rd1 col-md-3">
                            	<input placeholder="请输入接报时间" name="settime" value="${famsUnsafeeventBirdstrike.settime}" type="text" style="height:26px"  readonly="readonly"  class="form-control input-sm laydate-ymdate" ignore="checked" datatype="*" id="hxdate" />
								<%-- <input name="settime" type="text" style="height:26px"  readonly="readonly" class="form-control input-sm laydate-ymdate" value="${famsUnsafeeventBirdstrike.settime}" ignore="checked" datatype="*"  /> --%>
                            </td>
                            <td style="text-align: right;"  class="col-md-1" ><span style="color:red;">*</span>信息来源：</td>
                            <td class="rd1 col-md-3">
								<input name="sourceformation" type="text" style="height:26px"  class="form-control input-sm" maxlength="1000" value="${famsUnsafeeventBirdstrike.sourceformation}"  ignore="checked" datatype="*"   />
                            </td>
                            
                    </tr>
                    <tr>
                        <th style="text-align: left;">航班信息</th>
                        <th></th>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" ><span style="color:red;">*</span>航班号：</td>
                            <td class="rd1 col-md-3">
		                        <%-- <input id="flightno" name="flightno" type="text" style="height:26px"   value="${famsUnsafeeventBirdstrike.flightno}" class="form-control input-sm" oninput="getflightno()" maxlength="50"   ignore="checked" datatype="s1-50"   />
                             --%>
							 <input id="flightn" type="hidden"  readonly="readonly"  value="${famsUnsafeeventBirdstrike.flightno}"  ignore="ignore"  />
                            		<div class="input-group" style="width:100%">
										<input id="flightno" name="flightno" type="text" maxlength="100" style="height:26px"  class="form-control input-sm" placeholder="请输入航班号"  ignore="checked" datatype="*"  >
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
												<span class="caret"></span>
											</button>
											<ul class="dropdown-menu dropdown-menu-right" role="menu">
											</ul>
										</div>
									</div>
							</td>
                            <%-- <td style="text-align: right;"  class="col-md-1" >航班注册号：</td>
                            <td class="rd1 col-md-3">
								<input id="flightregistrationnumber"  name="flightregistrationnumber"   value="${famsUnsafeeventBirdstrike.flightregistrationnumber}"  type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td> --%>
                            <td style="text-align: right;"  class="col-md-1" >机型：</td>
                            <td class="rd1 col-md-3">
								<input id="models"  name="models" type="text" style="height:26px"  value="${famsUnsafeeventBirdstrike.models}"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                    
                    		<td style="text-align: right;"  class="col-md-1" >机号：</td>
                            <td class="rd1 col-md-3">
		                        <input id="immediately"  name="immediately"  value="${famsUnsafeeventBirdstrike.immediately}" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr>
                            <td style="text-align: right;"  class="col-md-1" >停机位：</td>
                            <td class="rd1 col-md-3">
								<input id="slots"  name="slots"  value="${famsUnsafeeventBirdstrike.slots}" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >航空公司：</td>
                            <td class="rd1 col-md-3">
								<input id="airlinescn"  name="airlinescn"  value="${famsUnsafeeventBirdstrike.airlinescn}" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                            
                    
                    		<td style="text-align: right;"  class="col-md-1" >航线：</td>
                            <td class="rd1 col-md-3">
		                        <input id="routes"  name="routes"  value="${famsUnsafeeventBirdstrike.routes}" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr>
                            <td style="text-align: right;"  class="col-md-1" >计划进港时间：</td>
                            <td class="rd1 col-md-3">
								<input id="plannedtime"      value="${famsUnsafeeventBirdstrike.plannedtime}"  readonly="readonly"  name="plannedtime" type="text" style="height:26px"  class="form-control input-sm  laydate-hxdate laydate-datetime"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >计划出港时间：</td>
                            <td class="rd1 col-md-3">
								<input id="planneddeparture"    value="${famsUnsafeeventBirdstrike.planneddeparture}"   readonly="readonly"  name="planneddeparture" type="text" style="height:26px"  class="form-control input-sm  laydate-hxdate laydate-datetime"  ignore="ignore"  />
                            </td>
                            
                    
                    		<td style="text-align: right;"  class="col-md-1" >实际进港时间：</td>
                            <td class="rd1 col-md-3">
		                        <input id="actualtimearrival"    value="${famsUnsafeeventBirdstrike.actualtimearrival}"   readonly="readonly"  name="actualtimearrival" type="text" style="height:26px"  class="form-control input-sm  laydate-hxdate laydate-datetime"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr>
                            <td style="text-align: right;"  class="col-md-1" >实际出港时间：</td>
                            <td class="rd1 col-md-3">
								<input id="actualdeparture"    value="${famsUnsafeeventBirdstrike.actualdeparture}"   readonly="readonly"  name="actualdeparture" type="text" style="height:26px"  class="form-control input-sm  laydate-hxdate laydate-datetime"  ignore="ignore"  />
                            </td>
                            <input id="starstation" name="starstation" type="hidden"  value="${famsUnsafeeventBirdstrike.starstation}" class="form-control input-sm"  maxlength="255"  ignore="ignore"  readonly="readonly" />
                            <input id="starstationcn" name="starstationcn" type="hidden"  value="${famsUnsafeeventBirdstrike.starstationcn}" class="form-control input-sm"  maxlength="255"  ignore="ignore"  readonly="readonly" />
                            <input id="terminalstation" name="terminalstation" type="hidden"  value="${famsUnsafeeventBirdstrike.terminalstation}" class="form-control input-sm"  maxlength="255"  ignore="ignore"  readonly="readonly" />
                            <input id="terminalstationcn" name="terminalstationcn" type="hidden"  value="${famsUnsafeeventBirdstrike.terminalstationcn}" class="form-control input-sm"  maxlength="255"  ignore="ignore"  readonly="readonly" />
                            <input id="airlines" name="airlines" type="hidden"  value="${famsUnsafeeventBirdstrike.airlines}" class="form-control input-sm"  maxlength="255"  ignore="ignore"  readonly="readonly" />
					</tr>
                    <tr>
                        <th style="text-align: left;">信息接收</th>
                        <th></th>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >痕迹部位：</td>
                            <td class="rd1 col-md-3">
		                        <input name="damagedlocation"  value="${famsUnsafeeventBirdstrike.damagedlocation}" type="text" style="height:26px"  class="form-control input-sm" maxlength="100" placeholder="请输入痕迹部位" ignore="ignore"  />
                            </td>
                            
                            <td style="text-align: right;"  class="col-md-1" >鸟击高度：</td>
                            <td class="rd1 col-md-3 bird-height">
								<t:dictSelect field="birdhighly" typeGroupCode="birdhighly"  defaultVal="${famsUnsafeeventBirdstrike.birdhighly}" hasLabel="false"  type="radio" extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect>
                                <input  id="birdhighlyDetail" name="birdhighlyDetail"  value="${famsUnsafeeventBirdstrike.birdhighlyDetail}" type="text"  maxlength="100"  style="width:30x " ignore="checked"  placeholder="详细高度，选填" maxlength="100"  oninput = "value=value.replace(/[^\d]/g,'')"  ignore="checked"  datatype="n0-100" />m

                            </td>
                            
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >鸟尸：</td>
                            <td class="rd1 col-md-3">
								<t:dictSelect field="abody" typeGroupCode="youwu"  defaultVal="${famsUnsafeeventBirdstrike.abody}" hasLabel="false"  type="radio" extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect>
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >鸟毛：</td>
                            <td class="rd1 col-md-3">
								<t:dictSelect field="birdfeathers" typeGroupCode="youwu"  defaultVal="${famsUnsafeeventBirdstrike.birdfeathers}" hasLabel="false"  type="radio" extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect>
                            </td>
                    		<td style="text-align: right;"  class="col-md-1" >血迹：</td>
                            <td class="rd1 col-md-3">
		                        <t:dictSelect field="blood" typeGroupCode="njsj_xueji"  defaultVal="${famsUnsafeeventBirdstrike.blood}" hasLabel="false"  type="radio" extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect>
                            </td>
                    </tr>
                    <tr>
                    		<td style="text-align: right;white-space:nowrap" class="col-md-1" >损伤尺寸长度（毫米）：</td>
                            <td class="rd1 col-md-3">
								<input name="woundsizelong" type="text" style="height:26px" placeholder="请填写损伤尺寸长度（单位为毫米）"  value="${famsUnsafeeventBirdstrike.woundsizelong}"  class="form-control input-sm" maxlength="100"  oninput = "value=value.replace(/[^\d]/g,'')"  ignore="checked"  datatype="n0-100"  />
                            </td>
                            <td style="text-align: right;white-space:nowrap"  class="col-md-1" >宽度（毫米）：</td>
                            <td class="rd1 col-md-3">
								<input name="woundsizewide" type="text" style="height:26px" placeholder="请填写损伤尺寸宽度（单位为毫米）" value="${famsUnsafeeventBirdstrike.woundsizewide}"  class="form-control input-sm" maxlength="100" oninput = "value=value.replace(/[^\d]/g,'')"   ignore="checked"  datatype="n0-100"  />
                            </td>
                    		<td style="text-align: right;white-space:nowrap" class="col-md-1" >深度（毫米）：</td>
                            <td class="rd1 col-md-3">
		                        <input name="woundsizedeep" type="text" style="height:26px" placeholder="请填写损伤尺寸深度（单位为毫米）" value="${famsUnsafeeventBirdstrike.woundsizedeep}"  class="form-control input-sm" maxlength="100" oninput = "value=value.replace(/[^\d]/g,'')"   ignore="checked"  datatype="n0-100"  />
                            </td>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >航空器损伤：</td>
                            <td class="rd1 col-md-3">
								<t:dictSelect field="aircraftdamage" typeGroupCode="youwu"  defaultVal="${famsUnsafeeventBirdstrike.aircraftdamage}" hasLabel="false"  type="radio" extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect>
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >损伤部位：</td>
                            <td class="rd1 col-md-3">
								<input name="injury" type="text" style="height:26px" placeholder="请填写损伤部位"  value="${famsUnsafeeventBirdstrike.injury}" class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr>
                        <th style="text-align: left;">信息通报及处置</th>
                        <th></th>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >通报生态时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="notificationecologicaltime" placeholder="请选择通报生态时间" readonly="readonly"  value="${famsUnsafeeventBirdstrike.notificationecologicaltime}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;padding-left: 0px;white-space:nowrap"  class="col-md-1" >通报值班领导时间：</td>
                            <td class="rd1 col-md-3">
								<input name="announcedutytime" placeholder="请选择值班领导时间" readonly="readonly"    value="${famsUnsafeeventBirdstrike.announcedutytime}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >通报AOC时间：</td>
                            <td class="rd1 col-md-3">
								<input name="notificationaoctime" readonly="readonly" placeholder="请选择通报AOC时间"  value="${famsUnsafeeventBirdstrike.notificationaoctime}"   type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  ignore="ignore"  />
                            </td>
                            
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >通报场道时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="announcetracktime" placeholder="请选择通报场道时间"  readonly="readonly"  value="${famsUnsafeeventBirdstrike.announcetracktime}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  ignore="ignore"  />
                            </td>
                            
                             <td style="text-align: right;"  class="col-md-1" >查道开始时间：</td>
                            <td class="rd1 col-md-3">
								<input name="trackstarttime" id="trackstarttime"  readonly="readonly" placeholder="请选择查道起时间"    value="${famsUnsafeeventBirdstrike.trackstarttime}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate laydate-datetime"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >查道结束时间：</td>
                            <td class="rd1 col-md-3">
								<input name="trackcheckstoptime"  id="trackcheckstoptime" readonly="readonly"  placeholder="请选择查道止时间"   value="${famsUnsafeeventBirdstrike.trackcheckstoptime}"   type="text" style="height:26px"  class="form-control input-sm laydate-hxdate laydate-datetime"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr>
							<td style="text-align: right;"  class="col-md-1" >跑道检查：</td>
                            <td class="rd1 col-md-3">
								<t:dictSelect field="checkrunway" typeGroupCode="is_main"  defaultVal="${famsUnsafeeventBirdstrike.checkrunway}" hasLabel="false"  type="radio" extendJson="{ class:'isUseFire i-checks ischeckAt'}"></t:dictSelect>
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >跑道：</td>
                            <td class="rd1 col-md-3">
								
								<c:forEach var="categoryList" items="${categoryList}">
                                    <input name="runway" class="form-control i-checks" datatype="*" type="radio" value="${categoryList.code}" ignore="checked" />${categoryList.name}
									<%--<input name="runway" type="radio"  value="${categoryList.code}"   ignore="ignore" >${categoryList.name}</input>--%>
							    </c:forEach>
							    
								<input id="runway"   type="hidden" class="form-control input-sm  i-checks" maxlength="255" value="${famsUnsafeeventBirdstrike.runway}" ignore="ignore"  />
                            </td>
                    		<td style="text-align: right;"  class="col-md-1" >检查方向：</td>
                            <td class="rd1 col-md-3">
		                        <t:dictSelect field="checkdirection" typeGroupCode="checkd"  defaultVal="${famsUnsafeeventBirdstrike.checkdirection}" hasLabel="false"  type="radio" extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect>
                            </td>
                           
                            
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >跑道异物：</td>
                            <td class="rd1 col-md-3">
		                        <t:dictSelect field="runwayforeign" typeGroupCode="youwu"  defaultVal="${famsUnsafeeventBirdstrike.runwayforeign}" hasLabel="false"  type="radio" extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect>
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >事件类型：</td>
                            <td class="rd1 col-md-3">
								<t:dictSelect field="eventtype" typeGroupCode="eventtype" defaultVal="${famsUnsafeeventBirdstrike.eventtype}"  hasLabel="false"  type="select"  extendJson="{style:'width:100%'}" ></t:dictSelect>
                            </td>
                    </tr>
                    <tr>
                            <td style="text-align: right;"  class="col-md-1" >补充说明：</td>
                            <td class="rd1 col-md-3">
                            	<textarea  id="addede" name="added" cols="30" rows="5" maxlength="1000"  style="width:100%" placeholder="请输入信息"></textarea>
                            	<input id="added" type="hidden"  readonly="readonly"  value="${famsUnsafeeventBirdstrike.added}"  ignore="ignore"  />
								<%-- <input name="added" type="text" style="height:26px"  class="form-control input-sm"  value="${famsUnsafeeventBirdstrike.added}" maxlength="10"  ignore="ignore"  /> --%>
                            </td>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >图片：</td>
                            <td class="rd1 col-md-3" colspan="5">
		                        <div class="col-sm-7">
							        <jsp:include page= "/webpage/common/jsp/upload.jsp">
							        	<jsp:param name= "name" value= "files"/>
							            <jsp:param name= "ids" value= "${files.ids}"/>
							            <jsp:param name= "value" value= "${files.paths}"/>
							            <jsp:param name= "fileNames" value= "${files.fileNames}"/>
							            <jsp:param name= "sizes" value= "${files.sizes}"/>
							            <jsp:param name= "types" value="${files.types}" />
							            <jsp:param name= "directory" value= "famsUnsafeeventBirdstrike"/>
							            <jsp:param name= "multiple" value= "multiple"/>
							            <jsp:param name= "required" value= ""/>
							            <jsp:param name= "disable" value= ""/>
							            <jsp:param name= "typeSwf" value= "0"/>
          								<jsp:param name= "acceptFileType" value= "image/*"/>
							        </jsp:include>
							    </div>
                            </td>
                    </tr>
            </table>
        </div>
		</div>
	</form>
	</div>
 </div>
  <script src="plug-in/hplus/plugins/suggest/bootstrap-suggest.min.js"></script>
<script type="text/javascript">
var subDlgIndex = '';

//右边表格版本对比
function getflightno() {
	var flightno = $("#flightno").val();
  $.ajax({
      type: 'post',
      async: true,
      url: "famsUnsafeeventTiredamageController.do?getflightno",
      dataType: 'json',
      data: {
      	flightno: flightno
      },
      success: function (res) {
           //alert(res.data[0].flightno);
           $("#flightno").val(res.data[0].flightno);
			 $("#flightregistrationnumber").val(res.data[0].ffid);
			 $("#models").val(res.data[0].crafttype);
			 $("#immediately").val(res.data[0].craftno);
			 $("#slots").val(res.data[0].craftsite);
			 $("#airlines").val(res.data[0].twocharcode);
			 $("#routes").val(res.data[0].airlinefullcn);
			 $("#plannedtime").val(res.data[0].sst);
			 $("#planneddeparture").val(res.data[0].est);
			 $("#actualtimearrival").val(res.data[0].sat);
			 $("#actualdeparture").val(res.data[0].eat);
      },
      error:function (e) {
          layer.msg("访问数据失败")
      }
  })
}

$(document).ready(function() {
	$("#flightno").val($("#flightn").val());
	/* var foreignmatter = $("#foreignmatter").val();
	$("[name='foreignmatter'][value='" + foreignmatter + "']").prop("checked", "checked");

	var renovationtire = $("#renovationtire").val();
	$("[name='renovationtire'][value='" + renovationtire + "']").prop("checked", "checked");
	
	var impactsubsequentflights = $("#impactsubsequentflights").val();
	$("[name='impactsubsequentflights'][value='" + impactsubsequentflights + "']").prop("checked", "checked"); */
	//获取网页中的单选按钮，挑选其中name=angel，value=0的，让其选中
	$("#addede").val($("#added").val());
	var runway = $("#runway").val();
	<c:if test="${famsUnsafeeventBirdstrike.checkrunway=='N'}">
	    // $("input[name='runway']").each(function (i,val){
        //     $(val).parent().removeClass("checked");
        //     $(val).attr("disabled", "disabled");
        // });
        $("input[name='checkdirection']").attr("disabled", "disabled");
	</c:if>



	<%--$("[name='runway'][value='" + runway + "']").prop("checked", "checked");--%>
	
	
	
	$(".laydate-setdatetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: this,
		  format: 'HH:mm',
		  type: 'time',
			ready: function(date){
			  	 $(_this).val(DateJsonFormat(date,this.format));
			  }
		});
	});
	$(".laydate-rqdatetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: this,
		  //format: 'HH:mm',
		  //type: 'time'
		format: 'yyyy-MM-dd',
		type: 'date',
			ready: function(date){
			  	 $(_this).val(DateJsonFormat(date,this.format));
			  }
		});
	});
	$(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: this,
		  //format: 'HH:mm',
		  //type: 'time'
		format: 'yyyy-MM-dd HH:mm',
		type: 'datetime',
			ready: function(date){
			  	 $(_this).val(DateJsonFormat(date,this.format));
			  },
            done: function(value, date, endDate){
                if(_this.id=="plannedtime" || _this.id=="planneddeparture"){
                    checkTimeSequence(_this,'plannedtime','planneddeparture');
                }if(_this.id=="actualtimearrival" || _this.id=="actualdeparture"){
                    checkTimeSequence(_this,'actualtimearrival','actualdeparture');
                }if(_this.id=="trackstarttime" || _this.id=="trackcheckstoptime"){
                    checkTimeSequence(_this,'trackstarttime','trackcheckstoptime');
                }

            }
		});
	});
    //ymdate
    $(".laydate-ymdate").each(function(){
        var _this = this;
        laydate.render({
            elem: this,
            //format: 'HH:mm',
            //type: 'time'
            format: 'HH:mm',
            type: 'time',//datetime是完整的yyymmm什么的
            ready: function(date){
                $(_this).val(DateJsonFormat(date,this.format));
            }
        });
    });
	$(".laydate-hxdate").each(function(){
		var _this = this;
		laydate.render({
			elem: this,
			//format: 'HH:mm',
			//type: 'time'
			format: 'yyyy-MM-dd HH:mm',
			type: 'datetime',
				ready: function(date){
				  	 $(_this).val(DateJsonFormat(date,this.format));
				  }
		});
	});
	$(".laydate-date").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  max: crtTimeFtt2(new Date()),
		  format: 'yyyy-MM-dd',
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  }
		});
	});
	//单选框/多选框初始化
	$('.i-checks').iCheck({
		labelHover : false,
		cursor : true,
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%'
	});
		$('.ischeckAt').on('ifChecked', function(event){
            var value=event.target.defaultValue;
            if(value=="Y"){
				$("input[name='checkdirection']").removeAttr("checked");
				$("input[name='checkdirection']").removeAttr("disabled");
				$("input[name='checkdirection']").attr("datatype","*");
				// $("input[name='runway']").removeAttr("checked");
				// $("input[name='runway']").removeAttr("disabled");
				// $("input[name='runway']").attr("ignore","checked");
                
            }else{
              	 $("input[name='checkdirection']").parent().removeClass("checked");
				 $("input[name='checkdirection']").attr("disabled", "disabled");
				 $("input[name='checkdirection']").prop("checked","");
				 $("input[name='checkdirection']").removeAttr("datatype");
				 // $("input[name='runway']").each(function (i,val){
				 //   $("#runway").val("");
					// $(val).prop("checked","");
					// $(val).parent().removeClass("checked");
					// $(val).attr("disabled", "disabled");
					// $(val).attr("ignore","ignore");
				 // });
            }
        });
		//alert(runway);
	// if(runway==""){
	// //alert(runway);
	//     $("input[name='runway']").each(function (i,val){
	// 		$(val).parent().removeClass("checked");
	// 		$(val).attr("disabled", "disabled");
	// 	 });
	// 	 $("input[name='checkdirection']").attr("disabled", "disabled");
	// }else{
	// console.log(runway);
	//
	// }
	 
	//表单提交
	$("#formobj").Validform({
		tiptype:function(msg,o,cssctl){
			if(o.type==3){
				validationMessage(o.obj,msg);
			}else{
				removeMessage(o.obj);
			}
		},
		btnSubmit : "#btn_sub",
		btnReset : "#btn_reset",
		ajaxPost : true,
		beforeSubmit : function(curform) {
		},
		usePlugin : {
			passwordstrength : {
				minLen : 6,
				maxLen : 18,
				trigger : function(obj, error) {
					if (error) {
						obj.parent().next().find(".Validform_checktip").show();
						obj.find(".passwordStrength").hide();
					} else {
						$(".passwordStrength").show();
						obj.parent().next().find(".Validform_checktip").hide();
					}
				}
			}
		},
		callback : function(data) {
			var win = frameElement.api.opener;
			if (data.success == true) {
				frameElement.api.close();
			    win.reloadTable();
			    win.tip(data.msg);
			} else {
			    if (data.responseText == '' || data.responseText == undefined) {
			        $.messager.alert('错误', data.msg);
			        $.Hidemsg();
			    } else {
			        try {
			            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
			            $.messager.alert('错误', emsg);
			            $.Hidemsg();
			        } catch (ex) {
			            $.messager.alert('错误', data.responseText + "");
			            $.Hidemsg();
			        }
			    }
			    return false;
			}
		}
	});
});

var resultTemp;
var testBsSuggest = $("#flightno").bsSuggest({
	url: "famsCommonController/loadFlightData.do?keyword="+$("#flightno").val(),
    idField: "rownum",
    showBtn: false,
    showHeader: false,
    getDataMethod: 'data',
    allowNoKeyword: false,
    inputWarnColor: 'rgba(255,0,0,.1)', //输入框内容不是下拉列表选择时的警告色
    effectiveFields: ['flightno','crafttype','airlinefullcn'],            //有效显示于列表中的字段，非有效字段都会过滤，默认全部。
    effectiveFieldsAlias: {'flightno':'航班号','crafttype':'机型','airlinefullcn':'航线'},       //有效字段的别名对象，用于 header 的显示
    delay: 0.1,
    keyField:"flightno",  //每组数据的哪个字段作为输入框内容，优先级高于 indexKey 设置（推荐）
    indexId: 0,                     //每组数据的第几个数据，作为input输入框的 data-id，设为 -1 且 idField 为空则不设置此值
    indexKey: 1                    //每组数据的第几个数据，作为input输入框的内容

}).on('onDataRequestSuccess', function (e, result) {
    resultTemp=result;
}).on('onSetSelectValue', function (e, keyword) {	 
	$("#flightno").val(resultTemp.value[keyword.id-1].flightno);
	$("#flightregistrationnumber").val(resultTemp.value[keyword.id-1].ffid);
	/* $("#airlines").val(resultTemp.value[keyword.id-1].airlinescn); */
    $("#immediately").val(resultTemp.value[keyword.id-1].craftno);
	$("#models").val(resultTemp.value[keyword.id-1].crafttype);
    $("#slots").val(resultTemp.value[keyword.id-1].craftsite);
    $("#airPlanId").val(resultTemp.value[keyword.id-1].id);
    $("#routes").val(resultTemp.value[keyword.id-1].airlinefullcn);

    $("#plannedtime").val(resultTemp.value[keyword.id-1].est.substring(0,16));
	$("#planneddeparture").val(resultTemp.value[keyword.id-1].sst.substring(0,16));
	$("#actualtimearrival").val(resultTemp.value[keyword.id-1].eat.substring(0,16));
	$("#actualdeparture").val(resultTemp.value[keyword.id-1].sat.substring(0,16));
	
	$("#starstation").val(resultTemp.value[keyword.id-1].starstation );
	$("#starstationcn").val(resultTemp.value[keyword.id-1].starstationcn );
	$("#terminalstation").val(resultTemp.value[keyword.id-1].terminalstation );
	$("#terminalstationcn").val(resultTemp.value[keyword.id-1].terminalstationcn );
	$("#airlines").val(resultTemp.value[keyword.id-1].twocharcode );
	$("#airlinescn").val(resultTemp.value[keyword.id-1].airlinescn);
}).on('onUnsetSelectValue', function (e) {
	//$("#flightno").val(null);
	$("#flightregistrationnumber").val(null);
	$("#airlines").val(null);
    $("#immediately").val(null);
	$("#models").val(null);
    $("#slots").val(null);
    $("#airPlanId").val(null);
    $("#routes").val(null);
	$("#plannedtime").val(null);			 
	$("#planneddeparture").val(null);
	$("#actualtimearrival").val(null);
	$("#actualdeparture").val(null);
	$("#starstation").val(null);
	$("#starstationcn").val(null);
	$("#terminalstation").val(null);
	$("#terminalstationcn").val(null);
	$("#airlinescn").val(null);
});
</script>
</body>
</html>