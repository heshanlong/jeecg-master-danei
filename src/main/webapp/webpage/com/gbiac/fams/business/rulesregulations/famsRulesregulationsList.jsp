<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>


<script src="webpage/com/gbiac/fams/business/rulesregulations/jquery.easyui.min.1.3.2.js" type="text/javascript"></script>


<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="famsRulesregulationsList" singleSelect="true" superQuery="false"  checkbox="true" pagination="false" treegrid="true" treeField="rulesName" fitColumns="true" title="规章制度" sortName="createDate" actionUrl="famsRulesregulationsController.do?datagrid" idField="id" fit="true" queryMode="group" btnCls="bootstrap btn btn-normal btn-xs">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="父目录"  field="pid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd HH:mm:ss"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="格式"  field="rulesType" image="false" hidden="true" queryMode="single"  width="20"></t:dgCol>
   <t:dgCol title="格式"  field="rulesFileIcon" hidden="true" image="true" queryMode="single"  width="20"></t:dgCol>
   <t:dgCol title="文件名称"  field="rulesName" query="true" queryMode="single"  width="120" showLen="30"></t:dgCol>
   <t:dgCol title="部号"  field="keyword" query="true" hidden="false"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="发文日期"  field="dispatchDate" formatter="yyyy-MM-dd" hidden="false" query="true"  queryMode="group" width="50"></t:dgCol>
   <t:dgCol title="创建人"  field="createName"  hidden="true"  queryMode="single"  width="30"></t:dgCol>
   <t:dgCol title="查看权限" replace="所有人_0,飞管内部_1" field="visible"  hidden="true"  queryMode="single"  width="30"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  width="50"></t:dgCol>
   <t:dgCol title="后缀"  field="rulesSuffix" queryMode="single"  hidden="true"  width="20"></t:dgCol>
   <t:dgCol title="文件路径ID"  field="rulesPathUrl" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="文件路径Name"  field="rulesPathUrlName" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark" hidden="true"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="后缀"  field="iconCls" hidden="true"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="办文单位"  field="articleUnit" hidden="false" query="true" queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="体裁分类"  field="genreClassification" hidden="false"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="代替规章"  field="replaceRegulations" hidden="false"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>

   <t:dgFunOpt funname="delFolder(id,rulesType,rulesName)" title="删除" urlStyle="background-color:red;" urlclass="ace_button"  urlfont="" operationCode="#delFolder"></t:dgFunOpt>
   <t:dgFunOpt funname="addFolder(id)" title="添加子目录" exp="rulesType#eq#0" urlStyle="background-color:#007465;" urlclass="ace_button"  urlfont="" operationCode="#addFolder"></t:dgFunOpt>
   <t:dgFunOpt funname="addFile(id)" title="上传文件" exp="rulesType#eq#0" urlclass="ace_button"  urlfont="" operationCode="#addFile"></t:dgFunOpt>
   
   <%-- <t:dgOpenOpt title="预览" width="700" height="600" exp="rulesType#eq#1" url="famsRulesregulationsController.do?openViewFile&fileid={rulesPathUrl}" urlclass="ace_button"  urlfont="" ></t:dgOpenOpt>--%>
   <t:dgFunOpt title="下载" operationCode="#download" funname="downloadFile(rulesPathUrl)" exp="rulesType#eq#1" urlStyle="background-color:#2590dc;" urlclass="ace_button"  urlfont=""></t:dgFunOpt>
   
   <t:dgToolBar title="添加根目录" width="665" height="200" icon="icon-add fa fa-plus" url="famsRulesregulationsController.do?goAdd" funname="add" operationCode="#add"></t:dgToolBar>
   <t:dgToolBar title="编辑"  width="665" height="600"  icon="icon-edit fa fa-edit" url="famsRulesregulationsController.do?goUpdate" funname="updatetreeRule" operationCode="#edit"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
		/* $("#famsRulesregulationsList").treegrid({
 				 onExpand : function(row){
 					var children = $("#famsRulesregulationsList").treegrid('getChildren',row.id);
 					 if(children.length<=0){
 					 	row.leaf=true;
 					 	$("#famsRulesregulationsList").treegrid('refresh', row.id);
 					 }
 				}
 		});
		
		$(".tree-icon").removeAttr();*/
		
 });
 function famsRulesregulationsListsearch() {
     try {
         if (!$("#famsRulesregulationsListForm").Validform({tiptype: 3}).check()) {
             return false;
         }
     } catch (e) {
     }
     if (true) {
         var $tree=$('#famsRulesregulationsList');
         var queryParams = $tree.datagrid('options').queryParams;
         $('#famsRulesregulationsListtb').find('*').each(function () {
             queryParams[$(this).attr('name')] = $(this).val();
         });
         $('#famsRulesregulationsList').treegrid({
             url: 'famsRulesregulationsController.do?datagrid&field=id,pid,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,createBy,rulesType,rulesFileIcon,rulesName,keyword,dispatchDate,dispatchDate_begin,dispatchDate_end,createName,visible,createDate,rulesSuffix,rulesPathUrl,rulesPathUrlName,remark,iconCls,articleUnit,genreClassification,replaceRegulations,',
             pageNumber: 1,
             queryParams:queryParams
         });
     }
 }
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'famsRulesregulationsController.do?upload', "famsRulesregulationsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("famsRulesregulationsController.do?exportXls","famsRulesregulationsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("famsRulesregulationsController.do?exportXlsByT","famsRulesregulationsList");
}
//下载文件
function downloadFile(t) {
	JeecgExcelExport("famsRulesregulationsController.do?export&rulesPathUrl="+t,"famsRulesregulationsList");
	//createwindow("下载文件","famsRulesregulationsController.do?download&rulesPathUrl="+t,600,600);
}
//预览
//function previewFile(id,t){
//	createwindow("添加根目录","famsRulesregulationsController.do?openViewFile&fileid="+id+"&rulesPathUrl="+t,600,600);
//}

