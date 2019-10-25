<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>施工安全员信息表</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsWorkSafePersonList" component="bootstrap-table"  checkbox="true" sortName="id"   sortOrder="asc"  pagination="true" fitColumns="true" title="施工安全员信息表" actionUrl="famsWorkSafePersonController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="施工申请"  field="approveId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="人员角色"  field="personRole"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="人员姓名"  field="personName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="人员电话"  field="personPhone"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="famsWorkSafePersonController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="famsWorkSafePersonController.do?goAdd" funname="add"  width="768"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsWorkSafePersonController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="famsWorkSafePersonController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsWorkSafePersonController.do?goUpdate" funname="detail"  width="768"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  <script type="text/javascript">
	 $(document).ready(function(){
	 });
	 
	   
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsWorkSafePersonController.do?upload', "famsWorkSafePersonList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("famsWorkSafePersonController.do?exportXls","famsWorkSafePersonList");
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsWorkSafePersonController.do?exportXlsByT","famsWorkSafePersonList");
	}
	
	 </script>
</body>
</html>