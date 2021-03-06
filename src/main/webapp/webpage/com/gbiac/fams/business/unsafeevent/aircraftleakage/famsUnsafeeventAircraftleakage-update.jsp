<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>fams_unsafeevent_aircraftleakage</title>
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
    </style>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsUnsafeeventAircraftleakageController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${famsUnsafeeventAircraftleakage.id}"/>
		<div class="row">
		<input name="no" type="hidden" class="form-control input-sm" value="${famsUnsafeeventAircraftleakage.no}"  maxlength="255"  ignore="ignore"  readonly="readonly" />
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
		                        <input name="thedate" type="text" style="height:26px"  readonly="readonly" class="form-control input-sm laydate-date"  value="${famsUnsafeeventAircraftleakage.thedate}" ignore="checked" datatype="*"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" ><span style="color:red;">*</span>接报时间：</td>
                            <td class="rd1 col-md-3">
                            	<input placeholder="请输入接报时间" name="settime" value="${famsUnsafeeventAircraftleakage.settime}" type="text" style="height:26px"  readonly="readonly"  class="form-control input-sm laydate-ymdate" ignore="checked" datatype="*" id="hxdate" />
								<%-- <input name="settime" type="text" style="height:26px"  readonly="readonly" class="form-control input-sm laydate-ymdate" value="${famsUnsafeeventAircraftleakage.settime}" ignore="checked" datatype="*"  /> --%>
                            </td>
                            <td style="text-align: right;"  class="col-md-1" ><span style="color:red;">*</span>信息来源：</td>
                            <td class="rd1 col-md-3">
								<input name="sourceformation" type="text" style="height:26px"  class="form-control input-sm" maxlength="1000"  value="${famsUnsafeeventAircraftleakage.sourceformation}"  ignore="checked" datatype="s1-1000"   />
                            </td>
                            
                    </tr>
                    <tr>
                        <th style="text-align: left;">航班信息</th>
                        <th></th>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" ><span style="color:red;">*</span>航班号：</td>
                            <td class="rd1 col-md-3">
		                        <%-- <input id="flightno" name="flightno" type="text" style="height:26px"   value="${famsUnsafeeventAircraftleakage.flightno}" class="form-control input-sm" oninput="getflightno()" maxlength="50"   ignore="checked" datatype="s1-50"   />
                             --%>
							 <input id="flightn" type="hidden"  readonly="readonly"  value="${famsUnsafeeventAircraftleakage.flightno}"  ignore="ignore"  />
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
								<input id="flightregistrationnumber"  name="flightregistrationnumber"   value="${famsUnsafeeventAircraftleakage.flightregistrationnumber}"  type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td> --%>
                            <td style="text-align: right;"  class="col-md-1" >机型：</td>
                            <td class="rd1 col-md-3">
								<input id="models"  name="models" type="text" style="height:26px"  value="${famsUnsafeeventAircraftleakage.models}"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                    
                    		<td style="text-align: right;"  class="col-md-1" >机号：</td>
                            <td class="rd1 col-md-3">
		                        <input id="immediately"  name="immediately"  value="${famsUnsafeeventAircraftleakage.immediately}" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr>
                            <td style="text-align: right;"  class="col-md-1" >停机位：</td>
                            <td class="rd1 col-md-3">
								<input id="slots"  name="slots"  value="${famsUnsafeeventAircraftleakage.slots}" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >航空公司：</td>
                            <td class="rd1 col-md-3">
								<input id="airlinescn"  name="airlinescn"  value="${famsUnsafeeventAircraftleakage.airlinescn}" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                            
                    
                    		<td style="text-align: right;"  class="col-md-1" >航线：</td>
                            <td class="rd1 col-md-3">
		                        <input id="routes"  name="routes"  value="${famsUnsafeeventAircraftleakage.routes}" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr>
                            <td style="text-align: right;"  class="col-md-1" >计划进港时间：</td>
                            <td class="rd1 col-md-3">
								<input id="plannedtime"      value="${famsUnsafeeventAircraftleakage.plannedtime}"  readonly="readonly"  name="plannedtime" type="text" style="height:26px"  class="form-control input-sm  laydate-hxdate laydate-datetime"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >计划出港时间：</td>
                            <td class="rd1 col-md-3">
								<input id="planneddeparture"    value="${famsUnsafeeventAircraftleakage.planneddeparture}"   readonly="readonly"  name="planneddeparture" type="text" style="height:26px"  class="form-control input-sm  laydate-hxdate laydate-datetime"  ignore="ignore"  />
                            </td>
                            
                    
                    		<td style="text-align: right;"  class="col-md-1" >实际进港时间：</td>
                            <td class="rd1 col-md-3">
		                        <input id="actualtimearrival"    value="${famsUnsafeeventAircraftleakage.actualtimearrival}"   readonly="readonly"  name="actualtimearrival" type="text" style="height:26px"  class="form-control input-sm  laydate-hxdate laydate-datetime"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr>
                            <td style="text-align: right;"  class="col-md-1" >实际出港时间：</td>
                            <td class="rd1 col-md-3">
								<input id="actualdeparture"    value="${famsUnsafeeventAircraftleakage.actualdeparture}"   readonly="readonly"  name="actualdeparture" type="text" style="height:26px"  class="form-control input-sm  laydate-hxdate laydate-datetime"  ignore="ignore"  />
                            </td>
                            <input id="starstation" name="starstation" type="hidden"  value="${famsUnsafeeventAircraftleakage.starstation}" class="form-control input-sm"  maxlength="255"  ignore="ignore"  readonly="readonly" />
                            <input id="starstationcn" name="starstationcn" type="hidden"  value="${famsUnsafeeventAircraftleakage.starstationcn}" class="form-control input-sm"  maxlength="255"  ignore="ignore"  readonly="readonly" />
                            <input id="terminalstation" name="terminalstation" type="hidden"  value="${famsUnsafeeventAircraftleakage.terminalstation}" class="form-control input-sm"  maxlength="255"  ignore="ignore"  readonly="readonly" />
                            <input id="terminalstationcn" name="terminalstationcn" type="hidden"  value="${famsUnsafeeventAircraftleakage.terminalstationcn}" class="form-control input-sm"  maxlength="255"  ignore="ignore"  readonly="readonly" />
                            <input id="airlines" name="airlines" type="hidden"  value="${famsUnsafeeventAircraftleakage.airlines}" class="form-control input-sm"  maxlength="255"  ignore="ignore"  readonly="readonly" />
					</tr>
                    <tr>
                        <th style="text-align: left;">信息通报处置</th>
                        <th></th>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >通报场道时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="announcetracktime" placeholder="请选择通报场道时间" value="${famsUnsafeeventAircraftleakage.announcetracktime}"   readonly="readonly" type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;padding-left: 0;white-space:nowrap"  class="col-md-1" >通报值班领导时间：</td>
                            <td class="rd1 col-md-3">
								<input name="announcedutytime" placeholder="请选择通报值班领导时间" value="${famsUnsafeeventAircraftleakage.announcedutytime}"   readonly="readonly"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >通报AOC时间：</td>
                            <td class="rd1 col-md-3">
								<input name="notificationaoctime" placeholder="请选择通报AOC时间" value="${famsUnsafeeventAircraftleakage.notificationaoctime}"   readonly="readonly"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  ignore="ignore"  />
                            </td>
                            
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >通报机坪时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="transactiontime" placeholder="通报通报机坪时间" value="${famsUnsafeeventAircraftleakage.transactiontime}"   readonly="readonly" type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >漏油种类：</td>
                            <td class="rd1 col-md-3">
		                        <t:dictSelect field="oiltypes" typeGroupCode="oiltypes" hasLabel="false"  defaultVal="${famsUnsafeeventAircraftleakage.oiltypes}"  type="radio" extendJson="{  class:'isUseFire i-checks ischeckAta'}"></t:dictSelect>
								<input  id="oiltypesOther" name="oiltypesOther" readonly="readonly" value="${famsUnsafeeventAircraftleakage.oiltypesOther}" type="text"  maxlength="100"  style="width:30x " ignore="checked"  datatype="s0-100" placeholder="点击其他可修改" />
                            </td>
                            <td style="text-align: right;white-space:nowrap"  class="col-md-1" >漏油面积（㎡）：</td>
                            <td class="rd1 col-md-3">
		                        <input name="spillarea"  id="spillarea" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"   value="${famsUnsafeeventAircraftleakage.spillarea}" placeholder="请输入漏油面积（单位为㎡）"  onBlur='checkFloatTwo("spillarea")'  oninput = "value=value.replace(/[^\d.]/g,'');  value=value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');if(isNaN(value)){value = value.substr(0,value.length-1)}"  />
                            </td>
                    </tr>
                    
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >漏油原因：</td>
                            <td class="rd1 col-md-3">
		                        <t:dictSelect field="oilleakreason" typeGroupCode="leakreason" hasLabel="false"  defaultVal="${famsUnsafeeventAircraftleakage.oilleakreason}"  type="radio" extendJson="{  class:'isUseFire i-checks '}"></t:dictSelect>
								<input  id="oilleakreasonOther" name="oilleakreasonOther"  value="${famsUnsafeeventAircraftleakage.oilleakreasonOther}"  type="text"  maxlength="100"  style="width:30x " ignore="checked"  datatype="s0-100" placeholder="备注" />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >影响运行区域：</td>
                            <td class="rd1 col-md-3">
		                        <input name="affectedoperationarea" type="text" placeholder="请输入影响运行区域" style="height:26px"  class="form-control input-sm" maxlength="100"  value="${famsUnsafeeventAircraftleakage.affectedoperationarea}"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;padding-left: 0;white-space:nowrap"  class="col-md-1" >启动燃油溢漏预案：</td>
                            <td class="rd1 col-md-3">
		                        <t:dictSelect field="initiatefuelspillplan" typeGroupCode="is_main" hasLabel="false"  defaultVal="${famsUnsafeeventAircraftleakage.initiatefuelspillplan}"  type="radio" extendJson="{  class:'isUseFire i-checks ischeckAtt'}"></t:dictSelect>
                            </td>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >启动时间：</td>
                            <td class="rd1 col-md-3">
		                        <input id="startuptime" name="startuptime" type="text" placeholder="请选择启动时间" style="height:26px"   disabled="disabled"  onblur="checkTimeSequence(this,'startuptime','lifttime')" value="${famsUnsafeeventAircraftleakage.startuptime}"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >解除时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="lifttime" id="lifttime"type="text" placeholder="请选择解除时间" style="height:26px"  disabled="disabled"   onblur="checkTimeSequence(this,'startuptime','lifttime')" value="${famsUnsafeeventAircraftleakage.lifttime}"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                            <%--  <td style="text-align: right;"  class="col-md-1" >检查方向：</td>
                            <td class="rd1 col-md-3">
                            	<t:dictSelect field="checdirection" typeGroupCode="cdirection" hasLabel="false" type="radio" defaultVal="${famsUnsafeeventAircraftleakage.checdirection}" extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect>
                            </td> --%>
                    </tr>
                    
                    <tr>
                        <th style="text-align: left;">清理恢复</th>
                        <th></th>
                    </tr>
                    
                    <tr>
                    		<td style="text-align: right;padding-left: 0;white-space:nowrap"  class="col-md-1" >油迹清理完毕时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="oilcleaningsite" placeholder="请选择现场油迹清理完毕时间" value="${famsUnsafeeventAircraftleakage.oilcleaningsite}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >飞机推出时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="aircraftlaunchtime" placeholder="请选择飞机推出时间" value="${famsUnsafeeventAircraftleakage.aircraftlaunchtime}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >机位可用时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="availabletime" placeholder="请选择机位可用时间" value="${famsUnsafeeventAircraftleakage.availabletime}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr>
                    		<td style="text-align: right;padding-left: 0"  class="col-md-1" >滑行道可用时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="relevanttaxiwayavailabilitytime" placeholder="请选择相关滑行道可用时间" value="${famsUnsafeeventAircraftleakage.relevanttaxiwayavailabilitytime}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >通报AOC时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="notificationofaoctime" placeholder="请选择通报AOC时间"  value="${famsUnsafeeventAircraftleakage.notificationofaoctime}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;padding-left: 0;white-space:nowrap"  class="col-md-1" >通报值班领导时间：</td>
                            <td class="rd1 col-md-3">
		                        <input name="announcetheleaderonduty" placeholder="请选择通报值班领导时间" value="${famsUnsafeeventAircraftleakage.announcetheleaderonduty}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >补充说明：</td>
                            <td class="rd1 col-md-3">
                            	<textarea  id="addede" name="added" cols="30" rows="5" maxlength="2000" placeholder="请输入字的信息" style="width:100%"></textarea>
                            	<input id="added" type="hidden"  readonly="readonly"  value="${famsUnsafeeventAircraftleakage.added}"  ignore="ignore"  />
		                        <!-- <input name="added" type="text" style="height:26px"  class="form-control input-sm" maxlength="10"  ignore="ignore"  /> -->
                            </td>
                    </tr>
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >图片：</td>

                            <td class="rd1" colspan="5">
		                        <div>
							        <jsp:include page= "/webpage/common/jsp/upload.jsp">
							        	<jsp:param name= "name" value= "files"/>
							            <jsp:param name= "ids" value= "${files.ids}"/>
							            <jsp:param name= "value" value= "${files.paths}"/>
							            <jsp:param name= "fileNames" value= "${files.fileNames}"/>
							            <jsp:param name= "sizes" value= "${files.sizes}"/>
							            <jsp:param name= "types" value="${files.types}" />
							            <jsp:param name= "directory" value= "famsUnsafeeventAircraftleakage"/>
							            <jsp:param name= "multiple" value= "multiple"/>
							            <jsp:param name= "required" value= ""/>
							            <jsp:param name= "disable" value= ""/>
							            <jsp:param name= "typeSwf" value= "0"/>
							            <jsp:param name= "acceptFileType" value= "image/*"/>
							        </jsp:include>
							    </div>
                            </td>
                    </tr>
                    <tr>
                        <th style="text-align: left;">特殊情况</th>
                        <th></th>
                    </tr>
                    
                    <tr>
                    		<td style="text-align: right;"  class="col-md-1" >飞机自主转弯：</td>
                            <td class="rd1 col-md-3">
                                <%--extendJson="{  class:'isUseFire i-checks'}"--%>
		                        <t:dictSelect field="autonomousturning" typeGroupCode="keyi" hasLabel="false"  type="radio"  defaultVal="${famsUnsafeeventAircraftleakage.autonomousturning}"  extendJson="{class:'isUseFire i-checks ischeckAt'}"></t:dictSelect>
                            </td>
                            <td></td>
                            <td class="rd1 col-md-3">
                            </td>
                            <td></td>
                            <td class="rd1 col-md-3">
                            </td>
                    </tr>
                    <tr class="autonomousturning">
                    		<td style="text-align: right;white-space:nowrap"  class="col-md-1" >引领车辆等待位置：</td>
                            <td class="rd1 col-md-3">
		                        <input id="guidevehicleswaitingposition"  name="guidevehicleswaitingposition" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  value='${famsUnsafeeventAircraftleakage.guidevehicleswaitingposition}'  />
                            </td>
                    		<td style="text-align: right;white-space:nowrap"  class="col-md-1" >拖车到达等待点时间：</td>
                            <td class="rd1 col-md-3">
		                        <input id="trailerarrivalwaitingtime"  name="trailerarrivalwaitingtime" value="${famsUnsafeeventAircraftleakage.trailerarrivalwaitingtime}"  type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;white-space:nowrap"  class="col-md-1" >引领车辆到达等待点时间：</td>
                            <td class="rd1 col-md-3">
		                        <input id="leadvehiclethewaitingpointtime"  name="leadvehiclethewaitingpointtime" value="${famsUnsafeeventAircraftleakage.leadvehiclethewaitingpointtime}"   type="text" style="height:26px"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                            
                    </tr>
                    <tr class="autonomousturning">
                    		<td style="text-align: right;"  class="col-md-1" >引领路线：</td>
                            <td class="rd1 col-md-3">
		                        <input id="leadline"  name="leadline" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  value='${famsUnsafeeventAircraftleakage.leadline}' ignore="ignore"  />
                            </td>
                    		<td style="text-align: right;"  class="col-md-1" >开始引领时间：</td>
                            <td class="rd1 col-md-3">
		                        <input id="leadtime"  name="leadtime" type="text" style="height:26px"   value="${famsUnsafeeventAircraftleakage.leadtime}"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                            <td style="text-align: right;"  class="col-md-1" >飞机进位时间：</td>
                            <td class="rd1 col-md-3">
		                        <input id="aircraftcarrytime"  name="aircraftcarrytime" type="text" style="height:26px"  value="${famsUnsafeeventAircraftleakage.aircraftcarrytime}"  class="form-control input-sm laydate-hxdate"  readonly="readonly"  ignore="ignore"  />
                            </td>
                    </tr>
                    <tr class="autonomousturning">
                            <td style="text-align: right;"  class="col-md-1" >飞机拖动路线：</td>
                            <td class="rd1 col-md-3">
		                        <input id="aircraftdragroute"  name="aircraftdragroute" type="text" style="height:26px"  class="form-control input-sm" maxlength="255"  value='${famsUnsafeeventAircraftleakage.aircraftdragroute}' ignore="ignore"  />
                            </td>
                    </tr>
                    <tr class="autonomousturning">
                    		<td style="text-align: right;"  class="col-md-1" >拖车拖动补充说明：</td>
                            <td class="rd1 col-md-3">
                            <textarea id="trailerdragaddede"  name="trailerdragadded" cols="30" rows="5" maxlength="2000"  value='${famsUnsafeeventAircraftleakage.trailerdragadded}' style="width:100%"></textarea></div>
							<input id="trailerdragadded" type="hidden"  readonly="readonly"  value="${famsUnsafeeventAircraftleakage.trailerdragadded}"  ignore="ignore"  />
                            </td>
                    </tr>
            </table>
        </div>
	</form>
	</div>
 </div>
  <script src="plug-in/hplus/plugins/suggest/bootstrap-suggest.min.js"></script>