//添加根目录文件夹
function addFolder(id,t){
	createwindow("添加子目录","famsRulesregulationsController.do?goAdd&id="+id,600,250);
}

//删除
function delFolder(id,rulesType,rulesName){
	var fc = rulesType == 0 ? "目录" : "文件";
	//createdialog("提示消息","确认删除'"+rulesName+"'该"+fc+"吗？","famsRulesregulationsController.do?doDel&id="+id,"famsRulesregulationsList");
	createdialog("提示消息","确认删除'"+rulesName+"'？","famsRulesregulationsController.do?doDel&id="+id,"famsRulesregulationsList");
}

//更新
function updatetreeRule(){


    var rowsData = $('#famsRulesregulationsList').treegrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择编辑项目');
		return;
	}
	
	var v = $("input[type='checkbox']:checked");

	//var w = $(v).parents("td").prev().find(".datagrid-cell-rownumber").html();
	var w = $(v).parents("tr").attr("node-id");

	//id
	var id = $(".datagrid-view2").find("tr[node-id='" + (w) + "']").find("td[field='id']").find("div").html();
	var rulesType = $(".datagrid-view2").find("tr[node-id='" + (w) + "']").find("td[field='rulesType']").find("div").html();

	//--author：zhoujf---------date：20180718---------for：弹出窗口大小控制问题
	var title = "编辑";
	var addurl = "famsRulesregulationsController.do?goUpdate&id="+id;
	var width = 665;
	var height = rulesType == 0?200:550;
	
	
	
	if(width=="100%" || height=="100%"){
		width = window.top.document.body.offsetWidth;
		height =window.top.document.body.offsetHeight-100;
	}else{
		width = isRealNum(width)?width:700;
		height = isRealNum(height)?height:400;
		width=parseInt(width);
		height=parseInt(height);
	}
	//--author：zhoujf---------date：20180718---------for：弹出窗口大小控制问题
    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
	if(typeof(windowapi) == 'undefined'){
		$.dialog({
			content: 'url:'+addurl,
			lock : true,
			zIndex: getzIndex(),
			width:width,
			height:height,
			title:title,
			opacity : 0.3,
			cache:false,
		    ok: function(){
		    	iframe = this.iframe.contentWindow;
				saveObj();
				return false;
		    },
		    okVal: $.i18n.prop('dialog.submit'),
		    cancelVal: $.i18n.prop('dialog.close'),
		    cancel: true /*为true等价于function(){}*/
		});
	}else{

		/*W.*/$.dialog({//使用W，即为使用顶级页面作为openner，造成打开的次级窗口获取不到关联的主窗口
			content: 'url:'+addurl,
			lock : true,
			width:width,
			zIndex:getzIndex(),
			height:height,
			parent:windowapi,
			title:title,
			opacity : 0.3,
			cache:false,
		    ok: function(){
		    	iframe = this.iframe.contentWindow;
				saveObj();
				return false;
		    },
		    okVal: $.i18n.prop('dialog.submit'),
		    cancelVal: $.i18n.prop('dialog.close'),
		    cancel: true /*为true等价于function(){}*/
		});

	}
    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
	
}

//上传文件夹
function addFile(id,t){
	createwindow("上传文件","famsRulesregulationsController.do?goAddFile&id="+id,660,600);
}

/**
 * 获取表格对象
 * @return 表格对象
 */
function getDataGrid(){
	var datagrid = $('#'+gridname);
	return datagrid;
}
 </script>
