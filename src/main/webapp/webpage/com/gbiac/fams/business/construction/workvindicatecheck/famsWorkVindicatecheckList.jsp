<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>维护作业检查表</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsWorkVindicatecheckList" component="bootstrap-table"  checkbox="true" sortName="createDate"  sortOrder="desc"  pagination="true" fitColumns="true" title="维护作业检查表" actionUrl="famsWorkVindicatecheckController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  hidden="true" formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关联的业务主表id"  field="bid"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="左上编号"  field="number"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="编号"      query="true"  field="numberContent"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查单位"  field="checkDepart"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="检查时间"  query="true"  field="checkTime"  formatter="yyyy-MM-dd hh:mm"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="建设单位"  field="buildDepart"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="作业项目"  field="workName"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="作业地点"  query="true" field="workPlace"  queryMode="single"  width="120" showLen="15"></t:dgCol>
   <t:dgCol title="作业单位"  query="true" field="workDepart"  queryMode="single"  width="120" showLen="15"></t:dgCol>
   <t:dgCol title="作业队进场时间"  field="workStartTime"  hidden="true" formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="作业预计结束时间"  field="workEndTime"  hidden="true" formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="检查结果"  field="checkResult" showLen="10"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="整改结果"  field="repairResult"  showLen="10" hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="检查小结"  query="true" field="checkResultNote"  queryMode="single" showLen="15" width="120"></t:dgCol>
   <t:dgCol title="整改意见"  field="checkRepairNote" showLen="10" hidden="true" query="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  formatter="yyyy-MM-dd hh:mm"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="录入人"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="建设单位签字"  field="jsSignature"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="飞行区管理部签字"  field="fxSignature"  hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="流程实例id"  field="procinstId" hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="famsWorkVindicatecheckController.do?doDel&id={id}"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="famsWorkVindicatecheckController.do?goAdd" funname="add"  width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsWorkVindicatecheckController.do?goUpdate" funname="update"  width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="famsWorkVindicatecheckController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsWorkVindicatecheckController.do?goUpdate" funname="detail"  width="100%" height="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  <script type="text/javascript">
	 $(document).ready(function(){
	 });
	 
	   
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsWorkVindicatecheckController.do?upload', "famsWorkVindicatecheckList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("famsWorkVindicatecheckController.do?exportXls","famsWorkVindicatecheckList");
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsWorkVindicatecheckController.do?exportXlsByT","famsWorkVindicatecheckList");
	}
	
	 </script>
</body>
</html>