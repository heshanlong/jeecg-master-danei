<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>关注机位</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="famsAttentionCraftsiteUserController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${famsAttentionCraftsiteUserPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							机位:
						</label>
					</td>
					<td class="value">
							<input id="craftsiteSeria" name="craftsiteSeria" type="text" style="width: 150px" class="searchbox-inputtext"  datatype="*" ignore="checked"   onclick="popupClick(this,'stnd_code','craftsiteSeria','select_stnd')"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">机位</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
							<input id="userName" name="userName" type="text" style="width: 150px" class="searchbox-inputtext"  datatype="*" ignore="checked"   onclick="popupClick(this,'id,realname','userId,userName','select_user')"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
