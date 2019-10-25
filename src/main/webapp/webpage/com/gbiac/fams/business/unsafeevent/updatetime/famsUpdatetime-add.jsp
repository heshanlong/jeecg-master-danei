<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>fams_updatetime</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsUpdatetimeController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<div class="form-group">
			<label for="no" class="col-sm-3 control-label">编号：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="no" name="no" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入编号"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="people" class="col-sm-3 control-label">作业人：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="people" name="people" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入作业人"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="module" class="col-sm-3 control-label">所属模块：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="module" name="module" type="text" maxlength="100" class="form-control input-sm" placeholder="请输入所属模块"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="intime" class="col-sm-3 control-label">插入时间：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
				<input name="intime" type="text" class="form-control laydate-date"  ignore="ignore"  />
				<span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="added" class="col-sm-3 control-label">补充说明：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="added" name="added" type="text" maxlength="255" class="form-control input-sm" placeholder="请输入补充说明"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="note" class="col-sm-3 control-label">备注：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="note" name="note" type="text" maxlength="255" class="form-control input-sm" placeholder="请输入备注"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="createBy" class="col-sm-3 control-label">创建人登录名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="createBy" name="createBy" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入创建人登录名称"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="createDate" class="col-sm-3 control-label">创建日期：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
				<input name="createDate" type="text" class="form-control laydate-date"  ignore="ignore"  />
				<span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="updateBy" class="col-sm-3 control-label">更新人登录名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="updateBy" name="updateBy" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入更新人登录名称"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="updateDate" class="col-sm-3 control-label">更新日期：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
				<input name="updateDate" type="text" class="form-control laydate-date"  ignore="ignore"  />
				<span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	$(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: this,
		  format: 'yyyy-MM-dd HH:mm:ss',
		  type: 'datetime'
		});
	});
	$(".laydate-date").each(function(){
		var _this = this;
		laydate.render({
		  elem: this
		});
	});
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