<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>航班保障作业检查表单配置信息</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
<style>
	.panel-body{
		border-style: none;
	}
</style>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsAircontrolSafecheckconfigController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="type" name="type" type="text" maxlength="2" value="1" />

		<%--<div class="form-group">--%>
			<%--<label for="checkProject" class="col-sm-3 control-label"><span style="color:red;">*</span>检查项目通用名称：</label>--%>
			<%--<div class="col-sm-7">--%>
				<%--<div class="input-group" style="width:100%">--%>
					<%--<input id="checkProject" name="checkProject" type="text" maxlength="10" class="form-control input-sm" placeholder="请输入检查项目通用名称"   ignore="checked" datatype="*" />--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-group">
			<label for="status" class="col-sm-3 control-label"><span style="color:red;">*</span>检查项目通用名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<t:dictSelect field="checkProject" type="list" extendJson="{class:'form-control input-sm'}"   typeGroupCode="safe_cf_cn"  hasLabel="false"  title="检查项目通用名称" datatype="*"></t:dictSelect>
				</div>
			</div>
		</div>
		<%--<div class="form-group">--%>
			<%--<label for="projectDetail" class="col-sm-3 control-label"><span style="color:red;">*</span>检查项目单个名称：</label>--%>
			<%--<div class="col-sm-7">--%>
				<%--<div class="input-group" style="width:100%">--%>
					<%--<input id="projectDetail" name="projectDetail" type="text" maxlength="128" class="form-control input-sm" placeholder="请输入检查项目单个名称"   ignore="checked" datatype="*" />--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-group">
			<label for="projectDetail" class="col-sm-3 control-label"><span style="color:red;">*</span>检查项目内容：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<textarea name="projectDetail" class="form-control input-sm" rows="3"  placeholder="请输入检查项目内容"   ignore="checked" datatype="*" ></textarea>
					<span class="Validform_checktip" style="float:left;height:0px;"></span>
					<label class="Validform_label" style="display: none">检查项目内容</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="eventName" class="col-sm-3 control-label">到场/完成时间名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="eventName" name="eventName" type="text" maxlength="50" class="form-control input-sm"
						   placeholder="请输入到场/完成时间名称"/>
				</div>
			</div>
		</div>


		<%--<div class="form-group">--%>
			<%--<label for="type" class="col-sm-3 control-label">类型：</label>--%>
			<%--<div class="col-sm-7">--%>
				<%--<div class="input-group" style="width:100%">--%>
					<%--<input id="type" name="type" type="text" maxlength="2" value="1" class="form-control input-sm" placeholder="请输入类型"  ignore="checked" datatype="*" />--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-group">
			<label for="status" class="col-sm-3 control-label">状态：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<t:dictSelect field="status" type="list" extendJson="{class:'form-control input-sm'}"   typeGroupCode="safe_cf_s"  hasLabel="false"  title="状态" datatype="*"></t:dictSelect>
				</div>
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