<script type="text/javascript">
var subDlgIndex = '';
function checkFloatTwo(obj){
	var a = $('#'+obj).val();
	var aa = Number(a).toFixed(2)<=0?'':Number(a).toFixed(2);
	if(aa.indexOf(".00")!=-1){
	  $('#'+obj).val( Number(aa.replace('.00','')));
	}else{
		if(aa.indexOf(".")!=-1 && aa.substr(aa.length-1,1)==0 ){
		$('#'+obj).val(aa.substr(0,aa.length-1));
		}else{
		$('#'+obj).val(aa);
		}	
	}
}
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
          // alert(res.data[0].flightno);
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

function autonomousturningfun(){
	var strgetSelectValue="";
	var getSelectValueMenbers = $("input[name='autonomousturning']:checked").val(); 
	if(getSelectValueMenbers == 'bukeyi'){
		$(".autonomousturning").css("display","");

	}
	
$('.ischeckAtt').on('ifChecked', function(event){
		
		var getSelectValueMenbers = $("input[name='initiatefuelspillplan']:checked").val(); 
		
		if(getSelectValueMenbers == 'Y'){
			//alert(getSelectValueMenbers);
			$("#startuptime").removeAttr("disabled");
			$("#lifttime").removeAttr("disabled");

		}
		if(getSelectValueMenbers == 'N'){
			$("#startuptime").val("");
			$("#lifttime").val("");
			$("#startuptime").attr("disabled","disabled");
			$("#lifttime").attr("disabled","disabled");


		}
    });
    
	if(getSelectValueMenbers == 'keyi'){
		$("#guidevehicleswaitingposition").val("");
		$("#trailerarrivalwaitingtime").val("");
		$("#leadvehiclethewaitingpointtime").val("");
		$("#leadline").val("");
		$("#leadtime").val("");
		$("#aircraftcarrytime").val("");
		$("#aircraftdragroute").val("");
		$("#autonomousturning").val("");
		$("#trailerdragaddede").val("");
		$(".autonomousturning").css("display","none");
		
	}
}
function oiltypesOtherType(){
	var damageType =$("input[name='oiltypes']:checked").val();
	if(damageType == '其它'){
		$("#oiltypesOther").removeAttr("readonly");
	}else{
		$("#oiltypesOther").attr("readonly","readonly");
		$("#oiltypesOther").val('');
	}
}
function oilleakreasonOtherType(){
	
	var damageType =$("input[name='oilleakreason']:checked").val();
	
	if(damageType == '其它'){

		$("#oilleakreasonOther").removeAttr("readonly");
	}else{

		$("#oilleakreasonOther").attr("readonly","readonly");
		$("#oilleakreasonOther").val('');
	}
}

