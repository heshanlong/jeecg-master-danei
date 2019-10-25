<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<!-- <div id="famsAttentionCraftsiteViewListtb" style="padding:3px; height: auto" class="datagrid-toolbar">
	<input id="_complexSqlbuilder" name="complexSqlbuilder" type="hidden">
		<div name="searchColums" id="searchColums">
			<input id="isShowSearchId" type="hidden" value="false">
			<input id="_sqlbuilder" name="sqlbuilder" type="hidden">
				<form onkeydown="if(event.keyCode==13){famsAttentionCraftsiteViewListsearch();return false;}" id="famsAttentionCraftsiteViewListForm">
					<span style="max-width: 79%;display: inline-block;">
						<span style="display:-moz-inline-box;display:inline-block;margin-bottom:2px;text-align:justify;">
							<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 90px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; " title="姓名">&nbsp;&nbsp;&nbsp;姓名：&nbsp;&nbsp;&nbsp;</span>
							<input onkeypress="EnterPress(event)" onkeydown="EnterPress()" type="text" name="name" style="width: 120px" class="inuptxt">
						</span>
						<span style="display:-moz-inline-box;display:inline-block;margin-bottom:2px;text-align:justify;">
							<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 90px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; " title="关注航班">关注航班：</span>
							<input onkeypress="EnterPress(event)" onkeydown="EnterPress()" type="text" name="flycode" style="width: 120px" class="inuptxt">
						</span>
						<span style="display:-moz-inline-box;display:inline-block;margin-bottom:2px;text-align:justify;"><span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 90px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; " title="关注机位">关注机位：</span>
							<input onkeypress="EnterPress(event)" onkeydown="EnterPress()" type="text" name="serial" style="width: 120px" class="inuptxt">
						</span>
					</span>
					<span>
						<span style="float:right;">
							<a href="#" class="easyui-linkbutton l-btn" iconcls="icon-search" onclick="famsAttentionCraftsiteViewListsearch()" id="">
								<span class="l-btn-left">
									<span class="l-btn-text icon-search l-btn-icon-left">查询</span>
								</span>
							</a>
							<a href="#" class="easyui-linkbutton l-btn" iconcls="icon-reload" onclick="searchReset('famsAttentionCraftsiteViewList')" id="">
								<span class="l-btn-left">
									<span class="l-btn-text icon-reload l-btn-icon-left">重 置</span>
								</span>
							</a>
						</span>
					</span>
				</form>
		</div>
		<div style="border-bottom-width:0;" class="datagrid-toolbar">
			<span style="float:left;">
				<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-search" onclick="detail('查看','famsAttentionCraftsiteViewController.do?goUpdate','famsAttentionCraftsiteViewList',null,null)" id="">
					<span class="l-btn-left">
						<span class="l-btn-text icon-search l-btn-icon-left">查看</span>
					</span>
				</a>
			</span>
			<div style="clear:both"></div>
		</div>
</div> -->

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="famsAttentionCraftsiteViewList" checkbox="false" pagination="true" fitColumns="true" title="查看关注" sortName="serialNumber" actionUrl="famsAttentionCraftsiteViewController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="serialNumber"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="name"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关注航班"  field="flycode"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关注机位"  field="serial"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="famsAttentionCraftsiteViewController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
 <%--   <t:dgToolBar title="录入" icon="icon-add" url="famsAttentionCraftsiteViewController.do?goAdd" funname="add"></t:dgToolBar>
	<t:dgToolBar title="编辑" icon="icon-edit" url="famsAttentionCraftsiteViewController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="famsAttentionCraftsiteViewController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="famsAttentionCraftsiteViewController.do?goUpdate" funname="detail"></t:dgToolBar>
  <%--  <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'famsAttentionCraftsiteViewController.do?upload', "famsAttentionCraftsiteViewList");
}

//导出
function ExportXls() {
	JeecgExcelExport("famsAttentionCraftsiteViewController.do?exportXls","famsAttentionCraftsiteViewList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("famsAttentionCraftsiteViewController.do?exportXlsByT","famsAttentionCraftsiteViewList");
}

 </script>
