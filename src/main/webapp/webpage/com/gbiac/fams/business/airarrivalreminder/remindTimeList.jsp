<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<style>
/*表格滚动条显示齐全*/
.datagrid-body {
	width: 99.5% !important;
}
/*查询选项一行显示*/
#tSSmsListForm>span>span {
	margin-left: 20px
}

#tSSmsListForm>span>span>span {
	width: auto !important;
}

.datagrid-row-selected {
	color: #2272CE !important;
} /*选中颜色*/
</style>
<![if !IE]>
<style>
select[name="isRead"] {
	padding: 0 4px;
}
</style>
<![endif]>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 0px; border: 0px">
		<t:datagrid name="tSSmsList" checkbox="true" fitColumns="false"
			title="" actionUrl="famsAirportrunwayPlaceController.do?mydatagrid" idField="id"
			fit="true" queryMode="group" sortName="esSendtime" sortOrder="desc"
			rowStyler="myRowStyle">
			<t:dgCol title="common.esId" field="id" hidden="true" formatterjs="formatter"
				queryMode="single"></t:dgCol>
			<t:dgCol title="common.reminderTime" field="reminderTime" formatterjs="formatter"
				queryMode="single" query="true"></t:dgCol>
			<t:dgCol title="common.role" formatterjs="func" replace="部门审核人_部门审核人,保洁人员_保洁人员,普通用户_普通用户,管理员_管理员,
			安全质量部_安全质量部,运行控制部_运行控制部,场道管理部_场道管理部,外部值班帐号_外部值班帐号,综合党群资产部_综合党群资产部,
			灯光管理部_灯光管理部,生态管理部_生态管理部,施工单位_施工单位,准入管理部_准入管理部,机坪监管部_机坪监管部"
				field="role" query="true" queryMode="single" width="130"></t:dgCol>
		</t:datagrid>
	</div>
</div>
<script type="text/javascript" charset="utf-8">

	//设置行样式
	function myRowStyle(index, row) {
		if (row.isRead != 1) {
			return 'font-weight:bold;';
		}
	}
	//文本显示title
	function formatter(val, row) {
		if (val) {
			return '<span title="' + val + '">' + val + '</span>';
		} else {
			return val;
		}
	}
	
	//下拉选改变触发事件
	function func(){
		function() {
			$.ajax({
	            url:"famsAirportrunwayPlaceController.do?role",
	            data:{},
	            dataType:"json",
	            success:function(result){
	            	console.log(result);
	                if(result.success){
	                	layer.msg(result.obj+'条消息标为已读', {
	        				time : 1000, //1s后自动关闭
	        				btn : []
	        			});
	                	$('#famsAirportrunwayPlaceList').datagrid('reload');
						//给父页面的标签赋值更新为最新值
						parent.$('#systemMsg').text('');
						parent.$('#systemMsg').attr('newNums', 0)
	                }else{
	                	layer.msg('操作失败，请重试', {
	        				time : 1500, //1.5s后自动关闭
	        				btn : []
	        			});
	                }
	            }
	        });
			
		}, function() {
			layer.msg('操作已取消', {
				time : 1000, //1s后自动关闭
				btn : []
			});
		});
		
	}

</script>


<%-- 
 
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>fams_unsafeevent_tiredamage</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base
	type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
<style>
.rd1 {
	padding-top: 8px;
}

.panel-body {
	border-style: none;
}

.mybutton {
	line-height: 20px;
}
</style>
</head>
<body style="overflow: hidden; overflow-y: auto;">
	<div class="container" style="width: 100%;">
		<div class="panel-heading"></div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="formobj"
				action="famsAirportrunwayPlaceController.do?doAdd2" method="POST">
				<input type="hidden" id="btn_sub" class="btn_sub" /> <input
					type="hidden" id="id" name="id" />
				<div class="row">
					<input name="no" type="hidden" class="form-control input-sm"
						value="${no}" maxlength="255" ignore="ignore" readonly="readonly" />

					<div style="line-height: 200%;">
						<input type="hidden" id="radioResult" name="radioResult" value="" />
						<table style="width: 100%">

							<tr>
								<td style="text-align: right; white-space: nowrap"
									class="col-md-1">提醒时间设置（分钟）：</td>
								<td class="rd1 col-md-3">
									oninput = "value=value.replace(/[^\d]/g,'')" 
									<input
									placeholder="30" name="woundsizelong" type="text"
									style="height: 26px" class="form-control input-sm"
									maxlength="100" value="${famsAirportrunwayPlace.reminderTime}" style="width: 20x" ignore="checked"
									datatype="d" step="0.01"
									oninput="value=value.replace(/[^\d|\/.]/g,'')" />
								</td>
							</tr>


							<tr>
								<td style="text-align: right;" class="col-md-1">角色：</td>
								<td class="rd1 col-md-3"><t:dictSelect
										field="role" dictTable="t_s_role" 
										dictField="rolename" dictText="rolename" 
										hasLabel="false" type="select"
										extendJson="{style:'width:100%'}"></t:dictSelect> 
								</td>
							</tr>
							
							<tr>
								<td>
									<div id="yes" class="yes">
										<button type="button" onclick="yes()">确定</button>
									</div>
								</td>
							</tr>
						</table>
						
					</div>
			</form>
		</div>
	</div>
</body>

<script>
	function yes(){
		var xmlhttp;
		if (window.XMLHttpRequest)
		{
			// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
			xmlhttp=new XMLHttpRequest();
		}
		else
		{
			// IE6, IE5 浏览器执行代码
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET","/try/ajax/demo_get.php",true);
		xmlhttp.send();

	}
</script>
</html> --%>