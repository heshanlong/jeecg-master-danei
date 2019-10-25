<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>航班保障作业检查表单配置信息</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsAircontrolSafecheckconfigList" component="bootstrap-table"  checkbox="true" sortName="createDate"  sortOrder="desc"  pagination="true" fitColumns="true" title="航班保障作业检查表单配置信息" actionUrl="famsAircontrolSafecheckconfigController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  query="true" formatter="yyyy-MM-dd hh:mm"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查项目通用名称"  field="checkProject"  query="true" dictionary="safe_cf_cn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查项目内容"  field="projectDetail"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="类型"  field="type"  query="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="状态"  field="status"  query="true"  queryMode="single"  width="120" dictionary="safe_cf_s" ></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
   <%--<t:dgDelOpt title="删除" url="famsAircontrolSafecheckconfigController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
   <t:dgToolBar title="录入" icon="icon-add" url="famsAircontrolSafecheckconfigController.do?goAdd" funname="add"  width="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsAircontrolSafecheckconfigController.do?goUpdate" funname="update"  width="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="famsAircontrolSafecheckconfigController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="famsAircontrolSafecheckconfigController.do?goUpdate" funname="detail"  width="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  <script type="text/javascript">
	 $(document).ready(function(){
	 });
	 
	   
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsAircontrolSafecheckconfigController.do?upload', "famsAircontrolSafecheckconfigList");
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
				JeecgExcelExport("famsAircontrolSafecheckconfigController.do?exportXls","famsAircontrolSafecheckconfigList");
			}else{
				layer.alert("仅允许导出时间周期为一年之内的台账，请选择创建时间范围的起始时间");
			}
		}else{
			if(date>new Date($("#createDate_begin").val())){
				layer.alert("仅允许导出时间周期为一年之内的台账，请重新选择时间范围");
			}else{			
				JeecgExcelExport("famsAircontrolSafecheckconfigController.do?exportXls","famsAircontrolSafecheckconfigList");
			}	
		}		
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsAircontrolSafecheckconfigController.do?exportXlsByT","famsAircontrolSafecheckconfigList");
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