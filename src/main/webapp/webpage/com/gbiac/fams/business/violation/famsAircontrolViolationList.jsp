<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>违章告知单</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer,common"></t:base>
</head>
<body>
<t:datagrid name="famsAircontrolViolationList" component="bootstrap-table"  checkbox="true" sortName="createDate"  sortOrder="desc"
            pagination="true" fitColumns="true"  nowrap ="true" title="违章告知单" actionUrl="famsAircontrolViolationController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="number"  query="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="单位id"  field="dutyId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="单位名称"   field="dutyCompany"  query="true" popup="true" dictionary="depart_msg,dutyCompany,departname"    width="120"></t:dgCol>--%>
   <t:dgCol title="责任单位"  field="dutyCompany"   query="true"  queryMode="single"   width="180" showLen="15"></t:dgCol>
   <t:dgCol title="当事人姓名"  field="dutyPersonName"  queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="当事人联系方式"  field="dutyPersonMobile"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="违章地点"  field="violationAddr"  queryMode="single"  width="120" showLen="15"></t:dgCol>
   <t:dgCol title="控制区通行证号"  field="apronCard"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="车牌号"  field="carNumber"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="违章行为"  field="violationName"  query="true"  queryMode="single" showLen="15" width="120"></t:dgCol>
   <t:dgCol title="处罚依据"  field="panishRule"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="机坪处理"  field="apronPanishResult"  hidden="true"  queryMode="single"  dictionary="voi_d_str"  width="120"></t:dgCol>
   <t:dgCol title="发现时间"  field="decideDate"  formatter="yyyy-MM-dd hh:mm"  query="true"  queryMode="group"  width="200"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  formatter="yyyy-MM-dd hh:mm"  query="true"  queryMode="group"  width="200"></t:dgCol>
   <t:dgCol title="派发人"  field="decideName"  query="true"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="准入处理"  field="accessPanishResult"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="处理人"  field="dealName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"  query="true"  queryMode="single"  dictionary="vio_status"    width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="150"></t:dgCol>
   <t:dgFunOpt funname="deal(id)" exp="status#eq#1" title="处理"   urlfont="fa-user" operationCode="#deal"></t:dgFunOpt>
   <t:dgFunOpt funname="reback(id)" exp="status#eq#1" title="撤销"   urlfont="fa-user" operationCode="#reback"></t:dgFunOpt>
   <t:dgFunOpt funname="receive(id)" exp="status#eq#2" title="接收"   urlfont="fa-user" operationCode="#receive"></t:dgFunOpt>
   <t:dgFunOpt funname="recall(id)" exp="status#eq#2" title="撤回"  urlfont="fa-user" operationCode="#recall"></t:dgFunOpt>


   <t:dgToolBar title="录入" icon="icon-add" url="famsAircontrolViolationController.do?goAdd" funname="add"  width="100%" height="100%" operationCode="#add"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="famsAircontrolViolationController.do?goReceive" funname="detail"  width="100%" height="100%"></t:dgToolBar>

   <%--<t:dgToolBar title="编辑" icon="icon-edit" url="famsAircontrolViolationController.do?goUpdate" funname="update"  width="800" height="500"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="famsAircontrolViolationController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls" operationCode="#ExportXls"></t:dgToolBar>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
<script src="webpage/com/gbiac/fams/business/anounce/js/famsAnnounceCommon.js"></script>

<script type="text/javascript">
    $(document).ready(function(){

        // 初始化检索,时间设置默认值
        // $('#btn_collapse_search').attr('state',0); //初始化添加未展开状态
        // clickEnvent('#btn_collapse_search','#decideDate_begin','#decideDate_end');
    });


    function  deal(id) {
         createwindow("处理","famsAircontrolViolationController.do?goDeal&id="+id,'100%','100%');
     }

     function  reback(id) {
         createwindow("撤销","famsAircontrolViolationController.do?goReback&id="+id,'100%','100%');
     }

    function  recall(id) {
        createwindow("撤回","famsAircontrolViolationController.do?goReback&id="+id,'100%','100%');
    }


    function  receive(id) {
         createwindow("接收","famsAircontrolViolationController.do?goReceive&id="+id,'100%','100%');
     }

     //导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsAircontrolViolationController.do?upload', "famsAircontrolViolationList");
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
				JeecgExcelExport("famsAircontrolViolationController.do?exportXls","famsAircontrolViolationList");
			}else{
				layer.alert("仅允许导出时间周期为一年之内的台账，请选择创建时间范围的起始时间");
			}
		}else{
			if(date>new Date($("#createDate_begin").val())){
				layer.alert("仅允许导出时间周期为一年之内的台账，请重新选择时间范围");
			}else{			
				JeecgExcelExport("famsAircontrolViolationController.do?exportXls","famsAircontrolViolationList");
			}	
		}		
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsAircontrolViolationController.do?exportXlsByT","famsAircontrolViolationList");
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