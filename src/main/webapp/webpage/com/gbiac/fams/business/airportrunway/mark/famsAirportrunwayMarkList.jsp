<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>fams_airportrunway_mark</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsAirportrunwayMarkList" component="bootstrap-table"  checkbox="true" sortName="endTime"  sortOrder="desc"  pagination="true" fitColumns="true" title="fams_airportrunway_mark" actionUrl="famsAirportrunwayMarkController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="no"   queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="位置"  field="location"  queryMode="single" query="true"  width="120" showLen="12" ></t:dgCol>
   <t:dgCol title="跑道标志"  field="runwayLogo"  queryMode="single" query="true"  dictionary="runwayLogo"  width="120" showLen="12" ></t:dgCol>
   <t:dgCol title="滑行道标志"  field="newSign"  queryMode="group"  width="120" showLen="12" ></t:dgCol>
   <t:dgCol title="滑行道标志"  field="taxiwayMark"  queryMode="single" query="true"  hidden="true"  dictionary="t_s_category where PARENT_CODE ='A10',code,name"   width="120" showLen="12" ></t:dgCol>
   
   <t:dgCol title="面积"  field="area" queryMode="single" query="true"  width="120" showLen="12" ></t:dgCol>
   <t:dgCol title="作业人"  field="people" queryMode="single" query="true"  width="120" showLen="12" ></t:dgCol>
   <t:dgCol title="作业开始时间"  field="startTime"  formatter="yyyy-MM-dd hh:mm" queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="作业结束时间"  field="endTime"  formatter="yyyy-MM-dd hh:mm" queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  formatter="yyyy-MM-dd hh:mm"  queryMode="group" query="true" width="120"></t:dgCol>
   <%-- <t:dgCol title="备注"  field="note"  queryMode="group"  width="120"></t:dgCol> --%>

   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol> --%>
   <%-- <t:dgDelOpt title="删除" url="famsAirportrunwayMarkController.do?doDel&id={id}" urlclass="ace_button"   operationCode="#delete"/> --%>
   <t:dgToolBar title="录入" icon="icon-add" url="famsAirportrunwayMarkController.do?goAdd" funname="add" width="100%" height="100%" operationCode="#add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsAirportrunwayMarkController.do?goUpdate" funname="update" width="100%" height="100%" operationCode="#edit"></t:dgToolBar>
   <%-- <t:dgToolBar title="批量删除"  icon="icon-remove" url="famsAirportrunwayMarkController.do?doBatchDel" funname="deleteALLSelect" operationCode="#batch_delete"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="famsAirportrunwayMarkController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls" operationCode="#export" ></t:dgToolBar>
   <%-- <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls" operationCode="#import"></t:dgToolBar>
   
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
    <script src="webpage/com/gbiac/fams/business/anounce/js/famsAnnounceCommon.js"></script>
  <script type="text/javascript">
	 $(document).ready(function(){

	 });
	 
	   
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsAirportrunwayMarkController.do?upload', "famsAirportrunwayMarkList");
	}
	
	//导出
	function ExportXls() {
		//实现导出时先判断创建时间
		var date = new Date();
		date.setFullYear(date.getFullYear()-1);
		if($("#createDate_begin").val()==""){
			if($("#createDate_end").val()==""){
				$("#createDate_begin").val(date.Format("yyyy-MM-dd hh:mm:ss"));
				$("#createDate_end").val(new Date().Format("yyyy-MM-dd hh:mm:ss"));
				JeecgExcelExport("famsAirportrunwayMarkController.do?exportXls","famsAirportrunwayMarkList");
			}else{
				layer.alert("仅允许导出时间周期为一年之内的台账，请选择创建时间范围的起始时间");
			}
		}else{
			if(date>new Date($("#createDate_begin").val())){
				layer.alert("仅允许导出时间周期为一年之内的台账，请重新选择时间范围");
			}else{			
				JeecgExcelExport("famsAirportrunwayMarkController.do?exportXls","famsAirportrunwayMarkList");
			}	
		}		
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsAirportrunwayMarkController.do?exportXlsByT","famsAirportrunwayMarkList");
	}

	Date.prototype.Format = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, // 月份
			"d+" : this.getDate(), // 日
			"h+" : this.getHours(), // 小时
			"m+" : this.getMinutes(), // 分
			"s+" : this.getSeconds(), // 秒
			"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
			"S" : this.getMilliseconds()		// 毫秒
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	 </script>
</body>
</html>