<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>施工管理流程节点信息表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsWorkNodeInfoController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<div class="form-group">
			<label for="taskKey" class="col-sm-3 control-label">任务key：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="taskKey" name="taskKey" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入任务key"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="taskState" class="col-sm-3 control-label">任务状态：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="taskState" name="taskState" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入任务状态"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="taskName" class="col-sm-3 control-label">任务名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="taskName" name="taskName" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入任务名称"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="funname" class="col-sm-3 control-label">执行的方法名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="funname" name="funname" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入执行的方法名称"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="flag" class="col-sm-3 control-label">方法标识：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="flag" name="flag" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入方法标识"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="jumpurl" class="col-sm-3 control-label">跳转路径：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="jumpurl" name="jumpurl" type="text" maxlength="200" class="form-control input-sm" placeholder="请输入跳转路径"  ignore="ignore" />
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