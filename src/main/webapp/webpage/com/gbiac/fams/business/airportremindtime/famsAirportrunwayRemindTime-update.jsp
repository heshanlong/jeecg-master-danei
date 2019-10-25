<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>航班入位提醒时间设置表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="famsAirportrunwayRemindTimeController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${famsAirportrunwayRemindTimePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								角色名称:
							</label>
						</td>
						<td class="value">
							<input id="rolename" name="rolename" type="text" style="width: 150px" class="searchbox-inputtext"  ignore="ignore"  onclick="popupClick(this,'rolecode,rolename','rolecode,rolename','role_list')" value='${famsAirportrunwayRemindTimePage.rolename}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">角色名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								角色编码:
							</label>
						</td>
						<td class="value">
						    <input id="rolecode" readonly="readonly" name="rolecode" type="text" maxlength="32" style="width: 150px" class="inputxt"  validType="fams_airportrunway_remind_time,rolecode,id" datatype="*" ignore="ignore"  value='${famsAirportrunwayRemindTimePage.rolecode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">角色编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								提醒时间:
							</label>
						</td>
						<td class="value">
						    <input id="remindTime" name="remindTime" type="text" maxlength="32" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${famsAirportrunwayRemindTimePage.remindTime}'/>分钟
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提醒时间</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
