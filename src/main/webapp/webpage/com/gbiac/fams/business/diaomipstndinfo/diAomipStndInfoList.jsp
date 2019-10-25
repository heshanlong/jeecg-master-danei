<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="diAomipStndInfoList" checkbox="true" pagination="true" fitColumns="true" title="机位管理test" sortName="id" actionUrl="diAomipStndInfoController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="stndCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="机位"  field="stndCnnm"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="stndEnnm"  field="stndEnnm"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="Y远机位N近机位"  field="stndRemt"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="stndTmlc"  field="stndTmlc"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="stus"  field="stndStus"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="stndStat"  field="stndStat"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="createTime"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="updateTime"  field="updateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="stat"  field="stat"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="diAomipStndInfoController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="diAomipStndInfoController.do?goAdd" funname="add"></t:dgToolBar>
	<t:dgToolBar title="编辑" icon="icon-edit" url="diAomipStndInfoController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="diAomipStndInfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="diAomipStndInfoController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'diAomipStndInfoController.do?upload', "diAomipStndInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("diAomipStndInfoController.do?exportXls","diAomipStndInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("diAomipStndInfoController.do?exportXlsByT","diAomipStndInfoList");
}

 </script>
