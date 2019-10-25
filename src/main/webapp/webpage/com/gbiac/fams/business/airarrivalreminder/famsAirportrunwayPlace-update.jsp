<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>航班入位提醒</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="famsAirportrunwayPlaceController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${famsAirportrunwayPlacePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<div>航班入位提醒</div>
					<tr>
						<td align="right">
							<label class="Validform_label">
								编号:
							</label>
						</td>
						<td class="value">
						    <input id="numbering" name="numbering" type="text" maxlength="32" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${famsAirportrunwayPlacePage.numbering}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								区域:
							</label>
						</td>
						<td class="value">
						    <input id="area" name="area" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${famsAirportrunwayPlacePage.area}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">区域</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								机位:
							</label>
						</td>
						<td class="value">
						    <input id="position" name="position" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${famsAirportrunwayPlacePage.position}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">机位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								航班号:
							</label>
						</td>
						<td class="value">
						    <input id="flightNumber" name="flightNumber" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${famsAirportrunwayPlacePage.flightNumber}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">航班号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								预计落地时间:
							</label>
						</td>
						<td class="value">
									   <input id="landingTime" name="landingTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  ignore="ignore"  value='<fmt:formatDate value='${famsAirportrunwayPlacePage.landingTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'/>
						      	
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预计落地时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								接收人:
							</label>
						</td>
						<td class="value">
						    <input id="receiver" name="receiver" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${famsAirportrunwayPlacePage.receiver}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接收人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								角色:
							</label>
						</td>
						<td class="value">
						    <input id="role" name="role" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${famsAirportrunwayPlacePage.role}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">角色</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发送时间:
							</label>
						</td>
						<td class="value">
									   <input id="sendingTime" name="sendingTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  ignore="ignore"  value='<fmt:formatDate value='${famsAirportrunwayPlacePage.sendingTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'/>
						      	
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送时间</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
