<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>作业角色表</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
  <t:datagrid name="famsWorkRoleList"  component="bootstrap-table"  checkbox="true" sortName="id"   sortOrder="asc"   pagination="true" fitColumns="true" title="作业角色表" actionUrl="famsWorkRoleController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="角色名称"  field="roleName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="角色code"  field="roleCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="famsWorkRoleController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="famsWorkRoleController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsWorkRoleController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="famsWorkRoleController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsWorkRoleController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  <script type="text/javascript">
	 $(document).ready(function(){
	 });
	 
	   
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsWorkRoleController.do?upload', "famsWorkRoleList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("famsWorkRoleController.do?exportXls","famsWorkRoleList");
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsWorkRoleController.do?exportXlsByT","famsWorkRoleList");
	}
	
	 </script>
</body>
</html>