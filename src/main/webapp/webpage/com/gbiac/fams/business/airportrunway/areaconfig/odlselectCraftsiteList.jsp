<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript">

	//选择
	function saveTask() {
		var serialNumbers = "";
		var v = $("input[type='checkbox']:checked");
		$.each($("input[type='checkbox']:checked"),function(i,val){
            var w = $(val).parents("tr").attr("datagrid-row-index");
			//机位编号
			var serialNumber = $(".datagrid-view2").find("tr[datagrid-row-index='" + (w) + "']").find("td[field='serialNumber']").find("div").html();
           	if(typeof(serialNumber)!='undefined'){
            	serialNumbers = serialNumbers + serialNumber + ",";
            }
        });
		return serialNumbers;
		
		/** 获取单个
		var v = $("input[type='checkbox']:checked");
		//var w = $(v).parents("td").prev().find(".datagrid-cell-rownumber").html();
		var w = $(v).parents("tr").attr("datagrid-row-index");

		//机位id
		var id = $(".datagrid-view2").find("tr[datagrid-row-index='" + (w) + "']").find("td[field='id']").find("div").html();
		//机位编号
		var serialNumber = $(".datagrid-view2").find("tr[datagrid-row-index='" + (w) + "']").find("td[field='serialNumber']").find("div").html();
		//机位
		var craftsite = $(".datagrid-view2").find("tr[datagrid-row-index='" + (w) + "']").find("td[field='craftsite']").find("div").html();
		
		var mycars = new Array()
		mycars[0] = id;
		mycars[1] = serialNumber;
		mycars[2] = craftsite;

		return mycars;
		*/
	}

</script>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="famsAttentionCraftsiteList" checkbox="true" singleSelect="${entrance=='add'?false:true}" pagination="true" fitColumns="true" title="机位" sortName="serialNumber" sortOrder="asc" actionUrl="famsAreaConfigController.do?selectCraftsiteDatagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="serialNumber"  queryMode="single"  width="120" query="true"></t:dgCol>
   <t:dgCol title="机位"  field="craftsite"  queryMode="single"  width="120"  query="true"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 