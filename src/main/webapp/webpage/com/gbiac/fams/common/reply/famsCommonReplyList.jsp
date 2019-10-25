<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>通用回复表</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsCommonReplyList" component="bootstrap-table"  checkbox="true" sortName="id"   sortOrder="asc"  pagination="true" fitColumns="true" title="通用回复表" actionUrl="famsCommonReplyController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="模块名称"  field="module"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务ID"  field="businessId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="state"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="回复部门id"  field="replyDepartId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="回复人id"  field="replyUserId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="回复目标任务id"  field="replyToUserId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="回复内容"  field="replyContent"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="冗余字段1"  field="filed1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="冗余字段2"  field="filed2"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="famsCommonReplyController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="famsCommonReplyController.do?goAdd" funname="add"  width="768"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsCommonReplyController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="famsCommonReplyController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsCommonReplyController.do?goUpdate" funname="detail"  width="768"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  <script type="text/javascript">
	 $(document).ready(function(){
	 });
	 
	   
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsCommonReplyController.do?upload', "famsCommonReplyList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("famsCommonReplyController.do?exportXls","famsCommonReplyList");
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsCommonReplyController.do?exportXlsByT","famsCommonReplyList");
	}
	
	 </script>
</body>
</html>