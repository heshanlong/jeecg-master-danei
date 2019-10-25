<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>分组维护主表</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsAnounceGroupList" component="bootstrap-table"  checkbox="true" sortName="createDate"  sortOrder="desc"  pagination="true" fitColumns="true" title="分组维护主表" actionUrl="famsAnounceGroupController.do?datagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="分组名称"  field="name"  query="true"  queryMode="single"  width="120" showLen="12"></t:dgCol>
   <t:dgCol title="分组人数"  field="count"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="分组成员"  field="user"  query="true"  queryMode="single"  width="200" showLen="20"></t:dgCol>
   <t:dgCol title="创建人"  field="createName"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="state"  query="true"  queryMode="single"  dictionary="groupState" width="80"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <%--<t:dgDelOpt title="删除" url="famsAnounceGroupController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
   <%--<t:dgFunOpt exp="state#eq#1" funname="blockedGroup(id)" title="禁用" urlclass="ace_button"  urlfont="fa-edit" urlStyle="background-color:#597EF7;" />--%>
   <t:dgFunOpt exp="state#eq#2" funname="stardGroup(id)" title="启用" urlclass="ace_button"  urlfont="" urlStyle="background-color:#597EF7;" />
   <t:dgConfOpt exp="state#eq#1"  message="确定要禁用该分组?"  title="禁用"  url="famsAnounceGroupController.do?doDel&id={id}"  urlclass="ace_button state"  urlfont=""></t:dgConfOpt>

   <t:dgToolBar title="录入" icon="icon-add" url="famsAnounceGroupController.do?goAdd" funname="add"  width="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="famsAnounceGroupController.do?goUpdate" funname="update"  width="100%" height="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="famsAnounceGroupController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="famsAnounceGroupController.do?goUpdate" funname="detail"  width="100%"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
<%--<script src="webpage/com/gbiac/fams/business/anounce/js/famsAnnounceCommon.js"></script>--%>
  <script type="text/javascript">
	 $(document).ready(function(){
         //已启用后不能编辑
         /*var $tableList = $('#famsAnounceGroupList tbody');
         $tableList.on('change','input[type="checkbox"]',function () {
             var $checkedState = $tableList.find('tr.selected a');
             if($checkedState.hasClass('state')){
                 $('#btn_edit').attr('disabled',true)
             }
         })*/

         // 初始化检索,时间设置默认值
         // $('#btn_collapse_search').attr('state',0); //初始化添加未展开状态
         // clickEnvent('#btn_collapse_search','#createDate');
     });

     //启用
    function stardGroup(id) {
         $.ajax({
             type: 'post',
             url: "famsAnounceGroupController.do?doDel",
             dataType: 'json',
             data: {
                 "id": id
             },
             success: function (data) {
                 if (data.success) {
                     layer.msg('已启用');
                     //bootstrap表格刷新
                     $('#famsAnounceGroupList').bootstrapTable('refresh');
                 } else {
                     layer.msg('启用失败')
                 }
             },
             error: function (e) {
                 layer.msg('启用失败,请检查网络情况')
             }
         });
     }

	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsAnounceGroupController.do?upload', "famsAnounceGroupList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("famsAnounceGroupController.do?exportXls","famsAnounceGroupList");
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsAnounceGroupController.do?exportXlsByT","famsAnounceGroupList");
	}
	
	 </script>
</body>
</html>