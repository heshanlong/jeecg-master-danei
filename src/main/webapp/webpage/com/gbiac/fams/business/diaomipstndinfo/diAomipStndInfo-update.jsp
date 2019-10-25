<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>机位管理test</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="diAomipStndInfoController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${diAomipStndInfoPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								编号:
							</label>
						</td>
						<td class="value">
						    <input id="stndCode" name="stndCode" type="text" maxlength="5" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${diAomipStndInfoPage.stndCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								机位:
							</label>
						</td>
						<td class="value">
						    <input id="stndCnnm" name="stndCnnm" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${diAomipStndInfoPage.stndCnnm}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">机位</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
