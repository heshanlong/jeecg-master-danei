<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>整改单</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsAircontrolReformList" component="bootstrap-table"  checkbox="true" sortName="createDate"  sortOrder="desc"
            pagination="true" fitColumns="true" title="整改单" actionUrl="famsAircontrolReformController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="number"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="责任单位"  field="dutyCompany"  query="true"  queryMode="single"  popup="true" dictionary="depart_msg,dutyCompany,departname"   showLen="7" width="150"></t:dgCol>--%>
   <t:dgCol title="责任单位"  field="dutyCompany"   query="true"  queryMode="single"   width="180" showLen="15"></t:dgCol>
   <%--<t:dgCol title="common.department" sortable="false" field="userOrgList.tsDepart.departname" query="false" width="100"></t:dgCol>--%>

   <t:dgCol title="当事人姓名"  field="dutyPersonName"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="当事人联系方式"  field="dutyPersonMobile"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="违章行为"  field="violationName"  query="true"  queryMode="single"  width="280" showLen="15" frozenColumn="true"></t:dgCol>
   <%--<t:dgCol title="处理结果"  field="panishResult"  queryMode="single"  dictionary="reform_p_r"  width="50"></t:dgCol>--%>
   <t:dgCol title="派发人"  field="decideName"  query="true"  queryMode="single"  width="10"></t:dgCol>
   <t:dgCol title="发现时间"  field="decideDate"  formatter="yyyy-MM-dd hh:mm"  query="true"  queryMode="group"  width="150"></t:dgCol>
    <t:dgCol title="创建时间"  field="createDate"  formatter="yyyy-MM-dd hh:mm"  query="true"  queryMode="group"  width="150"></t:dgCol>

    <t:dgCol title="整改措施描述"  field="reformWay"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="整改完成时间"  field="reformDate"  formatter="yyyy-MM-dd hh:mm:ss"  hidden="true"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="验收时间"  field="checkDate"  formatter="yyyy-MM-dd hh:mm:ss"  hidden="true"  queryMode="single"  width="120" ></t:dgCol>
   <t:dgCol title="验收不通过理由"  field="checkfailDec"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"  query="true"  queryMode="single"  dictionary="reform_sta"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"  extend="{style:'height:120px'}"></t:dgCol>
   <t:dgFunOpt funname="check(id)" exp="status#eq#3" operationCode="#check" title="验收"  urlfont="fa-user"></t:dgFunOpt>
   <t:dgFunOpt funname="receive(id)" exp="status#eq#1" operationCode="#receive" title="接收"  urlfont="fa-user"></t:dgFunOpt>
   <t:dgFunOpt funname="reform(id)" exp="status#eq#2,5" operationCode="#reform" title="整改完成"   urlfont="fa-user"></t:dgFunOpt>
   <t:dgFunOpt funname="reback(id)" exp="status#eq#1,2,5" operationCode="#reback" title="撤销"   urlfont="fa-user"></t:dgFunOpt>
   <t:dgToolBar title="录入"  icon="icon-add" url="famsAircontrolReformController.do?goAdd" funname="add"  width="100%" height="100%" operationCode="#add"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsAircontrolReformController.do?goCheck" funname="detail"  width="100%" height="100%"></t:dgToolBar>

   <%--<t:dgToolBar title="编辑" icon="icon-edit" url="famsAircontrolReformController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>--%>

   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="famsAircontrolReformController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls" operationCode="#ExportXls"></t:dgToolBar>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
<%--<script>--%>
    <%--$(function() {--%>
        <%--var datagrid = $("#famsAircontrolReformList");--%>
        <%--datagrid.find("div[name='searchColums']").find("form#famsAircontrolReformListForm").append($("#realNameSearchColums div[name='searchColumsRealName']").html());--%>
        <%--$("#realNameSearchColums").html('');--%>
        <%--datagrid.find("div[name='searchColums']").find("form#famsAircontrolReformListForm").append($("#tempSearchColums div[name='searchColums']").html());--%>
        <%--$("#tempSearchColums").html('');--%>
    <%--});--%>
<%--</script>--%>

<%--<div id="tempSearchColums" style="display: none;">--%>
   <%--<div name="searchColums">--%>
      <%--<t:departSelect hasLabel="true" selectedNamesInputId="orgNames"></t:departSelect>--%>
   <%--</div>--%>
<script src="webpage/com/gbiac/fams/business/anounce/js/famsAnnounceCommon.js"></script>

  <script type="text/javascript">
	 $(document).ready(function(){

         // 初始化检索,时间设置默认值
         // $('#btn_collapse_search').attr('state',0); //初始化添加未展开状态
         // clickEnvent('#btn_collapse_search','#decideDate_begin','#decideDate_end');
	 });
	 
     function  check(id) {
         createwindow("验收","famsAircontrolReformController.do?goCheck&id="+id,'100%','100%');
     }

     function  receive(id) {
         createwindow("接收","famsAircontrolReformController.do?goReceive&id="+id,'100%','100%');
     }

     function  reform(id) {
         createwindow("整改","famsAircontrolReformController.do?goReform&id="+id,'100%','100%');
     }

     function  reback(id) {
         createwindow("撤销","famsAircontrolReformController.do?goReback&id="+id,'100%','100%');
     }

	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsAircontrolReformController.do?upload', "famsAircontrolReformList");
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
				JeecgExcelExport("famsAircontrolReformController.do?exportXls","famsAircontrolReformList");
			}else{
				layer.alert("仅允许导出时间周期为一年之内的台账，请选择创建时间范围的起始时间");
			}
		}else{
			if(date>new Date($("#createDate_begin").val())){
				layer.alert("仅允许导出时间周期为一年之内的台账，请重新选择时间范围");
			}else{			
				JeecgExcelExport("famsAircontrolReformController.do?exportXls","famsAircontrolReformList");
			}	
		}
	}		
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsAircontrolReformController.do?exportXlsByT","famsAircontrolReformList");
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