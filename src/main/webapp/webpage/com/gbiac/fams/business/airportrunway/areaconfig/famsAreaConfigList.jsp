<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>区域配置</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsAreaConfigList" component="bootstrap-table"  checkbox="true" sortName="createDate"  sortOrder="desc"  pagination="true" fitColumns="true" title="区域配置" actionUrl="famsAreaConfigController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group" query="true" width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="serialNumber" hidden="true" queryMode="single" width="120"></t:dgCol>
   <t:dgCol title="区域id"  field="areaId" queryMode="single" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="区域"  field="area" query="true" queryMode="single" width="120"></t:dgCol>
   <t:dgCol title="机位"  field="craftsite"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgToolBar title="录入" icon="icon-add" url="famsAreaConfigController.do?goAdd" funname="add"  width="768"  operationCode="#add"></t:dgToolBar>
   <t:dgDelOpt title="删除" url="famsAreaConfigController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o" operationCode="#del"/>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsAreaConfigController.do?goUpdate" funname="update"  width="768"  operationCode="#edit"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsAreaConfigController.do?goUpdate" funname="detail"  width="768"  operationCode="#check"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"  operationCode="#export" ></t:dgToolBar>
   

  </t:datagrid>
  <script type="text/javascript">
	 $(document).ready(function(){
	 });
	 
	 //打开tab窗口
 	function addbytab(){
		//opensearchdwin("录入", "famsAttentionCraftsiteUserController.do?goAdd", 420,280);
		//createwindow('录入', 'famsAttentionCraftsiteUserController.do?goAdd',420,280);
		//TAB窗口
	 	//addOneTab("录入", "famsAttentionCraftsiteUserController.do?goAdd");
	}
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsAreaConfigController.do?upload', "famsAreaConfigList");
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
				JeecgExcelExport("famsAreaConfigController.do?exportXls","famsAreaConfigList");
			}else{
				layer.alert("仅允许导出时间周期为一年之内的台账，请选择创建时间范围的起始时间");
			}
		}else{
			if(date>new Date($("#createDate_begin").val())){
				layer.alert("仅允许导出时间周期为一年之内的台账，请重新选择时间范围");
			}else{			
				JeecgExcelExport("famsAreaConfigController.do?exportXls","famsAreaConfigList");
			}	
		}				
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsAreaConfigController.do?exportXlsByT","famsAreaConfigList");
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