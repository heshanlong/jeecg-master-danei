<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>规章制度</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style type="text/css">
  	.combo_self{height: 22px !important;width: 150px !important;}
  	.layout-header .btn {
	    margin:0;
	   float: none !important;
	}
	.btn-default {
	    height: 35px;
	    line-height: 35px;
	    font-size:14px;
	}
  </style>
  
  <script type="text/javascript">
	$(function(){
		$(".combo").removeClass("combo").addClass("combo combo_self");
		$(".combo").each(function(){
			$(this).parent().css("line-height","0px");
		});   
	});
  		
  		 /**树形列表数据转换**/
  function convertTreeData(rows, textField) {
      for(var i = 0; i < rows.length; i++) {
          var row = rows[i];
          row.text = row[textField];
          if(row.children) {
          	row.state = "open";
              convertTreeData(row.children, textField);
          }
      }
  }
  /**树形列表加入子元素**/
  function joinTreeChildren(arr1, arr2) {
      for(var i = 0; i < arr1.length; i++) {
          var row1 = arr1[i];
          for(var j = 0; j < arr2.length; j++) {
              if(row1.id == arr2[j].id) {
                  var children = arr2[j].children;
                  if(children) {
                      row1.children = children;
                  }
                  
              }
          }
      }
  }
</script>
<script src="webpage/com/gbiac/fams/business/rulesregulations/js/famsRulesregulations.js" type="text/javascript"></script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="famsRulesregulationsController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${famsRulesregulationsPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<c:if test="${famsRulesregulationsPage.id!=null && famsRulesregulationsPage.id!=''}">	
				<tr>
					<td align="right">
						<label class="Validform_label">
							父目录:
						</label>
					</td>
					<td class="value">
					        <!--
							<input id="pid" name="pid" type="text" disabled="disabled" style="width: 300px" class="inputxt easyui-combotree"  ignore="ignore" 
							data-options="panelHeight:'220',
				                    url: 'famsRulesregulationsController.do?datagrid&field=id,rulesName,addFile_rulesType',  
				                    loadFilter: function(data) {
				                    	var rows = data.rows || data;
				                    	var win = frameElement.api.opener;
				                    	var listRows = win.getDataGrid().treegrid('getData');
				                    	joinTreeChildren(rows, listRows);
				                    	convertTreeData(rows, 'rulesName');
				                    	return rows; 
				                    },
				                    onSelect:function(node){
				                    	$('#pid').val(node.id);
				                    },
				                    onLoadSuccess: function() {
				                    	var win = frameElement.api.opener;
				                    	var currRow = win.getDataGrid().treegrid('getSelected');
				                    	if(!'${famsRulesregulationsPage.id}') {
				                    		//增加时，选择当前父菜单
				                    		if(currRow) {
				                    			//$('#pid').combotree('setValue', currRow.id);
				                    			$('#pid').combotree('setValue', '');
				                    		}
				                    	}else {
				                    		//编辑时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#pid').combotree('setValue', currRow.pid);
				                    		}
				                    	}
				                    }"/>
				                 -->
				            <input id="" name="" type="text" maxlength="100" disabled="disabled" style="width: 300px" class="inputxt" value='${famsRulesregulationsPage.rulesName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">pid</label>
						</td>
				</tr>
			</c:if>
				<tr>
					<td align="right">
						<label class="Validform_label">
							${famsRulesregulationsPage.id!=null?'子':'根'}目录名称:
						</label>
					</td>
					<td class="value">
							<input id="pid" name="pid" type="hidden" style="width: 150px" class="inputxt" value='${famsRulesregulationsPage.id}' />
					        <input id="rulesType" name="rulesType" type="hidden" style="width: 150px" class="inputxt"  datatype="n"  value="0" />
					     	<input id="rulesName" name="rulesName" type="text" maxlength="100" style="width: 300px" class="inputxt"  datatype="*"  onkeyup = "validateValue(this)"  onchange="onkeyupcc()" />
							<span style="color:red; margin-top:5px;">*</span>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">目录名称</label>
					</td>
				</tr>
				
			</table>
		</t:formvalid>
 </body>
