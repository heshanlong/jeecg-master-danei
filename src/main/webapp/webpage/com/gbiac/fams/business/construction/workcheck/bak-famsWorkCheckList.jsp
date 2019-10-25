<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>例行检查表</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsWorkCheckList" component="bootstrap-table"  checkbox="true" sortName="createDate"  sortOrder="desc"  pagination="true" fitColumns="true" title="例行检查表" actionUrl="famsWorkCheckController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关联的业务主表id"  field="bid"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="左上编号"  field="number"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="numberContent" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查单位"  field="checkDepart"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查时间"  field="checkTime" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="建设单位"  field="buildDepart"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="施工项目"  field="workName"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="施工地点"  field="workPlace" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="施工单位"  field="workDepart" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="施工队进场时间"  field="workStartTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="施工预计结束时间"  field="workEndTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="车牌号"  field="carNumber"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="驾驶员"  field="carDriver"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="驾驶证号"  field="driverLicense"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单位"  field="carDepart"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查结果"  field="checkResult"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="整改结果"  field="repairResult"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查小结"  field="checkResultNote" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="整改意见"  field="checkRepairNote" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="安全员签字"  field="aqSignature"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="飞行区管理部机坪监管部签字"  field="fxSignature"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程实例id"  field="procinstId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="famsWorkCheckController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="famsWorkCheckController.do?goAdd" funname="add"  width="800" height="500"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsWorkCheckController.do?goUpdate" funname="update"  width="800" height="500"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="famsWorkCheckController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsWorkCheckController.do?goUpdate" funname="detail"  width="800" height="500"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  <script type="text/javascript">
	 $(document).ready(function(){
	 });
	 
	   
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsWorkCheckController.do?upload', "famsWorkCheckList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("famsWorkCheckController.do?exportXls","famsWorkCheckList");
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsWorkCheckController.do?exportXlsByT","famsWorkCheckList");
	}
	
	 </script>
</body>
</html>