$(document).ready(function() {
$("#flightno").val($("#flightn").val());
var getSelectValueMenbers = $("input[name='initiatefuelspillplan']:checked").val(); 

if(getSelectValueMenbers == 'Y'){
	//alert(getSelectValueMenbers);
	$("#startuptime").removeAttr("disabled");
	$("#lifttime").removeAttr("disabled");

}
if(getSelectValueMenbers == 'N'){
	$("#startuptime").val("");
	$("#lifttime").val("");
	$("#startuptime").attr("disabled","disabled");
	$("#lifttime").attr("disabled","disabled");


}
	$('.ischeckAta').on('ifChecked', function(event){
		oiltypesOtherType();
    });
    /** dzh 20190417 漏油原因 :飞机故障或其他都可进行备注
	 $('.ischeckAtz').on('ifChecked', function(event){
		 oilleakreasonOtherType();
    })
	oilleakreasonOtherType();
      */
	oiltypesOtherType();
	$(".autonomousturning").css("display","none");
	autonomousturningfun();
	$('.ischeckAt').on('ifChecked', function(event){
            var strgetSelectValue="";
			var getSelectValueMenbers = $("input[name='autonomousturning']:checked").val(); 
			if(getSelectValueMenbers == 'bukeyi'){
				$(".autonomousturning").css("display","");
			}
			if(getSelectValueMenbers == 'keyi'){
				$("#guidevehicleswaitingposition").val("");
				$("#trailerarrivalwaitingtime").val("");
				$("#leadvehiclethewaitingpointtime").val("");
				$("#leadline").val("");
				$("#leadtime").val("");
				$("#aircraftcarrytime").val("");
				$("#aircraftdragroute").val("");
				$("#autonomousturning").val("");
				$("#trailerdragadded").val("");
				$(".autonomousturning").css("display","none");
			}
        });
	
	$("#addede").val($("#added").val());
	$("#trailerdragaddede").val($("#trailerdragadded").val());
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
		  elem: this,
		  max: crtTimeFtt2(new Date()),
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
	/* $("#airlines").val(resultTemp.value[keyword.id-1].twocharcode); */
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