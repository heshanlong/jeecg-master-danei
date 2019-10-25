<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>航班保障作业检查单主记录</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsAircontrolSafecheckList" component="bootstrap-table"  checkbox="true" sortName="createDate"   sortOrder="desc"  pagination="true" fitColumns="true" title="航班保障作业检查单主记录" actionUrl="famsAircontrolSafecheckController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="number"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="航班计划id"  field="airPlanId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="航班号"  field="airNumber"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="航线"  field="airLine"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="机尾号"  field="airTail"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="机型"  field="airModel"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="停机位"  field="airSlots"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查人"  field="checkerName"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="检查时间"  field="checkDate"  formatter="yyyy-MM-dd hh:mm"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  formatter="yyyy-MM-dd hh:mm"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="预计时间"  field="preDate"  formatter="yyyy-MM-dd hh:mm"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="检查总结果"  field="result"  hidden="true"  query="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="检查结果"  field="result" hidden="true"  query="true"  queryMode="single"  width="120"  dictionary="safe_c_s"  ></t:dgCol>
   <t:dgCol title="检查结果描述"  field="resultDes"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>

   <t:dgFunOpt funname="edit(id)" title="编辑"   urlfont="fa-user"></t:dgFunOpt>

   <t:dgDelOpt title="删除" url="famsAircontrolSafecheckController.do?doDel&id={id}"  urlStyle="background-color:red;"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="famsAircontrolSafecheckController.do?goAdd" funname="add"  width="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="编辑" icon="icon-edit" url="famsAircontrolSafecheckController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>--%>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="famsAircontrolSafecheckController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsAircontrolSafecheckController.do?goUpdate" funname="detail"  width="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls" operationCode="#ExportXls"></t:dgToolBar>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
<script src="webpage/com/gbiac/fams/business/anounce/js/famsAnnounceCommon.js"></script>

  <script type="text/javascript">

	 $(document).ready(function(){


         // 初始化检索,时间设置默认值
         // $('#btn_collapse_search').attr('state',0); //初始化添加未展开状态
         // clickEnvent('#btn_collapse_search','#checkDate_begin','#checkDate_end');
	 });

     function  edit(id) {
         createwindow("编辑","famsAircontrolSafecheckController.do?goUpdate&id="+id,'100%','100%');
     }
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsAircontrolSafecheckController.do?upload', "famsAircontrolSafecheckList");
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
				JeecgExcelExport("famsAircontrolSafecheckController.do?exportXls","famsAircontrolSafecheckList");
			}else{
				layer.alert("仅允许导出时间周期为一年之内的台账，请选择创建时间范围的起始时间");
			}
		}else{
			if(date>new Date($("#createDate_begin").val())){
				layer.alert("仅允许导出时间周期为一年之内的台账，请重新选择时间范围");
			}else{			
				JeecgExcelExport("famsAircontrolSafecheckController.do?exportXls","famsAircontrolSafecheckList");
			}	
		}		
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsAircontrolSafecheckController.do?exportXlsByT","famsAircontrolSafecheckList");
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