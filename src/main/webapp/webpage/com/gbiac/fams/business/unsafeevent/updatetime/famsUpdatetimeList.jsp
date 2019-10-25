<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>fams_updatetime</title>
<meta name="description"   />
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
<style type="text/css">
.fixed-table-container{
height:500px;
padding-bottom: 20px;
}

</style>
</head>
<body>
<t:datagrid name="famsUpdatetimeList" component="bootstrap-table" isShowSearch="false"  checkbox="false" sortName="createDate" height="500px" sortOrder="desc"  pagination="true" fitColumns="true" title="fams_updatetime" actionUrl="famsUpdatetimeController.do?datagrid&no=${idd}" idField="id"  fit="true" queryMode="single">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改时间"  field="intime"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="group"  width="50"></t:dgCol>
   <t:dgCol title="修改人"  field="people"  queryMode="group"  width="50"></t:dgCol>
   <t:dgCol title="修改内容"  field="added"  queryMode="group"  width="260"></t:dgCol>
   <%-- 
   <t:dgCol title="编号"  field="no"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属模块"  field="module"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="note"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="famsUpdatetimeController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="famsUpdatetimeController.do?goAdd" funname="add"  width="768"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsUpdatetimeController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="famsUpdatetimeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsUpdatetimeController.do?goUpdate" funname="detail"  width="768"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  <script type="text/javascript">
	 $(document).ready(function(){
	 //columns columns-right btn-group pull-right
	 $('.columns').hide();
	     $('#toolbar').hide();
		 setTimeout(function(){
		 var tableHeight=$('.fixed-table-container').css("height");
		 if(Number(tableHeight.split("px")[0])<50){
		    window.location.reload();
		 }
		 console.log(tableHeight);
			 //if(tableHeight)
		     //$('.fixed-table-container').css('height','500px');
			// window.location.reload();
         },10);
	 });
	 
	 //window.location.reload();
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsUpdatetimeController.do?upload', "famsUpdatetimeList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("famsUpdatetimeController.do?exportXls","famsUpdatetimeList");
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsUpdatetimeController.do?exportXlsByT","famsUpdatetimeList");
	}
	
	 </script>
</body>
</html>