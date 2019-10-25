<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>通知通告表</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsAnnounceNotifyList" component="bootstrap-table"
            checkbox="true" sortName="sendTime"  sortOrder="desc"
            pagination="true" fitColumns="true" title="通知通告表"
            actionUrl="famsAnnounceNotifyController.do?datagrid"
            idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="number"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"  query="true"  queryMode="single"  width="200" showLen="16"></t:dgCol>
   <t:dgCol title="内容"  field="content"  query="true"  queryMode="single"  width="300" showLen="26"></t:dgCol>
   <t:dgCol title="类型"  field="type"  query="true"  queryMode="single"  width="120" dictionary="anouType"></t:dgCol>
   <t:dgCol title="发送模式"  field="sendPattern"  query="true"  queryMode="single"  width="160" dictionary="sePattern"></t:dgCol>
   <t:dgCol title="发送人"  field="sender"  query="true"  queryMode="single"  width="120" showLen="8"></t:dgCol>
   <t:dgCol title="发送时间"  field="sendTime"  formatter="yyyy-MM-dd hh:mm"  query="true"  queryMode="group"  width="200"></t:dgCol>
   <t:dgCol title="分组维护主表id"  field="groupId"  hidden="true"  queryMode="single"  width="36"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <!-- 添加状态 -->
   <t:dgCol title="状态" field="state" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <%--exp="state#ne#2" state不等于2时按钮隐藏--%>
   <t:dgFunOpt exp="state#ne#2" funname="reback(id)" title="撤回" urlclass="ace_button state"  urlfont=""/>
   <t:dgToolBar title="录入" icon="icon-add" url="famsAnnounceNotifyController.do?goAdd" funname="add"  width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsAnnounceNotifyController.do?goUpdate" funname="update"  width="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="famsAnnounceNotifyController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="famsAnnounceNotifyController.do?goDetail" funname="detail"  width="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
<%--<script src="webpage/com/gbiac/fams/business/anounce/js/famsAnnounceCommon.js"></script>--%>
  <script type="text/javascript">
	 $(document).ready(function(){
	     //撤回后不能编辑
	     var $tableList = $('#famsAnnounceNotifyList tbody');
         $tableList.on('change','input[type="checkbox"]',function () {
             var $checkedState = $tableList.find('tr.selected a');
             if(!$checkedState.hasClass('state')){
                 $('#btn_edit').attr('disabled',true)
             }
         })

         // 初始化检索,时间设置默认值
         // $('#btn_collapse_search').attr('state',0); //初始化添加未展开状态
         // clickEnvent('#btn_collapse_search','#sendTime_begin','#sendTime_end');
     });

     //撤回
     function reback(id) {
         createwindow("撤回","famsAnnounceNotifyController.do?goDetail&withdraw=1&id="+id,'100%','100%');
     }

	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsAnnounceNotifyController.do?upload', "famsAnnounceNotifyList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("famsAnnounceNotifyController.do?exportXls","famsAnnounceNotifyList");
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsAnnounceNotifyController.do?exportXlsByT","famsAnnounceNotifyList");
	}

	 </script>
</body>
</html>