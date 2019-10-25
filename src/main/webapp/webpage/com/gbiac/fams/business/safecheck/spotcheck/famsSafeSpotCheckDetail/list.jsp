<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="jformOrderTicket2List" filterBtn="true" onDblClick="datagridDbclick" checkbox="true" pagination="true" fitColumns="true" title="" actionUrl="famsSafeSpotCheckDetailController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id" extendParams="editor:'text'" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查人"  field="checkPerson"  extendParams="editor:'text'"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="航班号"  field="flightno"  extendParams="editor:'text'"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="机号"  field="immediately"  extendParams="editor:'text'"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="机型"  field="models"  extendParams="editor:'text'"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="停机位"  field="slots"  extendParams="editor:'text'"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查情况"  field="checkDetail"  extendParams="editor:'text'"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查开始时间"  field="checkDateStart"  formatter="hh:mm:ss"  filterType="datebox" extendParams="editor:{type:'datetimebox',options:{onShowPanel:initDateboxformat}}"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查结束时间"  field="checkDateEnd"  formatter="hh:mm:ss"  filterType="datebox" extendParams="editor:{type:'datetimebox',options:{onShowPanel:initDateboxformat}}"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外键"  field="fckId" extendParams="editor:'text'"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="famsSafeSpotCheckDetailController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
  </t:datagrid>
  </div>
   <input type="hidden" id = "jformOrderTicket2ListMainId"/>
 </div>
  <script type="text/javascript" src="plug-in/mutitables/mutitables.urd.js"></script>
  <script type="text/javascript" src="plug-in/mutitables/mutitables.curdInIframe.js"></script>	
 <script type="text/javascript">
 $(document).ready(function(){
	  curd = $.curdInIframe({
		  name:"famsSafeSpotCheckDetail",
		  describe:"抽查详情"
	  });
	  gridname = curd.getGridname();
 });
 
 /**
  * 双击事件开始编辑
  */
 function datagridDbclick(index,field,value){
 	$("#jformOrderTicket2List").datagrid('beginEdit', index);
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'famsSafeSpotCheckDetailControllerController.do?upload', "jformOrderTicket2List");
}

//导出
function ExportXls() {
	JeecgExcelExport("famsSafeSpotCheckDetailControllerController.do?exportXls","jformOrderTicket2List");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("famsSafeSpotCheckDetailControllerController.do?exportXlsByT","jformOrderTicket2List");
}

 </script>