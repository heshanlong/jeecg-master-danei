<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>鸟击</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsUnsafeeventBirdstrikeList" component="bootstrap-table"  checkbox="true" sortName="createDate"  sortOrder="desc"  pagination="true" fitColumns="true" title="fams_unsafeevent_birdstrike" actionUrl="famsUnsafeeventBirdstrikeController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="no"   queryMode="single" query="true"   width="120"></t:dgCol>
   <t:dgCol title="航班号"  field="flightno"   queryMode="single" query="true"   width="120"></t:dgCol>
   <t:dgCol title="机型"  field="models"   queryMode="group" width="50" showLen="12" ></t:dgCol>
   <t:dgCol title="机号"  field="immediately"  queryMode="single"  query="true"  width="50" showLen="12" ></t:dgCol>
   <t:dgCol title="鸟击类型"  field="eventtype"  queryMode="single"  query="true"  width="120" showLen="12" ></t:dgCol>
   <t:dgCol title="痕迹部位"  field="damagedlocation"   queryMode="single" query="true"   width="120" showLen="12" ></t:dgCol>
   <t:dgCol title="信息来源"  field="sourceformation"   queryMode="single" query="true"   width="120" showLen="12" ></t:dgCol>
   <t:dgCol title="日期"  field="thedate"  formatter="yyyy-MM-dd" queryMode="group" query="true" width="120"></t:dgCol>
   <t:dgCol title="接报时间"  field="settime"    queryMode="group"   width="120"></t:dgCol>
   <t:dgCol title="状态"  field="cloes"   dictionary="cloes"  queryMode="single" query="true"   width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  formatter="yyyy-MM-dd hh:mm"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="famsUnsafeeventBirdstrikeController.do?doDel&id={id}" urlclass="ace_button"  operationCode="#delete" />
   <t:dgToolBar title="录入" icon="icon-add" url="famsUnsafeeventBirdstrikeController.do?goAdd" funname="add"  operationCode="#add"  width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsUnsafeeventBirdstrikeController.do?goUpdate" funname="update"  operationCode="#edit"  width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="famsUnsafeeventBirdstrikeController.do?doBatchDel" funname="deleteALLSelect" operationCode="#batch_delete"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsUnsafeeventBirdstrikeController.do?goUpdate" funname="detail"  width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="修改日志" icon="icon-search" funname="opentag"  width="100%" height="100%"></t:dgToolBar>
   <%-- <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls" operationCode="#import"></t:dgToolBar>
   
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgConfOpt title="关闭" exp="cloes#ne#2"  urlclass="ace_button" urlStyle="background-color:red;" url="famsUnsafeeventBirdstrikeController.do?doCloesl&id={id}" message="是否关闭"  operationCode="#cloes" />
  </t:datagrid>
    <input id="jcxj" type="hidden"  readonly="readonly"  value = "${jcxj}"  ignore="ignore"  />
  <script src="webpage/com/gbiac/fams/business/anounce/js/famsAnnounceCommon.js"></script>
  <script type="text/javascript">
	 $(document).ready(function(){
		$('button:contains("修改日志")').prop('disabled', $('#famsUnsafeeventBirdstrikeList').bootstrapTable('getSelections').length !=1);
	    var jcxj = $('#jcxj').val()
		//判断是否选中表格中的数据，选中后可编辑或删除
		$('#famsUnsafeeventBirdstrikeList').on('check.bs.table uncheck.bs.table load-success.bs.table check-all.bs.table uncheck-all.bs.table', function () {
		$('#btn_delete').prop('disabled', ! $('#famsUnsafeeventBirdstrikeList').bootstrapTable('getSelections').length);
		$('button:contains("修改日志")').prop('disabled', $('#famsUnsafeeventBirdstrikeList').bootstrapTable('getSelections').length !=1);
		if($('#famsUnsafeeventBirdstrikeList').bootstrapTable('getSelections').length!=1){
		//alert(1);
			$('#btn_edit').prop('disabled', true)
		}else{
		var rows = $('#famsUnsafeeventBirdstrikeList').bootstrapTable('getSelections');
			for (var i = 0; i < rows.length; i++) {
				if(jcxj == 'true'){
				//alert(2);
					$('#btn_edit').prop('disabled', false);
				}else{
				//alert(3);
					$('#btn_edit').prop('disabled', rows[i].cloes =='2');
				}
				//$('#btn_edit').prop('disabled', rows[i].cloes =='2' && jcxj == true);// && $("#cloes").val() == '1'
			}
		}
		   
		});
		
		
	 });
     


     function opentag() {
         var rowsData = $('#famsUnsafeeventBirdstrikeList tr[class="selected"]').attr('data-uniqueid');
         $.dialog({
             content: 'url:famsUpdatetimeController.do?list&id='+rowsData,
             zIndex: getzIndex(),
             lock : true,
             width:window.top.document.body.offsetWidth,
             height: window.top.document.body.offsetHeight-100,
             title:'修改日志',
             opacity : 0.3,
             cache:false,
             okVal: '确定',
             cancelVal: '关闭',
             cancel: true /*为true等价于function(){}*/
         });
     }
	   
	 
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsUnsafeeventBirdstrikeController.do?upload', "famsUnsafeeventBirdstrikeList");
	}
	
	//导出
	function ExportXls() {
		//实现导出时先判断创建时间
		var date = new Date();
		date.setFullYear(date.getFullYear()-1);
		if($("#thedate_begin").val()==""){
			if($("#thedate_end").val()==""){
				$("#thedate_begin").val(date.Format("yyyy-MM-dd"));
				$("#thedate_end").val(new Date().Format("yyyy-MM-dd"));
				JeecgExcelExport("famsUnsafeeventBirdstrikeController.do?exportXls","famsUnsafeeventBirdstrikeList");
			}else{
				layer.alert("仅允许导出时间周期为一年之内的台账，请选择创建时间范围的起始时间");
			}
		}else{
			if(date>new Date($("#thedate_begin").val())){
				layer.alert("仅允许导出时间周期为一年之内的台账，请重新选择时间范围");
			}else{			
				JeecgExcelExport("famsUnsafeeventBirdstrikeController.do?exportXls","famsUnsafeeventBirdstrikeList");
			}	
		}		
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsUnsafeeventBirdstrikeController.do?exportXlsByT","famsUnsafeeventBirdstrikeList");
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