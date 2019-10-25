<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>APP版本管理</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsVersionController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<div class="form-group">
			<label for="version" class="col-sm-3 control-label">版本号：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="version" name="version" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入版本号"  datatype="*" ignore="checked" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">版本名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="name" name="name" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入版本名称"  datatype="*" ignore="checked" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="platform" class="col-sm-3 control-label">平台：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<t:dictSelect field="platform" type="list" extendJson="{class:'form-control input-sm'}"   typeGroupCode="platform"  hasLabel="false"  title="平台" datatype="*"></t:dictSelect>
				</div>
			</div>
		</div>
		<%--<div class="form-group">
			<label for="size" class="col-sm-3 control-label">版本大小：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="size" name="size" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入版本大小"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="url" class="col-sm-3 control-label">下载地址：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="url" name="url" type="text" maxlength="1000" class="form-control input-sm" placeholder="请输入下载地址"  ignore="ignore" />
				</div>
			</div>
		</div>--%>
		<div class="form-group">
			<label for="remark" class="col-sm-3 control-label">版本介绍：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
						<textarea name="remark" class="form-control input-sm" rows="6"  datatype="*" ignore="checked" ></textarea>
					<span class="Validform_checktip" style="float:left;height:0px;"></span>
					<label class="Validform_label" style="display: none">版本介绍</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">文件：</label>
			<div class="col-sm-7">
				<jsp:include page= "/webpage/common/jsp/upload.jsp">
					<jsp:param name= "name" value= "files"/>
					<jsp:param name= "ids" value= ""/>
					<jsp:param name= "value" value= ""/>
					<jsp:param name= "fileNames" value= ""/>
					<jsp:param name= "sizes" value= ""/>
					<jsp:param name= "directory" value= "version"/>
					<jsp:param name= "multiple" value= "multiple"/>
					<jsp:param name= "required" value= "required"/>
					<jsp:param name= "disable" value= ""/>
					<jsp:param name= "acceptFileType" value= ".apk,.ipa"/>
				</jsp:include>
			</div>
		</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	//单选框/多选框初始化
	$('.i-checks').iCheck({
		labelHover : false,
		cursor : true,
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%'
	});
	
	//表单提交
	$("#formobj").Validform({
		tiptype:function(msg,o,cssctl){
			if(o.type==3){
				validationMessage(o.obj,msg);
			}else{
				removeMessage(o.obj);
			}
		},
		btnSubmit : "#btn_sub",
		btnReset : "#btn_reset",
		ajaxPost : true,
		beforeSubmit : function(curform) {
		},
		usePlugin : {
			passwordstrength : {
				minLen : 6,
				maxLen : 18,
				trigger : function(obj, error) {
					if (error) {
						obj.parent().next().find(".Validform_checktip").show();
						obj.find(".passwordStrength").hide();
					} else {
						$(".passwordStrength").show();
						obj.parent().next().find(".Validform_checktip").hide();
					}
				}
			}
		},
		callback : function(data) {
			var win = frameElement.api.opener;
			if (data.success == true) {
				frameElement.api.close();
			    win.reloadTable();
			    win.tip(data.msg);
			} else {
			    if (data.responseText == '' || data.responseText == undefined) {
			        $.messager.alert('错误', data.msg);
			        $.Hidemsg();
			    } else {
			        try {
			            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
			            $.messager.alert('错误', emsg);
			            $.Hidemsg();
			        } catch (ex) {
			            $.messager.alert('错误', data.responseText + "");
			            $.Hidemsg();
			        }
			    }
			    return false;
			}
		}
	});
		
});
</script>
</body>
</html>