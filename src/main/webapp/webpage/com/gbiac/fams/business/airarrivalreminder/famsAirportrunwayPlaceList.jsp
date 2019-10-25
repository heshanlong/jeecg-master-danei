<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 0px; border: 0px">
		<t:datagrid name="famsAirportrunwayPlaceList" checkbox="false"
			pagination="true" fitColumns="true" title="航班入位提醒"
			sortName="createDate"
			actionUrl="famsAirportrunwayPlaceController.do?datagrid" idField="id"
			fit="true" queryMode="group">
			<t:dgCol title="主键" field="id" hidden="true" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="创建人名称" field="createName" hidden="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="创建人登录名称" field="createBy" hidden="true"
				queryMode="single" width="120"></t:dgCol>			
			<t:dgCol title="更新人名称" field="updateName" hidden="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="更新人登录名称" field="updateBy" hidden="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="更新日期" field="updateDate" formatter="yyyy-MM-dd"
				hidden="true" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="所属部门" field="sysOrgCode" hidden="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="所属公司" field="sysCompanyCode" hidden="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="流程状态" field="bpmStatus" hidden="true"
				queryMode="single" dictionary="bpm_status" width="120"></t:dgCol>
			<t:dgCol title="编号" field="numbering" query="true" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="区域" field="area" query="true" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="机位" field="position" query="true" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="航班号" field="flightNumber" query="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="预计落地时间" field="landingTime"
				formatter="yyyy-MM-dd hh:mm:ss" queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="接收人" field="receiver" query="true" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="角色" field="role" query="true" queryMode="single"
				width="120"></t:dgCol>
			<t:dgCol title="发送时间" field="sendingTime"
				formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"
				width="120"></t:dgCol>
			<t:dgCol title="创建日期" field="createDate" formatter="yyyy-MM-dd"
				hidden="true" queryMode="group" query="true" width="120"></t:dgCol>
			<t:dgCol title="提醒时间设置" field="reminderTime" hidden="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
			<t:dgDelOpt title="删除"
				url="famsAirportrunwayPlaceController.do?doDel&id={id}"
				urlclass="ace_button" urlfont="fa-trash-o" />
			<t:dgFunOpt funname="doView(id)" title="查看" urlclass="ace_button"
				urlfont="fa-wrench" />
			<t:dgToolBar title="录入" icon="icon-add"
				url="famsAirportrunwayPlaceController.do?goAdd" funname="add"></t:dgToolBar>
			<t:dgToolBar title="编辑" icon="icon-edit"
				url="famsAirportrunwayPlaceController.do?goUpdate" funname="update"></t:dgToolBar>
			<t:dgToolBar title="批量删除" icon="icon-remove"
				url="famsAirportrunwayPlaceController.do?doBatchDel"
				funname="deleteALLSelect"></t:dgToolBar>
			<t:dgToolBar title="查看" icon="icon-search"
				url="famsAirportrunwayPlaceController.do?goUpdate" funname="detail"></t:dgToolBar>
			<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
			<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
			<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
			<t:dgToolBar title="提醒时间设置" icon="icon-edit"
				funname="doReminder_time_setting"></t:dgToolBar>

		</t:datagrid>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
	});

	//自定义按钮-提醒时间设置
	function doReminder_time_setting(title, url, gridname) {
		javascript:
			createdetailwindow('提醒时间设置',
			'famsAirportrunwayRemindTimeController.do?list',800,400,'关闭');
	}

	//自定义按钮-查看
	function doView(id, index) {
		//选中当前行
		$("#famsAirportrunwayPlaceList").datagrid('selectRow', index);
		//查看选中行详情
		detail('查看', 'famsAirportrunwayPlaceController.do?goUpdate',
				'famsAirportrunwayPlaceList', null, null);
	}

	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsAirportrunwayPlaceController.do?upload',
				"famsAirportrunwayPlaceList");
	}

	//导出
	function ExportXls() {
		JeecgExcelExport("famsAirportrunwayPlaceController.do?exportXls",
				"famsAirportrunwayPlaceList");
	}

	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsAirportrunwayPlaceController.do?exportXlsByT",
				"famsAirportrunwayPlaceList");
	}
</script>
<script type="text/javascript">
	//JS增强
	function view() {

	}
</script>
