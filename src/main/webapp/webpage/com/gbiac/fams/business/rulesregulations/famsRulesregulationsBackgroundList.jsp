<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<script src="webpage/com/gbiac/fams/business/rulesregulations/jquery.easyui.min.1.3.2.js" type="text/javascript"></script>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="famsRulesregulationsList" checkbox="true" pagination="true" treegrid="true" treeField="rulesName" fitColumns="true" title="规章制度" sortName="createDate" actionUrl="famsRulesregulationsController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="父目录"  field="pid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd HH:mm:ss"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="格式"  field="rulesType" image="true" hidden="true" queryMode="single"  width="20"></t:dgCol>
   <t:dgCol title="格式"  field="rulesFileIcon" hidden="false" image="true" queryMode="single"  width="20"></t:dgCol>
   <t:dgCol title="名称"  field="rulesName" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="50"></t:dgCol>

   <%--<t:dgCol title="签发时间"  field="sendTime"  formatter="yyyy-MM-dd hh:mm"  query="true"  queryMode="group"  width="200"></t:dgCol>--%>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd hh:mm:ss"  hidden="false"  query="true"  queryMode="group"  width="50"></t:dgCol>
   <t:dgCol title="后缀"  field="rulesSuffix" queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="文件路径"  field="rulesPathUrl" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="后缀"  field="iconCls" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   
   <t:dgFunOpt funname="previewFile(id,rulesPathUrl)" title="预览" exp="rulesType#eq#1" urlStyle="background-color:#2590dc;" urlclass="ace_button"  urlfont="fa-search"></t:dgFunOpt>
   <t:dgFunOpt funname="downloadFile(rulesPathUrl)" title="下载" exp="rulesType#eq#1" urlStyle="background-color:#2590dc;" urlclass="ace_button"  urlfont="fa-search"></t:dgFunOpt>
   
   
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
		$("#famsRulesregulationsList").treegrid({
 				 onExpand : function(row){
 					var children = $("#famsRulesregulationsList").treegrid('getChildren',row.id);
 					 if(children.length<=0){
 					 	row.leaf=true;
 					 	$("#famsRulesregulationsList").treegrid('refresh', row.id);
 					 }
 				}
 		});
		
		$(".tree-icon").removeAttr();
		
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'famsRulesregulationsController.do?upload', "famsRulesregulationsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("famsRulesregulationsController.do?exportXls","famsRulesregulationsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("famsRulesregulationsController.do?exportXlsByT","famsRulesregulationsList");
}
//下载文件
function downloadFile(t) {
	JeecgExcelExport("famsRulesregulationsController.do?export&rulesPathUrl="+t,"famsRulesregulationsList");
	//createwindow("下载文件","famsRulesregulationsController.do?download&rulesPathUrl="+t,600,600);
}
//预览
function previewFile(id,t){
	createwindow("添加根目录","famsRulesregulationsController.do?openViewFile&fileid="+id+"&rulesPathUrl="+t,600,600);
}

//添加根目录文件夹
function addFolder(id,t){
	createwindow("添加根目录","famsRulesregulationsController.do?goAdd&id="+id,600,250);
}


//上传文件夹
function addFile(id,t){
	createwindow("添加文件","famsRulesregulationsController.do?goAddFile&id="+id,600,250);
}

/**
 * 获取表格对象
 * @return 表格对象
 */
function getDataGrid(){
	var datagrid = $('#'+gridname);
	return datagrid;
}
 </script>
