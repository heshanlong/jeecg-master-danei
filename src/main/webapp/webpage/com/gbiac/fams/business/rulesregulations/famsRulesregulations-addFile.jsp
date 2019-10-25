<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>规章制度</title>
<!--
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,tools,DatePicker,common"></t:base>
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
<t:base type="jquery,easyui,tools,DatePicker,bootstrap,"></t:base>
-->
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>

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
		

		$(".laydate-datetime").each(function(){
			var _this = this;
			laydate.render({
		  	elem: this,
		  	max: crtTimeFtt2(new Date()),
			ready: function(date){
			  	 $(_this).val(DateJsonFormat(date,this.format));
			  }
			});
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
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr style="background-color: #fff;">
					<td align="right">
						<label class="Validform_label">
							<span style="color:red; margin-top:5px;">*</span>文件目录名称:
						</label>
					</td>
					<td class="value">
					     	<input id="pid" name="pid" type="hidden" style="width: 300px" class="inputxt" value='${famsRulesregulationsPage.id}' ignore="ignore" />
					     	<input id="" name="" type="text" maxlength="100" disabled="disabled" style="width: 300px;height: 30px;" class="inputxt" value='${famsRulesregulationsPage.rulesName}' datatype="*" />
							<input id="rulesType" name="rulesType" type="hidden" style="width: 150px" class="inputxt"  datatype="n"  value="1" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
				</tr>
				<!--<tr style="background-color: #fff;">
					<td align="right">
						<label class="Validform_label">
							<span style="color:red; margin-top:5px;">*</span>文件名称:
						</label>
					</td>
					<td class="value">
					     	<input id="rulesName" name="rulesName" type="text" maxlength="20" style="width: 300px;height: 30px;" class="inputxt" value='' datatype="*" onchange="onkeyupcc()"/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">文件名称</label>
						</td>
				</tr>-->
				<tr style="background-color: #fff;">
					<td align="right">
						<label class="Validform_label">
							<span style="color:red; margin-top:5px;">*</span>部号:
						</label>
					</td>
					<td class="value">
					     	<input id="keyword2" name="keyword" type="text" maxlength="20" style="width: 300px;height: 30px;" class="inputxt" value='' datatype="*" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部号</label>
						</td>
				</tr>
				<tr style="background-color: #fff;">
					<td align="right">
						<label class="Validform_label">
							<span style="color:red; margin-top:5px;">*</span>查看权限:
						</label>
					</td>
					<td class="value">
					     	<input id="visible" name="visible" type="radio" value='0' checked="checked" datatype="*"/>所有人&nbsp;&nbsp;&nbsp;
					     	<input id="visible" name="visible" type="radio" value='1' datatype="*"/>飞管内部可见&nbsp;&nbsp;&nbsp;
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">查看权限</label>
						</td>
				</tr>
				<tr style="background-color: #fff;">
					<td align="right">
						<label class="Validform_label">
							发文日期:
						</label>
					</td>
					<td class="value">
					     	<input id="dispatchDate" name="dispatchDate" type="text" maxlength="20" style="width: 300px;height: 30px;" class="inputxt Wdate form-control laydate-datetime" value='' />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发文日期</label>
						</td>
				</tr>
				<tr style="background-color: #fff;">
					<td align="right">
						<label class="Validform_label">
							办文单位:
						</label>
					</td>
					<td class="value">
					     	<input id="articleUnit" name="articleUnit" type="text" maxlength="20" style="width: 300px;height: 30px;" class="inputxt" value='' />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">办文单位</label>
						</td>
				</tr>
				<tr style="background-color: #fff;">
					<td align="right">
						<label class="Validform_label">
							体裁分类:
						</label>
					</td>
					<td class="value">
					     	<input id="genreClassification" name="genreClassification" type="text" maxlength="20" style="width: 300px;height: 30px;" class="inputxt" value=''/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">体裁分类</label>
						</td>
				</tr>
				<tr style="background-color: #fff;">
					<td align="right">
						<label class="Validform_label">
							代替规章:
						</label>
					</td>
					<td class="value">
					     	<input id="replaceRegulations" name="replaceRegulations" type="text" maxlength="20" style="width: 300px;height: 30px;" class="inputxt" value=''/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">代替规章</label>
						</td>
				</tr>
				<tr style="background-color: #fff;">
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
							<textarea rows="3" cols="20" name="remark"  class="inputxt" style="margin: 0px; width: 300px; height: 80px;"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
				</tr>
				<tr style="background-color: #fff;">
					<td align="right">
						<label class="Validform_label">
							<span style="color:red; margin-top:5px;">*</span>文件:
						</label>
					</td>
					<td class="value" style="background-color: #fff;">
					     	<!-- <input id="rulesPathUrl" name="rulesPathUrl" type="text" maxlength="200" style="width: 150px" class="inputxt"  ignore="ignore" /> -->
					     	
					     	<div class="form-group">
    						  <label for="isEnable" class="col-sm-3 control-label"></label>
    						  <div class="col-sm-7">
    						  
        						<jsp:include page= "/webpage/common/jsp/upload.jsp">
            					<jsp:param name="name" value="rulesPathUrl"/>
            					<jsp:param name="value" value=""/>
            					<jsp:param name="directory" value="rulesRegulations"/>
            					<jsp:param name="multiple" value="multiple"/>
					            <jsp:param name= "ids" value= ""/>
					            <jsp:param name= "fileNames" value= ""/>
					            <jsp:param name= "sizes" value= ""/>
					            <jsp:param name= "typeSwf" value= "1"/>
					            <jsp:param name= "required" value= "required"/>
					            <jsp:param name= "acceptFileType" value= ".doc,.docx,.ppt,.pptx,.xlsx,.xls,.pdf"/>
					            <jsp:param name= "distinct" value= "rulesregulations"/>
					            <jsp:param name= "module" value= "${famsRulesregulationsPage.id}"/>
        						</jsp:include>
    						  </div>
							</div>
					     	
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">文件</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
